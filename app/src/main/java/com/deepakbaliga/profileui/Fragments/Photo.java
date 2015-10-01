package com.deepakbaliga.profileui.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.deepakbaliga.profileui.Adapters.PhotoAdapter;
import com.deepakbaliga.profileui.R;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by deezdroid on 01/10/15.
 */
public class Photo extends Fragment {

    private RecyclerView recyclerView;
    private PhotoAdapter photoAdapter;
    private OkHttpClient client = new OkHttpClient();
    private static final String JSONURL = "http://www.json-generator.com/api/json/get/clqZBZiyUO?indent=2";


    public Photo() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_photo, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.photos_recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        new GetPhotos().execute();
        return view;

    }

    class GetPhotos extends AsyncTask<Void, Void, ArrayList<String>>{

        @Override
        protected ArrayList<String> doInBackground(Void... params) {

            ArrayList<String> urls = new ArrayList<>();

            try {
                String strResponse = getResponseBody(JSONURL);
                JSONArray strArray = new JSONArray(strResponse);

                for(int i=0; i<strArray.length(); i++){
                    urls.add(strArray.get(i).toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            return urls;
        }

        @Override
        protected void onPostExecute(ArrayList<String> strings) {
            super.onPostExecute(strings);

            photoAdapter = new PhotoAdapter(getActivity(),strings);
            recyclerView.setAdapter(photoAdapter);
        }
    }

    private String getResponseBody(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static Photo newInstance() {
        Photo myFragment = new Photo();


        return myFragment;
    }
}
