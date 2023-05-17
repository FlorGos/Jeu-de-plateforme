package com.esme.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.esme.game.utils.Constants;
//import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;

import java.util.Random;

/**
 * Plateforme sur laquel le "character" peut monter (ou descendre)/
 */
public class Plateform extends Forme {
	/** Attribut probablement pas utilise car preferablement utiliser l'index de la collection contenant les plateformes.
	**/
	private int indexDansPlateforme = 0;

    /**
    	Utilité ?
    */
    private Random random;

    /**
     * Default constructor
     */
    public Plateform(float x, float y, String fichierImage) {
        super(x, y, fichierImage );
        this.random = new Random();
    }
    public Plateform(float x, float y) {
        super(x, y, "plateform.png" );
        this.random = new Random();
    }
    
    // Dans notre cas il faudrait dessiner une map entière avec différentes plateformes -> une classe par map ?
    /**
    	Utilité ?
    **/
    public Plateform(int i, float x, float y){ //i=index plateforme dans tableau
        super(x, y, "plateform.png" );
        indexDansPlateforme = i;
        this.random = new Random();
        //this.position=new Vector2(this.random.nextInt(Constants.VIEWPORT_WIDTH-this.texture.getWidth()), (350+this.random.nextInt(100)*i));
    }
    /**
     * Renvoie vrai si le "Character" peut sauter. Celui-ci ne peut sauter que s'il n'y a pas de plateforme au-dessus de lui.
     * @param yCaractere Ne sert à rien dans un premier temps. (il faudrait differente hauteur de plateformes. Plus tard...
     * @param yLongueurCaractere Idem ne sert à rien pour la meme raison
     * return true si le saut est possible
     */
    public boolean estDessous(float xCaractere, float yCaractere, float xLongueurCaractere, float yLongueurCaractere) {
        float hauteurPlateformeTmp = hautPlateformeCorrige();
        //System.out.println("estDessous hauteurPlateformeTmp=" + hauteurPlateformeTmp + "this.position.x= " + this.position.x + "- xCaractere=" + xCaractere + " - xLongueurCaractere=" + xLongueurCaractere + " - this.getLongueur() = " + this.getLongueur() + "." );
        System.out.println("estDessous hauteurPlateformeTmp=" + hauteurPlateformeTmp + "this.position.x= " + this.position.x + "<= xCaractere=" + xCaractere + " + xLongueurCaractere/2=" + (xLongueurCaractere/2) + "&& (xCaractere - (xLongueurCaractere/2)) =" + (xCaractere - (xLongueurCaractere/2)) + " <= (this.position.x + this.getLongueur())=" + (this.position.x + this.getLongueur()) +  "****** this.getLongueur() = " + this.getLongueur() + "." );
        if ( yCaractere < hauteurPlateformeTmp) {
            if ( (this.position.x <= (xCaractere + (xLongueurCaractere / 2))) && ((xCaractere + (xLongueurCaractere/2) ) <= (this.position.x + this.getLongueur()) ) ) {
                System.out.println("estDessOus TRUE");
                return true;
            }
        }
        return false;
    }
    public boolean estDessus(float xCaractere, float yCaractere, float xLongueurCaractere, float yLongueurCaractere) {
        float hauteurPlateformeTmp = hautPlateformeCorrige();
        System.out.println("estDessus hauteurPlateformeTmp=" + hauteurPlateformeTmp + "this.position.x= " + this.position.x + "<= xCaractere=" + xCaractere + " + 3 x  xLongueurCaractere=" + ((3*xLongueurCaractere) / 4) + " - this.getLongueur() = " + this.getLongueur() + "." );
        if ( yCaractere >= hauteurPlateformeTmp) {
            //- (xLongueurCaractere/2)
            if ( (this.position.x <= (xCaractere + ((3*xLongueurCaractere) / 4))) && ((xCaractere + (xLongueurCaractere/4) ) <= (this.position.x + this.getLongueur()) ) ) {
                System.out.println("estDessus TRUE");
                return true;
            }
        }
        return false;
    }

    public boolean autoriseASauterDessus(float xCaractere, float yCaractere, float xLongueurCaractere, float yLongueurCaractere) {
        float hauteurPlateformeTmp = hautPlateformeCorrige();
        System.out.println("Auto Gauche  this.position.x= " + this.position.x + "<= (xCaractere=" + xCaractere + " + 3 x (xLongueurCaractere)/4=" + (3 * (xLongueurCaractere/4)) + ") **" + (xCaractere + (xLongueurCaractere/2)) + "**." );
        System.out.println("Auto Droit (xCaractere=" + xCaractere + " - xLongueurCaractere=" + xLongueurCaractere + "  ) **" + (xCaractere + (xLongueurCaractere/4)) + "** <= lim = " + (this.position.x + this.getLongueur() - (xLongueurCaractere) -20)  + ") **") ;
        if ( yCaractere < hauteurPlateformeTmp) {
            //if ((this.position.x <= (xCaractere + (xLongueurCaractere / 2))) && ((xCaractere - (xLongueurCaractere/2)) <= (this.position.x + this.getLongueur() - xLongueurCaractere))) {
            if ( (this.position.x <= (xCaractere + ((3*xLongueurCaractere) / 4))) && ((xCaractere + (xLongueurCaractere/4)) <= (this.position.x + this.getLongueur()) ) ) {
                System.out.println("autorise TRUE");
                return true;
            }
        }
        return false;
    }
}
