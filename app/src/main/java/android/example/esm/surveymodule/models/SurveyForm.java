package android.example.esm.surveymodule.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SurveyForm {

    @SerializedName("id")
    @Expose
    private int formId;

    @SerializedName("created_by_id")
    @Expose
    private int creatorId;

    @SerializedName("title")
    @Expose
    private String surveyTitle;

    @SerializedName("desc")
    @Expose
    private String surveyDesc;

    @SerializedName("frequency")
    @Expose
    private int frequency;

    @SerializedName("is_test")
    @Expose
    private int isTest;

    @SerializedName("start")
    @Expose
    private String start;

    @SerializedName("end")
    @Expose
    private String end;

    @SerializedName("client_id")
    @Expose
    private int clientId;

    @SerializedName("is_active")
    @Expose
    private int isActive;

    @SerializedName("createdAt")
    @Expose
    private String dateCreated;

    @SerializedName("updatedAt")
    @Expose
    private String dateUpdated;

    @SerializedName("survey_sections")
    @Expose
    private List<SurveySection> surveySection;

    public int getFormId() {
        return formId;
    }

    public void setFormId(int formId) {
        this.formId = formId;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public String getSurveyTitle() {
        return surveyTitle;
    }

    public void setSurveyTitle(String surveyTitle) {
        this.surveyTitle = surveyTitle;
    }

    public String getSurveyDesc() {
        return surveyDesc;
    }

    public void setSurveyDesc(String surveyDesc) {
        this.surveyDesc = surveyDesc;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getIsTest() {
        return isTest;
    }

    public void setIsTest(int isTest) {
        this.isTest = isTest;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public List<SurveySection> getSurveySection() {
        return surveySection;
    }

    public void setSurveySection(List<SurveySection> surveySection) {
        this.surveySection = surveySection;
    }
}
