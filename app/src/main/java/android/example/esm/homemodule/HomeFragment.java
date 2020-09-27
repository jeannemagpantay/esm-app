package android.example.esm.homemodule;

import android.content.Context;
import android.content.SharedPreferences;
import android.example.esm.MainActivity;
import android.example.esm.researchmodule.SurveyListFragment;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.example.esm.R;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Objects;


public class HomeFragment extends Fragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeRefreshLayout;
    FrameLayout surveyHorizontal;
    public SharedPreferences sharedPreferences;
    public LinearLayout great, good, meh, sad, angry;
    int moodNumber = 0;
    boolean isMoodSelected = false;
    int clickedID = 0;
    TextView seeAllText;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(this);

        //seeAllText = (TextView) rootView.findViewById(R.id.text_chart);
        surveyHorizontal = (FrameLayout) rootView.findViewById(R.id.survey_horizontal);

        great = (LinearLayout) rootView.findViewById(R.id.great_mood);
        great.setOnClickListener(this);
        good = (LinearLayout) rootView.findViewById(R.id.good_mood);
        good.setOnClickListener(this);
        meh = (LinearLayout) rootView.findViewById(R.id.meh_mood);
        meh.setOnClickListener(this);
        sad = (LinearLayout) rootView.findViewById(R.id.sad_mood);
        sad.setOnClickListener(this);
        angry = (LinearLayout) rootView.findViewById(R.id.angry_mood);
        angry.setOnClickListener(this);
        seeAllText = (TextView) rootView.findViewById(R.id.text_see_all);
        seeAllText.setOnClickListener(this);

        setSurveyFragment();

        return rootView;
    }

    @Override
    public void onRefresh() {
        setSurveyFragment();
        swipeRefreshLayout.setRefreshing(false);
    }

    public void setMood(int clicked){
        sharedPreferences = Objects.requireNonNull(this.getActivity()).getSharedPreferences("mood", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("mood", clicked);
        editor.apply();
    }

    public void setSurveyFragment(){
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        SurveyListFragment surveyListFragment = new SurveyListFragment();
        surveyListFragment.isHorizontal = true;
        ft.replace(R.id.survey_horizontal, surveyListFragment);
        ft.commit();
    }

    @Override
    public void onClick(View v) {
        LinearLayout.LayoutParams selectParam = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
        LinearLayout.LayoutParams smallerParam = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
        LinearLayout.LayoutParams defParam = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT,0.7f);

        if (isMoodSelected == false || v.getId() != clickedID){
            switch (v.getId()) {
                case R.id.great_mood:
                    moodNumber = 1;
                    great.setLayoutParams(selectParam);
                    good.setLayoutParams(defParam);
                    meh.setLayoutParams(defParam);
                    sad.setLayoutParams(defParam);
                    angry.setLayoutParams(defParam);
                    break;
                case R.id.good_mood:
                    moodNumber = 2;
                    good.setLayoutParams(selectParam);
                    great.setLayoutParams(defParam);
                    meh.setLayoutParams(defParam);
                    sad.setLayoutParams(defParam);
                    angry.setLayoutParams(defParam);
                    break;
                case R.id.meh_mood:
                    moodNumber = 3;
                    meh.setLayoutParams(selectParam);
                    great.setLayoutParams(defParam);
                    good.setLayoutParams(defParam);
                    sad.setLayoutParams(defParam);
                    angry.setLayoutParams(defParam);
                    break;
                case R.id.sad_mood:
                    moodNumber = 4;
                    sad.setLayoutParams(selectParam);
                    great.setLayoutParams(defParam);
                    good.setLayoutParams(defParam);
                    meh.setLayoutParams(defParam);
                    angry.setLayoutParams(defParam);
                    break;
                case R.id.angry_mood:
                    moodNumber = 5;
                    angry.setLayoutParams(selectParam);
                    great.setLayoutParams(defParam);
                    good.setLayoutParams(defParam);
                    meh.setLayoutParams(defParam);
                    sad.setLayoutParams(defParam);
                    break;
                case R.id.text_see_all:
                    ((MainActivity)getActivity()).jumpToSurveys();

            }
            isMoodSelected = true;
        } else {
            moodNumber = 0;
            isMoodSelected = false;
            great.setLayoutParams(smallerParam);
            good.setLayoutParams(smallerParam);
            meh.setLayoutParams(smallerParam);
            sad.setLayoutParams(smallerParam);
            angry.setLayoutParams(smallerParam);
        }
        clickedID = v.getId();
        setMood(moodNumber);
    }

}
