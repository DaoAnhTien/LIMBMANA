package com.example.libmana_asi.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.libmana_asi.Database.Datahelper;
import com.example.libmana_asi.Model.ThanhVien;

import java.util.ArrayList;
import java.util.List;

public class ThanhVienDAO {

    private SQLiteDatabase db;

    public ThanhVienDAO(Context context) {
        Datahelper dbhelper = new Datahelper(context);
        db = dbhelper.getWritableDatabase();

    }
    //them thanh vien vao bang thanh vien
    public long insert(ThanhVien obj){
        ContentValues values= new ContentValues();
        values.put("hoTen",obj.getHoTen());
        values.put("namSinh",obj.getNamSinh());
        return db.insert("ThanhVien",null,values);

    }
    //updata thanh vien
    public int updata(ThanhVien obj){
        ContentValues values= new ContentValues();
        values.put("hoTen",obj.getHoTen());
        values.put("namSinh",obj.getNamSinh());
        return db.update("ThanhVien",values,"maTV=?",new String[]{String.valueOf(obj.getMaTv())});
    }
    //delete thanh vien
    public  int delete(String id){
        return db.delete("ThanhVien","maTv=?",new String[]{id});
    }
    //get tat ca trong bang thnh vien
    public List<ThanhVien> getALL(){
        String sql="SELECT * FROM ThanhVien";
        return getData(sql);
    }
    //get theo id
    public ThanhVien getID(String id){
        String sql="SELECT * FROM ThanhVien WHERE maTV=?";
        List<ThanhVien> list= getData(sql,id);
        return list.get(0);
    }

    private  List<ThanhVien> getData(String sql,String...selecttionArgs){
        List<ThanhVien> list= new ArrayList<>();
        Cursor c= db.rawQuery(sql,selecttionArgs);
        while (c.moveToNext()){
            ThanhVien obj= new ThanhVien();
            obj.setMaTv(Integer.parseInt(c.getString(c.getColumnIndex("maTV"))));
            obj.setHoTen(c.getString(c.getColumnIndex("hoTen")));
            obj.setNamSinh(c.getString(c.getColumnIndex("namSinh")));
            list.add(obj);
        }
        return list;
    }

}

