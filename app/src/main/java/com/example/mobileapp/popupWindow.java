package com.example.mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.view.View;
import android.graphics.drawable.ColorDrawable;

import java.util.Calendar;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class popupWindow extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    Spinner importance;
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window);

        DisplayMetrics sa = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(sa);

        int width = sa.widthPixels;
        int height = sa.heightPixels;
        getWindow().setLayout((int) (width * .9), (int) (height * .9));

        importance = findViewById(R.id.importance);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.importance,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        importance.setAdapter(adapter);

        mDisplayDate = (TextView) findViewById(R.id.date);
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


        //save to firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("Task");
        Task task =new Task();
        EditText title,Time;
        Button add;
        title=(EditText)findViewById(R.id.Title);
        Time=(EditText)findViewById(R.id.time);
        add=(Button)findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String t = title.getText().toString().trim();
                String p = importance.getSelectedItem().toString();
                String d = mDisplayDate.getText().toString();
                String time = Time.getText().toString();
                task.setTitle(t);
                task.setImportance(p);
                task.setDueDate(d);
                task.setDueTime(time);
                myRef.push().setValue(task);
            }
        });

        Button cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(popupWindow.this, MainActivity.class);
                startActivity(intent);
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
        };

        EditText et_title,et_date,et_time,et_p;
        et_title = findViewById(R.id.Title);
        et_date = findViewById(R.id.date);
        et_time = findViewById(R.id.time);
        AddReminder();
    }

    private void AddReminder(){
        Button addBtn = (Button)findViewById(R.id.add);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Extract data from UI
                EditText title =(EditText)findViewById(R.id.Title);
                EditText date =(EditText)findViewById(R.id.date);
                EditText time =(EditText)findViewById(R.id.time);
                String t = title.getText().toString();
                String d = date.getText().toString();
                String ti = time.getText().toString();
                String imp = importance.getSelectedItem().toString();
                //Pass data
                Intent i = new Intent();
                i.putExtra("Title",t);
                i.putExtra("Date",d);
                i.putExtra("Time",ti);
                i.putExtra("Importance",imp);
                setResult(Activity.RESULT_OK,i);
                finish();
            }
        });
    }
}