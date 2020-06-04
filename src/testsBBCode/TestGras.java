package testsBBCode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import bbcode.Gras;
import donnees.Texte;

class TestGras
{	
	@Test
	void test()
	{
		String test = "ceci est un texte [b]en gras[\\b]";
		
		Gras g = new Gras();
		ArrayList<Texte> texte = g.parser(test);
		
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
		
		if(texte.get(0).isGras())
		{
			fail("Premier texte gras");
		}
		
		if(!texte.get(1).getValeur().equals("en gras"))
		{
			fail("Valeur du second Texte incorrect : ");
		}
		
		if(!texte.get(1).isGras())
		{
			fail("Second texte pas en gras");
		}
	}
	
	@Test
	void test2()
	{
		String test = "[b]ceci est un texte en gras[\\b]";
		
		Gras g = new Gras();
		ArrayList<Texte> texte = g.parser(test);
		
		if(texte == null)
		{
			fail("Paragraphe null");
		}
		
		if(texte.size() != 1)
		{
			fail("Nombre d'objet Texte incorrect");
		}
		
		if(!texte.get(0).getValeur().equals("ceci est un texte en gras"))
		{
			fail("Valeur du Texte incorrect");
		}
		
		if(!texte.get(0).isGras())
		{
			fail("Texte pas en gras");
		}
	}
	
	@Test
	void test3()
	{
		String test = "[b]ceci est[\\b] un texte [b]en gras[\\b]";
		
		Gras g = new Gras();
		ArrayList<Texte> texte = g.parser(test);
		
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
		
		if(!texte.get(0).isGras())
		{
			fail("Premier texte pas en gras");
		}
		
		if(!texte.get(1).getValeur().equals(" un texte "))
		{
			fail("Valeur du deuxième Texte incorrect : ");
		}
		
		if(texte.get(1).isGras())
		{
			fail("Deuxième texte en gras");
		}
		
		if(!texte.get(2).getValeur().equals("en gras"))
		{
			fail("Valeur du troisième Texte incorrect");
		}
		
		if(!texte.get(2).isGras())
		{
			fail("Troisième texte pas en gras");
		}
	}
	
	@Test
	void test4()
	{
		String test = "[b]ceci est[\\b][b] un texte en gras[\\b]";
		
		Gras g = new Gras();
		ArrayList<Texte> texte = g.parser(test);
		
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
		
		if(!texte.get(0).isGras())
		{
			fail("Premier texte pas en gras");
		}
		
		if(!texte.get(1).getValeur().equals(" un texte en gras"))
		{
			fail("Valeur du deuxième Texte incorrect");
		}
		
		if(!texte.get(1).isGras())
		{
			fail("Deuxième texte pas en gras");
		}
	}
	
	@Test
	void test5()
	{
		String test = "[b][\\b]ceci est un texte en gras";
		
		Gras g = new Gras();
		ArrayList<Texte> texte = g.parser(test);
		
		if(texte == null)
		{
			fail("Paragraphe null");
		}
		
		if(texte.size() != 1)
		{
			fail("Nombre d'objet Texte incorrect");
		}
		
		if(!texte.get(0).getValeur().equals("ceci est un texte en gras"))
		{
			fail("Valeur du Texte incorrect");
		}
		
		if(texte.get(0).isGras())
		{
			fail("texte en gras");
		}
	}
	
	@Test
	void test6()
	{
		String test = "[b]ceci est [\\b]un texte en gras";
		
		Gras g = new Gras();
		ArrayList<Texte> texte = g.parser(test);
		
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
		
		if(!texte.get(0).isGras())
		{
			fail("Premier texte pas en gras");
		}
		
		if(!texte.get(1).getValeur().equals("un texte en gras"))
		{
			fail("Valeur du deuxième Texte incorrect");
		}
		
		if(texte.get(1).isGras())
		{
			fail("Deuxième texte en gras");
		}
	}
	
	@Test
	void test7()
	{
		String test = "ceci est un texte en gras";
		
		Gras g = new Gras();
		ArrayList<Texte> texte = g.parser(test);
		
		if(texte == null)
		{
			fail("Paragraphe null");
		}
		
		if(texte.size() != 1)
		{
			fail("Nombre d'objet Texte incorrect");
		}
		
		if(!texte.get(0).getValeur().equals("ceci est un texte en gras"))
		{
			fail("Valeur du Texte incorrect");
		}
		
		if(texte.get(0).isGras())
		{
			fail("texte en gras");
		}
	}
}
