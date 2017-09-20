package com.poscustomer.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DJ-PC on 4/3/2017.
 */





public class OrderHistory implements Serializable {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    private String status;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    // private Data data;
    private List<Data> data;



    public class Data  implements Serializable {
        private String order_id;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getRestaurant() {
            return restaurant;
        }

        public void setRestaurant(String restaurant) {
            this.restaurant = restaurant;
        }

        public String getPay_mode() {
            return pay_mode;
        }

        public void setPay_mode(String pay_mode) {
            this.pay_mode = pay_mode;
        }

        public String getTotal_cost() {
            return total_cost;
        }

        public void setTotal_cost(String total_cost) {
            this.total_cost = total_cost;
        }

        public String getTotal_discount() {
            return total_discount;
        }

        public void setTotal_discount(String total_discount) {
            this.total_discount = total_discount;
        }

        public String getOffer_discount() {
            return offer_discount;
        }

        public void setOffer_discount(String offer_discount) {
            this.offer_discount = offer_discount;
        }

        public String getGrand_total() {
            return grand_total;
        }

        public void setGrand_total(String grand_total) {
            this.grand_total = grand_total;
        }

        public String getPaid() {
            return paid;
        }

        public void setPaid(String paid) {
            this.paid = paid;
        }

        public String getCancelled() {
            return cancelled;
        }

        public void setCancelled(String cancelled) {
            this.cancelled = cancelled;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public List<OrderItems> getOrder_items() {
            return order_items;
        }

        public void setOrder_items(List<OrderItems> order_items) {
            this.order_items = order_items;
        }

        private String restaurant;
        private String pay_mode;
        private String total_cost;
        private String total_discount;
        private String offer_discount;
        private String grand_total;
        private String paid;

        private String cancelled;
        private String created_at;
        private List<OrderItems> order_items;
        public class OrderItems implements Serializable{
            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getItem_quantity() {
                return item_quantity;
            }

            public void setItem_quantity(String item_quantity) {
                this.item_quantity = item_quantity;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getImgLink() {
                return imgLink;
            }

            public void setImgLink(String imgLink) {
                this.imgLink = imgLink;
            }

            private String name;
            private String item_quantity;
            private String price;
            private String description;
            private String imgLink;


        }


    }
}
