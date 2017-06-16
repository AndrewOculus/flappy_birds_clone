package com.noname.mfb;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.*;

public class WorldScene
{
	private ShapeRenderer renderer;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private RenderKit rkit;
	private World world;
	private Box2DDebugRenderer debugRender;
	private PlayerBird bird;
	private boolean isJump = false;
	private WallCallback wallCallback;
	private float wallTimer = 2.5f;
	
	
	public WorldScene(RenderKit rkit)
	{
		this.wallCallback = new WallCallback();
		this.rkit = rkit;
		this.batch = rkit.getSpriteBatch();
		this.camera = rkit.getCamera();
		this.renderer = rkit.getShapeRenderer();
		this.world = rkit.getWorld();
		this.debugRender = rkit.getDebugRenderer();
		this.beginPhysics();
	}
	private void beginPhysics()
	{
		//Wall wall = new Wall(new Vector2(2 , -9f) , rkit);
		//wallCallback.registerCallback(wall);
		//wall = new Wall(new Vector2(2 , 9) , rkit);
		//wallCallback.registerCallback(wall);
		
		bird = new PlayerBird(new Vector2(0 , -2) , rkit);
	}
	public void update(float dt)
	{
		debugRender.render(world , camera.combined);
		if(Gdx.input.isTouched()&&isJump)
		{
			bird.jumpBird();
			isJump = false;
		}
		//jump up!
		if(!Gdx.input.isTouched())
		{
			isJump = true;
		}
		if(!bird.getIsDead())
			wallCallback.update(dt);
		
		//add dynamic walls
		wallTimer -= dt;
		
		if(wallTimer<=0&&!bird.getIsDead())
		{
			float w = MathUtils.random(9f,11f);
			float h = MathUtils.random(7.5f,9f);
			Wall wall = new Wall(new Vector2( w, -h) , rkit);
			wallCallback.registerCallback(wall);
			wall = new Wall(new Vector2(w , h) , rkit);
			wallCallback.registerCallback(wall);
			wallTimer = MathUtils.random(4f,6f);
		}
		bird.update(dt);
		
		
		if(bird.getIsDead()&&Gdx.input.isTouched())
		{
			this.restoreGame();
		}
	}
	public void restoreGame()
	{
		wallCallback.clearWallArray();
		wallCallback.dispose();
		bird.deletBird();
		
		bird = new PlayerBird(new Vector2(0 , -2) , rkit);
	}
	public void dispose()
	{
		
	}
	
}
