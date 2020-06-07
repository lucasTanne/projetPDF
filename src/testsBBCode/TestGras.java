package testsBBCode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import bbcode.Gras;
import donnees.Texte;

class TestGras
{	
	/**
	 * Test avec un texte gras a la fin
	 */
	@Test
	void test()
	{
		String test = "ceci est un texte [b]gras[\\b]";
		
		Gras u = new Gras();
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
		
		if(!texte.get(1).getValeur().equals("gras"))
		{
			fail("Valeur du second Texte incorrect : ");
		}
		
		// Verifie la mise en forme des textes
		if(texte.get(0).isGras())
		{
			fail("Premier texte gras");
		}
		
		if(!texte.get(1).isGras())
		{
			fail("Second texte pas gras");
		}
	}
	
	/**
	 * Test avec un texte entierement gras
	 */
	@Test
	void test2()
	{
		String test = "[b]ceci est un texte gras[\\b]";
		
		Gras u = new Gras();
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
		if(!texte.get(0).getValeur().equals("ceci est un texte gras"))
		{
			fail("Valeur du Texte incorrect");
		}
		
		// verifie la mise en forme du texte
		if(!texte.get(0).isGras())
		{
			fail("Texte pas gras");
		}
	}
	
	/**
	 * test avec un texte contenant deux morceaux gras et un morceau normal
	 */
	@Test
	void test3()
	{
		String test = "[b]ceci est[\\b] un texte [b]gras[\\b]";
		
		Gras u = new Gras();
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
		
		if(!texte.get(2).getValeur().equals("gras"))
		{
			fail("Valeur du troisième Texte incorrect");
		}
		
		// Verifie la mise en forme des textes
		if(!texte.get(0).isGras())
		{
			fail("Premier texte pas gras");
		}
		
		if(texte.get(1).isGras())
		{
			fail("Deuxième texte gras");
		}
		
		if(!texte.get(2).isGras())
		{
			fail("Troisième texte pas gras");
		}
	}
	
	/**
	 * Test avec un texte gras en deux fois
	 */
	@Test
	void test4()
	{
		String test = "[b]ceci est[\\b][b] un texte gras[\\b]";
		
		Gras u = new Gras();
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

		if(!texte.get(1).getValeur().equals(" un texte gras"))
		{
			fail("Valeur du deuxième Texte incorrect");
		}
		
		// Verifie la mise en forme des textes
		if(!texte.get(0).isGras())
		{
			fail("Premier texte pas gras");
		}
		
		if(!texte.get(1).isGras())
		{
			fail("Deuxième texte pas gras");
		}
	}
	
	/**
	 * Test avec un texte normal, mais avec deux balises vides au debut
	 */
	@Test
	void test5()
	{
		String test = "[b][\\b]ceci est un texte gras";
		
		Gras u = new Gras();
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
		if(!texte.get(0).getValeur().equals("ceci est un texte gras"))
		{
			fail("Valeur du Texte incorrect");
		}
		
		// Test de la mise en forme du texte
		if(texte.get(0).isGras())
		{
			fail("texte gras");
		}
	}
	
	/**
	 * Test avec un texte gras au debut
	 */
	@Test
	void test6()
	{
		String test = "[b]ceci est [\\b]un texte gras";
		
		Gras u = new Gras();
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

		if(!texte.get(1).getValeur().equals("un texte gras"))
		{
			fail("Valeur du deuxième Texte incorrect");
		}
		
		// Test de la mise en forme des textes
		if(!texte.get(0).isGras())
		{
			fail("Premier texte pas gras");
		}
		
		if(texte.get(1).isGras())
		{
			fail("Deuxième texte gras");
		}
	}
	
	/**
	 * Test avec un texte non mis en forme
	 */
	@Test
	void test7()
	{
		String test = "ceci est un texte gras";
		
		Gras u = new Gras();
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
		if(!texte.get(0).getValeur().equals("ceci est un texte gras"))
		{
			fail("Valeur du Texte incorrect");
		}
		
		
		// Test de la mise en forme du texte
		if(texte.get(0).isGras())
		{
			fail("texte gras");
		}
	}

}
