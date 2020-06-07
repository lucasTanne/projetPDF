package testsBBCode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import bbcode.Barre;
import donnees.Texte;

class TestBarre
{
	/**
	 * Test avec un texte barre a la fin
	 */
	@Test
	void test()
	{
		String test = "ceci est un texte [s]barré[\\s]";
		
		Barre u = new Barre();
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
		
		if(!texte.get(1).getValeur().equals("barré"))
		{
			fail("Valeur du second Texte incorrect : ");
		}
		
		// Verifie la mise en forme des textes
		if(texte.get(0).isBarre())
		{
			fail("Premier texte barré");
		}
		
		if(!texte.get(1).isBarre())
		{
			fail("Second texte pas barré");
		}
	}
	
	/**
	 * Test avec un texte entierement barre
	 */
	@Test
	void test2()
	{
		String test = "[s]ceci est un texte barré[\\s]";
		
		Barre u = new Barre();
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
		if(!texte.get(0).getValeur().equals("ceci est un texte barré"))
		{
			fail("Valeur du Texte incorrect");
		}
		
		// verifie la mise en forme du texte
		if(!texte.get(0).isBarre())
		{
			fail("Texte pas barré");
		}
	}
	
	/**
	 * test avec un texte contenant deux morceaux barre et un morceau normal
	 */
	@Test
	void test3()
	{
		String test = "[s]ceci est[\\s] un texte [s]barré[\\s]";
		
		Barre u = new Barre();
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
		
		if(!texte.get(2).getValeur().equals("barré"))
		{
			fail("Valeur du troisième Texte incorrect");
		}
		
		// Verifie la mise en forme des textes
		if(!texte.get(0).isBarre())
		{
			fail("Premier texte pas barré");
		}
		
		if(texte.get(1).isBarre())
		{
			fail("Deuxième texte barré");
		}
		
		if(!texte.get(2).isBarre())
		{
			fail("Troisième texte pas barré");
		}
	}
	
	/**
	 * Test avec un texte barre en deux fois
	 */
	@Test
	void test4()
	{
		String test = "[s]ceci est[\\s][s] un texte barré[\\s]";
		
		Barre u = new Barre();
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

		if(!texte.get(1).getValeur().equals(" un texte barré"))
		{
			fail("Valeur du deuxième Texte incorrect");
		}
		
		// Verifie la mise en forme des textes
		if(!texte.get(0).isBarre())
		{
			fail("Premier texte pas barré");
		}
		
		if(!texte.get(1).isBarre())
		{
			fail("Deuxième texte pas barré");
		}
	}
	
	/**
	 * Test avec un texte normal, mais avec deux balises vides au debut
	 */
	@Test
	void test5()
	{
		String test = "[s][\\s]ceci est un texte barré";
		
		Barre u = new Barre();
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
		if(!texte.get(0).getValeur().equals("ceci est un texte barré"))
		{
			fail("Valeur du Texte incorrect");
		}
		
		// Test de la mise en forme du texte
		if(texte.get(0).isBarre())
		{
			fail("texte barré");
		}
	}
	
	/**
	 * Test avec un texte barre au debut
	 */
	@Test
	void test6()
	{
		String test = "[s]ceci est [\\s]un texte barré";
		
		Barre u = new Barre();
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

		if(!texte.get(1).getValeur().equals("un texte barré"))
		{
			fail("Valeur du deuxième Texte incorrect");
		}
		
		// Test de la mise en forme des textes
		if(!texte.get(0).isBarre())
		{
			fail("Premier texte pas barré");
		}
		
		if(texte.get(1).isBarre())
		{
			fail("Deuxième texte barré");
		}
	}
	
	/**
	 * Test avec un texte non mis en forme
	 */
	@Test
	void test7()
	{
		String test = "ceci est un texte barré";
		
		Barre u = new Barre();
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
		if(!texte.get(0).getValeur().equals("ceci est un texte barré"))
		{
			fail("Valeur du Texte incorrect");
		}
		
		
		// Test de la mise en forme du texte
		if(texte.get(0).isBarre())
		{
			fail("texte barré");
		}
	}
}
