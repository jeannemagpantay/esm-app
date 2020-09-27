package android.example.esm.researchmodule;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.example.esm.MainActivity;
import android.example.esm.R;
import android.example.esm.researchmodule.models.GeneralMessage;
import android.example.esm.researchmodule.models.SurveyForm;
import android.example.esm.researchmodule.models.SurveyQuestion;
import android.example.esm.researchmodule.questiontypes.Checklist;
import android.example.esm.researchmodule.questiontypes.DateAndTime;
import android.example.esm.researchmodule.questiontypes.FreeTextEntry;
import android.example.esm.researchmodule.questiontypes.MultipleChoice;
import android.example.esm.researchmodule.questiontypes.NumberWheel;
import android.example.esm.researchmodule.questiontypes.PhotoEntry;
import android.example.esm.researchmodule.questiontypes.RatingScale;
import android.example.esm.researchmodule.questiontypes.SliderScale;
import android.example.esm.researchmodule.questiontypes.TimeAmount;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class SurveyFormActivity extends AppCompatActivity{

    ImageView backButton;
    TextView titleBar, progressCount;
    MessageListAdapter messageListAdapter;
    RecyclerView recyclerView;
    public List<GeneralMessage> messageList = new ArrayList<>();
    public List<SurveyQuestion> questions = new ArrayList<>();
    public List<String> userAnswers = new ArrayList<>();
    SurveyQuestion question;
    int countSections, countQuestions;
    ImageView arrowBack, arrowNext;
    public String userInput;
    Checklist checklist;
    DateAndTime dateAndTime;
    FreeTextEntry freeTextEntry, numericEntry, textOnly, webPage;
    MultipleChoice multipleChoice;
    NumberWheel numberWheel;
    PhotoEntry photoEntry;
    RatingScale ratingScale;
    SliderScale sliderScale;
    TimeAmount timeAmount;
    int type = 1, currentCount = 1, progress = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_form);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_messages);
        titleBar = (TextView) findViewById(R.id.text_titlebar);
        backButton = findViewById(R.id.back_button);
        arrowBack = findViewById(R.id.arrow_back);
        arrowNext = findViewById(R.id.arrow_next);
        progressCount = findViewById(R.id.text_question_count);

        Intent intent = getIntent();
        SurveyForm surveyForm = intent.getParcelableExtra("survey");
        titleBar.setText(surveyForm.getSurveyTitle());

        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        arrowNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUserInput();
                makeMessage(userInput, 1);
                nextQuestion();
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        countSections = surveyForm.getSurveySection().size();
        for (int i = 0; i < countSections; i++){
            int temp = surveyForm.getSurveySection().get(i).getSurveyQuestion().size();
            for (int j = 0; j < temp; j++){
                question = surveyForm.getSurveySection().get(i).getSurveyQuestion().get(j);
                questions.add(question);
            }
        }

        countQuestions = questions.size();

        messageListAdapter = new MessageListAdapter(messageList, this);
        LinearLayoutManager llm = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(llm);
        //TODO llm.setStackFromEnd(true);
        recyclerView.setAdapter(messageListAdapter);
        firstQuestion();


    }

    public void firstQuestion(){
        question = questions.get(0);
        makeMessage(question.getQuestion(), 2);
        createWidget(question, question.getQuestionType());
        questions.remove(0);
        messageListAdapter.notifyDataSetChanged();
    }

    public void nextQuestion(){
        if (questions.size() != 0 ){
            currentCount++;
            progressCount.setText(currentCount + " of " + countQuestions);
            question = questions.get(0);
            makeMessage(question.getQuestion(), 2);
            createWidget(question, question.getQuestionType());
            questions.remove(0);
        } else {
            closeKeyboard();
            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("Submit survey?");
            alertDialog.setMessage("Changes cannot be made once submitted.");
            alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(getApplicationContext(), "Submitted!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("TabNumber", 1);
                    startActivity(intent);

                }
            });

            alertDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            alertDialog.show();
        }
        messageListAdapter.notifyDataSetChanged();
    }

    public void createWidget(SurveyQuestion question, int questionType){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        userInput = "";

        closeKeyboard();

        switch (questionType) {
            case 0:
                type = 0;
                sliderScale = new SliderScale(question.getChoices().get(1));
                ft.replace(R.id.widget_container, sliderScale);
                break;
            case 1:
                freeTextEntry = new FreeTextEntry(false);
                ft.replace(R.id.widget_container, freeTextEntry);
                type = 1;
                break;
            case 2:
                multipleChoice = new MultipleChoice(question.getChoices());
                ft.replace(R.id.widget_container, multipleChoice);
                type = 2;
                break;
            case 3:
                ratingScale = new RatingScale(question.getChoices());
                ft.replace(R.id.widget_container, ratingScale);
                type = 3;
                break;
            case 4:
                type = 4;
                numericEntry = new FreeTextEntry(true);
                ft.replace(R.id.widget_container, numericEntry);
                break;
            case 5:
                type = 5;
                checklist = new Checklist(question.getChoices());
                ft.replace(R.id.widget_container, checklist);
                break;
            case 6:
                type = 6;
                numberWheel = new NumberWheel(question.getChoices().get(1));
                ft.replace(R.id.widget_container, numberWheel);
                break;
            case 7:
                type = 7;
                dateAndTime = new DateAndTime();
                ft.replace(R.id.widget_container, dateAndTime);
                break;
            case 8:
                timeAmount = new TimeAmount();
                ft.replace(R.id.widget_container, timeAmount);
                type = 8;
                break;
            case 9:
                textOnly = new FreeTextEntry(false);
                ft.replace(R.id.widget_container, textOnly);
                type = 9;
                break;
            case 10:
                type = 10;
                photoEntry = new PhotoEntry();
                ft.replace(R.id.widget_container, photoEntry);
                break;
            case 11:
                type = 11;
                webPage = new FreeTextEntry(false);
                ft.replace(R.id.widget_container, webPage);
                break;
        }

        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }


    public void makeMessage(String message, int type){
        GeneralMessage newMessage = new GeneralMessage(message,type);
        newMessage.setMessage(message);
        newMessage.setType(type);
        messageList.add(newMessage);
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

    private void closeKeyboard(){
        View view = this.getCurrentFocus();
        if (view!= null){
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    //TODO: recyclerView.smoothScrollToPosition(position);

    public void setUserInput(String str){
        if (str == null || str.trim().isEmpty()){
            userInput = "no answer";
        } else {
            userInput = str;
        }

        userAnswers.add(userInput);

    }

    public void getUserInput(){
        switch (type){
            case 0:
                sliderScale.getUserInput();
                break;
            case 1:
                freeTextEntry.getUserInput();
                break;
            case 2:
                multipleChoice.getUserInput();
                break;
            case 3:
                ratingScale.getUserInput();
                break;
            case 4:
                numericEntry.getUserInput();
                break;
            case 5:
                checklist.getUserInput();
                break;
            case 6:
                numberWheel.getUserInput();
                break;
            case 7:
                dateAndTime.getUserInput();
                break;
            case 8:
                timeAmount.getUserInput();
                break;
            case 9:
                textOnly.getUserInput();
                break;
            case 10:
                photoEntry.getUserInput();
                break;
            case 11:
                webPage.getUserInput();
                break;
        }

    }
}
