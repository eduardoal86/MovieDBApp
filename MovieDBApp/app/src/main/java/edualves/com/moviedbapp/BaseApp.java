package edualves.com.moviedbapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import edualves.com.moviedbapp.deps.DaggerDeps;
import edualves.com.moviedbapp.deps.Deps;
import edualves.com.moviedbapp.service.NetworkModule;

/**
 * Created by edualves on 13/06/17.
 */

public class BaseApp extends AppCompatActivity {

    Deps deps;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        deps = DaggerDeps.builder().networkModule(new NetworkModule()).build();
    }

    public Deps getDeps() {
        return deps;
    }

}
