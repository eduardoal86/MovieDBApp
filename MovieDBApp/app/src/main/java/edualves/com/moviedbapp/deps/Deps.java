package edualves.com.moviedbapp.deps;

import javax.inject.Singleton;

import dagger.Component;
import edualves.com.moviedbapp.service.NetworkModule;
import edualves.com.moviedbapp.ui.CatalogActivity;

/**
 * Created by edualves on 13/06/17.
 */

@Singleton
@Component(modules = NetworkModule.class)
public interface Deps {

    void inject(CatalogActivity catalogActivity);
}
