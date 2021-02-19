package com.silva021.covid.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.silva021.covid.AboutActivity;
import com.silva021.covid.FilterActivity;
import com.silva021.covid.R;
import com.silva021.covid.adapter.CovidDataAdapter;
import com.silva021.covid.model.CovidData;
import com.silva021.covid.model.Location;
import com.silva021.covid.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View {
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
    @BindView(R.id.recycler)
    RecyclerView recycler;

    MainPresenter mainPresenter;
    MainContract.Presenter mMainpresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initalizeComponents();
        Log.d("ciclo", "OnCreate");
    }
//
//    @Override
//    protected void onStart() {
//        Log.d("ciclo", "onStart");
//        super.onStart();
//    }
//    @Override
//    protected void onResume() {
//        Log.d("ciclo", "onResume");
//        super.onStart();
//    }
//
//    @Override
//    protected void onPause() {
//        Log.d("ciclo", "onPause");
//        super.onPause();
//    }
//
//    @Override
//    protected void onStop() {
//        Log.d("ciclo", "onStop");
//        super.onStop();
//    }
//
//    @Override
//    protected void onRestart() {
//        Log.d("ciclo", "onRestart");
//        super.onRestart();
//    }
//
//    @Override
//    protected void onDestroy() {
//        Log.d("ciclo", "onDestroy");
//        super.onDestroy();
//    }

    private void initalizeComponents() {
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Inicio");

        mainPresenter = new MainPresenter(this);
        mainPresenter.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_main_about:
                startActivityForResult(new Intent(this, AboutActivity.class), 10);
                break;
            case R.id.toolbar_main_filter:
                startActivityForResult(new Intent(this, FilterActivity.class), 18);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + item.getItemId());
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case 18:
                loadCovidDataUF(data);
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void loadCovidDataUF(Intent data) {
        String date = data.getStringExtra(Constant.KEY_DATE);
        Location location = (Location) data.getSerializableExtra(Constant.KEY_LOCATION);
        mainPresenter.loadCovidStateUF(location.getSigla());

    }

    @Override
    public void setPresenter(@NonNull MainContract.Presenter presenter) {
        this.mMainpresenter = presenter;
    }

    @Override
    public void notifyUserFailureGet(String message) {

    }

    @Override
    public void initializeRecycler(ArrayList<CovidData> covidData) {
        CovidDataAdapter covidDataAdapter = new CovidDataAdapter(getApplicationContext(), covidData);
        recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recycler.setAdapter(covidDataAdapter);
//        Toast.makeText(this, " " + covidData.size(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateCovidData(CovidData covidData) {
        txtUF.setText(covidData.getState());
        txtNumberCasos.setText(String.valueOf(Math.round(covidData.getCases())));
        txtNumberObitos.setText(String.valueOf(Math.round(covidData.getDeaths())));
//        Toast.makeText(this, "aaaaaa", Toast.LENGTH_SHORT).show();

    }
}