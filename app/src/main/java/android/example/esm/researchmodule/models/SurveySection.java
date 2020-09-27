package android.example.esm.researchmodule.models;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SurveySection implements Parcelable {

    public SurveySection(){}

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

    @SerializedName("order_id")
    @Expose
    private int orderId;

    @SerializedName("survey_questions")
    @Expose
    private List<SurveyQuestion> surveyQuestion;


    protected SurveySection(Parcel in) {
        sectionId = in.readInt();
        surveyId = in.readInt();
        title = in.readString();
        sectionDesc = in.readString();
        isRandom = in.readInt();
        orderId = in.readInt();
        surveyQuestion = in.createTypedArrayList(SurveyQuestion.CREATOR);
    }

    public static final Creator<SurveySection> CREATOR = new Creator<SurveySection>() {
        @Override
        public SurveySection createFromParcel(Parcel in) {
            return new SurveySection(in);
        }

        @Override
        public SurveySection[] newArray(int size) {
            return new SurveySection[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(sectionId);
        parcel.writeInt(surveyId);
        parcel.writeString(title);
        parcel.writeString(sectionDesc);
        parcel.writeInt(isRandom);
        parcel.writeInt(orderId);
        parcel.writeTypedList(surveyQuestion);
    }

    public int getSectionId() {
        return sectionId;
    }

    public int getSurveyId() {
        return surveyId;
    }

    public String getTitle() {
        return title;
    }

    public String getSectionDesc() {
        return sectionDesc;
    }

    public int getIsRandom() {
        return isRandom;
    }

    public int getOrderId() {
        return orderId;
    }

    public List<SurveyQuestion> getSurveyQuestion() {
        return surveyQuestion;
    }
}
