package fhj.swd05.hutteg.rezeptdb.rezept;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import nextapp.echo2.app.*;
import nextapp.echo2.app.event.ActionEvent;
import nextapp.echo2.app.event.ActionListener;

public class RezeptChooserGUI extends ContentPane implements ActionListener{
	/**
	 * Hierbei handelt es sich um ein Containerobjekt fuer Rezeptobjekte
	 */
	private static final long serialVersionUID = 1L;
	private Map<Rezept,Row> rezepte = new HashMap<Rezept,Row>();
	private Column col = new Column();
	private RezeptDAO rezeptDao;
	
	/**
	 * ctor
	 */
	RezeptChooserGUI(RezeptDAO rezeptDao)
	{
		this.rezeptDao = rezeptDao;
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
		Button b = new Button(rezept.getBriefDescription());
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
		Set<Entry<Rezept,Row>> elements = this.rezepte.entrySet();
		for(Entry<Rezept,Row> e : elements)
		{
			if(e.getValue().equals(r))
			{
				// Das Rezept anzeigen
				RezeptEditorWindow rew = new RezeptEditorWindow(e.getKey(), this.rezeptDao);
				r.getParent().getParent().add(rew);
				return;
			}
		}
	}
}
