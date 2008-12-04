package fhj.swd05.hutteg.rezeptdb.rezept;

import nextapp.echo2.app.ContentPane;
import nextapp.echo2.app.Label;
import nextapp.echo2.app.Pane;
import nextapp.echo2.app.Row;

public class RezeptGuiHelper {
	static Row getRezeptLineForRezeptObkject(Rezept rezept)
	{
		Row r = new Row();
		r.add(new Label(rezept.getName()));
		return r;
	}
}
