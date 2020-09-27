package android.example.esm.researchmodule.questiontypes;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.example.esm.researchmodule.SurveyFormActivity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.InputType;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.example.esm.R;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.List;



public class TimeAmount extends Fragment {

    ImageView timer;
    TextView displayTime;
    CharSequence timeCharSequence;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview =  inflater.inflate(R.layout.fragment_time_amount, container, false);

        timer = rootview.findViewById(R.id.time_only_icon);
        displayTime = rootview.findViewById(R.id.text_set_time);

        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleTimeButton();
            }
        });

        return rootview;
    }

    public void getUserInput(){
        ((SurveyFormActivity) getActivity()).setUserInput("" + timeCharSequence);

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
                displayTime.setText(timeString);

                Calendar newCalendar = Calendar.getInstance();
                newCalendar.set(Calendar.HOUR, hour);
                newCalendar.set(Calendar.MINUTE, minute);

                timeCharSequence = DateFormat.format("hh:mm a", newCalendar);
                displayTime.setText(timeCharSequence);

            }
        }, HOUR, MINUTE, is24HourFormat);

        timePickerDialog.show();
        timePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        timePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorPrimaryDark));
    }

}
