package com.noname.mfb;
import com.badlogic.gdx.utils.*;

public class WallCallback
{
	Array<WCallback> wallArray;
	
	interface WCallback{
		public void update(float dt);
		public void dispose();
	}
	public WallCallback()
	{
		wallArray = new Array<WCallback>();
	}
	public void registerCallback(WCallback wcb)
	{
		wallArray.add(wcb);
	}
	public void update(float dt)
	{
		for(int i = 0 ; i < wallArray.size ; i++)
		{
			wallArray.get(i).update(dt);
		}
	}
	public void dispose()
	{
		for(int i = 0 ; i < wallArray.size ; i++)
		{
			}
	}
	public void clearWallArray()
	{
		for(int i = 0 ; i < wallArray.size ; i++)
		{
			wallArray.get(i).dispose();
			
			wallArray.removeIndex(i);
		}
	}
}
