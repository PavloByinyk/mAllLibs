package com.example.gottgried.musealllibs;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import java.util.regex.Pattern;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Gottgried on 28.09.2017.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(getApplicationContext());

        initializeStetho(this);

        //------ init the db ----------------
        RealmConfiguration config = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().name("TheDb.realm").build();
        Realm.setDefaultConfiguration(config);
    }

    private void initializeStetho(final Context context) {

        Stetho.initialize(Stetho.newInitializerBuilder(context)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(context))
                .enableWebKitInspector(RealmInspectorModulesProvider.builder(context)
                        .withDescendingOrder()
                        .withLimit(1000)
                        .databaseNamePattern(Pattern.compile(".+\\.realm"))
                        .build())
                .build());
    }
}
