package com.week1.apps;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import model.Orang;

public class ProfileActivity extends AppCompatActivity {

    private Toolbar toolbar3;
    private ActionBar actionBar;
    private TextView namaProf, ageProf, addressProf;
    private ImageView editButton, deleteButton;

    final LoadingDialog loadingDialog = new LoadingDialog(ProfileActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        toolbar3 = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar3);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        initView();

    }

    private void initView() {
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);
        Orang editOrang = intent.getParcelableExtra("editOrang");
        namaProf = findViewById(R.id.namaProf);
        ageProf = findViewById(R.id.ageProf);
        addressProf = findViewById(R.id.addressProf);
        toolbar3 = findViewById(R.id.toolbar3);
        editButton = findViewById(R.id.editButton);
        deleteButton = findViewById(R.id.deleteButton);
        namaProf.setText(editOrang.getName());
        ageProf.setText(String.valueOf(editOrang.getAge()) + " years old");
        addressProf.setText(editOrang.getAddress());

        toolbar3.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog();
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent edit = new Intent(getBaseContext(), addeditUser.class);
                edit.putExtra("position", position);
                edit.putExtra("editmode", 3);
                startActivity(edit);
                finish();
            }
        });
    }

    public void alertDialog(){
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);
        Orang editOrang = intent.getParcelableExtra("editOrang");
        new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("Confirm Delete")
                .setMessage("Are you sure to delete " + editOrang.getName() + " data ?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        loadingDialog.startLoadingDialog();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                MainActivity.userData.remove(position);
                                MainActivity.adapter.notifyDataSetChanged();
                                finish();
                            }
                        }, 1000);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }
}