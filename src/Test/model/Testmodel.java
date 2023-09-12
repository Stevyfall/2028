package Test.model;

import model.model;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;



/**
 * Das ist die TestKlasse, die überprüft, ob dei Methoden des Models, wie gewünscht, laufen.
 */
public class Testmodel {
   @BeforeEach
    void setUp(){
        model model = new model();
    }
    @AfterEach
            void tearDown(){}
    int [] ngrid = new int[]{0,4,4,2,2,0,2,2,4};//4,2,4,2,4,0,0
    @Test
    void isArrayLeer(){
        var model= new model();
assertEquals(16, model.free_slots(model.grid));
    }


@Test
    void testOfGame_over(){
        var model = new model();
        assertTrue(model.is_game_over(model.grid));
}
@Test
    void testOfMove(){
        var model = new model();
        //model.move(ngrid);
        assertEquals(12,model.move(ngrid));
       // assertTrue(ngrid[1] == 0);

}
@Test
    void testOfInsertTile(){
        var model = new model();
       model.insert_tile(ngrid,0,4);
        assertTrue(ngrid[0] == 4);
        model.insert_tile(ngrid,0,8);
        assertTrue(ngrid[5] ==8);

}
    @Test
    void testOfMerge(){
        var model = new model();
      //  model.merge(ngrid);
        assertEquals(12,model.merge(ngrid));
    }


}
