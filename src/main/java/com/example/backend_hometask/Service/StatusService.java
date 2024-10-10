package com.example.backend_hometask.Service;

import org.springframework.boot.configurationprocessor.json.JSONException;
import java.io.IOException;
import java.net.URISyntaxException;

public interface StatusService {
    String checkStatus(Integer ciUploadId) throws URISyntaxException, IOException, InterruptedException, JSONException;
}
