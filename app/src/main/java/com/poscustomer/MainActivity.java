package com.poscustomer;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.mancj.slideup.SlideUp;
import com.poscustomer.Adapter.CustomAdapter;
import com.poscustomer.Adapter.DAdapter;
import com.poscustomer.Application.MyApp;
import com.poscustomer.Application.OffersInstance;
import com.poscustomer.Application.SingleInstance;
import com.poscustomer.Fragments.FragmentDrawer;
import com.poscustomer.Model.DummyData;
import com.poscustomer.Model.DummyListItem;
import com.poscustomer.Model.GetOffers;
import com.poscustomer.Model.ListItem;
import com.poscustomer.Model.OrderHistory;
import com.poscustomer.Model.RestUser;
import com.poscustomer.Utils.AppConstants;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends CustomActivity implements DAdapter.ItemClickCallback, CustomActivity.ResponseCallback, FragmentDrawer.FragmentDrawerListener,
        GoogleApiClient.ConnectionCallbacks, LocationProvider.LocationCallback, LocationProvider.PermissionCallback
        , GoogleApiClient.OnConnectionFailedListener, LocationListener, ResultCallback<LocationSettingsResult> {

    private RecyclerView recView;
    private DAdapter adapter;
    private GetOffers listdata;
    private Toolbar toolbar_title;
    private FragmentDrawer drawerFragment;
    protected GoogleApiClient mGoogleApiClient;
    protected boolean mRequestingLocationUpdates;
    public static final int REQUEST_PERMISSION_LOCATION = 10;
    protected LocationRequest mLocationRequest;
    protected LocationSettingsRequest mLocationSettingsRequest;
    protected static final int REQUEST_CHECK_SETTINGS = 0x1;
    public static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;
    public static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS =
            UPDATE_INTERVAL_IN_MILLISECONDS / 2;
    Location newLocation = null;
    boolean isFirstSet = false;
    private SlideUp slideUp;
    private LinearLayout llm;
    RatingBar ratingBar;

    ArrayList<DummyListItem> dummyListItems;
    ListView purchaseHistory;
    private static CustomAdapter customAdapter;
    Button Submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getAllOffers();
        toolbar_title = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar_title);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        setResponseListener(this);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        TextView mTitle = (TextView) toolbar_title.findViewById(R.id.toolbar_title);
        mTitle.setText("Offers");
        actionBar.setTitle("");

        drawerFragment = (FragmentDrawer) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout), toolbar_title);
        drawerFragment.setDrawerListener(this);
        llm = (LinearLayout) findViewById(R.id.prev_purchase);
        ratingBar = (RatingBar) findViewById(R.id.rating);

        Submit = (Button) findViewById(R.id.prev_purchase_submit);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ListItem item = (ListItem) listdata.get(p);
                Intent i = new Intent(MainActivity.this, CoordinatorActivity.class);
                startActivity(i);
                slideUp.hide();
            }
        });
        buildGoogleApiClient();
        //step 2
        createLocationRequest();
        //step 3
        buildLocationSettingsRequest();
        checkLocationSettings();
        setupUiElements();

        purchaseHistory = (ListView) findViewById(R.id.list_purchase);
        dummyListItems = new ArrayList<>();
        dummyListItems.add(new DummyListItem("29September1992 10:00 Pm", " Rs. 17,000.00"));
        dummyListItems.add(new DummyListItem("29September1992 10:00 Pm", " Rs. 17,000.00"));
        dummyListItems.add(new DummyListItem("29September1992 10:00 Pm", " Rs. 17,000.00"));
        dummyListItems.add(new DummyListItem("29September1992 10:00 Pm", " Rs. 17,000.00"));
        dummyListItems.add(new DummyListItem("29September1992 10:00 Pm", " Rs. 17,000.00"));
        customAdapter = new CustomAdapter(dummyListItems, getApplicationContext());
        purchaseHistory.setAdapter(customAdapter);

      //  listdata = (ArrayList) DummyData.getListData();
        /*rec_get_al_offers = (RecyclerView) findViewById(R.id.rec_get_al_offers);

        //listdata = SingleInstance.getInstance().getHistoryData();


        rec_get_al_offers.setLayoutManager(new LinearLayoutManager(this));
        adapter = new GetOfferAdapter(listdata, this);
        rec_get_al_offers.setAdapter(adapter);*/

        recView = (RecyclerView) findViewById(R.id.rec_list);


        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(createHelperCallback());
        itemTouchHelper.attachToRecyclerView(recView);


        slideUp = new SlideUp.Builder(llm)
                .withStartState(SlideUp.State.HIDDEN)
                .withStartGravity(Gravity.BOTTOM)
                .build();
        slideUp = new SlideUp.Builder(llm)
                .withListeners(new SlideUp.Listener() {
                    @Override
                    public void onSlide(float percent) {
                    }

                    @Override
                    public void onVisibilityChanged(int visibility) {
                        if (visibility == View.GONE) {
                        }
                    }
                })
                .withStartGravity(Gravity.BOTTOM)
                .withGesturesEnabled(false)
                .withStartState(SlideUp.State.HIDDEN)
                .build();

        RequestParams p = new RequestParams();
        p.put("task", "get_all_order");
        p.put("user_id", MyApp.getApplication().readUser().getData().getApp_user_id());
        postCall(getContext(), AppConstants.BASE_URL, p, "Loading...", 2);



    }


    public void getAllOffers() {
        RequestParams q = new RequestParams();
        q.put("task", "get_all_offers");
        q.put("restaurant_id", "5");

        postCall(getContext(), AppConstants.BASE_URL, q, "", 3);
    }

    private void checkLocationSettings() {
        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(
                        mGoogleApiClient,
                        mLocationSettingsRequest
                );
        result.setResultCallback(this);
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (mGoogleApiClient.isConnected()) {
            stopLocationUpdates();
        }
    }

    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this)
                .setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        mRequestingLocationUpdates = false;
                        //   setButtonsEnabledState();
                    }
                });
    }

    private void setupUiElements() {
    }

    protected synchronized void buildGoogleApiClient() {
        Log.i("TAG", "Building GoogleApiClient");
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();

        mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        // application will never receive updates faster than this value.
        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);

        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    protected void buildLocationSettingsRequest() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        mLocationSettingsRequest = builder.build();
    }


    private ItemTouchHelper.Callback createHelperCallback() {
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP |
                ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                moveItem(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                deleteItem(viewHolder.getAdapterPosition());

            }
        };
        return simpleItemTouchCallback;
    }

