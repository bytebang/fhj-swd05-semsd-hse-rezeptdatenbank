package fhj.swd05.hutteg.rezeptdb.rezept;

import java.util.HashMap;
import java.util.Map;

import nextapp.echo2.app.*;

public class RezeptChooserGUI extends ContentPane{
	/**
	 * Hierbei handelt es sich um ein Containerobjekt fuer Rezeptobjekte
	 */
	private static final long serialVersionUID = 1L;
	private Map<Rezept,Row> rezepte = new HashMap<Rezept,Row>();
	private Column col = new Column();
	
	/**
	 * ctor
	 */
	RezeptChooserGUI()
	{
		col.setInsets(new Insets(10));
		// Ein Spalte, in der dann alle Rezepte drinnen sind
		add(col);
	}
	/** 
	 * Fuegt ein Rezept in die Liste der Rezepte ein
	 * @param r
	 */
	public void addRezept(Rezept r)
	{
		if(rezepte.containsKey(r) == false)
		{
			Row rezrow = createRezeptRowForRezeptObject(r);
			rezepte.put(r, rezrow);
			col.add(rezrow);
		}
	}

	/**
	 * Entfernt ein Rezept aus der Anzeige
	 * @param rezept
	 * @return
	 */
	public void removeRezept(Rezept r)
	{
		if(rezepte.containsKey(r) == true)
		{
			col.remove(rezepte.get(r));
			rezepte.remove(r);
		}
	}
	private Row createRezeptRowForRezeptObject(Rezept rezept)
	{
		Row r = new Row();
		r.add(new Button(rezept.getBriefDescription()));
		return r;
	}
}
