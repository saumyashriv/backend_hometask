package com.example.backend_hometask.Processor;

import com.example.backend_hometask.Payload.StatusCheckResponse;
import com.example.backend_hometask.Payload.StatusUpdateResponse;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;

public class FileUploadRulesProcessor implements RulesProcessor{
    @Autowired
    private KieContainer kieContainer;

    public String processRules(Integer ciUploadId, StatusCheckResponse statusResponse) {
        StatusUpdateResponse statusUpdateResponse = new StatusUpdateResponse();
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.setGlobal("statusUpdateResponse", statusUpdateResponse);
        kieSession.insert(statusResponse);
        kieSession.fireAllRules();
        kieSession.dispose();
        return statusUpdateResponse.getResponse();
    }
}
