package com.example.ps24524_trantuananh_asm1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ps24524_trantuananh_asm1.Model.GiaoDich;
import com.example.ps24524_trantuananh_asm1.Model.PhanLoai;

import java.util.ArrayList;

public class GiaoDichDAO {
    SQLiteDatabase db;
    Context context;

    public void GiaoDichDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
        this.context = context;
    }

    public void taoGiaoDich (GiaoDich giaoDich) {
        ContentValues values = new ContentValues();
        values.put("maGd", giaoDich.getMaGd());
        values.put("soTien", giaoDich.getSoTien());
        values.put("ghiChu", giaoDich.getGhiChu());
        values.put("date", giaoDich.getDate());
        values.put("loai", giaoDich.getLoai());

        db.insert("GiaoDich", null, values);
    }

    public ArrayList<GiaoDich> ListGiaoDich() {
        ArrayList<GiaoDich> dsGiaoDich = new ArrayList<>();

        String sql = "select * from GiaoDich";
        Cursor c = db.rawQuery(sql, null);

        if (c.moveToFirst()) {
            do {
                int maGiaoDich = c.getInt(0);
                String tenGiaoDich = c.getString(1);
                double soTien = c.getDouble(2);
                String ngay = c.getString(3);
                String ghiChu = c.getString(4);
                int loai = c.getInt(5);
                GiaoDich giaoDich = new GiaoDich(maGiaoDich, tenGiaoDich, soTien, ngay, ghiChu, loai);
                dsGiaoDich.add(giaoDich);
            } while (c.moveToNext());
        }

        return dsGiaoDich;
    }



}
