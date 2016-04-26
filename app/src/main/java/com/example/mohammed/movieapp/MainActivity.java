package com.example.mohammed.movieapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.mohammed.movieapp.model.Movie;
import com.smartware.sharkawy.myapplication.R;


public class MainActivity extends AppCompatActivity implements MainActivityFragment.Callback {

    private boolean twoPane ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //hey its an update
        if (findViewById(R.id.movie_detail_container) != null) {
            twoPane = true;
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.movie_detail_container, new DetailedActivityFragment(),
                                DetailedActivityFragment.TAG)
                        .commit();
            }
        } else {
            twoPane = false;
        }
    }

    @Override
    public void onItemSelected(Movie movie) {
        if (twoPane) {
            Bundle arguments = new Bundle();
            arguments.putParcelable(DetailedActivityFragment.DETAIL_MOVIE, movie);

            DetailedActivityFragment fragment = new DetailedActivityFragment();
            fragment.setArguments(arguments);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movie_detail_container, fragment, DetailedActivityFragment.TAG)
                    .commit();
        } else {
            Intent intent = new Intent(this, DetailedActivity.class)
                    .putExtra(DetailedActivityFragment.DETAIL_MOVIE, movie);

            startActivity(intent);
        }
    }
}
