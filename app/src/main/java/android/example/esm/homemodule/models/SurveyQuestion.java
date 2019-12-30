package android.example.esm.homemodule.models;

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
    private int questionDesc;

    @SerializedName("choices")
    @Expose
    private String choices;
}
