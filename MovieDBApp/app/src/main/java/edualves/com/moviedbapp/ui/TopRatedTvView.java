package edualves.com.moviedbapp.ui;

import edualves.com.moviedbapp.model.TopRatedTVResponse;

/**
 * Created by edualves on 13/06/17.
 */

public interface TopRatedTvView {

    void FailureListTvShows(String errorMessage);

    void getListTvShowsSuccess(TopRatedTVResponse topRatedTVResponse);

    void updateMovieList(TopRatedTVResponse topRatedTVResponse);
}
