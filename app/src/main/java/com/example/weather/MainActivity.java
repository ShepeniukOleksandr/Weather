package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_main);

        mainImage = findViewById(R.id.imageView_main);
        mainTemp = findViewById(R.id.textView_Temp);
        condition = findViewById(R.id.textView_condition);
        rain = findViewById(R.id.textView_Rain);
        wind = findViewById(R.id.textView_wind);
        humidity = findViewById(R.id.textView9);
        date = findViewById(R.id.textView_date);

        HealperApi healperApi = new HealperApi();

        healperApi.fetchWeatherData(new HealperApi.WeatherDataCallback() {
            @Override
            public void onSuccess(ArrayList<Day> days) {
                if (!days.isEmpty()) {
                    Day firstDay = days.get(0);
                    mainTemp.setText(String.valueOf(firstDay.getAvgTemp()));
                    condition.setText(firstDay.getCondition());
                    rain.setText(String.valueOf(firstDay.getRainChance()));
                    wind.setText(String.valueOf(firstDay.getMaxWind()) + " km/h");
                    humidity.setText(String.valueOf(firstDay.getHumidity()) + " %");
                    updateImage(firstDay.getCondition());
                    date.setText(firstDay.getDate());
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
                    mainImage.setImageResource(R.drawable.sunny);
                } else if (condition.equals("Partly cloudy") || condition.equals("Cloudy")) {
                    mainImage.setImageResource(R.drawable.rainy);
                } else if (condition.equals("Patchy light rain") || condition.equals("Light rain") || condition.equals("Moderate rain") || condition.equals("Heavy rain")) {
                    mainImage.setImageResource(R.drawable.rain);
                }
            }
        });
    }
}
