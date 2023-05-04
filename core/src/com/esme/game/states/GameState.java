package com.esme.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esme.game.managers.GameStateManager;

public abstract class GameState {

    protected OrthographicCamera cam;
    protected GameStateManager gsm;

    public GameState(GameStateManager gsm){
        this.cam = new OrthographicCamera();
        this.gsm = gsm;
    }

    protected abstract void handleInput();
    public abstract void update(float dt); //delta time - difference entre deux frame
    public abstract void render(SpriteBatch sb); //outil qui permet de dessiner sur l'écran
    public abstract void dispose(); //permet de libérer la mémoire

}
