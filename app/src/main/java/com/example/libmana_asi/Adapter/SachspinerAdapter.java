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
import com.example.libmana_asi.Model.Sach;
import com.example.libmana_asi.R;

import java.util.ArrayList;

public class SachspinerAdapter extends ArrayAdapter<Sach> {
    private  Context context;
    ArrayList<Sach> list;
    TextView tvMasach,tvtensach;

    public SachspinerAdapter(@NonNull Context context, ArrayList<Sach> list) {
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
            v=inflater.inflate(R.layout.sach_item_spiner,null);
        }
        final Sach item= list.get(position);
        if(item!=null){
            tvMasach=v.findViewById(R.id.tvMasachSP);
            tvMasach.setText(item.getMaSach()+".");
            tvtensach=v.findViewById(R.id.tvtensachSP);
            tvtensach.setText(item.getTenSach());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable  View convertView, @NonNull  ViewGroup parent) {
        View v= convertView;
        if(v==null){
            LayoutInflater inflater= (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.sach_item_spiner,null);
        }
        final Sach item= list.get(position);
        if(item!=null){
            tvMasach=v.findViewById(R.id.tvMasachSP);
            tvMasach.setText(item.getMaSach()+".");
            tvtensach=v.findViewById(R.id.tvtensachSP);
            tvtensach.setText(item.getTenSach());
        }
        return v;
    }
}
