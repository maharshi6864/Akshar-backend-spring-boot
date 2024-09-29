package com.aksharspringboot.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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

    public boolean uploadFileToS3(String fileName, InputStream inputStream, long contentLength) {
        try {
            // Build the PutObjectRequest to upload the SVG directly from memory
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fileName)
                    .build();

            // Create a request body from the InputStream
            RequestBody requestBody = RequestBody.fromInputStream(inputStream, contentLength);

            // Upload the file directly to S3
            s3Client.putObject(putObjectRequest, requestBody);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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

    public byte[] zipSvgFiles(List<String> filePaths) {
        // Create an output stream for the zip file
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        // Use ZipOutputStream to zip the files
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream)) {
            for (String filePath : filePaths) {
                // Ensure the file path is valid and ends with .svg
                if (filePath != null && filePath.endsWith(".svg")) {
                    // Create the S3 GetObject request for each file
                    GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                            .bucket(bucketName)  // Assume bucketName is available in this class
                            .key(filePath)
                            .build();

                    // Fetch the file from S3
                    try (ResponseInputStream<GetObjectResponse> s3Object = s3Client.getObject(getObjectRequest)) {
                        // Use only the file name for the zip entry (not the full path)
                        ZipEntry zipEntry = new ZipEntry(Paths.get(filePath).getFileName().toString());
                        zipOutputStream.putNextEntry(zipEntry);

                        // Transfer data from the S3 object to the zip output stream
                        s3Object.transferTo(zipOutputStream);

                        // Close the current zip entry
                        zipOutputStream.closeEntry();
                    } catch (IOException e) {
                        System.err.println("Error reading from S3 object " + filePath + ": " + e.getMessage());
                    }
                } else {
                    // Optionally log invalid file paths
                    System.err.println("Invalid file path: " + filePath);
                }
            }
        } catch (IOException e) {
            System.err.println("Error creating zip output stream: " + e.getMessage());
        }

        // Return the zip file as a byte array
        return byteArrayOutputStream.toByteArray();
    }


}