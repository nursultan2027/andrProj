package com.androidtutorialshub.loginregister.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import com.androidtutorialshub.loginregister.R;
import com.androidtutorialshub.loginregister.model.Post;
import com.androidtutorialshub.loginregister.sql.dbHelper;

import java.util.List;

/**
 * Created by nurs on 10.03.18.
 */

public class PostListActivity extends AppCompatActivity{
    private final AppCompatActivity activity = PostListActivity.this;

    private RecyclerView recyclerViewPosts;
    private List<Post> listPosts;
    private ListView countriesList;
    private dbHelper dbhelper;
    private NestedScrollView nestedScrollView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_list);
        getSupportActionBar().hide();
        initObjects();
    }
    private void initObjects() {
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);
        dbhelper = new dbHelper(activity);
    }
}
