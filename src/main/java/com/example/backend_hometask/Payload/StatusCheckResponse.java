package com.example.backend_hometask.Payload;

public class StatusCheckResponse {

        Integer progress;
        Integer vulnerabilitiesFound;
        Integer unaffectedVulnerabilitiesFound;
        String automationsAction;
        String policyEngineAction;
        Integer responseCode;

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Integer getVulnerabilitiesFound() {
        return vulnerabilitiesFound;
    }

    public void setVulnerabilitiesFound(Integer vulnerabilitiesFound) {
        this.vulnerabilitiesFound = vulnerabilitiesFound;
    }

    public Integer getUnaffectedVulnerabilitiesFound() {
        return unaffectedVulnerabilitiesFound;
    }

    public void setUnaffectedVulnerabilitiesFound(Integer unaffectedVulnerabilitiesFound) {
        this.unaffectedVulnerabilitiesFound = unaffectedVulnerabilitiesFound;
    }

    public String getAutomationsAction() {
        return automationsAction;
    }

    public void setAutomationsAction(String automationsAction) {
        this.automationsAction = automationsAction;
    }

    public String getPolicyEngineAction() {
        return policyEngineAction;
    }

    public void setPolicyEngineAction(String policyEngineAction) {
        this.policyEngineAction = policyEngineAction;
    }
}
