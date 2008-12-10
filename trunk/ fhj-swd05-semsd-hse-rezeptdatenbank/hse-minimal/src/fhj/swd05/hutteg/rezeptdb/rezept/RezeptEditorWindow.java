package fhj.swd05.hutteg.rezeptdb.rezept;


import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import nextapp.echo2.app.Button;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Column;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.ContentPane;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Grid;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.Label;
import nextapp.echo2.app.Row;
import nextapp.echo2.app.SplitPane;
import nextapp.echo2.app.TextArea;
import nextapp.echo2.app.WindowPane;
import nextapp.echo2.app.event.ActionEvent;
import nextapp.echo2.app.event.ActionListener;
import nextapp.echo2.app.layout.SplitPaneLayoutData;

import org.stenerud.hse.base.ui.echo2.Theme;

import echopointng.Slider;
import echopointng.TabbedPane;
import echopointng.tabbedpane.DefaultTabModel;
import fhj.swd05.hutteg.rezeptdb.zutat.Zutat;

public class RezeptEditorWindow extends WindowPane implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Private Stuff
	private Rezept rezept = null;
	private RezeptDAO rezeptDAO = null;
	
	private Slider slZeit = new Slider();
	private Schwierigkeitsgrad sgSchwierigkeit = new Schwierigkeitsgrad();
	private TextArea taZubereitung = new TextArea();
	/**
	 * ctor
	 * @param rezept
	 */
	public RezeptEditorWindow(Rezept rezept, RezeptDAO rezeptDao) {
		this.rezept = rezept;
		this.rezeptDAO = rezeptDao;
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
        this.add(mainpane);
       

}
	/**
	 * Erzeugt eine Contentpane mit der die Zutaten angezeigt / editiert werden koennen
	 * @return
	 */
	private ContentPane getZutatenPane() {
		ContentPane cp = new ContentPane();
		
		// Grid mit den aktuellen Zutaten erzeugen
        Grid zutatenGrid = new Grid();
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
			Button deleteButton = new Button("X");
			deleteButton.setBackground(new Color(255,230,153));
			deleteButton.setActionCommand(String.valueOf(z.getId()));
			deleteButton.addActionListener(this);
			
			// Als erstes kommt der Entfernen Knopf hin
			zutatenGrid.add(deleteButton);
			// Actionhandler fuer entfernen setzen
			
			// Danach ein Label mit dem Namen der Zutat
			zutatenGrid.add(new Label(z.getName()));
			
			// Danach ein Label mit dem Namen der Zutat
			zutatenGrid.add(new Label(String.valueOf(m)));			
		}
        
		cp.add(zutatenGrid);
		
        refreshGUIValues();
		// Jetzt koennen die Zutaten angezeigt werden.
		// Jeder Zutateneintrag enthaelt eine Menge und eben die Zutat.
		// Jede Zeile erhaelt einen update und einen loeschen Knopf
		// Ganz unten wird eine ComboBox angezeigt, aus der weitere Zutaten hinzugefuegt werden koennen.
		// Wenn man etwas i.d. cb schreibt, wird automatisch gefiltert
		
		return cp;
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
        Grid propertiesGrid = new Grid();
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
		else if(actionevent.getActionCommand().equalsIgnoreCase("delete"))
		{
			rezeptDAO.delete(rezept);
			userClose();
		}
	}
	
	
}
