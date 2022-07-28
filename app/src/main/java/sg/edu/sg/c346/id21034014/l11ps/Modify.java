package sg.edu.sg.c346.id21034014.l11ps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Modify extends AppCompatActivity {

    EditText etMovieID, etMovieTitle, etMovieGenre, etMovieYear;
    Spinner spn;
    ArrayList<Movies> alMovieList;
    CustomAdapter caMovies;
    ArrayList<String> alsMovieList;
    Button btnCancel, btnUpdate, btnDelete;
    Movies data;
    String movieRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify2);

        etMovieID = findViewById(R.id.etID);
        etMovieTitle = findViewById(R.id.etTitle);
        etMovieGenre = findViewById(R.id.etgenre);
        etMovieYear = findViewById(R.id.etYear);
        spn = findViewById(R.id.spnRating);
        btnCancel = findViewById(R.id.btnCancel);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        movieRating = "";
        Intent i = getIntent();
        data = (Movies) i.getSerializableExtra("data");

        etMovieID.setText(data.getMovieid() + "");
        etMovieTitle.setText(data.getMovieTitle());
        etMovieGenre.setText(data.getMoviegenre());
        etMovieYear.setText(data.getMovieyear() + "");
        if(data.getMovierating().equals("G"))
        {
            spn.setSelection(0);
        } else if(data.getMovierating().equals("PG"))
        {
            spn.setSelection(1);
        }else if(data.getMovierating().equals("PG13"))
        {
            spn.setSelection(2);
        }else if(data.getMovierating().equals("NC16"))
        {
            spn.setSelection(3);
        }else if(data.getMovierating().equals("M18"))
        {
            spn.setSelection(4);
        }else if(data.getMovierating().equals("R21"))
        {
            spn.setSelection(5);
        }



        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(Modify.this);
                data.setMovieTitle(etMovieTitle.getText().toString());
                data.setMoviegenre(etMovieGenre.getText().toString());
                String yearString = etMovieYear.getText().toString();
                int year = Integer.parseInt(yearString);
                data.setMovieyear(year);
                data.setMovierating(movieRating);
                dbh.updateNote(data);
                dbh.close();

                finish();
//                Toast.makeText(ModifyPage.this, "hi", Toast.LENGTH_SHORT).show();
            }
        });



        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(Modify.this);
                int result = dbh.deleteNote(data.getMovieid());
                Log.d("Result", result + "");
                finish();
            }
        });

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        String spinnerItems1 = spn.getSelectedItem().toString();
                        movieRating = spinnerItems1;
                        break;
                    case 1:
                        String spinnerItems2 = spn.getSelectedItem().toString();
                        movieRating = spinnerItems2;
                        break;
                    case 2:
                        String spinnerItems3 = spn.getSelectedItem().toString();
                        movieRating = spinnerItems3;
                        break;
                    case 3:
                        String spinnerItems4 = spn.getSelectedItem().toString();
                        movieRating = spinnerItems4;
                        break;
                    case 4:
                        String spinnerItems5 = spn.getSelectedItem().toString();
                        movieRating = spinnerItems5;
                        break;
                    case 5:
                        String spinnerItems6 = spn.getSelectedItem().toString();
                        movieRating = spinnerItems6;
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

}