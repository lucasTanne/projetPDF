package testsBBCode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import bbcode.Italique;
import donnees.Texte;

class TestItalique
{
	/**
	 * Test avec un texte italique a la fin
	 */
	@Test
	void test()
	{
		String test = "ceci est un texte [i]italique[\\i]";
		
		Italique u = new Italique();
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
		
		if(!texte.get(1).getValeur().equals("italique"))
		{
			fail("Valeur du second Texte incorrect : ");
		}
		
		// Verifie la mise en forme des textes
		if(texte.get(0).isItalique())
		{
			fail("Premier texte italique");
		}
		
		if(!texte.get(1).isItalique())
		{
			fail("Second texte pas italique");
		}
	}
	
	/**
	 * Test avec un texte entierement italique
	 */
	@Test
	void test2()
	{
		String test = "[i]ceci est un texte italique[\\i]";
		
		Italique u = new Italique();
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
		if(!texte.get(0).getValeur().equals("ceci est un texte italique"))
		{
			fail("Valeur du Texte incorrect");
		}
		
		// verifie la mise en forme du texte
		if(!texte.get(0).isItalique())
		{
			fail("Texte pas italique");
		}
	}
	
	/**
	 * test avec un texte contenant deux morceaux italique et un morceau normal
	 */
	@Test
	void test3()
	{
		String test = "[i]ceci est[\\i] un texte [i]italique[\\i]";
		
		Italique u = new Italique();
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
		
		if(!texte.get(2).getValeur().equals("italique"))
		{
			fail("Valeur du troisième Texte incorrect");
		}
		
		// Verifie la mise en forme des textes
		if(!texte.get(0).isItalique())
		{
			fail("Premier texte pas italique");
		}
		
		if(texte.get(1).isItalique())
		{
			fail("Deuxième texte italique");
		}
		
		if(!texte.get(2).isItalique())
		{
			fail("Troisième texte pas italique");
		}
	}
	
	/**
	 * Test avec un texte italique en deux fois
	 */
	@Test
	void test4()
	{
		String test = "[i]ceci est[\\i][i] un texte italique[\\i]";
		
		Italique u = new Italique();
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

		if(!texte.get(1).getValeur().equals(" un texte italique"))
		{
			fail("Valeur du deuxième Texte incorrect");
		}
		
		// Verifie la mise en forme des textes
		if(!texte.get(0).isItalique())
		{
			fail("Premier texte pas italique");
		}
		
		if(!texte.get(1).isItalique())
		{
			fail("Deuxième texte pas italique");
		}
	}
	
	/**
	 * Test avec un texte normal, mais avec deux balises vides au debut
	 */
	@Test
	void test5()
	{
		String test = "[i][\\i]ceci est un texte italique";
		
		Italique u = new Italique();
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
		if(!texte.get(0).getValeur().equals("ceci est un texte italique"))
		{
			fail("Valeur du Texte incorrect");
		}
		
		// Test de la mise en forme du texte
		if(texte.get(0).isItalique())
		{
			fail("texte italique");
		}
	}
	
	/**
	 * Test avec un texte italique au debut
	 */
	@Test
	void test6()
	{
		String test = "[i]ceci est [\\i]un texte italique";
		
		Italique u = new Italique();
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

		if(!texte.get(1).getValeur().equals("un texte italique"))
		{
			fail("Valeur du deuxième Texte incorrect");
		}
		
		// Test de la mise en forme des textes
		if(!texte.get(0).isItalique())
		{
			fail("Premier texte pas italique");
		}
		
		if(texte.get(1).isItalique())
		{
			fail("Deuxième texte italique");
		}
	}
	
	/**
	 * Test avec un texte non mis en forme
	 */
	@Test
	void test7()
	{
		String test = "ceci est un texte italique";
		
		Italique u = new Italique();
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
		if(!texte.get(0).getValeur().equals("ceci est un texte italique"))
		{
			fail("Valeur du Texte incorrect");
		}
		
		
		// Test de la mise en forme du texte
		if(texte.get(0).isItalique())
		{
			fail("texte italique");
		}
	}
}
