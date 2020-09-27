package android.example.esm.researchmodule.questiontypes;

import android.example.esm.researchmodule.SurveyFormActivity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.example.esm.R;

import com.google.android.material.slider.Slider;


public class SliderScale extends Fragment {

    Slider slider;
    int maxNum;

    public SliderScale(String max){
        maxNum = Integer.parseInt(max);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_slider_scale, container, false);
        slider = (Slider) rootView.findViewById(R.id.slider_scale);

        slider.setValueTo(maxNum);
        return rootView;
    }

    public void getUserInput(){
        ((SurveyFormActivity) getActivity()).setUserInput("" + (int)slider.getValue());

    }



}
