package com.example.menuoptions_ex0911;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText num1Et, num2Et;
    TextView ansTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1Et = findViewById(R.id.num1Et);
        num2Et = findViewById(R.id.num2Et);
        ansTv = findViewById(R.id.ansTv);
    }

    public void bigNumSimplifier(double value){
        String scientificNotation = String.format("%.4e", value);
        String[] parts = scientificNotation.split("e");
        double base = Double.parseDouble(parts[0]) / 10.0;
        int exponent = Integer.parseInt(parts[1]) + 1;

        ansTv.setText(String.format("%.4f * 10^%d", base, exponent));
    }


    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu (menu);
    }

    public boolean onOptionsItemSelected(@Nullable MenuItem item) {
        int id = item.getItemId();
        double total = 0;
        String num1St = num1Et.getText().toString();
        String num2St = num2Et.getText().toString();

        if(id == R.id.clearMi){
            ansTv.setText("");
            num1Et.setText("");
            num2Et.setText("");
            Toast.makeText(this, "Cleared fields", Toast.LENGTH_SHORT).show();
        } else if (num1St.equals("-") || num1St.equals("-.") || num1St.equals("+") || num1St.equals("+.") || num1St.equals(".") || num2St.equals("-") || num2St.equals("-.") || num2St.equals("+") || num2St.equals("+.") || num2St.equals(".")){
            Toast.makeText(this, "Wrong input", Toast.LENGTH_SHORT).show();
        } else if (num1St.isEmpty() || num2St.isEmpty()) {
            Toast.makeText(this, "One or more field is empty", Toast.LENGTH_SHORT).show();
        } else {
            double num1 = Double.parseDouble(num1St);
            double num2 = Double.parseDouble(num2St);

            if(id == R.id.plusMi) {
                total = num1 + num2;
                Toast.makeText(this, "Added fields", Toast.LENGTH_SHORT).show();
            } else if(id == R.id.minusMi) {
                total = num1 - num2;
                Toast.makeText(this, "Subtracted fields", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.multMi) {
                total = num1 * num2;
                Toast.makeText(this, "Multiplied fields", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.divideMi) {
                total = num1 / num2;
                Toast.makeText(this, "Divided fields", Toast.LENGTH_SHORT).show();
            }

            if (((total > 1000000) || (total < -1000000)) && (id == R.id.multMi || id == R.id.divideMi)) {
                bigNumSimplifier(total);
            } else if(total > -1 && total < 1) {
                ansTv.setText(total + "");
            } else{
                ansTv.setText(String.format("%.2f", total));
            }
        }

        return true;
    }
}