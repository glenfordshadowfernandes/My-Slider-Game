package com.example.myslidergame;

import java.util.ArrayList;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

@SuppressLint("NewApi")
public class ChunkedImageActivity extends Activity {

	ImageView _GAME_PIC;
	ImageAdapter Images;
	ImageView img1,img2,img3,img4,img5,img6,img7,img8,img9;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image_grid);
		
		//Getting the image chunks sent from the previous activity
				ArrayList<Bitmap> imageChunks = getIntent().getParcelableArrayListExtra("image chunks");
				
				//Getting the grid view and setting an adapter to it
				/*GridView grid = (GridView) findViewById(R.id.imageGrid);
				Images = new ImageAdapter(this, imageChunks);
				Log.i("Chunked Class Log", "ImageAdapterSize = "+Images.getCount());
				
				grid.setAdapter(Images);
				grid.setNumColumns((int) Math.sqrt(imageChunks.size()));*/
				
				
				
				 img1 = (ImageView)findViewById(R.id.myimage1);
				 img2 = (ImageView)findViewById(R.id.myimage2);
				 img3 = (ImageView)findViewById(R.id.myimage3);
				 img4 = (ImageView)findViewById(R.id.myimage4);
				 img5 = (ImageView)findViewById(R.id.myimage5);
				 img6 = (ImageView)findViewById(R.id.myimage6);
				 img7 = (ImageView)findViewById(R.id.myimage7);
				 img8 = (ImageView)findViewById(R.id.myimage8);
				 img9 = (ImageView)findViewById(R.id.myimage9);
				 
				img1.setImageBitmap(imageChunks.get(0));
				img2.setImageBitmap(imageChunks.get(1));
				img3.setImageBitmap(imageChunks.get(2));
				img4.setImageBitmap(imageChunks.get(3));
				img5.setImageBitmap(imageChunks.get(4));
				img6.setImageBitmap(imageChunks.get(5));
				img7.setImageBitmap(imageChunks.get(6));
				img8.setImageBitmap(imageChunks.get(7));
				img9.setImageBitmap(imageChunks.get(8));
				
				img1.setOnTouchListener(new MyTouchListener());
				img2.setOnTouchListener(new MyTouchListener());
				img3.setOnTouchListener(new MyTouchListener());
				img4.setOnTouchListener(new MyTouchListener());
				img5.setOnTouchListener(new MyTouchListener());
				img6.setOnTouchListener(new MyTouchListener());
				img7.setOnTouchListener(new MyTouchListener());
				img8.setOnTouchListener(new MyTouchListener());
				img9.setOnTouchListener(new MyTouchListener());
				
				
				/*findViewById(R.id.myimage1).setOnTouchListener(new MyTouchListener());
			    findViewById(R.id.myimage2).setOnTouchListener(new MyTouchListener());
			    findViewById(R.id.myimage3).setOnTouchListener(new MyTouchListener());
			    findViewById(R.id.myimage4).setOnTouchListener(new MyTouchListener());
			    findViewById(R.id.myimage5).setOnTouchListener(new MyTouchListener());
			    findViewById(R.id.myimage6).setOnTouchListener(new MyTouchListener());
			    findViewById(R.id.myimage7).setOnTouchListener(new MyTouchListener());
			    findViewById(R.id.myimage8).setOnTouchListener(new MyTouchListener());
			    findViewById(R.id.myimage9).setOnTouchListener(new MyTouchListener());*/
				
			    findViewById(R.id.topleft).setOnDragListener(new MyDragListener());
			    findViewById(R.id.topmiddle).setOnDragListener(new MyDragListener());
			    findViewById(R.id.topright).setOnDragListener(new MyDragListener());
			    findViewById(R.id.midleft).setOnDragListener(new MyDragListener());
			    findViewById(R.id.midcenter).setOnDragListener(new MyDragListener());
			    findViewById(R.id.midright).setOnDragListener(new MyDragListener());
			    findViewById(R.id.bottomleft).setOnDragListener(new MyDragListener());
			    findViewById(R.id.bottomcenter).setOnDragListener(new MyDragListener());
			    findViewById(R.id.bottomright).setOnDragListener(new MyDragListener());
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_screen, menu);
		return true;
	}
	
	private final class MyTouchListener implements OnTouchListener {
		@Override
		public boolean onTouch(View view, MotionEvent motionEvent) {
	      if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
	        ClipData data = ClipData.newPlainText("", "");
	        DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
	        view.startDrag(data, shadowBuilder, view, 0);
	        view.setVisibility(View.INVISIBLE);
	        return true;
	      } else {
	        return false;
	      }
	    }

	  }

	class MyDragListener implements OnDragListener {
	    Drawable enterShape = getResources().getDrawable(R.drawable.shape_droptarget);
	    Drawable normalShape = getResources().getDrawable(R.drawable.shape);

	    @Override
	    public boolean onDrag(View v, DragEvent event) {
	      int action = event.getAction();
	      switch (event.getAction()) {
	      case DragEvent.ACTION_DRAG_STARTED:
	        // Do nothing
	        break;
	      case DragEvent.ACTION_DRAG_ENTERED:
	        v.setBackgroundDrawable(enterShape);
	        break;
	      case DragEvent.ACTION_DRAG_EXITED:
	        v.setBackgroundDrawable(normalShape);
	        break;
	      case DragEvent.ACTION_DROP:
	        // Dropped, reassign View to ViewGroup
	        View view = (View) event.getLocalState();
	        ViewGroup owner = (ViewGroup) view.getParent();
	        owner.removeView(view);
	        LinearLayout container = (LinearLayout) v;
	        container.addView(view);
	        view.setVisibility(View.VISIBLE);
	        break;
	      case DragEvent.ACTION_DRAG_ENDED:
	        v.setBackgroundDrawable(normalShape);
	      default:
	        break;
	      }
	      return true;
	    }
	  }
	
	
}
