package org.stenerud.hse.data.widget;

/**
 *  Hier werden businesspezifische Methoden implementiert
 *  sofern dies notwendig ist.
 *  
 *  In diesem Beispiel wird hier nur eine ID und ein NAME Feld angelegt.
 *  Damit kann gezeigt werden wie Hibernate, Spring, und Echo2 ... also HSE
 *  konfiguriert werden müssen um korrekt zu funktionieren.
 */
public class Widget {

	private int id;
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
