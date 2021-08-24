package com.arnoldvaz27.countriesofasia.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;

import com.arnoldvaz27.countriesofasia.R;
import com.arnoldvaz27.countriesofasia.databinding.SplashScreenBinding;

//splashscreen of the application
public class SplashScreen extends AppCompatActivity {

    SplashScreenBinding binding;
    private static final int SPLASH_SCREEN_TIME_OUT=5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getResources().getColor(R.color.background));
        getWindow().setNavigationBarColor(getResources().getColor(R.color.background));
        binding = DataBindingUtil.setContentView(this, R.layout.splash_screen);  //binding xml layout to java file

        binding.europe.setOnClickListener(v -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://programmingdrive.blogspot.com/2021/08/Countries%20Applications.html"))));
        binding.appStore.setOnClickListener(v -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://programmingdrive.blogspot.com/2021/08/Programming%20Drive%20App%20Store.html"))));

        // this new handler will move from splash screen to the main activity of the app after 2 sec or 2000 ms.
        new Handler().postDelayed(() -> {
            Intent i = new Intent(SplashScreen.this,
                    MainActivity.class);
            SplashScreen.this.startActivity(i);
            SplashScreen.this.finish();
        }, SPLASH_SCREEN_TIME_OUT);
    }
}