package edualves.com.moviedbapp.ui;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import edualves.com.moviedbapp.BaseApp;
import edualves.com.moviedbapp.R;
import edualves.com.moviedbapp.model.ResultsTopRatedTVResponse;
import edualves.com.moviedbapp.model.TopRatedTVResponse;
import edualves.com.moviedbapp.presenter.TopRatedTvPresenter;
import edualves.com.moviedbapp.service.Service;
import edualves.com.moviedbapp.ui.listener.EndlessRecyclerViewScrollListener;

public class CatalogActivity extends BaseApp implements TopRatedTvView {

    @BindView(R.id.recycler_list)
    RecyclerView recyclerList;

    TopRatedTvPresenter presenter;

    private EndlessRecyclerViewScrollListener scrollListener;

    ResultAdapter adapter;

    @Inject
    public Service service;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getDeps().inject(this);

        presenter = new TopRatedTvPresenter(service, this);

        presenter.getTopRatedTvShows(1);

        renderView();
        init();
    }

    private void renderView() {
        setContentView(R.layout.activity_catalog);
        ButterKnife.bind(this);

    }

    private void init() {
        recyclerList.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerList.setHasFixedSize(false);


        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerList.getLayoutManager();

        recyclerList.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                presenter.updateTvShowsList(page);
            }
        });
    }

    @Override
    public void FailureListTvShows(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void getListTvShowsSuccess(TopRatedTVResponse topRatedTVResponse) {
        adapter = new ResultAdapter(
                getApplicationContext(),
                topRatedTVResponse.getResultListResponse(),
                new ResultAdapter.OnItemClickListener() {
            @Override
            public void onClick(ResultsTopRatedTVResponse resultItem) {
                Toast.makeText(
                        CatalogActivity.this,
                        "Item: " + resultItem.getName(),
                        Toast.LENGTH_LONG).show();
            }
        });
        recyclerList.setAdapter(adapter);
    }

    @Override
    public void updateMovieList(TopRatedTVResponse topRatedTVResponse) {
        for (int i = 0; i < topRatedTVResponse.getResultListResponse().size(); i++) {
            adapter.add(topRatedTVResponse.getResultListResponse().get(i));
        }

    }
}
