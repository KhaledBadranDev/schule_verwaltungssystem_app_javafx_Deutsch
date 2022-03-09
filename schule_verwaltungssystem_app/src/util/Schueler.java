package util;

/**
 * Diese Klasse stellt einen/eine Schueler*in dar
 * @author Khaled Badran
 * @version 1.00 - 06/02/2022
 */
public class Schueler {
	private int id; // Identifikationsnummer // z.b. 1
	private String vorname; // z.b. Max
	private String nachname; // z.b. Mstermann
	private int klasse; // klasse liegt zwischen 1 und 13 // z.b. 10
	private	double durchschnittsnote; // durchschnittsnote liegt zwischen 1.0 und 6.0 // z.b. 2.3

	// diese Felder werden automatisch generiert
	private String name; // name = vorname + nachname // z.b. Max Mstermann
	private boolean hatBestanden; // wenn der/die Schueler*in bstanden hat, dann true, sont false
	
	/**
     * Konstruktor Methode für die Schueler Klasse
     * 
     * @author Khaled Badran
     * 
	 * @param id ist die Identifikationsnummer eines Schuelers
	 * @param vorname ist der Vorname des Schuelers
	 * @param nachname ist der Nachname des Schuelers
	 * @param klasse ist die Klasse, in die der/die Schueler*in geht 
	 * @param durchschnittsnote ist die Durchschnittsnote von allen Faecher
	 */
	public Schueler(int id, String vorname, String nachname, int klasse, double durchschnittsnote) {
		this.id = id; 
		this.vorname = vorname; 
		this.nachname = nachname; 
		this.klasse = klasse; 
		this.durchschnittsnote = durchschnittsnote;
		setHatBestanden();
		setName();
	}
	
	/**
	 * Setter Methode für die id Member-Variable
     * @author Khaled Badran
	 * @param id ist die Identifikationsnummer eines Schuelers
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Setter Methode für die vorname Member-Variable
     * @author Khaled Badran
	 * @param vorname ist der Vorname des Schuelers
	 */
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	
	/**
	 * Setter Methode für die nachname Member-Variable
     * @author Khaled Badran
	 * @param nachname ist der nachname des Schuelers
	 */
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
	
	/**
	 * Setter Methode für die klasse Member-Variable
     * @author Khaled Badran
	 * @param klasse ist die Klasse, in die der/die Schueler*in geht
	 */
	public void setKlasse(int klasse) {
		this.klasse = klasse;
	}
	
	/**
	 * Setter Methode für die durchschnittsnote Member-Variable
     * @author Khaled Badran
	 * @param durchschnittsnote ist die Durchschnittsnote von allen Faecher
	 */
	public void setDurchschnittsnote(double durchschnittsnote) {
		this.durchschnittsnote = durchschnittsnote;
	}
	
	/**
	 * Setter Methode für die name Member-Variable.
	 * Die name Member-Variable stellt den vollstaendigen Namen dar.
     * @author Khaled Badran
	 */
	public void setName() {
		this.name = vorname + " " + nachname;
	}
	
	/**
	 * Setter Methode für die hatBestanden Member-Variable.
	 * Wenn der/die Schueler*in bstanden hat(also durchschnittsnote >= 1.0 und durchschnittsnote <= 4.0), dann true, sont false
     * @author Khaled Badran
	 */
	public void setHatBestanden() {
		if (durchschnittsnote >= 1.0 && durchschnittsnote <= 4.0) // zwischen 1.0 und 4.0 --> bestanden
			this.hatBestanden = true;
		else // sonst durchgefallen
			this.hatBestanden = false;
	}
	
	/**
	 * Getter Methode für die id Member-Variable
	 * @author Khaled Badran
	 * @return id - die Identifikationsnummer eines Schuelers
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Getter Methode für die vorname Member-Variable
	 * @author Khaled Badran
	 * @return vorname - Vorname des Schuelers
	 */
	public String getVorname() {
		return vorname;
	}
	
	/**
	 * Getter Methode für die nachname Member-Variable
	 * @author Khaled Badran
	 * @return nachname - nachname des Schuelers
	 */
	public String getNachname() {
		return nachname;
	}
	
	/**
	 * Getter Methode für die klasse Member-Variable
	 * @author Khaled Badran
	 * @return klasse - Klasse, in die der/die Schueler*in geht
	 */
	public int getKlasse() {
		return klasse;
	}
	
	/**
	 * Getter Methode für die durchschnittsnote Member-Variable
	 * @author Khaled Badran
	 * @return durchschnittsnote - die Durchschnittsnote von allen Faecher
	 */
	public double getDurchschnittsnote() {
		return durchschnittsnote;
	}
	
	/**
	 * Getter Methode für die name Member-Variable
	 * @author Khaled Badran
	 * @return name - der vollstaendige Name eines Schuelers (also Vor und Nachname)
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Getter Methode für die hatBestanden Member-Variable
	 * @author Khaled Badran
	 * @return hatBestanden - wenn der/die Schueler*in bstanden hat, dann true, sont false
	 */
	public boolean getHatBestanden() {
		return this.hatBestanden;
	}
	
	/**
	 * Überschreibt den Standard toString() Methode
	 * @author Khaled Badran
	 * @return Zeichenfolge, die in der Listenansicht angezeigt werden soll
	 */
	@Override
	public String toString() {
		return String.valueOf(id) + " " + name; // z.b. 1 Max Mstermann
	}
	
}
