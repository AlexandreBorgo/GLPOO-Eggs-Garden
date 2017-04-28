package GLPOO_ESIEA_1617_Groupe_Borgo;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;

public class Garden1Test {

	private static final Logger LOGGER = Logger.getLogger("Garden Test Logger");
	
	private final static String GARDEN_FILE_1 = "gardens/test1.txt";
	
	private Game game;
	private Garden garden;
	private ArrayList<Kid> list_kids;
	
	@Before
	public void doBefore() {
		game = new Game();

		File file = new File(GARDEN_FILE_1);
		garden = new Garden(game, file);
		list_kids = garden.getKidsList();
    }
	
	
	/* ###################### Size ###################### */
	@Test
	public void Garden1_SizeX_Test() {
		assertEquals(10, garden.getSizeX());
	}
	
	@Test
	public void Garden1_SizeY_Test() {
		assertEquals(10, garden.getSizeY());
	}
	
	/* ###################### Cases ###################### */
	@Test
	public void Garden1_Case21_Test() {
		assertEquals(Item.ROCK, garden.getCase(2,1));
	}
	
	@Test
	public void Garden1_Case22_Test() {
		assertEquals(Item.ROCK, garden.getCase(2,2));
	}
	
	@Test
	public void Garden1_Case23_Test() {
		assertEquals(Item.ROCK, garden.getCase(2,3));
	}
	
	@Test
	public void Garden1_Case24_Test() {
		assertEquals(Item.ROCK, garden.getCase(2,4));
	}
	
	@Test
	public void Garden1_Case25_Test() {
		assertEquals(Item.ROCK, garden.getCase(2,5));
	}
	
	@Test
	public void Garden1_Case71_Test() {
		assertEquals(Item.EGG, garden.getCase(7,1));
	}
	
	@Test
	public void Garden1_Case72_Test() {
		assertEquals(Item.EGG, garden.getCase(7,2));
	}
	
	@Test
	public void Garden1_Case73_Test() {
		assertEquals(Item.EGG, garden.getCase(7,3));
	}
	
	@Test
	public void Garden1_Case74_Test() {	
		assertEquals(Item.EGG, garden.getCase(7,4));
	}
	
	@Test
	public void Garden1_Case75_Test() {
		assertEquals(Item.EGG, garden.getCase(7,5));
	}

	/* ###################### Kid ###################### */
	@Test public void Garden1_Kid10_Test() {
		int pos_x = 1;
		int pos_y = 0;
		for(int i=0; i<list_kids.size(); i++ ) {
			if(list_kids.get(i).getPosX() == pos_x && list_kids.get(i).getPosY() == pos_y) {
				assertTrue(true);
			}		
		}
	}
	
	@Test 
	public void Garden1_Kid36_Test() {
		int pos_x = 3;
		int pos_y = 6;
		for(int i=0; i<list_kids.size(); i++ ) {
			if(list_kids.get(i).getPosX() == pos_x && list_kids.get(i).getPosY() == pos_y) {
				assertTrue(true);
			}		
		}
	}
	
	@Test 
	public void Garden1_Kid09_Test() {
		int pos_x = 0;
		int pos_y = 9;
		for(int i=0; i<list_kids.size(); i++ ) {
			if(list_kids.get(i).getPosX() == pos_x && list_kids.get(i).getPosY() == pos_y) {
				assertTrue(true);
			}		
		}
	}
	
	@Test 
	public void Garden1_Kid99_Test() {
		int pos_x = 9;
		int pos_y = 9;
		for(int i=0; i<list_kids.size(); i++ ) {
			if(list_kids.get(i).getPosX() == pos_x && list_kids.get(i).getPosY() == pos_y) {
				assertTrue(true);
			}		
		}
	}
	
	@Test 
	public void Garden1_Kid85_Test() {
		int pos_x = 8;
		int pos_y = 5;
		for(int i=0; i<list_kids.size(); i++ ) {
			if(list_kids.get(i).getPosX() == pos_x && list_kids.get(i).getPosY() == pos_y) {
				assertTrue(true);
			}		
		}
	}
	
