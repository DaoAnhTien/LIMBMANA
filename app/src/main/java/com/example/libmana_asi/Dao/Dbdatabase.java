package com.example.libmana_asi.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.libmana_asi.Database.Datahelper;
import com.example.libmana_asi.Model.ThanhVien;

import java.util.ArrayList;
import java.util.List;

public class Dbdatabase {
    private  ThanhVienDAO dao;
    public static  String TAG="///========";
    private SQLiteDatabase db;
    public Dbdatabase(Context context) {
        Datahelper datahelper= new Datahelper(context);
        db=datahelper.getWritableDatabase();
        dao = new ThanhVienDAO(context);
    }
}
