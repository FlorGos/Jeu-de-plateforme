package com.esme.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.esme.game.utils.Constants;

public class Character {
    private Texture texture;
    private Vector2 position, fall;
    private Animation walking;

    public Character(float x, float y){
        this.texture = new Texture("character/BaseChara.png");
        this.position = new Vector2(x,y);
        this.fall = new Vector2(0,0);
        this.walking = new Animation(8,0.5f); //on a pas les différentes animations du sprite :eyes:
    }

    public void update(float dt){
        this.fall.add(0, Constants.GRAVITY);
        this.fall.scl(dt); //multiplie les valeurs du vecteur fall par dt afin d'avoir une chute plus naturelle
        this.position.add(this.fall);
        this.fall.scl(1/dt); //remet les bonnes coordonnées

        if(this.position.y <97){ //pour qu'il soit sur le sol
            this.position.y=97;
            this.fall.y=0;
        }

        if(this.position.x<0){
            this.position.x=0;
        }

        if(this.position.x>Constants.VIEWPORT_WIDTH-this.texture.getWidth()){
            this.position.x=Constants.VIEWPORT_WIDTH-this.texture.getWidth();
        }

        if(!Gdx.input.isKeyPressed(Input.Keys.LEFT)&& !Gdx.input.isKeyPressed(Input.Keys.RIGHT) && this.fall.y==0){
            //si le personnage ne bouge pas, on lui remet sa texture de base
            this.texture = new Texture("character/baseChara.png");
        }

        if(this.fall.y>0){
            //si le perso monte = saute
            this.texture=new Texture("character/Jump.png");
        }
        if(this.fall.y<0){
            //si le perso chute
            this.texture=new Texture("character/Fall.png");
        }
    }

    public void moveRight(){
        this.position.x+=Constants.CHAR_SPEED;
        this.texture = this.walking.getTexture();
        this.walking.update(Gdx.graphics.getDeltaTime());
    }

    public void moveLeft(){
        this.position.x-=Constants.CHAR_SPEED;
        this.texture = this.walking.getTexture();
        this.walking.update(Gdx.graphics.getDeltaTime());
    }

    public void jump(){

        if(this.fall.y==0){
        this.fall.y = 1350;}
    }

    public Texture getTexture(){
        return this.texture;
    }

    public Vector2 getPosition(){
        return this.position;
    }

    public void dispose(){
        this.texture.dispose();
    }
}
