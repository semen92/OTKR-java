package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Game.*;
public class MYGdxGame extends ApplicationAdapter {
	Snowstar[] snowstars;
	static final int shd = 540;
	static final int vd = 960;
	SpriteBatch batch;
	Texture snowstarTexture;

	Texture snowgroundTextuee;
	Texture igrokTexture;
	Igrok igrok;

	@Override
	public void create() {
		batch = new SpriteBatch();
		snowstarTexture = new Texture("snowstar.png");
		snowgroundTextuee = new Texture("snowground.jpg");
		snowstars = new Snowstar[100];
		igrok = new Igrok();
		igrokTexture = new Texture("ded3.png");
		for (int i = 0; i < snowstars.length; i++) {
			snowstars[i] = new Snowstar();
			snowstars[i].init();
		}
	}

	@Override
	public void render() {
		float dt = Gdx.graphics.getDeltaTime();
		update(dt);
		ScreenUtils.clear(0, 0, 0.15f, 1);
		batch.begin();
		for (int i = 0; i < snowstars.length; i++) {
			snowstars[i].render(batch);
		}
		igrok.render(batch);
		batch.draw(snowgroundTextuee, 0, 0);
		batch.end();
	}

	public void update(float dt) {
		for (int i = 0; i < snowstars.length; i++) {
			snowstars[i].snowupdate(dt);
		}
		igrok.move(dt);
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	class Snowstar {
		float size;
		float x, y;
		float xv, yv;

		public void snowupdate(float dt) {
			if (y < -10) {
				y = vd;
			}
			y -= 100.0f * dt;
		}

		public void render(SpriteBatch batch) {


	    		batch.draw(snowstarTexture, x - 8,  y- 8, 0, 0, 16, 16);
    		}

		public void init() {
			x = MathUtils.random(0, shd);
			y = MathUtils.random(0, vd);
			size = MathUtils.random(0.4f, 1.0f);
		}
	}

	class Igrok {
		float x, y, speed;

		public Igrok() {
			x = 120;
			y = 190;
			speed = 10.0f;
		}

		public void render(SpriteBatch batch) {
			batch.draw(igrokTexture, x, y);
		}

		public void move(float dt) {
			if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
				x += dt * speed;
			}
			if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
				x -= dt * speed;
			}
			if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
				y += dt * speed;
			}
			if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
				y -= dt * speed;
			}
		}
	}
}