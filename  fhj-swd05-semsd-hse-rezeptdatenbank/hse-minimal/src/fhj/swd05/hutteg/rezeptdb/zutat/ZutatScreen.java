package fhj.swd05.hutteg.rezeptdb.zutat;

import nextapp.echo2.app.Label;


/**
 * The widget screen. Does nothing at the moment.
 * 
 * @author You
 */
public class ZutatScreen extends org.stenerud.hse.base.ui.echo2.screen.PaneScreen
{
	private static final long serialVersionUID = 1L;

	public String getTitle()
	{
		// Remember to set screen.widgets in Messages.properties!
		return messages.get("screen.widgets");
	}

	protected void initComponents()
	{
		add(new Label("Hier kommt die Zutat hin"));

	}

	protected void resetComponents()
	{
		// Nothing to do.
	}

}
