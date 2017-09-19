package com.poscustomer.Adapter;

/**
 * Created by DJ-PC on 5/6/2017.
 */

public class DummyHistoryItem {
    public Integer getPaymentModeIcon() {
        return PaymentModeIcon;
    }

    public void setPaymentModeIcon(Integer paymentModeIcon) {
        PaymentModeIcon = paymentModeIcon;
    }

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getCost() {
        return Cost;
    }

    public void setCost(String cost) {
        Cost = cost;
    }

    private Integer PaymentModeIcon;
    private String DateTime;
    private String OrderId;
    private String Description;
    private String Cost;

}
