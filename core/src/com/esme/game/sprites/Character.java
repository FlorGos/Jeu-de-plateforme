package com.esme.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.esme.game.utils.Constants;

import javax.naming.ldap.Control;

public class Character {
    private Texture texture;
    private Vector2 position, fall;
public class Character extends Forme {
    public float getHauteurSurPlateforme() {
        return hauteurSurPlateforme;
    }

    public void setHauteurSurPlateforme(float hauteurSurPlateforme) {
        this.hauteurSurPlateforme = hauteurSurPlateforme;
    }

    /**
     * Hauteur a prendre en compte lors d'un saut sur une plateforme
     */
    private float hauteurSurPlateforme = 0;
    /**
     * Affichage du personnage
     */
    private Animation walking;
    private Vector2 fall;
    Controller controller;

    public Character(float x, float y, Controller controller){
        this.controller=controller;
        this.texture = new Texture(Gdx.files.internal("data/character/basechara.png"));
        this.position = new Vector2(x,y);
    /**
     * Pour l'affichage du personnage en cas de chute.
     */


    /**
     * Constructeur
     * @param position
     * @param fichierImage
     */
    public Character(Vector2 position, String fichierImage){
        super(position.x, position.y, fichierImage );
        this.fall = new Vector2(0,0);
        this.walking = new Animation(8,0.8f); //on a pas les différentes animations du sprite :eyes:
    }
    public Character(float x, float y, String fichierImage){
        super(x, y, fichierImage );
        this.fall = new Vector2(0,0);
        this.walking = new Animation(8,0.8f); //on a pas les différentes animations du sprite :eyes:
    }
    public Character(float x, float y){
        super(x, y, "data/character/basechara.png");
        this.fall = new Vector2(0,0);
        this.walking = new Animation(8,0.8f); //on a pas les différentes animations du sprite :eyes:
    }

    public void update(float dt){
        this.fall.add(0, Constants.GRAVITY);
        this.fall.scl(dt); //multiplie les valeurs du vecteur fall par dt afin d'avoir une chute plus naturelle
        this.position.add(this.fall);
        this.fall.scl(1/dt); //remet les bonnes coordonnées

        if(this.position.y <181){ //pour qu'il soit sur le sol
            this.position.y=181;
            this.fall.y=0;
        }

        if(this.position.x<0){
            this.position.x=0;
        }

        if(this.position.x>3840-this.texture.getWidth()){
            this.position.x=3840-this.texture.getWidth();
        }

        if(!Gdx.input.isKeyPressed(Input.Keys.LEFT)&& !Gdx.input.isKeyPressed(Input.Keys.RIGHT) && this.fall.y==0){
            //si le personnage ne bouge pas, on lui remet sa texture de base
            this.texture = new Texture(Gdx.files.internal("data/character/basechara.png"));
        }

        if(this.fall.y>0){
            //si le perso monte = saute
            this.texture=new Texture(Gdx.files.internal("data/character/Jump.png"));
        }
        if(this.fall.y<0){
            //si le perso chute
            this.texture=new Texture(Gdx.files.internal("data/character/Fall.png"));
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
    public void resteDessous(float positionHaute){
        this.position.y-=positionHaute;
        this.texture = this.walking.getTexture();
        this.walking.update(Gdx.graphics.getDeltaTime());
    }
    public void monteDessus(float positionHaute){
        this.position.y=positionHaute;
        this.hauteurSurPlateforme = positionHaute;
        this.texture = this.walking.getTexture();
        this.walking.update(Gdx.graphics.getDeltaTime());
    }
    public void tombeDessous(float positionBasse){
        this.position.y=positionBasse;
        this.hauteurSurPlateforme = positionBasse;
        this.texture = this.walking.getTexture();
        this.walking.update(Gdx.graphics.getDeltaTime());
    }

    public void jump(){

        if(this.fall.y==0){
        this.fall.y = 1150;}
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

    /**
     * Renvoie vrai si le "Character" peut sauter. Celui-ci ne peut sauter que s'il n'y a pas de plateforme au-dessus de lui.
     */
    public boolean estAutoriseASauter() {
        // TODO implement here
		/*
		Si ( ( XPlateformeGauche <= (X + longueurX  )) et   (X <= xPlateformDroit) ) alors
			// Pas de saut
			afficheEchecSaut()
		sinon
			//Saut autorise
				afficheSautReussi()
		fin
		*/
        return true;
    }

    /**
     * Affiche le personnage avec un échec du saut (Etoile ? Bosse ? avec beep ?)
     */
    private void afficheEchecSaut() {
        // TODO implement here
    }

    /**
     * Le saut est faisable, il faut donc affiche le "character" avec une position Y correspondante à la hauter de la plateforme (YPlateformeGauche + hauteurY) et
     * (si le temps de développement le permet !)
     * une position X du "character" avec un pas de plus  .  (Si le joueur appuiyait sur la flèche de droite il faux X = X + pas, sinon (il allait à gauche) : X = X - pas.
     *
     * Que faire si le joueur faisait du sur place ?
     * Il faut donc conserver dans le jeu l'action précédent le saut ! Et (ce serait encore mieux) permettre que cette action soit de rester sur place !
     */
    public void afficheReussiteSaut() {
        // TODO implement here
    }
}
