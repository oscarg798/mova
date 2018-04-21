package co.com.mova.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Request;
import com.squareup.picasso.Target;

import co.com.mova.R;

/**
 * Created by oscarg798 on 4/20/18.
 */

public class RoundCornersImageView extends AppCompatImageView implements Target {


    private int mRadius;

    private Drawable mDrawable;


    public RoundCornersImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.RoundCornersImageView);
        mRadius = attributes.getDimensionPixelSize(R.styleable.RoundCornersImageView_radius, 10);
        attributes.recycle();

    }


    public void loadImage(String url) {
        Picasso.get().load(url)
                .into((Target) this);
    }


    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), centerCropBitmap(bitmap));
        roundedBitmapDrawable.setCornerRadius(mRadius);
        setmDrawable(roundedBitmapDrawable);
    }

    private Bitmap centerCropBitmap(Bitmap bitmap){
        if (bitmap.getWidth() >= bitmap.getHeight()){

            return Bitmap.createBitmap(
                    bitmap,
                    bitmap.getWidth()/2 - bitmap.getHeight()/2,
                    0,
                    bitmap.getHeight(),
                    bitmap.getHeight()
            );

        }else{

            return Bitmap.createBitmap(
                    bitmap,
                    0,
                    bitmap.getHeight()/2 - bitmap.getWidth()/2,
                    bitmap.getWidth(),
                    bitmap.getWidth()
            );
        }
    }

    @Override
    public void onBitmapFailed(Exception e, Drawable errorDrawable) {

    }

    @Override
    public void onPrepareLoad(Drawable placeHolderDrawable) {

    }

    public void setmDrawable(Drawable mDrawable) {
        this.mDrawable = mDrawable;
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mDrawable != null) {
            mDrawable.setBounds(0, 0, this.getWidth(), this.getHeight());
            mDrawable.draw(canvas);
        }
    }
}