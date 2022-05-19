package com.example.babycareco225;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DataInsertionActivity extends AppCompatActivity {
Button btnInsertionData1;
    // creating a variable for our
// Firebase Database.
    FirebaseDatabase firebaseDatabase;

    // creating a variable for our Database
// Reference for Firebase.
    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_insertion);


        // below line is used to get the
// instance of our FIrebase database.
        firebaseDatabase = FirebaseDatabase.getInstance();

// below line is used to get reference for our database.
        databaseReference = firebaseDatabase.getReference().child("childe");

        btnInsertionData1=(Button)findViewById(R.id.btnInsertionData);
        btnInsertionData1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(DataInsertionActivity.this,"youclcickef", Toast.LENGTH_SHORT);

                String path="/Users/karanrasathurai/Downloads/Babycareco225/app/src/main/res/raw/boys.csv";
                String line="";
                try {
                    BufferedReader br= new BufferedReader(new FileReader(path));
                    int count=0;
                    while((line=br.readLine())!=null){
                        if(count==0) continue;
                        Dataholder dataholder=new Dataholder();



                        String [] values=line.split(",");

                        dataholder.setHV(Integer.parseInt(values[19]));
                        dataholder.setLV(Integer.parseInt(values[4]));
                        dataholder.setMonth(Integer.parseInt(values[0]));

                        databaseReference.push()
                                .setValue(dataholder);
                        Toast.makeText(DataInsertionActivity.this,"Data Addede sucessfully,To", Toast.LENGTH_SHORT);



                        System.out.println(line);

                        count++;




                    }


                } catch (FileNotFoundException e) {
                    Intent myIntent = new Intent(view.getContext(), MainActivity.class);
                    startActivityForResult(myIntent, 0);
                    Toast.makeText(DataInsertionActivity.this,"file nnot found", Toast.LENGTH_SHORT);

                    e.printStackTrace();

                }
                catch (IOException e) {
                    Toast.makeText(DataInsertionActivity.this,"input out put", Toast.LENGTH_SHORT);

                    e.printStackTrace();

                }




            }
        });
    }
}