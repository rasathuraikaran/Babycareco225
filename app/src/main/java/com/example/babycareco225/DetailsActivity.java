package com.example.babycareco225;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class DetailsActivity extends AppCompatActivity {
    Baby baby=new Baby();
    private EditText dob;
    private DatePickerDialog picker;
    Button btnDetails;
    EditText babyName;
    private DatabaseReference mdatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        dob=findViewById(R.id.DOB);
        btnDetails=findViewById(R.id.btndetails);
        Date d=new Date();
        int year1=d.getYear();
        final int[] babyyear = {0};
        mdatabaseReference= FirebaseDatabase.getInstance().getReference().child("baby");


        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar= Calendar.getInstance();
                int date=calendar.get(Calendar.DAY_OF_MONTH);
                int month=calendar.get(Calendar.MONTH);
            int  year=calendar.get(Calendar.YEAR);
                babyyear[0] =year;

                picker=new DatePickerDialog(DetailsActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dob.setText(dayOfMonth+"/"+(month+1)+"/"+year);

                    }
                },year,month,date);

                picker.show();

            }
        });


        btnDetails.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                // baby.setName((EditText)findViewById(R.id.Babyname).toString());
                babyName = (EditText)findViewById(R.id.Babyname);
                baby.setName(babyName.getText().toString());
                baby.setAge(year1-babyyear[0]);
              EditText babyheight=(EditText)findViewById(R.id.height);
              baby.setHeight(Integer.parseInt(babyheight.getText().toString()));

                EditText babyweight=(EditText)findViewById(R.id.weight);
                baby.setWeight(Integer.parseInt(babyweight.getText().toString()));

                ///baby.setId(mdatabaseReference.push().getKey());
               mdatabaseReference.push().setValue(baby);
                Toast.makeText(DetailsActivity.this,"Data Addede sucessfully,To", Toast.LENGTH_SHORT);



            }

        });
    }
}