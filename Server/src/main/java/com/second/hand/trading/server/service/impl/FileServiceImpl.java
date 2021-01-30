package com.second.hand.trading.server.service.impl;

import com.second.hand.trading.server.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author myl
 * @create 2020-12-20  20:39
 */
@Service
public class FileServiceImpl implements FileService {

    @Value("${userFilePath}")
    private String userFilePath;

    public boolean uploadFile(MultipartFile multipartFile,String fileName)throws IOException {
        File fileDir = new File(userFilePath);
        if (!fileDir.exists()) {
            if (!fileDir.mkdirs()) {
                return false;
            }
        }
//        System.out.println(fileDir.getAbsolutePath() +"/"+fileName);
        File file = new File(fileDir.getAbsolutePath() +"/"+fileName);
        if (file.exists()) {
            if (!file.delete()) {
                return false;
            }
        }
        if (file.createNewFile()) {
            multipartFile.transferTo(file);
            return true;
        }
        return false;
    }
}
