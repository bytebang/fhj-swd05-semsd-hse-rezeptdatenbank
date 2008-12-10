package fhj.swd05.hutteg.rezeptdb.rezept;

import nextapp.echo2.app.RadioButton;
import nextapp.echo2.app.Row;
import nextapp.echo2.app.button.ButtonGroup;
import nextapp.echo2.app.event.ActionEvent;
import nextapp.echo2.app.event.ActionListener;

//innere Klasse fuer Schwirigkeiten
class Schwierigkeitsgrad extends Row implements ActionListener 
{
	private static final long serialVersionUID = 1L;
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
