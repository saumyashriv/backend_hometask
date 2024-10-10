package com.example.backend_hometask.Service;

import com.example.backend_hometask.Payload.UploadFileResponse;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    UploadFileResponse uploadToDebrickedRepository(MultipartFile file) throws Exception;
}
