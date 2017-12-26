package com.example.adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.favorite.DatabaseHelper;
import com.example.item.ItemLatest;
import com.example.util.Constant;
import com.squareup.picasso.Picasso;
import com.think.foodie.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CategoryListAdapter extends ArrayAdapter<ItemLatest> {

     private Activity activity;
    private List<ItemLatest> itemsLatest;
    private ItemLatest objLatestBean;
    private int row;
    private ArrayList<ItemLatest> arraylist;
    DatabaseHelper databaseHelper;
    public CategoryListAdapter(Activity act, int resource, List<ItemLatest> arrayList, int columnWidth) {
        super(act, resource, arrayList);
        this.activity = act;
        this.row = resource;
        this.itemsLatest = arrayList;
        this.arraylist = new ArrayList<>();
        this.arraylist.addAll(itemsLatest);
        databaseHelper = new DatabaseHelper(activity);

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        final ViewHolder holder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(row, null);

            holder = new ViewHolder();
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        if ((itemsLatest == null) || ((position + 1) > itemsLatest.size()))
            return view;

        objLatestBean = itemsLatest.get(position);

        holder.img_recipe = (ImageView) view.findViewById(R.id.image_catlist);
        holder.txt_recipename = (TextView) view.findViewById(R.id.text_recipename);
        holder.txt_catname = (TextView) view.findViewById(R.id.text_catname);
        holder.txt_time=(TextView)view.findViewById(R.id.text_time);
//        holder.img_favlist=(ImageView)view.findViewById(R.id.img_favlist);
        holder.img_share=(ImageView)view.findViewById(R.id.img_share);
//        holder.txt_view=(TextView)view.findViewById(R.id.text_view);

        Picasso.with(activity).load(objLatestBean.getRecipeImage()).placeholder(R.drawable.ic_category).into(holder.img_recipe);

        holder.txt_recipename.setText(objLatestBean.getRecipeName().toString());
        holder.txt_catname.setText(objLatestBean.getRecipeCategoryName().toString());
        holder.txt_time.setText(objLatestBean.getRecipeTime());
//        holder.txt_view.setText(objLatestBean.getRecipeViews());
//        if (databaseHelper.getFavouriteById(objLatestBean.getRecipeid())) {
//            holder.img_favlist.setImageResource(R.drawable.fave_hov);
//        } else {
//            holder.img_favlist.setImageResource(R.drawable.fav);
//        }
//        holder.img_favlist.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ContentValues favo = new ContentValues();
//                if (databaseHelper.getFavouriteById(objLatestBean.getRecipeid())) {
//                    databaseHelper.removeFavouriteById(objLatestBean.getRecipeid());
//                    holder.img_favlist.setImageResource(R.drawable.fav);
//                    Toast.makeText(activity, activity.getString(R.string.favourite_remove), Toast.LENGTH_SHORT).show();
//                } else {
//                    favo.put(DatabaseHelper.KEY_ID, objLatestBean.getRecipeid());
//                    favo.put(DatabaseHelper.KEY_TITLE, objLatestBean.getRecipeName());
//                    favo.put(DatabaseHelper.KEY_IMAGE, objLatestBean.getRecipeImage());
//                    favo.put(DatabaseHelper.KEY_VIEW, objLatestBean.getRecipeViews());
//                    favo.put(DatabaseHelper.KEY_CATNAME, objLatestBean.getRecipeCategoryName());
//                    favo.put(DatabaseHelper.KEY_TIME, objLatestBean.getRecipeTime());
//                    databaseHelper.addFavourite(DatabaseHelper.TABLE_FAVOURITE_NAME, favo, null);
//                    holder.img_favlist.setImageResource(R.drawable.fave_hov);
//                    Toast.makeText(activity, activity.getString(R.string.favourite_add), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        holder.img_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (new SaveTask(activity)).execute(objLatestBean.getRecipeImage());
            }
        });

        return view;

    }

    public class ViewHolder {

        public ImageView img_recipe,img_favlist,img_share;
        public TextView txt_recipename,txt_catname,txt_time,txt_view;

    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        itemsLatest.clear();
        if (charText.length() == 0) {
            itemsLatest.addAll(arraylist);
        } else {
            for (ItemLatest wp : arraylist) {
                if (wp.getRecipeName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    itemsLatest.add(wp);
                }
            }
        }
        notifyDataSetChanged();
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
            if (objLatestBean.getRecipeType().equals("video")) {
                share.putExtra(Intent.EXTRA_TEXT, "Recipe Name:-" + objLatestBean.getRecipeName() + "\n" + "Recipe Time:-" + objLatestBean.getRecipeTime() + "\n" + "Ingredients:-" + Html.fromHtml(objLatestBean.getRecipeIngredient()) + "\n" + "Directions:-" + Html.fromHtml(objLatestBean.getRecipeDirection()) + "\n" + "Recipe Video:-" + "https://www.youtube.com/watch?v=" + objLatestBean.getRecipePlayId() + "\n" + activity.getString(R.string.share_message) + "\n" + "https://play.google.com/store/apps/details?id=" + activity.getPackageName());

            } else {
                share.putExtra(Intent.EXTRA_TEXT, "Recipe Name:-" +  objLatestBean.getRecipeName() + "\n" + "Recipe Time:-" +  objLatestBean.getRecipeTime() + "\n" + "Ingredients:-" + Html.fromHtml(objLatestBean.getRecipeIngredient()) + "\n" + "Directions:-" + Html.fromHtml(objLatestBean.getRecipeDirection()) + "\n"+ activity.getString(R.string.share_message) + "\n" + "https://play.google.com/store/apps/details?id=" + activity.getPackageName());

            }
            share.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + file.getAbsolutePath()));
            activity.startActivity(Intent.createChooser(share, "Share Image"));


        }
    }
}