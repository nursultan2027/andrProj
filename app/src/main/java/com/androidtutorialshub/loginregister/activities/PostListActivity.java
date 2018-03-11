package com.androidtutorialshub.loginregister.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
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

public class PostListActivity extends AppCompatActivity{
    private final AppCompatActivity activity = PostListActivity.this;

    private RecyclerView recyclerViewPosts;
    private List<Post> listPosts;

    private PostsRecyclerAdapter postsRecyclerAdapter;
    private dbHelper dbhelper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_list);
        getSupportActionBar().hide();
        initObjects();
        listPosts = dbhelper.getAllPosts();
    }
    private void initObjects() {
        dbhelper = new dbHelper(activity);
    }
}
