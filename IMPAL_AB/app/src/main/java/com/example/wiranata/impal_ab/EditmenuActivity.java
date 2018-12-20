package com.example.wiranata.impal_ab;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditmenuActivity extends AppCompatActivity {

    private MenuModel menuModel;
    private EditText etmenu, etharga;
    Button btnupdate, btndelete, btnback;
    private DatabaseHelper openHelper;
    String textnama, textharga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editmenu);

        Intent intent = getIntent();
        menuModel = (MenuModel) intent.getSerializableExtra("menu");

        openHelper = new DatabaseHelper(this);

        etmenu = (EditText) findViewById(R.id.menu);
        etharga = (EditText) findViewById(R.id.harga);
        btndelete = (Button) findViewById(R.id.btnHapus);
        btnupdate = (Button) findViewById(R.id.btnSubmit);
        btnback = (Button) findViewById(R.id.back);

        textnama = menuModel.getNama();
        textharga = Integer.toString(menuModel.getHarga());
        etmenu.setText(textnama);
        etharga.setText(textharga);

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((etmenu.getText().length() != 0) && (etharga.getText().length() != 0)){
                    textnama = etmenu.getText().toString();
                    textharga = etharga.getText().toString();
                    openHelper.updateMenu(menuModel.getId(),textnama,Integer.parseInt(textharga));
                    Toast.makeText(EditmenuActivity.this, "Edit Berhasil!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditmenuActivity.this,Managemenu.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else
                    Toast.makeText(getApplicationContext(), "Edit Menu Gagal, Nama dan Harga Tidak Boleh Kosong",Toast.LENGTH_LONG).show();
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHelper.deleteMenu(menuModel.getId());
                Toast.makeText(EditmenuActivity.this, "Delet Berhasil!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EditmenuActivity.this,Managemenu.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditmenuActivity.this,Managemenu.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
