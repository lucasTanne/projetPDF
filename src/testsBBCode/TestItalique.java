package testsBBCode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import bbcode.Italique;
import donnees.Texte;

class TestItalique
{
	@Test
	void test()
	{
		String test = "ceci est un texte [i]en italique[\\i]";
		
		Italique i = new Italique();
		ArrayList<Texte> texte = i.parser(test);
		
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
		
		if(texte.get(0).isItalique())
		{
			fail("Premier texte italique");
		}
		
		if(!texte.get(1).getValeur().equals("en italique"))
		{
			fail("Valeur du second Texte incorrect : ");
		}
		
		if(!texte.get(1).isItalique())
		{
			fail("Second texte pas en italique");
		}
	}
	
	@Test
	void test2()
	{
		String test = "[i]ceci est un texte en italique[\\i]";
		
		Italique i = new Italique();
		ArrayList<Texte> texte = i.parser(test);
		
		if(texte == null)
		{
			fail("Paragraphe null");
		}
		
		if(texte.size() != 1)
		{
			fail("Nombre d'objet Texte incorrect");
		}
		
		if(!texte.get(0).getValeur().equals("ceci est un texte en italique"))
		{
			fail("Valeur du Texte incorrect");
		}
		
		if(!texte.get(0).isItalique())
		{
			fail("Texte pas en italique");
		}
	}
	
	@Test
	void test3()
	{
		String test = "[i]ceci est[\\i] un texte [i]en italique[\\i]";
		
		Italique i = new Italique();
		ArrayList<Texte> texte = i.parser(test);
		
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
		
		if(!texte.get(0).isItalique())
		{
			fail("Premier texte pas en italique");
		}
		
		if(!texte.get(1).getValeur().equals(" un texte "))
		{
			fail("Valeur du deuxième Texte incorrect : ");
		}
		
		if(texte.get(1).isItalique())
		{
			fail("Deuxième texte en italique");
		}
		
		if(!texte.get(2).getValeur().equals("en italique"))
		{
			fail("Valeur du troisième Texte incorrect");
		}
		
		if(!texte.get(2).isItalique())
		{
			fail("Troisième texte pas en italique");
		}
	}
	
	@Test
	void test4()
	{
		String test = "[i]ceci est[\\i][i] un texte en italique[\\i]";
		
		Italique i = new Italique();
		ArrayList<Texte> texte = i.parser(test);
		
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
		
		if(!texte.get(0).isItalique())
		{
			fail("Premier texte pas en italique");
		}
		
		if(!texte.get(1).getValeur().equals(" un texte en italique"))
		{
			fail("Valeur du deuxième Texte incorrect");
		}
		
		if(!texte.get(1).isItalique())
		{
			fail("Deuxième texte pas en italique");
		}
	}
	
	@Test
	void test5()
	{
		String test = "[i][\\i]ceci est un texte en italique";
		
		Italique i = new Italique();
		ArrayList<Texte> texte = i.parser(test);
		
		if(texte == null)
		{
			fail("Paragraphe null");
		}
		
		if(texte.size() != 1)
		{
			fail("Nombre d'objet Texte incorrect");
		}
		
		if(!texte.get(0).getValeur().equals("ceci est un texte en italique"))
		{
			fail("Valeur du Texte incorrect");
		}
		
		if(texte.get(0).isItalique())
		{
			fail("texte en italique");
		}
	}
	
	@Test
	void test6()
	{
		String test = "[i]ceci est [\\i]un texte en italique";
		
		Italique i = new Italique();
		ArrayList<Texte> texte = i.parser(test);
		
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
		
		if(!texte.get(0).isItalique())
		{
			fail("Premier texte pas en italique");
		}
		
		if(!texte.get(1).getValeur().equals("un texte en italique"))
		{
			fail("Valeur du deuxième Texte incorrect");
		}
		
		if(texte.get(1).isItalique())
		{
			fail("Deuxième texte en italique");
		}
	}
	
	@Test
	void test7()
	{
		String test = "ceci est un texte en italique";
		
		Italique i = new Italique();
		ArrayList<Texte> texte = i.parser(test);
		
		if(texte == null)
		{
			fail("Paragraphe null");
		}
		
		if(texte.size() != 1)
		{
			fail("Nombre d'objet Texte incorrect");
		}
		
		if(!texte.get(0).getValeur().equals("ceci est un texte en italique"))
		{
			fail("Valeur du Texte incorrect");
		}
		
		if(texte.get(0).isItalique())
		{
			fail("texte en italique");
		}
	}

}
