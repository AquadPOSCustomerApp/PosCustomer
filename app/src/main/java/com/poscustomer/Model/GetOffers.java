package com.poscustomer.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DJ-PC on 4/3/2017.
 */

/*


         : "1"
         : "Test12"
         : "test"
         : "150.00"
         : "30.00"
         : "60.00"
         : null
         : "50"
         : "6"
         : "50"
         : "30"
         : "20"
         : "submitted"
         : "1"
         : "2017-07-06 08:05:00"
         : null
         : "Spice"
*/


public class GetOffers implements Serializable {
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    private String message;
    private List<Data> data;

    public class Data implements Serializable {
        private String offer_id;
        private String restaurant_id;
        private String offer_name;
        private String offer_description;
        private String offer_price;
        private String discount_value;
        private String actual_rate;
        private String offer_image;
        private String total_offer_count;
        private String max_per_user;
        private String total_likes;
        private String remaining_count;

        public String getOffer_id() {
            return offer_id;
        }

        public void setOffer_id(String offer_id) {
            this.offer_id = offer_id;
        }

        public String getRestaurant_id() {
            return restaurant_id;
        }

        public void setRestaurant_id(String restaurant_id) {
            this.restaurant_id = restaurant_id;
        }

        public String getOffer_name() {
            return offer_name;
        }

        public void setOffer_name(String offer_name) {
            this.offer_name = offer_name;
        }

        public String getOffer_description() {
            return offer_description;
        }

        public void setOffer_description(String offer_description) {
            this.offer_description = offer_description;
        }

        public String getOffer_price() {
            return offer_price;
        }

        public void setOffer_price(String offer_price) {
            this.offer_price = offer_price;
        }

        public String getDiscount_value() {
            return discount_value;
        }

        public void setDiscount_value(String discount_value) {
            this.discount_value = discount_value;
        }

        public String getActual_rate() {
            return actual_rate;
        }

        public void setActual_rate(String actual_rate) {
            this.actual_rate = actual_rate;
        }

        public String getOffer_image() {
            return offer_image;
        }

        public void setOffer_image(String offer_image) {
            this.offer_image = offer_image;
        }

        public String getTotal_offer_count() {
            return total_offer_count;
        }

        public void setTotal_offer_count(String total_offer_count) {
            this.total_offer_count = total_offer_count;
        }

        public String getMax_per_user() {
            return max_per_user;
        }

        public void setMax_per_user(String max_per_user) {
            this.max_per_user = max_per_user;
        }

        public String getTotal_likes() {
            return total_likes;
        }

        public void setTotal_likes(String total_likes) {
            this.total_likes = total_likes;
        }

        public String getRemaining_count() {
            return remaining_count;
        }

        public void setRemaining_count(String remaining_count) {
            this.remaining_count = remaining_count;
        }

        public String getRate_after_discount() {
            return rate_after_discount;
        }

        public void setRate_after_discount(String rate_after_discount) {
            this.rate_after_discount = rate_after_discount;
        }

        public String getOffer_status() {
            return offer_status;
        }

        public void setOffer_status(String offer_status) {
            this.offer_status = offer_status;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private String rate_after_discount;
        private String offer_status;
        private String status;
        private String created_at;
        private String updated_at;
        private String name;
    }
}
