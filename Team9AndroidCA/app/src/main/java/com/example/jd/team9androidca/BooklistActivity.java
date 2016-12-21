package com.example.jd.team9androidca;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.List;


public class BooklistActivity extends ListActivity {

    static String URL = "http://172.17.251.222/WCFService/Service.svc/Book/choose/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String category = getIntent().getExtras().getString("Category");
        new AsyncTask<String, Void, List<Book>>() {
            @Override
            protected List<Book> doInBackground(String... params) {
                return Book.jread(params[0]);
            }

            @Override
            protected void onPostExecute(List<Book> result) {
                SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), result,
                        android.R.layout.simple_list_item_2,
                        new String[]{"name", "price"},
                        new int[]{android.R.id.text1, android.R.id.text2});
                setListAdapter(adapter);
            }
        }.execute(URL + category);
    }

    protected void onListItemClick(ListView l, View v,
                                   int position, long id) {
        Book item = (Book) getListAdapter().getItem(position);
        Intent i = new Intent(this, BookdetailsActivity.class);
        i.putExtra("Book", item);
        startActivity(i);
    }
}