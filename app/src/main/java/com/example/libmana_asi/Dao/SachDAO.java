package com.example.libmana_asi.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.libmana_asi.Database.Datahelper;
import com.example.libmana_asi.Model.Sach;

import java.util.ArrayList;
import java.util.List;

public class SachDAO {
    private SQLiteDatabase db;
    public SachDAO(Context context) {
        Datahelper dbhelper = new Datahelper(context);
        db = dbhelper.getWritableDatabase();

    }
    //them thanh vien vao bang thanh vien
    public long insert(Sach obj){
        ContentValues values= new ContentValues();
        values.put("tenSach",obj.getTenSach());
        values.put("giaThue",obj.getGiaSach());
        values.put("maLoai",obj.getMaLoai());

        return db.insert("Sach",null,values);

    }
    //updata thanh vien
    public int updata(Sach obj){
        ContentValues values= new ContentValues();
        values.put("tenSach",obj.getTenSach());
        values.put("giaThue",obj.getGiaSach());
        values.put("maLoai",obj.getMaLoai());
        return db.update("Sach",values,"maSach=?",new String[]{String.valueOf(obj.getMaSach())});
    }
    //delete thanh vien
    public  int delete(String id){
        return db.delete("Sach","maSach=?",new String[]{id});
    }
    //get tat ca trong bang thnh vien
    public List<Sach> getALL(){
        String sql="SELECT*FROM Sach";
        return getData(sql);
    }
    //get theo id
    public Sach getID(String id){
        String sql="SELECT * FROM Sach WHERE maSach=?";
        List<Sach>list= getData(sql,id);
        return list.get(0);
    }

    private  List<Sach> getData(String sql,String...selecttionArgs){
        List<Sach> list= new ArrayList<Sach>();
        Cursor c= db.rawQuery(sql,selecttionArgs);
        while (c.moveToNext()){
            Sach obj= new Sach();
            obj.setMaSach(Integer.parseInt(c.getString(c.getColumnIndex("maSach"))));
            obj.setTenSach(c.getString(c.getColumnIndex("tenSach")));
            obj.setGiaSach(Integer.parseInt(c.getString(c.getColumnIndex("giaThue"))));
            obj.setMaLoai(Integer.parseInt(c.getString(c.getColumnIndex("maLoai"))));
            list.add(obj);
        }
        return list;
    }
}
