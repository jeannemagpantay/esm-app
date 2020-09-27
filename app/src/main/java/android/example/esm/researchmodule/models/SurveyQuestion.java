package android.example.esm.researchmodule.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SurveyQuestion implements Parcelable {

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


/*    @SerializedName("choices")
    @Expose
    private ChoiceScale choicesScale;*/

    @SerializedName("order_id")
    @Expose
    private int orderId;

    @SerializedName("is_required")
    @Expose
    private boolean isRequired;

    @SerializedName("desc_media")
    @Expose
    private String descMedia;

    protected SurveyQuestion(Parcel in) {
        questionId = in.readInt();
        sectionId = in.readInt();
        questionType = in.readInt();
        question = in.readString();
        questionDesc = in.readString();
        choices = in.createStringArrayList();
        orderId = in.readInt();
        isRequired = in.readByte() != 0;
        descMedia = in.readString();
    }

    public static final Creator<SurveyQuestion> CREATOR = new Creator<SurveyQuestion>() {
        @Override
        public SurveyQuestion createFromParcel(Parcel in) {
            return new SurveyQuestion(in);
        }

        @Override
        public SurveyQuestion[] newArray(int size) {
            return new SurveyQuestion[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(questionId);
        parcel.writeInt(sectionId);
        parcel.writeInt(questionType);
        parcel.writeString(question);
        parcel.writeString(questionDesc);
        parcel.writeStringList(choices);
        parcel.writeInt(orderId);
        parcel.writeByte((byte) (isRequired ? 1 : 0));
        parcel.writeString(descMedia);
    }



/*    public ChoiceScale getChoicesScale() {
        return choicesScale;
    }

    public void setChoicesScale(ChoiceScale choicesScale) {
        this.choicesScale = choicesScale;
    }*/

    public int getQuestionId() {
        return questionId;
    }

    public int getSectionId() {
        return sectionId;
    }

    public int getQuestionType() {
        return questionType;
    }

    public String getQuestion() {
        return question;
    }

    public String getQuestionDesc() {
        return questionDesc;
    }

    public List<String> getChoices() {
        return choices;
    }

    public int getOrderId() {
        return orderId;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public String getDescMedia() {
        return descMedia;
    }

}
