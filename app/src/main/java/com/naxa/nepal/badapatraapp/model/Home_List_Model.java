package com.naxa.nepal.badapatraapp.model;

/**
 * Created by user1 on 11/16/2015.
 */
public class Home_List_Model {

    private String Name;
    private int mThumbnail;
        private int main_catThumbnail;

    private String sub_cat_id;
    private String sub_catName;
    private String sub_catThumbnail;


        public String getName() { return Name;}
        public void setName(String Name) {
            this.Name = Name;
        }

        public int getThumbnail() {
            return mThumbnail;
        }
        public void setThumbnail(int thumbnail) {
            this.mThumbnail = thumbnail;
        }

}
