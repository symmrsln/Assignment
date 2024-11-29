package com.example.assignment1;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info); // Ensure you have this layout file created

        TextView infoView = findViewById(R.id.infoView);
        infoView.setText("How to Use the App:\n\n" +
                "1. Enter your electricity usage in number only.\n" +
                "2. Enter rebate between 0 and 5 %.\n" +
                "3. Tap 'Calculate Bill' to compute the total cost.\n" +
                "4. Review the results, including charges and rebate.\n" +
                "5. Tap 'Reset' if you want to reset."+"\n\n\nElectricity Rates:\n\n" +
                "For the first 200 kWh (1 - 200 kWh) per month: 21.8 sen/kWh\n" +
                "For the next 100 kWh (201 - 300 kWh) per month: 33.4 sen/kWh\n" +
                "For the next 300 kWh (301 - 600 kWh) per month: 51.6 sen/kWh\n" +
                "For the next 300 kWh (601 kWh and above) per month: 54.6 sen/kWh\n\n");
    }
}