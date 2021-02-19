package com.silva021.covid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;

import com.silva021.covid.locationFilter.LocationFilterActivity;
import com.silva021.covid.model.Location;
import com.silva021.covid.utils.Constant;
import com.silva021.covid.utils.DatePickerFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilterActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btnLocation)
    Button btnLocation;
    @BindView(R.id.btnDate)
    Button btnDate;

    Location location;
    String dateParms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Filtrar");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnLocation.setOnClickListener((view) -> startActivityForResult(new Intent(FilterActivity.this, LocationFilterActivity.class), Location.REQUEST_CODE));

        btnDate.setOnClickListener((view) -> {
            DatePickerFragment datePickerFragment = new DatePickerFragment();
            datePickerFragment.show(getSupportFragmentManager(), "date");

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_filter, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                returnMainActivityWithFilter();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void returnMainActivityWithFilter() {
        Intent intent = new Intent();
        intent.putExtra(Constant.KEY_DATE, dateParms);
        intent.putExtra(Constant.KEY_LOCATION, location);
        this.setResult(RESULT_OK, intent);
        this.finish();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case Location.REQUEST_CODE:
                location = (Location) data.getSerializableExtra(Location.KEY);
                btnLocation.setText(location.getNome());
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date;
        Calendar calendar = Calendar.getInstance();
        Locale locale = new Locale("pt", "BR");
        calendar.set(year, month, dayOfMonth);

        try {
            date = new SimpleDateFormat("dd-MM-yyyy", locale).format(calendar.getTime());
        } catch (Exception e) {
            date = null;
        }
        dateParms = "" + year + month + (dayOfMonth < 10? "0"+dayOfMonth: dayOfMonth);
        btnDate.setText(date);
    }
}