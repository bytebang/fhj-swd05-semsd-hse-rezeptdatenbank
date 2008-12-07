package fhj.swd05.hutteg.rezeptdb.rezept;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Button;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Column;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.ContentPane;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Grid;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.Label;
import nextapp.echo2.app.RadioButton;
import nextapp.echo2.app.Row;
import nextapp.echo2.app.SelectField;
import nextapp.echo2.app.SplitPane;
import nextapp.echo2.app.TextArea;
import nextapp.echo2.app.TextField;
import nextapp.echo2.app.WindowPane;
import nextapp.echo2.app.button.ButtonGroup;
import nextapp.echo2.app.event.ActionEvent;
import nextapp.echo2.app.event.ActionListener;
import nextapp.echo2.app.layout.ColumnLayoutData;
import nextapp.echo2.app.layout.GridLayoutData;
import nextapp.echo2.app.layout.RowLayoutData;
import nextapp.echo2.app.layout.SplitPaneLayoutData;
import nextapp.echo2.app.list.DefaultListModel;
import nextapp.echo2.app.list.ListModel;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.dao.support.DaoSupport;
import org.springframework.ui.velocity.SpringResourceLoader;
import org.stenerud.hse.base.tool.SpringHelper;
import org.stenerud.hse.base.ui.echo2.Theme;
import org.stenerud.hse.base.ui.echo2.screen.ScreenManager;

import echopointng.ComboBox;
import echopointng.Slider;

public class RezeptEditorWindow extends WindowPane implements ActionListener{
	
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
	public RezeptEditorWindow(Rezept rezept) {
		this.rezept = rezept;
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
       
        // Zubereitung
        taZubereitung.setText(rezept.getZubereitung());
        taZubereitung.setWidth(new Extent(98,Extent.PERCENT));
        taZubereitung.setHeight(new Extent(250,Extent.PX));
        
        // Keine Scrollbars
        SplitPaneLayoutData layout = new SplitPaneLayoutData();
		layout.setOverflow(SplitPaneLayoutData.OVERFLOW_HIDDEN);
        
        SplitPane sp = new SplitPane();
        sp.setLayoutData(layout);
        sp.setOrientation(SplitPane.ORIENTATION_VERTICAL);
        sp.setSeparatorColor(Color.GREEN);
        sp.setSeparatorPosition(new Extent(150,Extent.PX));
        sp.add(getPropGrid());
        
        sp.add(taZubereitung);

        
        add(sp);
       
}
	/**
	 * Liefert das Grind mit den Eigenschaften zurueck
	 * @return
	 */
	private Grid getPropGrid() {
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
		return propertiesGrid;
	}

	@Override
	public void actionPerformed(ActionEvent actionevent) {
		if(actionevent.getActionCommand().equalsIgnoreCase("save"))
		{
			rezeptDAO.update(rezept);
		}
		else if(actionevent.getActionCommand().equalsIgnoreCase("delete"))
		{
			rezeptDAO.delete(rezept);
		}
			
	}
	
	// innere Klasse fuer Schwirigkeiten
	class Schwierigkeitsgrad extends Row implements ActionListener 
	{
		public static final int SCHWIERIGKEIT_ANFAENGER = 0;
		public static final int SCHWIERIGKEIT_MITTEL = 1;
		public static final int SCHWIERIGKEIT_KOMPLIZIERT = 2;
		
		// Private stuff
		private int schwierigkeit;
		ButtonGroup rbg = new ButtonGroup();

		Schwierigkeitsgrad()
		{
			
			// RadioButtons erzeugen
			RadioButton rbAnfaenger = new RadioButton();
			rbAnfaenger.setActionCommand(String.valueOf(SCHWIERIGKEIT_ANFAENGER));
			rbAnfaenger.setText("Anfaenger");
			rbAnfaenger.addActionListener(this);
			rbAnfaenger.setGroup(rbg);
			
			RadioButton rbMittel = new RadioButton();
			rbMittel.setActionCommand(String.valueOf(SCHWIERIGKEIT_MITTEL));
			rbMittel.setText("Mittel");
			rbMittel.addActionListener(this);			
			rbMittel.setGroup(rbg);

			RadioButton rbKompliziert = new RadioButton();
			rbKompliziert.setActionCommand(String.valueOf(SCHWIERIGKEIT_KOMPLIZIERT));
			rbKompliziert.setText("Kompliziert");
			rbKompliziert.addActionListener(this);	
			rbKompliziert.setGroup(rbg);

			add(rbAnfaenger);
			add(rbMittel);
			add(rbKompliziert);
		}
		/**
		 * Liefert die ausgewaehlte Schwierigkeit zurueck
		 * @return
		 */
		public int getSchwierigkgeit()
		{
			return schwierigkeit;
		}
		public void setSchwierigkgeit(int newSchwierigkeit)
		{
			schwierigkeit = newSchwierigkeit;
			
			// und noch in der GUI nachziehen
			for(int i = 0; i < this.getComponentCount(); i++)
			{
				RadioButton rb = (RadioButton) this.getComponents()[i];
				if(rb.getActionCommand().equalsIgnoreCase(String.valueOf(newSchwierigkeit)))
				{
					rb.setSelected(true);
					break;
				}
			}
		}
		/**
		 * Wird von den Radiobuttons aufgerufen
		 * @param actionevent
		 */
		@Override
		public void actionPerformed(ActionEvent actionevent) {
			schwierigkeit = Integer.valueOf(actionevent.getActionCommand()).intValue();	
		}
	}
}
