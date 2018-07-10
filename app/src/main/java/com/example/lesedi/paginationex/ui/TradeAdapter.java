package com.example.lesedi.paginationex.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.lesedi.paginationex.R;
import com.example.lesedi.paginationex.models.Trade;

import java.util.ArrayList;
import java.util.List;

public class TradeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private static final String IMG_URL = "http://img.bidorbuy.co.za/image/upload/v1475092439/user_images/380/137380/160928215353_2016-09-28%2021.25.04.jpg";
    private Context context;
    private List<Trade> tradeList;
    private boolean isLoadingAdded = false;

    public TradeAdapter(Context context){
        this.context = context;
        this.tradeList = new ArrayList<>();

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ITEM:
                viewHolder = getViewHolder(parent, inflater);
                break;
            case LOADING:
                View view = inflater.inflate(R.layout.progress_item, parent, false);
                viewHolder = new Loading(view);
                break;
        }
        return viewHolder;
    }

    @NonNull
    private RecyclerView.ViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {
        RecyclerView.ViewHolder viewHolder;
        View view = inflater.inflate(R.layout.list_trade, parent, false);
        viewHolder = new TradeView(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Trade trade = tradeList.get(position);

        switch (getItemViewType(position)) {
            case ITEM:
                final TradeView v_trade = (TradeView) holder;

                v_trade.tradeTitle.setText(trade.getTitle());


                v_trade.tradeLocation.setText(trade.getLocation());
                v_trade.type.setText(trade.getType());

                Glide.with(context)
                        .load(IMG_URL + trade.getImages())
                        .listener(new RequestListener<String, GlideDrawable>() {
                            @Override
                            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                                v_trade.progressBar.setVisibility(View.GONE);
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                v_trade.progressBar.setVisibility(View.GONE);
                                return false;
                            }
                        })
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .centerCrop()
                        .crossFade()
                        .into(v_trade.imageView);

                break;

            case LOADING:

                break;
        }
    }

    @Override
    public int getItemCount() {
        return tradeList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == tradeList.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    protected class Loading extends RecyclerView.ViewHolder {

        public Loading(View itemView) {
            super(itemView);
        }
    }

    protected class TradeView extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private ProgressBar progressBar;
        private TextView tradeTitle;
        private TextView type;
        private TextView tradeLocation;


        public TradeView(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progress);
            tradeTitle = (TextView) itemView.findViewById(R.id.title);
            tradeLocation = (TextView) itemView.findViewById(R.id.location);
            type = (TextView) itemView.findViewById(R.id.type);

        }
    }


    public void add(Trade trade) {
        tradeList.add(trade);
        notifyItemInserted(tradeList.size() - 1);
    }

    public void addAll(List<Trade> list) {
        for (Trade trade : list) {
            add(trade);
        }
    }

    public void remove(Trade trade) {
        int position = tradeList.indexOf(trade);
        if (position > -1) {
            tradeList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }


    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new Trade());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = tradeList.size() - 1;
        Trade tradeR = getItem(position);

        if (tradeR != null) {
            tradeList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public Trade getItem(int position) {
        return tradeList.get(position);
    }
}
