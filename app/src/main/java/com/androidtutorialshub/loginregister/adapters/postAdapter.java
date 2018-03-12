//package com.androidtutorialshub.loginregister.adapters;
//import java.util.ArrayList;
//import java.util.List;
//
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.TextView;
//
//import com.androidtutorialshub.loginregister.R;
//import com.androidtutorialshub.loginregister.model.Post;
//
//public class postAdapter extends BaseAdapter {
//    Context ctx;
//    LayoutInflater lInflater;
//    List<Post> objects;
//
//    public postAdapter(Context context, List<Post> posts) {
//        ctx = context;
//        objects = posts;
//        lInflater = (LayoutInflater) ctx
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    }
//
//    // кол-во элементов
//    @Override
//    public int getCount() {
//        return objects.size();
//    }
//
//    // элемент по позиции
//    @Override
//    public Object getItem(int position) {
//        return objects.get(position);
//    }
//
//    // id по позиции
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    // пункт списка
////    @Override
////    public View getView(int position, View convertView, ViewGroup parent) {
////        View view = convertView;
////        if (view == null) {
////            view = lInflater.inflate(R.layout.item, parent, false);
////        }
////        Post p = getPost(position);
////        ((TextView) view.findViewById(R.id.tvDescr)).setText(p.getName());
////        ((TextView) view.findViewById(R.id.tvPrice)).setText(p.getPostDis());
////        ((TextView) view.findViewById(R.id.Owner)).setText(p.getPostDis());
////        return view;
////    }
////    Post getPost(int position) {
////        return ((Post) getItem(position));
////    }
////}