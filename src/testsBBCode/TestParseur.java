/**
 * 
 */
package testsBBCode;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import bbcode.Barre;
import bbcode.Gras;
import bbcode.Italique;
import bbcode.Parseur;
import bbcode.Souligne;

/**
 * @author stobr
 *
 */
class TestParseur {
	
	private static Parseur parseur;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception
	{
		parseur = new Parseur(new Gras(), new Italique(), new Souligne(), new Barre());
	}

	/**
	 * Test method for {@link bbcode.Parseur#parser(java.lang.String)}.
	 */
	@Test
	void testParser() {
		fail("Not yet implemented");
	}

}
