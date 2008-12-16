package fhj.swd05.hutteg.rezeptdb.zutat;

import java.util.List;

import echopointng.TabbedPane;
import echopointng.tabbedpane.DefaultTabModel;



/**
 * The widget screen. Does nothing at the moment.
 * 
 * @author You
 */
public class ZutatScreen extends org.stenerud.hse.base.ui.echo2.screen.PaneScreen
{
	public ZutatDAO getZutatDAO() {
		return zutatDAO;
	}

	public void setZutatDAO(ZutatDAO zutatDAO) {
		this.zutatDAO = zutatDAO;
	}

	private static final long serialVersionUID = 1L;
	private ZutatDAO zutatDAO = null;
	DefaultTabModel defaultTabModel = new DefaultTabModel();
	private TabbedPane zutatpane = new TabbedPane();

	public ZutatDAO getZutatDao() {
		return zutatDAO;
	}

	public void setZutatDao(ZutatDAO zutatDao) {
		this.zutatDAO = zutatDao;
	}

	public String getTitle()
	{
		// Remember to set screen.widgets in Messages.properties!
		return messages.get("screen.zutat");
	}

	@SuppressWarnings("unchecked")
	protected void initComponents()
	{

		List<Zutat> zutaten = zutatDAO.getZutaten();
		
		String firstLetter = "::::::"; // Ein Rezept das es bestimmt nicht gibt
		ZutatChooserGUI rgui = null;
		
		for(Zutat z : zutaten)
		{
			if(z.getName().startsWith(firstLetter))
			{
				// es sollte schon ein Tab geben, das rezept gehoert dahin
				rgui.addZutat(z);
			}
			else
			{
				// scheinbar gibt es noch kein Tab, also machen wir ein neues
				firstLetter = String.valueOf(z.getName().charAt(0));
				rgui = new ZutatChooserGUI(this.zutatDAO);
				rgui.addZutat(z);
				defaultTabModel.addTab(firstLetter, rgui);
			}
		}
		this.zutatpane.setModel(defaultTabModel);
	    
		// Wenn mindestens ein Tab hinzugefuegt wurde wird das erste Tab angezeigt
		if(rgui != null)
		{
			this.zutatpane.setSelectedIndex(0);
		}
		add(this.zutatpane);
	}

	protected void resetComponents()
	{
		// Nothing to do.
	}

}
