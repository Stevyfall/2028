package model;

import java.util.Arrays;
import java.util.Random;

/**
 *Diese Klasse ist das model von unserem Spiel und steuert deswegen die gesamte Funktionsweise des Spiels, d.h alles,
 *  was im Hintergrund passiert.
 */
public class model {

    /**
     * Erzeugung einen Objekten der Klasse Random, um Werte im Model zu randomisieren.
     */
    Random rand = new Random();
    /**
     * Score des Spiels.
     */
    public  int score = 0;
    /**
     * boolean Wert zur Bestimmung der Fortsetzung des Spiels.
     */
    public  boolean game = true;
    /**
     * angelegter Array.
     */

    public int[] grid = new int[16];

  /*  public int[] getGrid() {
        return this.grid;
    }

    public void setGrid(int[] grid) {
        this.grid = grid;
    }*/

    /**
     * prüft, ob das Array leer ist
     * @param grid Array
     * @return 15
     */
    public int free_slots(int[] grid) {
        int i = 0;
        for (int val : grid) {
            if (val == 0) {
                i++;
            }
        }
        return i;
    }

    /**
     *Steuert die genommene Richtung der Kästchen und deren Positionen.
     *
     * @param grid Array
     */

    public void rotate(int[] grid) {
       int [] temp_grid = new int[grid.length];
        for (int i=0; i<grid.length; i++) {
            temp_grid[i+12-(i%4)*5-(i/4)*3] = grid[i];
        }
        System.arraycopy(temp_grid,0, grid ,0,temp_grid.length);
    }

    /**
     *Wertet die Anzahl der gesetzten Kästchen.
     *
     * Sorgt fürs Balancieren der Zahlen bei der Bewegung der Kästchen.
     *
     * @param grid Array dessen Länge die Anzahl aller Kästchen ist.
     * @param n Anzahl noch verfügbaren Kästchen.
     */

    public void rotate_i(int[] grid, int n) {
        for (int i=1; i<=(n%4); i++) { rotate(grid); };

    }

    /**
     *Steuert die Auslenkung der Kästchen während des Spiels.
     *
     * @param grid Array dessen Länge die Anzahl aller Kästchen ist.
     */

    public void shift(int[] grid) {
        int offset=0;
        for (int i=0; i<grid.length; i++) {
            if (i%4 == 0) {
                offset = 0;
            }
            if (grid[i] == 0) {
                offset++;
            } else if (offset > 0) {
                grid[i-offset] = grid[i];
                grid[i] = 0;
            }
        }
    }

    /**
     *Steuert die Bewegung aller Kästchen je nach Eingabe und berechnet der dazu werdende Score,
     * in dem die Methoden Shift und merge aufgerufen werden.
     *
     * @param grid Array dessen Länge die Anzahl aller Kästchen ist.
     * @return Score.
     */
  public int move(int[] grid) {
        int score = 0;
        shift(grid);
        score = merge(grid);
        shift(grid);
        return score;
    }

    /**
     * Wertet im Laufe des Spiels aus, ob das Spiel beendet ist oder nicht.
     *
     * @param grid Array dessen Länge die Anzahl aller Kästchen ist.
     * @return eine des Arrays Grid.
     */
    public boolean is_game_over(int[] grid) {
        int[] temp_grid = new int[grid.length];
      //  System.arraycopy(grid,0, temp_grid,0,2);
        System.arraycopy(grid,0, temp_grid, 0, grid.length);
        for (int i=1; i<=8; i++) {
            move(temp_grid);
            rotate(temp_grid);
        }
        return Arrays.equals(temp_grid,grid);
    }

    /**
     * berechnet der Score im Laufe des Spiels und dibt es zurück.
     *
     * @param grid Array dessen Länge die Anzahl aller Kästchen ist.
     * @return erreichte Score.
     */
    public int merge(int[] grid) {
        int score = 0;
        for (int i=0; i<grid.length -1; i++) {
            if (i%4 < 3) {
                if (grid[i] > 0 && grid[i] == grid[i+1]) {
                    grid[i] += grid[i+1];
                    grid[i+1] = 0;
                    score += grid[i];
                }
            }
        }
        return score;
    }

    /**
     * Sorgt fürs Hinzufügen neuer Kästchen und gibt einen Wert in jedem.
     * @param grid Array dessen Länge die Anzahl aller Kästchen ist.
     * @param n Anzahl noch verfügbaren Kästchen.
     * @param val Im Kästchen angezeigter Wert.
     */
    public void insert_tile(int[] grid, int n, int val) {
        for (int i = 0; i < grid.length; i++) {
            if (grid[i] == 0) {
                if (n == 0) {
                    grid[i] = val;
                    break;
                }
                n--;
            }
        }
    }

    /**
     * randomisiert die Position aller hinzugefügten Kästchen.
     * @param grid Array dessen Länge die Anzahl aller Kästchen ist.
     */
    public void random_tile(int[] grid) {
        //Random random = new Random();
        int pos, val;
        //pos = (int)rand.nextFloat(0, free_slots(grid));
        pos = (int) rand.nextFloat(free_slots(grid));
        val = rand.nextFloat(0, 1) < 0.9 ? 2 : 4; //val = random(0, 1) < 0.9 ? 2 : 4;
        insert_tile(grid, pos, val);
    }

    /**
     * macht einen Zeilenumbruch im Terminal nach jeder Eingabe
     * @return Zeilenumbruchbefehl
     */
    public String toString() {
        String output = "";
        for(int i = 0; i < grid.length; i++) {
            if(i%4 == 0) output += "\n";
            output += " " + grid[i];
        }
        return output;
    }


    }
