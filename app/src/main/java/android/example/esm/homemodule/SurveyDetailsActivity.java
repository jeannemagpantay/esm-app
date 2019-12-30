package android.example.esm.homemodule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.example.esm.R;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SurveyDetailsActivity extends AppCompatActivity {

    private String mTitle, mDesc;
    TextView title, desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_details);

        Intent intent = getIntent();
        mTitle = intent.getStringExtra("title");
        mDesc = intent.getStringExtra("desc");

        title = findViewById(R.id.survey_title);;
        desc = findViewById(R.id.survey_desc);
        title.setText(mTitle);
        if (mDesc != null){
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

