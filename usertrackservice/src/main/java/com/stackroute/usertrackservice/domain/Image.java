package com.stackroute.usertrackservice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

public class Image {
    @Id
    private int imageId;
    @JsonProperty("text")
    private String imageurl;
    @JsonProperty("size")
    private String imageSpec;

    public Image() {

    }

    public Image(int imageId, String imageurl, String imageSpec) {
        super();
        this.imageId = imageId;
        this.imageurl = imageurl;
        this.imageSpec = imageSpec;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getImageSpec() {
        return imageSpec;
    }

    public void setImageSpec(String imageSpec) {
        this.imageSpec = imageSpec;
    }

    @Override
    public String toString() {
        return "Image [imageId=" + imageId + ", imageurl=" + imageurl + ", imageSpec=" + imageSpec + "]";
    }
}