	/* ###################### Direction ###################### */
	@Test public void Garden1_Direction10_Test() {
		int pos_x = 1;
		int pos_y = 0;
		for(int i=0; i<list_kids.size(); i++ ) {
			if(list_kids.get(i).getPosX() == pos_x && list_kids.get(i).getPosY() == pos_y) {				
				assertEquals("AAAAAAGAAGAAAAAAGAAGAAAAAA", list_kids.get(i).getStringPath());
			}		
		}
	}
	
	@Test 
	public void Garden1_Direction36_Test() {
		int pos_x = 3;
		int pos_y = 6;
		for(int i=0; i<list_kids.size(); i++ ) {
			if(list_kids.get(i).getPosX() == pos_x && list_kids.get(i).getPosY() == pos_y) {			
				assertEquals("AAAAAAGAAGAAAAAAGAAGAAAAAA", list_kids.get(i).getStringPath());
			}		
		}
	}
	
	@Test 
	public void Garden1_Direction09_Test() {
		int pos_x = 0;
		int pos_y = 9;
		for(int i=0; i<list_kids.size(); i++ ) {
			if(list_kids.get(i).getPosX() == pos_x && list_kids.get(i).getPosY() == pos_y) {			
				assertEquals("AAAGGAAAAGGAAAAGGGAAAAGGGA", list_kids.get(i).getStringPath());
			}		
		}
	}
	
	@Test 
	public void Garden1_Direction99_Test() {
		int pos_x = 9;
		int pos_y = 9;
		for(int i=0; i<list_kids.size(); i++ ) {
			if(list_kids.get(i).getPosX() == pos_x && list_kids.get(i).getPosY() == pos_y) {			
				assertEquals("AAAGGAAAAGGAAAAGGGAAAAGGGA", list_kids.get(i).getStringPath());
			}		
		}
	}
	
	@Test 
	public void Garden1_Direction85_Test() {
		int pos_x = 8;
		int pos_y = 5;
		for(int i=0; i<list_kids.size(); i++ ) {
			if(list_kids.get(i).getPosX() == pos_x && list_kids.get(i).getPosY() == pos_y) {			
				assertEquals("AADADAAGAGAADADAAGAGAADADAAGAG", list_kids.get(i).getStringPath());
			}		
		}
	}

	
	/* ###################### Path ###################### */
	@Test public void Garden1_Path10_Test() {
		int pos_x = 1;
		int pos_y = 0;
		for(int i=0; i<list_kids.size(); i++ ) {
			if(list_kids.get(i).getPosX() == pos_x && list_kids.get(i).getPosY() == pos_y) {				
				assertEquals("AAAAAAGAAGAAAAAAGAAGAAAAAA", list_kids.get(i).getStringPath());
			}		
		}
	}
	
	@Test 
	public void Garden1_Path36_Test() {
		int pos_x = 3;
		int pos_y = 6;
		for(int i=0; i<list_kids.size(); i++ ) {
			if(list_kids.get(i).getPosX() == pos_x && list_kids.get(i).getPosY() == pos_y) {			
				assertEquals("AAAAAAGAAGAAAAAAGAAGAAAAAA", list_kids.get(i).getStringPath());
			}		
		}
	}
	
	@Test 
	public void Garden1_Path09_Test() {
		int pos_x = 0;
		int pos_y = 9;
		for(int i=0; i<list_kids.size(); i++ ) {
			if(list_kids.get(i).getPosX() == pos_x && list_kids.get(i).getPosY() == pos_y) {			
				assertEquals("AAAGGAAAAGGAAAAGGGAAAAGGGA", list_kids.get(i).getStringPath());
			}		
		}
	}
	
	@Test 
	public void Garden1_Path99_Test() {
		int pos_x = 9;
		int pos_y = 9;
		for(int i=0; i<list_kids.size(); i++ ) {
			if(list_kids.get(i).getPosX() == pos_x && list_kids.get(i).getPosY() == pos_y) {			
				assertEquals("AAAGGAAAAGGAAAAGGGAAAAGGGA", list_kids.get(i).getStringPath());
			}		
		}
	}
	
	@Test 
	public void Garden1_Path85_Test() {
		int pos_x = 8;
		int pos_y = 5;
		for(int i=0; i<list_kids.size(); i++ ) {
			if(list_kids.get(i).getPosX() == pos_x && list_kids.get(i).getPosY() == pos_y) {			
				assertEquals("AADADAAGAGAADADAAGAGAADADAAGAG", list_kids.get(i).getStringPath());
			}		
		}
	}
	
}
