package com.example.mock_interview_be.resumeparser.service.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface ClamAVScannerService {
    public void scanFile(MultipartFile file);
}
