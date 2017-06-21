package com.noname.mfb;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.*;

public class Environmental
{
	private Texture envrmn;
	private Sprite mountain;
	private Array<Sprite> mountains;
	private RenderKit rkit;
	private SpriteBatch batch;
	private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;
	private float x = 2048;
	private boolean isStopped;
	
	public Environmental(RenderKit rkit)
	{
		this.rkit = rkit;
		this.batch = rkit.getSpriteBatch();
		this.envrmn = new Texture(Gdx.files.internal("mountains.png"));
		this.envrmn.setWrap(Texture.TextureWrap.Repeat , Texture.TextureWrap.Repeat);
		this.tiledMap = new TmxMapLoader().load("mount_tmx.tmx");
        this.renderer = new OrthogonalTiledMapRenderer(tiledMap);
		camera =new OrthographicCamera();
		camera.setToOrtho(false , Gdx.graphics.getWidth() , Gdx.graphics.getHeight());
		camera.update();
	}
	public void update(float dt)
	{
		
        renderer.setView(camera);
        renderer.render();
		if(!isStopped)
			x+=dt*125f;
		camera.position.set(x,Gdx.graphics.getHeight()/2,0);
		camera.update();
	}
	public void setIsStoppet(boolean iss)
	{
		if(!iss&&isStopped)
			x = 2048;
		this.isStopped = iss;
		
	}
	public void dispose()
	{
		envrmn.dispose();
	}
}
