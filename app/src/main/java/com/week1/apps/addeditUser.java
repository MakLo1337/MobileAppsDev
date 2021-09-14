package com.week1.apps;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import model.Orang;

public class addeditUser extends AppCompatActivity {

    private EditText nameInput, ageInput, addressInput;
    private Button addButton;
    private Toolbar toolbar2;
    private ActionBar actionBar;

    final LoadingDialog loadingDialog = new LoadingDialog(addeditUser.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        toolbar2 = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar2);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        int pos = intent.getIntExtra("position", 0);
        int option = intent.getIntExtra("editmode", 0);
        nameInput = findViewById(R.id.nameInput);
        ageInput = findViewById(R.id.ageInput);
        addressInput = findViewById(R.id.addressInput);
        addButton = findViewById(R.id.addButton);

        if (option == 3) {
            Orang edit = MainActivity.userData.get(pos);
            nameInput.setText(edit.getName());
            ageInput.setText(String.valueOf(edit.getAge()));
            addressInput.setText(edit.getAddress());
            addButton.setText("Edit");
            toolbar2.setTitle("Edit User");
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = nameInput.getText().toString().trim();
                    String age = ageInput.getText().toString().trim();
                    String address = addressInput.getText().toString().trim();

                    if (name.isEmpty() && age.isEmpty() && address.isEmpty()) {
                        Toast.makeText(addeditUser.this, "Please fill all the column!", Toast.LENGTH_SHORT).show();
                    } else {
                        loadingDialog.startLoadingDialog();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                loadingDialog.dismissDialog();
                                String name = nameInput.getText().toString().trim();
                                int age = Integer.parseInt(ageInput.getText().toString().trim());
                                String address = addressInput.getText().toString().trim();
                                Orang orangBaru = new Orang(name, address, age);
                                MainActivity.userData.set(pos, orangBaru);
                                MainActivity.adapter.notifyDataSetChanged();
                                finish();
                            }
                        }, 1000);
                    }
                }
            });

            toolbar2.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = nameInput.getText().toString().trim();
                    int age = Integer.parseInt(ageInput.getText().toString().trim());
                    String address = addressInput.getText().toString().trim();
                    Orang orangBaru = new Orang(name, address, age);
                    Intent intent = new Intent(getBaseContext(), ProfileActivity.class);
                    intent.putExtra("position", pos);
                    intent.putExtra("editOrang", orangBaru);
                    startActivity(intent);
                    finish();
                }
            });
        } else {

            nameInput.addTextChangedListener(editListener);
            ageInput.addTextChangedListener(editListener);
            addressInput.addTextChangedListener(editListener);

            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = nameInput.getText().toString().trim();
                    String age = ageInput.getText().toString().trim();
                    String address = addressInput.getText().toString().trim();

                    if (name.isEmpty() && age.isEmpty() && address.isEmpty()) {
                        Toast.makeText(addeditUser.this, "Please fill all the column!", Toast.LENGTH_SHORT).show();
                    } else {
                        loadingDialog.startLoadingDialog();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                loadingDialog.dismissDialog();
                                String name = nameInput.getText().toString().trim();
                                int age = Integer.parseInt(ageInput.getText().toString().trim());
                                String address = addressInput.getText().toString().trim();
                                Orang orangBaru = new Orang(name, address, age);
                                Intent intent = new Intent();
                                intent.putExtra("orangBaru", orangBaru);
                                setResult(200, intent);
                                finish();
                            }
                        }, 1000);
                    }
                }
            });

            toolbar2.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    public TextWatcher editListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String name = nameInput.getText().toString().trim();
            String age = ageInput.getText().toString().trim();
            String address = addressInput.getText().toString().trim();

            addButton.setEnabled(!name.isEmpty() && !age.isEmpty() && !address.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}