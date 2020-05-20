package ru.sapeshkoas.hw.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class HomeWorkGame extends ApplicationAdapter {
	
	private static class Iron {
		private Vector2 position;
		private Texture texture;
		float angel;
		float speed;

		public Iron(float x, float y) {
			position = new Vector2(x, y);
			texture = new Texture("iron.png");
			speed = 200.0f;

		}

		public void render(SpriteBatch batch) {
			batch.draw(texture,position.x - 40, position.y - 40, 40, 40, 80, 80, 1, 1, angel, 0, 0, 80, 80, false, false);
		}

		public void update(float dt) {
			if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
				angel += 180.0f * dt;
			}
			if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
				angel -= 180.0f * dt;
			}
			if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
				if (position.x > 20 && position.x < 1260) {
					position.x += speed * MathUtils.cosDeg(angel) * dt;
				} else {
					position.x += (position.x >= 1260) ? -0.1f : 0.1f;
				}
				if (position.y > 20 && position.y < 700) {
					position.y += speed * MathUtils.sinDeg(angel) * dt;
				} else {
					position.y += (position.y >= 700) ? -0.1f :  0.1f;
				}
			}
		}

		public void dispose() {
			texture.dispose();
		}
	}
	private SpriteBatch batch;
	private Iron iron;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		iron = new Iron(100, 100);
	}

	@Override
	public void render () {
		float dt = Gdx.graphics.getDeltaTime();
		iron.update(dt);
		Gdx.gl.glClearColor(0.75f, 1f, 0.85f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		iron.render(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		iron.dispose();
	}
}
