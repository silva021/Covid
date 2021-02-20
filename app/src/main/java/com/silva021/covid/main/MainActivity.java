package com.silva021.covid.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
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
import com.silva021.covid.model.Filter;
import com.silva021.covid.model.Location;
import com.silva021.covid.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.silva021.covid.utils.Constant.REQUEST_CODE_ACTIVITY_FILTER;

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
    Filter filter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initalizeComponents();
    }

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
                startActivity(new Intent(this, AboutActivity.class));
                break;
            case R.id.toolbar_main_filter:
                startActivityForResult(new Intent(MainActivity.this, FilterActivity.class).putExtra(Constant.KEY_FILTER, filter), REQUEST_CODE_ACTIVITY_FILTER);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + item.getItemId());
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_ACTIVITY_FILTER) {
            loadCovidDataUF(data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void loadCovidDataUF(Intent data) {
        filter = (Filter) data.getSerializableExtra(Constant.KEY_FILTER);
        if (filter != null) {
            mainPresenter.loadCovidStateUF(filter.getLocation().getSigla());
            mainPresenter.loadCovidDataAllBrazilUF(filter.getDate());
        }

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
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recycler.getContext(), DividerItemDecoration.VERTICAL);
        CovidDataAdapter covidDataAdapter = new CovidDataAdapter(getApplicationContext(), covidData);
        recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recycler.addItemDecoration(dividerItemDecoration);
        recycler.setAdapter(covidDataAdapter);
    }

    @Override
    public void updateCovidData(CovidData covidData) {
        txtUF.setText(covidData.getState());
        txtNumberCasos.setText(String.valueOf(Math.round(covidData.getCases())));
        txtNumberObitos.setText(String.valueOf(Math.round(covidData.getDeaths())));

    }
}