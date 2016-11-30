package com.naxa.nepal.badapatraapp.model;

/**
 * Created by samir on 11/29/2016.
 */

public class Cat_SubcatListModel {

    private String main_cat_id;
    private String main_catName;
    private String main_catThumbnail;

    private String sub_cat_id;
    private String sub_catName;
    private String sub_catThumbnail;

//==========================================main category getter and setter=====================================//

    public String getMain_cat_id() {
        return main_cat_id;
    }
    public void setMain_cat_id(String main_cat_id) {
        this.main_cat_id = main_cat_id;
    }


    public String getMain_catName() {
        return main_catName;
    }
    public void setMain_catName(String main_catName) {
        this.main_catName = main_catName;
    }


    public String getMain_catThumbnail() {
        return main_catThumbnail;
    }
    public void setMain_catThumbnail(String main_catThumbnail) {
        this.main_catThumbnail = main_catThumbnail;
    }

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
