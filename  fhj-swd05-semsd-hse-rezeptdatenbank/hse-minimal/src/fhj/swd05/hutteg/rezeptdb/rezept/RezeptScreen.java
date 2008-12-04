package fhj.swd05.hutteg.rezeptdb.rezept;

import java.util.List;

import echopointng.TabbedPane;
import echopointng.tabbedpane.DefaultTabModel;
import fhj.swd05.hutteg.rezeptdb.zutat.Zutat;
import fhj.swd05.hutteg.rezeptdb.zutat.ZutatDAO;
import nextapp.echo2.app.Label;
import nextapp.echo2.app.Row;


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

	protected void initComponents()
	{
		DefaultTabModel defaultTabModel = new DefaultTabModel();
		for(char c = 'A'; c <= 'Z'; c++)
		{
			List rezepte = rezeptDAO.getRezeptStartingWith(String.valueOf(c));
			for(int i = 0; i < rezepte.size(); i++)
			{
				defaultTabModel.addTab(String.valueOf(c), new Label("Hier kommt der Content hin ?"));
			}
		}

		this.rezeptpane.setModel(defaultTabModel);
	    add(this.rezeptpane);

/*		    
		add(new Label("Rezepte kommen hier hin"));
		
		Zutat milch = new Zutat();
		milch.setName("Milch");
		milch.setEinheit("liter");
		milch.setEnergiemenge(2000);
		zutatDAO.create(milch);
		
		Zutat kornflakes = new Zutat();
		kornflakes.setName("Kornfalkes");
		kornflakes.setEinheit("Gramm");
		kornflakes.setEnergiemenge(5000);
		zutatDAO.create(kornflakes);

		Rezept r = new Rezept();		
		r.addZutat(milch, 1);
		r.addZutat(kornflakes, 2);
		r.setName("Fruestueck fuer Champignons");
		r.setSchwierigkeit(1);
		r.setZeit(1);
		r.setZubereitung("Milch und Kornflakes in Schuessel geben");
		
		rezeptDAO.create(r);
*/
	}

	protected void resetComponents()
	{
		// Nothing to do.
	}

}
