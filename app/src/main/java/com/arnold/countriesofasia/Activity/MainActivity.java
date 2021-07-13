package com.arnold.countriesofasia.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.arnold.countriesofasia.Adapter.CountryAdapter;
import com.arnold.countriesofasia.JavaClasses.MySingleton;
import com.arnold.countriesofasia.R;
import com.arnold.countriesofasia.database.CountryDatabase;
import com.arnold.countriesofasia.entites.Country;
import com.arnold.countriesofasia.listeners.CountryListeners;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CountryListeners {

    // declaration of the various views and components
    ImageView imageView;
    String countryName, countryBorders, population, subregion, region, capital, flag, languages;
    private RecyclerView countryRecyclerView;
    private List<Country> countriesList;
    private CountryAdapter countryAdapter;
    JSONObject cityInfo;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getResources().getColor(R.color.holo_red));
        setContentView(R.layout.activity_main);

        //initializing
        loadingBar = new ProgressDialog(this);
        imageView = findViewById(R.id.delete);
        countryRecyclerView = findViewById(R.id.RecyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        countryRecyclerView.setLayoutManager(linearLayoutManager);

        //Checking whether the device android version is 9/P or above
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            findViewById(R.id.card).setVisibility(View.VISIBLE);
        }

        //Checking whether the device is connected to internet or not
        //if part executed if the device is connected if it is not connected the else part will be executed.
        if (isConnected()) {
            @SuppressLint("StaticFieldLeak")
            class Count extends AsyncTask<Void, Void, List<Country>> {
                int a;

                @Override
                protected List<Country> doInBackground(Void... voids) {
                    a = CountryDatabase.getCountryDatabase(getApplicationContext()).countryDao().getDataCount();
                    if (a == 50) {
                        Display();
                    } else {
                        @SuppressLint("StaticFieldLeak")
                        class Delete extends AsyncTask<Void, Void, List<Country>> {
                            @Override
                            protected List<Country> doInBackground(Void... voids) {
                                CountryDatabase.getCountryDatabase(getApplicationContext()).countryDao().deleteAll();
                                return null;
                            }

                            @Override
                            protected void onPostExecute(List<Country> countries) {
                                super.onPostExecute(countries);
                                if (isConnected()) {
                                    Fetching();
                                } else {
                                    showToast("Please connect to internet, and click on the refresh icon");
                                }
                            }
                        }
                        new Delete().execute();

                    }
                    return null;
                }

                @Override
                protected void onPostExecute(List<Country> countries) {
                    super.onPostExecute(countries);

                }
            }
            new Count().execute();
        } else {
            @SuppressLint("StaticFieldLeak")
            class Count extends AsyncTask<Void, Void, List<Country>> {
                int a;

                @Override
                protected List<Country> doInBackground(Void... voids) {
                    a = CountryDatabase.getCountryDatabase(getApplicationContext()).countryDao().getDataCount();
                    if (a == 50) {
                        Display();
                    } else {
                        @SuppressLint("StaticFieldLeak")
                        class Delete extends AsyncTask<Void, Void, List<Country>> {
                            @Override
                            protected List<Country> doInBackground(Void... voids) {
                                CountryDatabase.getCountryDatabase(getApplicationContext()).countryDao().deleteAll();
                                return null;
                            }

                            @Override
                            protected void onPostExecute(List<Country> countries) {
                                super.onPostExecute(countries);
                                if (isConnected()) {
                                    Fetching();
                                } else {
                                    showToast("Please connect to internet, and click on the refresh icon");
                                }
                            }
                        }
                        new Delete().execute();

                    }
                    return null;
                }

                @Override
                protected void onPostExecute(List<Country> countries) {
                    super.onPostExecute(countries);

                }
            }
            new Count().execute();
        }
        //end of the checking of the network state

        //imageview with click listener, which will delete the data after the user clicks on this view
        imageView.setOnClickListener(v -> DeletingData());

        //imageview with click listener, which will reload the whole activity without any animation.
        findViewById(R.id.refresh).setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            finish();
            overridePendingTransition(0, 0);
            startActivity(i);
            overridePendingTransition(0, 0);
        });
    }

    //method for fetching the data from the specified url (api) and saving it in the room database locally
    private void Fetching() {
        //loading bar to show the user that the data is retrieving
        loadingBar.setTitle("Fetching Details");
        loadingBar.setMessage("Please Wait while we are receiving the details for you...");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.getProgress();
        loadingBar.show();

        String url = "https://restcountries.eu/rest/v2/region/asia"; //url of the api and the region = asia

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < 50; i++) {
                        languages = null;
                        cityInfo = response.getJSONObject(i);
                        countryName = cityInfo.getString("name");
                        countryBorders = cityInfo.getString("borders");
                        population = cityInfo.getString("population");
                        subregion = cityInfo.getString("subregion");
                        region = cityInfo.getString("region");
                        capital = cityInfo.getString("capital");
                        JSONArray movies = cityInfo.getJSONArray("languages");
                        for (int j = 0; j < movies.length(); j++) {
                            JSONObject details = movies.getJSONObject(j);
                            String title = details.getString("name");
                            String title2 = details.getString("nativeName");
                            languages += title+" : "+title2 + " ,\n";
                        }
                        languages = languages.replaceAll("null","");
                        languages = languages.substring(0,languages.length() - 2);
                        flag = cityInfo.getString("flag");
                        Country country = new Country();
                        country.setName(countryName);
                        country.setBorders(countryBorders);
                        country.setRegion(region);
                        country.setSubRegion(subregion);
                        country.setLanguages(languages);
                        country.setPopulation(population);
                        country.setCapital(capital);
                        country.setFlagPath(flag);

                        //Async task used as the saving of the data to room database will be executed on the background thread
                        @SuppressLint("StaticFieldLeak")
                        class SaveNoteTask extends AsyncTask<Void, Void, Void> {

                            @Override
                            protected Void doInBackground(Void... voids) {
                                CountryDatabase.getCountryDatabase(getApplicationContext()).countryDao().insertCountry(country); //inserting data in room database
                                return null;
                            }

                            @Override
                            protected void onPostExecute(Void aVoid) {
                                super.onPostExecute(aVoid);

                            }
                        }

                        new SaveNoteTask().execute();
                    }

                    Display();
                    loadingBar.dismiss(); //loading bar dismissed as the data has been loaded and is saved in the database
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, error -> Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show()); //in case of any error this toast will be executed

        MySingleton.getInstance(MainActivity.this).addToRequestQueue(request);

    }

    //method to check the network state of device
    private boolean isConnected() {
        boolean connected;
        try {
            ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, (CharSequence) e, Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    //method to display all the data that has been saved in the room database
    private void Display() {
        countriesList = new ArrayList<>();
        countryAdapter = new CountryAdapter(countriesList, MainActivity.this);
        countryRecyclerView.setHasFixedSize(true);
        countryRecyclerView.setAdapter(countryAdapter);
        getCountries();
    }

    //method to get all the countries from the database with all the data so that the display method can display it
    private void getCountries() {
        @SuppressLint("StaticFieldLeak")
        class GetCountriesTask extends AsyncTask<Void, Void, List<Country>> {

            @Override
            protected List<Country> doInBackground(Void... voids) {
                return CountryDatabase.getCountryDatabase(getApplicationContext())
                        .countryDao().getAllCountries();
            }

            @Override
            protected void onPostExecute(List<Country> countries) {
                super.onPostExecute(countries);
                countriesList.addAll(countries);
                countryAdapter.notifyDataSetChanged();

            }
        }

        new GetCountriesTask().execute();
    }

    //item clicked method from the listener interface
    @Override
    public void onItemClicked(Country country, int position) {
        //nothing to do here as there is no onclick happening till now on the view, it can be used for further upgrades in the app
    }

    //method for deleting all the data from the room database
    public void DeletingData(){
        class Delete extends AsyncTask<Void, Void, List<Country>> {
            @Override
            protected List<Country> doInBackground(Void... voids) {
                CountryDatabase.getCountryDatabase(getApplicationContext()).countryDao().deleteAll();
                return null;
            }

            @Override
            protected void onPostExecute(List<Country> countries) {
                super.onPostExecute(countries);
                showToast("Data Deleted");
            }
        }
        new Delete().execute();
        countriesList = new ArrayList<>();
        countryAdapter = new CountryAdapter(countriesList, MainActivity.this);
        countryRecyclerView.setHasFixedSize(true);
        countryRecyclerView.setAdapter(countryAdapter);
    }

    //method to display custom toast to the user
    void showToast(String message) {
        Toast toast = new Toast(MainActivity.this);

        @SuppressLint("InflateParams") View view = LayoutInflater.from(MainActivity.this)
                .inflate(R.layout.toast_layout, null);

        TextView tvMessage = view.findViewById(R.id.Message); //text view from the custom toast layout
        tvMessage.setText(message);

        toast.setView(view);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }
}