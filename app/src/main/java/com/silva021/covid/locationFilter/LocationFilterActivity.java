package com.silva021.covid.locationFilter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.silva021.covid.R;
import com.silva021.covid.adapter.LocationFilterAdapter;
import com.silva021.covid.model.Location;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LocationFilterActivity extends AppCompatActivity implements LocationFilterContract.View, LocationFilterAdapter.OnItemClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler)
    RecyclerView recycler;

    private LocationFilterContract.Presenter mPresenter;
    private Location mLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_filter);
        ButterKnife.bind(this);
        new LocationFilterPresenter(this);
        mPresenter.start();

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Localização");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void notifyUserFailureGet(String message) {
        Snackbar.make(getCurrentFocus(), message, BaseTransientBottomBar.LENGTH_LONG).setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE).show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finishActivityForResult();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void initalizeRecycler(List<Location> list) {
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recycler.getContext(), DividerItemDecoration.VERTICAL);
        LocationFilterAdapter locationFilterAdapter = new LocationFilterAdapter(getApplicationContext(), list);
        locationFilterAdapter.setOnItemClickListener(this);
        recycler.setLayoutManager(new LinearLayoutManager(recycler.getContext(), RecyclerView.VERTICAL, false));
        recycler.addItemDecoration(dividerItemDecoration);
        recycler.setAdapter(locationFilterAdapter);
    }

    @Override
    public void setPresenter(@NonNull LocationFilterContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void onLocationItemClick(@NonNull Location location) {
        this.mLocation = location;
        finishActivityForResult();
    }

    private void finishActivityForResult() {
        Intent intent = new Intent();
        intent.putExtra(Location.KEY, mLocation);
        setResult(RESULT_OK, intent);
        finish();
    }
}