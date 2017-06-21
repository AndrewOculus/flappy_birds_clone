package com.noname.mfb;
import com.badlogic.gdx.utils.*;

public class ParticleCallback
{
	private Array<PCallback> particleArray;

	interface PCallback{
		public void update(float dt);
		public boolean getIsDead();
		public void setStop(boolean isStopped);
		public void dispose();
	}
	public ParticleCallback()
	{
		particleArray = new Array<PCallback>();
	}
	public void registerCallback(PCallback wcb)
	{
		particleArray.add(wcb);
	}
	public void update(float dt)
	{
		for(int i = 0 ; i < particleArray.size ; i++)
		{
			PCallback particle = particleArray.get(i);
			particle.update(dt);
			
			if(particle.getIsDead())
				particleArray.removeIndex(i);
		}
	}
	public void setIsStopped(boolean isStopped)
	{
		for(int i = 0 ; i < particleArray.size ; i++)
		{
			PCallback particle = particleArray.get(i);
			particle.setStop(isStopped);
		}
	}
	public void dispose()
	{
	}
}
