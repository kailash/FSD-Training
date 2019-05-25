package com.stackroute.usertrackservice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

public class Artist {

    @Id
    private int artistId;
    @JsonProperty("name")
    private String artistName;
    @JsonProperty("url")
    private String url;

    private Image image;

    public Artist() {

    }

    public Artist(int artistId, String artistName, String url, Image image) {
        super();
        this.artistId = artistId;
        this.artistName = artistName;
        this.url = url;
        this.image = image;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Artist [artistId=" + artistId + ", artistName=" + artistName + ", url=" + url + ", image=" + image
                + "]";
    }
}
