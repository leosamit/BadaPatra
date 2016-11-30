package com.naxa.nepal.badapatraapp.model;

/**
 * Created by Samir on 11/30/2016.
 */

public class Default_Details_Model {

    private String main_cat_id;
    private String sub_cat_id;
    private String sub_catThumbnail;

    private String steps;
    private String docsRequired;
    private String officeToVisit;
    private String resourceRequired;


    public String getMain_cat_id() {
        return main_cat_id;
    }

    public void setMain_cat_id(String main_cat_id) {
        this.main_cat_id = main_cat_id;
    }

    public String getSub_cat_id() {
        return sub_cat_id;
    }

    public void setSub_cat_id(String sub_cat_id) {
        this.sub_cat_id = sub_cat_id;
    }


    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public String getDocsRequired() {
        return docsRequired;
    }

    public void setDocsRequired(String docsRequired) {
        this.docsRequired = docsRequired;
    }

    public String getOfficeToVisit() {
        return officeToVisit;
    }

    public void setOfficeToVisit(String officeToVisit) {
        this.officeToVisit = officeToVisit;
    }

    public String getResourceRequired() {
        return resourceRequired;
    }

    public void setResourceRequired(String resourceRequired) {
        this.resourceRequired = resourceRequired;
    }


    public String getSub_catThumbnail() {
        return sub_catThumbnail;
    }

    public void setSub_catThumbnail(String sub_catThumbnail) {
        this.sub_catThumbnail = sub_catThumbnail;
    }


}
