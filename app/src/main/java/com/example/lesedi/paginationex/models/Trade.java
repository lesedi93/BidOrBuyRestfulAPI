package com.example.lesedi.paginationex.models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Trade
{
    @SerializedName("images")
    @Expose
    private List<String> images = new ArrayList<String>();

    @SerializedName("amount")
    @Expose
    private double amount;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("userId")
    @Expose
    private int userId;

    @SerializedName("hotSelling")
    @Expose
    private boolean hotSelling;

    @SerializedName("categoryBreadCrumb")
    @Expose
    private String categoryBreadCrumb;

    @SerializedName("userAlias")
    @Expose
    private String userAlias;

    @SerializedName("closeTime")
    @Expose
    private String closeTime;


    @SerializedName("homeCategoryId")
    @Expose
    private int homeCategoryId;


    @SerializedName("location")
    @Expose
    private String location;

    @SerializedName("openTime")
    @Expose
    private String openTime;


    @SerializedName("tradeId")
    @Expose
    private long tradeId;

    @SerializedName("status")
    @Expose
    private boolean status;


    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isHotSelling() {
        return hotSelling;
    }

    public void setHotSelling(boolean hotSelling) {
        this.hotSelling = hotSelling;
    }

    public String getCategoryBreadCrumb() {
        return categoryBreadCrumb;
    }

    public void setCategoryBreadCrumb(String categoryBreadCrumb) {
        this.categoryBreadCrumb = categoryBreadCrumb;
    }

    public String getUserAlias() {
        return userAlias;
    }

    public void setUserAlias(String userAlias) {
        this.userAlias = userAlias;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public int getHomeCategoryId() {
        return homeCategoryId;
    }

    public void setHomeCategoryId(int homeCategoryId) {
        this.homeCategoryId = homeCategoryId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public long getTradeId() {
        return tradeId;
    }

    public void setTradeId(long tradeId) {
        this.tradeId = tradeId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
