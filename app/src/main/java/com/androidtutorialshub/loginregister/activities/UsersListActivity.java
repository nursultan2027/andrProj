package com.androidtutorialshub.loginregister.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.androidtutorialshub.loginregister.R;
import com.androidtutorialshub.loginregister.model.User;

import java.util.ArrayList;
import java.util.List;


public class UsersListActivity extends AppCompatActivity implements View.OnClickListener {

    private AppCompatActivity activity = UsersListActivity.this;
    private AppCompatTextView textViewName;
    private RecyclerView recyclerViewUsers;
    private List<User> listUsers;
    private AppCompatButton appCompatButtonAddPost;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);
        getSupportActionBar().setTitle("");
        initViews();
        initListeners();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appCompatButtonAddPost:
                Intent intentPost= new Intent(getApplicationContext(), MainActivity.class);
                intentPost.putExtra("EMAIL", textViewName.getText().toString().trim());
                startActivity(intentPost);
                break;
        }
    }

    private void initListeners() {
        appCompatButtonAddPost.setOnClickListener(this);
    }

    private void initViews() {
        appCompatButtonAddPost = (AppCompatButton) findViewById(R.id.appCompatButtonAddPost);
        textViewName = (AppCompatTextView) findViewById(R.id.textViewName);
        String emailFromIntent = getIntent().getStringExtra("EMAIL");
        textViewName.setText(emailFromIntent);
    }


}
