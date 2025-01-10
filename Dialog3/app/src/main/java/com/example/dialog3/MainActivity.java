package com.example.dialog3;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void btn_pick_time(View v){
        int dH, dMin;
        Calendar c= Calendar.getInstance();
        dH=c.get(Calendar.HOUR);
        dMin=c.get(Calendar.MINUTE);
        TimePickerDialog tpd = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                Toast.makeText(MainActivity.this, i+"H:,"+i1+"m",Toast.LENGTH_SHORT).show();

            }
        }, dH, dMin,false);
        tpd.show();
    }

    public void btn_pick_date(View v){
        int dYear,dMon,dDay;
        Calendar c=Calendar.getInstance();
        dYear=c.get(Calendar.YEAR);
        dMon=c.get(Calendar.MONTH);
        dDay=c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                Toast.makeText(MainActivity.this, i+"Y "+(i1+1)+"m "+(i2+1)+"d ",Toast.LENGTH_LONG).show();
            }
        },dYear,dMon,dDay);
        dpd.setTitle("Pick a Date");
        dpd.setMessage("Hello World");
        dpd.show();
    }

    public void btn_show_dialog(View v){
        Log.d("This is a Dialog","btn_show_dialog:one");
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        Log.d("This is a Dialog","btn_show_dialog:two");
        Toast.makeText(this, "Hi",Toast.LENGTH_SHORT).show();
        adb.setTitle("Dialog");
        adb.setMessage("This is an Alert Dialog");
        adb.setCancelable(true);
        adb.setPositiveButton("OK!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Ok is Clicked!",Toast.LENGTH_SHORT).show();
            }
        });
        adb.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "No is Clicked",Toast.LENGTH_SHORT).show();

            }
        });
        AlertDialog ad= adb.create();
        Log.d("Dialog","btn_show_dialog:three"+ad.toString());
        ad.show();
    }


}