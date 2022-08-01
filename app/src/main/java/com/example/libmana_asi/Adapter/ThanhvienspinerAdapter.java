package com.example.libmana_asi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.libmana_asi.Model.Sach;
import com.example.libmana_asi.Model.ThanhVien;
import com.example.libmana_asi.R;

import java.util.ArrayList;

public class ThanhvienspinerAdapter extends ArrayAdapter<ThanhVien> {
    private  Context context;
    ArrayList<ThanhVien> list;
    TextView tvmaTV,tvTenTV;

    public ThanhvienspinerAdapter(@NonNull Context context, ArrayList<ThanhVien> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v= convertView;
        if(v==null){
            LayoutInflater inflater= (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.thanhvien_item_spiner,null);
        }
        final ThanhVien item= list.get(position);
        if(item!=null){
            tvmaTV=v.findViewById(R.id.tvmaTvsp);
            tvmaTV.setText(item.getMaTv()+"");
            tvTenTV=v.findViewById(R.id.tvtenTvsp);
            tvTenTV.setText(item.getHoTen());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable  View convertView, @NonNull  ViewGroup parent) {
        View v= convertView;
        if(v==null){
            LayoutInflater inflater= (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.thanhvien_item_spiner,null);
        }
        final ThanhVien item= list.get(position);
        if(item!=null){
            tvmaTV=v.findViewById(R.id.tvmaTvsp);
            tvmaTV.setText(item.getMaTv()+".");
            tvTenTV=v.findViewById(R.id.tvtenTvsp);
            tvTenTV.setText(item.getHoTen());
        }
        return v;
    }
}
