package com.example.backend_hometask.Controller;

import com.example.backend_hometask.Payload.UploadFileResponse;
import com.example.backend_hometask.Service.FileUploadService;
import com.example.backend_hometask.Service.StatusService;
import com.example.backend_hometask.Service.UploadService;
import com.example.backend_hometask.Service.UploadStatusService;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;

@RequestMapping( "/dependency/file")
@RestController
public class FileController {

    @GetMapping("/hello2")
    public String callApp(@RequestParam(value = "name", defaultValue = "Saumya") String name) {
        return String.format("Congratulations  %s, your base Spring Boot Application Works !!", name);
    }

    @PostMapping("/uploads")
    public UploadFileResponse uploadDependencyFile (@RequestParam("file") MultipartFile file) throws Exception {
        UploadService uploadService = new FileUploadService();
        UploadFileResponse uploadFileResponse = uploadService.uploadToDebrickedRepository(file);
        return uploadFileResponse;
    }

    @GetMapping("/checkUploadStatus")
    public String checkUploadStatus(@RequestParam("ciUploadId") Integer ciUploadId) throws IOException, JSONException, URISyntaxException, InterruptedException {
        StatusService statusService = new UploadStatusService();
        return statusService.checkStatus(ciUploadId);
    }
}
