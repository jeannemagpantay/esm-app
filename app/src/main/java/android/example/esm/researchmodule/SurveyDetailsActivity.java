package android.example.esm.researchmodule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.example.esm.R;
import android.example.esm.researchmodule.models.SurveyForm;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class SurveyDetailsActivity extends AppCompatActivity {

    private String mTitle, mDesc;
    TextView title, desc;
    ImageView backButton;
    FrameLayout frameLayout;
    CardView letsGoButton;
    Intent intentNew;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_details);

        title = (TextView) findViewById(R.id.survey_details_title);;
        desc = (TextView) findViewById(R.id.survey_details_desc);
        backButton = (ImageView) findViewById(R.id.back_button);
        frameLayout = (FrameLayout) findViewById(R.id.frame_survey_details);
        letsGoButton = (CardView) findViewById(R.id.letsgo_button);

        Intent intent = getIntent();
        SurveyForm surveyForm = (SurveyForm) intent.getParcelableExtra("survey");
        mTitle = surveyForm.getSurveyTitle();
        mDesc = surveyForm.getSurveyDesc();


        if (mTitle != null || !mTitle.equals("")){
            title.setText(mTitle);
        }
        if (mDesc != null  || !mDesc.equals("")){
            desc.setText(mDesc);
        }

        intentNew = new Intent(this, SurveyFormActivity.class);
        intentNew.putExtra("survey", surveyForm);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        letsGoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentNew);
            }
        });

    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        supportFinishAfterTransition();
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

