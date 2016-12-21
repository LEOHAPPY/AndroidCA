package com.example.jd.team9androidca;

import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yjday on 2016/12/21.
 */

public class Book extends HashMap<String, String> {
    public Book(String productID, String category, String name, String price, String desc, String qty) {
        put("productID", productID);
        put("category", category);
        put("name", name);
        put("price", price);
        put("desc", desc);
        put("qty", qty);
    }

    public Book() {
    }

    public static List<Book> jread(String url) {
        List<Book> list = new ArrayList<Book>();
        JSONArray a = JSONParser.getJSONArrayFromUrl(url);
        try {
            for (int i = 0; i < a.length(); i++) {
                JSONObject b = a.getJSONObject(i);
                list.add(new Book(b.getString("Id"), b.getString("Cg"), b.getString("Title"), b.getString("Price"), b.getString("Description"), b.getString("Quantity")));
            }
        } catch (Exception e) {
            Log.e("Book", "JSONArray error");
            Log.getStackTraceString(e);
        }
        return (list);
    }

    public static void updateBook(Book book) {
        JSONObject jbook = new JSONObject();
        try {
            jbook.put("Id", book.get("productID"));
            jbook.put("Cg", book.get("category"));
            jbook.put("Title", book.get("name"));
            jbook.put("Price", Double.parseDouble(book.get("price")));
            jbook.put("Description", book.get("desc"));
            jbook.put("Quantity", Integer.parseInt(book.get("qty")));
            Log.i("json-string", jbook.toString());
            String result = JSONParser.postStream("http://172.17.251.222/WCFService/Service.svc" + "/Update", jbook.toString());
        } catch (Exception e) {
            StackTrace.trace(e);
        }
    }

    public static void addBook(Book book) {
        JSONObject jbook = new JSONObject();
        try {
            jbook.put("Id","");
            jbook.put("Cg", book.get("category"));
            jbook.put("Title", book.get("name"));
            jbook.put("Price", Double.parseDouble(book.get("price")));
            jbook.put("Description", book.get("desc"));
            jbook.put("Quantity", Integer.parseInt(book.get("qty")));
            String result = JSONParser.postStream("http://172.17.251.222/WCFService/Service.svc/Insert", jbook.toString());

        } catch (Exception e) {
        }
    }

}
