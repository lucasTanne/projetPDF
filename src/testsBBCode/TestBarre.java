package testsBBCode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import bbcode.Barre;
import donnees.Texte;

class TestBarre
{
	@Test
	void test()
	{
		String test = "ceci est un texte [s]barré[\\s]";
		
		Barre s = new Barre();
		ArrayList<Texte> texte = s.parser(test);
		
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
		
		if(texte.get(0).isBarre())
		{
			fail("Premier texte barré");
		}
		
		if(!texte.get(1).getValeur().equals("barré"))
		{
			fail("Valeur du second Texte incorrect : ");
		}
		
		if(!texte.get(1).isBarre())
		{
			fail("Second texte pas barré");
		}
	}
	
	@Test
	void test2()
	{
		String test = "[s]ceci est un texte barré[\\s]";
		
		Barre s = new Barre();
		ArrayList<Texte> texte = s.parser(test);
		
		if(texte == null)
		{
			fail("Paragraphe null");
		}
		
		if(texte.size() != 1)
		{
			fail("Nombre d'objet Texte incorrect");
		}
		
		if(!texte.get(0).getValeur().equals("ceci est un texte barré"))
		{
			fail("Valeur du Texte incorrect");
		}
		
		if(!texte.get(0).isBarre())
		{
			fail("Texte pas barré");
		}
	}
	
	@Test
	void test3()
	{
		String test = "[s]ceci est[\\s] un texte [s]barré[\\s]";
		
		Barre s = new Barre();
		ArrayList<Texte> texte = s.parser(test);
		
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
		
		if(!texte.get(0).isBarre())
		{
			fail("Premier texte pas barré");
		}
		
		if(!texte.get(1).getValeur().equals(" un texte "))
		{
			fail("Valeur du deuxième Texte incorrect : ");
		}
		
		if(texte.get(1).isBarre())
		{
			fail("Deuxième texte barré");
		}
		
		if(!texte.get(2).getValeur().equals("barré"))
		{
			fail("Valeur du troisième Texte incorrect");
		}
		
		if(!texte.get(2).isBarre())
		{
			fail("Troisième texte pas barré");
		}
	}
	
	@Test
	void test4()
	{
		String test = "[s]ceci est[\\s][s] un texte barré[\\s]";
		
		Barre s = new Barre();
		ArrayList<Texte> texte = s.parser(test);
		
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
		
		if(!texte.get(0).isBarre())
		{
			fail("Premier texte pas barré");
		}
		
		if(!texte.get(1).getValeur().equals(" un texte barré"))
		{
			fail("Valeur du deuxième Texte incorrect");
		}
		
		if(!texte.get(1).isBarre())
		{
			fail("Deuxième texte pas barré");
		}
	}
	
	@Test
	void test5()
	{
		String test = "[s][\\s]ceci est un texte barré";
		
		Barre s = new Barre();
		ArrayList<Texte> texte = s.parser(test);
		
		if(texte == null)
		{
			fail("Paragraphe null");
		}
		
		if(texte.size() != 1)
		{
			fail("Nombre d'objet Texte incorrect");
		}
		
		if(!texte.get(0).getValeur().equals("ceci est un texte barré"))
		{
			fail("Valeur du Texte incorrect");
		}
		
		if(texte.get(0).isBarre())
		{
			fail("texte barré");
		}
	}
	
	@Test
	void test6()
	{
		String test = "[s]ceci est [\\s]un texte barré";
		
		Barre s = new Barre();
		ArrayList<Texte> texte = s.parser(test);
		
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
		
		if(!texte.get(0).isBarre())
		{
			fail("Premier texte pas barré");
		}
		
		if(!texte.get(1).getValeur().equals("un texte barré"))
		{
			fail("Valeur du deuxième Texte incorrect");
		}
		
		if(texte.get(1).isBarre())
		{
			fail("Deuxième texte barré");
		}
	}
	
	@Test
	void test7()
	{
		String test = "ceci est un texte barré";
		
		Barre s = new Barre();
		ArrayList<Texte> texte = s.parser(test);
		
		if(texte == null)
		{
			fail("Paragraphe null");
		}
		
		if(texte.size() != 1)
		{
			fail("Nombre d'objet Texte incorrect");
		}
		
		if(!texte.get(0).getValeur().equals("ceci est un texte barré"))
		{
			fail("Valeur du Texte incorrect");
		}
		
		if(texte.get(0).isBarre())
		{
			fail("texte barré");
		}
	}

}
