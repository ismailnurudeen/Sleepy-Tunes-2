package com.deepsyntax.sleeptunes;
import android.content.*;
import android.media.*;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView.*;
import java.util.*;

public class ItemListener implements OnItemClickListener
{
	ArrayList<Music> songList;
	Context mContext;
	MediaPlayer mp;
	MediaPlayer.OnCompletionListener mCompletionListener;
	public ItemListener(Context context, ArrayList<Music> arrList, MediaPlayer mediaplayer, MediaPlayer.OnCompletionListener comListener)
	{
		mContext = context;
		songList = arrList;
		mp = mediaplayer;
		mCompletionListener = comListener;

	}
	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id)
	{
		Toast.makeText(mContext, "You clicked on a grid", Toast.LENGTH_LONG).show();
		int songId=songList.get(position).getSongId();
		
		freeMediaResources();
		mp = MediaPlayer.create(mContext, songId);
		mp.start();
		
		ImageView playStateImg=(ImageView)v.findViewById(R.id.grid_play_ic);
		playStateImg.setImageResource(android.R.drawable.ic_media_pause);
		
		mp.setOnCompletionListener(mCompletionListener);
	}
	
	private void freeMediaResources()
	{
		if (mp != null)
		{
			mp.release();
			mp = null;
		}
	}

}
