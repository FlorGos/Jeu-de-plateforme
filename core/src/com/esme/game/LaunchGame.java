package com.esme.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.esme.game.managers.GameStateManager;
import com.esme.game.states.MainMenu;
import com.esme.game.states.Test;

public class LaunchGame extends ApplicationAdapter {

	private GameStateManager gsm;
	private SpriteBatch sb;

	@Override
	public void create () {
		this.gsm = new GameStateManager();
		this.sb = new SpriteBatch();

		//this.gsm.push(new Test(this.gsm)); //met en place le premier écran
		this.gsm.push(new MainMenu(this.gsm));
	}

	@Override
	public void render () { //dessine le jeu
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		this.gsm.update(Gdx.graphics.getDeltaTime());
		this.gsm.render(this.sb);
	}
	
	@Override
	public void dispose () {
		this.sb.dispose();
	}
}
