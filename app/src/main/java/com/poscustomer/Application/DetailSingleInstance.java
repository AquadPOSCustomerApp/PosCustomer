package com.poscustomer.Application;

import com.poscustomer.Model.OrderHistory;

/**
 * Created by HP on 9/22/2017.
 */

public class DetailSingleInstance {
    private static final DetailSingleInstance ourInstance = new DetailSingleInstance();

    public static DetailSingleInstance getInstance() {
        return ourInstance;
    }

    private DetailSingleInstance() {
    }

    public static DetailSingleInstance getOurInstance() {
        return ourInstance;
    }

    public OrderHistory.Data getItemData() {
        return ItemData;
    }

    public void setItemData(OrderHistory.Data itemData) {
        ItemData = itemData;
    }

    private OrderHistory.Data ItemData;

}
