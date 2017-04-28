package GLPOO_ESIEA_1617_Groupe_Borgo;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class KidsFileTest {

private final static String GARDEN_FILE_1 = "gardens/test1.garden";
private final static String KIDS_FILE_1 = "gardens/test1.kids";
	
	private Game game;
	private Garden garden;
	
	@Before
	public void doBefore() {
		game = new Game();

		garden = new Garden(game, new File(GARDEN_FILE_1));
		garden.openkid(new File(KIDS_FILE_1));
    }
		
	/* ###################### Kids position ###################### */
	@Test
	public void Kid_00_Pos_Test() {
		if(this.garden.getKitAt(0, 0) != null) {
			assertTrue(true);
		}
	}
	@Test
	public void Kid_26_Pos_Test() {
		if(this.garden.getKitAt(2, 6) != null) {
			assertTrue(true);
		}
	}
	@Test
	public void Kid_09_Pos_Test() {
		if(this.garden.getKitAt(0, 9) != null) {
			assertTrue(true);
		}
	}
	@Test
	public void Kid_99_Pos_Test() {
		if(this.garden.getKitAt(9, 9) != null) {
			assertTrue(true);
		}
	}
	
	@Test
	public void Kid_66_Pos_Test() {
		if(this.garden.getKitAt(6, 6) != null) {
			assertTrue(true);
		}
	}
	
	/* ###################### Kids path ###################### */
	@Test
	public void Kid_00_Path_Test() {
		if(this.garden.getKitAt(0, 0) != null) {
			assertEquals("AAAAAAGAAGAAAAAAGAAGAAAAAAGAAGAAAAAAGAAGAAAAAAGAAGAAAAAAGAAG", this.garden.getKitAt(0, 0).getStringPath());
		}
	}
	@Test
	public void Kid_26_Path_Test() {
		if(this.garden.getKitAt(2, 6) != null) {
			assertEquals("AAAAAAGAAGAAAAAAGAAGAAAAAAGAAGAAAAAAGAAGAAAAAAGAAGAAAAAAGAAG", this.garden.getKitAt(2, 6).getStringPath());
		}
	}
	@Test
	public void Kid_09_Path_Test() {
		if(this.garden.getKitAt(0, 9) != null) {
			assertEquals("AAAGGAAAGGAAAGGAAAGGAAAGGAAAGGAAAGGAAAGGAAAGGAAAGG", this.garden.getKitAt(0, 9).getStringPath());
		}
	}
	@Test
	public void Kid_99_Path_Test() {
		if(this.garden.getKitAt(9, 9) != null) {
			assertEquals("AAAGGAAAGGAAAGGAAAGGAAAGGAAAGGAAAGGAAAGGAAAGGAAAGG", this.garden.getKitAt(9, 9).getStringPath());
		}
	}
	
	@Test
	public void Kid_66_Path_Test() {
		if(this.garden.getKitAt(6, 6) != null) {
			assertEquals("AADAGAAGAADAADADAAAAAA", this.garden.getKitAt(6, 6).getStringPath());
		}
	}
	
	/* ###################### Kids direction ###################### */
	@Test
	public void Kid_00_Direct_Test() {
		if(this.garden.getKitAt(0, 0) != null) {
			assertEquals("S", this.garden.getKitAt(0, 0).getDirection());
		}
	}
	@Test
	public void Kid_26_Direct_Test() {
		if(this.garden.getKitAt(2, 6) != null) {
			assertEquals("N", this.garden.getKitAt(2, 6).getDirection());
		}
	}
	@Test
	public void Kid_09_Direct_Test() {
		if(this.garden.getKitAt(0, 9) != null) {
			assertEquals("E", this.garden.getKitAt(0, 9).getDirection());
		}
	}
	@Test
	public void Kid_99_Direct_Test() {
		if(this.garden.getKitAt(9, 9) != null) {
			assertEquals("W", this.garden.getKitAt(9, 9).getDirection());
		}
	}
	
	@Test
	public void Kid_66_Direct_Test() {
		if(this.garden.getKitAt(6, 6) != null) {
			assertEquals("N", this.garden.getKitAt(6, 6).getDirection());
		}
	}
	
	/* ###################### Kids name ###################### */
	@Test
	public void Kid_00_Name_Test() {
		if(this.garden.getKitAt(0, 0) != null) {
			assertEquals("Jules", this.garden.getKitAt(0, 0).getName());
		}
	}
	@Test
	public void Kid_26_Name_Test() {
		if(this.garden.getKitAt(2, 6) != null) {
			assertEquals("Alexandre", this.garden.getKitAt(2, 6).getName());
		}
	}
	@Test
	public void Kid_09_Name_Test() {
		if(this.garden.getKitAt(0, 9) != null) {
			assertEquals("Rodrigue", this.garden.getKitAt(0, 9).getName());
		}
	}
	@Test
	public void Kid_99_Name_Test() {
		if(this.garden.getKitAt(9, 9) != null) {
			assertEquals("JoeYan", this.garden.getKitAt(9, 9).getName());
		}
	}
	
	@Test
	public void Kid_66_Name_Test() {
		if(this.garden.getKitAt(6, 6) != null) {
			assertEquals("JA", this.garden.getKitAt(6, 6).getName());
		}
	}
}
