package com.example.crudrasanusantara;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.crudrasanusantara.adapter.Adapter;
import com.example.crudrasanusantara.helper.Helper;
import com.example.crudrasanusantara.model.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    ListView listView;
    //untuk menampilkan dialog kalo ada error
    AlertDialog.Builder dialog;
    List<Data> lists = new ArrayList<>();
    Adapter adapter;
    Helper db = new Helper(this);
    Button btnAdd,btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        db = new Helper(getApplicationContext());
        btnAdd = findViewById(R.id.btn_add);
        btnBack = findViewById(R.id.btn_back);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, AdminActivity.class);
                startActivity(intent);
            }
        });

        //membuat fungsi jika btnnya di klik maka akan buka activity editor
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, EditorActivity.class);
                startActivity(intent);
            }
        });


        listView = findViewById(R.id.list_item);
        adapter = new Adapter(MainActivity2.this, lists);
        listView.setAdapter(adapter);



        //jika ditekan lama akan menampilkan dialog hapus/edit
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long I) {
                final String id = lists.get(i).getId();
                final String name = lists.get(i).getName();
                final String telp = lists.get(i).getTelp();
                final CharSequence[] dialogItem = {"Edit", "Hapus"};
                dialog = new AlertDialog.Builder(MainActivity2.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            //untuk mengedit
                            case 0:
                                //membuat Intent untuk memamggil activity editor
                                Intent intent = new Intent(MainActivity2.this, EditorActivity.class);
                                intent.putExtra("id", id);
                                intent.putExtra("name", name);
                                intent.putExtra("telp", telp);
                                startActivity(intent);
                                break;
                            //untuk menghapus
                            case 1:
                                db.delete(Integer.parseInt(id));
                                lists.clear();
                                //panggil data ulang
                                getData();
                                break;
                        }

                    }
                }).show();
                return false;
            }
        });
        getData();
    }



    private void getData(){
        ArrayList<HashMap<String, String>> rows = db.getAll();
        for (int i = 0; i<rows.size(); i++){
            String id = rows.get(i).get("id");
            String name = rows.get(i).get("name");
            String telp = rows.get(i).get("telp");

            //memanggil model(data), data ini dimasukin ke dalam list
            Data data = new Data();
            data.setId(id);
            data.setName(name);
            data.setTelp(telp);
            lists.add(data);
        }
        //memberitahu adapter bahwa datanya sudah berubah/dipanggil ulang
        adapter.notifyDataSetChanged();
    }


    @Override
    protected void onResume() {
        super.onResume();
        lists.clear();
        getData();
    }
}