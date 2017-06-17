package com.noname.mfb;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.physics.box2d.*;
import android.renderscript.*;
import com.badlogic.gdx.math.*;

public class RenderKit
{
	private ShapeRenderer renderer;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private World world;
	private Box2DDebugRenderer debugRenderer;
	private BitmapFont font;
	private Matrix4 standartMatrix;
	
	public RenderKit(ShapeRenderer sr , SpriteBatch bch , OrthographicCamera cam , World world ,Box2DDebugRenderer debugRenderer,BitmapFont font )
	{
		this.batch = bch;
		standartMatrix = bch.getProjectionMatrix();
		this.camera = cam;
		this.renderer = sr;
		this.world = world;
		this.debugRenderer = debugRenderer;
		this.font = font;
		this.font.setColor(0,0,0,1);
		this.font.setScale(0.1f);
	}
	public SpriteBatch getSpriteBatch(){return batch;}
	public ShapeRenderer getShapeRenderer(){return renderer;}
	public OrthographicCamera getCamera(){return camera;}
	public World getWorld(){return world;}
	public Box2DDebugRenderer getDebugRenderer(){return debugRenderer;}
	public BitmapFont getFont(){return font;}
	public Matrix4 getStandartMatrix(){return standartMatrix;}
}
