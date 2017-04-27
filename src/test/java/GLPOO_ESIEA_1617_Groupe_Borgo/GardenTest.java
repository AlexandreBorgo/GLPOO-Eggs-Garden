package GLPOO_ESIEA_1617_Groupe_Borgo;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;

public class GardenTest {

	private static final Logger LOGGER = Logger.getLogger("Garden Test Logger");
	
	private final static String GARDEN_FILE_1 = "gardens/test1.txt";
	
	private Game game;
	
	@Before
	public void doBefore() {
		game = new Game();
    }
	
	@Test
	public void testGarden1() {
		File file = new File(GARDEN_FILE_1);
		Garden garden = new Garden(game, file);
		assertEquals(10, garden.getSizeX());
		assertEquals(10, garden.getSizeY());
	}
}
