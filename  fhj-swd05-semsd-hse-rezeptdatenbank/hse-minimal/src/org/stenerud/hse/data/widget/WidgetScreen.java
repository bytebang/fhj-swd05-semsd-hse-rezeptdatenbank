package org.stenerud.hse.data.widget;


import java.util.List;

import fhj.swd05.hutteg.rezeptdb.rezept.Rezept;
import fhj.swd05.hutteg.rezeptdb.rezept.RezeptDAO;
import fhj.swd05.hutteg.rezeptdb.rezept.RezeptDAODatabase;
import fhj.swd05.hutteg.rezeptdb.zutat.Zutat;
import fhj.swd05.hutteg.rezeptdb.zutat.ZutatDAO;
import fhj.swd05.hutteg.rezeptdb.zutat.ZutatDAODatabase;
import nextapp.echo2.app.Label;


/**
 * The widget screen. Does nothing at the moment.
 * 
 * @author You
 */
public class WidgetScreen extends org.stenerud.hse.base.ui.echo2.screen.PaneScreen
{
	private static final long serialVersionUID = 1L;
	
	private ZutatDAO zdao = null;
	private RezeptDAO rdao = null;	

	public ZutatDAO getZdao() {
		return zdao;
	}

	public void setZdao(ZutatDAO zdao) {
		this.zdao = zdao;
	}

	public RezeptDAO getRdao() {
		return rdao;
	}

	public void setRdao(RezeptDAO rdao) {
		this.rdao = rdao;
	}

	public String getTitle()
	{
		// Remember to set screen.widgets in Messages.properties!
		return messages.get("screen.widgets");
	}

