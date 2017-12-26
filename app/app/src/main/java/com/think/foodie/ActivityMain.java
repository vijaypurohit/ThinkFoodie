package com.think.foodie;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import com.example.fragment.CategoryFragment;
import com.example.fragment.FavoriteFragment;
import com.example.fragment.HomeFragment;
import com.example.fragment.LatestFragment;
import com.example.item.ItemAbout;
import com.example.util.Constant;
import com.example.util.JsonUtils;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class ActivityMain extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TextView txt_tag, txt_develop, txt_devname, txt_appname;
    private FragmentManager fragmentManager;
    private AdView mAdView;
    ArrayList<ItemAbout> mListItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getString(R.string.menu_home));

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.action_item1:
                                HomeFragment fragmenthome = new HomeFragment();
                                selectedFragment = fragmenthome;
                                break;

                            case R.id.action_item2:
                                CategoryFragment catfragment = new CategoryFragment();
                                selectedFragment = catfragment;
                                break;

                            case R.id.action_item3:
                                FavoriteFragment favfragment = new FavoriteFragment();
                                selectedFragment = favfragment;
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragment1, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
//        }
//        toolbar.post(new Runnable() {
//            @Override
//            public void run() {
//                Drawable d = ResourcesCompat.getDrawable(getResources(), R.drawable.d_slidemenu, null);
//                toolbar.setNavigationIcon(d);
//            }
//        });
//        mAdView = (AdView) findViewById(R.id.adView);
//        mAdView.loadAd(new AdRequest.Builder().build());
//
////        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
////        navigationView = (NavigationView) findViewById(R.id.nav_view);
//        View hView = navigationView.inflateHeaderView(R.layout.nav_header);
//        txt_tag = (TextView) hView.findViewById(R.id.tag);
//        txt_appname = (TextView) hView.findViewById(R.id.appname);
////        txt_develop = (TextView) findViewById(R.id.text_develop);
////        txt_devname = (TextView) findViewById(R.id.dev_name);
//        Typeface titleFont = Typeface.createFromAsset(ActivityMain.this.getAssets(), "myfonts/Lato-Bold.ttf");
//        txt_appname.setTypeface(titleFont);
//
//        mListItem = new ArrayList<>();
//        if (JsonUtils.isNetworkAvailable(ActivityMain.this)) {
//            new MyTaskDev().execute(Constant.ABOUT_URL);
//        } else {
//            showToast(getString(R.string.network_msg));
//        }
//
//        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
//            @Override
//            public void onDrawerClosed(View drawerView) {
//                super.onDrawerClosed(drawerView);
//            }
//
//            @Override
//            public void onDrawerOpened(View drawerView) {
//                super.onDrawerOpened(drawerView);
//            }
//        };
//        mDrawerLayout.setDrawerListener(actionBarDrawerToggle);
//        actionBarDrawerToggle.syncState();
//
//        if (navigationView != null) {
//            setupDrawerContent(navigationView);
//        }
        fragmentManager = getSupportFragmentManager();
        HomeFragment currenthome = new HomeFragment();
        fragmentManager.beginTransaction().replace(R.id.fragment1, currenthome).commit();
    }
