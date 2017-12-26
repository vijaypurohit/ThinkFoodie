package com.example.util;

import java.io.Serializable;

public class Constant implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    //server url
    public static final String SERVER_URL = "http://creatara.com/api/";

    public static final String IMAGE_PATH_URL = SERVER_URL + "images/";

    public static final String HOME_SLIDER_URL = SERVER_URL + "api.php?home";

    public static final String LATEST_URL = SERVER_URL + "api.php?latest";

    public static final String CATEGORY_URL = SERVER_URL + "api.php?cat_list";

    public static final String CATEGORYLIST_URL = SERVER_URL + "api.php?cat_id=";

    public static final String SINGLE_LIST_URL = SERVER_URL + "api.php?recipe_id=";
//most_view_recipe
    public static final String MOSTVIEW_LIST_URL = SERVER_URL + "api.php?latest";

    public static final String SEARCH_URL = SERVER_URL + "api.php?search_recipe=";

    public static final String ABOUT_URL = SERVER_URL + "api.php?";


    public static final String ARRAY_NAME = "RECIPE_APP";

    public static final String CATEGORY_ID = "cid";
    public static final String CATEGORY_NAME = "category_name";
    public static final String CATEGORY_IMAGE = "category_image";

    public static String CATEGORY_IDD;
    public static String CATEGORY_NAMEE;

    public static final String LATEST_RECIPE_ID = "id";
    public static final String LATEST_RECIPE_CAT_ID = "cat_id";
    public static final String LATEST_RECIPE_TYPE = "recipe_type";
    public static final String LATEST_RECIPE_NAME = "recipe_name";
    public static final String LATEST_RECIPE_TIME = "recipe_time";
    public static final String LATEST_RECIPE_INGRE = "recipe_ingredients";
    public static final String LATEST_RECIPE_DIRE = "recipe_direction";
    public static final String LATEST_RECIPE_IMAGE = "recipe_image_b";
    public static final String LATEST_RECIPE_VIDEOID = "video_id";
    public static final String LATEST_RECIPE_VIEW = "recipe_views";
    public static final String LATEST_RECIPE_CATNAME = "category_name";

    public static String LATEST_RECIPE_IDD;

    public static final String FEATURE_SUB_ARRAY = "featured_recipe";
    public static final String FEATURE_LATEST_ARRAY = "latest_recipe";
    //most_view_recipe
    public static final String FEATURE_MOST_ARRAY = "latest_recipe";
    public static final String FEATURE_ID = "id";
    public static final String FEATURE_NAME = "recipe_name";
    public static final String FEATURE_IMAGE = "recipe_image_b";
    public static final String FEATURE_CAT = "category_name";

    public static final String APP_NAME = "app_name";
    public static final String APP_IMAGE = "app_logo";
    public static final String APP_VERSION = "app_version";
    public static final String APP_AUTHOR = "app_author";
    public static final String APP_CONTACT = "app_contact";
    public static final String APP_EMAIL = "app_email";
    public static final String APP_WEBSITE = "app_website";
    public static final String APP_DESC = "app_description";
    public static final String APP_PRIVACY = "app_privacy_policy";
    public static final String APP_DEVELOP = "app_developed_by";
    public static final String APP_TAGLINE = "app_tagline";

    public static final String DOWNLOAD_FOLDER_PATH="/Recipe/";

}
