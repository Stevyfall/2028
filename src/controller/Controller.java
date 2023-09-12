package controller;

import model.model;
import view.dunkelview;
import view.hellview;

import java.util.Arrays;

/**
 * Der Controller steuert, welche Frames der View zeigen muss. Das heißt für jeden Schritt im Spiel
 * wird(dank der Enum {@link Gamestate})entschieden welches Frame dran ist.
 *Dieser Controller kann Objekte vom {@link model} erzeugen, und sie benutzen.
 */

public class Controller implements Icontroller{
    private  model model;
    private static IHellView Ihv;
    private Gamestate state;

    /**
     * Erzeugt ein Objekt vom Controller mit einem Objekt des Interfaces vom View, einem Objekt des Models
     * und einem Objekt des Gamestates als Elemente.
     * @param Ihv  Dieses Objekt vom IHellView kann später nicht verändert werden.
     */

    public Controller(IHellView Ihv) {
        this.Ihv= Ihv;
        this.model = new model();
        this.state = Gamestate.TITLE_SCREEN;
    }

    /**
     * Beschreibt je nach Spielstatus das anzuzeigende Frame und ruft dafür verschiedene Methoden aus dem {@link hellview}
     * bzw. aus dem {@link dunkelview}.
     *Sobald sich der state im Spiel sich verändert entscheidet diese Methode, was jetzt angezeigt werden soll.
     */

    public void nextFrame() {
        switch(state) {
            case TITLE_SCREEN -> {
                Ihv.drawTitleScreen();
            }
            case GAME -> {
                Ihv.drawGame(model.grid);
                if(model.is_game_over(model.grid)) state = Gamestate.GAME_OVER;
            }
            case GAME_OVER -> {
                Ihv.drawGameOver(model.score);
            }
        }
    }


    /**
     * Der Spielstatus wird je nach Eingaben des Spielers ausgewertet.
     * Am Anfang mus der Spieler auf eine beliebige Taste drücken, um das Spiel zu starten.
     * <p>
     * Nach dem Start wird der Score nach jeder Spielereingabe berechnet und im Terminal ausgegeben.
     * @param key kann entweder LEFT, RIGHT, UP oder DOWN sein.
     */

    public void userInput(String key) {
        switch(state) {
            case TITLE_SCREEN -> {
                Ihv.setupGame();
                state = Gamestate.GAME;
                return; //userInput bricht ab und es wird noch keine neue Tile erzeugt
            }
            case GAME -> {
                int[] temp_grid = new int[model.grid.length];
                //arrayCopy(model.grid, temp_grid);
                System.arraycopy(model.grid, 0, temp_grid, 0,model.grid.length);
                if(model.game) {
                    switch (key) {
                        case "LEFT" -> model.score += model.move(model.grid);
                        case "RIGHT" -> {
                            model.rotate_i(model.grid, 2);
                            model.score += model.move(model.grid);
                            model.rotate_i(model.grid, 2);
                        }
                        case "UP" -> {
                            model.rotate(model.grid);
                            model.score += model.move(model.grid);
                            model.rotate_i(model.grid, 3);
                        }
                        case "DOWN" -> {
                            model.rotate_i(model.grid, 3);
                            model.score += model.move(model.grid);
                            model.rotate(model.grid);
                        }
                    }
                }
                if(!Arrays.equals(model.grid,temp_grid)) {
                    model.random_tile(model.grid);
                    System.out.println("SCORE =" + model.score);
                }
                if(model.is_game_over(model.grid)) {
                    model.game = false;
                    System.out.println("GAME OVER. YOUR SCORE =" + model.score);
                }
            }
        }

    }

    /**
     * Ruft die Methode aus dem {@link model}, die neuen hinzugefügten Kästchen nach einer Eingabe des Spielers an leeren Stellen
     * positioniert.
     */
    public void setupGrid() {
        model.random_tile(model.grid);
        model.random_tile(model.grid);
        Ihv.drawGame(model.grid);
    }
}
