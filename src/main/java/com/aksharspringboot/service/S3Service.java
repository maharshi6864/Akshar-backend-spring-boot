package com.aksharspringboot.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;

@Slf4j
@Service
@Transactional
public class S3Service {


    @Autowired
    private S3Client s3Client;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName; // Add this property in your application.yml or application.properties

    public void uploadFile(String fileName, String filePath) {
        File file = new File(filePath);
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build();

        s3Client.putObject(putObjectRequest, file.toPath());
    }

    public byte[] downloadFile(String fileName) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build();

        try {
            ResponseInputStream<GetObjectResponse> responseInputStream = s3Client.getObject(getObjectRequest);
            return responseInputStream.readAllBytes();
        } catch (Exception e) {
            log.info("Failed to download object from S3.");
            e.printStackTrace();
            return null;
        }
    }
}

