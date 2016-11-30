package com.naxa.nepal.badapatraapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.naxa.nepal.badapatraapp.R;
import com.naxa.nepal.badapatraapp.model.Constants;
import com.naxa.nepal.badapatraapp.model.Home_List_Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 11/24/2016.
 */

public class KagajPatra_List_Adapter  extends RecyclerView.Adapter<KagajPatra_List_Adapter.ViewHolder> {
    List<Home_List_Model> mItems;
    Activity activity;
    String[] colorcode = {
            "#ffffff", "#ffffff",
            "#ffffff", "#ffffff",
            "#ffffff", "#ffffff"};

    public KagajPatra_List_Adapter(Context context, Activity activity) {
        super();
        mItems = new ArrayList<Home_List_Model>();
        Home_List_Model species = new Home_List_Model();
        String[] titleGrid;

        this.activity = activity;
        final SharedPreferences wmbPreference = PreferenceManager
                .getDefaultSharedPreferences(context);
        final boolean setData = wmbPreference.getBoolean("SET_ENGLISH_ON", true);

//        if (setData) {
//            titleGrid = Constants.menuNepali;
//        } else {
        titleGrid = Constants.listKagajPatra;
        // }

        species = new Home_List_Model();
        species.setName(titleGrid[0]);
//        species.setThumbnail(R.drawable.bajhang1);
        mItems.add(species);

        species = new Home_List_Model();
        species.setName(titleGrid[1]);
//        species.setThumbnail(R.drawable.bikash_aayog_barema);
        mItems.add(species);

        species = new Home_List_Model();
        species.setName(titleGrid[2]);
//        species.setThumbnail(R.drawable.hamro_sudur_paschhim);
        mItems.add(species);

    }

    @Override
    public KagajPatra_List_Adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_view_list_row, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Home_List_Model nature = mItems.get(i);


        Resources r = activity.getResources();
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, r.getDisplayMetrics());
        //this change height of rcv
        DisplayMetrics displaymetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.height = (height - px) / 7; //height recycleviewer

       viewHolder.cardViewGridView.setLayoutParams(params);
        viewHolder.tvspecies.setText(nature.getName());
        //  viewHolder.imgThumbnail.setImageResource(nature.getThumbnail());
        viewHolder.cardViewGridView.setCardBackgroundColor(Color.parseColor(colorcode[i]));
    }

    @Override
    public int getItemCount() {

        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgThumbnail;
        public TextView tvspecies;
        public CardView cardViewGridView;

        public ViewHolder(View itemView) {
            super(itemView);
            //   imgThumbnail = (ImageView) itemView.findViewById(R.id.img_thumbnail);
            tvspecies = (TextView) itemView.findViewById(R.id.textView_list_item);
           cardViewGridView = (CardView) itemView.findViewById(R.id.cardview_list_item);
        }
    }
}
