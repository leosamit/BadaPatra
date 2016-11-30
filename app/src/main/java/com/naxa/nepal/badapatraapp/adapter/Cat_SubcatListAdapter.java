package com.naxa.nepal.badapatraapp.adapter;

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
import com.naxa.nepal.badapatraapp.model.Cat_SubcatListModel;

import java.util.List;

/**
 * Created by User on 11/29/2016.
 */

public class Cat_SubcatListAdapter extends RecyclerView.Adapter<Cat_SubcatListAdapter.ContactViewHolder> implements View.OnClickListener {


    private List<Cat_SubcatListModel> faqList;

    Context context;


    public Cat_SubcatListAdapter(Context context, List<Cat_SubcatListModel> cList) {
        this.faqList = cList;
        this.context = context;

    }

    @Override
    public int getItemCount() {
        return faqList.size();
    }

    @Override
    public void onBindViewHolder(ContactViewHolder contactViewHolder, int i) {
        Cat_SubcatListModel ci = faqList.get(i);
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
        contactViewHolder.tvCatTitle.setText(ci.getMain_catName());

//        }else{
//            contactViewHolder.tvDevTitle.setText(ci.getDev_title_np());
//            contactViewHolder.tvContractor.setText(ci.getDev_contractor_np());
//            contactViewHolder.tvBudget.setText(ci.getDev_budget_np());
//            contactViewHolder.tvDistrict.setText(ci.getDistrict_name_np());
//        }


        String Img_Thumb_Url = ci.getMain_catThumbnail();

        Log.e("img_path: ", Img_Thumb_Url );

        if(Img_Thumb_Url != null) {
            Glide.with(context)
                    .load(Img_Thumb_Url)
                    .thumbnail(0.5f)
                    .override(460,150)
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
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.cardview_grid, viewGroup, false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onClick(View v) {

    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        protected TextView tvCatTitle;
        protected ImageView imageView;

        public ContactViewHolder(View v) {
            super(v);
            tvCatTitle = (TextView) v.findViewById(R.id.tv_species);
            imageView = (ImageView) v.findViewById(R.id.img_thumbnail);

        }
    }
}
