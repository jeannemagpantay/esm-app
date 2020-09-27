package android.example.esm.researchmodule.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SurveyForm implements Parcelable {

    @SerializedName("id")
    @Expose
    private int formId;

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

    @SerializedName("is_active")
    @Expose
    private int isActive;

    @SerializedName("access_code")
    @Expose
    private String accessCode;

    @SerializedName("anonymity")
    @Expose
    private int anonymity;

    @SerializedName("desc_media")
    @Expose
    private String descMedia;

    @SerializedName("survey_sections")
    @Expose
    private List<SurveySection> surveySection;

    protected SurveyForm(Parcel in) {
        formId = in.readInt();
        surveyTitle = in.readString();
        surveyDesc = in.readString();
        frequency = in.readInt();
        isTest = in.readInt();
        start = in.readString();
        end = in.readString();
        isActive = in.readInt();
        accessCode = in.readString();
        anonymity = in.readInt();
        descMedia = in.readString();
        surveySection = in.createTypedArrayList(SurveySection.CREATOR);
    }

    public static final Creator<SurveyForm> CREATOR = new Creator<SurveyForm>() {
        @Override
        public SurveyForm createFromParcel(Parcel in) {
            return new SurveyForm(in);
        }

        @Override
        public SurveyForm[] newArray(int size) {
            return new SurveyForm[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(formId);
        parcel.writeString(surveyTitle);
        parcel.writeString(surveyDesc);
        parcel.writeInt(frequency);
        parcel.writeInt(isTest);
        parcel.writeString(start);
        parcel.writeString(end);
        parcel.writeInt(isActive);
        parcel.writeString(accessCode);
        parcel.writeInt(anonymity);
        parcel.writeString(descMedia);
        parcel.writeTypedList(surveySection);
    }

    public int getFormId() {
        return formId;
    }

    public String getSurveyTitle() {
        return surveyTitle;
    }

    public String getSurveyDesc() {
        return surveyDesc;
    }

    public int getFrequency() {
        return frequency;
    }

    public int getIsTest() {
        return isTest;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public int getIsActive() {
        return isActive;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public int getAnonymity() {
        return anonymity;
    }

    public List<SurveySection> getSurveySection() {
        return surveySection;
    }


}
