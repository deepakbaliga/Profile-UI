package com.deepakbaliga.profileui.utils;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by deezdroid on 01/10/15.
 */
public class Font {
    public  Typeface montBlack;
    public  Typeface montBold;
    public  Typeface montHairline;
    public  Typeface montLight;
    public  Typeface montRegular;
    public  Typeface montUltraLight;
    public  Typeface montSemiBold;

    private Context context;

    public Font(Context context) {
        this.context = context;
        initTypeFace();
    }

    private void initTypeFace(){


        montBlack = Typeface.createFromAsset(context.getAssets(), "Montserrat-Black.otf");
        montBold = Typeface.createFromAsset(context.getAssets(), "Montserrat-Bold.otf");
        montHairline = Typeface.createFromAsset(context.getAssets(), "Montserrat-Hairline.otf");
        montLight = Typeface.createFromAsset(context.getAssets(), "Montserrat-Light.otf");
        montRegular = Typeface.createFromAsset(context.getAssets(), "Montserrat-Regular.otf");
        montUltraLight = Typeface.createFromAsset(context.getAssets(), "Montserrat-UltraLight.otf");
        montSemiBold = Typeface.createFromAsset(context.getAssets(), "Montserrat-SemiBold.otf");

    }
}
