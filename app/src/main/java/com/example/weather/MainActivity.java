package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weather.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;
    private ImageView mainImage;
    private TextView mainTemp;
    private TextView condition;
    private TextView rain;
    private TextView wind;
    private TextView humidity;

    private TextView date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);
        HealperApi healperApi = new HealperApi();
        healperApi.fetchWeatherData(new HealperApi.WeatherDataCallback() {
            @Override
            public void onSuccess(ArrayList<Day> days) {
                if (!days.isEmpty()) {
                    Day firstDay = days.get(0);
                    activityMainBinding.textViewTemp.setText(String.valueOf(firstDay.getAvgTemp()));
                    activityMainBinding.textViewCondition.setText(firstDay.getCondition());
                    activityMainBinding.textViewRain.setText(String.valueOf(firstDay.getAvgTemp())+ " %");
                    activityMainBinding.textViewWind.setText(String.valueOf(firstDay.getMaxWind())+ " km/h");
                    activityMainBinding.textViewHumudity.setText(String.valueOf(firstDay.getHumidity())+ " %");
                    updateImage(firstDay.getCondition());
                    activityMainBinding.textViewDate.setText(firstDay.getDate());
                }
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void updateImage(String condition) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (condition.equals("Sunny") || condition.equals("Clear")) {
                    activityMainBinding.imageViewMain.setImageResource(R.drawable.sunny);
                } else if (condition.equals("Partly cloudy") || condition.equals("Cloudy")) {
                    activityMainBinding.imageViewMain.setImageResource(R.drawable.rainy);
                } else if (condition.equals("Patchy light rain") || condition.equals("Light rain") || condition.equals("Moderate rain") || condition.equals("Heavy rain")) {
                    activityMainBinding.imageViewMain.setImageResource(R.drawable.rain);
                }
            }
        });
    }
}
