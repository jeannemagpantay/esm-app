package android.example.esm.surveymodule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.example.esm.R;
import android.os.Bundle;
import android.widget.TextView;

public class SurveyDetailsActivity extends AppCompatActivity {

    private String mTitle, mDesc;
    TextView title, desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_details);

        title = (TextView) findViewById(R.id.survey_details_title);;
        desc = (TextView) findViewById(R.id.survey_details_desc);

        Intent intent = getIntent();
        mTitle = intent.getStringExtra("title");
        mDesc = intent.getStringExtra("desc");


        if (mTitle != null || !mTitle.equals("")){
            title.setText(mTitle);
        }
        if (mDesc != null  || !mDesc.equals("")){
            desc.setText(mDesc);
        }
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

