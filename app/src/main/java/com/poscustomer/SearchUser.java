package com.poscustomer;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;
import com.poscustomer.Adapter.SearchUserAdapter;
import com.poscustomer.Application.MyApp;
import com.poscustomer.Model.UserSearch;
import com.poscustomer.Utils.AppConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchUser extends CustomActivity implements CustomActivity.ResponseCallback {
    private RecyclerView rv_history;
    EditText SearchKey;
    Toolbar toolbar;
    private SearchUserAdapter adapter;
    private ArrayList<UserSearch> horizontalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_user);
       // SearchKey = (EditText) findViewById(R.id.srch_key);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setResponseListener(this);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("");
        rv_history = (RecyclerView) findViewById(R.id.rc_history);
        horizontalList = new ArrayList<>();
        adapter = new SearchUserAdapter(this, horizontalList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_history.setLayoutManager(linearLayoutManager);
        rv_history.setAdapter(adapter);
       /* SearchKey.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (TextUtils.isEmpty(SearchKey.getText().toString())) {
                        SearchKey.setError("Enter Search Key");
                        return false;
                    }
                    performSearch();
                    return true;
                }
                return false;
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        final MenuItem item = menu.findItem(R.id.action_search);

        final SearchView searchView = (SearchView) MenuItemCompat
                .getActionView(item);
        // SearchView.SearchAutoComplete theTextArea =
        // (SearchView.SearchAutoComplete) searchView
        // .findViewById(R.id.search_src_text);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextChange(String newText) {
                // Log.e("onQueryTextChange", "called");
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {

                if (TextUtils.isEmpty(query)) {

                    Toast.makeText(SearchUser.this, "Enter Search Key", Toast.LENGTH_SHORT).show();
                    return false;
                }


                performSearch(query);
                return false;
            }

        });
        // theTextArea.setTextColor(Color.WHITE);
        return true;
    }


    private void performSearch(String key) {

        RequestParams p = new RequestParams();

        p.put("task", "user_search");
        p.put("search_key", key);
        postCall(SearchUser.this, AppConstants.BASE_URL, p, "Searching...", 1);
    }

    @Override
    public void onJsonObjectResponseReceived(JSONObject o, int callNumber) {

        Log.d("response", o.toString());
        if (o.optInt("status") == 1) {
            try {
                JSONArray arr = new JSONObject(o.optString("message")).getJSONArray("data");
                ArrayList<UserSearch> list = new ArrayList<>();
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject oo = arr.getJSONObject(i);
                    UserSearch usrch = new UserSearch();

                    usrch.setName(oo.optString("name"));
                    usrch.setEmail(oo.optString("email"));
                    usrch.setPassword(oo.optString("password"));
                    usrch.setPhone(oo.optString("one"));
                    usrch.setLng(oo.optString("lng"));
                    usrch.setLat(oo.optString("lat"));
                    usrch.setAddress(oo.optString("address"));
                    usrch.setStatus(oo.optString("status"));
                    usrch.setCreated_at(oo.optString("created_at"));
                    usrch.setUpdated_at(oo.optString("updated_at"));
                    list.add(usrch);

                }

                horizontalList.clear();
                horizontalList.addAll(list);
                adapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            MyApp.showMassage(SearchUser.this, o.optString("message"));
        }


    }

    @Override
    public void onJsonArrayResponseReceived(JSONArray a, int callNumber) {

    }

    @Override
    public void onErrorReceived(String error) {

    }
}
