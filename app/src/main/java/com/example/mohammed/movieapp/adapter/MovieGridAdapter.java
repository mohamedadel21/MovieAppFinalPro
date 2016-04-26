package com.example.mohammed.movieapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.mohammed.movieapp.model.Movie;
import com.smartware.sharkawy.myapplication.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.util.List;


public class MovieGridAdapter extends BaseAdapter {

    private final Context mContext;
    private final LayoutInflater mInflater;

    private final Movie mLock = new Movie();
    private List<Movie> mObjects;

    public MovieGridAdapter(Context context ,List<Movie> objects ) {
        mContext = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mObjects = objects;
    }

    public Context getmContext() {
        return mContext;
    }
    public void add(Movie object) {

        synchronized (mLock) {
            mObjects.add(object);
        }
        notifyDataSetChanged();
    }
    public void clear() {
        synchronized (mLock) {
            mObjects.clear();
        }
        notifyDataSetChanged();
    }
    public void setData(List<Movie> data) {
        clear();
        for (Movie movie : data) {
            add(movie);
        }
    }

    @Override
    public int getCount() {
        return mObjects.size();
    }

    @Override
    public Object getItem(int position) {
        return mObjects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder;



        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.movie_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        final Movie movie = (Movie) getItem(position);

        String image_url = "http://image.tmdb.org/t/p/w185" + movie.getImage_path();

        viewHolder = (ViewHolder) convertView.getTag();

        Picasso.with(getmContext()).load(image_url).into(viewHolder.imageView);
        viewHolder.titleView.setText(movie.getTitle());


        return convertView;
    }

    public static class ViewHolder {
        public final ImageView imageView;
        public final TextView titleView;

        public ViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.movie_item_image);
            titleView = (TextView) view.findViewById(R.id.movie_item_title);
        }
    }
}
