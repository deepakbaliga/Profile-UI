package com.deepakbaliga.profileui.utils;

import android.graphics.Bitmap;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.graphics.Palette;

import com.deepakbaliga.profileui.R;
import com.squareup.picasso.Transformation;

/**
 * Created by deezdroid on 01/10/15.
 */
public class PaletteTransformation implements Transformation {

    CollapsingToolbarLayout collapsingToolbarLayout;

    public PaletteTransformation(CollapsingToolbarLayout collapsingToolbarLayout) {
        this.collapsingToolbarLayout = collapsingToolbarLayout;
    }


    @Override
    public Bitmap transform(Bitmap source) {

        Palette.from(source).generate(new Palette.PaletteAsyncListener() {
            @SuppressWarnings("ResourceType")
            @Override
            public void onGenerated(Palette palette) {

                int vibrantColor = palette.getVibrantColor(R.color.colorPrimary);
                int vibrantDarkColor = palette.getDarkVibrantColor(R.color.colorPrimaryDark);
                collapsingToolbarLayout.setContentScrimColor(vibrantColor);
                collapsingToolbarLayout.setStatusBarScrimColor(vibrantDarkColor);
            }
        });

        return source;
    }

    @Override
    public String key() {
        return "Palette";
    }
}
