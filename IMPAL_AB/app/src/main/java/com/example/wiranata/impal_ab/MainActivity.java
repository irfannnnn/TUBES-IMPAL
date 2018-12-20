package com.example.wiranata.impal_ab;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.List;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    DatabaseHelper openHelper;
    SQLiteDatabase db;
    private ListView mListView;
    private ArrayList<MenuModel> menuModelArrayList;
    private int totalHarga, kembalian;
    private TextView txtharga, txtkembalian;
    private EditText bayar;
    private Button hitung, oke;
    private AlertDialog.Builder popupkembalian, popupkonfirmasi;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView)findViewById(R.id.listmain);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        openHelper = new DatabaseHelper(this);
        menuModelArrayList = openHelper.getAllMenu();
        CustomAdapterMenu customAdapter = new CustomAdapterMenu(this, menuModelArrayList);
        mListView.setAdapter(customAdapter);
        Button btnSubmit = (Button) findViewById(R.id.btnsubmitmainp);
//        harga = (TextView)findViewById(R.id.totalharga);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = openHelper.getWritableDatabase();
                totalHarga = 0;
                for (int position = 0; position < menuModelArrayList.size(); position++) {
                    totalHarga += menuModelArrayList.get(position).hitungHarga();
                }
                showPopupKonfirmasi(totalHarga);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (bayar.getText().length() == 0){
                            Toast.makeText(getApplicationContext(), "Masukkan Jumlah Uang",Toast.LENGTH_LONG).show();
                        } else {
                            kembalian = hitungKembalian(Integer.parseInt(bayar.getText().toString()), totalHarga);
                            if (kembalian < 0){
                                Toast.makeText(getApplicationContext(), "Jumlah Uang Kurang",Toast.LENGTH_LONG).show();
                            } else {
                                Calendar calendar = Calendar.getInstance();
                                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                                String tanggal = format.format(calendar.getTime());
                                boolean isInserted = openHelper.insertTrans(db, totalHarga, tanggal);

                                if (isInserted) {
                                    showPopupKembalian(kembalian);

                                    oke.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent intent = new Intent(MainActivity.this,MainActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    });
                                } else
                                    Toast.makeText(getApplicationContext(), "Transaksi Gagal",Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public int hitungKembalian(int uang, int totalHarga){
        kembalian = uang - totalHarga;
        return kembalian;
    }

    public void showPopupKonfirmasi(int totalHarga) {
        popupkonfirmasi = new AlertDialog.Builder(MainActivity.this);

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_popup, null  );

        popupkonfirmasi.setView(view);

        txtharga = (TextView) view.findViewById(R.id.totalharga);
        bayar = (EditText) view.findViewById(R.id.cash);
        hitung = (Button) view.findViewById(R.id.hitung);

        txtharga.setText("Rp " + Integer.toString(totalHarga));

        popupkonfirmasi.create();
        popupkonfirmasi.show();
    }
    public void showPopupKembalian(int kembalian) {
        popupkembalian = new AlertDialog.Builder(MainActivity.this);

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.content_kembalianpp, null  );

        popupkembalian.setView(view);

        txtkembalian = (TextView) view.findViewById(R.id.kembalian);
        oke = (Button) view.findViewById(R.id.oke);

        txtkembalian.setText("Rp " + Integer.toString(kembalian));

        popupkembalian.create();
        popupkembalian.show();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Managemenu) {
            Intent manmenu = new Intent(this,Managemenu.class);
            startActivity(manmenu);
        } else if (id == R.id.Note) {
            Intent trans = new Intent(this,Transaksilist.class);
            startActivity(trans);
            finish();
        } else if (id == R.id.register) {
            Intent reg = new Intent(this,EmployeeList.class);
            startActivity(reg);
        } else if (id == R.id.logout) {
            Intent logout = new Intent(this,LoginActivity.class);
            startActivity(logout);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
