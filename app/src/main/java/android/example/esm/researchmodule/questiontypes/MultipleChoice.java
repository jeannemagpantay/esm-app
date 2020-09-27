package android.example.esm.researchmodule.questiontypes;

import android.example.esm.researchmodule.SurveyFormActivity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.example.esm.R;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;


public class MultipleChoice extends Fragment {

    RadioGroup radioGroup;
    public List<String> choices;
    RadioButton[] radioButtons;
    String answer;

    public MultipleChoice(List<String> choiceList){
        choices = choiceList;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_multiple_choice, container, false);

        radioGroup = (RadioGroup) rootView.findViewById(R.id.radio_group);

        radioButtons = new RadioButton[choices.size()];

        for (int i = 0; i < choices.size(); i++) {
            //RadioButton radioButton = new RadioButton(this.getContext());
            radioButtons[i] = new RadioButton((new ContextThemeWrapper(getContext(), R.style.CustomWidgetTheme)));
            radioButtons[i].setText(choices.get(i));
            radioGroup.addView(radioButtons[i]);
        }


        return rootView;

    }

    public void getUserInput(){

        for (int i = 0; i < choices.size(); i++){
            if (radioButtons[i].isChecked()){
                answer = radioButtons[i].getText().toString();
                break;
            }
        }

        ((SurveyFormActivity) getActivity()).setUserInput(answer);
    }


}