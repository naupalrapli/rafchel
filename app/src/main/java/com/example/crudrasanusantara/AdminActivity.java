package com.example.crudrasanusantara;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity {

    String username = "admin";
    String password = "admin123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        EditText txtUser = findViewById(R.id.txtUser);
        EditText txtPass = findViewById(R.id.txtPass);
        Button btnmasuk1 = findViewById(R.id.btnmasuk1);
        Button btnkembali1 = findViewById(R.id.btnkembali1);

        btnmasuk1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtUser.getText().toString().equalsIgnoreCase(username)&& txtPass.getText().toString().equalsIgnoreCase(password)){
                    startActivity(new Intent(AdminActivity.this, MainActivity2.class));
                }else {
                    Toast.makeText(AdminActivity.this, "Username/Password salah", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnkembali1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}