package android.example.esm.researchmodule.questiontypes;

import android.example.esm.researchmodule.SurveyFormActivity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.example.esm.R;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

import java.util.List;


public class NumberWheel extends Fragment {

    NumberPicker numberPicker;
    int maxNum;

    public NumberWheel (String max){
        maxNum = Integer.parseInt(max);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview =  inflater.inflate(R.layout.fragment_number_wheel, container, false);

        numberPicker = rootview.findViewById(R.id.number_wheel);
        numberPicker.setMaxValue(maxNum);

        return rootview;
    }

    public void getUserInput(){
        ((SurveyFormActivity) getActivity()).setUserInput("" + numberPicker.getValue());

    }



}
