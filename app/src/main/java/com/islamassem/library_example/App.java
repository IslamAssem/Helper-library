
package com.islamassem.library_example;

import android.app.Application;
import android.content.Context;

import com.islamassem.utils.LocalHelper;
import com.islamassem.utils.SharedPreferencesHelper;

public class App extends Application {
    public  App(){
        SharedPreferencesHelper.INSTANCE.setMContext(this);
    }
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocalHelper.INSTANCE.onAttachApp(base));
    }
}
