package android.example.esm.surveymodule.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SurveyQuestion {

    @SerializedName("id")
    @Expose
    private int questionId;

    @SerializedName("section_id")
    @Expose
    private int sectionId;

    @SerializedName("type")
    @Expose
    private int questionType;

    @SerializedName("question")
    @Expose
    private String question;

    @SerializedName("desc")
    @Expose
    private String questionDesc;

    @SerializedName("choices")
    @Expose
    private List<String> choices;

    @SerializedName("choices")
    @Expose
    private ChoiceScale choicesScale;

    @SerializedName("order_id")
    @Expose
    private int orderId;

    @SerializedName("is_required")
    @Expose
    private int isRequired;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public int getQuestionType() {
        return questionType;
    }

    public void setQuestionType(int questionType) {
        this.questionType = questionType;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestionDesc() {
        return questionDesc;
    }

    public void setQuestionDesc(String questionDesc) {
        this.questionDesc = questionDesc;
    }


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(int isRequired) {
        this.isRequired = isRequired;
    }

    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }

    public ChoiceScale getChoicesScale() {
        return choicesScale;
    }

    public void setChoicesScale(ChoiceScale choicesScale) {
        this.choicesScale = choicesScale;
    }
}
