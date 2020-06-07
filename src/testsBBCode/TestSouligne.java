package testsBBCode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import bbcode.Souligne;
import donnees.Texte;

/**
 * @author stobr
 *
 */
class TestSouligne
{	
	/**
	 * Test avec un texte souligne a la fin
	 */
	@Test
	void test()
	{
		String test = "ceci est un texte [u]souligné[\\u]";
		
		Souligne u = new Souligne();
		ArrayList<Texte> texte = u.parser(test);
		
		// Verifie si le texte est null
		if(texte == null)
		{
			fail("Paragraphe null");
		}
		
		// Verifie la taille de la liste
		if(texte.size() != 2)
		{
			fail("Nombre d'objet Texte incorrect");
		}
		
		// Verifie la valeur des textes
		if(!texte.get(0).getValeur().equals("ceci est un texte "))
		{
			fail("Valeur du premier Texte incorrect");
		}
		
		if(!texte.get(1).getValeur().equals("souligné"))
		{
			fail("Valeur du second Texte incorrect : ");
		}
		
		// Verifie la mise en forme des textes
		if(texte.get(0).isSouligne())
		{
			fail("Premier texte souligné");
		}
		
		if(!texte.get(1).isSouligne())
		{
			fail("Second texte pas souligné");
		}
	}
	
	/**
	 * Test avec un texte entierement souligne
	 */
	@Test
	void test2()
	{
		String test = "[u]ceci est un texte souligné[\\u]";
		
		Souligne u = new Souligne();
		ArrayList<Texte> texte = u.parser(test);
		
		// Verifie si le texte est null
		if(texte == null)
		{
			fail("Paragraphe null");
		}
		
		// Verifie la taille de la liste
		if(texte.size() != 1)
		{
			fail("Nombre d'objet Texte incorrect");
		}
		
		// Verifie la valeur du texte
		if(!texte.get(0).getValeur().equals("ceci est un texte souligné"))
		{
			fail("Valeur du Texte incorrect");
		}
		
		// verifie la mise en forme du texte
		if(!texte.get(0).isSouligne())
		{
			fail("Texte pas souligné");
		}
	}
	
	/**
	 * test avec un texte contenant deux morceaux souligne et un morceau normal
	 */
	@Test
	void test3()
	{
		String test = "[u]ceci est[\\u] un texte [u]souligné[\\u]";
		
		Souligne u = new Souligne();
		ArrayList<Texte> texte = u.parser(test);
		
		// Verifie si le texte est null
		if(texte == null)
		{
			fail("Paragraphe null");
		}
		
		// Verifie la taille de la liste
		if(texte.size() != 3)
		{
			fail("Nombre d'objet Texte incorrect");
		}
		
		// Verifie la valeur des textes
		if(!texte.get(0).getValeur().equals("ceci est"))
		{
			fail("Valeur du premier Texte incorrect");
		}
		
		if(!texte.get(1).getValeur().equals(" un texte "))
		{
			fail("Valeur du deuxième Texte incorrect : ");
		}
		
		if(!texte.get(2).getValeur().equals("souligné"))
		{
			fail("Valeur du troisième Texte incorrect");
		}
		
		// Verifie la mise en forme des textes
		if(!texte.get(0).isSouligne())
		{
			fail("Premier texte pas souligné");
		}
		
		if(texte.get(1).isSouligne())
		{
			fail("Deuxième texte souligné");
		}
		
		if(!texte.get(2).isSouligne())
		{
			fail("Troisième texte pas souligné");
		}
	}
	
	/**
	 * Test avec un texte souligne en deux fois
	 */
	@Test
	void test4()
	{
		String test = "[u]ceci est[\\u][u] un texte souligné[\\u]";
		
		Souligne u = new Souligne();
		ArrayList<Texte> texte = u.parser(test);
		
		// Verifie si le texte est null
		if(texte == null)
		{
			fail("Paragraphe null");
		}
		
		// Verifie la taille de la liste
		if(texte.size() != 2)
		{
			fail("Nombre d'objet Texte incorrect");
		}
		
		// Test de la valeur des textes
		if(!texte.get(0).getValeur().equals("ceci est"))
		{
			fail("Valeur du premier Texte incorrect");
		}

		if(!texte.get(1).getValeur().equals(" un texte souligné"))
		{
			fail("Valeur du deuxième Texte incorrect");
		}
		
		// Verifie la mise en forme des textes
		if(!texte.get(0).isSouligne())
		{
			fail("Premier texte pas souligné");
		}
		
		if(!texte.get(1).isSouligne())
		{
			fail("Deuxième texte pas souligné");
		}
	}
	
	/**
	 * Test avec un texte normal, mais avec deux balises vides au debut
	 */
	@Test
	void test5()
	{
		String test = "[u][\\u]ceci est un texte souligné";
		
		Souligne u = new Souligne();
		ArrayList<Texte> texte = u.parser(test);
		
		// Verifie si le texte est null
		if(texte == null)
		{
			fail("Paragraphe null");
		}
		
		// Verifie la taille de la liste
		if(texte.size() != 1)
		{
			fail("Nombre d'objet Texte incorrect");
		}
		
		// Test de la valeur du texte
		if(!texte.get(0).getValeur().equals("ceci est un texte souligné"))
		{
			fail("Valeur du Texte incorrect");
		}
		
		// Test de la mise en forme du texte
		if(texte.get(0).isSouligne())
		{
			fail("texte souligné");
		}
	}
	
	/**
	 * Test avec un texte souligne au debut
	 */
	@Test
	void test6()
	{
		String test = "[u]ceci est [\\u]un texte souligné";
		
		Souligne u = new Souligne();
		ArrayList<Texte> texte = u.parser(test);
		
		// Verifie si le texte est null
		if(texte == null)
		{
			fail("Paragraphe null");
		}
		
		// Verifie la taille de la liste
		if(texte.size() != 2)
		{
			fail("Nombre d'objet Texte incorrect");
		}
		
		// Test de la valeur des textes
		if(!texte.get(0).getValeur().equals("ceci est "))
		{
			fail("Valeur du premier Texte incorrect");
		}

		if(!texte.get(1).getValeur().equals("un texte souligné"))
		{
			fail("Valeur du deuxième Texte incorrect");
		}
		
		// Test de la mise en forme des textes
		if(!texte.get(0).isSouligne())
		{
			fail("Premier texte pas souligné");
		}
		
		if(texte.get(1).isSouligne())
		{
			fail("Deuxième texte souligné");
		}
	}
	
	/**
	 * Test avec un texte non mis en forme
	 */
	@Test
	void test7()
	{
		String test = "ceci est un texte souligné";
		
		Souligne u = new Souligne();
		ArrayList<Texte> texte = u.parser(test);
		
		// Verifie si le texte est null
		if(texte == null)
		{
			fail("Paragraphe null");
		}
		
		// Verifie la taille de la liste
		if(texte.size() != 1)
		{
			fail("Nombre d'objet Texte incorrect");
		}
		
		// Test de la valeur du texte
		if(!texte.get(0).getValeur().equals("ceci est un texte souligné"))
		{
			fail("Valeur du Texte incorrect");
		}
		
		
		// Test de la mise en forme du texte
		if(texte.get(0).isSouligne())
		{
			fail("texte souligné");
		}
	}
}
