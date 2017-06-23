package edualves.com.moviedbapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by edualves on 13/06/17.
 */

public class TvShowResponse implements Serializable {

    @SerializedName("original_name")
    String originalName;

    @SerializedName("id")
    Integer id;

    @SerializedName("name")
    String name;

    @SerializedName("vote_average")
    Double voteAverage;

    @SerializedName("poster_path")
    String urlPoster;

    @SerializedName("backdrop_path")
    String UrlBackPoster;

    @SerializedName("overview")
    String overview;

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getUrlPoster() {
        return urlPoster;
    }

    public void setUrlPoster(String urlPoster) {
        this.urlPoster = urlPoster;
    }

    public String getUrlBackPoster() {
        return UrlBackPoster;
    }

    public void setUrlBackPoster(String urlBackPoster) {
        UrlBackPoster = urlBackPoster;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
