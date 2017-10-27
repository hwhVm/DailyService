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

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.PrintWriter;

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
}
