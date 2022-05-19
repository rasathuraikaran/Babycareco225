package com.example.babycareco225;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.mikephil.charting.data.Entry;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class WeightActivity extends AppCompatActivity {
EditText xValue,yValue;
Button btnInsert;
FirebaseDatabase database;
DatabaseReference reference;
LineGraphSeries series;
GraphView graphView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);
        xValue=(EditText) findViewById(R.id.xValues);
        xValue=(EditText) findViewById(R.id.yValue);

        btnInsert=(Button) findViewById(R.id.btnInsert);
        graphView=(GraphView) findViewById(R.id.graph);
        series=new LineGraphSeries();
        graphView.addSeries(series);
        database=FirebaseDatabase.getInstance();
        reference=database.getReference("jvsdk");
        setListeners();




    }

    private void setListeners() {
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=reference.push().getKey();
                int x=Integer.parseInt(xValue.getText().toString());
                int y=Integer.parseInt(yValue.getText().toString());
                DataPoint datapoint=new DataPoint(x,y);
                reference.child(id).setValue(datapoint);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();



        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataPoint[] dp=new DataPoint[(int)snapshot.getChildrenCount()];
                int index=0;



                    for (DataSnapshot mydataSnaphot:snapshot.getChildren()){

                        DataPoint dataPoint=mydataSnaphot.getValue(DataPoint.class);
                        dp[index]=new DataPoint(dataPoint.getxValue(),dataPoint.getyValue());
                        index++;
                    }
                   series.resetData((DataPointInterface[]) dp);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}