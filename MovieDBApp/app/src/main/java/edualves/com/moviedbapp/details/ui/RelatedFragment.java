package edualves.com.moviedbapp.details.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import edualves.com.moviedbapp.BaseApp;
import edualves.com.moviedbapp.R;
import edualves.com.moviedbapp.details.presenter.RelatedPresenter;
import edualves.com.moviedbapp.model.RelatedResponse;
import edualves.com.moviedbapp.model.ResultSimilarResponse;
import edualves.com.moviedbapp.service.Service;

/**
 * Created by edualves on 23/06/17.
 */

public class RelatedFragment extends Fragment implements RelatedShowView {

    @BindView(R.id.recycler_related)
    RecyclerView recyclerRelated;

    RelatedAdapter adapter;

    private Integer relatedId;
    private Bundle extras;

    private RelatedPresenter relatedPresenter;

    RecyclerView.LayoutManager recManager;

    @Inject
    public Service service;

    public static RelatedFragment newInstance(Integer relatedId) {
        RelatedFragment detailFragment = new RelatedFragment();
        Bundle extras = new Bundle(1);
        extras.putSerializable(DetailActivity.RELATED_ID, relatedId);
        detailFragment.setArguments(extras);
        return detailFragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        ((BaseApp) getActivity()).getDeps().inject(this);

        View view = inflater.inflate(R.layout.fragment_related, container, false);
        ButterKnife.bind(this, view);

        extras = getArguments();
        if (extras != null) {
            relatedId = (Integer) extras.getSerializable(DetailActivity.RELATED_ID);

            relatedPresenter = new RelatedPresenter(service, this);
            relatedPresenter.getResultSimilarShow(relatedId);

        }

        initList();

        return view;
    }

    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        SnapHelper snapHelper = new LinearSnapHelper();
        recyclerRelated.setLayoutManager(linearLayoutManager);
        snapHelper.attachToRecyclerView(recyclerRelated);

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
}
