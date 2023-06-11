package org.idnp.jetpackpagingsample;

import android.content.Context;

import androidx.room.Room;

import com.google.gson.Gson;

import org.idnp.jetpackpagingsample.entities.Country;
import org.idnp.jetpackpagingsample.model.AppDatabase;
import org.idnp.jetpackpagingsample.model.CountryDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class FillDatabase {
    public void fillDatabase(Context context) throws IOException {

        AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, "database-name").build();
        CountryDao countryDao = db.countryDao();

        InputStream inputStream = context.getAssets().open("countries.txt");
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        bufferedReader.close();

        String json = stringBuilder.toString();
        CountriesResponse countriesResponse = new Gson().fromJson(json, CountriesResponse.class);
        List<Country> countries = countriesResponse.countries;


        for (Country country : countries) {
            countryDao.insertAll(country);
        }
    }
}
