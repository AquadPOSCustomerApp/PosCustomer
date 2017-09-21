package com.poscustomer.Application;

import com.poscustomer.Model.OrderHistory;

/**
 * Created by HP on 9/21/2017.
 */

public class SingleInstance {
    private static final SingleInstance ourInstance = new SingleInstance();

    public static SingleInstance getInstance() {
        return ourInstance;
    }

    private SingleInstance() {
    }

    private OrderHistory historyData;

    public OrderHistory getHistoryData() {
        return historyData;
    }

    public void setHistoryData(OrderHistory historyData) {
        this.historyData = historyData;
    }
}
