package com.example.gottgried.musealllibs.mainActivity;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;

import com.example.gottgried.musealllibs.R;
import com.hannesdorfmann.mosby3.mvp.MvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;


public class MainActivity extends MvpActivity<MainActivityMVPContract.View, MainActivityMVPContract.Presenter> implements MainActivityMVPContract.View {
    public static final String LOG_TAG = MainActivity.class.getName();
    private FloatingActionButton fab;

    @BindView(R.id.et_dog_name)   EditText etDogName;
    @BindView(R.id.et_dog_mark)   EditText etDogMark;
    @BindView(R.id.et_dog_age)   EditText etDogAge;

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

    }

    @Override
    public void showSnackBarMsg(int masg) {
        Snackbar.make(fab, masg, Snackbar.LENGTH_SHORT).setAction("Action" , null).show();
    }
}
