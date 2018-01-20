package com.deepsyntax.sleeptunes;

public class Music
{
	private int mSongId;
	private String mSongName;
	private int mSongPicture;
	
	public Music(int song,String songName,int songPicture){
		mSongId=song;
		mSongName=songName;
		mSongPicture=songPicture;
	}
	public int getSongId(){
		return mSongId;
	}
	public String getSongName(){
		return mSongName;
	}
	public int getSongPicture(){
		return mSongPicture;
	}
}
