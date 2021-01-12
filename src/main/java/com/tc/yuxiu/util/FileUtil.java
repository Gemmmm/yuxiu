package com.tc.yuxiu.util;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FileUtil {


    /**
     * @param file
     * @param path
     * @return
     */
    public static String uploadImage(MultipartFile file, String path) {
        String fileName = null;
        //随机名处理

        System.out.println("file:" + file.getOriginalFilename());
        try {
            String filename = file.getOriginalFilename();
            //System.out.println("filename:" + filename);
            if (file != null && !"".equals(filename)) {
                String suffix = filename.substring(filename.lastIndexOf("."));

                InputStream inputStream = file.getInputStream();
                File folder = new File(Constants.PicPath);
                if (!folder.exists()) {
                    folder.mkdirs();
                }
                File targetFile = new File(folder.getPath() + "test" + suffix);
                if (!targetFile.exists()) {
                    targetFile.createNewFile();
                }
                byte[] buffer = new byte[8192];
                int bytesRead = 0;
                FileOutputStream out = new FileOutputStream(targetFile);
                while ((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
                inputStream = new FileInputStream(targetFile);

                fileName = DateUtil.thisDayBeginNew() + "/" + System.currentTimeMillis() + suffix;
                String flag = OssUtil.uploadFile(inputStream, path, fileName);
                if (flag != null && !"".equals(flag)) {
                    return path + "/" + fileName;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }


    public static String uploadImage(MultipartFile file, HttpServletRequest request, String folderName) {
        String tomcatPath = request.getSession().getServletContext().getRealPath("/upload");
        //随机名处理
        try {
            String filename = file.getOriginalFilename();
            System.out.println("filename:" + filename);
            if (file != null && !"".equals(filename)) {
                String suffix = filename.substring(filename.lastIndexOf("."));
                InputStream inputStream = file.getInputStream();
                File folder = new File(tomcatPath);
                if (!folder.exists()) {
                    folder.mkdirs();
                }
                File targetFile = new File(folder.getPath() + "/" + filename);
                if (!targetFile.exists()) {
                    targetFile.createNewFile();
                }
                byte[] buffer = new byte[8192];
                int bytesRead = 0;
                FileOutputStream out = new FileOutputStream(targetFile);
                while ((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
                inputStream = new FileInputStream(targetFile);
                filename = DateUtil.thisDayBeginNew() + "/" + System.currentTimeMillis() + suffix;
                System.out.println("filename:" + filename);
                String flag = OssUtil.uploadFile(inputStream, folderName, filename);
                System.out.println("flag:" + flag);
                if (flag != null && !"".equals(flag)) {
                    return folderName + "/" + filename;
                }
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

}
