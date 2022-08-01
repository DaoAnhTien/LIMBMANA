package com.example.libmana_asi.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.libmana_asi.Database.Datahelper;
import com.example.libmana_asi.Model.Sach;
import com.example.libmana_asi.Model.Top;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ThongKeDAO {
    private SQLiteDatabase db;
    private Context context;
    SimpleDateFormat sdf= new SimpleDateFormat("yyyy/MM/dd");
    public ThongKeDAO(Context context){
        this.context=context;
        Datahelper datahelper=new Datahelper(context);
        db=datahelper.getWritableDatabase();
    }
    //thong ke top 10
    public List<Top> getTOP(){
        String sql="SELECT maSach, count(maSach) as soLuong FROM PhieuMuon GROUP BY maSach ORDER BY soLuong DESC LIMIT 10";
        List<Top>list= new ArrayList<>();
        SachDAO sachDAO= new SachDAO(context);
        Cursor c=db.rawQuery(sql,null);
        while (c.moveToNext()){
            Top top= new Top();
            Sach sach=sachDAO.getID(c.getString(c.getColumnIndex("MaSach")));
            top.setTenSach(sach.getTenSach());
            top.setSoLuong(Integer.parseInt(c.getString(c.getColumnIndex("soLuong"))));
            list.add(top);
        }
        return list;
    }
    public int getDoanhThu(String tungay, String denngay){
        String sqlDoanhThu="SELECT SUM(tienThue)as doanhThu FROM PhieuMuon WHERE ngay BETWEEN ? AND ? ";
        List<Integer> list= new ArrayList<Integer>();
        Cursor c= db.rawQuery(sqlDoanhThu, new String[]{tungay,denngay});
        while (c.moveToNext()){
            try {
                list.add(Integer.parseInt(c.getString(c.getColumnIndex("doanhThu"))));
            }   catch (Exception e){
                list.add(0);
            }
        }
        return list.get(0);
    }
}
