package com.poscustomer.Adapter;


import com.poscustomer.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Abhishek on 29-03-2017.
 */

public class DummyHistoryData {
    private static final Integer[] PaymentModeIcon = {R.drawable.money,R.drawable.money,R.drawable.paytm_icon,R.drawable.money,R.drawable.paytm_icon};
    private static final String[] DateTime = {"Sat, Mar 18, 09:14 PM","Sat, Mar 18, 09:14 PM","Sat, Mar 18, 09:14 PM","Sat, Mar 18, 09:14 PM","Sat, Mar 18, 09:14 PM"};
    private static final String[] OrderId = {"Order_45","Order_45","Order_45","Order_45","Order_45"};
    private static final String[] Description = {"Men Hair cut & wash includes head massage","Men Hair cut & wash includes head massage","Men Hair cut & wash includes head massage","Men Hair cut & wash includes head massage","Men Hair cut & wash includes head massage"};
    private static final String[] Cost = {"Rs 114","Rs 114","Rs 114","Rs 114","Rs 114"};



    public static List<DummyHistoryItem> getListData() {
        List<DummyHistoryItem> data = new ArrayList<>();


            for (int x = 0; x < OrderId.length; x++) {
                DummyHistoryItem item = new DummyHistoryItem();
                item.setOrderId(OrderId[x]);
                item.setPaymentModeIcon(PaymentModeIcon[x]);
                item.setDateTime(DateTime[x]);
                item.setDescription(Description[x]);
                item.setCost(Cost[x]);

                data.add(item);
            }

        return (data);
    }

    public static DummyHistoryItem getRandomListItem() {
        int rand = new Random().nextInt(5);

        DummyHistoryItem item = new DummyHistoryItem();
        item.setOrderId(OrderId[rand]);
        item.setPaymentModeIcon(PaymentModeIcon[rand]);
        item.setDateTime(DateTime[rand]);
        item.setDescription(Description[rand]);
        item.setCost(Cost[rand]);
        return item;
    }
}
