package com.example.backend_hometask.Payload;

public class UploadFileResponse {

    Integer ciUploadId;
    Integer uploadProgramsFileId;
    Integer totalScans;
    Integer remainingScans;
    Float percentage;
    Integer estimatedDaysLeft;

    public Integer getCiUploadId() {
        return ciUploadId;
    }

    public void setCiUploadId(Integer ciUploadId) {
        this.ciUploadId = ciUploadId;
    }

    public Integer getUploadProgramsFileId() {
        return uploadProgramsFileId;
    }

    public void setUploadProgramsFileId(Integer uploadProgramsFileId) {
        this.uploadProgramsFileId = uploadProgramsFileId;
    }

    public Integer getTotalScans() {
        return totalScans;
    }

    public void setTotalScans(Integer totalScans) {
        this.totalScans = totalScans;
    }

    public Integer getRemainingScans() {
        return remainingScans;
    }

    public void setRemainingScans(Integer remainingScans) {
        this.remainingScans = remainingScans;
    }

    public Float getPercentage() {
        return percentage;
    }

    public void setPercentage(Float percentage) {
        this.percentage = percentage;
    }

    public Integer getEstimatedDaysLeft() {
        return estimatedDaysLeft;
    }

    public void setEstimatedDaysLeft(Integer estimatedDaysLeft) {
        this.estimatedDaysLeft = estimatedDaysLeft;
    }
}
