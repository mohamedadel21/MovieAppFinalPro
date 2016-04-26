package com.example.mohammed.movieapp.model;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import com.example.mohammed.movieapp.MainActivityFragment;
import org.json.JSONException;
import org.json.JSONObject;


public class Movie implements Parcelable {

    private int id;
    private String title;
    private String image_path;
    private String image_detailed_path;
    private String overview;
    private int rating;
    private String date;

    public Movie() {

    }
    public Movie(JSONObject movie) throws JSONException {
        this.id = movie.getInt("id");
        this.title = movie.getString("original_title");
        this.image_path = movie.getString("poster_path");
        this.image_detailed_path = movie.getString("backdrop_path");
        this.overview = movie.getString("overview");
        this.rating = movie.getInt("vote_average");
        this.date = movie.getString("release_date");
    }
    public Movie(Cursor cursor) {
        this.id = cursor.getInt(MainActivityFragment.COL_MOVIE_ID);
        this.title = cursor.getString(MainActivityFragment.COL_TITLE);
        this.image_path = cursor.getString(MainActivityFragment.COL_IMAGE);
        this.image_detailed_path = cursor.getString(MainActivityFragment.COL_IMAGE2);
        this.overview = cursor.getString(MainActivityFragment.COL_OVERVIEW);
        this.rating = cursor.getInt(MainActivityFragment.COL_RATING);
        this.date = cursor.getString(MainActivityFragment.COL_DATE);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage_path() {
        return image_path;
    }

    public String getImage_detailed_path() {
        return image_detailed_path;
    }

    public String getOverview() {
        return overview;
    }

    public int getRating() {
        return rating;
    }

    public String getDate() {
        return date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(image_path);
        dest.writeString(image_detailed_path);
        dest.writeString(overview);
        dest.writeInt(rating);
        dest.writeString(date);
    }

    public static final Creator<Movie> CREATOR
            = new Creator<Movie>() {
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    private Movie(Parcel in) {
        id = in.readInt();
        title = in.readString();
        image_path = in.readString();
        image_detailed_path = in.readString();
        overview = in.readString();
        rating = in.readInt();
        date = in.readString();
    }
}
