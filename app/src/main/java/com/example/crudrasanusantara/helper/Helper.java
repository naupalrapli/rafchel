package com.example.crudrasanusantara.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class Helper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "crudrn";


    public Helper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //hanya dipanggil sekali setelah aplikasi di instal&db dibuat
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_TABLE = "CREATE TABLE users (id INTEGER PRIMARY KEY autoincrement, name TEXT NOT NULL, telp TEXT NOT NULL)";
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int il) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS users");
        onCreate(sqLiteDatabase);
    }

    //membuat query untuk menampilkan semua data
    public ArrayList<HashMap<String, String>> getAll(){
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        //membuat QUERY untuk mengambil semua data
        String QUERY = "SELECT * FROM users";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(QUERY, null);
        //jadi cursornya ke data yang pertama duli
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("id", cursor.getString(0));
                map.put("name", cursor.getString(1));
                map.put("telp", cursor.getString(2));
                list.add(map);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    //QUERY INSERT / menambah data ke dalam db
    public void insert(String name, String telp){
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "INSERT INTO users (name, telp) VALUES('"+name+"', '"+telp+"')";
        database.execSQL(QUERY);
    }

    //QUERY UPDATE
    public void update (int id, String name, String telp){
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "UPDATE users SET name = '"+name+"', telp = '"+telp+"' WHERE id = "+id;
        database.execSQL(QUERY);
    }

    //QUERY DELETE
    public void delete(int id){
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "DELETE FROM users WHERE id = "+id;
        database.execSQL(QUERY);
    }
}
