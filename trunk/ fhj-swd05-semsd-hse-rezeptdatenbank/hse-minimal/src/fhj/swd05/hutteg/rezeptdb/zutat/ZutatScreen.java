package fhj.swd05.hutteg.rezeptdb.zutat;

import java.util.List;
import java.util.regex.Pattern;

import nextapp.echo2.app.Button;
import nextapp.echo2.app.Column;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Row;
import nextapp.echo2.app.event.ActionEvent;
import nextapp.echo2.app.event.ActionListener;

import org.stenerud.hse.base.ui.echo2.BaseApplicationHelper;
import org.stenerud.hse.base.ui.echo2.screen.PaneScreen;

import echopointng.TabbedPane;
import echopointng.tabbedpane.DefaultTabModel;



/**
 * The widget screen. Does nothing at the moment.
 * 
 * @author You
 */
public class ZutatScreen extends PaneScreen implements ActionListener
{

	private static final long serialVersionUID = 1L;

	// == INJECTED MEMBERS ==
	private ZutatDAO zutatDAO = null;
	private ZutatEditRequestor zutatEditor = null;
	
	//== PRIVATE MEMBERS ==


	@SuppressWarnings("unchecked")
	protected void initComponents()
	{
		List<Zutat> zutaten = zutatDAO.getZutaten();
		TabbedPane zutatpane = new TabbedPane();
		DefaultTabModel dtm = new DefaultTabModel();
		
		dtm.addTab("A-E", createColumnWithZutaten(zutaten, "[A-E].*"));
		dtm.addTab("F-N", createColumnWithZutaten(zutaten, "[F-N].*"));
		dtm.addTab("O-T", createColumnWithZutaten(zutaten, "[O-T].*"));
		dtm.addTab("U-Z", createColumnWithZutaten(zutaten, "[U-Z].*"));
		
		zutatpane.setModel(dtm);
		zutatpane.setSelectedIndex(0);
		add(zutatpane);
		
		// ActionListener fuer Zutateneditor hinyufuegen
		zutatEditor.addActionListener(this);
		
	}
	/*
	 * Eryeugt eine Column mit den Zutatenbuttons
	 * @pattern eine Regular Expression
	 */
	private Component createColumnWithZutaten(List<Zutat> zutaten, String pattern) {
		Column col = new Column();
		Pattern p = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);

		for(Zutat z : zutaten)
		{
			// Wenn das Pattern passt wird was zurueckgegeben
			if(p.matcher(z.getName()).matches())
			{
				Row r = createZutatRowForZutatObject(z);
				col.add(r);
			}
		}
		return col;
	}
	/*
	 * Erzeugt eine Zeile fuer ein Zutatenobjekt 
	 */
	private Row createZutatRowForZutatObject(final Zutat zutat)
	{
		Row r = new Row();
		Button b = new Button(zutat.getName());
		b.addActionListener(new ActionListener()
		{
			private static final long serialVersionUID = 1L;
			public void actionPerformed(ActionEvent e)
			{
				processZutatenEdit(zutat);
			}
		});
		r.add(b);
		return r;
	}

	/*
	 * Wird von den 
	 */
	private void processZutatenEdit(Zutat zutat) {
		System.out.println("Die Zutat " + zutat.getName() + " wird editiert");
		
		BaseApplicationHelper.displayRequestor(zutatEditor);
		zutatEditor.setActiveZutat(zutat);
		System.out.println("Die Zutat " + zutat.getName() + " wurde editiert");		
		
	}
	protected void resetComponents()
	{
		// Nothing to do.
	}
	

	public String getTitle()
	{
		// Remember to set screen.widgets in Messages.properties!
		return messages.get("screen.zutat");
	}
	
	// == GETTERS AND SETTERS ==
	
	public ZutatEditRequestor getZutatEditor() {
		return zutatEditor;
	}
	
	public void setZutatEditor(ZutatEditRequestor zutatEditor) {
		this.zutatEditor = zutatEditor;
	}
	
	public ZutatDAO getZutatDAO() {
		return zutatDAO;
	}
	
	public void setZutatDAO(ZutatDAO zutatDAO) {
		this.zutatDAO = zutatDAO;
	}
	/** 
	 * Wird vom Zutateneditor aufgerufen
	 */
	@Override
	public void actionPerformed(ActionEvent actionevent) {

		// Nur JA Events interessieren uns hier
		if ( !actionevent.getActionCommand().equals(ZutatEditRequestor.COMMAND_FALSE) )
		{
			zutatDAO.update(zutatEditor.getActiveZutat());
		}
		else
		{
			// Sonst werden die alten Werte wieder aus der DB geladen
			zutatDAO.refresh(zutatEditor.getActiveZutat());
		}
		// Wie auch immer der Benutzer gewaehlt hat, der Editor wird geschlossen
		zutatEditor.dispose();
	}
}
