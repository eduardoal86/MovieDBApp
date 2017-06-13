package edualves.com.moviedbapp.service;

import edualves.com.moviedbapp.model.TopRatedTVResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by edualves on 13/06/17.
 */

public interface NetworkService {

    @GET("3/tv/top_rated")
    Observable<TopRatedTVResponse> getResults(@Query("api_key") String apiKey,
                                              @Query("page") Integer page);
}
