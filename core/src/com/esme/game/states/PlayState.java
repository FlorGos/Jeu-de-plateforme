package com.esme.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.esme.game.managers.GameStateManager;
import com.esme.game.sprites.Plateform;
import com.esme.game.utils.Constants;
import com.esme.game.sprites.Character;

public class PlayState extends GameState{

    private Texture background, ground, plateforms;
    private Character character;
    private Array<Plateform> platforms; //liste contenant des plateformes
    //TODO 03 : Par manque de temps il n'y a qu'une seule plateforme dans le jeu. Sinon il faut remplacer
    // l'instance de plateforme par une collection de plateforme.
    private Plateform laPlateforme;

    private boolean backwards = false;


    public PlayState(GameStateManager gsm) {
        super(gsm);
        this.background = new Texture(Gdx.files.internal("bg.jpg"));
        this.ground = new Texture(Gdx.files.internal("bg_ground.png"));
        this.plateforms = new Texture(Gdx.files.internal("plateform.png"));
        //TODO 02: Creer l'objet sprite.Plateform contenant la texture. A améliorer en créant plusieurs instances de plateforme
        // et en les mettant dans la collection de plateforme.
        this.laPlateforme = new Plateform(0);
        this.character = new Character(Constants.VIEWPORT_WIDTH/2-128/2, this.ground.getHeight());
        //this.plateform = new Plateform(Constants.VIEWPORT_WIDTH/2, Constants.VIEWPORT_HEIGHT/2);
        /*this.platforms = new Array<Plateform>();
        for(int i=0; i<Constants.PLATEFORM_COUNT ; i++){
            this.platforms.add(new Plateform(i));
        }*/

        this.cam.setToOrtho(false, Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            this.character.moveRight();
            this.backwards=false;
            this.cam.position.set((this.character.getPosition().x + this.character.getTexture().getWidth() / 2), Constants.VIEWPORT_HEIGHT/2, 0);
            this.cam.update();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            this.character.moveLeft();
            this.backwards=true;
            this.cam.position.set((this.character.getPosition().x + this.character.getTexture().getWidth() / 2), Constants.VIEWPORT_HEIGHT/2, 0);
            this.cam.update();
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            this.character.jump();
            this.cam.position.set((this.character.getPosition().x + this.character.getTexture().getWidth() / 2), Constants.VIEWPORT_HEIGHT/2, 0);
            this.cam.update();
        }
    }

    @Override
    public void update(float dt) {
        this.handleInput();
        this.character.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {

        sb.setProjectionMatrix(this.cam.combined);
        sb.begin();
        sb.draw(this.background,0,0);
        sb.draw(this.ground,0,0);
        // FIXME 01 : Remplacer l'affichage "this.plateforms" par l'affichage d'une liste de plateform (pas texture !)
        sb.draw(this.plateforms,250,250);
        //sb.draw(this.plateforms,750,250);

        sb.draw(this.character.getTexture(), this.backwards?this.character.getPosition().x+this.character.getTexture().getWidth():this.character.getPosition().x, this.character.getPosition().y,this.backwards?-this.character.getTexture().getWidth():this.character.getTexture().getWidth(),this.character.getTexture().getHeight());
        //? fonctionne comme une condition if - traitement condition remplie : else condition non remplie

        //sb.draw(this.plateform.getTexture(),this.plateform.getPosition().x, this.plateform.getPosition().y);
        /*for(Plateform plateform : this.platforms){
            sb.draw(plateform.getTexture(), plateform.getPosition().x, plateform.getPosition().y);
        }*/
        sb.end();
    }

    @Override
    public void dispose() {
        this.background.dispose();
        this.ground.dispose();
        this.character.dispose();
        //this.plateform.dispose();
        /*for(Plateform plateform : this.platforms){
            plateform.dispose();
        }*/
    }
}
