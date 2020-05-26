package sohan.com.sohancodingtest.models;

import com.google.gson.annotations.SerializedName;


public class TopAlbumsResponse {
    public TopAlbums getTopAlbums() {
        return topAlbums;
    }

    public void setTopAlbums(TopAlbums topAlbums) {
        this.topAlbums = topAlbums;
    }

    @SerializedName("topalbums")
    TopAlbums topAlbums;
}
