package com.esme.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.esme.game.managers.GameStateManager;
import com.esme.game.sprites.Plateform;
import com.esme.game.utils.Constants;
import com.esme.game.sprites.Character;

public class PlayState extends GameState{

    private Texture background, ground;
    private Character character;
    private Array<Plateform> platforms; //liste contenant des plateformes
    //TODO 03 : Par manque de temps il n'y a qu'une seule plateforme dans le jeu. Sinon il faut remplacer
    // l'instance de plateforme par une collection de plateforme.
    private Plateform plateform;

    private boolean backwards = false;


    public PlayState(GameStateManager gsm) {
        super(gsm);
        this.background = new Texture(Gdx.files.internal("bg.jpg"));
        this.ground = new Texture(Gdx.files.internal("bg_ground.png"));

        //TODO 02: Creer l'objet sprite.Plateform contenant la texture. A améliorer en créant plusieurs instances de plateforme
        // et en les mettant dans la collection de plateforme.

        this.character = new Character(Constants.VIEWPORT_WIDTH/2-128/2, this.ground.getHeight());
        //FixMe : Remettre le code suivant pour la gestion avec plusieurs plateforme
        this.platforms = new Array<Plateform>();

        // x = 1280/2 = 640 & y=  768 / 2 = 384
        // Le code suivant est presque bon mais il faut ajuster surtout la valeur X pour qu'elle permette la répartition des plateformes sur l'ecran.
        // this.plateform = new Plateform(Constants.VIEWPORT_WIDTH/2, Constants.VIEWPORT_HEIGHT/2);
        // 4 plateformes
        //for(int i=0; i<Constants.PLATEFORM_COUNT ; i++){
            this.plateform = new Plateform(250,340);
            this.platforms.add(this.plateform);
            this.plateform = new Plateform(1500,360);
            this.platforms.add(this.plateform);
            this.plateform = new Plateform(3000,360);
            this.platforms.add(this.plateform);
        //}

        this.cam.setToOrtho(false, Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            boolean estSurUnePlateforme = false;
            for(Plateform plateform : this.platforms) {
                if (plateform.estDessus(this.character.getPositionX(), this.character.getPositionY(), this.character.getLongueur(), this.character.getHauteur()))
                {
                    estSurUnePlateforme = true;
                }
            }
            if (estSurUnePlateforme)
            {
                this.character.moveRight();
                this.backwards = false;
                this.cam.position.set((this.character.getPosition().x + this.character.getTexture().getWidth() / 2), Constants.VIEWPORT_HEIGHT / 2, 0);
                this.cam.update();
            }
            else
            {
                if ( this.character.getPositionY() > 181  )
                {
                    System.out.println("Cherche DOIT TOMBER");
                    float hauteurATomber = 0;
                    this.character.tombeDessous(hauteurATomber);
                    this.cam.position.set((this.character.getPosition().x + this.character.getTexture().getWidth() / 2), Constants.VIEWPORT_HEIGHT/2, 0);
                    this.cam.update();
                    java.awt.Toolkit.getDefaultToolkit().beep();
                }
                else
                {
                    this.character.moveRight();
                    this.backwards = false;
                    this.cam.position.set((this.character.getPosition().x + this.character.getTexture().getWidth() / 2), Constants.VIEWPORT_HEIGHT / 2, 0);
                    this.cam.update();
                }
            }

        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            boolean estSurUnePlateforme = false;
            for(Plateform plateform : this.platforms) {
                if (plateform.estDessus(this.character.getPositionX(), this.character.getPositionY(), this.character.getLongueur(), this.character.getHauteur()))
                {
                    estSurUnePlateforme = true;
                }
            }
            if (estSurUnePlateforme)
            {
                this.character.moveLeft();
                this.backwards=true;
                this.cam.position.set((this.character.getPosition().x + this.character.getTexture().getWidth() / 2), Constants.VIEWPORT_HEIGHT / 2, 0);
                this.cam.update();
            }
            else
            {
                 if ( this.character.getPositionY() > 181  )
                {
                    System.out.println("Cherche DOIT TOMBER");
                    float hauteurATomber = 0;
                    this.character.tombeDessous(hauteurATomber);
                    this.cam.position.set((this.character.getPosition().x + this.character.getTexture().getWidth() / 2), Constants.VIEWPORT_HEIGHT/2, 0);
                    this.cam.update();
                    java.awt.Toolkit.getDefaultToolkit().beep();
                }
                else
                {
                    this.character.moveLeft();
                    this.backwards=true;
                    this.cam.position.set((this.character.getPosition().x + this.character.getTexture().getWidth() / 2), Constants.VIEWPORT_HEIGHT / 2, 0);
                    this.cam.update();
                }
            }
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            this.character.jump();
            //FixMe Gestion a partir de la collection pour le saut
            boolean estSousUnePlateforme = false;
            for(Plateform plateform : this.platforms) {
                if (plateform.estDessous(this.character.getPositionX(), this.character.getPositionY(), this.character.getLongueur(), this.character.getHauteur()))
                {
                    estSousUnePlateforme = true;
                }
            }
            if (estSousUnePlateforme)
            {
                this.character.resteDessous(this.plateform.getPositionX() + this.plateform.getHauteur() );
                this.cam.position.set((this.character.getPosition().x + this.character.getTexture().getWidth() / 2), Constants.VIEWPORT_HEIGHT/2, 0);
                this.cam.update();
            }
            else
            {
                System.out.println("Cherche a monter");
                boolean estAutoriseAMonter = false;
                for(Plateform plateform : this.platforms) {
                    if (plateform.autoriseASauterDessus(this.character.getPositionX(), this.character.getPositionY(), this.character.getLongueur(), this.character.getHauteur() ))
                    {
                        System.out.println("Cherche DOIT monter");
                        float hauteurASauter = this.plateform.getPositionY();
                        hauteurASauter -= 181;
                        float epaisseurAPlateforme = this.plateform.getHauteur();
                        hauteurASauter += epaisseurAPlateforme;
                        this.character.monteDessus(hauteurASauter);
                        estAutoriseAMonter = true;
                        this.cam.position.set((this.character.getPosition().x + this.character.getTexture().getWidth() / 2), Constants.VIEWPORT_HEIGHT/2, 0);
                        this.cam.update();

                    }
                }
                if (!estAutoriseAMonter)
                {
                    this.cam.position.set((this.character.getPosition().x + this.character.getTexture().getWidth() / 2), Constants.VIEWPORT_HEIGHT/2, 0);
                    this.cam.update();
                }
            }
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
        //sb.draw(this.plateform.getTexture(),this.plateform.getPositionX(),this.plateform.getPositionY());

        //sb.draw(this.plateforms,750,250);
        //? fonctionne comme une condition if - traitement condition remplie : else condition non remplie
        //sb.draw(this.plateform.getTexture(),this.plateform.getPosition().x, this.plateform.getPosition().y);
        for(Plateform plateform : this.platforms) {
            sb.draw(plateform.getTexture(), plateform.getPositionX(), plateform.getPositionY());
        }
        sb.draw(this.character.getTexture(), this.backwards?this.character.getPosition().x+this.character.getTexture().getWidth():this.character.getPosition().x, this.character.getPosition().y,this.backwards?-this.character.getTexture().getWidth():this.character.getTexture().getWidth(),this.character.getTexture().getHeight());
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
