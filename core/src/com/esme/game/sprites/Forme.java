package com.esme.game.sprites;

import java.util.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.esme.game.utils.Constants;

/**
 * Conteneur graphique des éléments graphiques (ou formes) affichées à l'écran.
 * Elle contient des informations sur la surface à l'écran (pour la 3D on verra + tard :-) ) et des informations sur l'image.
 */
public class Forme {
    /**
     * Contient la position (en bas et à gauche de l'élément grahique.
     */
    protected Vector2 position;

    /**
     * Contient l'affichage graphique de l'élément (en terme informatique)
     */
    protected Texture texture;

    /**
     * Contient le chemin vers le fichier image. Permet la création de la texture.
     */
    protected String fichierImage;
    //..............................................................................................

    public Texture getTexture() {
        return texture;
    }
    public float getLongueur() {
        return texture.getWidth();
    }
    public float getHauteur() {
        return texture.getHeight();
    }
    /**
     * Renvoie sur l'axe de X, le nombre d'unité (Pixel ?) de la fin de l'Element graphique : Forme.
     * Le code est optimisable. Nous fixerons dans un premier temps (manque de temps) la valeur de largeur et de la hauteur en dur 760 sur l'axe des X et 110 sur l'axe des X.
     *
     * S'il y a le temps la méthode sera automatique avec peut-être l'utilisation de org.apache.commons.imaging.ImafeInfo.
     */
    public float getPositionX() {
        return this.position.x;
    }
    /**
     * Identique mais sur l'axe des Y.
     */
    public float getPositionY() {
        return this.position.y;
    }


    /**
     * Construit a partir du fichier image, et de la position  (x et y)  nécessaire au placement de l'image.
     * @param x
     * @param y
     * @param fichierImage
     */
    public Forme(float x, float y, String fichierImage) {
        //"data/character/basechara.png"
        this.position = new Vector2(x,y);
        this.texture = new Texture(Gdx.files.internal(fichierImage));
        this.fichierImage = fichierImage;
    }
    //..............................................................................................

    protected float hautPlateformeCorrige() {
        float hautPlateforme = this.position.y;
        hautPlateforme -= 181;
        float epaisseurAPlateforme = this.getHauteur();
        hautPlateforme += epaisseurAPlateforme;
        return hautPlateforme;
    }
    /**
     * @return
     */
    public void dispose() {
        this.texture.dispose();

    }

}