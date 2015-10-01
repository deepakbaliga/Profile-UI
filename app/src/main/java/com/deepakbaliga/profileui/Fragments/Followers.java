package com.deepakbaliga.profileui.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.deepakbaliga.profileui.R;

/**
 * Created by deezdroid on 01/10/15.
 */
public class Followers extends Fragment {

    public Followers() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_followers, container, false);
        return view;
    }

    public static Followers newInstance() {
        Followers followers = new Followers();


        return followers;
    }
}
