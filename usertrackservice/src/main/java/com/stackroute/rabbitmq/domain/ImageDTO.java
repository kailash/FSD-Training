package com.stackroute.rabbitmq.domain;

public class ImageDTO {

  private int imageId;
  private String imageurl;
  private String imageSpec;

  public ImageDTO() {

  }

  public ImageDTO(int imageId, String imageurl, String imageSpec) {
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
