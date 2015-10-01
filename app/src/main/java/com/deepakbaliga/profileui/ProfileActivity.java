package com.deepakbaliga.profileui;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.deepakbaliga.profileui.Adapters.ViewPagerAdapter;
import com.deepakbaliga.profileui.Fragments.Followers;
import com.deepakbaliga.profileui.Fragments.Following;
import com.deepakbaliga.profileui.Fragments.Photo;
import com.deepakbaliga.profileui.models.User;
import com.deepakbaliga.profileui.utils.BlurTransformation;
import com.deepakbaliga.profileui.utils.Font;
import com.deepakbaliga.profileui.utils.RoundTransformation;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.IOException;



public class ProfileActivity extends AppCompatActivity {

    private OkHttpClient client = new OkHttpClient();
    private static final String JSONURL = "http://www.json-generator.com/api/json/get/ccbrnpyhyW?indent=2";
    private static final String LOGTAG = "PROFILE_ACTIVITY";
    private User buildUser = new User();

    private String titles[] = {"Photos\n", "Followers\n", "Following\n"};

    private Font font;

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private CoordinatorLayout coordinatorLayout;

    private ImageView backgroundImage ;
    private ImageView profileImage ;
    private TextView  usernameText;
    private TextView  locationText;
    private ToggleButton followButton;

    private ViewPager viewPager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        referenceViews();

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        new GetData().execute();


    }

    class GetData extends AsyncTask<Void, Void, User>{

        @Override
        protected User doInBackground(Void... params) {

            try {
                String strResponse = getResponseBody(JSONURL);
                JSONObject jsonReponse = new JSONObject(strResponse);

                buildUser.setFollowersCount(jsonReponse.getInt("followersCount"));
                buildUser.setFollowingCount(jsonReponse.getInt("followingCount"));
                buildUser.setPhotosCount(jsonReponse.getInt("photosCount"));

                buildUser.setLocation(jsonReponse.getString("location"));
                buildUser.setName(jsonReponse.getString("name"));
                buildUser.setProfilePicture(jsonReponse.getString("profilePicture"));


            } catch (Exception e) {
                e.printStackTrace();
            }


            return buildUser;
        }

        @Override
        protected void onPostExecute(User user) {
            super.onPostExecute(user);

            setUserDetails(user);

            setUpComponents();

        }

    }




    //Returns the HTTP reponse
    private String getResponseBody(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }


    //Add to LogCat
    private void log(String text){
       Log.e(LOGTAG, text);
    }


    //Referemce All the Views
    private void referenceViews(){

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorlayout);

        backgroundImage = (ImageView) findViewById(R.id.background_image);
        profileImage = (ImageView) findViewById(R.id.profile_image);
        usernameText = (TextView) findViewById(R.id.username);
        locationText = (TextView) findViewById(R.id.location);
        followButton = (ToggleButton) findViewById(R.id.follow_button);


        viewPager = (ViewPager) findViewById(R.id.viewpager);

        font = new Font(this);



    }

    private void setUserDetails(User user) {

        //Setting profile Picture
        Picasso.with(ProfileActivity.this).load(user.getProfilePicture())
                .transform(new RoundTransformation(500,0)).into(profileImage);

        //Setting Blur in the background
        //Android Image Blurring using picasso
        // 'http://deepakbaliga.com/index.php/2015/05/24/applying-blur-effect-to-image-in-android/'
        Picasso.with(ProfileActivity.this).load(user.getProfilePicture())
                .transform(new BlurTransformation(ProfileActivity.this, 20)).into(backgroundImage, new Callback() {
            @Override
            public void onSuccess() {
                Bitmap bitmap = ((BitmapDrawable)backgroundImage.getDrawable()).getBitmap();

                Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                    @SuppressWarnings("ResourceType")
                    @Override
                    public void onGenerated(Palette palette) {

                        int vibrantColor = palette.getVibrantColor(R.color.colorPrimary);
                        int vibrantDarkColor = palette.getDarkVibrantColor(R.color.colorPrimaryDark);

                        collapsingToolbarLayout.setContentScrimColor(vibrantColor);
                        collapsingToolbarLayout.setStatusBarScrimColor(vibrantDarkColor);

                        coordinatorLayout.setBackgroundColor(vibrantDarkColor);
                    }
                });
            }

            @Override
            public void onError() {

            }
        });




        //Setting fonts
        usernameText.setText(user.getName());
        locationText.setText(user.getLocation());

        usernameText.setTypeface(font.montBlack);
        locationText.setTypeface(font.montUltraLight);

        followButton.setTypeface(font.montSemiBold);
    }


    private void setUpComponents() {

        setupViewPager(viewPager);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());

                switch (tab.getPosition()) {
                    case 0:

                        break;
                    case 1:


                        break;
                    case 2:


                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFrag(Photo.newInstance(), titles[0]+buildUser.getPhotosCount());
        adapter.addFrag(Followers.newInstance(), titles[1] + buildUser.getFollowersCount());
        adapter.addFrag(Following.newInstance(), titles[2] + buildUser.getFollowingCount());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);



    }

}
