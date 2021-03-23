package com.example.mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.view.View;
import android.graphics.drawable.ColorDrawable;
import java.util.Calendar;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TextView;
import android.app.TimePickerDialog;
import android.widget.TimePicker;

public class popupWindow extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    Spinner importance;
    private EditText mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window);

        DisplayMetrics sa = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(sa);

        int width = sa.widthPixels;
        int height = sa.heightPixels;
        getWindow().setLayout((int) (width * .9), (int) (height * .9));
//dropdown menu
        importance = findViewById(R.id.importance);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.importance,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        importance.setAdapter(adapter);
        Calendar cal2 = Calendar.getInstance();
        //time
        final int hour = cal2.get(Calendar.HOUR_OF_DAY);
        final int minute = cal2.get(Calendar.MINUTE);
        final EditText time = (EditText) findViewById(R.id.time);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimePickerDialog timePick = new TimePickerDialog(popupWindow.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        time.setText(i + ":" + i1);
                    }
                }, hour , minute , true);
                timePick.setTitle("Select date time");
                timePick.show();
            }
        });//time end

//Date
        mDisplayDate = (EditText) findViewById(R.id.date);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        popupWindow.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
               mDisplayDate.setText(date);
            }
        }; //end date
    }
}