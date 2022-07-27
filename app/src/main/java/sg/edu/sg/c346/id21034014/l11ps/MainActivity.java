package sg.edu.sg.c346.id21034014.l11ps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etTitle, etgenre, etYear;
    Button btnInsert, btnShow;
    RadioGroup rgStars;
    ArrayList<Movie> al;
    ArrayAdapter<Movie> aa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = findViewById(R.id.etTitle);
        etgenre = findViewById(R.id.etgenre2);
        etYear = findViewById(R.id.etYear);
        rgStars = findViewById(R.id.rgStars);
        btnInsert = findViewById(R.id.btnUpdate);
        btnShow = findViewById(R.id.btnDelete);


        al = new ArrayList<Movie>();
        aa = new ArrayAdapter<Movie>(this, android.R.layout.simple_list_item_1,al);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String title = etTitle.getText().toString();
                String genre = etgenre.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());
                int stars = 1;
                DBHelper dbh = new DBHelper(MainActivity.this);
                long inserted_song = dbh.insertmovie(title,genre,year, stars);
                al.clear();
                al.addAll(dbh.getAllmovies());
                aa.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Insert successful",
                        Toast.LENGTH_SHORT).show();
            }
        });


    }
}