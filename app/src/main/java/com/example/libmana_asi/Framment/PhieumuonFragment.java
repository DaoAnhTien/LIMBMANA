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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.libmana_asi.Adapter.PhieumuonAdapter;
import com.example.libmana_asi.Adapter.SachspinerAdapter;
import com.example.libmana_asi.Adapter.ThanhvienspinerAdapter;
import com.example.libmana_asi.Dao.PhieuMuonDAO;
import com.example.libmana_asi.Dao.SachDAO;
import com.example.libmana_asi.Dao.ThanhVienDAO;
import com.example.libmana_asi.Model.PhieuMuon;
import com.example.libmana_asi.Model.Sach;
import com.example.libmana_asi.Model.ThanhVien;
import com.example.libmana_asi.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PhieumuonFragment extends Fragment {
    ListView lvPhieumuon;
    ArrayList<PhieuMuon> list;
    static PhieuMuonDAO dao;
    PhieumuonAdapter adapter;
    PhieuMuon item;
    Dialog dialogl;
    FloatingActionButton fab;
    EditText edMaPM;
    Spinner spTV, spSach;
    TextView tvngay, tvtienthue;
    CheckBox chktrasach;
    Button btncancel, btnsave;
    SimpleDateFormat sdf =  new SimpleDateFormat();

    ThanhvienspinerAdapter thanhvienspinerAdapter;
    ArrayList<ThanhVien> listthanhVien;
    ThanhVienDAO thanhVienDAO;

    SachspinerAdapter sachspinerAdapter;
    ArrayList<Sach> listSach;
    SachDAO sachDAO;

    int mathanhvien,masach,tienthue;
    int postionTV,postionSach;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_phieumuon, container, false);
        lvPhieumuon=v.findViewById(R.id.lvPhieumuon);
        dao= new PhieuMuonDAO(getActivity());
        fab=v.findViewById(R.id.fabPM);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialog(getActivity(),0);
            }
        });
        lvPhieumuon.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item= list.get(position);
                opendialog(getActivity(),1);
                return false;
            }
        });

        capnhatLV();
        return v;
    }
    void capnhatLV(){
        list=(ArrayList<PhieuMuon>) dao.getALL();
        adapter= new PhieumuonAdapter(getActivity(),this,list);
        lvPhieumuon.setAdapter(adapter);
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
    protected void opendialog(final Context context,int type){
        dialogl= new Dialog(context);
        dialogl.setContentView(R.layout.phieu_muon_dialog);
        edMaPM=dialogl.findViewById(R.id.edmaPM);
        spSach=dialogl.findViewById(R.id.spMasach);
        spTV=dialogl.findViewById(R.id.spmaTV);
        tvngay=dialogl.findViewById(R.id.tvNgayPM);
        tvtienthue=dialogl.findViewById(R.id.tvtienThue);
        chktrasach=dialogl.findViewById(R.id.chktraSach);
        btncancel=dialogl.findViewById(R.id.btncancelPM);
        btnsave=dialogl.findViewById(R.id.btnSavePM);
        edMaPM.setEnabled(false);
        //set ngay thue,mac dinh  ngay hien hanh
        tvngay.setText("ngày Thuê :"+sdf.format(new Date()) );

        thanhVienDAO= new ThanhVienDAO(context);
        listthanhVien=  new ArrayList<ThanhVien>();
        listthanhVien=(ArrayList<ThanhVien>)thanhVienDAO.getALL();
        thanhvienspinerAdapter= new ThanhvienspinerAdapter(context,listthanhVien);
        spTV.setAdapter(thanhvienspinerAdapter);
        spTV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mathanhvien=listthanhVien.get(position).getMaTv();
                //Toast.makeText(context,"chọn"+listthanhVien.get(position).getHoTen(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sachDAO= new SachDAO(context);
        listSach=  new ArrayList<Sach>();
        listSach=(ArrayList<Sach>)sachDAO.getALL();
        sachspinerAdapter= new SachspinerAdapter(context,listSach);
        spSach.setAdapter(sachspinerAdapter);
        spSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                masach=listSach.get(position).getMaSach();
                tienthue =listSach.get(position).getGiaSach();
                tvtienthue.setText("Tiền Thuê:"+tienthue);
                //Toast.makeText(context,"chọn"+listSach.get(position).getTenSach(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogl.dismiss();
            }
        });
        if(type!=0) {
            edMaPM.setText(String.valueOf(item.getMaPM()));
            for (int i = 0; i < listthanhVien.size(); i++)
                if (item.getMaTV() == (listthanhVien.get(i).getMaTv())) {
                    postionTV = i;
                }
                spTV.setSelection(postionTV);
            for (int i = 0; i < listSach.size(); i++)
                if (item.getMaSach() == (listSach.get(i).getMaSach())) {
                    postionSach = i;
                }
            spSach.setSelection(postionSach);

            tvngay.setText("Ngày Thuê"+sdf.format(item.getNgay()));
            tvtienthue.setText("Tiền Thuê"+item.getTienThue());
            if(item.getTraSach()==0){
                    chktrasach.setChecked(false);
            }else{

                    chktrasach.setChecked(true);
            }

        }
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item= new PhieuMuon();
                item.setMaSach(masach);
                item.setMaTV(mathanhvien);
                item.setNgay(new Date());
                item.setTienThue(tienthue);
                if(chktrasach.isChecked()){
                    item.setTraSach(1);
                }else{
                    item.setTraSach(0);
                }
                if(type==0){
                    if(dao.insert(item)>0){
                        Toast.makeText(context,"Thêm thành công",Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(context,"Thêm không thành  công",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    item.setMaPM(Integer.parseInt(edMaPM.getText().toString()));
                    if(dao.updata(item)>0){
                        Toast.makeText(context,"sửa  thành công",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context,"Sửa không thành công",Toast.LENGTH_SHORT).show();
                    }
                }
                dialogl.dismiss();
                capnhatLV();
            }

        });

        dialogl.show();
    }


}