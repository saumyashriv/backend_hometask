package com.example.backend_hometask.Processor;

import com.example.backend_hometask.Payload.StatusCheckResponse;

public interface RulesProcessor {
    String processRules(Integer ciUploadId, StatusCheckResponse statusResponse);
}
