package fhj.swd05.hutteg.rezeptdb.zutat;


import nextapp.echo2.app.Column;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.TextField;
import nextapp.echo2.app.event.ActionEvent;

import org.stenerud.hse.base.ui.echo2.component.StaticTable;
import org.stenerud.hse.base.ui.echo2.component.TrueFalseRequestor;
import org.stenerud.hse.base.ui.echo2.tool.TwoColumnDataHelper;
import org.stenerud.hse.base.ui.echo2.validation.ValidationRuleMaker;
import org.stenerud.hse.base.ui.validation.ValidationException;
import org.stenerud.hse.base.ui.validation.Validator;

public class ZutatEditRequestor extends TrueFalseRequestor{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Private Stuff
	private TextField einheitTextField = null;
	private TextField energieMengeTextField = null; // Textfield, damit man die Formularvalidierung testen kann
	private Validator validator = new Validator();

	private Zutat activeZutat;
	// ctor
	public ZutatEditRequestor() {
	}

	// muss ueberschrieben werden um Inhalte hinzuzufuegen
	protected void initContents(Column contents)
	{
		
		setTrueString("Speichern");
		setFalseString("Abbrechen");
		setResizable(true);

		ValidationRuleMaker rules = new ValidationRuleMaker(validator);

		StaticTable table = new StaticTable(2);
		contents.add(table);
		table.setStyleName("TwoColumn.Table");

		TwoColumnDataHelper dataHelper = new TwoColumnDataHelper(messages, "TwoColumn.FieldName", "TwoColumn.FieldValue");

		einheitTextField = dataHelper.addTextField(table, "zutat.einheit", 15, -1);
		einheitTextField.setWidth(new Extent(200, Extent.PX));
		rules.addNotEmptyRule("zutat.einheit", einheitTextField);

		energieMengeTextField = dataHelper.addTextField(table, "zutat.energiemenge", 15, -1);
		energieMengeTextField.setWidth(new Extent(200, Extent.PX));
		rules.addIntegerRule("zutat.energiemenge", energieMengeTextField);
		
	}
	
	// Kann ueberschrieben werden
	protected boolean handleAction(ActionEvent ev)
	{
		if ( !ev.getActionCommand().equals(COMMAND_FALSE) )
		{
			try
			{
				validator.validate(messages);
				
				// Wenn wir hier ankommen, hat alles gepasst und wir koennen die Eigenschaften am
				// Businessobjekt setzen
				activeZutat.setEinheit(getEinheit());
				activeZutat.setEnergiemenge(Integer.parseInt(getEnergiemenge()));
			}
			catch ( ValidationException ex )
			{
				handleValidationException(ex);
				return false;
			}
			catch ( RuntimeException ex )
			{
				if ( !handleException(ex) )
				{
					throw ex;
				}
				return false;
			}
		}
		return true;

	}
	
	// == Getters and Setters ==
	public void setEinheit(String newValue)
	{
		einheitTextField.setText(newValue);
	}
	
	public String getEinheit()
	{
		return einheitTextField.getText();
	}
	
	public void setEnergiemenge(String newValue)
	{
		energieMengeTextField.setText(newValue);
	}
	
	public String getEnergiemenge()
	{
		return energieMengeTextField.getText();
	}

	/**
	 * Wird vom Aufrufenden Objekt gesetzt. Dient als Referenz auf die zu aendernde Zutat
	 * @param zutat
	 */
	public void setActiveZutat(Zutat zutat) {
		this.activeZutat = zutat;
		setEinheit(zutat.getEinheit());
		setEnergiemenge(String.valueOf(zutat.getEnergiemenge()));
		setTitle("Zutat : " + zutat.getName());
	}

	public Zutat getActiveZutat() {
		return activeZutat;
	}
}
