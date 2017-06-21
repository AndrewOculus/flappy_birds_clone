package com.noname.mfb;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;

public class PlayerBird
{
	private Vector2 position;
	private RenderKit rkit;
	private World world;
	private Body birdBody;
	private boolean isDead;
	private SpriteBatch batch;
	private BitmapFont font;
	private OrthographicCamera camera;
	private Texture birdTexture;
	private TextureRegion birdTexRegion;
	private float birdSize = 0.45f;
	private float rotation = 0;
	private ParticleCallback pcallback;
	
	public PlayerBird(Vector2 pos , RenderKit rkit)
	{
		this.position = pos;
		this.rkit = rkit;
		this.world = rkit.getWorld();
		this.font = rkit.getFont();
		this.birdTexture = new Texture(Gdx.files.internal("plain.png"));
		this.birdTexRegion = new TextureRegion(birdTexture);
		this.batch = rkit.getSpriteBatch();
		this.camera = rkit.getCamera();
		this.pcallback = new ParticleCallback();
		this.initPhysic();
	}
	private void initPhysic()
	{
		BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(position.x, position.y);
		MassData md = new MassData();
		
		md.mass = 0.01f;
		md.I = 0.01f;
		
        birdBody = world.createBody(bodyDef);
		birdBody.setMassData(md);
		PolygonShape shape = new PolygonShape();
        shape.setAsBox(birdSize, birdSize);
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
				}

				@Override
				public void postSolve(Contact p1, ContactImpulse p2)
				{
				}
			});
	}
	public void update(float dt)
	{
		//Particle p = new Particle(birdBody.getPosition() , rotation/150f , rkit);
		//pcallback.registerCallback(p);
		//pcallback.update(dt);
		//pcallback.setIsStopped(isDead);
		
		if(birdBody.getPosition().y>10||birdBody.getPosition().y<-10)
		{
			isDead = true;
		}
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		//batch.draw(birdTexture , birdBody.getPosition().x - birdSize, birdBody.getPosition().y - birdSize, birdSize*2,birdSize*2);
		rotation = birdBody.getLinearVelocity().y*5f;
		batch.draw(birdTexRegion , birdBody.getPosition().x - birdSize, 
		birdBody.getPosition().y - birdSize , 
		birdSize , birdSize , birdSize*2, birdSize*2 , 1.2f , 1.5f,rotation);
		batch.end();
	}
	public void jumpBird()
	{
		if(!isDead)
		birdBody.applyLinearImpulse(new Vector2(0,birdSize*25) , birdBody.getPosition(), true);
	}
	public boolean getIsDead()
	{
		return isDead;
	}
	public void deletBird()
	{
		world.destroyBody(birdBody);
		birdTexture.dispose();
	}
}
