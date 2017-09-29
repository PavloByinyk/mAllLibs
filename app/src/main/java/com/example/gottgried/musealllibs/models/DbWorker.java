package com.example.gottgried.musealllibs.models;

import android.support.design.widget.Snackbar;
import android.util.Log;

import com.example.gottgried.musealllibs.mainActivity.MainActivityMVPContract;
import com.example.gottgried.musealllibs.pojo.Dog;

import io.realm.Realm;

import static com.example.gottgried.musealllibs.mainActivity.MainActivity.LOG_TAG;

/**
 * Created by Gottgried on 29.09.2017.
 */

public class DbWorker implements MainActivityMVPContract.Model {
    public static final String LOG_TAG = DbWorker.class.getName();

    private Realm realmDb;

    public DbWorker(Realm realmDb) {
        this.realmDb = realmDb;
    }

    @Override
    public void saveDog(final Dog dog) {
        realmDb.beginTransaction();
        Dog dogR = realmDb.copyToRealm(dog);
        realmDb.commitTransaction();
    }

    @Override
    public void deleteDog() {

    }

    @Override
    public void getAllDogs() {

    }

    @Override
    public void closeModel() {
        realmDb.close();
    }
}
