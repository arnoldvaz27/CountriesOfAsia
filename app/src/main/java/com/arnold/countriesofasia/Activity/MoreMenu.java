package com.arnold.countriesofasia.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.arnold.countriesofasia.R;

public class MoreMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getResources().getColor(R.color.holo_red));
        setContentView(R.layout.more_menu);

        findViewById(R.id.back).setOnClickListener(v -> finish());

        findViewById(R.id.privacy).setOnClickListener(v -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://programmingdrive.blogspot.com/2021/08/Privacy%20Policy%20-%20Countries%20Of%20Asia.html"))));
        findViewById(R.id.terms).setOnClickListener(v -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://programmingdrive.blogspot.com/2021/08/terms-and-condition-for-countries-of.html"))));
        findViewById(R.id.developer).setOnClickListener(v -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/arnoldvaz27/CountriesOfAsia"))));
        findViewById(R.id.website).setOnClickListener(v -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://linktr.ee/arnoldvaz"))));
        findViewById(R.id.youtube).setOnClickListener(v -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/VvjG3GofHZk"))));
    }
}