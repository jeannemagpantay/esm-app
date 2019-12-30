package android.example.esm.homemodule.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SurveySection {

    @SerializedName("id")
    @Expose
    private int sectionId;

    @SerializedName("survey_id")
    @Expose
    private int surveyId;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("desc")
    @Expose
    private String sectionDesc;

    @SerializedName("is_random")
    @Expose
    private int isRandom;

    @SerializedName("survey_questions")
    @Expose
    private List<SurveyQuestion> surveyQuestion;

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public int getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(int surveyId) {
        this.surveyId = surveyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSectionDesc() {
        return sectionDesc;
    }

    public void setSectionDesc(String sectionDesc) {
        this.sectionDesc = sectionDesc;
    }

    public int getIsRandom() {
        return isRandom;
    }

    public void setIsRandom(int isRandom) {
        this.isRandom = isRandom;
    }

    public List<SurveyQuestion> getSurveyQuestion() {
        return surveyQuestion;
    }

    public void setSurveyQuestion(List<SurveyQuestion> surveyQuestion) {
        this.surveyQuestion = surveyQuestion;
    }



}
