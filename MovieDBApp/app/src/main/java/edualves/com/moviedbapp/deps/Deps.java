package edualves.com.moviedbapp.deps;

import javax.inject.Singleton;

import dagger.Component;
import edualves.com.moviedbapp.details.ui.DetailActivity;
import edualves.com.moviedbapp.details.ui.DetailFragment;
import edualves.com.moviedbapp.details.ui.RelatedFragment;
import edualves.com.moviedbapp.service.NetworkModule;
import edualves.com.moviedbapp.movies.ui.CatalogActivity;

/**
 * Created by edualves on 13/06/17.
 */

@Singleton
@Component(modules = NetworkModule.class)
public interface Deps {

    void inject(CatalogActivity catalogActivity);

    void inject(RelatedFragment relatedFragment);

    void inject(DetailActivity detailActivity);

}
