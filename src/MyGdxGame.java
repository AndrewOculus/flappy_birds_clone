package com.noname.mfb;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import android.transition.*;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.math.*;

public class MyGdxGame implements ApplicationListener
{
	SpriteBatch batch;
	ShapeRenderer renderer;
	OrthographicCamera camera;
	RenderKit rkit;
	WorldScene scene;
	World world;
	Box2DDebugRenderer debugRender;
	int w , h;
	BitmapFont font;

	@Override
	public void create()
	{
		font = new BitmapFont();
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
		batch = new SpriteBatch();
		renderer = new ShapeRenderer();
		camera = new OrthographicCamera(w,h);
		camera.position.set(0,0,0);
		camera.zoom = 0.01f;
		camera.update();
		debugRender = new Box2DDebugRenderer();
		world = new World(new Vector2(0 , -9.81f) , false);
		rkit = new RenderKit(renderer , batch , camera , world , debugRender ,font);
		scene = new WorldScene(rkit);
		
	}

	@Override
	public void render()
	{        
	    Gdx.gl.glClearColor(1, 1, 1, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		float dt = Gdx.graphics.getDeltaTime();
		scene.update(dt);
		world.step(dt,2,6);
	
	}

	@Override
	public void dispose()
	{
		renderer.dispose();
		batch.dispose();
		world.dispose();
		debugRender.dispose();
	}

	@Override
	public void resize(int width, int height)
	{
	}

	@Override
	public void pause()
	{
	}

	@Override
	public void resume()
	{
	}
}
