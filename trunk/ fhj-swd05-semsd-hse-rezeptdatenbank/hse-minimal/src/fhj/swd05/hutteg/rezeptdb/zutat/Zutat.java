package fhj.swd05.hutteg.rezeptdb.zutat;

public class Zutat {
	private int id;
	private String name;
	private String einheit;
	private int energiemenge;
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
	public String getEinheit() {
		return einheit;
	}
	public void setEinheit(String einheit) {
		this.einheit = einheit;
	}
	public int getEnergiemenge() {
		return energiemenge;
	}
	public void setEnergiemenge(int energiemenge) {
		this.energiemenge = energiemenge;
	}
}
