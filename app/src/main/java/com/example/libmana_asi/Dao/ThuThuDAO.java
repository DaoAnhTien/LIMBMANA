package com.example.libmana_asi.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.libmana_asi.Database.Datahelper;
import com.example.libmana_asi.Model.ThuThu;

import java.util.ArrayList;
import java.util.List;

public class ThuThuDAO {
    private SQLiteDatabase db;
    public ThuThuDAO(Context context) {
        Datahelper dbhelper = new Datahelper(context);
        db = dbhelper.getWritableDatabase();
    }
    public long insert(ThuThu obj){
        ContentValues values= new ContentValues();
        values.put("maTT",obj.getMaTT());
        values.put("hoTen",obj.getHoTen());
        values.put("matKhau",obj.getMatKhau());
        return  db.insert("ThuThu" ,null ,values);

    }
    //updata Thuthu
    public int updataPass(ThuThu obj){
        ContentValues values= new ContentValues();
        values.put("hoTen",obj.getHoTen());
        values.put("matKhau",obj.getMatKhau());
        return db.update("ThuThu",values,"maTT=?", new String[]{obj.getMaTT()});
    }
    //delete thanh vien
    public  int delete(String id){
        return db.delete("ThuThu","maTT=?",new String[]{id});
    }

    //get tat ca trong bang thnh vien
    public List<ThuThu> getALL(){
        String sql="SELECT*FROM ThuThu";
        return getData(sql);
    }
    //get theo id
     public ThuThu getID(String id){
        String sql="SELECT * FROM ThuThu WHERE maTT=?";
        List<ThuThu>list= getData(sql,id);
        return list.get(0);
    }
     public  List<ThuThu> getData(String sql ,String...selecttionArgs){
        List<ThuThu> list= new ArrayList<ThuThu>();
        Cursor c= db.rawQuery(sql,selecttionArgs);
        while (c.moveToNext()){
            ThuThu obj= new ThuThu();
            obj.setMaTT( c.getString(c.getColumnIndex("maTT")));
            obj.setHoTen(c.getString(c.getColumnIndex("hoTen")));
            obj.setMatKhau(c.getString(c.getColumnIndex("matKhau")));//thieu chữ t
            list.add(obj);
        }
        return list;
    }
    //chek Login
    public int chekLogin(String id,String Password){
        String sql="SELECT * FROM ThuThu WHERE maTT=? AND matKhau=?";
        List<ThuThu> list=getData(sql,id,Password);
        if(list.size()==0)
            return  -1 ;
        return 1;
    }
}
