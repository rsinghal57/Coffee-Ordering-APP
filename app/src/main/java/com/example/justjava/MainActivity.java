package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    int quantity=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Coffee Smash");

    }



    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox WhippedCreamCheckBox=(CheckBox) findViewById(R.id.whipped_cream);
        boolean hasWhippedCream=WhippedCreamCheckBox.isChecked();

        CheckBox ChocolateCreamCheckBox=(CheckBox) findViewById(R.id.chocolate_cream);
        boolean hasChocolateCream=ChocolateCreamCheckBox.isChecked();

        EditText text=(EditText) findViewById(R.id.name_field);
        String name=text.getText().toString();

        String priceMessage="Free";
        int price=calculatePrice(hasWhippedCream,hasChocolateCream);
        String message=createOrderSummary(name,quantity,price,hasWhippedCream,hasChocolateCream);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for "+name);
        intent.putExtra(Intent.EXTRA_TEXT,message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

        displayMessage(message);



    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {

        TextView orderSummaryTextView=(TextView) findViewById(R.id.order_summary_text_view);

//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    public void increment(View view)
    {
        quantity++;
        displayQuantity(quantity);
    }
    public void decrement(View view)
    {
        if(quantity==0)
        {

        }
        else
        {
            quantity--;
        }
        displayQuantity(quantity);
    }
    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolateCream)
    {
        int pricePercup=5;
        if(hasWhippedCream)
        {
            pricePercup+=2;
        }
        if(hasChocolateCream)
        {
            pricePercup+=3;
        }
        return quantity*pricePercup;
    }


    private String createOrderSummary(String name,int quantity,int total,boolean hasWhippedCream,boolean hasChocolateCream)
    {
        String finalMessage="Name: "+name+"\n"+"Add Whipped Cream? "+hasWhippedCream+"\n"+"Add Chocolate Cream? "+hasChocolateCream+"\n"+"Quantity: "+quantity+"\n"+"Total: "+total+"\n"+"Thank you!";
        return finalMessage;
    }


}