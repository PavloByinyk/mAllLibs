package com.example.gottgried.musealllibs;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gottgried.musealllibs.models.Dog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity {
    public static final String LOG_TAG = MainActivity.class.getName();
    private Realm realmDb;

    @BindView(R.id.et_dog_name)   EditText etDogName;
    @BindView(R.id.et_dog_mark)   EditText etDogMark;
    @BindView(R.id.et_dog_age)   EditText etDogAge;
    @BindView(R.id.btn_add_dog)   Button btnAddDog;
    @BindView(R.id.btn_delete_dog)   Button btnDeleteDog;
    @BindView(R.id.btn_show_all_dogs)   Button btnShowAllDogs;

    @OnClick(R.id.btn_add_dog)
    public void addDogToDb(final View view){
        realmDb.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                Dog dog = bgRealm.createObject(Dog.class);
                dog.setName(etDogName.getText().toString());
                dog.setMark(etDogMark.getText().toString());
                dog.setAge(Integer.valueOf(etDogAge.getText().toString()));
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Snackbar.make(view, "the dog was added succesfully", Snackbar.LENGTH_SHORT).setAction("Action" , null).show();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.d(LOG_TAG, "onError: ***" + error.getMessage().toString());
            }
        });

    }

    @OnClick(R.id.btn_delete_dog)
    public void deleteDogFromDb(View view){}

    @OnClick(R.id.btn_show_all_dogs)
    public void shoAllDogs(View view){}




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        realmDb = Realm.getDefaultInstance();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
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
        realmDb.close();
    }
}
