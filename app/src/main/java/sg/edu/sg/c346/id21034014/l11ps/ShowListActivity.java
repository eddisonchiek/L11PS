package sg.edu.sg.c346.id21034014.l11ps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowListActivity extends AppCompatActivity {

    ListView lvMovies;
    Button PG13;
    CustomAdapter caMovie;
    ArrayList<Movies> alMovieList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);

        lvMovies = findViewById(R.id.listViewMoviesList);
        alMovieList = new ArrayList<Movies>();
        caMovie = new CustomAdapter(this,R.layout.row,alMovieList);
        lvMovies.setAdapter(caMovie);
        PG13 = findViewById(R.id.btnShowPG13);

        DBHelper dbh= new DBHelper(ShowListActivity.this);
        alMovieList.clear();
        alMovieList.addAll(dbh.getAllMovies());
        caMovie.notifyDataSetChanged();


        lvMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Movies data = alMovieList.get(position);
                Intent i = new Intent(ShowListActivity.this,
                        Modify.class);
                i.putExtra("data", data);
                startActivity(i);
            }
        });

        PG13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(ShowListActivity.this);
                alMovieList.clear();
                alMovieList.addAll(dbh.getAllPG13Movies());
                caMovie.notifyDataSetChanged();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        DBHelper dbh = new DBHelper(ShowListActivity.this);
        alMovieList.clear();
        alMovieList.addAll(dbh.getAllMovies());
        caMovie.notifyDataSetChanged();
    }
}