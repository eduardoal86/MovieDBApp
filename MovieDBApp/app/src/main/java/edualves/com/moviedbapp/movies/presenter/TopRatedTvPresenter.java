package edualves.com.moviedbapp.movies.presenter;

import android.accounts.NetworkErrorException;
import android.util.Log;

import edualves.com.moviedbapp.model.TopRatedTVResponse;
import edualves.com.moviedbapp.movies.ui.TopRatedTvView;
import edualves.com.moviedbapp.service.Service;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by edualves on 13/06/17.
 */

public class TopRatedTvPresenter {

    private final Service service;

    private final TopRatedTvView view;

    private CompositeSubscription subscriptions;

    private final String LOG_TAG = TopRatedTvPresenter.class.getName();

    //TODO move this api key to another place
    String apikey = "de6449f3144f614ba03ad6d47111098b";

    public TopRatedTvPresenter(Service service, TopRatedTvView view) {
        this.service = service;
        this.view = view;
        this.subscriptions = new CompositeSubscription();

    }

    public void getTopRatedTvShows(Integer page) {

        Subscription subscription = service.getTvTopRated(apikey, page, new Service.GetResultsCallback() {
            @Override
            public void onSuccess(TopRatedTVResponse topRatedTVResponse) {
                Log.d(LOG_TAG, "OnSuccess:OK");
                Log.d(LOG_TAG, "OnSuccess:"+ topRatedTVResponse.getTotalResults());
                view.getListTvShowsSuccess(topRatedTVResponse);
            }

            @Override
            public void onError(NetworkErrorException exception) {
                Log.d(LOG_TAG, "OnError:"+exception.getMessage());
                view.FailureListTvShows(exception.getMessage());
            }
        });

        subscriptions.add(subscription);

    }

    public void updateTvShowsList(Integer page) {

        Subscription subscription = service.getTvTopRated(apikey, page, new Service.GetResultsCallback() {
            @Override
            public void onSuccess(TopRatedTVResponse topRatedTVResponse) {
                Log.d(LOG_TAG, "OnSuccess:OK");
                Log.d(LOG_TAG, "OnSuccess:"+ topRatedTVResponse.getTotalResults());
                view.updateMovieList(topRatedTVResponse);
            }

            @Override
            public void onError(NetworkErrorException exception) {
                Log.d(LOG_TAG, "OnError:"+exception.getMessage());
                view.FailureListTvShows(exception.getMessage());
            }
        });

        subscriptions.add(subscription);

    }

    public void onStop() {
        subscriptions.unsubscribe();
    }


}
