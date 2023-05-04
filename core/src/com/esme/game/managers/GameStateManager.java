package com.esme.game.managers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esme.game.states.GameState;

import java.util.Stack;

public class GameStateManager {
    private Stack<GameState> gameStates;

    public GameStateManager(){
        this.gameStates = new Stack<GameState>();
    }

    public void push(GameState gameState){ //met en place le premier élément sur la pile de gameState
        this.gameStates.push(gameState);
    }

    public void set(GameState gameState){ //permet le changement de gameState
        this.gameStates.pop().dispose(); //libère la mémoire de l'ancien gameState
        this.gameStates.push(gameState); //met en place le nouveau gameState
    }

    public void update(float dt){
        this.gameStates.peek().update(dt); //utilise l'objet au dessus de la pile et le replace au dessus - ne le détruit pas
    }

    public void render(SpriteBatch sb){
        this.gameStates.peek().render(sb);
    }
}
