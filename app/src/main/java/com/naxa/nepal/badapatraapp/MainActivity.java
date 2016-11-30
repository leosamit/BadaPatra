package com.naxa.nepal.badapatraapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
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

import com.naxa.nepal.badapatraapp.activities.SubCatListActivity;
import com.naxa.nepal.badapatraapp.adapter.Cat_SubcatListAdapter;
import com.naxa.nepal.badapatraapp.model.Cat_SubcatListModel;
import com.naxa.nepal.badapatraapp.model.UrlClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;


public class MainActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeContainer;

    private static final String TAG = "MAINActivity";


    ConnectivityManager connectivityManager;
    NetworkInfo networkInfo;

    ProgressDialog mProgressDlg;
    public static final String MyPREFERENCES = "badapatra_catlist";
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;

    String text = null;
    String jsonToSend = null;
    int main_id= 1 ;


    private Toolbar toolbar;

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
   // RecyclerView.LayoutManager mLayoutManager;
    ImageView imageView;

    /*

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
     */

    Cat_SubcatListAdapter ca;
//   RecyclerView.Adapter ca;
    public static List<Cat_SubcatListModel> resultCur = new ArrayList<>();
    public static List<Cat_SubcatListModel> filteredList = new ArrayList<>();


    //private SharedPreferences sharedpreferences;
    private boolean setData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("नागरिक वडापत्र ");
        toolbar.setTitleTextColor(getResources().getColor(R.color.accent));

       // Check internet connection
        connectivityManager = (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
        networkInfo = connectivityManager.getActiveNetworkInfo();


        sharedpreferences = this.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_grid);

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        convertDataToJson();
        createList();
        fillTable();


        //Swipe Refresh Action
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (networkInfo != null && networkInfo.isConnected()) {
                    editor.clear();
                    editor.commit();
                   editor.apply();
                    convertDataToJson();
                    CatSubcatListService restApi = new CatSubcatListService();
                    restApi.execute();
                    fillTable();
//                    swipeContainer.setRefreshing(false);


                } else {
                    final View coordinatorLayoutView = findViewById(R.id.main_content);
                    Snackbar.make(coordinatorLayoutView, "ईन्टरनेट कनेक्सन छैन । ", Snackbar.LENGTH_LONG)
                            .setAction("Retry", null).show();
                }

            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light);




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
                    int position = recyclerView.getChildPosition(child);
                    //callFragment(position);
                    Intent intent = new Intent (MainActivity.this, SubCatListActivity.class);
                    intent.putExtra("main_category_id",resultCur.get(position).getMain_cat_id());
                    intent.putExtra("main_category_name",resultCur.get(position).getMain_catName());
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



    // data convert
    public void convertDataToJson() {
        //function in the activity that corresponds to the layout button

        try {

            JSONObject header = new JSONObject();

            header.put("token", "bf5d483811");
            jsonToSend = header.toString();

            Log.e("token: ", jsonToSend);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    private void createList() {
        resultCur.clear();

//        if (setData) {
        mProgressDlg = new ProgressDialog(this);
        mProgressDlg.setMessage("कृपया पर्खनुहोस्...");
        mProgressDlg.setIndeterminate(false);
        mProgressDlg.setCancelable(false);
//        mProgressDlg.show();

        CatSubcatListService restApi = new CatSubcatListService();
        restApi.execute();

//        } else {
//
//            mProgressDlg = new ProgressDialog(getActivity());
//            mProgressDlg.setMessage("Loading please Wait...");
//            mProgressDlg.setIndeterminate(false);
//            mProgressDlg.show();
//            PoticianListService restApi = new PoticianListService();
//            restApi.execute();
//        }


    }

    private class CatSubcatListService extends AsyncTask<String, Void, String> {
        JSONArray data = null;

        protected String getASCIIContentFromEntity(HttpURLConnection entity)
                throws IllegalStateException, IOException {
            InputStream in = (InputStream) entity.getContent();

            StringBuffer out = new StringBuffer();
            int n = 1;
            while (n > 0) {
                byte[] b = new byte[4096];
                n = in.read(b);

                if (n > 0)
                    out.append(new String(b, 0, n));
            }

            return out.toString();
        }

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            String text = "";
//            editor.clear();

            if (sharedpreferences.getString("badapatra_catlist", "").trim().isEmpty()) {
                if (networkInfo != null && networkInfo.isConnected()) {
//
                    text = POST(UrlClass.URL_CATEGORY_LIST);
                    editor.putString("badapatra_catlist", text);
                    editor.commit();
                } else {
                    final View coordinatorLayoutView = findViewById(R.id.main_content);
                    Snackbar.make(coordinatorLayoutView, "ईन्टरनेट कनेक्सन छैन । ", Snackbar.LENGTH_LONG)
                            .setAction("Retry", null).show();
                }
            } else {
                text = sharedpreferences.getString("badapatra_catlist", "");
                Log.e("SHARED: ",text.toString() );
            }


            JSONArray list;
            String txtDisp = null, main_category;
            ArrayList<String> question = new ArrayList<>();
            try {


                JSONObject jsonObj = new JSONObject(text);
                data = jsonObj.getJSONArray("data");

                Log.e("DATA", "" + data.toString());


                for (int i = 0; i < data.length(); i++) {

                    Log.e("DATA_LENGTH: ",""+ data.length() );

                    JSONObject c = data.getJSONObject(i);

                    Cat_SubcatListModel newData = new Cat_SubcatListModel();


                    // for(int j=0 ; j!=main_id ; j++ ) {
                    main_category = c.getString("main_category_id");
                    int main_cat_id= Integer.parseInt(main_category);


                  //  main_id= Integer.parseInt(main_category);

                    Log.e("main_cat_id: ",""+main_cat_id );
//                    Log.e("main_id: ",""+main_id );


                    if(main_cat_id == main_id ){

                        newData.setMain_cat_id(c.getString("main_category_id"));
                        newData.setMain_catName(c.getString("name"));
                        newData.setMain_catThumbnail(c.getString("img_main"));

//
                    newData.setSub_cat_id(c.getString("sub_category_id"));
                    newData.setSub_catName(c.getString("sub_cat_name"));
                    newData.setSub_catThumbnail(c.getString("img_path"));

                        resultCur.add(newData);
                        main_id= main_id+1;
                        Log.e("main_id_if",""+main_id );

                    }


                }
            } catch (Exception e) {
                return e.getLocalizedMessage();
            }

            return text.toString();
        }


        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            //Log.e("ONPOSTEXECUTE", "ONPOST");
            if ((mProgressDlg != null) && mProgressDlg.isShowing()) {
                mProgressDlg.dismiss();
            }
            if (result != null) {
                fillTable();
                swipeContainer.setRefreshing(false);
            }

        }

        public String POST(String myurl) {

            URL url;
            String response = "";
            try {
                url = new URL(myurl);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);


                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("data", jsonToSend);


                Log.e("token: ", jsonToSend);
                String query = builder.build().getEncodedQuery();
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                int responseCode = conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    String line;
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    while ((line = br.readLine()) != null) {
                        response += line;
                    }
                } else {
                    response = "";


                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return response;
        }

    }


    public void fillTable() {

        filteredList = resultCur;
        ca = new Cat_SubcatListAdapter(this, filteredList);
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
