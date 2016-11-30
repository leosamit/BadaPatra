package com.naxa.nepal.badapatraapp.model;

/**
 * Created by Samir on 11/30/2016.
 */

public class SubCat_List_Model {
    private String sub_cat_id;
    private String sub_catName;
    private String sub_catThumbnail;


    //===========================================sub category getter and setter=============================//

    public String getSub_cat_id() {
        return sub_cat_id;
    }
    public void setSub_cat_id(String sub_cat_id) {
        this.sub_cat_id = sub_cat_id;
    }


    public String getSub_catName() {
        return sub_catName;
    }
    public void setSub_catName(String sub_catName) {
        this.sub_catName = sub_catName;
    }


    public String getSub_catThumbnail() {
        return sub_catThumbnail;
    }
    public void setSub_catThumbnail(String sub_catThumbnail) {
        this.sub_catThumbnail = sub_catThumbnail;
    }
}
