package com.example.administrator.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int quantity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void submitOrder(View view){
        EditText nameField=(EditText)findViewById(R.id.name_field);
        String name=nameField.getText().toString();

        CheckBox whippedCreaamCheckBox=(CheckBox)findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream=whippedCreaamCheckBox.isChecked();

        CheckBox chocolateCheckBox=(CheckBox)findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate=chocolateCheckBox.isChecked();

        int price=caluatePrice(hasWhippedCream, hasChocolate);
         String message= createOrderSummary(name,price,hasWhippedCream,hasChocolate);
         displayMessage(message);

        Intent intent=new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just java order for" + name);
        intent.putExtra(Intent.EXTRA_TEXT,message);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }

    }
    private int caluatePrice(boolean addWhippedCream,boolean addChocolate){
        int basePrice=5;
        if(addWhippedCream){
           basePrice =basePrice+2;
        }
        if(addChocolate){
            basePrice =basePrice+1;
        }
        return quantity*basePrice;
    }
    private void displayQuantity(int numberOfCoffee){
        TextView quantityTextView=(TextView) findViewById(
          R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffee);
    }
    public void increment(View view){
        if(quantity==100)
            return;
        quantity=quantity+1;
        displayQuantity(quantity);
    }
    public void decrement(View view){
        if(quantity==0)
            return;
        quantity=quantity-1;
        displayQuantity(quantity);
    }
    public void displayMessage(String message){
        TextView orderSummaryTextView=(TextView)findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
    private String createOrderSummary(String name,int price,boolean addWhippedCream,boolean addChocolate){

        String priceMessage="Name:"+name;
        priceMessage+="\nAdd whipped cream?"+addWhippedCream;
        priceMessage+="\nAdd chocolate ?"+addChocolate;
        priceMessage+="\nQuantity："+quantity;
        priceMessage+="\nTotal: $"+price;
        priceMessage+="\nThank you!";
        return priceMessage;
    }

}
