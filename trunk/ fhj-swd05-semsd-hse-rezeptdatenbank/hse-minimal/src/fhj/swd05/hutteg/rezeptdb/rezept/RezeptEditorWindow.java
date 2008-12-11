package fhj.swd05.hutteg.rezeptdb.rezept;


import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import nextapp.echo2.app.Button;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Column;
import nextapp.echo2.app.ContentPane;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Grid;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.Label;
import nextapp.echo2.app.Row;
import nextapp.echo2.app.TextArea;
import nextapp.echo2.app.TextField;
import nextapp.echo2.app.WindowPane;
import nextapp.echo2.app.event.ActionEvent;
import nextapp.echo2.app.event.ActionListener;
import nextapp.echo2.app.list.DefaultListModel;


import org.stenerud.hse.base.ui.echo2.Theme;

import echopointng.ComboBox;
import echopointng.Separator;
import echopointng.Slider;
import echopointng.TabbedPane;
import echopointng.tabbedpane.DefaultTabModel;
import fhj.swd05.hutteg.rezeptdb.zutat.Zutat;
import fhj.swd05.hutteg.rezeptdb.zutat.ZutatDAO;

public class RezeptEditorWindow extends WindowPane implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Private Stuff
	private Rezept rezept = null;
	private RezeptDAO rezeptDAO = null;
	private ZutatDAO zutatDAO = null;
	
	private Slider slZeit = new Slider();
	private Schwierigkeitsgrad sgSchwierigkeit = new Schwierigkeitsgrad();
	private TextArea taZubereitung = new TextArea();
    Grid propertiesGrid = new Grid();
    Grid zutatenGrid = new Grid();
    
	private ComboBox cbZutaten = new ComboBox();
	private Button zutatenAddButton = new Button();
	private TextField mengeTextfield = new TextField();
	/**
	 * ctor
	 * @param rezept
	 */
	public RezeptEditorWindow(Rezept rezept, RezeptDAO rezeptDao, ZutatDAO zutatDao) {
		this.rezept = rezept;
		this.rezeptDAO = rezeptDao;
		this.zutatDAO = zutatDao;
		this.initGui();
	}

	/** 
	 * Erzeugt die GUI Komponenten
	 */
	private void initGui() {
		// Das sollte das einzige Fenster sein
		setModal(true);

		// Window will automatically close when "X" button is clicked.
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Titel
        setTitle(rezept.getName());
       
        TabbedPane mainpane = new TabbedPane();
        DefaultTabModel defaultTabModel = new DefaultTabModel();
        
        defaultTabModel.addTab("Info",getInfoPane());
        defaultTabModel.addTab("Zubereitung",getZubereitungPane());
        defaultTabModel.addTab("Zutaten",getZutatenPane());

        refreshGUIValues();
        mainpane.setModel(defaultTabModel);
        mainpane.setSelectedIndex(0);
        this.add(mainpane);

}
	/**
	 * Erzeugt eine Contentpane mit der die Zutaten angezeigt / editiert werden koennen
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private ContentPane getZutatenPane() {
		ContentPane cp = new ContentPane();
		Column col = new Column();
		cp.add(col);
		
		// Grid mit den aktuellen Zutaten erzeugen
        zutatenGrid.setOrientation(Grid.ORIENTATION_HORIZONTAL);
        zutatenGrid.setSize(3); // Es gibt 3 Spalten
        zutatenGrid.setInsets(new Insets(10));
		
        rezeptDAO.refresh(rezept); // Sonst funktioniert lazyLoading nicht !!
        Set<Entry<Zutat,Integer>> elements = rezept.getZutaten().entrySet();
		for(Entry<Zutat,Integer> e : elements)
		{
			Zutat z = e.getKey();
			Integer m = e.getValue();
			// Zeile erzeugen
			addZutatToGrid(zutatenGrid, z, m);			
		}
        
		col.add(zutatenGrid);
		
		col.add(new Separator());
		Row zutatenaddrow = new Row();
		col.add(zutatenaddrow);
		
		// ComboBox mit allen Zutaten befuellen
		List zutaten = this.zutatDAO.getZutaten();
		DefaultListModel lm = new DefaultListModel();
			
		for(int i = 0; i < zutaten.size(); i++)
		{
			lm.add(((Zutat)zutaten.get(i)).getName());
		}
		cbZutaten.setListModel(lm);
		cbZutaten.setTextMatchingPerformed(true);
		
		zutatenaddrow.add(new Label("Zutat: "));
		zutatenaddrow.add(cbZutaten);
		zutatenaddrow.add(new Label("Menge: "));
		zutatenaddrow.add(mengeTextfield);
		mengeTextfield.setText("1");
		mengeTextfield.setMaximumLength(5);
		mengeTextfield.setWidth(new Extent(50,Extent.PX));
		
		zutatenaddrow.add(zutatenAddButton);
		zutatenAddButton.setText("+");
		zutatenAddButton.setBackground(new Color(120,230,120));
		zutatenAddButton.setToolTipText("Fuegt die Zutat in die Liste hinzu");
		zutatenAddButton.setActionCommand("add_zutat");
		zutatenAddButton.addActionListener(this);
		
		
        refreshGUIValues();
		// Jetzt koennen die Zutaten angezeigt werden.
		// Jeder Zutateneintrag enthaelt eine Menge und eben die Zutat.
		// Jede Zeile erhaelt einen update und einen loeschen Knopf
		// Ganz unten wird eine ComboBox angezeigt, aus der weitere Zutaten hinzugefuegt werden koennen.
		// Wenn man etwas i.d. cb schreibt, wird automatisch gefiltert
		
		return cp;
	}

	/*
	 * Fuegt eine Zutat mit einer bestimmten Menge in das Grid ein
	 */
	private void addZutatToGrid(Grid zutatenGrid, Zutat z, Integer m) {
		Button deleteButton = new Button("X");
		deleteButton.setBackground(new Color(255,230,153));
		deleteButton.setActionCommand("delete_zutat_" + String.valueOf(z.getId()));
		deleteButton.addActionListener(this);
		deleteButton.setToolTipText("Die Zutat " + z.getName() + " loeschen." );
		
		// Als erstes kommt der Entfernen Knopf hin
		zutatenGrid.add(deleteButton);
		// Actionhandler fuer entfernen setzen
		
		// Danach ein Label mit dem Namen der Zutat
		zutatenGrid.add(new Label(z.getName()));
		
		// Danach ein Label mit dem Namen der Zutat
		zutatenGrid.add(new Label(String.valueOf(m)));
	}

	
	/** 
	 * Erzeugt die Pane auf der die Zubereitung beschrieben wird
	 */
	private ContentPane getZubereitungPane() {
		ContentPane cp = new ContentPane();
		
		Column col = new Column();
		col.add(taZubereitung);
		taZubereitung.setText(rezept.getZubereitung());
        taZubereitung.setWidth(new Extent(98,Extent.PERCENT));
        taZubereitung.setHeight(new Extent(250,Extent.PX));
        cp.add(col);
        return cp;
	}
	/**
	 * Liefert das Grind mit den Eigenschaften zurueck
	 * @return
	 */
	private ContentPane getInfoPane() {
		ContentPane cp = new ContentPane();
		
		// Create a grid to contain buttons and add to toolbar pane.
        propertiesGrid.setOrientation(Grid.ORIENTATION_HORIZONTAL);
        propertiesGrid.setSize(2); // Es gibt 2 Spalten
        propertiesGrid.setInsets(new Insets(10));
        
        // Nur wenn ein DAO da ist kann man etwas speichern
        if(rezeptDAO != null)
        {
	        // Add "save" Button.
	        Button savebutton = new Button("Speichern");
	        savebutton.setIcon(Theme.getIcon16("Yes"));
	        savebutton.setActionCommand("save");
	        savebutton.addActionListener(this);
	        propertiesGrid.add(savebutton);
	        
	        // Add "delete" Button.
	        Button deletebutton = new Button("Loeschen");
	        deletebutton.setIcon(Theme.getIcon16("Delete"));
	        deletebutton.setActionCommand("delete");
	        deletebutton.addActionListener(this);
	        propertiesGrid.add(deletebutton);
        }
        // ---- Rezeptspezifische dinge

        // Schwierigkeit
        propertiesGrid.add(new Label("Schwierigkeit :"));
        sgSchwierigkeit.setSchwierigkgeit(rezept.getSchwierigkeit());
        propertiesGrid.add(sgSchwierigkeit);
        
        // Zubereitungszeit
        propertiesGrid.add(new Label("Zubereitungszeit [Minuten] :"));    
        slZeit.setMinimum(1);
        slZeit.setMaximum(60);
        slZeit.setValue(rezept.getZeit());
        slZeit.setWidth(new Extent(90,Extent.PERCENT));
        propertiesGrid.add(slZeit);
        
        cp.add(propertiesGrid);
		return cp;
	}
	/**
	 * Updatet die GUI mit den Werten aus dem Rezept
	 */
	private void refreshGUIValues()
	{
		rezeptDAO.refresh(rezept);
		this.sgSchwierigkeit.setSchwierigkgeit(rezept.getSchwierigkeit());
		this.slZeit.setValue(rezept.getZeit());
		this.taZubereitung.setText(rezept.getZubereitung());
	}
	/**
	 * Holt aus der GUI die aktuellen Werte und schreibt Sie ins Rezept Objekt
	 */
	private void updateRezeptValues()
	{
		rezept.setSchwierigkeit(this.sgSchwierigkeit.getSchwierigkgeit());
		rezept.setZeit(this.slZeit.getValue());
		rezept.setZubereitung(this.taZubereitung.getText());
	}
	
	@Override
	public void actionPerformed(ActionEvent actionevent) {
		if(actionevent.getActionCommand().equalsIgnoreCase("save"))
		{
			this.updateRezeptValues();
			rezeptDAO.update(rezept);
			userClose();
		}
		else if (actionevent.getActionCommand().startsWith("delete_zutat_"))
		{
			Button eventButton = (Button) actionevent.getSource();
			Grid g = (Grid) eventButton.getParent();
			// Wir suchen den Button im Grid
			for (int i = 0; i < g.getComponentCount(); i++)
			{
				// Handelt es sich um den Eventausloesenden Button ?
				if(g.getComponent(i).equals(eventButton))
				{
					// Jep, also loeschen wir ihn weg
					g.remove(i+2); // Mengenlabel
					g.remove(i+1); // Bezeichnung
					g.remove(i);   // Button selbst
					// Wir sind fertig
					break;
				}
			}	
			
			// Jetzt entfernen wir ihn noch aus der DB
			
			// Als erstes suchen wir ihn in der Zutatenliste
			// Zuerst die ZutatenId herausholen
			String sZutatId = actionevent.getActionCommand().substring(13);
			int zutatId = Integer.parseInt(sZutatId);
			
			rezeptDAO.refresh(rezept); // Sonst funktioniert lazyLoading nicht !!
	        Set<Entry<Zutat,Integer>> elements = rezept.getZutaten().entrySet();
	        Zutat z = null;
	        for(Entry<Zutat,Integer> e : elements)
			{
				// Haben wir die Richtige Zutat gefunden ?
				if( e.getKey().getId() == zutatId)
				{
					// Ja, also entfernen wir sie aus der Liste
					z = e.getKey();
					break;
				}
			}
	        if(z != null)
	        {
	        	rezept.getZutaten().remove(z);
	        }
			rezeptDAO.update(rezept);
			refreshGUIValues();
		}
		else if(actionevent.getActionCommand().equalsIgnoreCase("add_zutat"))
		{
			String zutatenname = this.cbZutaten.getText();
			String mengenstring = this.mengeTextfield.getText();
			if(zutatenname.equals(""))
			{
				return;
			}
			Zutat neueZutat = this.zutatDAO.getZutat(zutatenname);
			Integer neueMenge = Integer.parseInt(mengenstring);
			
			this.addZutatToGrid(zutatenGrid, neueZutat, neueMenge);
			
			// Es wird de aktuell ausgewaehlte Zutat hinzugefuegt.
			rezeptDAO.refresh(rezept);
			this.rezept.getZutaten().put(neueZutat, neueMenge);
			rezeptDAO.update(rezept);
			refreshGUIValues();
		}
		else if(actionevent.getActionCommand().equalsIgnoreCase("delete"))
		{
			rezeptDAO.delete(rezept);
			userClose();
		}
	}
	
	
}
