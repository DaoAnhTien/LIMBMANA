package com.example.libmana_asi.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Datahelper extends SQLiteOpenHelper {
    static  final  String dbNAME="PNLIB";
    static  final  int  dvVersion=1;
    //tao bang thuthu
    static final String creatableThuThu=
            "create table ThuThu("+
                            "maTT TEXT PRIMARY KEY,"+
                            "hoTen TEXT NOT NULL,"+
                            "matKhau TEXT NOT NULL)";
    //tao bang thanh vien
    static final String creatableThanhVien=
            "create table ThanhVien("+
                    "maTV INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "hoTen TEXT NOT NULL,"+
                    "namSinh TEXT NOT NULL)";
    //tao bang loai sach
    static final String creatableLoaiSach=
            "create table LoaiSach("+
                    "maLoai INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "tenLoai TEXT NOT NULL)";
    //tao bang sach
    static final String creatableSach=
            "create table Sach("+
                    "maSach INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "tenSach TEXT NOT NULL,"+
                    "giaThue INTEGER NOT NULL,"+
                    "maLoai INTEGER REFERENCES LoaiSach(maLoai))";
    static final String creatablePhieuMuon=
            "create table PhieuMuon("+
                    "maPM INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "MaTT TEXT REFERENCES ThuThu(maTT),"+
                    "MaTv INTEGER REFERENCES ThanhVien(maTV),"+
                    "MaSach INTEGER REFERENCES Sach(maSach),"+
                    "tienThue INTEGER NOT NULL,"+
                    "ngay DATA NOT NULL,"+
                    "traSach INTEGER NOT NULL)";
    public Datahelper(@Nullable Context context) {
        super(context, dbNAME, null, dvVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(creatableThuThu);
        db.execSQL(creatableThanhVien);
        db.execSQL(creatableLoaiSach);
        db.execSQL(creatableSach);
        db.execSQL(creatablePhieuMuon);
        //insert data
        db.execSQL(Data_sqlite_mau.INSERT_THUTHU);
        db.execSQL(Data_sqlite_mau.INSERT_THANHVIEN);
        db.execSQL(Data_sqlite_mau.INSERT_LOAISACH);
        db.execSQL(Data_sqlite_mau.INSERT_PHIEUMUON);
        db.execSQL(Data_sqlite_mau.INSERT_SACH);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String droptableThuThu="drop table if exists ThuThu";
        db.execSQL(droptableThuThu);
        String droptableThanhVien="drop table if exists ThanhVien";
        db.execSQL(droptableThanhVien);
        String droptableLoaiSach="drop table if exists LoaiSach";
        db.execSQL(droptableLoaiSach);
        String droptableSach="drop table if exists Sach";
        db.execSQL(droptableSach);
        String droptablePhieuMuon="drop table if exists PhieuMuon";
        db.execSQL(droptablePhieuMuon);
        onCreate(db);

    }
}
