package com.noname.mfb;
import com.badlogic.gdx.utils.*;

public class WallCallback
{
	private Array<WCallback> wallArray;
	
	interface WCallback{
		public void update(float dt);
		public void render(float dt);
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
	public void render(float dt)
	{
		for(int i = 0 ; i < wallArray.size ; i++)
		{
			wallArray.get(i).render(dt);
		}
	}
	public void clearWallArray()
	{
		for(int i = 0 ; i < wallArray.size ; i++)
		{
			wallArray.get(i).dispose();
		}
		wallArray = new Array<WCallback>();
	}
	public void dispose()
	{
		for(int i = 0 ; i < wallArray.size ; i++)
		{
			wallArray.get(i).dispose();
		}
	}
}
