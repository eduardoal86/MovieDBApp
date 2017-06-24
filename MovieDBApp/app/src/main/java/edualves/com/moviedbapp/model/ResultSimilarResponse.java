package edualves.com.moviedbapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by edualves on 24/06/17.
 */

public class ResultSimilarResponse implements Serializable {

    @SerializedName("results")
    List<RelatedResponse> relatedResponseList;

    public List<RelatedResponse> getRelatedResponseList() {
        return relatedResponseList;
    }

    public void setRelatedResponseList(List<RelatedResponse> relatedResponseList) {
        this.relatedResponseList = relatedResponseList;
    }
}
