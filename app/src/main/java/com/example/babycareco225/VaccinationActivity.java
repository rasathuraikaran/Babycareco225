package com.example.babycareco225;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class VaccinationActivity extends AppCompatActivity {


    RecyclerView recyclerView;

    DatabaseReference database;
    MyAdapter myAdapter;
    ArrayList<Vaccination> list;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(VaccinationActivity.this,MainActivity.class));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccination);


        getSupportActionBar().setTitle("Vaccinations Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        recyclerView =findViewById(R .id.VaccinationDetails);
        database= FirebaseDatabase.getInstance().getReference("Vaccine");

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list=new ArrayList<>();
        myAdapter=new MyAdapter(this,list);
        recyclerView.setAdapter(myAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Vaccination vaccination=dataSnapshot.getValue(Vaccination.class);
                    list.add(vaccination);


                }

                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}