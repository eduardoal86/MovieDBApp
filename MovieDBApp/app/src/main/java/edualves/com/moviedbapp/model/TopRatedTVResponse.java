package edualves.com.moviedbapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by edualves on 13/06/17.
 */

public class TopRatedTVResponse implements Serializable {

    @SerializedName("page")
    Integer page;

    @SerializedName("total_results")
    Integer totalResults;

    @SerializedName("total_pages")
    Integer totalPages;

    @SerializedName("results")
    List<ResultsTopRatedTVResponse> resultListResponse;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<ResultsTopRatedTVResponse> getResultListResponse() {
        return resultListResponse;
    }

    public void setResultListResponse(List<ResultsTopRatedTVResponse> resultListResponse) {
        this.resultListResponse = resultListResponse;
    }
}
