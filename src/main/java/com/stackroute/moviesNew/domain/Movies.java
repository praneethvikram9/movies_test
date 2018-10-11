package com.stackroute.moviesNew.domain;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Movies {
    @Id
    private String imdbId;
    private String movieTitle;
    private String postedUrl;
    private float rating;
    private String yearOfRelease;
    private String comments;

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getPostedUrl() {
        return postedUrl;
    }

    public void setPostedUrl(String postedUrl) {
        this.postedUrl = postedUrl;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(String yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }


    public Movies() {
    }

    public Movies(String imdbId, String movieTitle, String postedUrl, float rating, String yearOfRelease, String comments) {
        this.imdbId = imdbId;
        this.movieTitle = movieTitle;
        this.postedUrl = postedUrl;
        this.rating = rating;
        this.yearOfRelease = yearOfRelease;
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Movies{" +
                "imdbId='" + imdbId + '\'' +
                ", movieTitle='" + movieTitle + '\'' +
                ", postedUrl='" + postedUrl + '\'' +
                ", rating=" + rating +
                ", yearOfRelease='" + yearOfRelease + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }


}
