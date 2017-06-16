package com.noname.mfb;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.*;

public class PlayerBird
{
	private Vector2 position;
	private RenderKit rkit;
	private World world;
	private Body birdBody;
	private boolean isDead;
	
	public PlayerBird(Vector2 pos , RenderKit rkit)
	{
		this.position = pos;
		this.rkit = rkit;
		this.world = rkit.getWorld();
		this.initPhysic();
	}
	private void initPhysic()
	{
		BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(position.x, position.y);
		MassData md = new MassData();
		
		md.mass = 0.001f;
		md.I = 0.01f;
		
        birdBody = world.createBody(bodyDef);
		birdBody.setMassData(md);
		PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.5f, 0.5f);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        Fixture fixture = birdBody.createFixture(fixtureDef);
		
		world.setContactListener(new ContactListener(){

				@Override
				public void beginContact(Contact p1)
				{
					isDead = true;
				}

				@Override
				public void endContact(Contact p1)
				{
				}

				@Override
				public void preSolve(Contact p1, Manifold p2)
				{
					// TODO: Implement this method
				}

				@Override
				public void postSolve(Contact p1, ContactImpulse p2)
				{
					// TODO: Implement this method
				}
			});
	}
	public void update(float dt)
	{
		if(isDead)
		{
			rkit.getSpriteBatch().begin();
			rkit.getFont().draw(rkit.getSpriteBatch() , "Wasted" , Gdx.graphics.getWidth()/3.5f , Gdx.graphics.getHeight()/2);
			rkit.getSpriteBatch().end();
		}
	}
	public void jumpBird()
	{
		if(!isDead)
		birdBody.applyLinearImpulse(new Vector2(0,14f) , birdBody.getPosition(), true);
	}
	public boolean getIsDead()
	{
		return isDead;
	}
	public void deletBird()
	{
		world.destroyBody(birdBody);
	}
}
