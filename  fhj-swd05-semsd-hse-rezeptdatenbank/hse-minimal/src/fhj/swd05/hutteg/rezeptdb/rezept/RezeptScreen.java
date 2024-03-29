package fhj.swd05.hutteg.rezeptdb.rezept;


import java.util.List;

import echopointng.TabbedPane;
import echopointng.tabbedpane.DefaultTabModel;

import fhj.swd05.hutteg.rezeptdb.zutat.ZutatDAO;



/**
 * The widget screen. Does nothing at the moment.
 * 
 * @author You
 */
public class RezeptScreen extends org.stenerud.hse.base.ui.echo2.screen.PaneScreen
{
	private static final long serialVersionUID = 1L;
	private RezeptDAO rezeptDAO = null;
	private ZutatDAO zutatDAO = null;
	
	private TabbedPane rezeptpane = new TabbedPane();
	DefaultTabModel defaultTabModel = new DefaultTabModel();
	
	public ZutatDAO getZutatDAO() {
		return zutatDAO;
	}

	public void setZutatDAO(ZutatDAO zutatDAO) {
		this.zutatDAO = zutatDAO;
	}

	public RezeptDAO getRezeptDAO() {
		return rezeptDAO;
	}

	public void setRezeptDAO(RezeptDAO rezeptDAO) {
		this.rezeptDAO = rezeptDAO;
	}

	public String getTitle()
	{
		// Remember to set screen.widgets in Messages.properties!
		return messages.get("screen.rezept");
	}

	@SuppressWarnings("unchecked")
	protected void initComponents()
	{

		List<Rezept> rezepte = rezeptDAO.getRezepte();
		
		String firstLetter = "::::::"; // Ein Rezept das es bestimmt nicht gibt
		RezeptChooserGUI rgui = null;
		for(Rezept r : rezepte)
		{
			if(r.getName().startsWith(firstLetter))
			{
				// es sollte schon ein Tab geben, das rezept gehoert dahin
				rgui.addRezept(r);
			}
			else
			{
				// scheinbar gibt es noch kein Tab, also machen wir ein neues
				firstLetter = String.valueOf(r.getName().charAt(0));
				rgui = new RezeptChooserGUI(this.rezeptDAO, this.zutatDAO);
				rgui.addRezept(r);
				defaultTabModel.addTab(firstLetter, rgui);
			}
		}
		this.rezeptpane.setModel(defaultTabModel);
	    
		// Wenn mindestens ein Tab hinzugefuegt wurde wird das erste Tab angezeigt
		if(rgui != null)
		{
			this.rezeptpane.setSelectedIndex(0);
		}
		add(this.rezeptpane);
	}

	protected void resetComponents()
	{
		// Nothing to do.
	}

}
