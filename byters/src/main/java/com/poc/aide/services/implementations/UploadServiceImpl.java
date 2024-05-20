package com.poc.aide.services.implementations;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.poc.aide.entities.Repo;
import com.poc.aide.exceptions.UploadException;
import com.poc.aide.repositories.RepoRepository;
import com.poc.aide.services.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class UploadServiceImpl implements UploadService {
    @Autowired
    private RepoRepository repoRepository;
    @Autowired
    private AmazonS3 amazonS3Client;

    @Value("${app.s3.bucket}")
    private String bucket;

    @Override
    public String uploadFile(MultipartFile file, Long repoId) {
        String actualFileName = file.getOriginalFilename();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        try {
            PutObjectResult putObjectResult = amazonS3Client.putObject(new PutObjectRequest(bucket, actualFileName, file.getInputStream(), metadata));
            Repo repo = repoRepository.findById(repoId).get();
            repo.setScriptUrl(this.presignedUrl(actualFileName));
            return repoRepository.save(repo).getScriptUrl();
        } catch (IOException ex){
            throw new UploadException("Failed to Upload: "+ex.getMessage());
        }
    }

    @Override
    public String presignedUrl(String filename) {
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucket, filename);
        return amazonS3Client.generatePresignedUrl(generatePresignedUrlRequest).toString();
    }
}
