package com.example.meditationapp.ModelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetHomeDataResponseData {
    @SerializedName("banner_data")
    @Expose
    private List<GetHomeDataResponseBannerData> bannerData = null;
    @SerializedName("song_data")
    @Expose
    private List<GetHomeDataResponseSongData> songData = null;
    @SerializedName("album_data")
    @Expose
    private List<GetHomeDataResponseAlbumData> albumData = null;
    @SerializedName("popular_playlist_data")
    @Expose
    private List<GetHomeDataResponsePopularPlaylist> popularPlaylistData = null;
    @SerializedName("featured_playlist_data")
    @Expose
    private List<GetHomeDataResponseFeaturedPlaylist> featuredPlaylistData = null;

    public List<GetHomeDataResponseBannerData> getBannerData() {
        return bannerData;
    }

    public void setBannerData(List<GetHomeDataResponseBannerData> bannerData) {
        this.bannerData = bannerData;
    }

    public List<GetHomeDataResponseSongData> getSongData() {
        return songData;
    }

    public void setSongData(List<GetHomeDataResponseSongData> songData) {
        this.songData = songData;
    }

    public List<GetHomeDataResponseAlbumData> getAlbumData() {
        return albumData;
    }

    public void setAlbumData(List<GetHomeDataResponseAlbumData> albumData) {
        this.albumData = albumData;
    }

    public List<GetHomeDataResponsePopularPlaylist> getPopularPlaylistData() {
        return popularPlaylistData;
    }

    public void setPopularPlaylistData(List<GetHomeDataResponsePopularPlaylist> popularPlaylistData) {
        this.popularPlaylistData = popularPlaylistData;
    }

    public List<GetHomeDataResponseFeaturedPlaylist> getFeaturedPlaylistData() {
        return featuredPlaylistData;
    }

    public void setFeaturedPlaylistData(List<GetHomeDataResponseFeaturedPlaylist> featuredPlaylistData) {
        this.featuredPlaylistData = featuredPlaylistData;
    }

}
