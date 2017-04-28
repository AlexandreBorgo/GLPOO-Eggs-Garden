package GLPOO_ESIEA_1617_Groupe_Borgo;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class GardenFileTest {
	
	private final static String GARDEN_FILE_1 = "gardens/test1.garden";
	
	private Game game;
	private Garden garden;
	
	@Before
	public void doBefore() {
		game = new Game();

		File file = new File(GARDEN_FILE_1);
		garden = new Garden(game, file);
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
	public void Garden1_Case11_Test() {
		assertEquals(Item.ROCK, garden.getCase(1,1));
	}
	
	@Test
	public void Garden1_Case12_Test() {
		assertEquals(Item.ROCK, garden.getCase(1,2));
	}
	
	@Test
	public void Garden1_Case13_Test() {
		assertEquals(Item.ROCK, garden.getCase(1,3));
	}
	
	@Test
	public void Garden1_Case14_Test() {
		assertEquals(Item.ROCK, garden.getCase(1,4));
	}
	@Test
	public void Garden1_Case29_Test() {
		assertEquals(Item.EGG, garden.getCase(2,9));
	}
	
	@Test
	public void Garden1_Case50_Test() {
		assertEquals(Item.EGG, garden.getCase(5,0));
	}
	
	@Test
	public void Garden1_Case51_Test() {
		assertEquals(Item.EGG, garden.getCase(5,1));
	}
	
	@Test
	public void Garden1_Case52_Test() {	
		assertEquals(Item.EGG, garden.getCase(5,2));
	}
	
	@Test
	public void Garden1_Case60_Test() {
		assertEquals(Item.EGG, garden.getCase(6,0));
	}	
}
