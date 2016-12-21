package com.example.jd.team9androidca;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class BookdetailsActivity extends AppCompatActivity {

    final static int[] textViews = { R.id.editTextName, R.id.editTextQty, R.id.editTextPrice, R.id.editTextDescription};
    final static String[] key = { "name", "qty", "price", "desc"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookdetails);
        final TextView textViewProductID = (TextView) findViewById(R.id.textView_ProductID);
        final Spinner spinnerCategory = (Spinner) findViewById(R.id.spinnerCategory);
        EditText editTextName = (EditText) findViewById(R.id.editTextName);
        EditText editTextQty = (EditText) findViewById(R.id.editTextQty);
        EditText editTextPrice = (EditText) findViewById(R.id.editTextPrice);
        Spinner spinnerStatus = (Spinner) findViewById(R.id.spinnerStatus);
        EditText editTextDescription = (EditText) findViewById(R.id.editTextDescription);
        HashMap<String, String> book = (HashMap<String, String>) getIntent().getExtras().get("Book");
        textViewProductID.setText((String) book.get("productID"));
        spinnerCategory.setSelection(findCategoryPosition(book.get("category")));
        editTextName.setText((String) book.get("name"));
        editTextQty.setText((String) book.get("qty"));
        editTextPrice.setText((String) book.get("price"));
        editTextDescription.setText((String) book.get("desc"));
        if (Integer.parseInt((String) book.get("qty")) == 0)
            spinnerStatus.setSelection(0);
        else
            spinnerStatus.setSelection(1);
        Button buttonModify = (Button) findViewById(R.id.buttonModify);
        buttonModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                for (int i = 0; i < textViews.length; i++) {
                    EditText t = (EditText) findViewById(textViews[i]);
                    book.put(key[i], t.getText().toString());
                }
                book.put("productID",textViewProductID.getText().toString());
                book.put("category", spinnerCategory.getSelectedItem().toString());
                Toast.makeText(getApplicationContext(),book.get("name"),Toast.LENGTH_LONG).show();
                new AsyncTask<Book, Void, Void>() {
                    @Override
                    protected Void doInBackground(Book... params) {
                        Book.updateBook(params[0]);
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void result) {
                        finish();
                    }
                }.execute(book);
            }
        });

        Button buttonBuy = (Button) findViewById(R.id.buttonBuy);
        buttonBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> book = (HashMap<String, String>) getIntent().getExtras().get("Book");
                Book bookBought;
                if (Integer.parseInt(book.get("qty")) <= 0)
                    Toast.makeText(getApplicationContext(), "Book Sold Out!", Toast.LENGTH_LONG);
                else {
                    bookBought = new Book(book.get("productID"), book.get("category"), book.get("name"), book.get("price"), book.get("desc"), Integer.toString(Integer.parseInt(book.get("qty")) - 1));
                    new AsyncTask<Book, Void, Void>() {
                        @Override
                        protected Void doInBackground(Book... params) {
                            Book.updateBook(params[0]);
                            return null;
                        }
                        @Override
                        protected void onPostExecute(Void result) {
                            finish();
                        }
                    }.execute(bookBought);

                }
            }
        });
    }

    protected int findCategoryPosition(String category) {
        int position = 0;
        String[] values = {"Humour", "History", "Children", "Fiction", "Romance", "Cooking"};
        switch (category) {
            case "Humour":
                position = 0;
                break;
            case "History":
                position = 1;
                break;
            case "Children":
                position = 2;
                break;
            case "Fiction":
                position = 3;
                break;
            case "Romance":
                position = 4;
                break;
            case "Cooking":
                position = 5;
                break;
        }
        return position;
    }
}



