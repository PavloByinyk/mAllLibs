package com.example.gottgried.musealllibs.mainActivity;

import android.widget.EditText;

import com.example.gottgried.musealllibs.pojo.Dog;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import io.realm.RealmResults;

/**
 * Created by Gottgried on 28.09.2017.
 */

public interface MainActivityMVPContract {

    interface View extends MvpView{
        void onClickAddDog();
        void onClickDeleteDog();
        void onClickShowAllDogs();
        void onClickClearList();
        void setListToAdapter(RealmResults<Dog> listDogs);
        void showSnackBarMsg(int masg);
    }

    interface Presenter extends MvpPresenter<MainActivityMVPContract.View>{
        void saveDog(EditText nameField, EditText markField, EditText ageField);
        void deleteDog();
        void getAllDogs();
    }

    interface Model{
        void saveDog(Dog dog);
        void deleteDog();
        RealmResults<Dog> getAllDogs();

        void closeModel();
    }


}
