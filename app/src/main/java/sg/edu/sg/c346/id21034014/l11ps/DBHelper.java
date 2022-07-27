package sg.edu.sg.c346.id21034014.l11ps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ndps.db";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_Movie = "Movie";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_SINGERS = "singers";
    private static final String COLUMN_YEARS = "year";
    private static final String COLUMN_STARS = "star";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createMovieTableSql = "CREATE TABLE " + TABLE_Movie + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_SINGERS +" TEXT ) ";
        db.execSQL(createMovieTableSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Movie);
        onCreate(db);
    }

    public long insertMovie(String title, String singers, int year, int stars) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_SINGERS, singers);
        values.put(COLUMN_YEARS, year);
        values.put(COLUMN_STARS, stars);
        long result = db.insert(TABLE_Movie, null, values);
        db.close();
        Log.d("SQL Insert","ID:"+ result); //id returned, should'nt be -1
        return result;

    }

    public ArrayList<Movie> getAllMovies() {
        ArrayList<Movie> Movies = new ArrayList<Movie>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns= {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGERS, COLUMN_YEARS, COLUMN_STARS};
        Cursor cursor = db.query(TABLE_Movie, columns, null, null,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String title = cursor.getString(0);
                String singers = cursor.getString(1);
                int year = cursor.getInt(2);
                int stars = cursor.getInt(3);
                Movie Movie = new Movie(title, singers, year, stars);
                Movies.add(Movie);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return Movies;
    }

    public ArrayList<Movie> getAllMoviesByStars(int stars) {
        ArrayList<Movie> Movies = new ArrayList<Movie>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns= {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGERS, COLUMN_YEARS};
        Cursor cursor = db.query(TABLE_Movie, columns, null, null,
                null, null, COLUMN_STARS , null);

        if (cursor.moveToFirst()) {
            do {
                String title = cursor.getString(0);
                String singers = cursor.getString(1);
                int year = cursor.getInt(2);
                Movie Movie = new com.example.ndpMovies.Movie(title, singers, year, 1);
                Movies.add(Movie);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return Movies;
    }

    public int updateMovie(Movie data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, data.getTitle());
        values.put(COLUMN_SINGERS, data.getSingers());
        values.put(COLUMN_YEARS, data.getYear());
        values.put(COLUMN_STARS, data.getStar());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.get_id())};
        int result = db.update(TABLE_Movie, values, condition, args);
        db.close();
        return result;
    }

    public int deleteMovie(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_Movie, condition, args);
        db.close();
        return result;
    }



}