	protected void initComponents()
	{
		
		add(new Label("Testdaten werden erzeugt."));
		
		// Alten Mist aus der DB loeschen
		
		List rezepte = rdao.getRezepte();
		for(int i = 0; i < rezepte.size(); i++)
		{
			rdao.delete((Rezept) rezepte.get(i));
		}
		
		List zutaten = zdao.getZutaten();
		for(int i = 0; i < zutaten.size(); i++)
		{
			zdao.delete((Zutat) zutaten.get(i));
		}
		
		// Nun die Zutaten erzeugen
		Zutat brot = new Zutat();
		brot.setName("Brot");
		brot.setEinheit("Stk");

		Zutat butter = new Zutat();
		butter.setName("Butter");
		butter.setEinheit("g");
		
		Zutat salz = new Zutat();
		salz.setName("Salz");
		salz.setEinheit("brise");
		
		Zutat pfeffer = new Zutat();
		pfeffer.setName("Pfeffer");
		pfeffer.setEinheit("brise");
		
		Zutat oregano = new Zutat();
		oregano.setName("Oregano");
		oregano.setEinheit("brise");
		
		Zutat tabasco = new Zutat();
		tabasco.setName("Tabasco");
		tabasco.setEinheit("schuss");
		
		Zutat spagheti = new Zutat();
		spagheti.setName("Spaghetti");
		spagheti.setEinheit("g");
		
		Zutat milch = new Zutat();
		milch.setName("Milch");
		milch.setEinheit("ml");
		
		Zutat kornflakes = new Zutat();
		kornflakes.setName("Kornfalkes");
		kornflakes.setEinheit("100 g");
		
		Zutat hackfleisch = new Zutat();
		hackfleisch.setName("Hackfleisch");
		hackfleisch.setEinheit("g");
		
		Zutat paprika = new Zutat();
		paprika.setName("Paprika");
		paprika.setEinheit("stk");
		
		Zutat zwiebeln = new Zutat();
		zwiebeln.setName("Zwiebeln");
		zwiebeln.setEinheit("stk");
		
		Zutat tomaten = new Zutat();
		tomaten.setName("Tomaten");
		tomaten.setEinheit("stk");
				
		Zutat zucker = new Zutat();
		zucker.setName("Zucker");
		zucker.setEinheit("gramm");
		
		Zutat ei = new Zutat();
		ei.setName("Ei");
		ei.setEinheit("stk");
		
		Zutat wasser = new Zutat();
		wasser.setName("Wasser");
		wasser.setEinheit("ml");
		
		Zutat schweinsschnitzel = new Zutat();
		schweinsschnitzel.setName("Schweinsschnitzel");
		schweinsschnitzel.setEinheit("ml");		
		
		Zutat semmelbroesel = new Zutat();
		semmelbroesel.setName("Semmelbroesel");
		semmelbroesel.setEinheit("ml");	
		
		Zutat oel = new Zutat();
		oel.setName("Oel");
		oel.setEinheit("ml");			

		Zutat mehl = new Zutat();
		mehl.setName("mehl");
		mehl.setEinheit("g");		
		// So, jetzt haben wir genug Zutaten erzeugt, wir pflegen Sie in die DB ein
			
		zdao.create(brot);
		zdao.create(butter);
		zdao.create(salz);
		zdao.create(pfeffer);
		zdao.create(oregano);
		zdao.create(tabasco);
		zdao.create(spagheti);
		zdao.create(milch);
		zdao.create(kornflakes);
		zdao.create(hackfleisch);
		zdao.create(paprika);
		zdao.create(zwiebeln);
		zdao.create(tomaten);
		zdao.create(zucker);
		zdao.create(ei);
		zdao.create(wasser);
		zdao.create(schweinsschnitzel);
		zdao.create(semmelbroesel);
		zdao.create(oel);
		zdao.create(mehl);

		// Jetzt noch einige BeispielRezepte
		
		Rezept butterbrot = new Rezept();
		butterbrot.setName("Butterbrot");
		butterbrot.setSchwierigkeit(0);
		butterbrot.setZeit(5);
		butterbrot.setZubereitung("Ein Stueck Brot herunterschneiden und auf diesem mit einem Messer die Butter verteilen.");
		butterbrot.addZutat(brot, 1);
		butterbrot.addZutat(butter, 10);
		
		
		Rezept nudelnmitei = new Rezept();
		nudelnmitei.setName("Nudeln mit Ei");
		nudelnmitei.setSchwierigkeit(1);
		nudelnmitei.setZeit(14);
		nudelnmitei.setZubereitung("Wasser in einen Topf geben, Nudeln darin 8 Minuten Kochen lassen. Danach Wasser entfernen und das rohe Ei in den Topf geben. Nochmals kurz erhitzen. Anschliessend mit Zalz und Pfeffer abschmecken.");
		nudelnmitei.addZutat(ei, 1);
		nudelnmitei.addZutat(spagheti, 150);
		nudelnmitei.addZutat(wasser, 500);
		nudelnmitei.addZutat(salz, 1);
		nudelnmitei.addZutat(pfeffer, 1);
		
		Rezept ffc = new Rezept();
		ffc.setName("Fruehstueck fuer Champinions");
		ffc.setSchwierigkeit(0);
		ffc.setZeit(1);
		ffc.setZubereitung("Kornflakes zusammen mit der Milch in eine Schuessel geben und umruehren");
		ffc.addZutat(milch, 150);
		ffc.addZutat(kornflakes, 100);
		
		Rezept wienerschnitzel = new Rezept();
		wienerschnitzel.setName("Wiener Schnitzel");
		wienerschnitzel.setSchwierigkeit(2);
		wienerschnitzel.setZeit(30);
		wienerschnitzel.setZubereitung("Schweinsschnitzel zuerst in Mehl, anschliessend in Ei und dann in Broesel legen. Die so panierten Schnitzel in Oel herausbraten bis sie goldbraun sind.");
		wienerschnitzel.addZutat(schweinsschnitzel, 1);
		wienerschnitzel.addZutat(mehl, 75);
		wienerschnitzel.addZutat(ei, 1);
		wienerschnitzel.addZutat(semmelbroesel, 75);
		wienerschnitzel.addZutat(oel, 133);
		wienerschnitzel.addZutat(salz, 1);

		rdao.create(butterbrot);
		rdao.create(nudelnmitei);
		rdao.create(ffc);
		rdao.create(wienerschnitzel);
		

	}

	protected void resetComponents()
	{
		// Nothing to do.
	}

}
