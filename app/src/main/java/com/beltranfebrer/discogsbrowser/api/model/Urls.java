package com.beltranfebrer.discogsbrowser.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by miquel on 22.04.16.
 */
public class Urls {
    @SerializedName("next")
    private String next;
    @SerializedName("pages")
    private int pages;
    @SerializedName("last")
    private String last;

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }
}
