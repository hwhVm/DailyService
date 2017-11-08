package com.beini.controller;

import com.beini.http.FileResponse;
import com.beini.util.BLog;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    /**
     * 请求下载 的时候应该返回：
     * 1  文件修改时间；
     * 2   总长度
     *
     * @param file
     * @param request
     * @param model
     * @param out
     */
    @RequestMapping(value = "breakpointdownload")
    public void download(
            @RequestParam(value = "file", required = false) MultipartFile file,
            HttpServletRequest request, HttpServletResponse response, ModelMap model, PrintWriter out) throws IOException {

        String contentRange = request.getHeader("Content-Range");
        String range = request.getHeader("Range");
        BLog.d("       contentRange=" + contentRange + "   range=" + range);

        String filename = "D:\\javaee\\daily\\DailyService\\out\\artifacts\\DailyService_war_exploded\\upload\\zz.zip";
        BLog.d("          new File(filename).exists()=  " + new File(filename).exists());
        RandomAccessFile raFile = new RandomAccessFile(filename, "r");

        int start = 0, end = 0;
        if (null != range && range.startsWith("bytes=")) {
            String[] values = range.split("=")[1].split("-");
            start = Integer.parseInt(values[0]);
            end = Integer.parseInt(values[1]);
        }
        int requestSize = 0;
        if (end != 0 && end > start) {
            requestSize = end - start + 1;
            response.addHeader("content-length", "" + (requestSize));
        } else {
            requestSize = Integer.MAX_VALUE;
        }
        byte[] buffer = new byte[4096];
        response.setContentType("application/x-download");
        filename = new String(filename.getBytes("UTF-8"), "ISO8859-1");
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);
        ServletOutputStream os = response.getOutputStream();
        int needSize = requestSize;
        raFile.seek(start);
        while (needSize > 0) {
            int len = raFile.read(buffer);
            if (needSize < buffer.length) {
                os.write(buffer, 0, needSize);
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
