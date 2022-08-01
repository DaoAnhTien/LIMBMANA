package com.example.libmana_asi.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.libmana_asi.Database.Datahelper;
import com.example.libmana_asi.Model.LoaiSach;

import java.util.ArrayList;
import java.util.List;

public class LoaiSachDAO {
    private SQLiteDatabase db;
    public LoaiSachDAO(Context context) {
        Datahelper dbhelper = new Datahelper(context);
        db = dbhelper.getWritableDatabase();

    }
    //them thanh vien vao bang thanh vien
    public long insert(LoaiSach obj){
        ContentValues values= new ContentValues();
        values.put("tenLoai",obj.getTenLoai());
        return db.insert("LoaiSach",null,values);

    }
    //updata thanh vien
    public int updata(LoaiSach obj){
        ContentValues values= new ContentValues();
        values.put("tenLoai",obj.getTenLoai());
        return db.update("LoaiSach",values,"maLoai=?",new String[]{String.valueOf(obj.getMaLoai())});
    }
    //delete thanh vien
    public  int delete(String id){
        return db.delete("LoaiSach","maLoai=?",new String[]{id});
    }
    //get tat ca trong bang thnh vien
    public List<LoaiSach> getALL(){
        String sql="SELECT*FROM LoaiSach";
        return getData(sql);
    }
    //get theo id
    public LoaiSach getID(String id){
        String sql="SELECT * FROM LoaiSach WHERE maLoai=?";
        List<LoaiSach>list= getData(sql,id);
        return list.get(0);
    }

    private  List<LoaiSach> getData(String sql,String...selecttionArgs){
        List<LoaiSach> list= new ArrayList<LoaiSach>();
        Cursor c= db.rawQuery(sql,selecttionArgs);
        while (c.moveToNext()){
            LoaiSach obj= new LoaiSach();
            obj.setMaLoai( Integer.parseInt(c.getString(c.getColumnIndex("maLoai"))));
            obj.setTenLoai(c.getString(c.getColumnIndex("tenLoai")));
            list.add(obj);
        }
        return list;
    }

}
