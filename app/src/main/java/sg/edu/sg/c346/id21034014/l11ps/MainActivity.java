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

    EditText etTitle, etSinger, etYear;
    Button btnInsert, btnShow;
    RadioGroup rgStars;
    ArrayList<Song> al;
    ArrayAdapter<Song> aa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = findViewById(R.id.etTitle);
        etSinger = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);
        rgStars = findViewById(R.id.rgStars);
        btnInsert = findViewById(R.id.btnUpdate);
        btnShow = findViewById(R.id.btnDelete);


        al = new ArrayList<Song>();
        aa = new ArrayAdapter<Song>(this, android.R.layout.simple_list_item_1,al);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String title = etTitle.getText().toString();
                String singer = etSinger.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());
                int stars = 1;
                DBHelper dbh = new DBHelper(MainActivity.this);
                long inserted_song = dbh.insertSong(title,singer,year, stars);
                al.clear();
                al.addAll(dbh.getAllSongs());
                aa.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Insert successful",
                        Toast.LENGTH_SHORT).show();
            }
        });


    }
}