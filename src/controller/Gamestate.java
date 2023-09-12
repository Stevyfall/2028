package controller;

/**
 * Es werden hier alle möglichen Staten des Spiels aufgelistet. Diese werden im Controller und im View benutzt.
 */

public enum Gamestate {
    /**
     * Der Erste Spielstatus bei dem der Spieler eine beliebige Taste drücken soll. Dann wird er zum <code>GAME</code>
     * weitergeleitet.
     */
    TITLE_SCREEN,
    /**
     * In diesem Status wird das Spiel fortgesetzt bis zum <code>GAME_OVER</code>.
     */
    GAME,
    /**
     * Der letzte Status, der drauf hinweist, dass das Spiel beendet ist.
     */
    GAME_OVER
}
