package fhj.swd05.hutteg.rezeptdb.zutat;

import java.util.List;

public interface ZutatDAO {
	/**
	 * Liefert alle Zutaten in alphabetischer Reihenfolge zurueck
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getZutaten();
	
	
	/**
	 * Liefert eine Zutat anhand ihres Namens
	 * 
	 * @param name der Name der zu suchenden Zutat
	 * @return Zutat oder null wenn nciht gefunden
	 */
	public Zutat getZutat(String name);
	
	/**
	 * Liefert alle Zutaten die mit einem bestimmten String beginnen
	 * @param name Anfang der Zutat
	 * @return Liste der gefundenen Zutaten
	 */
	@SuppressWarnings("unchecked")
	public List getZutatStartingWith(String name);
	/**
	 * Refresh a Zutat with a version from permanent storage.
	 * 
	 * @param Zutat the Zutat to refresh.
	 * @return a reference to the Zutat passed in.
	 */
	public Zutat refresh(Zutat zutat);

	/**
	 * Create a new Zutat
	 * 
	 * @param Zutat the Zutat to create.
	 */
	public void create(Zutat zutat);

	/**
	 * Update a Zutat.
	 * 
	 * @param Zutat the Zutat to update.
	 */
	public void update(Zutat zutat);

	/**
	 * Delete a Zutat.
	 * 
	 * @param Zutat the Zutat to delete.
	 */
	public void delete(Zutat zutat);
}
