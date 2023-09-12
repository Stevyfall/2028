package controller;

/**
 * Ein Interface vom View dessen Methoden im Controller benutzt werden können.
 */
public interface IHellView {
    /**
     * Definiert alle Einstellungen vom ersten Frame und diesbezüglich vom ersten Spielstatus.
     */
    public void drawTitleScreen();

    /**
     * Definiert die Form aller Kästchen und wie der Inhalt gezeigt wird.
     */
    public void setupGame();

    /**
     * Zeigt gie Zahl in jedem Kästchen nach jeder Spielereingabe und verändert die Farbe des Kästchen je nach dem,
     * welche Zahl im Kästchen steht.
     * @param grid Array dessen Länge ist die Anzahl der kästchen im Spiel.
     */
    public void drawGame(int[] grid);

    /**
     * Zeichnet das letzte Frame und sagt, dass das Spiel am Ende ist. Es wird dann den Score zurückgegeben.
     * @param score Endergebnis aller bisherigen additionen im Spiel.
     */

    public void drawGameOver(int score);
}
