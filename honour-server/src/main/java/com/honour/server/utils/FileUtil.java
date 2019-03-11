package com.honour.server.utils;

import com.honour.server.constant.FileTypeEnum;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
public class FileUtil {

    public static int getFileType(String fileName) {
        int type = FileTypeEnum.OTHER.code();
        if (fileName.contains(".mp4")) {
            type = FileTypeEnum.VIDEO.code();
        } else if (fileName.contains(".mp3")) {
            type = FileTypeEnum.AUDIO.code();
        } else if ((fileName.contains(".jpg")) ||
                (fileName.contains(".png")) ||
                (fileName.contains(".gif")) ||
                (fileName.contains(".jpeg")) ||
                (fileName.contains(".JPG")) ||
                (fileName.contains(".JPEG")) ||
                (fileName.contains(".PNG")) ||
                (fileName.contains(".GIF"))) {
            type = FileTypeEnum.IMG.code();
        }
        return type;
    }

    public static String getAbsolutePath(String fileDir, String fileName) {
        String secondDir = "";
        int type = getFileType(fileName);
        if (type == FileTypeEnum.IMG.code()) {
            secondDir = "/image/";
        } else if (type == FileTypeEnum.VIDEO.code()) {
            secondDir = "/video/";
        } else if (type == FileTypeEnum.AUDIO.code()) {
            secondDir = "/audio/";
        } else {
            secondDir = "/other/";
        }
        String filePath = fileDir + secondDir;
        File directory = new File(filePath);
        if (!directory.exists()) {
            boolean flag = directory.mkdirs();
            if (flag) {
                log.info("创建文件夹成功，filePath = {}", filePath);
            } else {
                log.error("创建文件夹失败，filePath = {}", filePath);
            }
        }
        return filePath + fileName;
    }
}
