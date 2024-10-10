package com.example.backend_hometask.Service;

import com.example.backend_hometask.Payload.StatusCheckResponse;
import com.example.backend_hometask.Processor.FileUploadRulesProcessor;
import com.example.backend_hometask.Processor.RulesProcessor;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class UploadStatusService implements StatusService{

    public String checkStatus(Integer ciUploadId) throws URISyntaxException, IOException, InterruptedException, JSONException {
        String ruleResult = null;
        RulesProcessor rulesProcessor = new FileUploadRulesProcessor();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzUxMiJ9.eyJpYXQiOjE3Mjg1NTc0MjQsImV4cCI6MTcyODU2MTAyNCwicm9sZXMiOlsiUk9MRV9VU0VSIiwiUk9MRV9DT01QQU5ZX0FETUlOIiwiUk9MRV9SRVBPU0lUT1JZX0FETUlOIl0sImVtYWlsIjoic2lsa3lzYXVteWFAZ21haWwuY29tIn0.JeNMuqXfJw959wlsnq0eZNRsY_IO5NL4oSDe73vxSsin5GFbdw67CaBOBlvIMbyfvCWLxQNgWtI88us4hYQoqFiFkLo_GfN6xtdCJzaOCBeAEZWGqY_BG2iK7K3twssDyQLaZbzivbf8lbttZ01LRf9hj6UfrG7nPH3g_7nx0ajwJlQJdNEwB8TcmKywKhzsfwOG2IlHckX3QO3l8yQx8EC_RQ0cbKxIgabibQgl0MfEPUi30k3Jd_8NIDXq-1UML5GUjeteE9TgWZlZBWqHdPnrU9fPjWt9-bIkQWrRByNqOoXpom6WE1GTtYEACKc0xJQrto-omxaZJrtGiuPXUNDkKsClk6ZXoEa-OilviQz-G17Nz3_ijkXWmcWh8ZCPbkDVtMd1-GNcmqHg15tF7PTkIAcgfvnJ64o4SIzQW-2DWQx53n9XqXA-YzXSq40N-lmT2GIkpu_JT8MjZJO4bP7SEKdqJwYmKrrlCwRksBKqEVz9JsEYo4N5C-6grkEaG4LsucooD7qBjNLXOhnI2UsAoOHHqwGAbtf7mBqDlZ6glAgEvDux7N4eYDWMs6xWxEuw-O9jF-7sSDYG8gwnicm-7EZ6lSpWrSwQACjbcb4EFIWEIE4E50khd9PnPe4JAuClvsWJ0ouVUVJahzw-8yfubE45Wrbd85GOn6bkj7U")
                .uri(new URI("https://debricked.com/api/1.0/open/ci/upload/status?ciUploadId=" + ciUploadId.toString()))
                .GET()
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        StatusCheckResponse statusResponse = getStatusCheckResponse(httpResponse);
        ruleResult = rulesProcessor.processRules(ciUploadId,statusResponse);
        return ruleResult;
    }

    private static StatusCheckResponse getStatusCheckResponse(HttpResponse<String> response) throws JSONException {
        JSONObject responseJason = new JSONObject(response.body());
        StatusCheckResponse statusResponse = new StatusCheckResponse();
        statusResponse.setProgress((Integer) responseJason.get("progress"));
        statusResponse.setVulnerabilitiesFound((Integer) responseJason.get("vulnerabilitiesFound"));
        statusResponse.setUnaffectedVulnerabilitiesFound((Integer) responseJason.get("unaffectedVulnerabilitiesFound"));
        statusResponse.setAutomationsAction((String) responseJason.get("automationsAction"));
        statusResponse.setPolicyEngineAction((String) responseJason.get("policyEngineAction"));
        statusResponse.setResponseCode((Integer) response.statusCode());
        return statusResponse;
    }

}
