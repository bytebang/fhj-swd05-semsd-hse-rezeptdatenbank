package fhj.swd05.hutteg.rezeptdb.zutat;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import fhj.swd05.hutteg.rezeptdb.zutat.ZutatDAO;

import nextapp.echo2.app.*;
import nextapp.echo2.app.event.ActionEvent;
import nextapp.echo2.app.event.ActionListener;

public class ZutatChooserGUI extends ContentPane implements ActionListener{
	/**
	 * Hierbei handelt es sich um ein Containerobjekt fuer Rezeptobjekte
	 */
	private static final long serialVersionUID = 1L;
	private Map<Zutat,Row> zutaten = new HashMap<Zutat,Row>();
	private Column col = new Column();
	private ZutatDAO zutatDao;
	/**
	 * ctor
	 */
	ZutatChooserGUI(ZutatDAO zutatDao)
	{
		this.zutatDao = zutatDao;
		col.setInsets(new Insets(10));
		// Ein Spalte, in der dann alle Rezepte drinnen sind
		add(col);
	}
	/** 
	 * Fuegt ein Rezept in die Liste der Rezepte ein
	 * @param r
	 */
	public void addZutat(Zutat z)
	{
		if(zutaten.containsKey(z) == false)
		{
			Row zutrow = createZutatRowForZutatObject(z);
			zutaten.put(z, zutrow);
			col.add(zutrow);
		}
	}

	/**
	 * Entfernt ein Rezept aus der Anzeige
	 * @param rezept
	 * @return
	 */
	public void removeZutat(Zutat z)
	{
		if(zutaten.containsKey(z) == true)
		{
			col.remove(zutaten.get(z));
			zutaten.remove(z);
		}
	}
	private Row createZutatRowForZutatObject(Zutat zutat)
	{
		Row r = new Row();
		Button b = new Button(zutat.getName());
		b.addActionListener(this);
		r.add(b);
		return r;
	}

	/**
	 * Wird fuer jeden Button aufgerufen
	 */
	@Override
	public void actionPerformed(ActionEvent aevent) {

		// Wer hat den Event ausgeloest ?
		Button b = (Button) aevent.getSource();

		// Ueber die Row findet man des RezeptObjekt
		Row r = (Row) b.getParent();

		// Rezept suchen, eventuell gibts da eine elegantere Loesung
		Set<Entry<Zutat,Row>> elements = this.zutaten.entrySet();
		for(Entry<Zutat,Row> e : elements)
		{
			if(e.getValue().equals(r))
			{
				// Das Rezept anzeigen
				ZutatEditorWindow rew = new ZutatEditorWindow(e.getKey(), this.zutatDao);
				r.getParent().getParent().add(rew);
				return;
			}
		}
	}
}
