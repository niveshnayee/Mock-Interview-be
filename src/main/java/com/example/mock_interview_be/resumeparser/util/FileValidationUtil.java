package com.example.mock_interview_be.resumeparser.util;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class FileValidationUtil {
    private static final long MAX_SIZE = 1024 * 1024; // 1 MB
    private static final List<String> ALLOWED_TYPES =
            List.of("application/pdf",
                    "application/msword",
                    "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
                    "application/txt",
                    "text/plain");

    public static void validateResumeFile(MultipartFile file){
        if(file == null || file.isEmpty())
            throw new IllegalArgumentException("File is empty or missing");
        if(file.getSize() > MAX_SIZE)
            throw new IllegalArgumentException("File size exceeds limit of 1MB");
        if(!ALLOWED_TYPES.contains(file.getContentType()))
            throw new IllegalArgumentException("Unsupported file type: " + file.getContentType());
    }
}
