package com.example.ps24524_trantuananh_asm1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static String dbName = "ASM";
    public static int dbVersion = 1;

    public DBHelper(@Nullable Context context) {
        super(context, dbName, null, dbVersion);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tao bang phan loai
        String sqlPhanLoai = "create table PhanLoai" +
                "("+
                "MaLoai integer primary key autoincrement, " +
                "TenLoai text, " +
                "TrangThai text" +
                ")";
        db.execSQL(sqlPhanLoai);

        // Tao bang giao dich
        String sqlGiaoDich = "create table GiaoDich" +
                "("+
                "MaGd integer primary key autoincrement," +
                "TenGiaoDich text," +
                "SoTien double," +
                "Ngay Date," +
                "GhiChu text," +
                "Loai integer REFERENCES PhanLoai(MaLoai)" +
                ")";
        db.execSQL(sqlGiaoDich);

        sqlPhanLoai = "INSERT INTO PhanLoai VALUES(1, 'lương', 'thu')";
        db.execSQL(sqlPhanLoai);
        sqlPhanLoai = "INSERT INTO PhanLoai VALUES(2, 'thu nợ', 'thu')";
        db.execSQL(sqlPhanLoai);
        sqlPhanLoai = "INSERT INTO PhanLoai VALUES(3, 'trúng số', 'thu')";
        db.execSQL(sqlPhanLoai);
        sqlPhanLoai = "INSERT INTO PhanLoai VALUES(4, 'thuê nhà', 'chi')";
        db.execSQL(sqlPhanLoai);
        sqlPhanLoai = "INSERT INTO PhanLoai VALUES(5, 'sinh hoạt', 'chi')";
        db.execSQL(sqlPhanLoai);
        sqlPhanLoai = "INSERT INTO PhanLoai VALUES(6, 'đổ xăng', 'chi')";
        db.execSQL(sqlPhanLoai);
        sqlPhanLoai = "INSERT INTO PhanLoai VALUES(7, 'khác', 'thu')";
        db.execSQL(sqlPhanLoai);
        sqlPhanLoai = "INSERT INTO PhanLoai VALUES(8, 'khác', 'chi')";
        db.execSQL(sqlPhanLoai);


        sqlGiaoDich = "INSERT INTO GiaoDich VALUES(1, 'Lương tháng 5', '10000', '1/5/2022','', 1)";
        db.execSQL(sqlGiaoDich);
        sqlGiaoDich = "INSERT INTO GiaoDich VALUES(2, 'Thuê nhà', '800', '1/5/2022','', 4)";
        db.execSQL(sqlGiaoDich);
        sqlGiaoDich = "INSERT INTO GiaoDich VALUES(3, 'Đổ xăng', '50', '3/5/2022','', 6)";
        db.execSQL(sqlGiaoDich);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql1 = "drop table if exists GiaoDich";
        db.execSQL(sql1);
        String sql2 = "drop table if exists PhanLoai";
        db.execSQL(sql2);
        onCreate(db);
    }
}