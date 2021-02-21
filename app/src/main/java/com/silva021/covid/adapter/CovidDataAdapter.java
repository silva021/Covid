package com.silva021.covid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.silva021.covid.R;
import com.silva021.covid.model.CovidData;

import java.text.NumberFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CovidDataAdapter extends RecyclerView.Adapter<CovidDataAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<CovidData> mList;

    public CovidDataAdapter(Context mContext, ArrayList<CovidData> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.main_covid_data_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CovidData covidData = mList.get(position);

        holder.txtState.setText(covidData.getState());
        holder.txtCases.setText(NumberFormat.getInstance().format(Math.round(covidData.getCases())));
        holder.txtDeath.setText(NumberFormat.getInstance().format(Math.round(covidData.getDeaths())));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtState)
        TextView txtState;
        @BindView(R.id.txtCases)
        TextView txtCases;
        @BindView(R.id.txtDeath)
        TextView txtDeath;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
