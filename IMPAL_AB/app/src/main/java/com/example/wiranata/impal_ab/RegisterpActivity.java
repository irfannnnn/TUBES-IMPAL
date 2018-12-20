package com.example.wiranata.impal_ab;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterpActivity extends AppCompatActivity {
    DatabaseHelper openHelper;
    SQLiteDatabase db;
    Cursor cursor;
    Button btnAdd, btnBack;
    EditText txtNama, txtPass, txtConfPass;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerp);
        btnAdd = (Button)findViewById(R.id.bDaftar);
        btnBack = (Button)findViewById(R.id.bBack);
        txtNama = (EditText)findViewById(R.id.RegUsername);
        txtPass = (EditText)findViewById(R.id.RegPassword);
        txtConfPass = (EditText)findViewById(R.id.RegConfirm);
        openHelper = new DatabaseHelper(this);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = openHelper.getWritableDatabase();

                if ((txtNama.getText().length() != 0 ) && (txtPass.getText().length() != 0)) {

                    String nama = txtNama.getText().toString();
                    String pass = txtPass.getText().toString();
                    String confPass = txtConfPass.getText().toString();

                    Cursor cursor = openHelper.cekAkun(nama);
                    if (cursor != null){
                        if (cursor.getCount() > 0) {
                            Toast.makeText(getApplicationContext(), "Tambah Akun Gagal, Username Sudah Digunakan", Toast.LENGTH_SHORT).show();
                        } else {
                            if (pass.equals(confPass)) {
                                boolean isInserted = openHelper.insertakun(db, nama, pass, "pegawai");
                                if (isInserted) {
                                    Toast.makeText(getApplicationContext(), "Registrasi Akun Berhasil", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(RegisterpActivity.this, EmployeeList.class);
                                    startActivity(intent);
                                    finish();
                                } else
                                    Toast.makeText(getApplicationContext(), "Registrasi Akun Gagal", Toast.LENGTH_LONG).show();
                            } else
                                Toast.makeText(getApplicationContext(), "Confirm Password Salah", Toast.LENGTH_LONG).show();
                        }
                    }
                } else
                    Toast.makeText(getApplicationContext(), "Tambah Akun Gagal, Nama dan Password Tidak Boleh Kosong",Toast.LENGTH_LONG).show();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(RegisterpActivity.this,EmployeeList.class);
                startActivity(back);
                finish();
            }
        });
    }
}
