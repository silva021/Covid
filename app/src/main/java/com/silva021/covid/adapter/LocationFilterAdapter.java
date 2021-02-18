package com.silva021.covid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.silva021.covid.R;
import com.silva021.covid.model.Location;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LocationFilterAdapter extends RecyclerView.Adapter<LocationFilterAdapter.ViewHolder> {
    Context mContext;
    List<Location> mList;
    private OnItemClickListener mListener;

    public LocationFilterAdapter(Context mContext, List<Location> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    private Location getItem(int position) {
        return mList.get(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.location_filter_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Location location = mList.get(position);
        holder.txtUF.setText(location.getNome() + ", " + location.getSigla());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtUF)
        Button txtUF;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }

        @OnClick(R.id.txtUF)
        public void onClick() {
            if (mListener != null) {
                Location location = getItem(getAdapterPosition());
                mListener.onLocationItemClick(location);
            }
        }
    }

    public interface OnItemClickListener {

        void onLocationItemClick(@NonNull Location location);

    }
}
