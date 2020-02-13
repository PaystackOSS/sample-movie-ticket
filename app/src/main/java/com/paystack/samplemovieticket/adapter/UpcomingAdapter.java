package com.paystack.samplemovieticket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.paystack.samplemovieticket.R;
import com.paystack.samplemovieticket.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.paystack.samplemovieticket.utils.Constants.IMAGE_BASE_URL;

public class UpcomingAdapter extends RecyclerView.Adapter<UpcomingAdapter.UpcomingAdapterViewHolder>{

    private final UpcomingAdapterClickListener upcomingAdapterClickListener;
    private List<Movie> movies;
    private Context context;

    public UpcomingAdapter(UpcomingAdapterClickListener upcomingAdapterClickListener) {
        this.upcomingAdapterClickListener = upcomingAdapterClickListener;
    }

    @NonNull
    @Override
    public UpcomingAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.movie_list, parent, false);

        return new UpcomingAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingAdapterViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        if(this.movies == null) return 0;
        return this.movies.size();
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    class UpcomingAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mMoviePoster;
        private TextView mMovieName;

        UpcomingAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            mMoviePoster = itemView.findViewById(R.id.iv_movie_image);
            mMovieName = itemView.findViewById(R.id.tv_movie_name);
            itemView.setOnClickListener(this);
        }

        void bind(int position) {
            Movie movie = movies.get(position);
            String moviePosterUrl = movie.getPosterPath();
            String movieTitle = movie.getTitle();

            mMovieName.setText(movieTitle);

            if(moviePosterUrl.length() <= 0) {
                Picasso.with(context)
                        .load(R.drawable.ic_launcher_background)
                        .into(mMoviePoster);
            } else {
                moviePosterUrl = IMAGE_BASE_URL + moviePosterUrl;
                Picasso.with(context)
                        .load(moviePosterUrl)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_background)
                        .fit()
                        .centerCrop()
                        .into(mMoviePoster);
            }
        }

        @Override
        public void onClick(View v) {
            upcomingAdapterClickListener.movieClicked(getAdapterPosition());

        }
    }

    public interface UpcomingAdapterClickListener {
        void movieClicked(int position);
    }
}
