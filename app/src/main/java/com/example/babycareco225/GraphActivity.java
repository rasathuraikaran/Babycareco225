package com.example.babycareco225;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GraphActivity extends AppCompatActivity {

    EditText xValue,
            yValue;
    Button weightGraph;
    LineChart linechart;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myref;
    LineDataSet lineDataSet =new LineDataSet(null,null);
    ArrayList<ILineDataSet> iLineDataSet=new ArrayList<>();
    LineData lineData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        xValue=findViewById(R.id.NoOfMonths);
        yValue=findViewById(R.id.yWeight);
        weightGraph=findViewById(R.id.btnGraph);
        linechart=findViewById(R.id.lineChart);
        firebaseDatabase
                =FirebaseDatabase.getInstance();
        myref= firebaseDatabase.getReference("ChartValues");
        insertData();














    }

    private void insertData() {
        weightGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=myref.push().getKey();
                int x=Integer.parseInt(xValue.getText().toString());
                int y=Integer.parseInt(yValue.getText().toString());
                DataPoint datapoint=new DataPoint(x,y);
                myref.child(id).setValue(datapoint);
                retrieveData();
            }
        });
    }

    private void retrieveData() {
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Entry> dataVals=new ArrayList<Entry>();
                if(snapshot.hasChildren()){
                    for (DataSnapshot mydataSnaphot:snapshot.getChildren()){

                        DataPoint dataPoint=mydataSnaphot.getValue(DataPoint.class);
                        dataVals.add(new Entry(dataPoint.getxValue(),dataPoint.getyValue()));
                    }
                    showChart(dataVals);
                }else {

                    linechart.clear();
                    linechart.invalidate();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void showChart(ArrayList<Entry> dataVals) {
        lineDataSet.setValues(dataVals);
        lineDataSet.setLabel("Weight");
        iLineDataSet.clear();
        iLineDataSet.add(lineDataSet);
        lineData=new LineData((ILineDataSet) iLineDataSet);
        linechart.clear();
        linechart.setData(lineData);
        linechart.invalidate();


    }
}