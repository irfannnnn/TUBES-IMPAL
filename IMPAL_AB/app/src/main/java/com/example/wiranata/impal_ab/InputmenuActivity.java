package com.example.wiranata.impal_ab;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Currency;

public class InputmenuActivity extends AppCompatActivity {
    DatabaseHelper openHelper;
    SQLiteDatabase db;
    Cursor cursor;
    Button btnAdd, btnBack;
    EditText txtNama, txtHarga;
    private int harga;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputmenu);
        btnAdd = (Button)findViewById(R.id.btnSubmit);
        btnBack = (Button)findViewById(R.id.btnCancel);
        txtNama = (EditText)findViewById(R.id.menu);
        txtHarga = (EditText)findViewById(R.id.harga);
        openHelper = new DatabaseHelper(this);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = openHelper.getWritableDatabase();

                if ((txtHarga.getText().length() != 0) && (txtNama.getText().length() != 0)){

                    String nama = txtNama.getText().toString();
                    String strHarga = txtHarga.getText().toString();
                    harga = Integer.parseInt(strHarga);

                    Cursor cursor = openHelper.cekMenu(nama);
                    if (cursor != null){
                        if (cursor.getCount() > 0){
                            Toast.makeText(getApplicationContext(), "Tambah Menu Gagal, Menu Sudah Ada", Toast.LENGTH_SHORT).show();
                        } else {
                            boolean isInserted = openHelper.insertmenu(db,nama,harga);
                            if (isInserted) {
                                Toast.makeText(getApplicationContext(), "Tambah Menu Berhasil", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(InputmenuActivity.this,Managemenu.class);
                                startActivity(intent);
                                finish();
                            } else
                                Toast.makeText(getApplicationContext(), "Tambah Menu Gagal",Toast.LENGTH_LONG).show();
                        }
                    }
                } else
                    Toast.makeText(getApplicationContext(), "Tambah Menu Gagal, Nama dan Harga Tidak Boleh Kosong",Toast.LENGTH_LONG).show();

            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(InputmenuActivity.this,Managemenu.class);
                startActivity(back);
                finish();
            }
        });
    }
}
