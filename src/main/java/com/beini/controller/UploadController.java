package com.beini.controller;

import com.beini.bean.FileRequestBean;
import com.beini.bean.UserBean;
import com.beini.http.FileResponse;
import com.beini.util.BLog;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.rmi.runtime.Log;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by beini on 2017/10/26.
 */
@Controller
public class UploadController {
    /**
     * 接收单文件
     *
     * @param file
     * @param request
     * @param model
     * @param out
     */
    @RequestMapping(value = "upload")
    public void uploadFile(
            @RequestParam(value = "file", required = false) MultipartFile file,
            HttpServletRequest request, ModelMap model, PrintWriter out) {
        BLog.d("      uploadFile   ");
        String path = request.getSession().getServletContext()
                .getRealPath("upload");


        BLog.d("  fileName=" + file + " path== " + path);

        String fileName = file.getOriginalFilename();

        File targetFile = new File(path, fileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        FileResponse fileResponse = new FileResponse();
        fileResponse.setReturnCode(0);
        fileResponse.setReturnMessage("error msg");
        fileResponse
                .setFileId(request.getContextPath() + "/upload/" + fileName);
        Gson gson = new Gson();
        out.write(gson.toJson(fileResponse));
    }

    /**
     * 接收多文件
     *
     * @param files
     * @param request
     * @param model
     * @param out
     */
    @RequestMapping(value = "multipart_upload")
    public void uploadMultipartFile(
            @RequestParam(value = "file", required = false) MultipartFile[] files,
            HttpServletRequest request, ModelMap model, PrintWriter out) {

        String path = request.getSession().getServletContext()
                .getRealPath("upload");
        BLog.d("    files.length=" + files.length + "    path=" + path);

        for (MultipartFile fileSingle : files) {
            String fileName = fileSingle.getOriginalFilename();
            File targetFile = new File(path, fileName);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            try {
                fileSingle.transferTo(targetFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        FileResponse fileResponse = new FileResponse();
        fileResponse.setReturnCode(0);
        fileResponse.setReturnMessage("error msg");
        fileResponse
                .setFileId(request.getContextPath() + "/upload/");
        Gson gson = new Gson();
        out.write(gson.toJson(fileResponse));
    }

    @RequestMapping(value = "returnFileInfo",method = RequestMethod.POST)
    public @ResponseBody
    String returnFileInfo(@RequestBody FileRequestBean bean) {
        String filePath = "D:\\javaee\\daily\\DailyService\\out\\artifacts\\DailyService_war_exploded\\upload\\upload.zip"; //根据id/username查出来,暂时固定测速
        File file = new File(filePath);
        FileRequestBean newFileRequestBean = new FileRequestBean();
        newFileRequestBean.setFilePath(filePath);
        newFileRequestBean.setLastModified(file.lastModified());
        newFileRequestBean.setFileSize(file.length());
        newFileRequestBean.setFileName("");
        newFileRequestBean.setId(1);
        newFileRequestBean.setRange("3");
        return new Gson().toJson(newFileRequestBean);
    }


    /**
     * 请求下载 的时候应该返回：
     * 1  文件修改时间；
     * 2   总长度
     *
     * @param request
     */
    @RequestMapping(value = "breakpointdownload")
    public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String contentRange = request.getHeader("Content-Range");

        String range = request.getHeader("Range");
//        String filePath = request.getParameter("filePath");//可能为空
        String filePath = "D:\\javaee\\daily\\DailyService\\out\\artifacts\\DailyService_war_exploded\\upload\\upload.zip"; //根据id/username查出来,暂时固定测速

        BLog.d("          new File(filename).exists()=  " + new File(filePath).exists());

        File file1 = new File(filePath);
        long maxLength = file1.length();
        long lastModified = file1.lastModified();

        BLog.d("   range=" + range + "   file.length()()=" + maxLength + "     lastModified=" + lastModified);

        long start = Long.parseLong(range), end = maxLength;

        long requestSize;
        if (end != 0 && end > start) {
            requestSize = end - start;
            response.addHeader("content-length", "" + (requestSize));
        } else {
            requestSize = Integer.MAX_VALUE;
        }
        response.addHeader("If-Range", String.valueOf(lastModified));

        response.setContentType("application/x-download");
        filePath = new String(filePath.getBytes("UTF-8"), "ISO8859-1");
        response.addHeader("Content-Disposition", "attachment;filename=" + filePath);


        RandomAccessFile raFile = new RandomAccessFile(filePath, "r");
        byte[] buffer = new byte[4096];
        ServletOutputStream os = response.getOutputStream();
        long needSize = requestSize;
        raFile.seek(start);

        while (needSize > 0) {
            int len = raFile.read(buffer);
            if (needSize < buffer.length) {
                os.write(buffer, 0, (int) needSize);
            } else {
                os.write(buffer, 0, len);
                if (len < buffer.length) {
                    break;
                }
            }
            needSize -= buffer.length;
        }

        raFile.close();
        os.close();

    }
}
