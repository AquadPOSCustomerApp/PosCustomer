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
    private OrderHistory.Data historyDetails;

    public OrderHistory.Data getHistoryDetails() {
        return historyDetails;
    }

    public void setHistoryDetails(OrderHistory.Data historyDetails) {
        this.historyDetails = historyDetails;
    }

    public OrderHistory getHistoryData() {
        return historyData;
    }

    public void setHistoryData(OrderHistory historyData) {
        this.historyData = historyData;
    }
}
