package androidrealm.com.utsavtest;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import androidrealm.com.utsavtest.adapter.ShopingDataAdapter;
import androidrealm.com.utsavtest.model.Dataresponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvData;
    public static final String URL = "http://27.109.20.118/SilverPixelz/api/categories/4/products";
    private ArrayList<Dataresponse> dataresponseArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvData = (RecyclerView) findViewById(R.id.rv_data);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        rvData.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.HORIZONTAL));
        rvData.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL));
        rvData.setLayoutManager(layoutManager);
        ShopingDataTask shopingDataTask = new ShopingDataTask();
        shopingDataTask.execute(URL);
    }

    public class ShopingDataTask extends AsyncTask<String, Void, ArrayList<Dataresponse>> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(MainActivity.this, "Loading", "task");
        }

        @Override
        protected ArrayList<Dataresponse> doInBackground(String... params) {
            try {
                String json = getUrl(params[0]);
                Log.d("TEST", json);
                Type listType = new TypeToken<ArrayList<Dataresponse>>() {
                }.getType();
                dataresponseArrayList = new GsonBuilder().create().fromJson(json, listType);
                return dataresponseArrayList;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Dataresponse> dataresponses) {
            super.onPostExecute(dataresponses);
            progressDialog.dismiss();
            ShopingDataAdapter shopingDataAdapter = new ShopingDataAdapter(dataresponses, MainActivity.this);
            rvData.setAdapter(shopingDataAdapter);
        }
    }

    private String getUrl(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();


        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}

