package com.naxa.nepal.badapatraapp.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.naxa.nepal.badapatraapp.R;
import com.naxa.nepal.badapatraapp.adapter.SubCat_List_Adapter;
import com.naxa.nepal.badapatraapp.model.SubCat_List_Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SubCatListActivity extends AppCompatActivity {

    private static final String TAG = "SUB_CAT_LIST";

    ProgressDialog mProgressDlg;
    public static final String MyPREFERENCES = "badapatra_catlist";
    SharedPreferences sharedpreferences;
    private boolean setData;

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    ImageView imageView;

    SubCat_List_Adapter ca;
    public static List<SubCat_List_Model> resultCur = new ArrayList<>();
    public static List<SubCat_List_Model> filteredList = new ArrayList<>();

    String text = null;
    JSONArray data = null;
   // String main_id ;
    String main_category_id;

    public static final String EXTRA_NAME = "cheese_name";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_cat_list);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        Intent i = getIntent();
        // Receiving the Data
        main_category_id = i.getStringExtra("main_category_id");
        Log.e("getExtra", main_category_id );
        String main_category_name = i.getStringExtra("main_category_name");

        toolbar.setTitle(main_category_name);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.accent));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        final Drawable upArrow = getResources().getDrawable(R.mipmap.ic_back_icon);
//        upArrow.setColorFilter(getResources().getColor(R.color.accent), PorterDuff.Mode.SRC_ATOP);
//        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        sharedpreferences = this.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_grid);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        //        sharedpreferences = PreferenceManager
//                .getDefaultSharedPreferences(getActivity());
//        setData = sharedpreferences.getBoolean("SET_ENGLISH_ON", true);
//
//        if (setData) {
        mProgressDlg = new ProgressDialog(this);
        mProgressDlg.setMessage("कृपया पर्खनुहोस्...");
        mProgressDlg.setIndeterminate(false);
        mProgressDlg.setCancelable(false);
        mProgressDlg.show();
        createList();



        final GestureDetector mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

        });


        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());


                if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {
//                    Drawer.closeDrawers();
                    int position = recyclerView.getChildPosition(child);


                    Intent intent = new Intent(SubCatListActivity.this, DefaultDetailsActivity.class);

                    intent.putExtra("", resultCur.get(position).getSub_catName());
                    intent.putExtra("", resultCur.get(position).getSub_cat_id());
                    intent.putExtra("", resultCur.get(position).getSub_catThumbnail());
//                    intent.putExtra("project_title_np", resultCur.get(position).dev_title_np);
//                    intent.putExtra("project_desc_np", resultCur.get(position).dev_desc_np);
//
//                    intent.putExtra("project_district_np", resultCur.get(position).district_name_np);
//                    intent.putExtra("project_contractor_np", resultCur.get(position).dev_contractor_np);
//                    intent.putExtra("project_budget_np", resultCur.get(position).dev_budget_np);
//                    intent.putExtra("project_image", resultCur.get(position).mThumbnail);
//
                    startActivity(intent);


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

    private void createList() {
        resultCur.clear();
        jsonParse();
        mProgressDlg.dismiss();
        fillTable();
    }


    public void jsonParse(){

        String main_id=null;

        JSONObject jsonObj = null;
        try {

            text = sharedpreferences.getString("badapatra_catlist", "");
            if (text!= null){
                Log.e("M_JSON", "" + text.toString());
                jsonObj = new JSONObject(text);

                data = jsonObj.getJSONArray("data");

                Log.e("DATA", "" + data.toString());
                for (int i = 0; i < data.length(); i++) {

                    JSONObject c = data.getJSONObject(i);
                    main_id = c.getString("main_category_id");

                    Log.e("SUBCAT_IN_LOOP", "" + c.toString());
                    Log.e("main_id", main_id );

                    if(main_id.equals(main_category_id ) ){
                        SubCat_List_Model newData = new SubCat_List_Model();
                        Log.d(TAG, "jsonParse: if" );
//                newData.set(c.getString("dev_status_id"));
                        newData.setSub_cat_id(c.getString("sub_category_id"));
                        newData.setSub_catName(c.getString("sub_cat_name"));
                        newData.setSub_catThumbnail(c.getString("img_path"));

                        resultCur.add(newData);
                        Log.e("POJO", "" + newData.toString());

                    }
                }


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void fillTable() {

        filteredList = resultCur;
        ca = new SubCat_List_Adapter(this, filteredList);
        recyclerView.setAdapter(ca);

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
