package com.silva021.covid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.txtEstado)
    TextView txtUF;
    @BindView(R.id.txtNumeroCasos)
    TextView txtNumberCasos;
    @BindView(R.id.txtNumeroObitos)
    TextView txtNumberObitos;
    @BindView(R.id.linearDataCovid)
    LinearLayout linearDataCovid;
    @BindView(R.id.linearPermisisonDenied)
    LinearLayout linearPermisisonDenied;
    @BindView(R.id.btnLocation)
    Button btnLocation;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initalizeComponents();
        checkPermission();
    }

    private void checkPermission() {
        if (getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_LOCATION)) {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), LOCATION_SERVICE) == PackageManager.PERMISSION_GRANTED) {
                searchLocalization();
            } else {
                uiPermissionDenied();
            }
        } else {
            Toast.makeText(getApplicationContext(), "não Tem", Toast.LENGTH_LONG).show();
            ;
        }
    }

    public void searchLocalization() {

    }

    public void requestPermission() {
//        ActivityResultLauncher<String> requestPermisisonLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
//            if (isGranted){
//                Toast.makeText(getApplicationContext(), "aceito", Toast.LENGTH_LONG).show();
//            } else {
//                Toast.makeText(getApplicationContext(), "não aceitou", Toast.LENGTH_LONG).show();
//            }
//        });
    }

    private void initalizeComponents() {
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Inicio");

        btnLocation.setOnClickListener(click -> requestPermission());
    }

    private void uiPermissionDenied() {
        linearDataCovid.setVisibility(View.GONE);
        linearPermisisonDenied.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.toolbar_main_about:
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}