//
//    private void setupDrawerContent(final NavigationView navigationView) {
//        navigationView.setNavigationItemSelectedListener(
//                new NavigationView.OnNavigationItemSelectedListener() {
//                    @Override
//                    public boolean onNavigationItemSelected(MenuItem menuItem) {
//                        int id = menuItem.getItemId();
//                        switch (id) {
//                            case R.id.nav_home:
//                                HomeFragment fragmenthome = new HomeFragment();
//                                fragmentManager.beginTransaction().replace(R.id.fragment1, fragmenthome).commit();
//                                toolbar.setTitle(getString(R.string.menu_home));
//                                mDrawerLayout.closeDrawers();
//                                break;
//                            case R.id.nav_latest:
//                                LatestFragment latefragment = new LatestFragment();
//                                fragmentManager.beginTransaction().replace(R.id.fragment1, latefragment).commit();
//                                toolbar.setTitle(getString(R.string.menu_latest));
//                                mDrawerLayout.closeDrawers();
//                                break;
//                            case R.id.nav_cat:
//                                CategoryFragment catfragment = new CategoryFragment();
//                                fragmentManager.beginTransaction().replace(R.id.fragment1, catfragment).commit();
//                                toolbar.setTitle(getString(R.string.menu_category));
//                                mDrawerLayout.closeDrawers();
//                                break;
//                            case R.id.nav_fav:
//                                FavoriteFragment favfragment = new FavoriteFragment();
//                                fragmentManager.beginTransaction().replace(R.id.fragment1, favfragment).commit();
//                                toolbar.setTitle(getString(R.string.menu_favorite));
//                                mDrawerLayout.closeDrawers();
//                                break;
//
////                            case R.id.nav_rate:
////                                final String appName = ActivityMain.this.getPackageName();
////                                try {
////                                    startActivity(new Intent(Intent.ACTION_VIEW,
////                                            Uri.parse("market://details?id="
////                                                    + appName)));
////                                } catch (android.content.ActivityNotFoundException anfe) {
////                                    startActivity(new Intent(
////                                            Intent.ACTION_VIEW,
////                                            Uri.parse("http://play.google.com/store/apps/details?id="
////                                                    + appName)));
////                                }
////                                mDrawerLayout.closeDrawers();
////                                break;
////                            case R.id.nav_more:
////
////                                startActivity(new Intent(
////                                        Intent.ACTION_VIEW,
////                                        Uri.parse(getString(R.string.more_app_url))));
////                                mDrawerLayout.closeDrawers();
////                                break;
//
//                            case R.id.nav_share:
//                                Intent sendIntent = new Intent();
//                                sendIntent.setAction(Intent.ACTION_SEND);
//                                sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_message) + "\n" + "\n" + "https://play.google.com/store/apps/details?id=" + getPackageName());
//                                sendIntent.setType("text/plain");
//                                startActivity(sendIntent);
//                                mDrawerLayout.closeDrawers();
//                                break;
////                            case R.id.nav_about:
////                                Intent intentab = new Intent(ActivityMain.this, ActivityAboutUs.class);
////                                startActivity(intentab);
////                                mDrawerLayout.closeDrawers();
////                                break;
//
//                            case R.id.nav_privacy:
//                                Intent intenpri = new Intent(ActivityMain.this, ActivityPrivacy.class);
//                                startActivity(intenpri);
//                                mDrawerLayout.closeDrawers();
//                                break;
//                        }
//                        return true;
//                    }
//                });
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                mDrawerLayout.openDrawer(GravityCompat.START);
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//    }
//
//    @Override
//    public void onPageSelected(int position) {
//
//
//    }
//
//    @Override
//    public void onPageScrollStateChanged(int state) {
//
//    }
//
//    @Override
//    public void onClick(View v) {
//
//    }
//
//    private class MyTaskDev extends AsyncTask<String, Void, String> {
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//
//        }
//
//        @Override
//        protected String doInBackground(String... params) {
//            return JsonUtils.getJSONString(params[0]);
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            super.onPostExecute(result);
//
//            if (null == result || result.length() == 0) {
//                showToast(getString(R.string.no_data_found));
//            } else {
//
//                try {
//                    JSONObject mainJson = new JSONObject(result);
//                    JSONArray jsonArray = mainJson.getJSONArray(Constant.ARRAY_NAME);
//                    JSONObject objJson;
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        objJson = jsonArray.getJSONObject(i);
//                        ItemAbout itemAbout = new ItemAbout();
//
//                        itemAbout.setappDevelop(objJson.getString(Constant.APP_DEVELOP));
//                        itemAbout.setappTagline(objJson.getString(Constant.APP_TAGLINE));
//                        mListItem.add(itemAbout);
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                setResult();
//            }
//        }
//    }
//
//    private void setResult() {
//
//        ItemAbout itemAbout = mListItem.get(0);
//        txt_develop.setText(itemAbout.getappDevelop());
//        txt_tag.setText(itemAbout.getappTagline());
//
//
//    }
//
    public void showToast(String msg) {
        Toast.makeText(ActivityMain.this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            AlertDialog.Builder alert = new AlertDialog.Builder(
                    ActivityMain.this);
            alert.setTitle(getString(R.string.app_name));
            alert.setIcon(R.mipmap.app_icon);
            alert.setMessage("Are You Sure You Want To Quit?");

            alert.setPositiveButton("Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int whichButton) {

                            finish();
                        }

                    });
            alert.setNegativeButton("No",
                    new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub

                        }
                    });
            alert.show();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}