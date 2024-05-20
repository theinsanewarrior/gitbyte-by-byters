package com.poc.aide.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface UploadService {
    String uploadFile(MultipartFile file, Long repoId);
    String presignedUrl(String filename);
}
