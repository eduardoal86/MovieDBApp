package edualves.com.moviedbapp.movies.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import edualves.com.moviedbapp.BaseApp;
import edualves.com.moviedbapp.R;
import edualves.com.moviedbapp.details.ui.DetailActivity;
import edualves.com.moviedbapp.model.TvShowResponse;
import edualves.com.moviedbapp.model.TopRatedTVResponse;
import edualves.com.moviedbapp.movies.presenter.TopRatedTvPresenter;
import edualves.com.moviedbapp.service.Service;
import edualves.com.moviedbapp.movies.ui.listener.EndlessRecyclerViewScrollListener;

public class CatalogActivity extends BaseApp implements TopRatedTvView {

    @BindView(R.id.recycler_list)
    RecyclerView recyclerList;

    TopRatedTvPresenter presenter;

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
    public void getListTvShowsSuccess(final TopRatedTVResponse topRatedTVResponse) {
        adapter = new ResultAdapter(
                getApplicationContext(),
                topRatedTVResponse.getResultListResponse(),
                new ResultAdapter.OnItemClickListener() {
            @Override
            public void onClick(TvShowResponse resultItem) {
                Toast.makeText(
                        CatalogActivity.this,
                        "Item: " + resultItem.getName(),
                        Toast.LENGTH_LONG).show();

                Intent intent = DetailActivity.getStartIntent(CatalogActivity.this, resultItem);
                startActivity(intent);
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
