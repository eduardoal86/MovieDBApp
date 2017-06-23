package edualves.com.moviedbapp.details.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import edualves.com.moviedbapp.R;
import edualves.com.moviedbapp.model.TvShowResponse;
import edualves.com.moviedbapp.utils.Utils;

/**
 * Created by edualves on 23/06/17.
 */

public class DetailFragment extends Fragment {

    @BindView(R.id.name_show_detail)
    TextView nameDetail;

    @BindView(R.id.overview_detail)
    TextView overviewDetail;

    @BindView(R.id.poster_detail)
    ImageView posterDetail;

    @BindView(R.id.vote_average_detail)
    TextView averageDetail;

    private TvShowResponse tvShowResponse;
    private String imagePath;
    private Bundle extras;

    public static DetailFragment newInstance(TvShowResponse tvShowResponse) {
        DetailFragment detailFragment = new DetailFragment();
        Bundle extras = new Bundle(1);
        extras.putSerializable(DetailActivity.TV_SHOW, tvShowResponse);
        detailFragment.setArguments(extras);
        return detailFragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);

        extras = getArguments();

        if (extras != null) {
            tvShowResponse = (TvShowResponse) extras.getSerializable(DetailActivity.TV_SHOW);
            displayTvShowInfo();
        }

        return view;
    }


    private void displayTvShowInfo() {

        imagePath = getActivity().getResources().getString(R.string.poster_path) + tvShowResponse.getUrlPoster();

        Glide.with(this)
                .load(imagePath)
                .into(posterDetail);

        nameDetail.setText(Utils.countCharsForSpace(tvShowResponse.getName(), 15));
        overviewDetail.setText(tvShowResponse.getOverview());
        averageDetail.setText(tvShowResponse.getVoteAverage().toString());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
