package edualves.com.moviedbapp.presenter;

import android.accounts.NetworkErrorException;

import edualves.com.moviedbapp.model.TopRatedTVResponse;
import edualves.com.moviedbapp.service.Service;
import edualves.com.moviedbapp.ui.TopRatedTvView;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by edualves on 13/06/17.
 */

public class TopRatedTvPresenter {

    private final Service service;

    private final TopRatedTvView view;

    private CompositeSubscription subscriptions;

    //TODO move this api key to another place
    String apikey = "de6449f3144f614ba03ad6d47111098b";

    public TopRatedTvPresenter(Service service, TopRatedTvView view) {
        this.service = service;
        this.view = view;
        this.subscriptions = new CompositeSubscription();

    }

    public void getTopRatedTvShows(Integer page) {

        //check if it necessary call a method to display wait

        Subscription subscription = service.getTvTopRated(apikey, page, new Service.GetResultsCallback() {
            @Override
            public void onSuccess(TopRatedTVResponse topRatedTVResponse) {
                //Log
                view.getListTvShowsSuccess(topRatedTVResponse);
            }

            @Override
            public void onError(NetworkErrorException exception) {
                //LOG

                view.FailureListTvShows(exception.getMessage());
            }
        });

        subscriptions.add(subscription);

    }

    public void onStop() {
        subscriptions.unsubscribe();
    }


}
