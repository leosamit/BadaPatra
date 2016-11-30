package com.naxa.nepal.badapatraapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.naxa.nepal.badapatraapp.R;
import com.naxa.nepal.badapatraapp.model.SubCat_List_Model;

import java.util.List;

/**
 * Created by Samir on 11/30/2016.
 */

public class SubCat_List_Adapter extends RecyclerView.Adapter<SubCat_List_Adapter.ContactViewHolder> implements View.OnClickListener {


    private List<SubCat_List_Model> faqList;
    Activity activity;
    Context context;


    public SubCat_List_Adapter(Context context, List<SubCat_List_Model> cList) {
        this.faqList = cList;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return faqList.size();
    }

    @Override
    public void onBindViewHolder(SubCat_List_Adapter.ContactViewHolder contactViewHolder, int i) {
        SubCat_List_Model ci = faqList.get(i);
        final SharedPreferences wmbPreference = PreferenceManager
                .getDefaultSharedPreferences(context);
//        final boolean setData = wmbPreference.getBoolean("SET_ENGLISH_ON", true);
//

//        Resources r = context.getResources();
//        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 90, r.getDisplayMetrics());
//        //this change height of rcv
//        DisplayMetrics displaymetrics = new DisplayMetrics();
//        activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
//        int height = displaymetrics.heightPixels;
//        int width = displaymetrics.widthPixels;
//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        params.height = (height - px) / 4; //height recycleviewer
////        if(setData){
        contactViewHolder.tvCatTitle.setText(ci.getSub_catName());

//        }else{
//            contactViewHolder.tvDevTitle.setText(ci.getDev_title_np());
//            contactViewHolder.tvContractor.setText(ci.getDev_contractor_np());
//            contactViewHolder.tvBudget.setText(ci.getDev_budget_np());
//            contactViewHolder.tvDistrict.setText(ci.getDistrict_name_np());
//        }


        String Img_Thumb_Url = ci.getSub_catThumbnail();

        Log.e("img_path: ", Img_Thumb_Url );

        if(Img_Thumb_Url != null) {
            Glide.with(context)
                    .load(Img_Thumb_Url)
                    .thumbnail(0.5f)
                    .override(75,50)
                    .into(contactViewHolder.imageView);
        }

//        String img_url = ci.photo ;
//        if(img_url.equals("")){
//            img_url = "www";
//        }
//        Picasso.with(context)
//                .load(img_url)
//                .placeholder(R.mipmap.ic_launcher)
//                .resize(400, 230)
//                .into(contactViewHolder.imageView);

    }

    @Override
    public SubCat_List_Adapter.ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_view_list_row, viewGroup, false);
        return new SubCat_List_Adapter.ContactViewHolder(itemView);
    }

    @Override
    public void onClick(View v) {

    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        protected TextView tvCatTitle;
        protected ImageView imageView;

        public ContactViewHolder(View v) {
            super(v);
            tvCatTitle = (TextView) v.findViewById(R.id.textView_list_item);
            imageView = (ImageView) v.findViewById(R.id.img_thumbnail);

        }
    }
}

