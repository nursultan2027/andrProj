package com.androidtutorialshub.loginregister.adapters;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidtutorialshub.loginregister.R;
import com.androidtutorialshub.loginregister.model.Post;

import java.util.List;

public class PostsRecyclerAdapter extends RecyclerView.Adapter<PostsRecyclerAdapter.PostViewHolder> {

    private List<Post> listPosts;

    public PostsRecyclerAdapter(List<Post> listPosts) {
        this.listPosts = listPosts;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_post_recycler, parent, false);

        return new PostViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        holder.textViewName.setText(listPosts.get(position).getName());
        holder.textViewDis.setText(listPosts.get(position).getPostDis());
        holder.textViewOwner.setText(listPosts.get(position).getPostOwner());
    }

    @Override
    public int getItemCount() {
        Log.v(PostsRecyclerAdapter.class.getSimpleName(),""+listPosts.size());
        return listPosts.size();
    }


    /**
     * ViewHolder class
     */
    public class PostViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textViewName;
        public AppCompatTextView textViewDis;
        public AppCompatTextView textViewOwner;

        public PostViewHolder(View view) {
            super(view);
            textViewName = (AppCompatTextView) view.findViewById(R.id.textViewName);
            textViewDis = (AppCompatTextView) view.findViewById(R.id.textViewEmail);
            textViewOwner = (AppCompatTextView) view.findViewById(R.id.textViewPassword);
        }
    }


}
