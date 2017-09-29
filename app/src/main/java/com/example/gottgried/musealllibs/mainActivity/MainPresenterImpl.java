package com.example.gottgried.musealllibs.mainActivity;

import android.text.TextUtils;
import android.widget.EditText;

import com.example.gottgried.musealllibs.R;
import com.example.gottgried.musealllibs.models.DbWorker;
import com.example.gottgried.musealllibs.pojo.Dog;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import io.realm.Realm;

import static com.example.gottgried.musealllibs.mainActivity.MainActivityMVPContract.*;

/**
 * Created by Gottgried on 29.09.2017.
 */

public class MainPresenterImpl extends MvpBasePresenter<MainActivityMVPContract.View> implements Presenter {

    private MainActivityMVPContract.Model model;

    public MainPresenterImpl() {
        model = new DbWorker(Realm.getDefaultInstance());
    }


    @Override
    public void saveDog(EditText etDogName, EditText etDogMark, EditText etDogAge) {

        if(TextUtils.isEmpty(etDogName.getText()) || TextUtils.isEmpty(etDogMark.getText()) || TextUtils.isEmpty(etDogAge.getText())){
            getView().showSnackBarMsg(R.string.msg_error_emty_fields);
        }else {
            model.saveDog(new Dog(etDogName.getText().toString(), etDogMark.getText().toString(), Integer.parseInt(etDogAge.getText().toString())));
            if(isViewAttached()) {
                getView().showSnackBarMsg(R.string.msg_success);
            }
        }
    }

    @Override
    public void deleteDog() {

    }

    @Override
    public void getAllDogs() {
        if(isViewAttached()){
            getView().onClickShowAllDogs();
        }
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if(retainInstance){
            model.closeModel();
        }
    }
}
