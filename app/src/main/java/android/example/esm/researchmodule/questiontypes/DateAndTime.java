package android.example.esm.researchmodule.questiontypes;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.example.esm.researchmodule.SurveyFormActivity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.example.esm.R;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class DateAndTime extends Fragment {

    TextView dateText, timeText;
    ImageView dateIcon, timeIcon;
    CharSequence timeCharSequence, dateCharSequence;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview =  inflater.inflate(R.layout.fragment_date_and_time, container, false);
        dateText = rootview.findViewById(R.id.text_date);
        timeText = rootview.findViewById(R.id.text_time);
        dateIcon = rootview.findViewById(R.id.image_date);
        timeIcon = rootview.findViewById(R.id.image_time);


        dateIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleDateButton();
            }
        });

        timeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleTimeButton();
            }
        });

        return rootview;
    }

    public void handleDateButton(){

        Calendar calendar = Calendar.getInstance();
        int YEAR = calendar.get(Calendar.YEAR);
        int MONTH = calendar.get(Calendar.MONTH);
        int DATE = calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), R.style.CustomWidgetTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                String dateString = year + " " + month + " " + date;
                dateText.setText(dateString);

                Calendar newCalendar = Calendar.getInstance();
                newCalendar.set(Calendar.YEAR, year);
                newCalendar.set(Calendar.MONTH, month);
                newCalendar.set(Calendar.DATE, date);

                dateCharSequence = DateFormat.format("EEEE, dd MMM yyyy", newCalendar);
                dateText.setText(dateCharSequence);
            }
        }, YEAR, MONTH, DATE);

        datePickerDialog.show();
        datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorPrimaryDark));


    }

    public void handleTimeButton(){
        Calendar calendar = Calendar.getInstance();
        int HOUR = calendar.get(Calendar.HOUR);
        int MINUTE = calendar.get(Calendar.MINUTE);
        boolean is24HourFormat = DateFormat.is24HourFormat(getActivity());

        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), R.style.CustomWidgetTheme, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                String timeString = hour + ":" + minute;
                timeText.setText(timeString);

                Calendar newCalendar = Calendar.getInstance();
                newCalendar.set(Calendar.HOUR, hour);
                newCalendar.set(Calendar.MINUTE, minute);

                timeCharSequence = DateFormat.format("hh:mm a", newCalendar);
                timeText.setText(timeCharSequence);

            }
        }, HOUR, MINUTE, is24HourFormat);

        timePickerDialog.show();
        timePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        timePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorPrimaryDark));

    }


    public void getUserInput(){
       ((SurveyFormActivity) getActivity()).setUserInput(dateCharSequence + " " + timeCharSequence);
    }


}
