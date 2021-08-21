package com.arnold.countriesofasia.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import com.arnold.countriesofasia.R;
import com.arnold.countriesofasia.databinding.MoreMenuBinding;

public class MoreMenu extends AppCompatActivity {

    MoreMenuBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getResources().getColor(R.color.holo_red));
        binding = DataBindingUtil.setContentView(this, R.layout.more_menu);  //binding xml layout to java file

        binding.back.setOnClickListener(v -> finish());

        binding.privacy.setOnClickListener(v -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://programmingdrive.blogspot.com/2021/08/Privacy%20Policy%20-%20Countries%20Of%20Asia.html"))));
        binding.terms.setOnClickListener(v -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://programmingdrive.blogspot.com/2021/08/terms-and-condition-for-countries-of.html"))));
        binding.developer.setOnClickListener(v -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/arnoldvaz27/CountriesOfAsia"))));
        binding.website.setOnClickListener(v -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://linktr.ee/arnoldvaz"))));
        binding.youtube.setOnClickListener(v -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/VvjG3GofHZk"))));
        binding.appStore.setOnClickListener(v -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://programmingdrive.blogspot.com/2021/08/Programming%20Drive%20App%20Store.html"))));
        binding.europe.setOnClickListener(v -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://programmingdrive.blogspot.com/2021/08/Programming%20Drive%20App%20Store.html"))));
        binding.americas.setOnClickListener(v -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://programmingdrive.blogspot.com/2021/08/Programming%20Drive%20App%20Store.html"))));
    }
}