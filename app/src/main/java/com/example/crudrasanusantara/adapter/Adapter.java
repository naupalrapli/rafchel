package com.example.crudrasanusantara.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.crudrasanusantara.R;
import com.example.crudrasanusantara.model.Data;

import java.util.List;


public class Adapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    //list yang dipanggil adalah file model(data)
    private List<Data> lists;

    public Adapter(Activity activity, List<Data> lists){
        this.activity = activity;
        this.lists = lists;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int i) {
        return lists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (inflater == null ){
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (view == null && inflater != null){
            view = inflater.inflate(R.layout.list_users, null);
        }
        if (view != null) {
            //memanggil TextView yang ada di layout lists_user
            TextView name = view.findViewById(R.id.text_name);
            TextView telp = view.findViewById(R.id.text_telp);
            //panggil data yang ada di dalam model(Data)
            Data data = lists.get(i);
            name.setText(data.getName());
            telp.setText(data.getTelp());
        }
        return view;
    }
}
