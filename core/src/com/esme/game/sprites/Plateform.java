package com.esme.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.esme.game.utils.Constants;
import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;

import java.util.Random;

public class Plateform {
    private Texture texture;
    private Vector2 position;
    private Random random;

    //dans notre cas il faudrait dessiner une map entière avec différentes plateformes -> une classe par map ?
    public Plateform(int i){ //i=index plateforme dans tableau
        this.texture=new Texture("plateform.png");
        this.random = new Random();
        this.position=new Vector2(this.random.nextInt(Constants.VIEWPORT_WIDTH-this.texture.getWidth()), (350+this.random.nextInt(100)*i));
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
