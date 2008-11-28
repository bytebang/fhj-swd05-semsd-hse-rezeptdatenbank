package fhj.swd05.hutteg.rezeptdb.rezept;

import java.util.List;

import fhj.swd05.hutteg.rezeptdb.mengenangabe.Mengenangabe;

public class Rezept {
	private int id;
	private String name;
	private int zeit;
	private int schwierigkeit;
	private String zubereitung;
	private List<Mengenangabe> bestandteile;
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
	public List<Mengenangabe> getBestandteile() {
		return bestandteile;
	}
	public void setBestandteile(List<Mengenangabe> bestandteile) {
		this.bestandteile = bestandteile;
	}
}
