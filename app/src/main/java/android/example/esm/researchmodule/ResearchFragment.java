package android.example.esm.researchmodule;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.example.esm.R;


public class ResearchFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_research, container, false);

        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        SurveyListFragment surveyListFragment = new SurveyListFragment();
        ft.replace(R.id.frame_recycler, surveyListFragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
        return rootView;
    }

}
