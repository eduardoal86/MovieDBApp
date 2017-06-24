package edualves.com.moviedbapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by edualves on 24/06/17.
 */

public class RelatedResponse implements Serializable {

    @SerializedName("poster_path")
    String urlPoster;

    @SerializedName("original_name")
    String originalName;

    @SerializedName("name")
    String name;


    public String getUrlPoster() {
        return urlPoster;
    }

    public void setUrlPoster(String urlPoster) {
        this.urlPoster = urlPoster;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
