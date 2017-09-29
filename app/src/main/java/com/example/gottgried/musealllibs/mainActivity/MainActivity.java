package com.example.gottgried.musealllibs.mainActivity;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import android.widget.EditText;

import com.example.gottgried.musealllibs.R;
import com.example.gottgried.musealllibs.adapters.MainDogsAdapter;
import com.example.gottgried.musealllibs.pojo.Dog;
import com.hannesdorfmann.mosby3.mvp.MvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.RealmResults;


public class MainActivity extends MvpActivity<MainActivityMVPContract.View, MainActivityMVPContract.Presenter> implements MainActivityMVPContract.View {
    public static final String LOG_TAG = MainActivity.class.getName();
    private FloatingActionButton fab;
    private MainDogsAdapter mainDogsAdapter;

    @BindView(R.id.et_dog_name)   EditText etDogName;
    @BindView(R.id.et_dog_mark)   EditText etDogMark;
    @BindView(R.id.et_dog_age)   EditText etDogAge;
    @BindView(R.id.recycler_view_main)  RecyclerView recyclerView;


    @Override
    protected void onStart() {
        super.onStart();
        getPresenter().attachView(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getPresenter().detachView(true);
    }

    @NonNull
    @Override
    public MainActivityMVPContract.Presenter createPresenter() {
        return new MainPresenterImpl();
    }

    @OnClick(R.id.btn_add_dog)
    @Override
    public void onClickAddDog() {
        getPresenter().saveDog(etDogName, etDogMark, etDogAge);
    }

    @OnClick(R.id.btn_delete_dog)
    @Override
    public void onClickDeleteDog() {

    }

    @OnClick(R.id.btn_show_all_dogs)
    @Override
    public void onClickShowAllDogs() {
        getPresenter().getAllDogs();
    }

    @OnClick(R.id.btn_clear_list)
    @Override
    public void onClickClearList() {
        recyclerView.setAdapter(null);
    }

    @Override
    public void setListToAdapter(RealmResults<Dog> list) {
        mainDogsAdapter = new MainDogsAdapter(list);
        recyclerView.setAdapter(mainDogsAdapter);
    }

    @Override
    public void showSnackBarMsg(int masg) {
        Snackbar.make(fab, masg, Snackbar.LENGTH_SHORT).setAction("Action" , null).show();
    }
}
