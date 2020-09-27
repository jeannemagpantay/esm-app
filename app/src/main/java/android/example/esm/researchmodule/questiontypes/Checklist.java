package android.example.esm.researchmodule.questiontypes;

import android.example.esm.researchmodule.SurveyFormActivity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.InputType;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.example.esm.R;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.List;


public class Checklist extends Fragment {

    List<String> checkList;
    CheckBox[] checkBoxes;

    public Checklist (List<String> choices){
        checkList = choices;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview =  inflater.inflate(R.layout.fragment_checklist, container, false);

        LinearLayout layout = (LinearLayout)rootview.findViewById(R.id.layout_checkbox);

        checkBoxes = new CheckBox[checkList.size()];
        for(int i = 0; i < checkList.size(); i++) {
            checkBoxes[i] = new CheckBox((new ContextThemeWrapper(getContext(), R.style.CustomWidgetTheme)));
            checkBoxes[i].setId(i);
            checkBoxes[i].setText(checkList.get(i));
            layout.addView(checkBoxes[i]);
        }

        return rootview;
    }

    public void getUserInput(){
        CharSequence answer = "";
        for(int i = 0; i < checkBoxes.length; i++) {
            if (checkBoxes[i].isChecked()){
                 if (answer!= "") {
                     answer = answer + ", " + checkBoxes[i].getText();
                } else {
                     answer = checkBoxes[i].getText();
                 }
            }
        }
        ((SurveyFormActivity) getActivity()).setUserInput("" + answer);

    }



}
