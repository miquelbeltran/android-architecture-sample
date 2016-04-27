package com.beltranfebrer.discogsbrowser.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by miquel on 22.04.16.
 */
public class Urls {
    @SerializedName("next")
    private String next;
    @SerializedName("last")
    private String last;

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }
}
