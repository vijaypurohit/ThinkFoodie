package com.example.fragment;

import android.content.Intent;
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

import com.example.adapter.CategoryListAdapter;
import com.example.favorite.DatabaseHelper;
import com.example.item.ItemLatest;
import com.example.util.Constant;
import com.think.foodie.ActivityRecipeDetails;
import com.think.foodie.ActivitySearch;
import com.think.foodie.R;

import java.util.ArrayList;
import java.util.List;


public class FavoriteFragment extends Fragment {

    ListView lsv_cat;
    List<ItemLatest> arrayOfCaList;
    CategoryListAdapter objAdapter;
    private ItemLatest objAllBean;
    ProgressBar pbar;
    private int columnWidth;
    DatabaseHelper databaseHelper;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_latest, container, false);
        setHasOptionsMenu(true);
        getActivity().setTitle(getString(R.string.menu_favorite));
        databaseHelper = new DatabaseHelper(getActivity());
        lsv_cat = (ListView) rootView.findViewById(R.id.listcatlist);
        pbar = (ProgressBar) rootView.findViewById(R.id.progressBar);

        arrayOfCaList = new ArrayList<>();

        lsv_cat.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {
                // TODO Auto-generated method stub

                objAllBean = arrayOfCaList.get(position);
                Constant.LATEST_RECIPE_IDD = objAllBean.getRecipeid();

                Intent intplay = new Intent(getActivity(), ActivityRecipeDetails.class);
                startActivity(intplay);
            }
        });

        return rootView;
    }
    @Override
    public void onResume() {
        super.onResume();
        arrayOfCaList = databaseHelper.getFavourite();
        setAdapterToListview();
    }
    public void setAdapterToListview() {
        objAdapter = new CategoryListAdapter(getActivity(), R.layout.categorylist_row_item,
                arrayOfCaList, columnWidth);
        lsv_cat.setAdapter(objAdapter);
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
