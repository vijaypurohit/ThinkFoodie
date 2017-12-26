package com.example.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adapter.LatestAdapter;
import com.example.item.ItemLatest;
import com.example.item.ItemSlider;
import com.example.util.Constant;
import com.example.util.JsonUtils;
import com.squareup.picasso.Picasso;
import com.think.foodie.ActivityMostviewList;
import com.think.foodie.ActivityRecipeDetails;
import com.think.foodie.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;


public class HomeFragment extends Fragment {


    List<ItemSlider> arrayofSlider;
    ItemSlider itemSlider;
    RelativeLayout mainlay;
    ProgressBar pbar;
    List<ItemLatest> arrayofLatest, arrayofMost;
    GridView grid_latest, grid_mostview;
    LatestAdapter objAdapter;
    private int columnWidth;
    Button btn_latest, btn_mostview;
    private FragmentManager fragmentManager;
    private ItemLatest objAllBean;
    TextView txt_latest, txt_mostview;
    ViewPager viewpager_main;
    ImagePagerAdapter adapter;
    CircleIndicator circleIndicator;
    int currentCount = 0;
    String[] allArrayimages, allArrayname, allArraylink, allArray;
    ArrayList<String> allListimages, allListname, allListlink;
    private ArrayList<ItemSlider> arraylist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        setHasOptionsMenu(true);

        arrayofSlider = new ArrayList<>();
        arrayofLatest = new ArrayList<>();
        arrayofMost = new ArrayList<>();
        getActivity().setTitle(getString(R.string.app_name));

        mainlay = (RelativeLayout) rootView.findViewById(R.id.main);
        pbar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        fragmentManager = getActivity().getSupportFragmentManager();

        viewpager_main = (ViewPager) rootView.findViewById(R.id.viewPager);
        arrayofSlider = new ArrayList<ItemSlider>();
        this.arraylist = new ArrayList<ItemSlider>();
        circleIndicator = (CircleIndicator) rootView.findViewById(R.id.indicator_unselected_background);

        allListimages = new ArrayList<String>();
        allListlink = new ArrayList<>();
        allListname = new ArrayList<>();

        allArrayimages = new String[allListimages.size()];
        allArraylink = new String[allListlink.size()];
        allArrayname = new String[allListname.size()];

//        grid_latest = (GridView) rootView.findViewById(R.id.grid_latest);
        grid_mostview = (GridView) rootView.findViewById(R.id.grid_mostview);
//        btn_latest = (Button) rootView.findViewById(R.id.btn_latest);
        btn_mostview = (Button) rootView.findViewById(R.id.btn_mostview);
//        txt_latest = (TextView) rootView.findViewById(R.id.text_latest);
        txt_mostview = (TextView) rootView.findViewById(R.id.text_mostview);

        if (JsonUtils.isNetworkAvailable(getActivity())) {
            new MyTaskSlider().execute(Constant.HOME_SLIDER_URL);
        } else {
            showToast(getString(R.string.network_msg));
        }

//        btn_latest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intentlatest = new Intent(getActivity(), ActivityLatestList.class);
//                startActivity(intentlatest);
//            }
//        });

