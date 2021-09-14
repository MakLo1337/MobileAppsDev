package com.week1.apps;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import model.Orang;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    public static ArrayList<Orang> userData;
    static RVAdapter adapter;
    private FloatingActionButton addNewButton;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        recyclerSetup();
        setListener();
    }

    private void initView(){
        recyclerView = findViewById(R.id.recyclerView);
        userData = new ArrayList<Orang>();
        adapter = new RVAdapter(userData);
        addNewButton = findViewById(R.id.addNewButton);
    }

    private void recyclerSetup(){
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    private void setListener(){
        addNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), addeditUser.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            if (resultCode == 200){
                Orang orangBaru = data.getParcelableExtra("orangBaru");
                userData.add(orangBaru);
                adapter.notifyDataSetChanged();
            }
        }
    }
}