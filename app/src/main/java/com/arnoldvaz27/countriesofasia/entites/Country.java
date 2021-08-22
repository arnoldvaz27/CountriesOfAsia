package com.arnoldvaz27.countriesofasia.entites;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

//this class consists of all the columns that will be available in the room database
@Entity(tableName = "countries") //table name
public class Country implements Serializable {

    //declaration of the fields (columns in the table)
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String Name;

    @ColumnInfo(name = "capital")
    private String Capital;

    @ColumnInfo(name = "flag_path")
    private String FlagPath;

    @ColumnInfo(name = "region")
    private String Region;

    @ColumnInfo(name = "subregion")
    private String SubRegion;

    @ColumnInfo(name = "population")
    private String Population;

    @ColumnInfo(name = "borders")
    private String Borders;

    @ColumnInfo(name = "languages")
    private String Languages;

    //getters and setters of all the declared columns
    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public String getSubRegion() {
        return SubRegion;
    }

    public void setSubRegion(String subRegion) {
        SubRegion = subRegion;
    }

    public String getPopulation() {
        return Population;
    }

    public void setPopulation(String population) {
        Population = population;
    }

    public String getBorders() {
        return Borders;
    }

    public void setBorders(String borders) {
        Borders = borders;
    }

    public String getLanguages() {
        return Languages;
    }

    public void setLanguages(String languages) {
        Languages = languages;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCapital() {
        return Capital;
    }

    public void setCapital(String capital) {
        Capital = capital;
    }

    public String getFlagPath() {
        return FlagPath;
    }

    public void setFlagPath(String flagPath) {
        this.FlagPath = flagPath;
    }
}
