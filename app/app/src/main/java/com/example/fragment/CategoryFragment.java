package com.example.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.adapter.CategoryAdapter;
import com.example.item.ItemCategory;
import com.example.util.Constant;
import com.example.util.JsonUtils;
import com.think.foodie.ActivityCategoryList;
import com.think.foodie.ActivitySearch;
import com.think.foodie.PopUpAds;
import com.think.foodie.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class CategoryFragment extends Fragment {

    ListView lsv_allvideos;
    List<ItemCategory> arrayOfCategory;
    CategoryAdapter objAdapter;
    private ItemCategory objAllBean;
    ArrayList<String> allListCatid, allListCatName, allListCatImageUrl;
    String[] allArrayCatid, allArrayCatname, allArrayCatImageurl;
     ProgressBar pbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_category, container, false);

        lsv_allvideos = (ListView) rootView.findViewById(R.id.listcat);
        arrayOfCategory = new ArrayList<ItemCategory>();
        pbar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        setHasOptionsMenu(true);
        getActivity().setTitle(getString(R.string.menu_category));
        allListCatid = new ArrayList<String>();
        allListCatImageUrl = new ArrayList<String>();
        allListCatName = new ArrayList<String>();

        allArrayCatid = new String[allListCatid.size()];
        allArrayCatname = new String[allListCatName.size()];
        allArrayCatImageurl = new String[allListCatImageUrl.size()];

        lsv_allvideos.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {
                // TODO Auto-generated method stub
                objAllBean = arrayOfCategory.get(position);

                Constant.CATEGORY_IDD = objAllBean.getCategoryId();
                Constant.CATEGORY_NAMEE = objAllBean.getCategoryName();
                PopUpAds.ShowInterstitialAds(getActivity());
                Intent intcat = new Intent(getActivity(), ActivityCategoryList.class);
                startActivity(intcat);
            }
        });

        if (JsonUtils.isNetworkAvailable(getActivity())) {
            new MyTask().execute(Constant.CATEGORY_URL);
        } else {
            showToast(getString(R.string.network_msg));

        }

        return rootView;
    }

    private class MyTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pbar.setVisibility(View.VISIBLE);
            lsv_allvideos.setVisibility(View.GONE);
        }

        @Override
        protected String doInBackground(String... params) {
            return JsonUtils.getJSONString(params[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            pbar.setVisibility(View.INVISIBLE);
            lsv_allvideos.setVisibility(View.VISIBLE);

            if (null == result || result.length() == 0) {
                showToast(getString(R.string.no_data_found));
            } else {

                try {
                    JSONObject mainJson = new JSONObject(result);
                    JSONArray jsonArray = mainJson.getJSONArray(Constant.ARRAY_NAME);
                    JSONObject objJson = null;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        objJson = jsonArray.getJSONObject(i);

                        ItemCategory objItem = new ItemCategory();
                        objItem.setCategoryName(objJson.getString(Constant.CATEGORY_NAME));
                        objItem.setCategoryId(objJson.getString(Constant.CATEGORY_ID));
                        objItem.setCategoryImageurl(objJson.getString(Constant.CATEGORY_IMAGE));
                        arrayOfCategory.add(objItem);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                for (int j = 0; j < arrayOfCategory.size(); j++) {
                    objAllBean = arrayOfCategory.get(j);

                    allListCatid.add(String.valueOf(objAllBean.getCategoryId()));
                    allArrayCatid = allListCatid.toArray(allArrayCatid);

                    allListCatName.add(objAllBean.getCategoryName());
                    allArrayCatname = allListCatName.toArray(allArrayCatname);

                    allListCatImageUrl.add(objAllBean.getCategoryImageurl());
                    allArrayCatImageurl = allListCatImageUrl.toArray(allArrayCatImageurl);

                }

                setAdapterToListview();
            }

        }
    }

    public void setAdapterToListview() {
        objAdapter = new CategoryAdapter(getActivity(), R.layout.category_row_item,
                arrayOfCategory);
        lsv_allvideos.setAdapter(objAdapter);
    }

    public void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);

        final SearchView searchView = (SearchView) menu.findItem(R.id.search)
                .getActionView();

        final MenuItem searchMenuItem = menu.findItem(R.id.search);
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                if (!hasFocus) {
                    searchMenuItem.collapseActionView();
                    searchView.setQuery("", false);
                }
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextChange(String newText) {
                // TODO Auto-generated method stub


                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(getActivity(), ActivitySearch.class);
                intent.putExtra("search", query);
                startActivity(intent);
                searchView.clearFocus();
                return false;
            }
        });


    }
}
