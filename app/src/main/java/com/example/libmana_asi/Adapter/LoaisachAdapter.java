package com.example.libmana_asi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.libmana_asi.Dao.LoaiSachDAO;
import com.example.libmana_asi.Dao.ThanhVienDAO;
import com.example.libmana_asi.Framment.LoaiSachFragment;
import com.example.libmana_asi.Framment.ThanhVienFragment;
import com.example.libmana_asi.Model.LoaiSach;
import com.example.libmana_asi.Model.ThanhVien;
import com.example.libmana_asi.R;

import java.util.ArrayList;
import java.util.List;

public class LoaisachAdapter extends ArrayAdapter<LoaiSach> {
    private Context context;
    LoaiSachFragment fragment;
    private ArrayList<LoaiSach> lists;
    TextView tvMaLoai,tvTenLoai;
    ImageView imgdel;
//    LoaiSachDAO dao;

    public LoaisachAdapter(@NonNull  Context context, LoaiSachFragment fragment, ArrayList<LoaiSach> lists) {
        super(context, 0, lists);
        this.context = context;
        this.fragment = fragment;
        this.lists = lists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v= convertView;
        if(v==null){
            LayoutInflater inflate=(LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflate.inflate(R.layout.loaisach_item,null);
        }
        final LoaiSach item=lists.get(position);
        if(item!=null){
            tvMaLoai=v.findViewById(R.id.tvMaloaisach);
            tvMaLoai.setText("Mã Loại: "+item.getMaLoai());
            tvTenLoai=v.findViewById(R.id.tvTenLoaisach);
            tvTenLoai.setText("Tên Loai Sách: "+item.getTenLoai());
            imgdel=v.findViewById(R.id.imgDeleteS);

        }
        imgdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //phuon thuc xoa
                fragment.delete(String.valueOf(item.getMaLoai()));
            }
        });
        return v;
    }
}
