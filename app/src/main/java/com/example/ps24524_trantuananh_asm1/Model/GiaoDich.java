package com.example.ps24524_trantuananh_asm1.Model;

public class GiaoDich {
    private int maGd;
    private double soTien;
    private String tenGiaoDich;
    private String ghiChu;
    private String date;
    private int loai;

    public GiaoDich(int maGd,String tenGiaoDich, double soTien, String ghiChu, String date, int loai) {
        this.maGd = maGd;
        this.soTien = soTien;
        this.tenGiaoDich = tenGiaoDich;
        this.ghiChu = ghiChu;
        this.date = date;
        this.loai = loai;
    }



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getMaGd() {
        return maGd;
    }

    public void setMaGd(int maGd) {
        this.maGd = maGd;
    }

    public double getSoTien() {
        return soTien;
    }

    public void setSoTien(double soTien) {
        this.soTien = soTien;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public int getLoai() {
        return loai;
    }

    public void setLoai(int loai) {
        this.loai = loai;
    }
}
