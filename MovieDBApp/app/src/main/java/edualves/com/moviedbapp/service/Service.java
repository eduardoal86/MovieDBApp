package edualves.com.moviedbapp.service;

import android.accounts.NetworkErrorException;

import edualves.com.moviedbapp.model.TopRatedTVResponse;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by edualves on 13/06/17.
 */

public class Service {

    private final NetworkService service;

    public Service(NetworkService service) {
        this.service = service;
    }

    public Subscription getTvTopRated(String apiKey, Integer page, final GetResultsCallback callback) {

        return service.getResults(apiKey, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends TopRatedTVResponse>>() {
                    @Override
                    public Observable<? extends TopRatedTVResponse> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<TopRatedTVResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(TopRatedTVResponse topRatedTVResponse) {
                        callback.onSuccess(topRatedTVResponse);
                    }
                });
    }


    public interface GetResultsCallback {

        void onSuccess(TopRatedTVResponse topRatedTVResponse);

        void onError(NetworkErrorException exception);
    }
}
