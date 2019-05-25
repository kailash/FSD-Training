package com.stackroute.rabbitmq.domain;

public class TrackDTO {

  private String trackId;
  private String trackName;
  private String comments;
  private String trackListeners;
  private String trackUrl;

  private ArtistDTO artist;

  public TrackDTO() {

  }

  public TrackDTO(String trackId, String trackName, String comments, String trackListeners, String trackUrl,
               ArtistDTO artist) {
    super();
    this.trackId = trackId;
    this.trackName = trackName;
    this.comments = comments;
    this.trackListeners = trackListeners;
    this.trackUrl = trackUrl;
    this.artist = artist;
  }

  public String getTrackId() {
    return trackId;
  }

  public void setTrackId(String trackId) {
    this.trackId = trackId;
  }

  public String getTrackName() {
    return trackName;
  }

  public void setTrackName(String trackName) {
    this.trackName = trackName;
  }

  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  public String getTrackListeners() {
    return trackListeners;
  }

  public void setTrackListeners(String trackListeners) {
    this.trackListeners = trackListeners;
  }

  public String getTrackUrl() {
    return trackUrl;
  }

  public void setTrackUrl(String trackUrl) {
    this.trackUrl = trackUrl;
  }

  public ArtistDTO getArtist() {
    return artist;
  }

  public void setArtist(ArtistDTO artist) {
    this.artist = artist;
  }

  @Override
  public String toString() {
    return "Track [trackId=" + trackId + ", trackName=" + trackName + ", comments=" + comments + ", trackListeners="
      + trackListeners + ", trackUrl=" + trackUrl + ", artist=" + artist + "]";
  }
}
