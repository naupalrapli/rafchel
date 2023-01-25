package com.example.crudrasanusantara;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crudrasanusantara.helper.Helper;

public class DaftarActivity extends AppCompatActivity {
    Button btnkembali, btnSimpan;
    private EditText editName1, editTelp1;
    private String id,name, telp;
    private Helper db = new Helper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        editName1 = (EditText) findViewById(R.id.uName);
        editTelp1 = (EditText) findViewById(R.id.uNomeja);
        btnkembali = (Button) findViewById(R.id.btn_kembali);
        btnSimpan = findViewById(R.id.btn_selesai);

        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        telp = getIntent().getStringExtra("telp");

        btnkembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        //jika btn save nya di klik maka
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (id == null || id.equals("")){
                        save();
                    }
                } catch (Exception e){
                    Log.e("Saving", e.getMessage());
                }
            }
        });
    }

    private void save(){
        if (validate()) {
            db.insert(editName1.getText().toString(), editTelp1.getText().toString());
            Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private boolean validate() {
        boolean valid = false;

        String name = editName1.getText().toString();
        String noTlp = editTelp1.getText().toString();

        if (name.isEmpty()) {
            valid = false;
            editName1.setError("Please enter valid name!");
        } else {
            valid = true;
        }

        if (noTlp.isEmpty()) {
            valid = false;
            editTelp1.setError("Pleaae enter valid no telepon!");
        } else {
            valid = true;
        }

        return valid;
    }
}