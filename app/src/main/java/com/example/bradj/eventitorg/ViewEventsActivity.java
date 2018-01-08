package com.example.bradj.eventitorg;

import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.bradj.eventitorg.Model.Adapter.DataAdapter;
import com.example.bradj.eventitorg.Model.Adapter.EventRVAdapter;
import com.example.bradj.eventitorg.Model.Entity.Event;
import com.example.bradj.eventitorg.Model.Entity.EventList;
import com.example.bradj.eventitorg.Model.Service.ApiUtils;
import com.example.bradj.eventitorg.Model.Service.EventService;
import com.example.bradj.eventitorg.Utilities.LoginUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewEventsActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private EventService mService;
    private EventRVAdapter mAdapter;
    private List<Event> eventList;
    private RecyclerView recyclerView;
    private DataAdapter dataAdapter;
    private List<Event> dataArrayList;
    private LoginUtil loginUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_events);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.top_toolbar);
        setSupportActionBar(myToolbar);
//        mService = ApiUtils.getEventService();
//        mRecyclerView = (RecyclerView) findViewById(R.id.rvEvents);
//        mAdapter = new EventRVAdapter(this, new ArrayList<Event>(0), new EventRVAdapter.EventItemListener() {
//
//            @Override
//            public void onEventClick(long id) {
//                Toast.makeText(ViewEventsActivity.this, "Event id is" + id, Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(layoutManager);
//        mRecyclerView.setAdapter(mAdapter);
//        mRecyclerView.setHasFixedSize(true);
//        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
//        mRecyclerView.addItemDecoration(itemDecoration);
//
//        loadEvents();
        loginUtil=LoginUtil.getInstance();
        initViews();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.action_dashboard:

                return true;

            case R.id.action_events:
                Intent myIntent = new Intent(ViewEventsActivity.this, SplashActivity.class);
                startActivity(myIntent);

                return true;

            case R.id.action_log_out:
//
                logOut(item);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    public void loadEvents() {
        eventList = new ArrayList<Event>();
        mService.getEvents().enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {

                if (response.isSuccessful()) {
                    eventList = response.body();

                    mAdapter = new EventRVAdapter(getApplicationContext(), eventList);
                    mRecyclerView.setAdapter(mAdapter);
                    //  mAdapter.updateEvents(response.body());
                    Log.d("ViewEventsActivity", "events loaded from API");
                    Log.d("MyResponse", response.body().get(0).getName());
                } else {
                    int statusCode = response.code();
                    Log.d("ApiStatus", Integer.toString(statusCode));
                    // handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                //showErrorMessage();
                Log.d("Errors", t.getMessage());
                Log.d("ViewEventsActivity", "error loading from API");


            }
        });
    }

    public void loadEvent() {
        mService.getEvent().enqueue(new Callback<Event>() {
            @Override
            public void onResponse(Call<Event> call, Response<Event> response) {

                if (response.isSuccessful()) {

                    // mAdapter.updateEvents(response.body().getItems());
                    Log.d("ViewEventsActivity", "events loaded from API");

                    Log.d("Event", response.body().getName());
                } else {
                    int statusCode = response.code();
                    Log.d("ApiStatus", Integer.toString(statusCode));
                    // handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<Event> call, Throwable t) {
                //showErrorMessage();
                Log.d("Errors", t.getMessage());
                Log.d("ViewEventsActivity", "error loading from API");


            }
        });
    }

    public void logOut(MenuItem item) {
        // 1. Instantiate an AlertDialog.Builder with its constructor
        Log.i("info","Logout clicked");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

// 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage(R.string.logout_confirm)
                .setTitle(R.string.log_out);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                loginUtil.setLoggedIn(getApplicationContext(),false);
                Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
                dialog.dismiss();
            }
        });
// Set other dialog properties

// Create the AlertDialog

// 3. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();
        builder.show();

    }


    private void initViews() {
        recyclerView = (RecyclerView) findViewById(R.id.rvEvents);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        loadJSON();
    }

    private void loadJSON() {
        dataArrayList = new ArrayList<>();
        mService = ApiUtils.getEventService();
        Call<List<Event>> call = mService.getEvents();
        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                dataArrayList = response.body();
                dataAdapter = new DataAdapter(getApplicationContext(), dataArrayList);
                recyclerView.setAdapter(dataAdapter);
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });
    }
    public void onClickViewEvent (View view) {
        Intent myIntent = new Intent(ViewEventsActivity.this, EventActivity.class);
        startActivity(myIntent);
    }
}
