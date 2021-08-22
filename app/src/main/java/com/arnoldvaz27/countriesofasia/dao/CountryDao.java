package com.arnoldvaz27.countriesofasia.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.arnoldvaz27.countriesofasia.entites.Country;

import java.util.List;

//All the queries in the application are mentioned in these place
@Dao
public interface CountryDao {

    @Query("SELECT * FROM countries ORDER BY id DESC")
    List<Country> getAllCountries();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCountry(Country country);

    @Query("SELECT COUNT(*) FROM countries")
    int getDataCount();

    @Query("DELETE FROM countries")
    void deleteAll();
}
