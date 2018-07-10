package com.example.lesedi.paginationex.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TradeResults
{

    @SerializedName("totalResults")
    @Expose
    private Integer totalResults;

    @SerializedName("pageNumber")
    @Expose
    private Integer pageNumber;

    @SerializedName("resultsPerPage")
    @Expose
    private Integer resultsPerPage;
    @SerializedName("trade")
    @Expose
    private List<Trade> trade = new ArrayList<Trade>();


    public List<Trade> getTradeList() {
        return trade;
    }

    public void setTradeList(List<Trade> trade) {
        this.trade = trade;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getResultsPerPage() {
        return resultsPerPage;
    }

    public void setResultsPerPage(Integer resultsPerPage) {
        this.resultsPerPage = resultsPerPage;
    }
}
