package com.example.libmana_asi.Framment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.libmana_asi.Adapter.LoaiSachSpinnerAdapter;
import com.example.libmana_asi.Adapter.SachAdapter;
import com.example.libmana_asi.Adapter.ThanhVienAdapter;
import com.example.libmana_asi.Dao.LoaiSachDAO;
import com.example.libmana_asi.Dao.SachDAO;
import com.example.libmana_asi.Dao.ThanhVienDAO;
import com.example.libmana_asi.Model.LoaiSach;
import com.example.libmana_asi.Model.Sach;
import com.example.libmana_asi.Model.ThanhVien;
import com.example.libmana_asi.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class SachFragment extends Fragment {
    ListView lvSach;
    ArrayList<Sach> list;
    static SachDAO dao;
    SachAdapter adapter;
    Sach item;
    LoaiSachDAO loaiSachDAO;

    FloatingActionButton fabs;
    Dialog dialog;
    EditText edmasach,edtensach,edgiathue,edtenloai;
    Button btnSaveS,btncancelS;

    Spinner spinner;
    LoaiSachSpinnerAdapter spinnerAdapter;
    ArrayList<LoaiSach> listLoaisach;
    int maLoaiSach,postion;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_sach, container, false);
        fabs=v.findViewById(R.id.fabS);
        lvSach=v.findViewById(R.id.LvSach);
        dao= new SachDAO(getActivity());
        fabs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               OpenDialog(getActivity(),0);
               capnhatLV();
            }
        });
        lvSach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item=list.get(position);
                OpenDialog(getActivity(),1);
                return false;
            }
        });
        capnhatLV();
        return v;
    }
    void capnhatLV(){
        list=(ArrayList<Sach>)dao.getALL();
        adapter= new SachAdapter(getActivity(),this,list);
        lvSach.setAdapter(adapter);
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
    protected  void OpenDialog(final Context context, final int type){
        //custom dialog
        dialog= new Dialog(context);
        dialog.setContentView(R.layout.sach_dialog);
        edmasach=dialog.findViewById(R.id.edmaSach);
        edtensach=dialog.findViewById(R.id.edTenSach);
        edgiathue=dialog.findViewById(R.id.edGiasach);
        spinner=dialog.findViewById(R.id.spLoaiSach);
        btnSaveS=dialog.findViewById(R.id.btnSaveS);
        btncancelS=dialog.findViewById(R.id.btncancelS);
        edmasach.setEnabled(false);
        loaiSachDAO= new LoaiSachDAO(context);
        listLoaisach=(ArrayList<LoaiSach>)loaiSachDAO.getALL();

        spinnerAdapter= new LoaiSachSpinnerAdapter(context,listLoaisach);
        spinner.setAdapter(spinnerAdapter);
        //lay ma loai sach
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maLoaiSach=listLoaisach.get(position).getMaLoai();
                Toast.makeText(context,"chon"+listLoaisach.get(position).getMaLoai(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if(type!=0){
            edmasach.setText(String.valueOf(item.getMaSach()));
            edtensach.setText(item.getTenSach());
            edgiathue.setText(String.valueOf(item.getGiaSach()));
        }
        for(int i=0;i<listLoaisach.size();i++){
            if(item.getMaLoai()==(listLoaisach.get(i).getMaLoai())){
                postion = i;
            }
            Log.i("demo","possach"+postion);
            spinner.setSelection(postion);
        }
        btnSaveS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item= new Sach();
                item.setTenSach(edtensach.getText().toString());
                item.setGiaSach(Integer.parseInt(edgiathue.getText().toString()));
                item.setMaLoai(maLoaiSach);
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
                        item.setMaSach(Integer.parseInt(edmasach.getText().toString()));
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
        btncancelS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }
    public  int validate(){
        int chek =1;
        if(edtensach.getText().length()==0||edgiathue.getText().length()==0){
            Toast.makeText(getActivity(),"bạn Phải nhap đủ thong tin",Toast.LENGTH_SHORT).show();
            chek=1;
        }
        return  chek=1;
    }
}