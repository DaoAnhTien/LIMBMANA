package com.example.libmana_asi.Framment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.libmana_asi.Dao.ThuThuDAO;
import com.example.libmana_asi.Model.ThuThu;
import com.example.libmana_asi.R;
import com.google.android.material.textfield.TextInputEditText;

public class ChangePassFragment extends Fragment {
    TextInputEditText edPassdOld,edPass,edRepass;
    Button btnSave,btnCancel;
    ThuThuDAO dao;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_change_pass, container, false);
        dao = new ThuThuDAO(getActivity());
        edPassdOld=v.findViewById(R.id.edPassOld);
        edPass=v.findViewById(R.id.edPassChange);
        edRepass=v.findViewById(R.id.edrePasschange);
        btnSave=v.findViewById(R.id.btnSaveUserChange);
        btnCancel=v.findViewById(R.id.btnCancleUserchange);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edPassdOld.setText("");
                edPass.setText("");
                edRepass.setText("");
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref= getActivity().getSharedPreferences("USER_FILE",Context.MODE_PRIVATE);
                String user=pref.getString("USERNAME","");
                if(validate()>0){
                    ThuThu thuThu= dao.getID(user);
                    thuThu.setMatKhau(edPass.getText().toString());
                    dao.updataPass(thuThu);
                            if(dao.updataPass(thuThu)>0){
                                Toast.makeText(getActivity(), "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                                edPassdOld.setText("");
                                edPass.setText("");
                                edRepass.setText("");
                            }else{
                                Toast.makeText(getActivity(), "Thay đổi mật khẩu không thành công thành công", Toast.LENGTH_SHORT).show();
                            }
                }
            }
        });
        return v;
    }
    public int validate(){
        int chek=1;
        if(edPassdOld.getText().length()==0||edPass.getText().length()==0||edRepass.getText().length()==0){
            Toast.makeText(getActivity(), "Ban phai nhap du thong tin", Toast.LENGTH_SHORT).show();
            chek=-1;
        }else{
                //doc user pass trong Shared
            SharedPreferences pref= getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
            String passold= pref.getString("PASSWORD","");
            String pass=edPass.getText().toString();
            String repass=edRepass.getText().toString();
            if(!passold.equals(edPassdOld.getText().toString())){
                Toast.makeText(getActivity(), "mat khau cu sai", Toast.LENGTH_SHORT).show();
                chek=-1;
            }
            if (!pass.equals(repass)){
                Toast.makeText(getActivity(), "mat khau khong trung khop", Toast.LENGTH_SHORT).show();
                chek=-1;
            }
        }
        return chek;
    }
}