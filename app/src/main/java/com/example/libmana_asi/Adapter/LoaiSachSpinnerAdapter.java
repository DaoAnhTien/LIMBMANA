package com.example.libmana_asi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.libmana_asi.Model.LoaiSach;
import com.example.libmana_asi.R;

import java.util.ArrayList;

public class LoaiSachSpinnerAdapter extends ArrayAdapter<LoaiSach> {
    Context context;
    ArrayList<LoaiSach> lists;
    TextView tvMaloaisach, tvTenLoaiSach;
    public LoaiSachSpinnerAdapter(@NonNull Context context, ArrayList<LoaiSach> lists) {
        super(context, 0, lists);
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v= convertView;
        if(v==null){
            LayoutInflater inflater= (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.loaisach_item_spiner,null);
        }
        final LoaiSach item= lists.get(position);
        if(item!=null){
            tvMaloaisach=v.findViewById(R.id.tvMaloaisachSp);
            tvMaloaisach.setText(item.getMaLoai()+".");
            tvTenLoaiSach=v.findViewById(R.id.tvTenloaiSachSp);
            tvTenLoaiSach.setText(item.getTenLoai());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v= convertView;
        if(v==null){
            LayoutInflater inflater= (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.loaisach_item_spiner,null);
        }
        final LoaiSach item= lists.get(position);
        if(item!=null){
            tvMaloaisach=v.findViewById(R.id.tvMaloaisachSp);
            tvMaloaisach.setText(item.getMaLoai()+".");
            tvTenLoaiSach=v.findViewById(R.id.tvTenloaiSachSp);
            tvTenLoaiSach.setText(item.getTenLoai());
        }
        return v;
    }
}
