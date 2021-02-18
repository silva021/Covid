package com.silva021.covid;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Instrumentation;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initalizeComponents();
        checkPermission();
    }

    private void checkPermission() {
        if (getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_LOCATION)) {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), LOCATION_SERVICE) == PackageManager.PERMISSION_GRANTED){
                searchLocalization();
            } else {
                uiPermissionDenied();
            }
        } else {
            Toast.makeText(getApplicationContext(), "não Tem", Toast.LENGTH_LONG).show();;
        }
    }

    public void searchLocalization(){

    }

    public void requestPermission(){
//        ActivityResultLauncher<String> requestPermisisonLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
//            if (isGranted){
//                Toast.makeText(getApplicationContext(), "aceito", Toast.LENGTH_LONG).show();
//            } else {
//                Toast.makeText(getApplicationContext(), "não aceitou", Toast.LENGTH_LONG).show();
//            }
//        });

    }
    private void initalizeComponents() {
        mViewHolder.txtUF           = findViewById(R.id.txtEstado);
        mViewHolder.txtNumberCasos  = findViewById(R.id.txtNumeroCasos);
        mViewHolder.txtNumberObitos = findViewById(R.id.txtNumeroObitos);

        mViewHolder.linearDataCovid         = findViewById(R.id.linearDataCovid);
        mViewHolder.linearPermisisonDenied  = findViewById(R.id.linearPermisisonDenied);
        mViewHolder.btnLocation             = findViewById(R.id.btnLocation);

        mViewHolder.btnLocation.setOnClickListener(click -> {
            requestPermission();
        });
    }
    private void uiPermissionDenied(){
        mViewHolder.linearDataCovid.setVisibility(View.GONE);
        mViewHolder.linearPermisisonDenied.setVisibility(View.VISIBLE);
    }

    static class ViewHolder {
        TextView txtUF, txtObitos, txtCasos, txtNumberCasos, txtNumberObitos;
        LinearLayout linearPermisisonDenied, linearDataCovid;
        Button btnLocation;
    }
}