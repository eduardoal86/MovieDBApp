package edualves.com.moviedbapp.details.ui;


import android.os.Bundle;

import edualves.com.moviedbapp.BaseApp;
import edualves.com.moviedbapp.R;

/**
 * Created by edualves on 23/06/17.
 */

public class DetailActivity extends BaseApp {

    DetailFragment detailFragment = new DetailFragment();

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_detail);

        goToDetailFragment();
    }

    private void goToDetailFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_detail, detailFragment)
                .commit();

    }
}
