package jesuitas.dam.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class NewLibroActivity extends AppCompatActivity {
    TextView titleText;
    TextView autorText;
    TextView añoText;


    // Base URL for Books API.
    private static final String BOOK_BASE_URL =
            "https://imdb-api.com/api/%23Search-header";
    // Parameter for the search string.
    private static final String QUERY_PARAM = "q";
    // Parameter that limits search results.
    private static final String MAX_RESULTS = "maxResults";
    // Parameter to filter by print type.
    private static final String PRINT_TYPE = "printType";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titleText = findViewById(R.id.tv_title);
        autorText = findViewById(R.id.tv_autor);
        añoText = findViewById(R.id.tv_año);
          }

    public void submit(View view) {
        String queryString = query.getText().toString();

        if (queryString.length() == 0) {
            displayToast("Texto Vacio");
        } else {
            RequestQueue queue = Volley.newRequestQueue(this);
            Uri builtURI = Uri.parse(BOOK_BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, queryString)
                    .appendQueryParameter(MAX_RESULTS, "10")
                    .appendQueryParameter(PRINT_TYPE, "peliculas")
                    .build();

            String url = builtURI.toString();

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, url, (String) null, new
                            Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    parseJSONResponse(response);
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            titleText.setText("No funciona");
                        }
                    });
            queue.add(jsonObjectRequest);
        }
    }

    private void parseJSONResponse(JSONObject response) {
        try {
            JSONArray itemsArray = response.getJSONArray("items");
            int i = 0;
            String title = null;
            String autor = null;
            while (i < itemsArray.length() &&
                    (autor == null && title == null)) {
                // Get the current item information.
                JSONObject book = itemsArray.getJSONObject(i);
                JSONObject volumeInfo = book.getJSONObject("volumeInfo");
                // Try to get the author and title from the current item,
                // catch if either field is empty and move on.
                try {
                    title = volumeInfo.getString("title");
                    autor = volumeInfo.getString("autor");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // Move to the next item.
                i++;
            }
            if (title != null && autor != null) {
                titleText.setText(title);
                autorText.setText(autor);
            } else {
                titleText.setText("No Resultados");
            }
        } catch (Exception e) {
            titleText.setText("No resultados");
        }
    }


    private void displayToast(String string) {
        Toast.makeText(this,string,Toast.LENGTH_LONG).show();
    }
}