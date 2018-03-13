package com.androidtutorialshub.loginregister.activities;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.androidtutorialshub.loginregister.R;

import com.androidtutorialshub.loginregister.sql.DbCon;


public class MainActivity extends AppCompatActivity {

    EditText name, surname;
    TextView textViewName;
    Button btnAdd;
    String emailFromIntent;
    ListView lst;
    DbCon dbcon;
    DbCon.DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.etName);
        surname = (EditText) findViewById(R.id.etSurname);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        textViewName = (TextView) findViewById(R.id.textView);
        dbcon=new DbCon(this);
        emailFromIntent = getIntent().getStringExtra("EMAIL");
        textViewName.setText(emailFromIntent);
        load();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    dbcon.insert(name.getText().toString(), surname.getText().toString(), textViewName.getText().toString());
                    load();
                } catch (Exception e) {

                }
            }
        });

            lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Cursor c=dbcon.selected(id);
                    Intent intent = new Intent(MainActivity.this,ShowDetails.class);
                    String sendName=c.getString(1);
                    String sendSurname=c.getString(2);
                    String sendPhone=c.getString(3);
                    String sendId=c.getString(0);
                    intent.putExtra("sendName",sendName);
                    intent.putExtra("sendSurname",sendSurname);
                    intent.putExtra("EMAIL", textViewName.getText().toString());
                    intent.putExtra("sendPhone",sendPhone);
                    intent.putExtra("sendId",sendId);
                    startActivity(intent);
                }
            });
    }


    public void load() {
        Cursor cursor = null;
        try {
            dbcon.open();
            cursor = dbcon.readAll();
        } catch (Exception e) {

        }
        String[] from = new String[]{ dbHelper.ID, dbHelper.NAME, dbHelper.SURNAME, dbHelper.PHONE};
        int[] to = new int[]{ R.id.tvId, R.id.tvName, R.id.tvSurname, R.id.tvPhone};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(MainActivity.this, R.layout.showlist, cursor, from, to);
        adapter.notifyDataSetChanged();
        lst = (ListView) findViewById(R.id.lst);
        lst.setAdapter(adapter);
    }
}
