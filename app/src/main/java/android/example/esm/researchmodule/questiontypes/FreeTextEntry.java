package android.example.esm.researchmodule.questiontypes;

import android.app.Activity;
import android.content.Context;
import android.example.esm.researchmodule.SurveyFormActivity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.example.esm.R;
import android.widget.EditText;


public class FreeTextEntry extends Fragment {


    boolean isNumeric;
    public EditText chatbox;
    String answer;

    public FreeTextEntry (boolean isNumericEntry){
        isNumeric = isNumericEntry;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview =  inflater.inflate(R.layout.fragment_free_text_entry, container, false);

        chatbox = (EditText) rootview.findViewById(R.id.edittext_chatbox);
        chatbox.getText().clear();

        if (isNumeric){
            chatbox.setInputType(InputType.TYPE_CLASS_NUMBER);
        }

        return rootview;
    }

    public void getUserInput(){
        answer = chatbox.getText().toString();
        ((SurveyFormActivity) getActivity()).setUserInput("" + answer);

    }



}
