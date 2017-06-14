package edualves.com.moviedbapp.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import edualves.com.moviedbapp.R;
import edualves.com.moviedbapp.model.ResultsTopRatedTVResponse;

/**
 * Created by edualves on 13/06/17.
 */

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {

    private final OnItemClickListener listener;
    private List<ResultsTopRatedTVResponse> resultsList;
    private Context context;
    String imagePoster;

    public ResultAdapter(Context context,
                         List<ResultsTopRatedTVResponse> resultsList,
                         OnItemClickListener listener) {
        this.listener = listener;
        this.context = context;
        this.resultsList = resultsList;
    }

    @Override
    public ResultAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tvshow_item_view, null);
        view.setLayoutParams(
                new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                        RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ResultAdapter.ViewHolder holder, int position) {
        holder.onClick(resultsList.get(position), listener);

        holder.displayName.setText(resultsList.get(position).getName());
        holder.average.setText(resultsList.get(position).getVoteAverage().toString());

        this.imagePoster = "https://image.tmdb.org/t/p/w185_and_h278_bestv2" + resultsList.get(position).getUrlPoster();

        Glide.with(context)
                .load(this.imagePoster)
                .placeholder(android.R.drawable.star_on)
                .into(holder.posterImage);

    }

    @Override
    public int getItemCount() {
        return resultsList.size();
    }

    public interface OnItemClickListener {
        void onClick(ResultsTopRatedTVResponse resultItem);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView displayName;
        ImageView posterImage;
        TextView average;

        public ViewHolder(View itemView) {
            super(itemView);

            displayName = (TextView) itemView.findViewById(R.id.title);
            posterImage = (ImageView) itemView.findViewById(R.id.poster_show);
            average = (TextView) itemView.findViewById(R.id.vote_average);


        }

        public void onClick(final ResultsTopRatedTVResponse result,
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
