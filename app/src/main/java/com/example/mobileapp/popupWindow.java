package com.example.mobileapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
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
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class popupWindow extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    Spinner importance;
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private ListView listView;
    private NotificationHelper myNotificationHelper;
    private Calendar  notificationCalendar;
    String titleEx ;
    String dateEx;
    String timeEx;
    String imprtaEx;
    int imprtaExInt=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window);

        DisplayMetrics sa = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(sa);

        int width = sa.widthPixels;
        int height = sa.heightPixels;
        getWindow().setLayout((int) (width * .9), (int) (height * .9));
        myNotificationHelper =new NotificationHelper(this);

        importance = findViewById(R.id.importance);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.importance,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        importance.setAdapter(adapter);

//        mDisplayDate = (TextView) findViewById(R.id.date);
//        mDisplayDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Calendar cal = Calendar.getInstance();
//                int year = cal.get(Calendar.YEAR);
//                int month = cal.get(Calendar.MONTH);
//                int day = cal.get(Calendar.DAY_OF_MONTH);
//
//                DatePickerDialog dialog = new DatePickerDialog(
//                        popupWindow.this,
//                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
//                        mDateSetListener,
//                        year,month,day);
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                dialog.show();
//            }
//        });

        //time
//        Calendar cal2 = Calendar.getInstance();
//        final int hour = cal2.get(Calendar.HOUR_OF_DAY);
//        final int minute = cal2.get(Calendar.MINUTE);
//        final EditText time = (EditText) findViewById(R.id.time);
//        time.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                TimePickerDialog timePick = new TimePickerDialog(popupWindow.this, new TimePickerDialog.OnTimeSetListener() {
//                    @Override
//                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
//                        time.setText(i + ":" + i1);
//                        notificationCalendar = Calendar.getInstance();
//                        notificationCalendar.set(Calendar.HOUR_OF_DAY,i);
////                        c.set(Calendar.MONTH,);
////                        final int day = c.get(Calendar.DAY_OF_MONTH);
////                        final int hour = c.get(Calendar.HOUR_OF_DAY);
//                        notificationCalendar.set(Calendar.MINUTE,i1);
//                        notificationCalendar.set(Calendar.SECOND,0);
//                    }
//                }, hour , minute , true);
//                timePick.setTitle("Select date time");
//                timePick.show();
//            }
//        });//time end
//
//        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
//                month = month + 1;
//                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);
//
//                String date = month + "/" + day + "/" + year;
//                mDisplayDate.setText(date);
//                notificationCalendar = Calendar.getInstance();
//                notificationCalendar.set(Calendar.YEAR,year);
////                        c.set(Calendar.MONTH,);
////                        final int day = c.get(Calendar.DAY_OF_MONTH);
////                        final int hour = c.get(Calendar.HOUR_OF_DAY);
//                notificationCalendar.set(Calendar.MONTH,month);
//                notificationCalendar.set(Calendar.DAY_OF_MONTH,day);
//            }
//        };



        final Calendar c = Calendar.getInstance();
        final int year = c.get(Calendar.YEAR);
        final int month = c.get(Calendar.MONTH);
        final int day = c.get(Calendar.DAY_OF_MONTH);
        final int hour = c.get(Calendar.HOUR_OF_DAY);
        final int minute = c.get(Calendar.MINUTE);
        final EditText txtTime = (EditText) findViewById(R.id.time);
        final EditText txtDate = (EditText) findViewById(R.id.date);

        txtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TimePickerDialog timePick = new TimePickerDialog(popupWindow.this, new TimePickerDialog.OnTimeSetListener() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        txtTime.setText(i + ":" + i1);
                        timeEx = txtTime.getText().toString().trim();

                        notificationCalendar = Calendar.getInstance();
                        notificationCalendar.set(Calendar.HOUR_OF_DAY,i);
                        notificationCalendar.set(Calendar.MINUTE,i1);
                        notificationCalendar.set(Calendar.SECOND,0);

                    }
                }, hour , minute , true);
                timePick.setTitle("Select date time");
                timePick.show();
            }
        });


        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePick = new DatePickerDialog(popupWindow.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        txtDate.setText(i + "-" + i1 + "-" + i2);
                        dateEx = txtDate.getText().toString().trim();
                        notificationCalendar = Calendar.getInstance();
                        notificationCalendar.set(Calendar.YEAR,i);
                        notificationCalendar.set(Calendar.MONTH,i1);
                        notificationCalendar.set(Calendar.DAY_OF_MONTH,i2);
                    }
                }, year , month, day);
                datePick.setTitle("Select Date");
                datePick.show();

            }
        });



        //save to firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("Task");
        Task task =new Task();
        EditText title,Time;
        Button add;
        title=(EditText)findViewById(R.id.Title);
        Time=(EditText)findViewById(R.id.time);
        add=(Button)findViewById(R.id.addreminder);
        add.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                titleEx = title.getText().toString().trim();
                imprtaEx = importance.getSelectedItem().toString().trim();
                if (imprtaEx.equalsIgnoreCase("Low")){
                    imprtaExInt = 1;
                }else {
                    imprtaExInt = 2;
                }
