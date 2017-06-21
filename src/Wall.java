package com.noname.mfb;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.*;

public class Wall implements WallCallback.WCallback
{
	private Vector2 position;
	private RenderKit rkit;
	private World world;
	private Body wallBody;
	private Fixture fixture;
	private float speed = 2.5f , sizeWall = 0.5f;
	private ShapeRenderer renderer;
	private OrthographicCamera camera;
	private boolean isSensor;
	private WorldScene scene;
	
	public Wall(Vector2 pos , RenderKit rkit , boolean isSensor ,WorldScene scene )
	{
		this.position = pos;
		this.rkit = rkit;
		this.world = rkit.getWorld();
		this.renderer = rkit.getShapeRenderer();
		this.camera = rkit.getCamera();
		this.isSensor = isSensor;
		this.scene = scene;
		this.initPhysic();
	}
	public void initPhysic()
	{
		BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(position.x, position.y);
		MassData md = new MassData();
		md.mass = 0.1f;
		md.I = 0.1f;
        wallBody = world.createBody(bodyDef);
		PolygonShape shape = new PolygonShape();
        shape.setAsBox(sizeWall, sizeWall*10);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
		
        fixture = wallBody.createFixture(fixtureDef);	
	}
	@Override
	public void update(float dt)
	{
		wallBody.setTransform(wallBody.getPosition().x - dt*speed, wallBody.getPosition().y , 0);
		if(wallBody.getPosition().x < 0 && isSensor)
		{
			isSensor = false;
			scene.addScore();
		}
	}
	@Override
	public void render(float dt)
	{
		renderer.setProjectionMatrix(camera.combined);
		renderer.setColor(Color.LIGHT_GRAY);
		renderer.begin(ShapeRenderer.ShapeType.Filled);
		renderer.rect(wallBody.getPosition().x - sizeWall , wallBody.getPosition().y - sizeWall*10 , sizeWall*2 , sizeWall*20);
		renderer.end();
	
	}
	@Override
	public void dispose()
	{
		world.destroyBody(wallBody);
	}
}
