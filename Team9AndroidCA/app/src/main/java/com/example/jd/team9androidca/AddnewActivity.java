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

public class AddnewActivity extends AppCompatActivity {

    final static int[] textViews = {R.id.editTextName, R.id.editTextQty, R.id.editTextPrice, R.id.editTextDescription};
    final static String[] key = {"name", "qty", "price", "desc"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnew);
        final Spinner spinnerCategory = (Spinner) findViewById(R.id.spinnerCategory);
        EditText editTextName = (EditText) findViewById(R.id.editTextName);
        EditText editTextQty = (EditText) findViewById(R.id.editTextQty);
        EditText editTextPrice = (EditText) findViewById(R.id.editTextPrice);
        EditText editTextDescription = (EditText) findViewById(R.id.editTextDescription);
        final Button b = (Button) findViewById(R.id.buttonSave);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                for (int i=0; i<textViews.length; i++) {
                    EditText t = (EditText) findViewById(textViews[i]);
                    book.put(key[i], t.getText().toString());
                }
                book.put("category",spinnerCategory.getSelectedItem().toString());
                new AsyncTask<Book, Void, Void>() {
                    @Override
                    protected Void doInBackground(Book... params) {
                        Book.addBook(params[0]);
                        return null;
                    }
                    @Override
                    protected void onPostExecute(Void result) {
                        finish();
                    }
                }.execute(book);
            }
        });
    }
}
