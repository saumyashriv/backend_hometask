package com.example.backend_hometask.Service;

import com.example.backend_hometask.Helper.HTTPRequestMultipartBody;
import com.example.backend_hometask.Payload.UploadFileResponse;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class FileUploadService implements UploadService {
    URL url = new URL("https://debricked.com/api/1.0/open/uploads/dependencies/files");
    HttpURLConnection connection;

    public FileUploadService() throws IOException {
        connection = (HttpURLConnection) url.openConnection();
    }

    public UploadFileResponse uploadToDebrickedRepository(MultipartFile file) throws Exception {
        HttpResponse<String> response = createRequest(file);

        JSONObject jsonObject = new JSONObject(response.body());

        System.out.println(jsonObject);
        UploadFileResponse uploadFileResponse = new UploadFileResponse();
        uploadFileResponse.setCiUploadId((Integer) jsonObject.get("ciUploadId"));
        uploadFileResponse.setUploadProgramsFileId((Integer) jsonObject.get("uploadProgramsFileId"));
        uploadFileResponse.setTotalScans((Integer) jsonObject.get("totalScans"));
        uploadFileResponse.setRemainingScans((Integer) jsonObject.get("remainingScans"));
        uploadFileResponse.setEstimatedDaysLeft((Integer) jsonObject.get("estimatedDaysLeft"));
        uploadFileResponse.setPercentage((Float) jsonObject.get("percentage"));

        Integer ciId = (Integer) jsonObject.get("ciUploadId");
        System.out.println(ciId);
        return uploadFileResponse;
    }


    private HttpResponse<String> createRequest(MultipartFile file) throws IOException, URISyntaxException, InterruptedException {


        HTTPRequestMultipartBody multipartBody = new HTTPRequestMultipartBody.Builder()
                .addPart("name", file.getName())
                .addPart("fileData", file, file.getContentType(), file.getOriginalFilename())
                .addPart("repositoryName", "SampleRepository")
                .addPart("commitName", "1.1")
                .build();
        //TODO : The values are to be put in config file.
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .header("Content-Type", multipartBody.getContentType())
                .header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzUxMiJ9.eyJpYXQiOjE3Mjg1NTc0MjQsImV4cCI6MTcyODU2MTAyNCwicm9sZXMiOlsiUk9MRV9VU0VSIiwiUk9MRV9DT01QQU5ZX0FETUlOIiwiUk9MRV9SRVBPU0lUT1JZX0FETUlOIl0sImVtYWlsIjoic2lsa3lzYXVteWFAZ21haWwuY29tIn0.JeNMuqXfJw959wlsnq0eZNRsY_IO5NL4oSDe73vxSsin5GFbdw67CaBOBlvIMbyfvCWLxQNgWtI88us4hYQoqFiFkLo_GfN6xtdCJzaOCBeAEZWGqY_BG2iK7K3twssDyQLaZbzivbf8lbttZ01LRf9hj6UfrG7nPH3g_7nx0ajwJlQJdNEwB8TcmKywKhzsfwOG2IlHckX3QO3l8yQx8EC_RQ0cbKxIgabibQgl0MfEPUi30k3Jd_8NIDXq-1UML5GUjeteE9TgWZlZBWqHdPnrU9fPjWt9-bIkQWrRByNqOoXpom6WE1GTtYEACKc0xJQrto-omxaZJrtGiuPXUNDkKsClk6ZXoEa-OilviQz-G17Nz3_ijkXWmcWh8ZCPbkDVtMd1-GNcmqHg15tF7PTkIAcgfvnJ64o4SIzQW-2DWQx53n9XqXA-YzXSq40N-lmT2GIkpu_JT8MjZJO4bP7SEKdqJwYmKrrlCwRksBKqEVz9JsEYo4N5C-6grkEaG4LsucooD7qBjNLXOhnI2UsAoOHHqwGAbtf7mBqDlZ6glAgEvDux7N4eYDWMs6xWxEuw-O9jF-7sSDYG8gwnicm-7EZ6lSpWrSwQACjbcb4EFIWEIE4E50khd9PnPe4JAuClvsWJ0ouVUVJahzw-8yfubE45Wrbd85GOn6bkj7U"
                )
                .uri(new URI("https://debricked.com/api/1.0/open/uploads/dependencies/files"))

                .POST(HttpRequest.BodyPublishers.ofByteArray(multipartBody.getBody()))
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println("POST Response Code :: " + response.body());
        return response;


    }






}
