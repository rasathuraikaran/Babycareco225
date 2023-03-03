package com.example.babycareco225;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class BMIActivity extends AppCompatActivity {
    private EditText height;
    private EditText weight;
    private TextView result;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("BMI Calculator");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_bmiactivity);
        height=(EditText)findViewById(R.id.bmiHeight);
        weight=(EditText)findViewById(R.id.bmiWeight);

        result=(TextView) findViewById(R.id.resultBMI);



    }
    public void CalculateBMI(View v){


        String h=height.getText().toString();
        String w =weight.getText().toString();

        if(h!=null && !"".equals(h)&& w!=null && !"".equals(w)){

            float hf= Float.parseFloat(h);
            float wf= Float.parseFloat(w);

            float bmi=(wf/(hf*hf))*10000;
            String lbl="";
            if(bmi<=18.5) {lbl="Under Weight ";}
            else if(bmi>18.5 &&bmi<=24.99){ lbl="Normal";}
            else {lbl ="Obesity";}
            result.setText(bmi +"\n\n"+lbl);



        }


    }
}