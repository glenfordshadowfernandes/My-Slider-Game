package com.example.myslidergame;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

	private Context mContext;
	private ArrayList<Bitmap> imageChunks;
	private int imageWidth, imageHeight;
	
	//constructor
	public ImageAdapter(Context c, ArrayList<Bitmap> images){
		mContext = c;
		imageChunks = images;
		imageWidth = images.get(0).getWidth();
		imageHeight = images.get(0).getHeight();
	}
	
	@Override
	public int getCount() {
		return imageChunks.size();
	}

	@Override
	public Object getItem(int position) {
		return imageChunks.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView image;
		if(convertView == null){
			image = new ImageView(mContext);
			
			/*
			 * NOTE: I have set imageWidth - 10 and imageHeight 
			 * as arguments to LayoutParams class. 
			 * But you can take anything as per your requirement 
			 */
			//image.setLayoutParams(new GridView.LayoutParams(imageWidth - 10 , imageHeight));
			Log.i("Image width", ""+imageWidth);
			Log.i("Image height", ""+imageHeight);
			image.setLayoutParams(new GridView.LayoutParams(167, 183));
			image.setPadding(5, 5, 5, 5);
		}else{
			image = (ImageView) convertView;
		}
		image.setImageBitmap(imageChunks.get(position));
		
		return image;
	}
}