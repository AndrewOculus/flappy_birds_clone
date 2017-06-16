package com.noname.mfb;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.physics.box2d.*;

public class Wall implements WallCallback.WCallback
{
	private Vector2 position;
	private RenderKit rkit;
	private World world;
	private Body wallBody;
	private Fixture fixture;
	
	public Wall(Vector2 pos , RenderKit rkit)
	{
		this.position = pos;
		this.rkit = rkit;
		this.world = rkit.getWorld();
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
        shape.setAsBox(0.5f, 5);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        fixture = wallBody.createFixture(fixtureDef);	
	}
	public void update(float dt)
	{
		wallBody.setTransform(wallBody.getPosition().x - dt, wallBody.getPosition().y , 0);
	}
	@Override
	public void dispose()
	{
		world.destroyBody(wallBody);
	}
}
