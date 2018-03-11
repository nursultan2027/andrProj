package com.androidtutorialshub.loginregister.activities;

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
import com.androidtutorialshub.loginregister.adapters.PostsRecyclerAdapter;
import com.androidtutorialshub.loginregister.adapters.UsersRecyclerAdapter;
import com.androidtutorialshub.loginregister.model.Post;
import com.androidtutorialshub.loginregister.model.User;
import com.androidtutorialshub.loginregister.sql.DatabaseHelper;
import com.androidtutorialshub.loginregister.sql.dbHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nurs on 10.03.18.
 */

public class PostListActivity extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = PostListActivity.this;
    private AppCompatTextView textViewName;
    private RecyclerView recyclerViewPosts;
    private List<Post> listPosts;

    private PostsRecyclerAdapter postsRecyclerAdapter;

    private dbHelper dbhelper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_list);
        getSupportActionBar().hide();
        initViews();
    }

    @Override
    public void onClick(View view) {

    }

    private void initViews() {
        textViewName = (AppCompatTextView) findViewById(R.id.textViewName);
        recyclerViewPosts= (RecyclerView) findViewById(R.id.recyclerViewPosts);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        listPosts = new ArrayList<>();
        postsRecyclerAdapter = new PostsRecyclerAdapter(listPosts);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewPosts.setLayoutManager(mLayoutManager);
        recyclerViewPosts.setItemAnimator(new DefaultItemAnimator());
        recyclerViewPosts.setHasFixedSize(true);
        recyclerViewPosts.setAdapter(postsRecyclerAdapter);
        dbhelper = new dbHelper(activity);

//        String emailFromIntent = getIntent().getStringExtra("EMAIL");
//        textViewName.setText(emailFromIntent);
        getDataFromSQLite();
    }

    /**
     * This method is to fetch all user records from SQLite
     */
    private void getDataFromSQLite() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listPosts.clear();
                listPosts.addAll(dbhelper.getAllPost());

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                postsRecyclerAdapter.notifyDataSetChanged();
            }
        }.execute();
    }
}
