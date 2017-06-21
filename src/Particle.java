package com.noname.mfb;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.*;

public class Particle implements ParticleCallback.PCallback
{
	private boolean isDead , isStopped;
	private float x , y , speed = 2.5f , lifeTimer = 1.5f , radius;
	private RenderKit rkit;
	
	public Particle(Vector2 pos ,float radius, RenderKit rkit)
	{
		this.rkit = rkit;
		this.x = pos.x;
		this.y = pos.y;
		this.radius = radius;
	}
	
	@Override
	public void update(float dt)
	{
		if(!isStopped)
		x-= speed*dt;
		
		lifeTimer -=dt;
		
		if(lifeTimer <0)
			isDead = true;
			
		rkit.getShapeRenderer().setColor(new Color(0.97f,0.97f,0.97f,0.97f));
		rkit.getShapeRenderer().setProjectionMatrix(rkit.getCamera().combined);
		rkit.getShapeRenderer().begin(ShapeRenderer.ShapeType.Filled);
		rkit.getShapeRenderer().circle(x , y , radius);
		rkit.getShapeRenderer().end();
	}

	@Override
	public void setStop(boolean isStopped)
	{
		this.isStopped = isStopped;
	}
	
	@Override
	public void dispose()
	{
		
	}
	@Override
	public boolean getIsDead()
	{
		return isDead;
	}

}
