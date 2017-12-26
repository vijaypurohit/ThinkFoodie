
package com.example.util;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class CategorySquareImageView extends ImageView
{

    public CategorySquareImageView(Context context)
    {
        super(context);
    }

    public CategorySquareImageView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public CategorySquareImageView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
    }

    protected void onMeasure(int i, int j)
    {
        super.onMeasure(i, j);
        setMeasuredDimension(getMeasuredWidth(), (int) ((int) (getMeasuredWidth())/2.3));
    }
}
