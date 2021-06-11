package com.example.simplenews.Api;

import android.content.res.Resources;
import androidx.core.os.ConfigurationCompat;
import java.util.Locale;

public class CountryCodeProvider {

    private final Locale locale = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration()).get(0);
    private final String[] codeArray = new String[]{"ae", "ar", "at", "au", "be", "bg", "br", "ca", "ch", "cn",
            "co", "cu", "cz", "de", "eg", "fr", "gb", "gr", "hk", "hu",
            "id", "ie", "il", "in", "it", "jp", "kr", "lt", "lv", "ma",
            "mx", "my", "ng", "nl", "no", "nz", "ph", "pl", "pt", "ro",
            "rs", "ru", "sa", "se", "sg", "si", "sk", "th", "tr", "tw",
            "ua", "us", "ve", "za"};

    public CountryCodeProvider(){
    }

    public String getCountryCode(){
        for (String s : codeArray){
            if (locale.getCountry().toLowerCase().equals(s)){
                return s;
            }
        }
        return "us";
    }

    public boolean containsCountryCode(){
        for (String s : codeArray){
            if (locale.getCountry().toLowerCase().equals(s)){
                return true;
            }
        }
        return false;
    }

}
