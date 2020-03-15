package com.rojer_ko.homework.lesson_4;

import android.app.Application;
import com.rojer_ko.homework.lesson_4.di.components.AppComponent;
import com.rojer_ko.homework.lesson_4.di.components.DaggerAppComponent;
import com.rojer_ko.homework.lesson_4.di.modules.DataModule;
import com.rojer_ko.homework.lesson_4.di.modules.NetworkModule;

public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent
                .builder()
                .dataModule(new DataModule())
                .networkModule(new NetworkModule())
                .build();
    }

    public AppComponent getAppComponent(){
        return this.appComponent;
    }

}
