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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.silva021.covid.AboutActivity;
import com.silva021.covid.FilterActivity;
import com.silva021.covid.R;
import com.silva021.covid.adapter.CovidDataAdapter;
import com.silva021.covid.model.CovidData;
import com.silva021.covid.model.Filter;
import com.silva021.covid.utils.Constant;

import java.text.NumberFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.silva021.covid.utils.Constant.REQUEST_CODE_ACTIVITY_FILTER;

public class MainActivity extends AppCompatActivity implements MainContract.View {
    @BindView(R.id.lytRecoverd)
    LinearLayout lytRecoverd;
    @BindView(R.id.lytRecoverdLabel)
    LinearLayout lytRecoverdLabel;
    @BindView(R.id.txtEstado)
    TextView txtUF;
    @BindView(R.id.txtCasesConfirmedLabel)
    TextView txtCasesConfirmedLabel;
    @BindView(R.id.txtCases)
    TextView txtCases;
    @BindView(R.id.txtCasesConfirmed)
    TextView txtCasesConfirmed;
    @BindView(R.id.txtRecoverd)
    TextView txtRecoverd;
    @BindView(R.id.txtDeath)
    TextView txtDeath;
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
    @BindView(R.id.progressBarRecycler)
    ProgressBar progressBarRecycler;
    @BindView(R.id.progressBarData)
    ProgressBar progressBarData;

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
                openActivityFilter();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + item.getItemId());
        }

        return super.onOptionsItemSelected(item);
    }

    private void openActivityFilter() {
        startActivityForResult(new Intent(MainActivity.this, FilterActivity.class).putExtra(Constant.KEY_FILTER, filter), REQUEST_CODE_ACTIVITY_FILTER);
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
            if (filter.getLocation() != null) {
                showProgressLiveData(true);
                mainPresenter.loadCovidStateUF(filter.getLocation().getSigla());
            }

            if (filter.getDate() != null) {
                showProgressRecycler(true);
                mainPresenter.loadCovidDataAllBrazilUF(filter.getDate());
            }
        }

    }

    @Override
    public void setPresenter(@NonNull MainContract.Presenter presenter) {
        this.mMainpresenter = presenter;
    }

    @Override
    public void notifyUserCovidDataEmply(String message) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_INDEFINITE)
                .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE)
                .setAction("Filtrar outra data", view -> {
                    openActivityFilter();
                })
                .show();
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
    public void updateViewCovidDataCountry(CovidData covidData) {
        txtUF.setText(covidData.getState());
        txtCases.setText(NumberFormat.getInstance().format(Math.round(covidData.getCases())));
        txtCasesConfirmed.setText(NumberFormat.getInstance().format(Math.round(covidData.getConfirmed())));
        txtDeath.setText(NumberFormat.getInstance().format(Math.round(covidData.getDeaths())));
        txtRecoverd.setText(NumberFormat.getInstance().format(Math.round(covidData.getRecovered())));
        lytRecoverd.setVisibility(View.VISIBLE);
        lytRecoverdLabel.setVisibility(View.VISIBLE);
        txtCasesConfirmed.setVisibility(View.VISIBLE);
        txtCasesConfirmedLabel.setVisibility(View.VISIBLE);
    }

    @Override
    public void updateViewCovidDataUF(CovidData covidData) {
        txtUF.setText(covidData.getState());
        txtCases.setText(NumberFormat.getInstance().format(Math.round(covidData.getCases())));
        txtCasesConfirmed.setText(NumberFormat.getInstance().format(Math.round(covidData.getConfirmed())));
        txtDeath.setText(NumberFormat.getInstance().format(Math.round(covidData.getDeaths())));
        txtRecoverd.setText(NumberFormat.getInstance().format(Math.round(covidData.getRecovered())));
        lytRecoverd.setVisibility(View.GONE);
        lytRecoverdLabel.setVisibility(View.GONE);
        txtCasesConfirmed.setVisibility(View.GONE);
        txtCasesConfirmedLabel.setVisibility(View.GONE);
    }

    @Override
    public void showProgressRecycler(boolean b) {
        progressBarRecycler.setVisibility(b ? View.VISIBLE : View.GONE);
        recycler.setVisibility(b ? View.GONE : View.VISIBLE);
    }

    @Override
    public void showProgressLiveData(boolean b) {
        progressBarData.setVisibility(b ? View.VISIBLE : View.GONE);
        linearDataCovid.setVisibility(b ? View.GONE : View.VISIBLE);
    }
}