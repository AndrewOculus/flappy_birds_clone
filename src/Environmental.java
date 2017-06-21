package com.noname.mfb;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.*;

public class Environmental
{
	private Texture envrmn;
	private RenderKit rkit;
	private SpriteBatch batch;
	
	public Environmental(RenderKit rkit)
	{
		this.rkit = rkit;
		this.batch = rkit.getSpriteBatch();
		this.envrmn = new Texture(Gdx.files.internal("envr.png"));
	}
	public void update(float dt)
	{
		batch.setProjectionMatrix(rkit.getCamera().combined);
		batch.begin();
		batch.draw(envrmn , -5 , -5 , 10, 10);
		batch.end();
	}
	public void dispose()
	{
		envrmn.dispose();
	}
}
