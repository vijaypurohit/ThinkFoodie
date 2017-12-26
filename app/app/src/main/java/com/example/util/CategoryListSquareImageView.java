
package com.example.util;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class CategoryListSquareImageView extends ImageView
{

    public CategoryListSquareImageView(Context context)
    {
        super(context);
    }

    public CategoryListSquareImageView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public CategoryListSquareImageView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
    }

    protected void onMeasure(int i, int j)
    {
        super.onMeasure(i, j);
        setMeasuredDimension(getMeasuredWidth(), (int) ((int) (getMeasuredWidth())/1.9));
    }
}
