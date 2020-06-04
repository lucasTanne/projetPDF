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
	 * 
	 */
	@Test
	void test()
	{
		String test = "ceci est un texte [u]souligné[\\u]";
		
		Souligne u = new Souligne();
		ArrayList<Texte> texte = u.parser(test);
		
		if(texte == null)
		{
			fail("Paragraphe null");
		}
		
		if(texte.size() != 2)
		{
			fail("Nombre d'objet Texte incorrect");
		}
		
		if(!texte.get(0).getValeur().equals("ceci est un texte "))
		{
			fail("Valeur du premier Texte incorrect");
		}
		
		if(texte.get(0).isSouligne())
		{
			fail("Premier texte souligné");
		}
		
		if(!texte.get(1).getValeur().equals("souligné"))
		{
			fail("Valeur du second Texte incorrect : ");
		}
		
		if(!texte.get(1).isSouligne())
		{
			fail("Second texte pas souligné");
		}
	}
	
	/**
	 * 
	 */
	@Test
	void test2()
	{
		String test = "[u]ceci est un texte souligné[\\u]";
		
		Souligne u = new Souligne();
		ArrayList<Texte> texte = u.parser(test);
		
		if(texte == null)
		{
			fail("Paragraphe null");
		}
		
		if(texte.size() != 1)
		{
			fail("Nombre d'objet Texte incorrect");
		}
		
		if(!texte.get(0).getValeur().equals("ceci est un texte souligné"))
		{
			fail("Valeur du Texte incorrect");
		}
		
		if(!texte.get(0).isSouligne())
		{
			fail("Texte pas souligné");
		}
	}
	
	/**
	 * 
	 */
	@Test
	void test3()
	{
		String test = "[u]ceci est[\\u] un texte [u]souligné[\\u]";
		
		Souligne u = new Souligne();
		ArrayList<Texte> texte = u.parser(test);
		
		if(texte == null)
		{
			fail("Paragraphe null");
		}
		
		if(texte.size() != 3)
		{
			fail("Nombre d'objet Texte incorrect");
		}
		
		if(!texte.get(0).getValeur().equals("ceci est"))
		{
			fail("Valeur du premier Texte incorrect");
		}
		
		if(!texte.get(0).isSouligne())
		{
			fail("Premier texte pas souligné");
		}
		
		if(!texte.get(1).getValeur().equals(" un texte "))
		{
			fail("Valeur du deuxième Texte incorrect : ");
		}
		
		if(texte.get(1).isSouligne())
		{
			fail("Deuxième texte souligné");
		}
		
		if(!texte.get(2).getValeur().equals("souligné"))
		{
			fail("Valeur du troisième Texte incorrect");
		}
		
		if(!texte.get(2).isSouligne())
		{
			fail("Troisième texte pas souligné");
		}
	}
	
	/**
	 * 
	 */
	@Test
	void test4()
	{
		String test = "[u]ceci est[\\u][u] un texte souligné[\\u]";
		
		Souligne u = new Souligne();
		ArrayList<Texte> texte = u.parser(test);
		
		if(texte == null)
		{
			fail("Paragraphe null");
		}
		
		if(texte.size() != 2)
		{
			fail("Nombre d'objet Texte incorrect");
		}
		
		if(!texte.get(0).getValeur().equals("ceci est"))
		{
			fail("Valeur du premier Texte incorrect");
		}
		
		if(!texte.get(0).isSouligne())
		{
			fail("Premier texte pas souligné");
		}
		
		if(!texte.get(1).getValeur().equals(" un texte souligné"))
		{
			fail("Valeur du deuxième Texte incorrect");
		}
		
		if(!texte.get(1).isSouligne())
		{
			fail("Deuxième texte pas souligné");
		}
	}
	
	/**
	 * 
	 */
	@Test
	void test5()
	{
		String test = "[u][\\u]ceci est un texte souligné";
		
		Souligne u = new Souligne();
		ArrayList<Texte> texte = u.parser(test);
		
		if(texte == null)
		{
			fail("Paragraphe null");
		}
		
		if(texte.size() != 1)
		{
			fail("Nombre d'objet Texte incorrect");
		}
		
		if(!texte.get(0).getValeur().equals("ceci est un texte souligné"))
		{
			fail("Valeur du Texte incorrect");
		}
		
		if(texte.get(0).isSouligne())
		{
			fail("texte souligné");
		}
	}
	
	/**
	 * 
	 */
	@Test
	void test6()
	{
		String test = "[u]ceci est [\\u]un texte souligné";
		
		Souligne u = new Souligne();
		ArrayList<Texte> texte = u.parser(test);
		
		if(texte == null)
		{
			fail("Paragraphe null");
		}
		
		if(texte.size() != 2)
		{
			fail("Nombre d'objet Texte incorrect");
		}
		
		if(!texte.get(0).getValeur().equals("ceci est "))
		{
			fail("Valeur du premier Texte incorrect");
		}
		
		if(!texte.get(0).isSouligne())
		{
			fail("Premier texte pas souligné");
		}
		
		if(!texte.get(1).getValeur().equals("un texte souligné"))
		{
			fail("Valeur du deuxième Texte incorrect");
		}
		
		if(texte.get(1).isSouligne())
		{
			fail("Deuxième texte souligné");
		}
	}
	
	/**
	 * 
	 */
	@Test
	void test7()
	{
		String test = "ceci est un texte souligné";
		
		Souligne u = new Souligne();
		ArrayList<Texte> texte = u.parser(test);
		
		if(texte == null)
		{
			fail("Paragraphe null");
		}
		
		if(texte.size() != 1)
		{
			fail("Nombre d'objet Texte incorrect");
		}
		
		if(!texte.get(0).getValeur().equals("ceci est un texte souligné"))
		{
			fail("Valeur du Texte incorrect");
		}
		
		if(texte.get(0).isSouligne())
		{
			fail("texte souligné");
		}
	}
}
