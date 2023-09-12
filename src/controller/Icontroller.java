package controller;

/**
 * Ein Interface vom Controller. Alle Elemente, die hier nicht aufgelistet werden, können nicht vom View benutzt werden.
 */

public interface Icontroller {
    /**
     * Wird bei jeder vom Controller entschiedene Anzeigeänderung aufgerufen.
     */
    public void nextFrame();
   // public  int[] getm();

    /**
     * Wird bei jeder Keyeingabe des Spielers aufgerufen.
     * @param key : die vom Spieler ausgewählte Bewegungsrichtung.
     */

    public void userInput(String key);

    /**
     * Sorgt für die Beliebigkeit der Position neuer Kästchen nach einer Spielereingabe.
     */
    public  void setupGrid();


}
