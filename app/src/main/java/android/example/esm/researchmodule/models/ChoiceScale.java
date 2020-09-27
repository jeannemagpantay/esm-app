package android.example.esm.researchmodule.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChoiceScale {
    @SerializedName("min")
    @Expose
    private int min;

    @SerializedName("max")
    @Expose
    private int max;

    @SerializedName("marks")
    @Expose
    private int marks;

}
