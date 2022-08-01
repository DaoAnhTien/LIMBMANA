package com.example.libmana_asi.Model;

public class Sach {
    public int maSach;
    public String tenSach;
    public int giaSach ;
    public int  maLoai;

    public Sach() {
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getGiaSach() {
        return giaSach;
    }

    public void setGiaSach(int giaSach) {
        this.giaSach = giaSach;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public Sach(int maSach, String tenSach, int giaSach, int maLoai) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.giaSach = giaSach;
        this.maLoai = maLoai;
    }
}