//    private void addItemToList() {
//        ListItem item = DummyData.getRandomListItem();
//        listdata.add(item);
//        adapter.notifyItemInserted(listdata.indexOf(item));
//    }

    private void moveItem(int oldPos, int newPos) {

        //ListItem item = (ListItem) listdata.get(oldPos);
        //listdata.remove(oldPos);
      //  listdata.add(newPos, item);
        adapter.notifyItemMoved(oldPos, newPos);
    }

    private void deleteItem(final int position) {
       // listdata.remove(position);
        adapter.notifyItemRemoved(position);
    }


    @Override
    public void onItemClick(int p) {
     //   ListItem item = (ListItem) listdata.get(p);
        slideUp.show();
        Toast.makeText(this, "Item CLiked" + p, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSecondaryIconClick(int p) {
        /*ListItem item = (ListItem) listdata.get(p);
        //update our data
        if (item.isFavorite()) {
            item.setFavorite(false);
        } else {
            item.setFavorite(true);
        }
        //pass new data to adapter and update
        adapter.setListData(listdata);
        adapter.notifyDataSetChanged();*/

    }

    @Override
    public void onLocationChanged(Location location) {
        try {
            if (location != null && !isFirstSet) {
                newLocation = location;
                isFirstSet = true;
                updateUserProfile(location);
            }
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    @Override
    protected void onStart() {
        super.onStart();

        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int resultCode = googleAPI.isGooglePlayServicesAvailable(this);
        if (resultCode == ConnectionResult.SUCCESS) {
            mGoogleApiClient.connect();
        } else {
            googleAPI.getErrorDialog(this, resultCode, 0);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mGoogleApiClient.isConnected() && mRequestingLocationUpdates) {
            startLocationUpdates();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i("TAG", "Connection suspended");
        mGoogleApiClient.connect();

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i("TAG", "Connection failed: ConnectionResult.getErrorCode() = " + connectionResult.getErrorCode());
    }

    @Override
    public void onResult(@NonNull LocationSettingsResult locationSettingsResult) {
        final Status status = locationSettingsResult.getStatus();
        switch (status.getStatusCode()) {
            case LocationSettingsStatusCodes.SUCCESS:
                Log.i("TAG", "All location settings are satisfied.");
                startLocationUpdates();
                break;
            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                Log.i("TAG", "Location settings are not satisfied. Show the user a dialog to" +
                        "upgrade location settings ");

                try {
                    status.startResolutionForResult(MainActivity.this, REQUEST_CHECK_SETTINGS);
                } catch (IntentSender.SendIntentException e) {
                    Log.i("TAG", "PendingIntent unable to execute request.");
                }
                break;
            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                Log.i("TAG", "Location settings are inadequate, and cannot be fixed here. Dialog " +
                        "not created.");
                break;
        }

    }

    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_PERMISSION_LOCATION);
        } else {
            goAndDetectLocation();
        }
    }

    public void goAndDetectLocation() {
        if (ActivityCompat.checkSelfPermission
                (this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates
                (mGoogleApiClient, mLocationRequest, MainActivity.this).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(Status status) {
                mRequestingLocationUpdates = true;
            }
        });
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            newLocation = mLastLocation;
            Log.d("TAG", "ON connected");
        } else
            try {
                LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, MainActivity.this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        try {
            LocationRequest mLocationRequest = new LocationRequest();
            mLocationRequest.setInterval(10000);
            mLocationRequest.setFastestInterval(5000);
            mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onJsonObjectResponseReceived(JSONObject o, int callNumber) {
        if (callNumber == 1) {
            String jo = o.toString();
        } else if (callNumber == 2) {
            Log.d("response", o.toString());

            OrderHistory u = new Gson().fromJson(o.toString(), OrderHistory.class);
           // OrderHistory p = new Gson().fromJson(o.toString(), OrderItem.class);
            SingleInstance.getInstance().setHistoryData(u);
          //  DetailSingleInstance.getInstance().getItemData(p);
            int size = u.getData().size();
           // MyApp.getApplication().writeUser(u);
        }else if(callNumber == 3){
            Log.d("response", o.toString());
            GetOffers g = new Gson().fromJson(o.toString(), GetOffers.class);
            OffersInstance.getInstance().setOffersData(g);

            listdata = OffersInstance.getInstance().getOffersData();
            recView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new DAdapter(listdata, this);
            recView.setAdapter(adapter);
            adapter.SetItemClickCallback(this);
        }
    }

    @Override
    public void onJsonArrayResponseReceived(JSONArray a, int callNumber) {
    }

    @Override
    public void onErrorReceived(String error) {
    }

    @Override
    public void handleNewLocation(Location location) {
        if (location != null && !isFirstSet) {
            newLocation = location;
            isFirstSet = true;
            updateUserProfile(location);
        }
    }

    public void updateUserProfile(Location location) {
        RequestParams p = new RequestParams();
        p.put("task", "update_user_profile");
        p.put("device_token", MyApp.getSharedPrefString(AppConstants.DEVICE_TOKEN));
        p.put("lat", location.getLatitude());
        p.put("lon", location.getLongitude());
        p.put("deviceType", "Android");
        p.put("user_id", MyApp.getApplication().readUser().getData().getApp_user_id());
        postCall(getContext(), AppConstants.BASE_URL, p, "", 1);
    }

    private Context getContext() {
        return MainActivity.this;
    }

    @Override
    public void handleManualPermission() {
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        if (position == 3) {
            Intent i = (new Intent(MainActivity.this, Login_Activity.class));
            startActivity(i);
            finishAffinity();
            MyApp.setStatus(AppConstants.IS_LOGGED, false);
        } else if (position == 1) {
            Intent i = new Intent(this, GetAllOffersActivity.class);
            startActivity(i);
        } else if (position == 0) {
            startActivity(new Intent(getContext(), ProfileActivity.class));
        } else if (position == 2) {
            startActivity(new Intent(getContext(), HistoryActivity.class));
        }
    }
}
