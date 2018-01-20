package com.deepsyntax.sleeptunes;

import android.content.*;
import android.view.*;
import android.widget.*;
import java.util.*;

public class LayoutAdapter extends ArrayAdapter<Music>
{
	private boolean useGrid;

	public LayoutAdapter(Context context, ArrayList<Music> songList, boolean isGrid)
	{
		super(context, 0, songList);
		useGrid = isGrid;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{

		if (convertView == null)
		{
			if (useGrid)
			{
				convertView = LayoutInflater.from(getContext()).inflate(R.layout.grid_style, parent, false);
				Music currentItem=getItem(position);

				ImageButton imageBtn=(ImageButton) convertView.findViewById(R.id.grid_baby_image);
				TextView songLabel=(TextView)convertView.findViewById(R.id.grid_label);

				imageBtn.setImageResource(currentItem.getSongPicture());
				songLabel.setText(currentItem.getSongName());
			}
			else
			{
				convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_style, parent, false);
				Music currentItem=getItem(position);

				ImageButton imageBtn=(ImageButton) convertView.findViewById(R.id.list_baby_image);
				TextView songLabel=(TextView)convertView.findViewById(R.id.list_label);

				imageBtn.setImageResource(currentItem.getSongPicture());
				songLabel.setText(currentItem.getSongName());
			}
		}

		return convertView;
	}

}
