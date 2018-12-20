package com.example.wiranata.impal_ab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditakunpActivity extends AppCompatActivity {

    private AkunModel akunModel;
    private DatabaseHelper openHelper;
    private String textuname, textpass;
    Button btndelete, btnupdate, btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editakunp);

        Intent intent = getIntent();
        akunModel = (AkunModel) intent.getSerializableExtra("akun");

        openHelper = new DatabaseHelper(this);

        final EditText etuname = (EditText) findViewById(R.id.edUsernameP);
        final EditText etpass = (EditText) findViewById(R.id.edPasswordP);
        btndelete = (Button) findViewById(R.id.btndelete);
        btnupdate = (Button) findViewById(R.id.btnSubmit);
        btnback = (Button) findViewById(R.id.back);

        textuname = akunModel.getUsername();
        textpass = akunModel.getPassword();
        etuname.setText(textuname);
        etpass.setText(textpass);

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((etuname.getText().length() != 0) && (etpass.getText().length() != 0)){
                    textuname = etuname.getText().toString();
                    textpass = etpass.getText().toString();
                    openHelper.updateAkun(akunModel.getId(),textuname,textpass);
                    Toast.makeText(EditakunpActivity.this, "Edit Berhasil!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditakunpActivity.this,EmployeeList.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else
                    Toast.makeText(getApplicationContext(), "Edit Menu Gagal, Username dan Password Tidak Boleh Kosong",Toast.LENGTH_LONG).show();

            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHelper.deleteAkun(akunModel.getId());
                Toast.makeText(EditakunpActivity.this, "Delet Berhasil!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EditakunpActivity.this,EmployeeList.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditakunpActivity.this,EmployeeList.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
