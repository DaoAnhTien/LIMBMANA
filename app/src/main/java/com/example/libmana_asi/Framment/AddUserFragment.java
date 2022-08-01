package com.example.libmana_asi.Framment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.libmana_asi.Dao.ThuThuDAO;
import com.example.libmana_asi.Model.ThuThu;
import com.example.libmana_asi.R;
import com.google.android.material.textfield.TextInputEditText;

public class AddUserFragment extends Fragment {
    Button btnsave, btncancel;
    TextInputEditText edpass,edrepass;
    EditText eduser,edhoten;
    ThuThuDAO dao;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_add_user, container, false);
        btncancel=view.findViewById(R.id.btnCancleadduser);
        btnsave=view.findViewById(R.id.btnsaveadduser);
        eduser=view.findViewById(R.id.edusernameadd);
        edhoten=view.findViewById(R.id.edhoTenadd);
        edpass=view.findViewById(R.id.edpassadd);
        edrepass=view.findViewById(R.id.edrepasadd);

        //
        dao= new ThuThuDAO(getActivity());
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eduser.setText("");
                edhoten.setText("");
                edpass.setText("");
                edrepass.setText("");
            }
        });
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThuThu thuthu= new ThuThu();
                thuthu.setMaTT(eduser.getText().toString());
                thuthu.setHoTen(edhoten.getText().toString());
                thuthu.setMatKhau(edpass.getText().toString());
                if(validate()>0){
                    if(dao.insert(thuthu)>0){
                        Toast.makeText(getActivity(),"lưu thành công",Toast.LENGTH_SHORT).show();
                        eduser.setText("");
                        edhoten.setText("");
                        edpass.setText("");
                        edrepass.setText("");
                    }else{
                        Toast.makeText(getActivity(),"lưu thất bại",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return view;
    }
    public int validate(){
        int chek=1;
        if(eduser.getText().length()==0||edhoten.getText().length()==0||edpass.getText().length()==0||edrepass.getText().length()==0){
            Toast.makeText(getActivity(), "Ban phai nhap du thong tin", Toast.LENGTH_SHORT).show();
            chek=-1;
        }else{
            String pass=edpass.getText().toString();
            String repass=edrepass.getText().toString();
            if(!pass.equals(repass)){
                Toast.makeText(getActivity(), "mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                chek=-1;
            }
        }
        return chek;
    }
}