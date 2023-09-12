package view;


import controller.Controller;
import controller.IHellView;
import controller.Icontroller;
import processing.core.PApplet;

/**
 * Die View Klasse des Spiels mit dem Darktheme.
 */
public class dunkelview extends PApplet implements IHellView {
    final int X_POS = 0;
    final int Y_POS = 0;
    final int X_OFFSET = 20;
    final int Y_OFFSET = 20;
    final int SIZE_TILE = 80;
    final int SIZE_BORDER = 10;
    private final int X_SIZE = 2*X_POS+2*X_OFFSET+SIZE_BORDER+4*(SIZE_TILE+SIZE_BORDER);
    private final int Y_SIZE = 2*Y_POS+2*Y_OFFSET+SIZE_BORDER+4*(SIZE_TILE+SIZE_BORDER);
    /**
     * Erzeugt ein Objekt des Interfaces vom Controller, um seine Methoden zu benutzen.
     */
    Icontroller icontroller;

    /**
     *
     */
    public dunkelview(){

  Controller con;

}

    /**
     * Die Main Methode lässt das gesamte Projekt laufen.
     * @param args args default Parameter von der Main Methode.
     */
    public static void main(String[] args) {
      PApplet.main(dunkelview.class);
    }

    /**
     * Definiert die Größe des Fernsters des Spiels.
     */
    public void settings() {
        super.size(X_SIZE, Y_SIZE);
    }

    /**
     * Plaziert ein Zeiger auf dem Objekt des IController.
     */
    public void setup() {
        icontroller= new Controller(this);
    }

    /**
     * Zeichnet alle Frames.
     */
   public void draw() {
       icontroller.nextFrame();
   }
   /**
    * Zeichnet der erste Frame und alles, was angezeigt werden muss.
    */

    public void drawTitleScreen() {
        super.background(color(0, 0, 0));
        super.textAlign(CENTER, CENTER);
        super.noStroke();
        super.textSize(64);
        super.text("Game Start", X_SIZE/2, Y_SIZE/2);
        super.textSize(16);
        super.text("Press any key to start", X_SIZE/2, Y_SIZE/2+45);
        super.fill(255);
    }

    /**
     * Eine Ergänzung der Keypressed-Methode vom Controller. Diese hier beinhaltet Methoden von der Processing-Klasse,
     * die nur im View implementiert wird.
     */
    public void keyPressed() {
        if (key == CODED) {
            switch(keyCode) {
                case LEFT:
                    icontroller.userInput("LEFT");
                    break;
                case RIGHT:
                    icontroller.userInput("RIGHT");
                    break;
                case UP:
                    icontroller.userInput("UP");
                    break;
                case DOWN:
                    icontroller.userInput("DOWN");
            }
        }
    }

    /**
     * Zeichnet den Inhalt der Kästchen und deren veränderungen im Laufe des Spiels.
     */
    public void setupGame() {
        super.textAlign(CENTER, CENTER);
        super.textSize(35);
        super.stroke(33);
        super.strokeWeight(2);
        super.background(color(50, 50, 50));
        super.colorMode(HSB, 360, 100, 100);
        icontroller.setupGrid();
    }

    /**
     * Zeichnet der Frame im Falle eines <code>GAME_OVER</code>.
     * @param score Endergebnis aller bisherigen additionen im Spiel.
     */
    public void drawGameOver(int score) {
        super.colorMode(RGB, 255, 255, 255);
        super.background(color(0, 255, 0));
        super.textAlign(CENTER, CENTER);
        super.noStroke();
        super.textSize(64);
        super.text("Gameover", X_SIZE/2, Y_SIZE/2);
        super.textSize(16);
        super.text("Score: " + score, X_SIZE/2, Y_SIZE/2+45);
        super.fill(255);
    }

    /**
     * Gibt die Form und Dimensionen der Kästchen, die Veränderungen der Zahlen und die Veränderungen der Farbe
     * jedes Kästchen je nach dem Score im Laufe des Spiels.
     * @param grid Array dessen Länge ist die Anzahl der kästchen im Spiel.
     */
    public void drawGame(int[] grid) {
        //int edge_length = int(sqrt(grid.length));
        int edge_length = (int)sqrt(grid.length);
        int i = 0;
        int X, Y;
        for (int y=0; y<edge_length; y++) {
            Y = Y_POS+Y_OFFSET+SIZE_BORDER+y*(SIZE_TILE+SIZE_BORDER);
            for (int x=0; x<edge_length; x++) {
                X = X_POS+X_OFFSET+SIZE_BORDER+x*(SIZE_TILE+SIZE_BORDER);
                //fill(color(179, 189, 214));
                super.fill(color(30+log(grid[i]+1)/log(2)*10, 100, 100));
                super.rect(X, Y, SIZE_TILE, SIZE_TILE, 15);
                if (grid[i] != 0) {
                    super.fill(color(271, 0, 1));
                    super.text(grid[i], X+SIZE_TILE/2+1, Y+SIZE_TILE/2+1);
                }
                i++;
            }
        }
    }

}




