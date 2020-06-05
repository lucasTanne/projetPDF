/**
 * 
 */
package testsBBCode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import bbcode.Barre;
import bbcode.Gras;
import bbcode.Italique;
import bbcode.Parseur;
import bbcode.Souligne;
import donnees.Texte;

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
	void testParser()
	{
		String test = "";
		
		if(parseur.parser(test) != null)
		{
			fail("Paragraphe non null");
		}
	}
	
	@Test
	void testParser2()
	{
		String test = "[b]gras[\\b] normal [i][u]italique[\\u][\\i]";
		
		ArrayList<Texte> paragraphe = parseur.parser(test);
		
		// Test paragraphe null
		if(paragraphe == null)
		{
			fail("Paragraphe null");
		}
		
		// Test de la taille du paragraphe
		if(paragraphe.size() != 3)
		{
			fail("Nombre d'objet Texte incorrect");
		}
		
		// Test de la valeur des textes
		if(!paragraphe.get(0).getValeur().equals("gras"))
		{
			fail("Valeur du premier texte incorrect");
		}
		
		if(!paragraphe.get(1).getValeur().equals(" normal "))
		{
			fail("Valeur du deuxième texte incorrect");
		}
		
		if(!paragraphe.get(2).getValeur().equals("italique"))
		{
			fail("Valeur du troisième texte incorrect");
		}
		
		// Test du formatage du premier texte
		if(!paragraphe.get(0).isGras())
		{
			fail("Premier texte pas en gras");
		}
		
		if(paragraphe.get(0).isBarre())
		{
			fail("Premier texte barré");
		}
		
		if(paragraphe.get(0).isSouligne())
		{
			fail("Premier texte souligné");
		}
		
		if(paragraphe.get(0).isItalique())
		{
			fail("Premier texte en italique");
		}
		
		// Test du formatage du deuxieme texte
		if(paragraphe.get(1).isGras())
		{
			fail("Deuxième texte en gras");
		}
		
		if(paragraphe.get(1).isBarre())
		{
			fail("Deuxième texte barré");
		}
		
		if(paragraphe.get(1).isSouligne())
		{
			fail("Deuxième texte souligné");
		}
		
		if(paragraphe.get(1).isItalique())
		{
			fail("Deuxième texte en italique");
		}
		
		// Test du formatage du troisieme texte
		if(paragraphe.get(2).isGras())
		{
			fail("Troisième texte pas en gras");
		}
		
		if(paragraphe.get(2).isBarre())
		{
			fail("Troisième texte barré");
		}
		
		if(!paragraphe.get(2).isSouligne())
		{
			fail("Troisième texte pas souligné");
		}
		
		if(!paragraphe.get(2).isItalique())
		{
			fail("Troisième texte pas italique");
		}
	}
}