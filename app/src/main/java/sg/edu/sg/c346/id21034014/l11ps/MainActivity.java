package sg.edu.sg.c346.id21034014.l11ps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView tvTitle, tvGenre, tvYear, tvRating;
    EditText etTitle, etGenre, etYear;
    Spinner spnRating;
    Button btnInsert, btnShowList;
    String movieRating;
    ArrayList<Movies> alMovieList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tvTitle = findViewById(R.id.tvTitle);
        tvGenre = findViewById(R.id.tvGenre);
        tvYear =findViewById(R.id.tvYear);
        tvRating =findViewById(R.id.tvRating);
        etTitle =findViewById(R.id.etTitle);
        etGenre =findViewById(R.id.etGenre);
        etYear =findViewById(R.id.etYear);
        spnRating =findViewById(R.id.spnRating);
        btnInsert =findViewById(R.id.btnInsert);
        btnShowList =findViewById(R.id.btnShowList);

        alMovieList = new ArrayList<>();


        populateData();


        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ShowListActivity.class);
                startActivity(i);
            }});

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String movietitle = etTitle.getText().toString();
                String moviegenre = etGenre.getText().toString();
                String yearString = etYear.getText().toString();
                int year = Integer.parseInt(yearString);
                String movieratingfinal = movieRating + "";
                DBHelper dbh = new DBHelper(MainActivity.this);


                if(year >= 1877){
                    long inserted_id =dbh.insertMovie(movietitle,moviegenre,year,movieratingfinal);

                    if (inserted_id != -1){
                    alMovieList.clear();
                    alMovieList.addAll(dbh.getAllMovies());
                    // alMovie.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(MainActivity.this, "Invalid Movie Date",
                            Toast.LENGTH_SHORT).show();


                }

            }
        });

        spnRating.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        String spinnerItems1 = spnRating.getSelectedItem().toString();
                        movieRating = spinnerItems1;
                        break;
                    case 1:
                        String spinnerItems2 = spnRating.getSelectedItem().toString();
                        movieRating = spinnerItems2;
                        break;
                    case 2:
                        String spinnerItems3 = spnRating.getSelectedItem().toString();
                        movieRating = spinnerItems3;
                        break;
                    case 3:
                        String spinnerItems4 = spnRating.getSelectedItem().toString();
                        movieRating = spinnerItems4;
                        break;
                    case 4:
                        String spinnerItems5 = spnRating.getSelectedItem().toString();
                        movieRating = spinnerItems5;
                        break;
                    case 5:
                        String spinnerItems6 = spnRating.getSelectedItem().toString();
                        movieRating = spinnerItems6;
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    void populateData(){
        DBHelper dbh = new DBHelper(MainActivity.this);
        Movies item1 = new Movies(1,"test","Genre",2000,"test");
        alMovieList.add(item1);
    }
}
