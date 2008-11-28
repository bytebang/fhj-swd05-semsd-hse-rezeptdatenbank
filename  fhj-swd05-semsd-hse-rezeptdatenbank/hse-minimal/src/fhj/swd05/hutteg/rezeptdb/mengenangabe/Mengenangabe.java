package fhj.swd05.hutteg.rezeptdb.mengenangabe;

import fhj.swd05.hutteg.rezeptdb.zutat.Zutat;

public class Mengenangabe {
	private int id;
	private int menge;
	private Zutat zutat;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMenge() {
		return menge;
	}
	public void setMenge(int menge) {
		this.menge = menge;
	}
	public Zutat getZutat() {
		return zutat;
	}
	public void setZutat(Zutat zutat) {
		this.zutat = zutat;
	}
}
