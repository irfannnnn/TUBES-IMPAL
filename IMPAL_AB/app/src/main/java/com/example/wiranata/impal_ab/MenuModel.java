package com.example.wiranata.impal_ab;

import java.io.Serializable;

public class MenuModel implements Serializable {

    private String nama;
    private int id, harga, jumlah;


    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int hitungHarga () {
        return this.harga * this.jumlah;
    }

}
