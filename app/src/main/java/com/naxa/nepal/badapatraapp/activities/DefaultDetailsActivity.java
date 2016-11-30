package com.naxa.nepal.badapatraapp.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.naxa.nepal.badapatraapp.R;
import com.naxa.nepal.badapatraapp.adapter.ExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DefaultDetailsActivity extends Activity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_details);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Steps");
        listDataHeader.add("Docs Required");
        listDataHeader.add("Office to visit");
        listDataHeader.add("Resource Required");

        // Adding child data
        List<String> steps = new ArrayList<String>();
        steps.add("One");
        steps.add("Two");
        steps.add("ThereekasklaskaknasjkkdnjajsahdajaasmaKNMASDBA HAD A HJJ  JAKDSAJKD AKSSSSSSSSSSSSSSSS DNASJSADJASDASSJASshakjsda");
        steps.add("Two");

        List<String> docsRequired = new ArrayList<String>();
        docsRequired.add("The Conjuring");
        docsRequired.add("Despicable Me 2");
        docsRequired.add("Turbo");

        List<String> officeToVisit = new ArrayList<String>();
        officeToVisit.add("2 Guns");
        officeToVisit.add("The Smurfs 2");
        officeToVisit.add("The Spectacular Now");


        listDataChild.put(listDataHeader.get(0), steps); // Header, Child data
        listDataChild.put(listDataHeader.get(1), docsRequired);
        listDataChild.put(listDataHeader.get(2), officeToVisit);
    }
}
