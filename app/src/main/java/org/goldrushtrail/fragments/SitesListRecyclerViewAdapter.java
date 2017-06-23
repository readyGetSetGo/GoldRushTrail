package org.goldrushtrail.fragments;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.goldrushtrail.R;
import org.goldrushtrail.locations.GoldRushLocation;

import java.util.ArrayList;

public class SitesListRecyclerViewAdapter
        extends RecyclerView.Adapter<SitesListRecyclerViewAdapter.ViewHolder>
{

    private final SitesListFragment.GoldRushSiteFragmentListener mListener;
    private final ArrayList<GoldRushLocation> mLocations;
    private final Resources mResources;
    private final String mPackageName;
    //TODO: uncomment once you've created the small images.
    public final int[] smallImages = { R.drawable.d0, R.drawable.d1, R.drawable.d2, R.drawable.d3, R.drawable.d4, R.drawable.d5, R.drawable.d6, R.drawable.d7, R.drawable.d8, R.drawable.d9, R.drawable.d10, R.drawable.d11, R.drawable.d12, R.drawable.d13, R.drawable.d14, R.drawable.d15, R.drawable.d16, R.drawable.d17, R.drawable.d18, R.drawable.d19, R.drawable.d20, R.drawable.d21, R.drawable.d22, R.drawable.d23, R.drawable.d24, R.drawable.d25, R.drawable.d26, R.drawable.d27, R.drawable.d28, R.drawable.d29, R.drawable.d30, R.drawable.d31, R.drawable.d32, R.drawable.d33, R.drawable.d34, R.drawable.d35, R.drawable.d36, R.drawable.d37, R.drawable.d38, R.drawable.d39, R.drawable.d40, R.drawable.d41, R.drawable.d42, R.drawable.d43, R.drawable.d44, R.drawable.d45, R.drawable.d46, R.drawable.d47, R.drawable.d48};

    public SitesListRecyclerViewAdapter(SitesListFragment.GoldRushSiteFragmentListener listener )
    {
        mListener = listener;
        mLocations = mListener.getLocations();
        mResources = mListener.getResources();
        mPackageName = mListener.getPackageName();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.goldrushlocation_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position)
    {
        //ViewHolder is the inner class below
        holder.mLocation = mLocations.get(position);
        holder.mTitleView.setText(mLocations.get(position).getTitle());
        String details = mLocations.get(position).getDetails();
        if ( details.length()>80 ) {
            holder.mDetailsView.setText(details.substring(0, 80) + "...");
        } else {
            holder.mDetailsView.setText(details);
        }
        String tourEnum = mLocations.get(position).tourEnum().toString();
        int imageResource = R.drawable.d48;

        if ( mLocations.get(position).hasImage() )
        {
            int imageIdNumber = smallImages[position];
            Bitmap bitmap = BitmapFactory.decodeResource(mResources, imageIdNumber);
            holder.mImageView.setImageBitmap(bitmap);
            /*
            //TODO: Uncomment when you put the images in the drawable folder.
            //int tResource = mResources.getIdentifier(smallImages[(mLocations.get(position)], "drawable", mPackageName);
            int tResource = mResources.getIdentifier(mLocations.get(position).getDrawable(), "drawable", mPackageName);
            if (tResource > 1)
            {
                //
                //How to crop a bitmap for ImageView
                //https://stackoverflow.com/questions/18232034/how-can-i-crop-a-bitmap-for-imageview

                //FIRST: Reduce the size of the image
                //TODO: Delete the unnecessary code.
                Bitmap bitmap = BitmapFactory.decodeResource(mResources, tResource);
                int originalWidth = bitmap.getWidth();
                int originalHeight = bitmap.getHeight();
                int reducedWidth = 100;
                int reducedHeight = reduceHeight(originalWidth, originalHeight, reducedWidth);
                bitmap = Bitmap.createScaledBitmap(bitmap, reducedWidth, reducedHeight, true);
                //SECOND: Crop the image by using the width of the reduced image.
                int newWidth = bitmap.getWidth();
                int newHeight = getNewHeight(newWidth);
                int yCoordinate = getYCoordinate(bitmap.getHeight(), newHeight);
                if(yCoordinate >= 0 )
                {
                    Log.d("bitmap", "name: "+mLocations.get(position).getTitle());
                    bitmap = Bitmap.createBitmap(bitmap, 0, yCoordinate, newWidth, newHeight);
                    //bitmap = Bitmap.createScaledBitmap(bitmap,200,184, true);
                    Log.d("bitmap", "\t\tnewWidth: "+bitmap.getWidth()+"; newHeight: "+bitmap.getHeight());
                    //Bitmap resized = Bitmap.createScaledBitmap(yourBitmap, newWidth, newHeight, true);
                    holder.mImageView.setImageBitmap(bitmap);
                }
                else
                {
                    holder.mImageView.setImageResource(imageResource);
                }

                //imageResource = tResource;
            }
            else
            {
                holder.mImageView.setImageResource(imageResource);
            }
            */
        }
        else
        {
            holder.mImageView.setImageResource(imageResource);
        }

        holder.mView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mListener.sitesListClickEventHandler(holder.mLocation);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return mLocations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private final View mView;
        private final TextView mTitleView;
        private final TextView mDetailsView;
        private final ImageView mImageView;
        private GoldRushLocation mLocation;

        public ViewHolder(View view)
        {
            super(view);
            mView = view;
            mTitleView = (TextView) view.findViewById(R.id.siteTitle);
            mDetailsView = (TextView) view.findViewById(R.id.siteDetail);
            mImageView = (ImageView) view.findViewById(R.id.imageView);
        }

        @Override
        public String toString()
        {
            return super.toString() + " '" + mTitleView.getText() + "'";
        }
    }
}

/*
int imageInt = mResources.getIdentifier(mTour.getDrawable(), "drawable", mPackName);
Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imageInt);
//The dimensions of the ListView is precisely W: 1000, H: 925
int width = bitmap.getWidth();
int height = getNewHeight(width);
int yCoordinate = getYCoordinate(bitmap.getHeight(), height);
bitmap = Bitmap.createBitmap(bitmap, 0, yCoordinate, width, height);
Drawable drawable = new BitmapDrawable(activity.getResources(),bitmap);
//appBarLayout.setBackground(drawable);


 */

