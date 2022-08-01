package com.example.libmana_asi.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.libmana_asi.Database.Datahelper;
import com.example.libmana_asi.Model.PhieuMuon;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PhieuMuonDAO {
    private SQLiteDatabase db;
    private Context context;
    private SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
    public PhieuMuonDAO(Context context) {
        this.context=context;
        Datahelper dbhelper = new Datahelper(context);
        db = dbhelper.getWritableDatabase();

    }
    //them thanh vien vao bang thanh vien
    public long insert(PhieuMuon obj){
        ContentValues values= new ContentValues();
        values.put("maTT",obj.getMaTT());
        values.put("maTV",obj.getMaTV());
        values.put("maSach",obj.getMaSach());
        values.put("tienThue",obj.getTienThue());
        values.put("ngay", sdf.format(obj.getNgay()));
        values.put("traSach",obj.getTraSach());
        return db.insert("PhieuMuon",null,values);

    }
    //updata thanh vien
    public int updata(PhieuMuon obj){
        ContentValues values= new ContentValues();
        values.put("maTT",obj.getMaTT());
        values.put("maTV",obj.getMaTV());
        values.put("maSach",obj.getMaSach());
        values.put("tienThue",obj.getTienThue());
        values.put("ngay", sdf.format(obj.getNgay()));
        values.put("traSach",obj.getTraSach());
        return db.update("PhieuMuon",values,"maPM=?",new String[]{String.valueOf(obj.getMaPM())});
    }
    //delete thanh vien
    public  int delete(String id){
        return db.delete("PhieuMuon","maPM=?",new String[]{id});
    }
    //get tat ca trong bang thnh vien
    public List<PhieuMuon> getALL(){
        String sql="SELECT*FROM PhieuMuon";
        return getData(sql);
    }
    //get theo id
    public PhieuMuon getID(String id){
        String sql="SELECT * FROM PhieuMuon WHERE maPM=?";
        List<PhieuMuon>list= getData(sql,id);
        return list.get(0);
    }

    private  List<PhieuMuon> getData(String sql,String...selecttionArgs)  {
        List<PhieuMuon> list= new ArrayList<PhieuMuon>();
        Cursor c= db.rawQuery(sql,selecttionArgs);
        while (c.moveToNext()){
            PhieuMuon obj= new PhieuMuon();
            obj.setMaPM( Integer.parseInt(c.getString(c.getColumnIndex("maPM"))));
            obj.setMaTV( Integer.parseInt(c.getString(c.getColumnIndex("MaTv"))));
            obj.setMaSach(Integer.parseInt(c.getString(c.getColumnIndex("MaSach"))));
            obj.setMaTT(c.getString(c.getColumnIndex("MaTT")));
            obj.setTienThue( Integer.parseInt(c.getString(c.getColumnIndex("tienThue"))));
            try{
                obj.setNgay(sdf.parse(c.getString(c.getColumnIndex("ngay"))));
            }catch (ParseException e){

            }

            obj.setTraSach( Integer.parseInt(c.getString(c.getColumnIndex("traSach"))));
            list.add(obj);
        }
        return list;
    }
}
