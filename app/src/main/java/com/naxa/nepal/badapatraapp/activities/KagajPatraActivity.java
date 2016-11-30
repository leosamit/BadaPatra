package com.naxa.nepal.badapatraapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.naxa.nepal.badapatraapp.R;
import com.naxa.nepal.badapatraapp.adapter.GridSpacingItemDecorator;
import com.naxa.nepal.badapatraapp.adapter.KagajPatra_List_Adapter;

public class KagajPatraActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    private Toolbar toolbar;



    private SharedPreferences wmbPreference;
    private boolean setData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kagaj_patra);


        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_grid_kp);
        mRecyclerView.setHasFixedSize(true);

        toolbar = (Toolbar) findViewById(R.id.toolbar_kp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("कागजपत्र सम्बन्धि");


        // The number of Columns
        mLayoutManager = new GridLayoutManager(this, 1);
        mRecyclerView.setLayoutManager(mLayoutManager);

        int spanCount = 2;
        int spacing = 5;
        boolean includeEdge = true;

      mRecyclerView.addItemDecoration(new GridSpacingItemDecorator(spanCount, spacing, includeEdge));

        mAdapter = new KagajPatra_List_Adapter(this, this);
        mRecyclerView.setAdapter(mAdapter);

        final GestureDetector mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

        });


        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());


                if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {
                    int position = recyclerView.getChildPosition(child);
                    callFragment(position);
                    return true;
                }

                return false;
            }


            @Override
            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }

        });
    }

    private void callFragment(int position) {

//        Fragment mFragment = null;
//        FragmentManager mFragmentManager = getActivity().getSupportFragmentManager();
        String title_english = null;
        String title_nepali = null;
        int color = R.color.colorPrimary;
//        connectonDetector = new ConnectonDettor.isConnectedToInternet();
        switch (position) {
            case 0:
                Intent intent1 = new Intent(this, DefaultDetailsActivity.class);

                intent1.putExtra("title", "नागरिकता सम्बन्धि");
                intent1.putExtra("image1", "नागरिकता सम्बन्धि");
                intent1.putExtra("image2","");
                intent1.putExtra("image3", "नागरिकता सम्बन्धि");
                intent1.putExtra("image4", "नागरिकता सम्बन्धि");
                intent1.putExtra("steps_list", "नागरिकता सम्बन्धि");
                intent1.putExtra("video", "SDZc1kDjpD0");

                startActivity(intent1);
                // title_nepali = getResources().getString(R.string.good_construction_practices_np);
//                title_english = "Introduction";
//                color = R.color.colorPrimary;
                break;


            case 1:
                Intent intent2 = new Intent(this, DefaultDetailsActivity.class);
                startActivity(intent2);
                //  title_nepali = getResources().getString(R.string.worksite_safety_np);
//                title_english = "About FWDC";
//                color = R.color.colorPrimary;
                break;
            case 2:
                Intent intent3 = new Intent(this, DefaultDetailsActivity.class);
                startActivity(intent3);
                // title_nepali = getResources().getString(R.string.dudbc_catalogue_np);
//                title_english = "Socio Economic Status";
//                color = R.color.colorPrimary;
                break;



        }

//        if (mFragment != null) {
//            mFragmentManager.beginTransaction().addToBackStack("home").replace(R.id.containerView, mFragment).commit();
//        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about_us) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
