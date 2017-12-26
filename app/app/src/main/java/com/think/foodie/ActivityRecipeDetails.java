package com.think.foodie;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.favorite.DatabaseHelper;
import com.example.item.ItemLatest;
import com.example.util.Constant;
import com.example.util.JsonUtils;
import com.example.youtube.YoutubePlay;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class ActivityRecipeDetails extends AppCompatActivity {

    public DatabaseHelper db;
    String r_img, r_name, r_catname, r_id, r_dire, r_ingre, r_time, r_type, r_view, r_videoid;
    private AdView mAdView;
    Toolbar toolbar;
    private ItemLatest objAllBean;
    List<ItemLatest> arrayOfRecipe;
    ImageView img_recipe, img_play, img_fav;
    TextView txt_name, txt_time, txt_view;
    LinearLayout lay_fav, lay_share;
    WebView webingre, webdirec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipedetails);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        mAdView = (AdView) findViewById(R.id.adView);
        mAdView.loadAd(new AdRequest.Builder().build());

        db = new DatabaseHelper(this);
        arrayOfRecipe = new ArrayList<>();

        img_recipe = (ImageView) findViewById(R.id.img_gmain);
        txt_name = (TextView) findViewById(R.id.text_title);
        img_play = (ImageView) findViewById(R.id.image_play);
        txt_time = (TextView) findViewById(R.id.text_time);
        webingre = (WebView) findViewById(R.id.desweb);
        txt_view = (TextView) findViewById(R.id.text_dview);
        lay_fav = (LinearLayout) findViewById(R.id.lay_fav);
        lay_share = (LinearLayout) findViewById(R.id.lay_share);
        webdirec = (WebView) findViewById(R.id.desdire);
        img_fav = (ImageView) findViewById(R.id.text_dfavimg);

        if (JsonUtils.isNetworkAvailable(ActivityRecipeDetails.this)) {
            new MyTask().execute(Constant.SINGLE_LIST_URL + Constant.LATEST_RECIPE_IDD);
        } else {
            showToast(getString(R.string.network_msg));
        }

    }

    private class MyTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params) {
            return JsonUtils.getJSONString(params[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (null == result || result.length() == 0) {
                showToast(getString(R.string.no_data_found));

            } else {

                try {
                    JSONObject mainJson = new JSONObject(result);
                    JSONArray jsonArray = mainJson.getJSONArray(Constant.ARRAY_NAME);
                    JSONObject objJson = null;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        objJson = jsonArray.getJSONObject(i);

                        ItemLatest objItem = new ItemLatest();

                        objItem.setRecipeImage(objJson.getString(Constant.LATEST_RECIPE_IMAGE));
                        objItem.setRecipeName(objJson.getString(Constant.LATEST_RECIPE_NAME));
                        objItem.setRecipeCategoryName(objJson.getString(Constant.LATEST_RECIPE_CATNAME));
                        objItem.setRecipeid(objJson.getString(Constant.LATEST_RECIPE_ID));
                        objItem.setRecipeCategoryId(objJson.getString(Constant.LATEST_RECIPE_CAT_ID));
                        objItem.setRecipeDirection(objJson.getString(Constant.LATEST_RECIPE_DIRE));
                        objItem.setRecipeIngredient(objJson.getString(Constant.LATEST_RECIPE_INGRE));
                        objItem.setRecipeTime(objJson.getString(Constant.LATEST_RECIPE_TIME));
                        objItem.setRecipeType(objJson.getString(Constant.LATEST_RECIPE_TYPE));
                        objItem.setRecipeViews(objJson.getString(Constant.LATEST_RECIPE_VIEW));
                        objItem.setRecipePlayId(objJson.getString(Constant.LATEST_RECIPE_VIDEOID));

                        arrayOfRecipe.add(objItem);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                setAdapterToListview();
            }

        }

        public void setAdapterToListview() {

            objAllBean = arrayOfRecipe.get(0);

            r_catname = objAllBean.getRecipeCategoryName();
            r_dire = objAllBean.getRecipeDirection();
            r_id = objAllBean.getRecipeid();
            r_img = objAllBean.getRecipeImage();
            r_ingre = objAllBean.getRecipeIngredient();
            r_name = objAllBean.getRecipeName();
            r_time = objAllBean.getRecipeTime();
            r_type = objAllBean.getRecipeType();
            r_videoid = objAllBean.getRecipePlayId();
            r_view = objAllBean.getRecipeViews();

            Picasso.with(ActivityRecipeDetails.this).load(r_img).placeholder(R.drawable.ic_category).into(img_recipe);

            txt_name.setText(r_name);
            txt_time.setText(r_time);
            txt_view.setText(r_view);

            if (r_type.equals("video")) {

                img_play.setVisibility(View.VISIBLE);
            } else {
                img_play.setVisibility(View.INVISIBLE);
            }

            webingre.setBackgroundColor(0);
            webingre.setFocusableInTouchMode(false);
            webingre.setFocusable(false);
            webingre.getSettings().setDefaultTextEncodingName("UTF-8");

            String mimeType = "text/html;charset=UTF-8";
            String encoding = "utf-8";
            String htmlText = r_ingre;

            String text = "<html><head>"
                    + "<style type=\"text/css\">@font-face {font-family: MyFont;src: url(\"file:///android_asset/myfonts/LatoRegular.ttf\")}body{font-family: MyFont;color: #545454;text-align:justify;margin-left:0px}"
                    + "</style></head>"
                    + "<body>"
                    + htmlText
                    + "</body></html>";

            webingre.loadDataWithBaseURL(null, text, mimeType, encoding, null);
            webingre.setBackgroundColor(0);

            webdirec.setFocusableInTouchMode(false);
            webdirec.setFocusable(false);
            webdirec.getSettings().setDefaultTextEncodingName("UTF-8");

            String mimeTypedir = "text/html;charset=UTF-8";
            String encodingdir = "utf-8";
            String htmlTextdir = r_dire;

            String textdir = "<html><head>"
                    + "<style type=\"text/css\">@font-face {font-family: MyFont;src: url(\"file:///android_asset/myfonts/LatoRegular.ttf\")}body{font-family: MyFont;color: #545454;text-align:justify;margin-left:0px}"
                    + "</style></head>"
                    + "<body>"
                    + htmlTextdir
                    + "</body></html>";

            webdirec.loadDataWithBaseURL(null, textdir, mimeTypedir, encodingdir, null);
            img_play.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Intent i = new Intent(ActivityRecipeDetails.this, YoutubePlay.class);
                    i.putExtra("id", r_videoid);
                    startActivity(i);
                }
            });
            firstFavourite();
            lay_fav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ContentValues favo = new ContentValues();
                    if (db.getFavouriteById(r_id)) {
                        db.removeFavouriteById(r_id);
                        img_fav.setImageResource(R.drawable.fav);
                        Toast.makeText(ActivityRecipeDetails.this, getString(R.string.favourite_remove), Toast.LENGTH_SHORT).show();
                    } else {
                        favo.put(DatabaseHelper.KEY_ID, r_id);
                        favo.put(DatabaseHelper.KEY_TITLE, r_name);
                        favo.put(DatabaseHelper.KEY_IMAGE, r_img);
                        favo.put(DatabaseHelper.KEY_VIEW, r_view);
                        favo.put(DatabaseHelper.KEY_CATNAME, r_catname);
                        favo.put(DatabaseHelper.KEY_TIME, r_time);
                        db.addFavourite(DatabaseHelper.TABLE_FAVOURITE_NAME, favo, null);
                        img_fav.setImageResource(R.drawable.fave_hov);
                        Toast.makeText(ActivityRecipeDetails.this, getString(R.string.favourite_add), Toast.LENGTH_SHORT).show();
                    }
                }
            });

            lay_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    (new SaveTask(ActivityRecipeDetails.this)).execute(r_img);
                }
            });


        }
    }

    public void showToast(String msg) {
        Toast.makeText(ActivityRecipeDetails.this, msg, Toast.LENGTH_LONG).show();
    }

    private void firstFavourite() {
        if (db.getFavouriteById(r_id)) {
            img_fav.setImageResource(R.drawable.fave_hov);
        } else {
            img_fav.setImageResource(R.drawable.fav);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    public class SaveTask extends AsyncTask<String, String, String> {
        private Context context;
        private ProgressDialog pDialog;
        String image_url;
        URL myFileUrl;


        String myFileUrl1;
        Bitmap bmImg = null;
        File file;

        public SaveTask(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub

            super.onPreExecute();


        }

        @Override
        protected String doInBackground(String... args) {
            // TODO Auto-generated method stub

            try {

                myFileUrl = new URL(args[0]);

                HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
                conn.setDoInput(true);
                conn.connect();
                InputStream is = conn.getInputStream();
                bmImg = BitmapFactory.decodeStream(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {

                String path = myFileUrl.getPath();
                String idStr = path.substring(path.lastIndexOf('/') + 1);
                File filepath = Environment.getExternalStorageDirectory();
                File dir = new File(filepath.getAbsolutePath() + Constant.DOWNLOAD_FOLDER_PATH);
                dir.mkdirs();
                String fileName = idStr;
                file = new File(dir, fileName);
                FileOutputStream fos = new FileOutputStream(file);
                bmImg.compress(Bitmap.CompressFormat.JPEG, 75, fos);
                fos.flush();
                fos.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String args) {
            // TODO Auto-generated method stub

            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("image/jpeg");
            if (r_type.equals("video")) {
                share.putExtra(Intent.EXTRA_TEXT, "Recipe Name:-" + r_name + "\n" + "Recipe Time:-" + r_time + "\n" + "Ingredients:-" + Html.fromHtml(r_ingre) + "\n" + "Directions:-" + Html.fromHtml(r_dire) + "\n" + "Recipe Video:-" + "https://www.youtube.com/watch?v=" + r_videoid + "\n" + getString(R.string.share_message) + "\n" + "https://play.google.com/store/apps/details?id=" + getPackageName());

            } else {
                share.putExtra(Intent.EXTRA_TEXT, "Recipe Name:-" + r_name + "\n" + "Recipe Time:-" + r_time + "\n" + "Ingredients:-" + Html.fromHtml(r_ingre) + "\n" + "Directions:-" + Html.fromHtml(r_dire) + "\n"+ getString(R.string.share_message) + "\n" + "https://play.google.com/store/apps/details?id=" + getPackageName());

            }
            share.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + file.getAbsolutePath()));
            startActivity(Intent.createChooser(share, "Share Image"));


        }
    }
}

