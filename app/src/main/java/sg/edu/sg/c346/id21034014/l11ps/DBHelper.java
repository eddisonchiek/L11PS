package sg.edu.sg.c346.id21034014.l11ps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "movies.db";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_MOVIES = "movies";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "movie_title";
    private static final String COLUMN_GENRE = "movie_genre";
    private static final String COLUMN_YEAR = "movie_year";
    private static final String COLUMN_RATING = "movie_rating";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createNoteTableSql = "CREATE TABLE " + TABLE_MOVIES + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT , "
                + COLUMN_GENRE + " TEXT , "
                + COLUMN_YEAR + " INTEGER , "
                + COLUMN_RATING + " TEXT ) ";
        db.execSQL(createNoteTableSql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("ALTER TABLE " + TABLE_MOVIES + " ADD COLUMN  module_name TEXT ");
    }

    public long insertMovie(String title, String genre, int year,String rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_GENRE, genre);
        values.put(COLUMN_YEAR, year);
        values.put(COLUMN_RATING, rating);
        long result = db.insert(TABLE_MOVIES, null, values);
        db.close();
        return result;
    }

    public ArrayList<Movies> getAllMovies() {
        ArrayList<Movies> movies = new ArrayList<Movies>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns= {COLUMN_ID, COLUMN_TITLE, COLUMN_GENRE, COLUMN_YEAR, COLUMN_RATING };
        Cursor cursor = db.query(TABLE_MOVIES, columns, null, null,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String genre = cursor.getString(2);
                int year = cursor.getInt(3);
                String rating = cursor.getString(4);
                Movies movie = new Movies(id,title,genre,year,rating);
                movies.add(movie);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return movies;
    }

    public ArrayList<Movies> getAllPG13Movies() {
        ArrayList<Movies> movies = new ArrayList<Movies>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_ID, COLUMN_TITLE, COLUMN_GENRE, COLUMN_YEAR, COLUMN_RATING };
        String condition = COLUMN_RATING + " Like ?";
        String[] args = { "%" +  "PG13" + "%"};
        Cursor cursor = db.query(TABLE_MOVIES, columns, condition, args,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String genre = cursor.getString(2);
                int year = cursor.getInt(3);
                String rating = cursor.getString(4);
                Movies movie = new Movies(id,title,genre,year,rating);
                movies.add(movie);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return movies;
    }


    public int updateNote(Movies data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, data.getMovieTitle());
        values.put(COLUMN_GENRE, data.getMoviegenre());
        values.put(COLUMN_YEAR, data.getMovieyear());
        values.put(COLUMN_RATING, data.getMovierating());

        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getMovieid())};
        int result = db.update(TABLE_MOVIES, values, condition, args);

        db.close();
        return result;
    }
    //
    public int deleteNote(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_MOVIES, condition, args);
        db.close();
        return result;
    }

}
