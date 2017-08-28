package com.poscustomer.Model;

/**
 * Created by DJ-PC on 5/6/2017.
 */

public class DummyListItem {
    String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    String cost;
    public DummyListItem(String time, String cost){
        this.time=time;
        this.cost=cost;
    }

}
