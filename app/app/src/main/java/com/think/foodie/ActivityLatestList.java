package com.think.foodie;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.adapter.CategoryListAdapter;
import com.example.item.ItemLatest;
import com.example.util.Constant;
import com.example.util.JsonUtils;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ActivityLatestList extends AppCompatActivity {

	ListView lsv_cat;
	List<ItemLatest> arrayOfCaList;
	CategoryListAdapter objAdapter;
 	private ItemLatest objAllBean;
	JsonUtils util;
	int textlength = 0;
	private AdView mAdView;
	Toolbar toolbar;
	ProgressBar pbar;
	private int columnWidth;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_categorylist);
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setTitle(getString(R.string.home_latest));
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
		}
		mAdView = (AdView) findViewById(R.id.adView);
		mAdView.loadAd(new AdRequest.Builder().build());

		lsv_cat=(ListView)findViewById(R.id.listcatlist);
		pbar = (ProgressBar)findViewById(R.id.progressBar);

		arrayOfCaList=new ArrayList<>();

		util=new JsonUtils(getApplicationContext());


		if (JsonUtils.isNetworkAvailable(ActivityLatestList.this)) {
			new MyTask().execute(Constant.LATEST_URL);
		} else {
			showToast(getString(R.string.network_msg));
		}

		lsv_cat.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
									long arg3) {
				// TODO Auto-generated method stub

				objAllBean=arrayOfCaList.get(position);
				Constant.LATEST_RECIPE_IDD=objAllBean.getRecipeid();
				PopUpAds.ShowInterstitialAds(ActivityLatestList.this);
				Intent intplay=new Intent(getApplicationContext(),ActivityRecipeDetails.class);
				startActivity(intplay);
			}
		});
 	}

	private	class MyTask extends AsyncTask<String, Void, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			pbar.setVisibility(View.VISIBLE);
			lsv_cat.setVisibility(View.GONE);

		}

		@Override
		protected String doInBackground(String... params) {
			return JsonUtils.getJSONString(params[0]);
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			pbar.setVisibility(View.INVISIBLE);
			lsv_cat.setVisibility(View.VISIBLE);

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


						arrayOfCaList.add(objItem);

 					}

				} catch (JSONException e) {
					e.printStackTrace();
				}


				setAdapterToListview();
			}

		}
	}

	public void setAdapterToListview() {
		objAdapter = new CategoryListAdapter(ActivityLatestList.this, R.layout.categorylist_row_item,
				arrayOfCaList,columnWidth);
		lsv_cat.setAdapter(objAdapter);
	}

	public void showToast(String msg) {
		Toast.makeText(ActivityLatestList.this, msg, Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_main, menu);

 		final SearchView searchView = (SearchView) menu.findItem(R.id.search)
				.getActionView();

		final MenuItem searchMenuItem = menu.findItem(R.id.search);
		searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(!hasFocus) {
					searchMenuItem.collapseActionView();
					searchView.setQuery("", false);
				}
			}
		});

		searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			@Override
			public boolean onQueryTextChange(String newText) {

				return false;
			}

			@Override
			public boolean onQueryTextSubmit(String query) {
				// Do something
				Intent intent = new Intent(ActivityLatestList.this, ActivitySearch.class);
				intent.putExtra("search", query);
				startActivity(intent);
				searchView.clearFocus();
				return true;
			}
		});

 		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem)
	{
		switch (menuItem.getItemId())
		{
			case android.R.id.home:
				onBackPressed();
				break;

			default:
				return super.onOptionsItemSelected(menuItem);
		}
		return true;
	}

}
