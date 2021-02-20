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
import com.silva021.covid.model.Filter;
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

    Filter filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Filtrar");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        filter = (Filter) getIntent().getSerializableExtra(Constant.KEY_FILTER);
        if (filter != null) {
            btnLocation.setText(filter.getLocation().getNome());
            btnDate.setText(returnDateFormat(filter.getDate()));
        }
        btnLocation.setOnClickListener((view) -> startActivityForResult(new Intent(FilterActivity.this, LocationFilterActivity.class), Location.REQUEST_CODE));

        btnDate.setOnClickListener((view) -> {
            DatePickerFragment datePickerFragment = new DatePickerFragment();
            datePickerFragment.show(getSupportFragmentManager(), "date");

        });
    }

    private String returnDateFormat(String date) {
        // 4:50 da manhã, não irei usar o sdf não kkkkkkk
        String year = date.substring(0, 4);
        String month = date.substring(4, 6);
        String day = date.substring(6, 8);

        return day + "-" + month +"-" + year;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_filter, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishActivityForResult();
    }

    private void finishActivityForResult() {
        Intent intent = new Intent();
        intent.putExtra(Constant.KEY_FILTER, filter);
        this.setResult(RESULT_OK, intent);
        this.finish();
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case Location.REQUEST_CODE:
                Location location = (Location) data.getSerializableExtra(Location.KEY);

                if (location != null) {
                    if (filter == null)
                        filter = new Filter();

                    filter.setLocation(location);
                    btnLocation.setText(location.getNome());
                }
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

        filter.setDate("" + year + (month < 10 ? "0" + month : month) + (dayOfMonth < 10 ? "0" + dayOfMonth : dayOfMonth));
        btnDate.setText(date);
    }
}