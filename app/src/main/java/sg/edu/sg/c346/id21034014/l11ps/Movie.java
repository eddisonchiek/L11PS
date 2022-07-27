package sg.edu.sg.c346.id21034014.l11ps;

import java.io.Serializable;

public class Movie implements Serializable {

    private int _id;
    private String title;
    private String genre;
    private int year;
    private int rating;

    public Movie(String title, String genre, int year, int rating) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.rating = rating;
    }

    public int get_id() { return _id; }

    public String getTitle() {return title;}

    public String getGenre() {return genre;}

    public int getYear() {return year;}

    public int getRating() {return rating;}


}
