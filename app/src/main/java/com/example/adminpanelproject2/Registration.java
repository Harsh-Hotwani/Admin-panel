package com.example.adminpanelproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {
    EditText eUsername , ePassword , eRePassword;
    Button registerbtn,loginbtn;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        eUsername = findViewById(R.id.regName);
        ePassword = findViewById(R.id.regPass);
        eRePassword = findViewById(R.id.regRePass);
        registerbtn = findViewById(R.id.regBtn);
        loginbtn = findViewById(R.id.loginBtn);
        dbHelper = new DBHelper(this);

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user,pass,rePass;
                user = eUsername.getText().toString();
                pass = ePassword.getText().toString();
                rePass = eRePassword.getText().toString();

                if (user=="" || pass=="" || rePass==""){
                    Toast.makeText(Registration.this, "please fill all the details", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (pass.equals(rePass))
                    {
                        if (dbHelper.checkUsername(user)){
                            Toast.makeText(Registration.this, "User already exists", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        boolean registeredSucess = dbHelper.insertUserData(user,pass);
                        if (registeredSucess)
                        {
                            Toast.makeText(Registration.this, "User registered sucessfully", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(Registration.this, "user registration failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(Registration.this, "password do not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registration.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}