package com.example.libmana_asi.Framment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.libmana_asi.Dao.PhieuMuonDAO;
import com.example.libmana_asi.Dao.ThongKeDAO;
import com.example.libmana_asi.Model.PhieuMuon;
import com.example.libmana_asi.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class DoanhthuFragment extends Fragment {
    Button btntungay,btndenngay,btnDoanhthu;
    TextView tvdoanhthu;
    EditText edtungay,eddengay;
    SimpleDateFormat sdf= new SimpleDateFormat("yyyy/MM/dd");
    int nam,thang,ngay;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_doanhthu, container, false);
        btndenngay=v.findViewById(R.id.btndenngay);
        btntungay=v.findViewById(R.id.btntungay);
        btnDoanhthu=v.findViewById(R.id.btnDoanhthu);
        edtungay=v.findViewById(R.id.edtungay);
        eddengay=v.findViewById(R.id.eddenngay);
        tvdoanhthu=v.findViewById(R.id.txDoanhthu);
        btntungay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c=  Calendar.getInstance();
                nam=c.get(Calendar.YEAR);
                thang=c.get(Calendar.MONTH);
                ngay=c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d= new DatePickerDialog(getActivity(),0
                ,mdatatungay,nam,thang,ngay);
                d.show();
            }
        });
        btndenngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c=  Calendar.getInstance();
                nam=c.get(Calendar.YEAR);
                thang=c.get(Calendar.MONTH);
                ngay=c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d= new DatePickerDialog(getActivity(),0
                        ,mdenngay,nam,thang,ngay);
                d.show();
            }
        });
        btnDoanhthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tungay= edtungay.getText().toString();
                String denngay=eddengay.getText().toString();
                ThongKeDAO thongKeDAO= new ThongKeDAO(getActivity());
                tvdoanhthu.setText("Doanh Thu :"+thongKeDAO.getDoanhThu(tungay,denngay)+"VND");
            }
        });
        return v;
    }
    DatePickerDialog.OnDateSetListener mdatatungay= new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                nam=year;
                thang=month;
                ngay=dayOfMonth;
            GregorianCalendar c= new GregorianCalendar(nam, thang,ngay);
            edtungay.setText(sdf.format(c.getTime()));
        }
    };
    DatePickerDialog.OnDateSetListener mdenngay= new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            nam=year;
            thang=month;
            ngay=dayOfMonth;
            GregorianCalendar c= new GregorianCalendar(nam, thang,ngay);
            eddengay.setText(sdf.format(c.getTime()));
        }
    };

}