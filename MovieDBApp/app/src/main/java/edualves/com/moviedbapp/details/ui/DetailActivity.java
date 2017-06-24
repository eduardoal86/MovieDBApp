package edualves.com.moviedbapp.details.ui;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edualves.com.moviedbapp.BaseApp;
import edualves.com.moviedbapp.R;
import edualves.com.moviedbapp.model.TvShowResponse;

/**
 * Created by edualves on 23/06/17.
 */

public class DetailActivity extends BaseApp {

    private static final String LOG_TAG = DetailActivity.class.getSimpleName();

    public static final String TV_SHOW = "TV_SHOW";
    public static final String RELATED_ID = "RELATED_ID";

    @BindView(R.id.related_container)
    LinearLayout relatedContainer;

    @BindView(R.id.fragment_related)
    FrameLayout frameRelated;

    private Bundle extras;

    private TvShowResponse tvShowResponse;

    private boolean isDisplayed;

    public static Intent getStartIntent(Context context, TvShowResponse tvShowResponse) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(TV_SHOW, tvShowResponse);

        return intent;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstance) {
        super.onCreate(savedInstance);

        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        extras = getIntent().getExtras();

        if (extras != null) {
            tvShowResponse = (TvShowResponse) extras.getSerializable(TV_SHOW);

            goToDetailFragment();
            loadRelatedFragment();

        } //Just in case bundle == null, we can create an empty screen or display some error more friendly

    }

    private void goToDetailFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_detail, DetailFragment.newInstance(tvShowResponse))
                .commit();

    }

    private void loadRelatedFragment() {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_related, RelatedFragment.newInstance(tvShowResponse.getId()))
                .commit();
    }

    @OnClick(R.id.related_title)
    void displayRelatedFragment() {

        if (!isDisplayed) {
            frameRelated.setVisibility(View.VISIBLE);
            isDisplayed = true;
        } else {
            frameRelated.setVisibility(View.GONE);
            isDisplayed = false;
        }

    }


}
