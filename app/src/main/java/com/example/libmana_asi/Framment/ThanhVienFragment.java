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

import com.example.libmana_asi.Adapter.ThanhVienAdapter;
import com.example.libmana_asi.Dao.ThanhVienDAO;
import com.example.libmana_asi.Model.ThanhVien;
import com.example.libmana_asi.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class ThanhVienFragment extends Fragment {
    ListView lvThanhvien;
    ArrayList<ThanhVien> list;
    static ThanhVienDAO dao;
    ThanhVienAdapter adapter;
    ThanhVien item;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaTV,edNamsinh,edtenTV;
    Button btnSaveTV,btncancelTv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_thanh_vien, container, false);
        lvThanhvien=v.findViewById(R.id.lvthanhvien);
        fab= v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenDialog(getActivity(),0);
            }
        });
        lvThanhvien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                item=list.get(position);
                OpenDialog(getActivity(),1);//update
            }
        });
        dao= new ThanhVienDAO(getActivity());
        capnhatLV();
        return v;
    }
    void capnhatLV(){
        list=(ArrayList<ThanhVien>)dao.getALL();
        adapter= new ThanhVienAdapter(getActivity(),this,list);
        lvThanhvien.setAdapter(adapter);
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
    protected  void OpenDialog(final Context context,final int type){
        dialog= new Dialog(context);
        dialog.setContentView(R.layout.thanhvien_dialog);
        edMaTV=dialog.findViewById(R.id.edMaTV);
        edtenTV=dialog.findViewById(R.id.edtenTV);
        edNamsinh=dialog.findViewById(R.id.ednamSinh);
        btnSaveTV=dialog.findViewById(R.id.btnSaveTV);
        btncancelTv=dialog.findViewById(R.id.btncancelTV);
        edMaTV.setEnabled(false);
        if(type!=0){
            edMaTV.setText(String.valueOf(item.getMaTv()));
            edtenTV.setText(item.getHoTen());
            edNamsinh.setText(item.getNamSinh());
        }
        btnSaveTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item= new ThanhVien();
                item.setHoTen(edtenTV.getText().toString());
                item.setNamSinh(edNamsinh.getText().toString());
                if(validate()>0){
                    //lưu dũ lieu
                    if(type==0){
                        if(dao.insert(item)>0){
                            Toast.makeText(getActivity(),"Thêm Thành công",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getActivity(),"Thêm Thất bại",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        //update
                        item.setMaTv(Integer.parseInt(edMaTV.getText().toString()));
                        if(dao.updata(item)>0){
                            Toast.makeText(getActivity(),"Sửa  Thành công",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getActivity(),"sửa không Thành công",Toast.LENGTH_SHORT).show();
                        }

                    }
                    capnhatLV();
                    dialog.dismiss();
                }

            }
        });
        dialog.show();
        btncancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
    }
    public  int validate(){
        int chek =1;
        if(edtenTV.getText().length()==0||edNamsinh.getText().length()==0){
            Toast.makeText(getActivity(),"bạn Phải nhap đủ thong tin",Toast.LENGTH_SHORT).show();
            chek=1;
        }
        return  chek=1;
    }

}