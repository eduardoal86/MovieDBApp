package edualves.com.moviedbapp.details.ui;

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
import edualves.com.moviedbapp.model.RelatedResponse;
import edualves.com.moviedbapp.model.ResultSimilarResponse;
import edualves.com.moviedbapp.utils.Utils;

/**
 * Created by edualves on 23/06/17.
 */

public class RelatedAdapter extends RecyclerView.Adapter<RelatedAdapter.ViewHolder> {

    private ResultSimilarResponse resultsList;
    private Context context;
    String imagePoster;

    public RelatedAdapter(Context context,
                          ResultSimilarResponse resultsList) {
        this.context = context;
        this.resultsList = resultsList;
    }

    @Override
    public RelatedAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.related_show_item_view, null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RelatedAdapter.ViewHolder holder, int position) {

        holder.displayName.setText(Utils.countCharsForSpace(resultsList.getRelatedResponseList().get(position).getName(), 15));

        if (resultsList.getRelatedResponseList().get(position).getUrlPoster() == null) {
            this.imagePoster = String.format(context.getString(R.string.poster_path),
                    resultsList.getRelatedResponseList().get(position).getUrlBackPoster());
        } else {
            this.imagePoster = String.format(context.getString(R.string.poster_path),
                    resultsList.getRelatedResponseList().get(position).getUrlPoster());
        }

        Glide.with(context)
                .load(this.imagePoster)
                .placeholder(android.R.drawable.star_on)
                .into(holder.posterImage);

    }

    @Override
    public int getItemCount() {
        return resultsList.getRelatedResponseList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView displayName;
        ImageView posterImage;

        public ViewHolder(View itemView) {
            super(itemView);
            displayName = (TextView) itemView.findViewById(R.id.related_show_name);
            posterImage = (ImageView) itemView.findViewById(R.id.related_poster);
        }

    }
}
