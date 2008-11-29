package fhj.swd05.hutteg.rezeptdb.rezept;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import fhj.swd05.hutteg.rezeptdb.zutat.Zutat;

public class Rezept {
	private int id;
	private String name;
	private int zeit;
	private int schwierigkeit;
	private String zubereitung;
	private Map <Zutat,Integer> zutaten = new HashMap<Zutat,Integer>();
	
	/**
	 * Fuegt dem Rezept eine neue Zutat hinzu
	 * @param neueZutat
	 */
	public void addZutat(Zutat neueZutat, int menge)
	{
		this.zutaten.put(neueZutat, menge);
	}
	
	/**
	 * Entfernt eine Zutat aus dem Rezept
	 * @param neueZutat
	 */
	public void removeZutat(Zutat zuEntfernendeZutat)
	{
		this.zutaten.remove(zuEntfernendeZutat);
	}
	/**
	 * Ermittelt die Gesamtenergie des Rezepts. (Summe ueber alle Zutaten)
	 * @return
	 */
	public float getEnergie()
	{
		float gesamtenergie = 0;
		for(Entry<Zutat,Integer> e: zutaten.entrySet())
		{
			Zutat z = e.getKey();
			int menge = e.getValue();
			gesamtenergie += z.getEnergieForAnzEinheiten(menge);	
		}
		return gesamtenergie;
	}
	//========== GETTERS AND SETTERS ================
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getZeit() {
		return zeit;
	}
	public void setZeit(int zeit) {
		this.zeit = zeit;
	}
	public int getSchwierigkeit() {
		return schwierigkeit;
	}
	public void setSchwierigkeit(int schwierigkeit) {
		this.schwierigkeit = schwierigkeit;
	}
	public String getZubereitung() {
		return zubereitung;
	}
	public void setZubereitung(String zubereitung) {
		this.zubereitung = zubereitung;
	}
	public Map<Zutat,Integer> getZutaten() {
		return zutaten;
	}
	public void setZutaten(Map<Zutat,Integer> zutaten) {
		this.zutaten = zutaten;
	}
}
