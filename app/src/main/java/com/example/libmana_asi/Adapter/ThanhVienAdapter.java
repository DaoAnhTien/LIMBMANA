package com.example.libmana_asi.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.libmana_asi.Dao.ThanhVienDAO;
import com.example.libmana_asi.Framment.ThanhVienFragment;
import com.example.libmana_asi.Model.ThanhVien;
import com.example.libmana_asi.R;

import java.util.ArrayList;
import java.util.List;

public class ThanhVienAdapter  extends ArrayAdapter<ThanhVien> {
    private  Context context;
    ThanhVienFragment fragment;
    private ArrayList<ThanhVien> lists;
    TextView tvMatv,tvTenTV,tvNamsinh;
    ImageView imgdel;
    ThanhVienDAO dao;

    public ThanhVienAdapter(@NonNull  Context context, ThanhVienFragment fragment, ArrayList<ThanhVien> lists) {
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
            LayoutInflater inflater= (LayoutInflater)
                    context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.thanhvien_item,null);
        }
        final ThanhVien item=lists.get(position);
        if(item!=null){
            tvTenTV=v.findViewById(R.id.tvTentv);
            tvTenTV.setText("Tên Thành Viên :"+item.getHoTen());
            tvMatv=v.findViewById(R.id.tvMatv);
            tvMatv.setText("Mã thành viên: "+item.getMaTv());
            tvNamsinh=v.findViewById(R.id.tvNamsinh);
            tvNamsinh.setText("Năm Sinh"+item.getNamSinh());
            imgdel=v.findViewById(R.id.imgDeleteLS);
        }
        imgdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //phuong thuc xoa
                fragment.delete(String.valueOf(item.getMaTv()));
            }
        });




        return v;
    }

}
