package edualves.com.moviedbapp.details.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edualves.com.moviedbapp.BaseApp;
import edualves.com.moviedbapp.R;
import edualves.com.moviedbapp.details.presenter.RelatedPresenter;
import edualves.com.moviedbapp.model.RelatedResponse;
import edualves.com.moviedbapp.model.ResultSimilarResponse;
import edualves.com.moviedbapp.model.TvShowResponse;
import edualves.com.moviedbapp.service.Service;
import edualves.com.moviedbapp.utils.Utils;

/**
 * Created by edualves on 23/06/17.
 */

public class DetailFragment extends Fragment implements RelatedShowView {

    @BindView(R.id.name_show_detail)
    TextView nameDetail;

    @BindView(R.id.overview_detail)
    TextView overviewDetail;

    @BindView(R.id.poster_detail)
    ImageView posterDetail;

    @BindView(R.id.vote_average_detail)
    TextView averageDetail;

    @BindView(R.id.recycler_related)
    RecyclerView recyclerRelated;

    @BindView(R.id.related_container)
    RelativeLayout relatedContainer;

    @Inject
    public Service service;

    RelatedAdapter adapter;

    private Integer relatedId;

    private RelatedPresenter relatedPresenter;

    private TvShowResponse tvShowResponse;
    private String imagePath;
    private Bundle extras;
    private boolean isDisplayed;

    public static DetailFragment newInstance(TvShowResponse tvShowResponse) {
        DetailFragment detailFragment = new DetailFragment();
        Bundle extras = new Bundle(1);
        extras.putSerializable(DetailActivity.TV_SHOW, tvShowResponse);
        detailFragment.setArguments(extras);
        return detailFragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        ((BaseApp) getActivity()).getDeps().inject(this);

        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);

        extras = getArguments();

        if (extras != null) {
            tvShowResponse = (TvShowResponse) extras.getSerializable(DetailActivity.TV_SHOW);

            relatedId = tvShowResponse.getId();

            relatedPresenter = new RelatedPresenter(service, this);
            relatedPresenter.getResultSimilarShow(relatedId);

            displayTvShowInfo();
            initList();
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

    private void initList() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        //recyclerRelated.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        SnapHelper snapHelper = new LinearSnapHelper();
        recyclerRelated.setLayoutManager(linearLayoutManager);
        snapHelper.attachToRecyclerView(recyclerRelated);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void FailureListSimilarShows(String errorMessage) {
        Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void getListSimilarShowsSuccess(ResultSimilarResponse resultSimilarResponse) {
        adapter = new RelatedAdapter(
                getActivity(),
                resultSimilarResponse,
                new RelatedAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(RelatedResponse resultItem) {
                        Toast.makeText(
                                getActivity(),
                                "Item: " + resultItem.getName(),
                                Toast.LENGTH_LONG).show();
                    }
                });

        recyclerRelated.setAdapter(adapter);
    }

    @OnClick(R.id.related_container)
    void displayRelatedContainer() {
        if (!isDisplayed) {
            recyclerRelated.setVisibility(View.VISIBLE);
            isDisplayed = true;
        } else {
            recyclerRelated.setVisibility(View.GONE);
            isDisplayed = false;
        }

    }
}
