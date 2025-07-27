package com.zhora.admin.service;

import org.springframework.web.multipart.MultipartFile;

public interface LangChain4jService {
    String upload(MultipartFile file);

    String search(String message);
}
