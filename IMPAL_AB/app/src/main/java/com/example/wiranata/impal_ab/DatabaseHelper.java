package com.example.wiranata.impal_ab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.view.Menu;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="abwar.db";
    public static final String TAB1_NAME="akun";
    public static final String TAB1_COL1="id_akun";
    public static final String TAB1_COL2="username";
    public static final String TAB1_COL3="password";
    public static final String TAB1_COL4="role";
    public static final String TAB2_NAME="menu";
    public static final String TAB2_COL1="id_menu";
    public static final String TAB2_COL2="nama_menu";
    public static final String TAB2_COL3="harga";
    public static final String TAB3_NAME="transaksi";
    public static final String TAB3_COL1="id_trans";
    public static final String TAB3_COL2="total_trans";
    public static final String TAB3_COL3="tanggal";
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TAB1_NAME + "(" + TAB1_COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TAB1_COL2 +" TEXT, " + TAB1_COL3 + " TEXT, "+ TAB1_COL4 + " TEXT)");
        db.execSQL("CREATE TABLE " + TAB2_NAME + "(" + TAB2_COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TAB2_COL2 +" TEXT, " + TAB2_COL3 + " INTEGER)");
        db.execSQL("CREATE TABLE " + TAB3_NAME + "(" + TAB3_COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TAB3_COL2 +" INTEGER, " + TAB3_COL3 + " TEXT)");
        insertakun(db, "admin","admin","pemilik");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TAB1_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TAB2_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TAB3_NAME);
        onCreate(db);
    }

    public ArrayList<AkunModel> getAllAkun() {
        ArrayList<AkunModel> akunModelArrayList = new ArrayList<AkunModel>();

        String role = "pegawai";
//        String query =;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery( "SELECT * FROM " + TAB1_NAME + " WHERE " + TAB1_COL4 + "=?", new String[]{role});
        if (cursor.moveToFirst()) {
            do {
                AkunModel akunModel = new AkunModel();
                akunModel.setId(cursor.getInt(cursor.getColumnIndex(TAB1_COL1)));
                akunModel.setUsername(cursor.getString(cursor.getColumnIndex(TAB1_COL2)));
                akunModel.setPassword(cursor.getString(cursor.getColumnIndex(TAB1_COL3)));
                akunModel.setRole(cursor.getString(cursor.getColumnIndex(TAB1_COL4)));
                akunModelArrayList.add(akunModel);
            } while (cursor.moveToNext());
        }

        return akunModelArrayList;
    }

    public boolean insertakun(SQLiteDatabase db, String username, String password, String role) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.TAB1_COL2, username);
        contentValues.put(DatabaseHelper.TAB1_COL3, password);
        contentValues.put(DatabaseHelper.TAB1_COL4, role);
        long result = db.insert(DatabaseHelper.TAB1_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public int updateAkun(int id, String uname, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TAB1_COL2, uname);
        values.put(TAB1_COL3, pass);

        return db.update(TAB1_NAME, values, TAB1_COL1 + " = ?",
                new String[]{String.valueOf(id)});
    }

    public void deleteAkun(int id) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TAB1_NAME, TAB1_COL1 + " = ?",
                new String[]{String.valueOf(id)});
    }

    public Cursor cekAkun (String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TAB1_NAME + " WHERE " + DatabaseHelper.TAB1_COL2 + "=?", new String[]{username});
        return  cursor;
    }

    public ArrayList<MenuModel> getAllMenu() {
        ArrayList<MenuModel> menuModelArrayList = new ArrayList<MenuModel>();

        String query = "SELECT * FROM " + TAB2_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                MenuModel menuModel = new MenuModel();
                menuModel.setId(cursor.getInt(cursor.getColumnIndex(TAB2_COL1)));
                menuModel.setNama(cursor.getString(cursor.getColumnIndex(TAB2_COL2)));
                menuModel.setHarga(cursor.getInt(cursor.getColumnIndex(TAB2_COL3)));
                menuModel.setJumlah(0);
                menuModelArrayList.add(menuModel);
            } while (cursor.moveToNext());
        }

        return menuModelArrayList;
    }

    public boolean insertmenu(SQLiteDatabase db, String nama, int harga){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.TAB2_COL2, nama);
        contentValues.put(DatabaseHelper.TAB2_COL3, harga);
        long result = db.insert(DatabaseHelper.TAB2_NAME, null, contentValues);
        if (result== -1)
            return false;
        else
            return true;
    }

    public int updateMenu(int id, String nama, int harga) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TAB2_COL2, nama);
        values.put(TAB2_COL3, harga);
        return db.update(TAB2_NAME, values, TAB2_COL1 + " = ?",
                new String[]{String.valueOf(id)});
    }

    public void deleteMenu(int id) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TAB2_NAME, TAB2_COL1 + " = ?",
                new String[]{String.valueOf(id)});
    }

    public Cursor cekMenu (String nama){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TAB2_NAME + " WHERE " + DatabaseHelper.TAB2_COL2 + "=?", new String[]{nama});
        return  cursor;
    }

    public ArrayList<TransaksiModel> getAllTransaksi() {
        ArrayList<TransaksiModel> transaksiModelArrayList = new ArrayList<TransaksiModel>();

        String query = "SELECT * FROM " + TAB3_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                TransaksiModel transaksiModel = new TransaksiModel();
                transaksiModel.setId(cursor.getInt(cursor.getColumnIndex(TAB3_COL1)));
                transaksiModel.setTotal(cursor.getInt(cursor.getColumnIndex(TAB3_COL2)));
                transaksiModel.setTanggal(cursor.getString(cursor.getColumnIndex(TAB3_COL3)));
                transaksiModelArrayList.add(transaksiModel);
            } while (cursor.moveToNext());
        }

        return transaksiModelArrayList;
    }

    public boolean insertTrans(SQLiteDatabase db, int total, String tanggal){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.TAB3_COL2, total);
        contentValues.put(DatabaseHelper.TAB3_COL3, tanggal);
        long result = db.insert(DatabaseHelper.TAB3_NAME, null, contentValues);
        if (result== -1)
            return false;
        else
            return true;
    }
}
