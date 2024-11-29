package com.example.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class MainActivity extends AppCompatActivity {
    private EditText Electricity, Rebate;
    private TextView tCharge, tRebate, fCost;
    private Button btnCalculate, resetButton;
    private ImageView btnInfo, btnAbtMe, btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Electricity = findViewById(R.id.Electricity);
        Rebate = findViewById(R.id.Rebate);
        btnCalculate = findViewById(R.id.btnCalculate);
        resetButton = findViewById(R.id.Reset);
        btnAbtMe = findViewById(R.id.btnAbtMe);
        btnInfo = findViewById(R.id.btnInfo);
        btnHome = findViewById(R.id.btnHome);
        tCharge = findViewById(R.id.tCharge);
        tRebate = findViewById(R.id.tRebate);
        fCost = findViewById(R.id.fCost);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfoDialog();
            }
        });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBill();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear the input fields
                Electricity.setText("");
                Rebate.setText("");
                tCharge.setText("Total Charges: RM 0.00");
                tRebate.setText("Total Rebate: RM 0.00");
                fCost.setText("Your Final Bill: RM 0.00");
            }
        });

        btnAbtMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutMe.class);
                startActivity(intent);
            }
        });
    }

    private void showInfoDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Information")
                .setMessage("How to Use the App:\n\n" +
                        "1. Enter your electricity usage in number only.\n" +
                        "2. Enter rebate between 0 and 5 %.\n" +
                        "3. Tap 'Calculate Bill' to compute the total cost.\n" +
                        "4. Review the results, including charges and rebate.\n" +
                        "5. Tap 'Reset' if you want to reset."+"\n\n\nElectricity Rates:\n\n" +
                        "For the first 200 kWh (1 - 200 kWh) per month: 21.8 sen/kWh\n" +
                        "For the next 100 kWh (201 - 300 kWh) per month: 33.4 sen/kWh\n" +
                        "For the next 300 kWh (301 - 600 kWh) per month: 51.6 sen/kWh\n" +
                        "For the next 300 kWh (601 kWh and above) per month: 54.6 sen/kWh\n\n")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss(); // Close the dialog
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void calculateBill() {
        try {
            // Get input values
            String electricityInput = Electricity.getText().toString();
            String rebateInput = Rebate.getText().toString();

            // Validate inputs
            if (electricityInput.isEmpty() || rebateInput.isEmpty()) {
                showToast("Please enter both values.");
                return;
            }

            int electricityUnits = Integer.parseInt(electricityInput);
            double rebatePercentage = Double.parseDouble(rebateInput);

            // Check limits
            if (electricityUnits < 0 || electricityUnits > 1000) {
                showToast("Electricity units must be between 0 and 1000.");
                return;
            }

            if (rebatePercentage < 0 || rebatePercentage > 5) {
                showToast("Rebate percentage must be between 0% and 5%.");
                return;
            }

            // Calculate total charges based on electricity units
            double totalCharge = calculateTotalBill(electricityUnits);

            // Calculate total rebate
            double totalRebate = totalCharge * (rebatePercentage / 100);

            // Calculate final cost
            double finalCost = totalCharge - totalRebate;

            // Update the TextViews with the calculated values
            tCharge.setText(String.format("Total Charges: RM %.2f", totalCharge / 100)); // Convert to RM
            tRebate.setText(String.format("Total Rebate: RM %.2f", totalRebate / 100)); // Convert to RM
            fCost.setText(String.format("Your Final Bill: RM %.2f", finalCost / 100)); // Convert to RM

        } catch (NumberFormatException e) {
            showToast("Please enter valid numeric inputs.");
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private double calculateTotalBill(int units) {
        double total = 0;

        if (units <= 200) {
            total = units * 21.8;
        } else if (units <= 300) {
            total = (200 * 21.8) + ((units - 200) * 33.4);
        } else if (units <= 600) {
            total = (200 * 21.8) + (100 * 33.4) + ((units - 300) * 51.6);
        } else {
            total = (200 * 21.8) + (100 * 33.4) + (300 * 51.6) + ((units - 600) * 54.6);
        }

        return total;
    }
}