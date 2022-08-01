package com.example.libmana_asi.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.libmana_asi.Dao.PhieuMuonDAO;
import com.example.libmana_asi.Dao.SachDAO;
import com.example.libmana_asi.Dao.ThanhVienDAO;
import com.example.libmana_asi.Framment.PhieumuonFragment;
import com.example.libmana_asi.Model.PhieuMuon;
import com.example.libmana_asi.Model.Sach;
import com.example.libmana_asi.Model.ThanhVien;
import com.example.libmana_asi.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PhieumuonAdapter extends ArrayAdapter<PhieuMuon> {
    PhieumuonFragment fragment;
    private Context context;
    private ArrayList<PhieuMuon> list;
    TextView tvMaPM,tvTenTV,tvTensach,tvTienThue,tvNgay,tvTrasach;
    ImageView imgdelete;
    SachDAO sachDAO;
    ThanhVienDAO thanhviendao;
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");

    public PhieumuonAdapter(@NonNull  Context context, PhieumuonFragment fragment, ArrayList<PhieuMuon> list) {
        super(context, 0, list);
        this.fragment = fragment;
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        if(v==null){
            LayoutInflater inflate=(LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflate.inflate(R.layout.phieumuon_item,null);
        }
        final PhieuMuon item=list.get(position);
        if(item!=null){
            tvMaPM=v.findViewById(R.id.tvmaPM);
            tvMaPM.setText("Mã phiếu: "+item.getMaPM());
            sachDAO = new SachDAO(context);
            Sach sach= sachDAO.getID(String.valueOf(item.getMaSach()));
            tvTensach=v.findViewById(R.id.tvTenSachPM);
            tvTensach.setText("Tên Sách: "+sach.getTenSach());
            thanhviendao= new ThanhVienDAO(context);
            ThanhVien thanhVien= thanhviendao.getID(String.valueOf(item.getMaTV()));
            tvTenTV=v.findViewById(R.id.tvtenTV);
            tvTenTV.setText("Tên:"+thanhVien.getHoTen());
            tvTienThue=v.findViewById(R.id.tvTienThue);
            tvTienThue.setText("Tiền Thuê: "+item.getTienThue());
            tvNgay=v.findViewById(R.id.tvNgay);
            tvNgay.setText("Ngày: "+sdf.format(item.getNgay()));
            tvTrasach=v.findViewById(R.id.tvTrasach);
            if(item.getTraSach()==1){
                tvTrasach.setTextColor(Color.BLUE);
                tvTrasach.setText("Đã Trả sách");
            }else{
                tvTrasach.setTextColor(Color.RED);
                tvTrasach.setText("Chưa Trả Sách");
            }
            imgdelete=v.findViewById(R.id.imgDeletePM);

        }
        imgdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //phuon thuc xoa
               fragment.delete(String.valueOf(item.getMaPM()));
            }
        });
        return v;
    }
}
