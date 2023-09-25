package com.example.weather.Activitis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.weather.Adapters.HourlyAdapters;
import com.example.weather.Day;
import com.example.weather.Domains.Hourly;
import com.example.weather.HealperApi;
import com.example.weather.R;
import com.example.weather.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterHourly;
    private RecyclerView recyclerView;

    ActivityMainBinding activityMainBinding;
    private ArrayList<Day> daysArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        Log.d("MyLog", "created");

        HealperApi healperApi = new HealperApi();
        healperApi.fetchWeatherData(new HealperApi.WeatherDataCallback() {
            @Override
            public void onSuccess(ArrayList<Day> days) {
                Log.d("MyLog", "Success");
                if (!days.isEmpty()) {
//                    Log.d("MyLog", days.toString());
//                    daysArray = days;
//                    updateData(daysArray);

                    Day firstDay = days.get(0);
                    activityMainBinding.textViewTemp.setText(String.valueOf(firstDay.getAvgTemp()));
                    activityMainBinding.textViewCondition.setText(firstDay.getCondition());
                    activityMainBinding.textViewRain.setText(String.valueOf(firstDay.getAvgTemp()) + " %");
                    activityMainBinding.textViewWind.setText(String.valueOf(firstDay.getMaxWind()) + " km/h");
                    activityMainBinding.textViewHumidity.setText(String.valueOf(firstDay.getHumidity()) + " %");
                    updateImage(firstDay.getCondition());
                    activityMainBinding.textViewDate.setText(firstDay.getDate());
                }
            }
            @Override
            public void onFailure(Exception e) {
                Log.d("MyLog", "Exception");
                e.printStackTrace();
            }
        });

        initRecyclerview();

    }

    private void initRecyclerview() {
        ArrayList<Hourly> items = new ArrayList<>();

        for (int i = 0; i < 5; i++) {

        }
        items.add(new Hourly("9 pm", 28, "cloudy"));
        items.add(new Hourly("11 pm", 29, "sunny"));
        items.add(new Hourly("12 pm", 31, "wind"));
        items.add(new Hourly("1 am", 31, "rainy"));
        items.add(new Hourly("2 am", 30, "storm"));

        recyclerView = findViewById(R.id.view1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        adapterHourly = new HourlyAdapters(items);
        recyclerView.setAdapter(adapterHourly);
    }

//    public void updateData(ArrayList<Day> days){
//        Day firstDay = days.get(0);
//        activityMainBinding.textViewTemp.setText(String.valueOf(firstDay.getAvgTemp()));
//        activityMainBinding.textViewCondition.setText(firstDay.getCondition());
//        activityMainBinding.textViewRain.setText(String.valueOf(firstDay.getAvgTemp()) + " %");
//        activityMainBinding.textViewWind.setText(String.valueOf(firstDay.getMaxWind()) + " km/h");
//        activityMainBinding.textViewHumidity.setText(String.valueOf(firstDay.getHumidity()) + " %");
//        updateImage(firstDay.getCondition());
//        activityMainBinding.textViewDate.setText(firstDay.getDate());
//
//        updateRecyclerview(days,recyclerView);
//    }

    public void updateRecyclerview(ArrayList<Day> days, RecyclerView view){



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
