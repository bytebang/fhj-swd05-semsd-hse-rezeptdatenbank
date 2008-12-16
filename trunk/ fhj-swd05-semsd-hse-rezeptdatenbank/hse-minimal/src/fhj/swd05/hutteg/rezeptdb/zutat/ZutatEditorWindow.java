package fhj.swd05.hutteg.rezeptdb.zutat;


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

public class ZutatEditorWindow extends WindowPane implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Private Stuff
	private ZutatDAO zutatDAO = null;
	private Zutat zutat = null;
	private Grid propertiesGrid;

	public ZutatEditorWindow(Zutat zutat, ZutatDAO zutatDao) {
		this.zutatDAO = zutatDao;
		this.zutat = zutat;
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
        setTitle(zutat.getName());
       
        TabbedPane mainpane = new TabbedPane();
        DefaultTabModel defaultTabModel = new DefaultTabModel();
        
        defaultTabModel.addTab("Info",getInfoPane());

        refreshGUIValues();
        mainpane.setModel(defaultTabModel);
        mainpane.setSelectedIndex(0);
        this.add(mainpane);

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
        if(zutatDAO != null)
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

        cp.add(propertiesGrid);
		return cp;
	}
	/**
	 * Updatet die GUI mit den Werten aus dem Rezept
	 */
	private void refreshGUIValues()
	{
		zutatDAO.refresh(zutat);

	}

	
	@Override
	public void actionPerformed(ActionEvent actionevent) {

	}
	
	
}
