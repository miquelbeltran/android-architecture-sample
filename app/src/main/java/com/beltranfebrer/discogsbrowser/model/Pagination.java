package com.beltranfebrer.discogsbrowser.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by miquel on 22.04.16.
 */
public class Pagination {
    @SerializedName("items")
    private Integer items;
    @SerializedName("page")
    private Integer page;
    @SerializedName("per_page")
    private Integer perPage;
    @SerializedName("urls")
    private Urls paging;

    public Integer getItems() {
        return items;
    }

    public void setItems(Integer items) {
        this.items = items;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    public Urls getPaging() {
        return paging;
    }

    public void setPaging(Urls paging) {
        this.paging = paging;
    }
}
