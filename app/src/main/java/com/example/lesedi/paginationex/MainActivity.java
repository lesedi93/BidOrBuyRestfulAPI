package com.example.lesedi.paginationex;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.lesedi.paginationex.api_service.TradeAPI;
import com.example.lesedi.paginationex.api_service.TradeService;
import com.example.lesedi.paginationex.models.Trade;
import com.example.lesedi.paginationex.models.TradeResults;
import com.example.lesedi.paginationex.ui.TradeAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {




    ProgressBar progressBar;
    private RecyclerView rv;
    private LinearLayoutManager linearLayoutManager;

    private static final int PAGE_NUM = 1;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int resultsPerPage = 25;
    private int currentPage = PAGE_NUM;

    private TradeAdapter adapter;


    private TradeService tradeService;

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = (RecyclerView) findViewById(R.id.r_view);
        progressBar = (ProgressBar) findViewById(R.id.main_progress);

        adapter = new TradeAdapter(this);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(linearLayoutManager);

        rv.setItemAnimator(new DefaultItemAnimator());

        rv.setAdapter(adapter);

        rv.addOnScrollListener(new PaginatedScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadNextPage();
                    }
                }, 1000);
            }

            @Override
            public int getTotalPageCount() {
                return resultsPerPage;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

        tradeService = TradeAPI.getClient().create(TradeService.class);

        loadFirstPage();
    }

    /**
     * funtion to load first pages results
     *
     */
    private void loadFirstPage() {
        Log.d(TAG, "loadFirstPage: ");

        callTradeApi().enqueue(new Callback<TradeResults>() {
            @Override
            public void onResponse(Call<TradeResults> call, Response<TradeResults> response) {
                // Got data. Send it to adapter

                List<Trade> tradeList = fetchTradeResults(response);
                progressBar.setVisibility(View.GONE);
                adapter.addAll(tradeList);

                if (currentPage <= resultsPerPage) adapter.addLoadingFooter();
                else isLastPage = true;
            }

            @Override
            public void onFailure(Call<TradeResults> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    /**
     * @param response extracts List<{ Trade>} from response
     * @return
     */
    private List<Trade> fetchTradeResults(Response<TradeResults> response) {
        TradeResults tradeResults = response.body();
        return tradeResults.getTradeList();
    }

    /**
     * funtion to load upcoming pages results
     *
     */
    private void loadNextPage() {
        Log.d(TAG, "loadNextPage: " + currentPage);

        callTradeApi().enqueue(new Callback<TradeResults>() {
            @Override
            public void onResponse(Call<TradeResults> call, Response<TradeResults> response) {
                adapter.removeLoadingFooter();
                isLoading = false;

                List<Trade> trades = fetchTradeResults(response);
                adapter.addAll(trades);

                if (currentPage != resultsPerPage) adapter.addLoadingFooter();
                else isLastPage = true;
            }

            @Override
            public void onFailure(Call<TradeResults> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    /**
     * Performs a Retrofit call
     * by @{PaginatedScrollListener} to load next page.
     */
    private Call<TradeResults> callTradeApi() {
        return tradeService.getTradeSearch("frRurWKTnhEAUxZXQsAtHjZe8ubALbXdsx9YtrDj",
                4,987654321);
    }
}
