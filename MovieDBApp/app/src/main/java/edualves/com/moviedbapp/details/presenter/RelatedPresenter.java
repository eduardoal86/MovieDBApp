package edualves.com.moviedbapp.details.presenter;

import android.accounts.NetworkErrorException;
import android.util.Log;

import edualves.com.moviedbapp.details.ui.RelatedShowView;
import edualves.com.moviedbapp.model.ResultSimilarResponse;
import edualves.com.moviedbapp.service.Service;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by edualves on 24/06/17.
 */

public class RelatedPresenter {

    private final Service service;
    private final RelatedShowView view;

    private CompositeSubscription subscriptions;

    private final String LOG_TAG = RelatedPresenter.class.getName();

    //TODO move this api key to another place
    String apikey = "de6449f3144f614ba03ad6d47111098b";

    public RelatedPresenter(Service service, RelatedShowView view) {
        this.service = service;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }

    public void getResultSimilarShow(Integer id) {

        Subscription subscription = service.getSimilarShows(id, apikey, new Service.GetResultSimilarCallback() {
            @Override
            public void onSuccess(ResultSimilarResponse resultSimilarResponse) {
                Log.d(LOG_TAG, "OnSuccess:OK");
                Log.d(LOG_TAG, "OnSuccess:"+ resultSimilarResponse.getRelatedResponseList());
                view.getListSimilarShowsSuccess(resultSimilarResponse);
            }

            @Override
            public void onError(NetworkErrorException exception) {
                Log.d(LOG_TAG, "OnError:"+exception.getMessage());
                view.FailureListSimilarShows(exception.getMessage());
            }
        });

        subscriptions.add(subscription);

    }


}
