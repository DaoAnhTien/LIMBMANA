package com.example.libmana_asi.Framment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.libmana_asi.Adapter.LoaisachAdapter;
import com.example.libmana_asi.Adapter.ThanhVienAdapter;
import com.example.libmana_asi.Dao.LoaiSachDAO;
import com.example.libmana_asi.Dao.ThanhVienDAO;
import com.example.libmana_asi.Model.LoaiSach;
import com.example.libmana_asi.Model.ThanhVien;
import com.example.libmana_asi.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class LoaiSachFragment extends Fragment {
    ListView lvLoaiSach;
    ArrayList<LoaiSach> list;
    static LoaiSachDAO dao;
    LoaisachAdapter adapter;
    LoaiSach item;

    FloatingActionButton fab;
    Dialog dialog;
    EditText edmaLoai,edTenLoai;
    Button btnSaveLs,btncancelLs;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_loai_sach, container, false);
        lvLoaiSach=v.findViewById(R.id.LVLoaisach);
        dao = new LoaiSachDAO(getActivity());
        fab= v.findViewById(R.id.fabLS);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(),0);
                capnhatLV();
            }
        });
        lvLoaiSach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item=list.get(position);
                openDialog(getActivity(),1);
                return false;
            }
        });
        capnhatLV();
        return v;

    }


    void capnhatLV() {
        list = (ArrayList<LoaiSach>) dao.getALL();
        adapter = new LoaisachAdapter(getActivity(), this, list);
        lvLoaiSach.setAdapter(adapter);
    }
    public  void delete(final  String id){
        //su dung Alert
        AlertDialog.Builder builder= new AlertDialog.Builder(getContext());
        builder.setTitle("Dlete");
        builder.setMessage("Bạn có muôn xóa không ");
        builder.setCancelable(true);
        builder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dao.delete(id);
                        capnhatLV();
                        dialog.cancel();
                    }
                });
        builder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog dialog=builder.create();
        dialog.show();
    }

    protected void openDialog(final Context context,final int type){
        dialog= new Dialog(context);
        dialog.setContentView(R.layout.loaisach_dialog);
        edmaLoai=dialog.findViewById(R.id.edMaLoaisach);
        edTenLoai=dialog.findViewById(R.id.edtenLoai);

        btnSaveLs=dialog.findViewById(R.id.btnSaveLS);
        btncancelLs=dialog.findViewById(R.id.btncancelLS);
        //kiem tra type
        edmaLoai.setEnabled(false);

        if(type!=0){
            edmaLoai.setText(String.valueOf(item.getMaLoai()));
            edTenLoai.setText(item.getTenLoai());

        }
        btncancelLs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSaveLs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item= new LoaiSach();
                item.setTenLoai(edTenLoai.getText().toString());
                if(validate()>0){
                    if(type==0){
                        if(dao.insert(item)>0){
                            Toast.makeText(context,"Thêm thành công",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context,"Thêm không  thành công",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        item.setMaLoai(Integer.parseInt(edmaLoai.getText().toString()));
                        if(dao.updata(item)>0){
                            Toast.makeText(getActivity(),"Sửa thành công",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getActivity(),"sửa không thành công",Toast.LENGTH_SHORT).show();
                        }

                    }
                    capnhatLV();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }
    public  int validate(){
        int chek =1;
        if(edTenLoai.getText().length()==0){
            Toast.makeText(getActivity(),"bạn Phải nhap đủ thong tin",Toast.LENGTH_SHORT).show();
            chek=-1;
        }
        return  chek;
    }

}