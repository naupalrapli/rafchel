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

public class EditorActivity extends AppCompatActivity {

    private EditText editName, editTelp;
    private Button btnSave,btnKembali;
    private Helper db = new Helper(this);
    private String id,name, telp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        editName = findViewById(R.id.edit_name);
        editTelp = findViewById(R.id.edit_telp);
        btnSave = findViewById(R.id.btn_save);
        btnKembali = findViewById(R.id.btn_kembali);

        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        telp = getIntent().getStringExtra("telp");

        //button KEMBALI/BACK
        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditorActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        //jika id nya null/tidak apa apa/kosong berarti tambah data
        if (id == null || id.equals("")){
            setTitle("Tambah User");
        //jika tidak kosong maka edit data
        }else {
            setTitle("Edit User");
            editName.setText(name);
            editTelp.setText(telp);
        }

        //jika btn save nya di klik maka
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (id == null || id.equals("")){
                        save();
                    }else {
                        edit();
                    }
                }catch (Exception e){
                    Log.e("Saving", e.getMessage());
                }
            }
        });
    }

    private void save(){
        if (String.valueOf(editName.getText()).equals("") || String.valueOf(editTelp.getText()).equals("")){
            Toast.makeText(getApplicationContext(), "silahkan isi semua data", Toast.LENGTH_SHORT).show();
        }else {
            db.insert(editName.getText().toString(), editTelp.getText().toString());
            finish();
        }
    }

    private void edit(){
        if (String.valueOf(editName.getText()).equals("") || String.valueOf(editTelp.getText()).equals("")){
            Toast.makeText(getApplicationContext(), "silahkan isi semua data", Toast.LENGTH_SHORT).show();
        }else {
            db.update(Integer.parseInt(id), editName.getText().toString(), editTelp.getText().toString());
            finish();
        }
    }

}