package fhj.swd05.hutteg.rezeptdb.rezept;

import java.util.List;

public interface RezeptDAO {
	/**
	 * Liefert alle Zutaten in alphabetischer Reihenfolge zurueck
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getRezepte();
	
	
	/**
	 * Liefert eine Zutat anhand ihres Namens
	 * 
	 * @param name der Name der zu suchenden Zutat
	 * @return Zutat oder null wenn nciht gefunden
	 */
	public Rezept getRezept(String name);
	
	/**
	 * Liefert alle Rezepte die mit einem bestimmten String beginnen
	 * @param name Anfang der Zutat
	 * @return Liste der gefundenen Zutaten
	 */
	@SuppressWarnings("unchecked")
	public List getRezeptStartingWith(String name);
	/**
	 * Refresh a Rezept with a version from permanent storage.
	 * 
	 * @param Zutat the Rezept to refresh.
	 * @return a reference to the Rezept passed in.
	 */
	public Rezept refresh(Rezept rezept);

	/**
	 * Create a new Rezept
	 * 
	 * @param Rezept the Rezept to create.
	 */
	public void create(Rezept rezept);

	/**
	 * Update a Rezept.
	 * 
	 * @param Zutat the Rezept to update.
	 */
	public void update(Rezept rezept);

	/**
	 * Delete a Rezept.
	 * 
	 * @param Zutat the Rezept to delete.
	 */
	public void delete(Rezept rezept);
}
