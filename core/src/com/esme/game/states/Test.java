package com.esme.game.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esme.game.managers.GameStateManager;

public class Test extends GameState{ //Un écran du jeu

    private Texture perso;

    public Test(GameStateManager gsm) {
        super(gsm);
        this.perso = new Texture("character/BaseChara.png");
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin(); //on va utiliser le spritebatch
        sb.draw(this.perso, 300, 300); //dessiner la texture (objet, pos X, pos Y)
        sb.end(); //on a fini de dessiner
    }

    @Override
    public void dispose() {
        this.perso.dispose(); //libérer l'espace utilisé par le perso
    }
}
