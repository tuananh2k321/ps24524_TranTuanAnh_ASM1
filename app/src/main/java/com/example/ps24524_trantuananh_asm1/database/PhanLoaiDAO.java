package com.example.ps24524_trantuananh_asm1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ps24524_trantuananh_asm1.Model.PhanLoai;

import java.util.ArrayList;

public class PhanLoaiDAO {
    SQLiteDatabase db;
    Context context;

    public PhanLoaiDAO (Context context) {
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
        this.context = context;
    }

    public void delete(PhanLoai phanLoai) {
        db.delete("PhanLoai", "MaLoai=?", new String[]{phanLoai.getMaLoai()+""});
    }


    public void updateTenLoai(PhanLoai phanLoai) {

        ContentValues values = new ContentValues();
        values.put("TenLoai", phanLoai.getTenLoai());
        values.put("TrangThai", phanLoai.getTrangThai());
        db.update("PhanLoai", values, "MaLoai=?", new String[]{phanLoai.getMaLoai()+""});
    }

    public void taoPhanLoai (PhanLoai phanLoai) {
        ContentValues values = new ContentValues();
        values.put("tenLoai", phanLoai.getTenLoai());
        values.put("trangThai", phanLoai.getTrangThai());
        db.insert("PhanLoai", null, values);
    }

    public ArrayList<PhanLoai> tenLoaiThu() {
        ArrayList<PhanLoai> dsTenLoaiThu = new ArrayList<>();

        String sql = "select * from PhanLoai where trangThai like 'thu'";
        Cursor c = db.rawQuery(sql, null);

        if (c.moveToFirst()) {
            do {
                int tenLoai = c.getInt(0);
                String tenLoaiThu = c.getString(1);
                String trangThai = c.getString(2);
                PhanLoai phanLoai = new PhanLoai(tenLoai, tenLoaiThu, trangThai);
                dsTenLoaiThu.add(phanLoai);
            } while (c.moveToNext());
        }

        return dsTenLoaiThu;
    }

    public ArrayList<PhanLoai> tenLoaiChi() {
        ArrayList<PhanLoai> dsTenLoaiThu = new ArrayList<>();

        String sql = "select * from PhanLoai where trangThai like 'chi'";
        Cursor c = db.rawQuery(sql, null);

        if (c.moveToFirst()) {
            do {
                int tenLoai = c.getInt(0);
                String tenLoaiThu = c.getString(1);
                String trangThai = c.getString(2);
                PhanLoai phanLoai = new PhanLoai(tenLoai, tenLoaiThu, trangThai);
                dsTenLoaiThu.add(phanLoai);
            } while (c.moveToNext());
        }

        return dsTenLoaiThu;
    }
}
