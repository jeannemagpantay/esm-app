package android.example.esm.researchmodule.questiontypes;

import android.example.esm.researchmodule.SurveyFormActivity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.example.esm.R;
import android.widget.RatingBar;

import java.util.List;


public class RatingScale extends Fragment {

    public List<String> minMax;
    RatingBar ratingBar;

    public RatingScale(List<String> scale){
        minMax = scale;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_rating_scale, container, false);

        ratingBar = (RatingBar) rootView.findViewById(R.id.rating_bar);
        ratingBar.setNumStars(Integer.parseInt(minMax.get(1)));
        ratingBar.setMax(Integer.parseInt(minMax.get(1)));
        ratingBar.setStepSize(1);

        return rootView;

    }

    public void getUserInput(){
        ((SurveyFormActivity) getActivity()).setUserInput("" + (int)ratingBar.getRating());

    }
}