//                dateEx = mDisplayDate.getText().toString();
//                timeEx = Time.getText().toString();
                task.setTitle(titleEx);
                task.setImportance(imprtaEx);
                task.setDueDate(dateEx);
                task.setDueTime(timeEx);
                sendOnChanels(imprtaEx, titleEx,dateEx, timeEx);
                myRef.push().setValue(task);
            }
        });

        Button cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



//        EditText et_title,et_date,et_time,et_p;
//        et_title = findViewById(R.id.Title);
//        et_date = findViewById(R.id.date);
//        et_time = findViewById(R.id.time);
//        AddReminder();
    }

//    private void AddReminder(){
//        Button addBtn = (Button)findViewById(R.id.addreminder);
//        addBtn.setOnClickListener(new View.OnClickListener() {
//            FirebaseDatabase database = FirebaseDatabase.getInstance();
//            DatabaseReference myRef = database.getReference().child("Task");
//            Task task =new Task();
//            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//            @Override
//            public void onClick(View v) {
//                //Extract data from UI
//                EditText title =(EditText)findViewById(R.id.Title);
//                EditText date =(EditText)findViewById(R.id.date);
//                EditText time =(EditText)findViewById(R.id.time);
//                String t = title.getText().toString();
//                String d = date.getText().toString();
//                String ti = time.getText().toString();
//                String imp = importance.getSelectedItem().toString();
//                task.setTitle(t);
//                task.setImportance(imp);
//                task.setDueDate(d);
//                task.setDueTime(ti);
//                myRef.push().setValue(task);
//                sendOnChanels("High", titleEx,dateEx, timeEx);
//
//                //Pass data
//                Intent i = new Intent();
//                i.putExtra("Title",t);
//                i.putExtra("Date",d);
//                i.putExtra("Time",ti);
//                i.putExtra("Importance",imp);
//                setResult(Activity.RESULT_OK,i);
//                finish();
//            }
//        });
//    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void sendOnChanels(String importance1, String title, String date,String message) {

        if (importance1.equalsIgnoreCase("H")){
            NotificationCompat.Builder nb = myNotificationHelper.getChanelNotification(2,title,date,message);
            startAlarm(notificationCalendar);
        }
        else {
            NotificationCompat.Builder nb = myNotificationHelper.getChanelNotification(1,title,date,message);
            startAlarm(notificationCalendar);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void startAlarm(Calendar c){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this , AlertReceiver.class);
        intent.putExtra("title", titleEx);
        intent.putExtra("time", timeEx);
        intent.putExtra("imp", imprtaEx);
        intent.putExtra("date", dateEx);

        Log.d("myTag", "This is my message"+imprtaEx);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,imprtaExInt,intent,0);
        if (!(c.before(Calendar.getInstance()))){
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(),pendingIntent);
        }

        else
            Toast.makeText(popupWindow.this, "reminder doesn't added", Toast.LENGTH_LONG).show();

    }

}