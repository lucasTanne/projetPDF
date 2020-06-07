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
	 * instancie le parseur aavnt d'effectuer les tests
	 */
	@BeforeAll
	static void setUpBeforeClass()
	{
		parseur = new Parseur(new Gras(), new Italique(), new Souligne(), new Barre());
	}

	/**
	 * Test le parse avec un texte vide
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
	
	/**
	 * Test avec un texte contenant chaque mise en forme
	 */
	@Test
	void testParser2()
	{
		String test = "[b]gras [\\b][i]italique [\\i][u]souligné [\\u][s]barré [\\s]normal";
		
		ArrayList<Texte> paragraphe = parseur.parser(test);
		
		// Test paragraphe null
		if(paragraphe == null)
		{
			fail("Paragraphe null");
		}
		
		// Test de la taille du paragraphe
		if(paragraphe.size() != 5)
		{
			System.out.println(paragraphe.size());
			fail("Nombre d'objet Texte incorrect");
		}
		
		// Test de la valeur des textes
		if(!paragraphe.get(0).getValeur().equals("gras "))
		{
			fail("Valeur du premier texte incorrect");
		}
		
		if(!paragraphe.get(1).getValeur().equals("italique "))
		{
			fail("Valeur du deuxième texte incorrect");
		}
		
		if(!paragraphe.get(2).getValeur().equals("souligné "))
		{
			fail("Valeur du troisième texte incorrect");
		}
		
		if(!paragraphe.get(3).getValeur().equals("barré "))
		{
			fail("Valeur du quatrième texte incorrect");
		}
		
		if(!paragraphe.get(4).getValeur().equals("normal"))
		{
			fail("Valeur du cinquième texte incorrect");
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
		
		if(!paragraphe.get(1).isItalique())
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
		
		if(paragraphe.get(2).isItalique())
		{
			fail("Troisième texte pas italique");
		}
		
		// Test du formatage du quatrieme texte
		if(paragraphe.get(3).isGras())
		{
			fail("Quatrième texte pas en gras");
		}
		
		if(!paragraphe.get(3).isBarre())
		{
			fail("Quatrième texte barré");
		}
		
		if(paragraphe.get(3).isSouligne())
		{
			fail("Quatrième texte pas souligné");
		}
		
		if(paragraphe.get(3).isItalique())
		{
			fail("Quatrième texte pas italique");
		}
		
		// Test du formatage du conquieme texte
		if(paragraphe.get(4).isGras())
		{
			fail("Quatrième texte pas en gras");
		}
		
		if(paragraphe.get(4).isBarre())
		{
			fail("Quatrième texte barré");
		}
		
		if(paragraphe.get(4).isSouligne())
		{
			fail("Quatrième texte pas souligné");
		}
		
		if(paragraphe.get(4).isItalique())
		{
			fail("Quatrième texte pas italique");
		}
	}
	
	/**
	 * Test d'un texte avec un mot contenant deux mises en formes a la fin du texte
	 */
	@Test
	void testParser3()
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
	
	/**
	 * Test d'un texte avec un mot contenant deux mises en formes au debut du texte
	 */
	@Test
	void testParser4()
	{
		String test = "[i][b]ceci [\\b][\\i]est un [s]texte [\\s][u]formatté[\\u]";
		
		ArrayList<Texte> paragraphe = parseur.parser(test);
		
		// Test paragraphe null
		if(paragraphe == null)
		{
			fail("Paragraphe null");
		}
		
		// Test de la taille du paragraphe
		if(paragraphe.size() != 4)
		{
			fail("Nombre d'objet Texte incorrect");
		}
		
		// Test de la valeur des textes
		if(!paragraphe.get(0).getValeur().equals("ceci "))
		{
			fail("Valeur du premier texte incorrect");
		}
		
		if(!paragraphe.get(1).getValeur().equals("est un "))
		{
			fail("Valeur du deuxième texte incorrect");
		}
		
		if(!paragraphe.get(2).getValeur().equals("texte "))
		{
			fail("Valeur du troisième texte incorrect");
		}
		
		if(!paragraphe.get(3).getValeur().equals("formatté"))
		{
			fail("Valeur du quatrième texte incorrect");
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
		
		if(!paragraphe.get(0).isItalique())
		{
			fail("Premier texte pas en italique");
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
			fail("Troisième texte en gras");
		}
		
		if(!paragraphe.get(2).isBarre())
		{
			fail("Troisième texte barré");
		}
		
		if(paragraphe.get(2).isSouligne())
		{
			fail("Troisième texte pas souligné");
		}
		
		if(paragraphe.get(2).isItalique())
		{
			fail("Troisième texte italique");
		}
		
		// Test du formatage du quatrieme texte
		if(paragraphe.get(3).isGras())
		{
			fail("Quatrième texte en gras");
		}
		
		if(paragraphe.get(3).isBarre())
		{
			fail("Quatrième texte pas barré");
		}
		
		if(!paragraphe.get(3).isSouligne())
		{
			fail("Quatrième texte pas souligné");
		}
		
		if(paragraphe.get(3).isItalique())
		{
			fail("Quatrième texte italique");
		}
	}
	
	/**
	 * Test avec un texte sans mise en forme
	 */
	@Test
	void testParser5()
	{
		String test = "ceci est un texte normal";
		
		ArrayList<Texte> paragraphe = parseur.parser(test);

		// Test paragraphe null
		if(paragraphe == null)
		{
			fail("Paragraphe null");
		}
		
		// Test de la taille du paragraphe
		if(paragraphe.size() != 1)
		{
			fail("Nombre d'objet Texte incorrect");
		}
		
		// Test de la valeur des textes
		if(!paragraphe.get(0).getValeur().equals("ceci est un texte normal"))
		{
			fail("Valeur du texte incorrect");
		}
		
		// Test du formatage du quatrieme texte
		if(paragraphe.get(0).isGras())
		{
			fail("Texte en gras");
		}
		
		if(paragraphe.get(0).isBarre())
		{
			fail("Texte barré");
		}
		
		if(paragraphe.get(0).isSouligne())
		{
			fail("Texte souligné");
		}
		
		if(paragraphe.get(0).isItalique())
		{
			fail("Texte italique");
		}
	}
}