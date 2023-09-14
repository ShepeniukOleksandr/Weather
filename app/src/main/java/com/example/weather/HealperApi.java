package com.example.weather;

import androidx.annotation.NonNull;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
public class HealperApi extends Day {
    private final OkHttpClient client = new OkHttpClient();

    public interface WeatherDataCallback {
        void onSuccess(ArrayList<Day> days);
        void onFailure(Exception e);
    }
    public void fetchWeatherData(final WeatherDataCallback callback){
        Request request = new Request.Builder()
                .url("https://api.weatherapi.com/v1/forecast.json?key=1f52bf057e1348168ef94025232308 &q=Vinnitsa&days=6&aqi=no&alerts=yes")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String jsonData = response.body().string();
                ArrayList<Day> days = parseWeatherData(jsonData);
                callback.onSuccess(days);
            }
        });
    }

    private static ArrayList<Day> parseWeatherData(String jsonData) {
        ArrayList<Day> days = new ArrayList<>();
        try{
//            JSONObject jsonObject = new JSONObject(jsonData);
//            JSONObject forecast = jsonObject.getJSONObject("forecast");
//            JSONArray forecastDays = forecast.getJSONArray("forecastday");
//            for (int i = 0; i < forecastDays.length(); i++) {
//                JSONObject dayData = forecastDays.getJSONObject(i);
//                String date = dayData.getString("date");
//                JSONObject day = dayData.getJSONObject("day");
//                double avgTempC = day.getDouble("avgtemp_c");
//                double maxWind = day.getDouble("maxwind_kph");
//                double chance_of_rain = day.getDouble("daily_chance_of_rain");
//                double humidity = day.getDouble("avghumidity");
//
//                JSONObject condition = day.getJSONObject("condition");
//                String conditionText = condition.getString("text");
//
//                Day dayW = new Day();
//                dayW.setDate(date);
//                dayW.setAvgTemp(avgTempC);
//                dayW.setCondition(conditionText);
//                dayW.setRain(chance_of_rain);
//                dayW.setMaxWind(maxWind);
//                dayW.setHumidity(humidity);
//                days.add(dayW);
//            }
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONObject location = jsonObject.getJSONObject("location");
            JSONObject current = jsonObject.getJSONObject("current");
            JSONObject condition = current.getJSONObject("condition");
            JSONObject forecast = jsonObject.getJSONObject("forecast");
            JSONArray forecastDays = forecast.getJSONArray("forecastday");
            String city = location.getString("name");
            for (int i = 0; i < forecastDays.length(); i++) {
                JSONObject dayData = forecastDays.getJSONObject(i);
                JSONObject day = dayData.getJSONObject("day");
                JSONObject dayCondition = day.getJSONObject("condition");
                String date;
                String text;
                double temp;
                double rain;
                double windSpeed;
                double humidity;

                if(i == 0){
                    date = location.getString("localtime");
                    text = condition.getString("text");
                    temp = current.getDouble("temp_c");
                    rain = current.getDouble("precip_mm");
                    windSpeed = current.getDouble("wind_kph");
                    humidity = current.getDouble("humidity");
                }else{
                    date = dayData.getString("date");
                    text = dayCondition.getString("text");
                    temp = day.getDouble("avgtemp_c");
                    rain = day.getDouble("daily_chance_of_rain");
                    windSpeed = day.getDouble("maxwind_kph");
                    humidity = day.getDouble("avghumidity");
                }
                Day dayW = new Day();
                dayW.setCity(city);
                dayW.setDate(date);
                dayW.setCondition(text);
                dayW.setAvgTemp(temp);
                dayW.setRainChance(rain);
                dayW.setMaxWind(windSpeed);
                dayW.setHumidity(humidity);
                days.add(dayW);
            }

        }
        catch(JSONException e){
            e.printStackTrace();
        }
        return days;
    }
}