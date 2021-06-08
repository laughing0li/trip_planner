package com.banjo.planner.utils;

import com.banjo.planner.pojo.Weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Tools
 * This class is used to parse the JSON
 * data returned by the weather website
 */

public class ParseJson {

    /**
     * @param url A city's weather url
     * @return Read the weather information and return it as String
     */
    public String parseUrl(String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL urlObject = new URL(url);
            URLConnection uc = urlObject.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                // Add each row of the acquired data to the json object
                json.append(inputLine);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json.toString();
    }

    /**
     * Use JSONObject and JSONArray to extract
     * all the data we need. Then encapsulate it
     * into a list containing the weather information
     * of the current city for five days
     * @param url A city's weather url
     * @return return the weather list
     */
    public List<Weather> getWeatherInfos(String url) {

        // Declare an empty list for storing weather information
        List<Weather> weatherList = new ArrayList<>();
        String json = parseUrl(url);
        JSONObject obj = new JSONObject(json);

        // First get the country and city information from the city of the JSON object
        JSONObject city = obj.getJSONObject("city");
        String country = city.getString("country");
        String cityName = city.getString("name");

        // The weather and other attributes are stored in the list Array of the JSON object
        JSONArray list = obj.getJSONArray("list");

        // use for loop to get all the single weather infos
        for (int i = 0; i < list.length(); i++) {

            // use weather object to receive the infos
            Weather weather = new Weather();
            weather.setCity(cityName);
            weather.setCountry(country);
            JSONObject weatherDetail = list.getJSONObject(i);

            // get time
            String time = weatherDetail.getString("dt_txt");
            weather.setTime(time);

            // Extract the main JSON object from the weather information,
            JSONObject main = weatherDetail.getJSONObject("main");

            // get the temperature from the main object
            Double temp = main.getDouble("temp");
            weather.setTemp(temp);

            // The specific information of the weather is stored in the JSON array weather
            JSONArray rainOrNot = weatherDetail.getJSONArray("weather");
            for (int j=0; j<rainOrNot.length(); j++) {
                JSONObject weather_like = rainOrNot.getJSONObject(j);
                weather.setWeather(weather_like.getString("main"));
            }
            // Add all the obtained weather objects to the weatherList
            weatherList.add(weather);
        }
        return weatherList;
    }

}
