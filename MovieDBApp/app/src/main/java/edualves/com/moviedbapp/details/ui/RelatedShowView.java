package edualves.com.moviedbapp.details.ui;

import edualves.com.moviedbapp.model.ResultSimilarResponse;

/**
 * Created by edualves on 24/06/17.
 */

public interface RelatedShowView {

    void FailureListSimilarShows(String errorMessage);

    void getListSimilarShowsSuccess(ResultSimilarResponse resultSimilarResponse);
}
