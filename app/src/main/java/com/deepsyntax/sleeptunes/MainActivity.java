package com.deepsyntax.sleeptunes;

import android.app.*;
import android.os.*;
import java.util.*;
import android.widget.*;
import android.widget.AdapterView.*;
import android.view.*;
import android.media.*;
import android.view.ContextMenu.*;

public class MainActivity extends Activity 
{ 
	private String[] musicsName={"Hush Little Baby","Good Night","Rock A Bye","Twinkle Little Star","Baby Sleep Piano","Pretty Little Horses"};
	private int[] musicId={R.raw.hush_little_baby,R.raw.lullaby_goodnight,R.raw.rock_a_bye_baby,R.raw.twinkle_twinkle_little_star,R.raw.baby_sleep_music,R.raw.pretty_little_horses};
	private int[] musicPic={R.drawable.baby_image_1,R.drawable.baby_image_2,R.drawable.baby_image_3,R.drawable.baby_image_4,R.drawable.baby_image_5,R.drawable.baby_image_6};
	private boolean isGrid=true;
	private MediaPlayer mp;
	private GridView grid;
	private ListView list;
	//private LayoutAdapter adapter;
	private  ArrayList<Music> songList;

	private MediaPlayer.OnCompletionListener mCompletionListener=new MediaPlayer.OnCompletionListener(){

		@Override
		public void onCompletion(MediaPlayer mp)
		{
			freeMediaResources();
			ImageView playStateImg=(ImageView)findViewById(R.id.grid_play_ic);
			playStateImg.setImageResource(android.R.drawable.ic_media_pause);
		}


	};

	private int currentPosition;

	private int pauseId;

	private int playId;
	private ImageView prevGridPlayStateImg;
	private ImageView prevListPlayStateImg;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		//An Array list of Music objects.
		songList = new ArrayList<Music>();

		//Adding new Music objects to the songList with a loop.
		for (int i=0;i < musicId.length;i++)
		{
			songList.add(new Music(musicId[i], musicsName[i], musicPic[i]));
		}

		grid = (GridView) findViewById(R.id.mainGridView);
		list = (ListView) findViewById(R.id.mainListView);	

		//initial View on App lunch 
		initializeView();
	}


	private void initializeView()
	{
		if (isGrid)
		{ 
			/**
			 * Using the @LayoutAdapter to add contents to the
			 * GridView and setting Click Listeners for
			 * each GridView Item
			 */

			LayoutAdapter adapter = new LayoutAdapter(this, songList, isGrid);
			grid.setVisibility(View.VISIBLE);
			list.setVisibility(View.GONE);
			grid.setAdapter(adapter);

			/*ItemListener iListener=new ItemListener(MainActivity.this, songList, mp, mCompletionListener);*/
			grid.setOnItemClickListener(new OnItemClickListener(){
					@Override
					public void onItemClick(AdapterView<?> parent, View v, int position, long id)
					{
						//Toast.makeText(MainActivity.this, "You clicked on grid Item " + position, Toast.LENGTH_LONG).show();
						int songId=songList.get(position).getSongId();
						if (prevGridPlayStateImg != null)
						{
							prevGridPlayStateImg.setImageResource(android.R.drawable.ic_media_play);
						}
						ImageView gridPlayStateImg = (ImageView)v.findViewById(R.id.grid_play_ic);				
						prevGridPlayStateImg = gridPlayStateImg;

						if (playId == songId)
						{
							pauseSong(songId);
							playId=0;
							gridPlayStateImg.setImageResource(android.R.drawable.ic_media_play);
						}
						else
						{
							boolean isPaused = pauseId == songId ;
							playSong(songId, isPaused);
							gridPlayStateImg.setImageResource(android.R.drawable.ic_media_pause);
						}
						mp.setOnCompletionListener(mCompletionListener);
					}
				});
		}
		else
		{

			/**
			 * Using the @LayoutAdapter to add contents to the
			 * ListView and setting Click Listeners for
			 * each Listview Item
			 */
			LayoutAdapter adapter = new LayoutAdapter(this, songList, isGrid);
			grid.setVisibility(View.GONE);
			list.setVisibility(View.VISIBLE);
			list.setAdapter(adapter);

			/*ItemListener iListener=new ItemListener(MainActivity.this, songList, mp, mCompletionListener);*/
			list.setOnItemClickListener(new OnItemClickListener(){
					@Override
					public void onItemClick(AdapterView<?> parent, View v, int position, long id)
					{
						//Toast.makeText(MainActivity.this, "You clicked on List Item " + position, Toast.LENGTH_LONG).show();
						int songId=songList.get(position).getSongId();
						if (prevListPlayStateImg != null)
						{
							prevListPlayStateImg.setImageResource(android.R.drawable.ic_media_play);
						}
						ImageView listPlayStateImg = (ImageView)v.findViewById(R.id.list_play_ic);				
						prevListPlayStateImg = listPlayStateImg;

						if (playId == songId)
						{
							pauseSong(songId);
							listPlayStateImg.setImageResource(android.R.drawable.ic_media_play);
						}
						else
						{
							boolean isPaused = pauseId == songId ;
							playSong(songId, isPaused);
							listPlayStateImg.setImageResource(android.R.drawable.ic_media_pause);
						}

						mp.setOnCompletionListener(mCompletionListener);
					}
				});
		}
	}

	private void freeMediaResources()
	{
		if (mp != null)
		{
			mp.release();
			mp = null;
		}
	}

	private void playSong(int id, boolean paused)
	{
		playId = id;
		if (paused)
		{
			mp.seekTo(currentPosition);
		}
		else
		{
			freeMediaResources();
			mp = MediaPlayer.create(MainActivity.this, id);
		}
		mp.start();
	}
	private void pauseSong(int id)
	{

		if (mp != null && mp.isPlaying())
		{
			pauseId = id;
			currentPosition = mp.getCurrentPosition();
			mp.stop();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// TODO: Implement this method
		MenuInflater mInflater=getMenuInflater();
		mInflater.inflate(R.menu.option_menu, menu);
		mInflater.inflate(R.menu.actionbar_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.switchView:
				isGrid = !isGrid;
				initializeView();
				break;
		}
		return super.onOptionsItemSelected(item);
	}




}
