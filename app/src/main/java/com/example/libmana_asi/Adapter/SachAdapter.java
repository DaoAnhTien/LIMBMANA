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
import com.example.libmana_asi.Dao.SachDAO;
import com.example.libmana_asi.Dao.ThanhVienDAO;
import com.example.libmana_asi.Framment.SachFragment;
import com.example.libmana_asi.Framment.ThanhVienFragment;
import com.example.libmana_asi.Model.LoaiSach;
import com.example.libmana_asi.Model.Sach;
import com.example.libmana_asi.Model.ThanhVien;
import com.example.libmana_asi.R;

import java.util.ArrayList;
import java.util.List;

public class SachAdapter extends ArrayAdapter<Sach> {
    private  Context context;
    SachFragment fragment;
    List<Sach> list;
    TextView tvMasach,tvTenSach,tvgiathue,tvLoai;
    ImageView imgdel;
    SachDAO dao;

    public SachAdapter(@NonNull  Context context, SachFragment fragment, List<Sach> lists) {
        super(context, 0, lists);
        this.context = context;
        this.fragment = fragment;
        this.list = lists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v= convertView;
        if(v==null){
            LayoutInflater inflater= (LayoutInflater)
                    context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.sach_item,null);
        }
        final Sach item=list.get(position);
        if(item!=null){
            LoaiSachDAO loaiSachDAO= new LoaiSachDAO(context);
            LoaiSach loaiSach= loaiSachDAO.getID(String.valueOf(item.getMaLoai()));
            tvTenSach=v.findViewById(R.id.tvTenSach);
            tvTenSach.setText("Tên sách :"+item.getTenSach());
            tvMasach=v.findViewById(R.id.tvMasach);
            tvMasach.setText("Mã Sách: "+item.getMaSach());
            tvgiathue=v.findViewById(R.id.tvGiathue);
            tvgiathue.setText("Giá Sách"+item.getGiaSach());
            tvLoai=v.findViewById(R.id.tvLoai);
            tvLoai.setText("Loại Sách:"+loaiSach.getTenLoai());
            imgdel=v.findViewById(R.id.imgDeleteLS);
        }
        imgdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //phuong thuc xoa
               fragment.delete(String.valueOf(item.getMaSach()));
            }
        });




        return v;
    }

}