        btn_mostview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentmost = new Intent(getActivity(), ActivityMostviewList.class);
                startActivity(intentmost);
            }
        });

        viewpager_main.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub
                //position=viewcatalogimg.getCurrentItem();
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });

        return rootView;
    }

    public class MyTaskSlider extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pbar.setVisibility(View.VISIBLE);
            mainlay.setVisibility(View.GONE);
        }

        @Override
        protected String doInBackground(String... params) {
            return JsonUtils.getJSONString(params[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            pbar.setVisibility(View.INVISIBLE);
            mainlay.setVisibility(View.VISIBLE);

            if (null == result || result.length() == 0) {
                showToast(getString(R.string.no_data_found));

            } else {

                try {
                    JSONObject mainJson = new JSONObject(result);
                    JSONObject mainJsonob = mainJson.getJSONObject(Constant.ARRAY_NAME);
                    JSONArray jsonArray = mainJsonob.getJSONArray(Constant.FEATURE_SUB_ARRAY);
                    JSONObject objJson = null;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        objJson = jsonArray.getJSONObject(i);

                        ItemSlider objItem = new ItemSlider();

                        objItem.setId(objJson.getString(Constant.FEATURE_ID));
                        objItem.setCat(objJson.getString(Constant.FEATURE_CAT));
                        objItem.setName(objJson.getString(Constant.FEATURE_NAME));
                        objItem.setImage(objJson.getString(Constant.FEATURE_IMAGE));

                        arrayofSlider.add(objItem);

                    }
                    JSONArray jsonArray2 = mainJsonob.getJSONArray(Constant.FEATURE_LATEST_ARRAY);
                    JSONObject objJson2 = null;
                    for (int i = 0; i < jsonArray2.length(); i++) {
                        objJson2 = jsonArray2.getJSONObject(i);

                        ItemLatest objItem = new ItemLatest();

                        objItem.setRecipeid(objJson2.getString(Constant.FEATURE_ID));
                        objItem.setRecipeCategoryName(objJson2.getString(Constant.FEATURE_CAT));
                        objItem.setRecipeName(objJson2.getString(Constant.FEATURE_NAME));
                        objItem.setRecipeImage(objJson2.getString(Constant.FEATURE_IMAGE));

                        arrayofLatest.add(objItem);

                    }
                    JSONArray jsonArray2v = mainJsonob.getJSONArray(Constant.FEATURE_MOST_ARRAY);
                    JSONObject objJson2v = null;
                    for (int i = 0; i < jsonArray2v.length(); i++) {
                        objJson2v = jsonArray2v.getJSONObject(i);

                        ItemLatest objItem = new ItemLatest();

                        objItem.setRecipeid(objJson2v.getString(Constant.FEATURE_ID));
                        objItem.setRecipeCategoryName(objJson2v.getString(Constant.FEATURE_CAT));
                        objItem.setRecipeName(objJson2v.getString(Constant.FEATURE_NAME));
                        objItem.setRecipeImage(objJson2v.getString(Constant.FEATURE_IMAGE));

                        arrayofMost.add(objItem);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                setAdapterToFeatured();
            }

        }
    }

    public void setAdapterToFeatured() {

        adapter = new ImagePagerAdapter();
        viewpager_main.setAdapter(adapter);
        circleIndicator.setViewPager(viewpager_main);
        autoPlay(viewpager_main);

//        objAdapter = new LatestAdapter(getActivity(), R.layout.latest_row_item,
//                arrayofLatest, columnWidth);
//        grid_latest.setAdapter(objAdapter);

        objAdapter = new LatestAdapter(getActivity(), R.layout.latest_row_item,
                arrayofMost, columnWidth);
        grid_mostview.setAdapter(objAdapter);


//        grid_latest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                objAllBean = arrayofLatest.get(position);
//                Constant.LATEST_RECIPE_IDD = objAllBean.getRecipeid();
//                Intent intplay = new Intent(getActivity(), ActivityRecipeDetails.class);
//                startActivity(intplay);
//            }
//        });

        grid_mostview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                objAllBean = arrayofMost.get(position);
                Constant.LATEST_RECIPE_IDD = objAllBean.getRecipeid();
                Intent intplay = new Intent(getActivity(), ActivityRecipeDetails.class);
                startActivity(intplay);
            }
        });

    }

    private void autoPlay(final ViewPager viewPager) {

        viewPager.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    if (adapter != null && viewpager_main.getAdapter().getCount() > 0) {
                        int position = currentCount % adapter.getCount();
                        currentCount++;
                        viewpager_main.setCurrentItem(position);
                        autoPlay(viewpager_main);
                    }
                } catch (Exception e) {
                    Log.e("TAG", "auto scroll pager error.", e);
                }
            }
        }, 2500);
    }

    private class ImagePagerAdapter extends PagerAdapter {

        private LayoutInflater inflater;

        public ImagePagerAdapter() {
            // TODO Auto-generated constructor stub

            inflater = getActivity().getLayoutInflater();
        }

        @Override
        public int getCount() {
            return arrayofSlider.size();

        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {

            View imageLayout = inflater.inflate(R.layout.viewpager_item, container, false);
            assert imageLayout != null;
            itemSlider = arrayofSlider.get(position);
            ImageView imageview = (ImageView) imageLayout.findViewById(R.id.imageView_viewitem);
            TextView textView_catname = (TextView) imageLayout.findViewById(R.id.text_homecatanme);
            TextView textView_recipename = (TextView) imageLayout.findViewById(R.id.text_homerecipename);

            textView_catname.setText(itemSlider.getName());
            textView_recipename.setText(itemSlider.getCat());

            Picasso.with(getActivity()).load(itemSlider.getImage()).placeholder(R.drawable.ic_category).into(imageview);

            imageview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemSlider = arrayofSlider.get(position);
                    String id = itemSlider.getId();
                    Log.e("id", "" + id);
                    Constant.LATEST_RECIPE_IDD = itemSlider.getId();
                    Intent intplay = new Intent(getActivity(), ActivityRecipeDetails.class);
                    startActivity(intplay);

                }
            });

            container.addView(imageLayout, 0);
            return imageLayout;

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((View) object);
        }
    }

    public void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }
}
