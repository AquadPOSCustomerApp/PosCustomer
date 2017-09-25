package com.poscustomer.Application;

import com.poscustomer.Model.GetOffers;
import com.poscustomer.Model.OrderHistory;

/**
 * Created by HP on 9/21/2017.
 */

public class OffersInstance {
    private static final OffersInstance ourInstance = new OffersInstance();

    public static OffersInstance getInstance() {
        return ourInstance;
    }

    private OffersInstance() {
    }

    private GetOffers offersData;

    public static OffersInstance getOurInstance() {
        return ourInstance;
    }

    public GetOffers getOffersData() {
        return offersData;
    }

    public void setOffersData(GetOffers offersData) {
        this.offersData = offersData;
    }

    public GetOffers.Data getOfferDetail() {
        return offerDetail;
    }

    public void setOfferDetail(GetOffers.Data offerDetail) {
        this.offerDetail = offerDetail;
    }

    private GetOffers.Data offerDetail;








}
