package com.androidtutorialshub.loginregister.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.androidtutorialshub.loginregister.R;
import com.androidtutorialshub.loginregister.model.Post;
import com.androidtutorialshub.loginregister.sql.DatabaseHelper;
import com.androidtutorialshub.loginregister.sql.dbHelper;

/**
 * Created by nurs on 10.03.18.
 */

public class PostActivity extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = PostActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutPostName;
    private TextInputLayout textInputLayoutPostDis;

    private TextInputEditText textInputEditTextPostName;
    private TextInputEditText textInputEditTextPostDis;
    private AppCompatTextView textViewName;

    private AppCompatButton appCompatButtonPost;
    private dbHelper dbhelper;
    private Post post;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_post);
        getSupportActionBar().hide();
        initViews();
        initListeners();
        initObjects();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appCompatButtonPost:
                post.setName(textInputEditTextPostName.getText().toString().trim());
                post.setPostDis(textInputEditTextPostDis.getText().toString().trim());
                post.setPostOwner(textViewName.getText().toString().trim());
                dbhelper.addPost(post);
                Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
                Intent postsIntent = new Intent(getApplicationContext(), PostListActivity.class);
                startActivity(postsIntent);
                break;
        }
    }
    private void initObjects() {
        dbhelper = new dbHelper(activity);
        post = new Post();
    }

    private void initListeners() {
        appCompatButtonPost.setOnClickListener(this);
    }
    private void initViews() {

        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutPostName = (TextInputLayout) findViewById(R.id.textInputLayoutPostName);
        textInputLayoutPostDis = (TextInputLayout) findViewById(R.id.textInputLayoutPostDis);

        textInputEditTextPostName = (TextInputEditText) findViewById(R.id.textInputEditTextPostName);
        textInputEditTextPostDis = (TextInputEditText) findViewById(R.id.textInputEditTextPostDis);
        textViewName = (AppCompatTextView) findViewById(R.id.textViewName);
        String emailFromIntent = getIntent().getStringExtra("EMAIL");
        textViewName.setText(emailFromIntent);
        appCompatButtonPost = (AppCompatButton) findViewById(R.id.appCompatButtonPost);
    }

}
