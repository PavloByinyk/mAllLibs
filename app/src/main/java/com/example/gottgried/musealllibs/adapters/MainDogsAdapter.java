package com.example.gottgried.musealllibs.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gottgried.musealllibs.R;
import com.example.gottgried.musealllibs.pojo.Dog;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.RealmResults;

/**
 * Created by Gottgried on 29.09.2017.
 */

public class MainDogsAdapter extends RecyclerView.Adapter<MainDogsAdapter.DogsViewHolder>{

    private RealmResults<Dog> listDogs;

    public MainDogsAdapter(RealmResults<Dog> listDogs) {
        this.listDogs = listDogs;
        this.listDogs.addChangeListener(new OrderedRealmCollectionChangeListener<RealmResults<Dog>>() {
            @Override
            public void onChange(RealmResults<Dog> dogs, OrderedCollectionChangeSet changeSet) {
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public DogsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dogs, parent, false);
        return new DogsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DogsViewHolder holder, int position) {
        holder.bind(listDogs.get(position));
    }

    @Override
    public int getItemCount() {
        return listDogs.size();
    }


    public static class DogsViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_item_dog_name) TextView name;
        @BindView(R.id.tv_item_dog_mark) TextView mark;
        @BindView(R.id.tv_item_dog_age) TextView age;
        @BindView(R.id.imageView)   ImageView imageView;

        public DogsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Dog dog){
            name.setText(dog.getName());
            mark.setText(dog.getMark());
            age.setText(String.valueOf(dog.getAge()));

            Random rnd = new Random();
            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));

            imageView.setBackgroundColor(color);

        }
    }
}
