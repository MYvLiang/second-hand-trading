package com.second.hand.trading.server.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author myl
 * @create 2020-12-20  20:39
 */
public interface FileService {

    boolean uploadFile(MultipartFile multipartFile,String fileName)throws IOException;
}
