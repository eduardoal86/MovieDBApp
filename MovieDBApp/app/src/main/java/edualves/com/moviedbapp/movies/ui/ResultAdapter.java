package edualves.com.moviedbapp.movies.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

import edualves.com.moviedbapp.R;
import edualves.com.moviedbapp.model.TvShowResponse;
import edualves.com.moviedbapp.utils.Utils;

/**
 * Created by edualves on 13/06/17.
 */

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {

    private final OnItemClickListener listener;
    private List<TvShowResponse> resultsList;
    private Context context;
    String imagePoster;

    public ResultAdapter(Context context,
                         List<TvShowResponse> resultsList,
                         OnItemClickListener listener) {
        this.listener = listener;
        this.context = context;
        this.resultsList = resultsList;
    }

    @Override
    public ResultAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tvshow_item_view, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ResultAdapter.ViewHolder holder, int position) {
        holder.onClick(resultsList.get(position), listener);

        holder.displayName.setText(Utils.countCharsForSpace(resultsList.get(position).getName(), 15));
        holder.average.setText(String.format(context.getString(R.string.vote_score),
                resultsList.get(position).getVoteAverage().toString()));

        if (resultsList.get(position).getUrlPoster() == null) {
            this.imagePoster = String.format(context.getString(R.string.poster_path),
                    resultsList.get(position).getUrlBackPoster());
        } else {
            this.imagePoster = String.format(context.getString(R.string.poster_path),
                    resultsList.get(position).getUrlPoster());
        }

        Glide.with(context)
                .load(this.imagePoster)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e,
                                               String model,
                                               Target<GlideDrawable> target,
                                               boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource,
                                                   String model,
                                                   Target<GlideDrawable> target,
                                                   boolean isFromMemoryCache,
                                                   boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                //.placeholder(android.R.drawable.star_on)
                .into(holder.posterImage);

    }

    @Override
    public int getItemCount() {
        return resultsList.size();
    }

    public void add(TvShowResponse tvShow) {
        resultsList.add(tvShow);
        notifyDataSetChanged();
    }


    public interface OnItemClickListener {
        void onClick(TvShowResponse resultItem);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView displayName;
        ImageView posterImage;
        TextView average;
        ProgressBar progressBar;

        public ViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progress_bar);
            displayName = (TextView) itemView.findViewById(R.id.title);
            posterImage = (ImageView) itemView.findViewById(R.id.poster_show);
            average = (TextView) itemView.findViewById(R.id.vote_average);
        }

        public void onClick(final TvShowResponse result,
                            final OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(result);
                }
            });
        }

    }
}
