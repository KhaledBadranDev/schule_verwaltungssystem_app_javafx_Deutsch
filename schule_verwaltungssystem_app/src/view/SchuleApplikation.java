package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import util.Schueler;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * Diese Klasse stellt die grafische Benutzeroberfläche von einem Schule-Verwaltungssystem dar.
 * @author Khaled Badran
 * @version 1.00 - 06/02/2022
 */
public class SchuleApplikation extends Application {
//	Erstellen/Initialisieren die erforderlichen Member-Variablen
//	Erstens layouts/panes
    private HBox wurzelHBox = new HBox(); // HBox layout als wurzel layout
    private VBox seitenleisteLinksVBox = new VBox(); // VBox layout für die seitenleiste links
    private GridPane schuelerAddOrEditGP = new GridPane(); // grid pane/layout um einen schueler hinzufuegen oder bearbeiten.
    private VBox listeVBox = new VBox(); // VBox layout für die list view
//  zweitens controls/components/widgets
    private Label schuelerListViewLabel = new Label("Schueler*innen Liste"); // label der list view
    private ListView<Schueler> schuelerListView = new ListView<>(); // die list view um die Schueler*innen zu zeigen.
    private ArrayList<Schueler> schuelerArrayList = new ArrayList<>();
    private FileChooser fc = new FileChooser(); //um es dem Benutzer zu ermöglichen, eine Datei hochzuladen, indem er den Computer durchsucht  // fc = fileChooser
    private DirectoryChooser dc = new DirectoryChooser(); // um es dem Benutzer zu ermöglichen, die Liste der SChueler*innen an einem bestimmten Ort zu speichern/exportieren, indem er das gewünschte Verzeichnis auswählt// dc = DirectoryChooser

    private Button[] seitenleisteButtons = {new Button("Schueler*in Hinzufuegen"), new Button("CSV Datei Importieren"),  new Button("Als CSV Datei Exportieren")}; // Buttons für seitenleiste
    private Control[] ausgewaehltSchuelerControls = { // alle erforderlichen Steuerelemente/controls zum Anzeigen und Bearbeiten eines Schuelers.
		new Button("Schueler*in Bearbeiten"), new Button("Schueler*in Loeschen"),
		new Label("Identifikationsnummer: "), new Label(),
		new Label("Vorname: "), new Label(),
		new Label("Nachname: "), new Label(),
		new Label("klasse: "), new Label(),
		new Label("Durchschnittsnote: "), new Label(),
		new Label("Hat Bestanden? "), new Label(),
		new Button("Bearbeitung Bestaetigen")		
	}; 
    private Control[] schuelerAddOrEditControls = { // alle erforderlichen Steuerelemente/controls zum Hinzufuegen eines Schuelers.
		new Label("Vorname: "), new TextField(),
		new Label("Nachname: "), new TextField(),
		new Label("Klasse: "), new TextField(),
		new Label("Durchschnittsnote: "), new TextField(), 
		new Button("Bestaetigen")		
	}; 

    
    /**
     * Dies ist die Main-Methode zum Starten der Anwendung.
     * 
     * @author Khaled Badran
     * @param args arguments, die beim Kompilieren des Programms angegeben werden
     */
	public static void main(String[] args) {		
		launch(args); // launch(args) ruft die start(Stage primaryStage) automatisch an  
	}

	/**
	 * Dies ist die Hauptschleife der JavaFX-Bibliothek, die die die grafische Benutzeroberfläche "GUI" immer aktualisiert wird.
     * @author Khaled Badran
     * @param primaryStage ist die haupt stage
     * @throws Exception
	 */
	@SuppressWarnings("exports")
	@Override
	public void start(Stage primaryStage) throws Exception{				
		initMainScene();    
        bindButtons();
//      new Scene(Node node, int width, int height);
        Scene mainScene = new Scene(wurzelHBox, 1000,  430); //  fuege die Wurzel HBox hinzu 

        primaryStage.setTitle("     Schule Verwaltungssystem");
        primaryStage.setScene(mainScene);
        primaryStage.show();
	}
	
	/**
	 * Initialisieren die erforderlichen Steuerelemente/controls und Layouts, um die mainscene zu zeigen
	 * @author Khaled Badran
	 */
    private void initMainScene(){
//      fuege elemente zu dem Wurzel hinzu
		wurzelHBox.getChildren().addAll(seitenleisteLinksVBox, schuelerAddOrEditGP, listeVBox); // adding the list of contacts    	

//      anpassen den listeVBox layout
		listeVBox.getChildren().addAll(schuelerListViewLabel, schuelerListView); // adding the list view and its label to the listeVBox pane/layout
    	listeVBox.setPadding(new Insets(10, 5, 5, 5));
		schuelerListViewLabel.setPadding(new Insets(0, 100, 4, 100));

//      anpassen den seitenleisteLinksVBox layout	
//      Hinzufügen von Polsterung um die Innenkanten von seitenleisteLinksVBox // new Insets(TOP, RIGHT, BOTTOM, LEFT)
    	seitenleisteLinksVBox.setPadding(new Insets(30, 5, 30, 5));
//    	Hinzufügen von Abständen zwischen den Elementen innerhalb seitenleisteLinksVBox
        seitenleisteLinksVBox.setSpacing(20);
		for (Button btn : seitenleisteButtons) { // Hinzufügen aller seitenleisteButtons zum seitenleisteLinksVBox Layout
			btn.setPrefWidth(200);
//			new Insets(TOP, RIGHT, BOTTOM, LEFT)
			btn.setPadding(new Insets(8, 0, 8, 0));
			seitenleisteLinksVBox.getChildren().add(btn);	
		}
// 		ändere die Hintergrundfarbe von seitenleisteLinksVBox layout
		seitenleisteLinksVBox.setBackground(new Background(new BackgroundFill(Color.HOTPINK, CornerRadii.EMPTY, Insets.EMPTY)));
		
//		Verstecken den schuelerAddOrEditGP in der mainScene
		schuelerAddOrEditGP.setVisible(false);

    }
    
	/**
	 * Initialisieren die erforderlichen Steuerelemente/controls und Layouts,
	 * um einen Schueler hinzuzufuegen
	 * @author Khaled Badran
	 */
    private void initAddSchuelerScene() {
        aktualisiereListView();
    	resetschuelerAddOrEditGP();
		schuelerAddOrEditGP.setVisible(true);
        
        int row = 0;
        for(int i = 0; i < schuelerAddOrEditControls.length-1; i++, row++) { 
//    		anyGridPane.add(Node child, int indexColumn, int indexRow)
        	schuelerAddOrEditGP.add(schuelerAddOrEditControls[i], 0, row);
        	i++;
        	schuelerAddOrEditGP.add(schuelerAddOrEditControls[i], 1, row);
        }
//		anyGridPane.add(Node child, int indexColumn, int indexRow, int colspan, int rowspan)
    	schuelerAddOrEditGP.add(schuelerAddOrEditControls[schuelerAddOrEditControls.length-1], 0, row , 2, 1);

    }
    
    
	/**
	 * Initialisieren die erforderlichen Steuerelemente/controls und Layouts,
	 * um einen Schueler zu bearbeiten
	 * @author Khaled Badran
	 */
    private void initBearbeiteSchuelerScene() {
     	((Button)ausgewaehltSchuelerControls[0]).setDisable(true); // deaktivieren "Schueler*in Bearbeiten" Button
		int row = 8;
        for(int i = 0; i < schuelerAddOrEditControls.length-1; i++, row++) { 
//    		anyGridPane.add(Node child, int indexColumn, int indexRow)
        	schuelerAddOrEditGP.add(schuelerAddOrEditControls[i], 0, row);
        	i++;
        	schuelerAddOrEditGP.add(schuelerAddOrEditControls[i], 1, row);
        }
//		anyGridPane.add(Node child, int indexColumn, int indexRow, int colspan, int rowspan)
    	schuelerAddOrEditGP.add(ausgewaehltSchuelerControls[ausgewaehltSchuelerControls.length-1], 0, row , 2, 1);
    }

	/**
	 * Initialisieren die erforderlichen Steuerelemente/Controls und Layouts,
	 * um die Details des ausgewählten Schuelers aus der List view zu zeigen
	 * @author Khaled Badran
	 * @param ausgewaehltSchueler ist der/die ausgewählte Schueler*in aus der list view
	 */
    private void initausgewaehltSchuelerScene(Schueler ausgewaehltSchueler) {
    	resetschuelerAddOrEditGP();
		schuelerAddOrEditGP.setVisible(true);
        for(int i = 0, row = 0; i < ausgewaehltSchuelerControls.length-1; i++, row++) { 
//    		anyGridPane.add(Node child, int indexColumn, int indexRow)
        	schuelerAddOrEditGP.add(ausgewaehltSchuelerControls[i], 0, row);
        	i++;	
    		if (i==3) ((Label)ausgewaehltSchuelerControls[i]).setText(String.valueOf(ausgewaehltSchueler.getId()));
    		if (i==5) ((Label)ausgewaehltSchuelerControls[i]).setText(ausgewaehltSchueler.getVorname());
    		else if (i==7) ((Label)ausgewaehltSchuelerControls[i]).setText(ausgewaehltSchueler.getNachname());
    		else if (i==9) ((Label)ausgewaehltSchuelerControls[i]).setText(String.valueOf(ausgewaehltSchueler.getKlasse()));
    		else if (i==11) ((Label)ausgewaehltSchuelerControls[i]).setText(String.valueOf(ausgewaehltSchueler.getDurchschnittsnote()));
    		else if (i==13) ((Label)ausgewaehltSchuelerControls[i]).setText(String.valueOf(ausgewaehltSchueler.getHatBestanden()));
        	schuelerAddOrEditGP.add(ausgewaehltSchuelerControls[i], 1, row);
        }
    }
    
	/**
	 * Binden alle Buttons/Schaltflächen mit allen entsprechenden Methoden
	 * @author Khaled Badran
	 */
    private void bindButtons() {
//		seitenleisteButtons
    	seitenleisteButtons[0].setOnAction(event -> initAddSchuelerScene()); // binden "Schueler*in Hinzufuegen" Button
    	seitenleisteButtons[1].setOnAction(event -> importFile()); // binden "CSV Datei Importieren" Button
    	seitenleisteButtons[2].setOnAction(event -> exportFile()); // binden "Als CSV Datei Exportieren" Button
    	((Button)schuelerAddOrEditControls[schuelerAddOrEditControls.length-1]).setOnAction(event -> schuelerAbsenden(null)); // binden "Bestaetigen" Button um einen Schueler hinzufuegen
    	    	
//    	zur Auswahl eines Schuelers aus der list view
    	schuelerListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Schueler>() {
            @Override
            public void changed(ObservableValue<? extends Schueler> arg0, Schueler arg1, Schueler ausgewaehltSchueler) {
                if (ausgewaehltSchueler != null) {
                	initausgewaehltSchuelerScene(ausgewaehltSchueler);
                	((Button)ausgewaehltSchuelerControls[0]).setOnAction(event -> initBearbeiteSchuelerScene()); // bind "Schueler*in Bearbeiten" Button
                	((Button)ausgewaehltSchuelerControls[1]).setOnAction(event -> deleteausgewaehltSchueler(ausgewaehltSchueler)); // bind "Schueler*in Loeschen" Button
                	((Button)ausgewaehltSchuelerControls[ausgewaehltSchuelerControls.length-1]).setOnAction(event -> schuelerAbsenden(ausgewaehltSchueler)); // bind "Bearbeitung Bestaetigen" Button um einen Schueler zu bearbeiten
                }
            }
        });
    }
    
	/**
	 * exportieren die list view vonSchueler*innen als ein csv Datei
	 * @author Khaled Badran
	 */
    private void exportFile() {
    	File CSVFilesDir = new File("src");
    	dc.setInitialDirectory(CSVFilesDir);
    	File desiredDir = dc.showDialog(null); // Pfad des gewünschten Verzeichnisses
    	if (desiredDir != null) { // Schreibe/exportiere die Daten der Listview bzw. SchuelerArrayList in eine *.csv Datei
    		try {
        		String dateAndTimeNow = String.valueOf(LocalDateTime.now()).replaceAll(":", "-"); //  Ändern des Formats der Daten e.g. von "2015-02-20T06:30:00" zu "2015-02-20T06-30-00"
				PrintWriter writer = new PrintWriter(desiredDir + "//" + "exportiert_Schueler-innen" + dateAndTimeNow + ".csv"); // der erstellte Dateiname sollte folgedermassen aussehen:  exportiert_<<aktuelles Datum und Uhrzeit>>
				
				// die erste Zeile(alse die headers Zeile) ist: Id,Vorname,Nachname,Klasse,Durchschnittsnote,HatBestanden
    			writer.print("Id"+","+"Vorname"+","+"Nachname"+","+"Klasse"+","+"Durchschnittsnote"+","+"HatBestanden");
    			writer.println();
	    		for (Schueler schueler: schuelerArrayList) {
	    			writer.print(schueler.getId()+",");
	    			writer.print(schueler.getVorname()+",");
	    			writer.print(schueler.getNachname()+",");
	    			writer.print(schueler.getKlasse()+",");
	    			writer.print(schueler.getDurchschnittsnote()+",");
	    			writer.print(schueler.getHatBestanden());
	    			writer.println();
	    		}
				resetschuelerAddOrEditGP();
				writer.close(); // Schließen den PrintWriter
	            Alert errorAlert = new Alert(AlertType.CONFIRMATION);
	            errorAlert.setHeaderText("Exportieren Status");
	            errorAlert.setContentText("Exportieren War Erfolgreich");
	            errorAlert.showAndWait();
			} catch (Exception e) {
	            Alert errorAlert = new Alert(AlertType.ERROR);
	            errorAlert.setHeaderText("Exportieren Status");
	            errorAlert.setContentText("Exportieren Hat Gescheitert");
	            errorAlert.showAndWait();
				e.printStackTrace();
			}
    	}
    }
    
	/**
	 * importieren ein csv Datei
	 * @author Khaled Badran
	 */
    private void importFile() {
    	File CSVFilesDir = new File("src\\resources_dateien");
    	ExtensionFilter allowedExtension = new ExtensionFilter("CSV Datei", "*.csv");
    	fc.setInitialDirectory(CSVFilesDir); // um das gewünschte Verzeichnis automatisch zu öffnen
    	fc.getExtensionFilters().add(allowedExtension);
    	fc.setTitle("Auswählen CSV-Datei fürs Schule-Verwaltungssystem"); // Festlegen eines Titels für das Dateiauswahlfenster
    	File importiertDatei = fc.showOpenDialog(null); // Pfad von dem importierten Datei
    	String line = ""; // das wäre die geparste Zeile aus der *.csv-Datei
    	BufferedReader parser;
    	if (importiertDatei != null) {
			try { // Öffnen der *.csv-Datei
				parser = new BufferedReader(new FileReader(importiertDatei));
				try { 
					int lineIndex = 0;
					while((line = parser.readLine()) != null) {
						if (lineIndex > 0) { // um die erste Zeile (alse die headers Zeile) zu ignorieren.
							String[] row = line.split(","); //e.g. String[] row = [id,vorname,nachname,klasse,durchschnittsnote]
//					        Speichern der geparsten Daten in einem Schueler-Object und fügen dieses zur list view hinzu.
							int id = Integer.valueOf(row[0]);
					    	String vorname = row[1];
					    	String nachname = row[2];
					    	int klasse = Integer.valueOf(row[3]);
					    	double durchschnittsnote = Double.valueOf(row[4]);
				            Schueler neuSchueler = new Schueler(id, vorname, nachname, klasse, durchschnittsnote);
				            schuelerArrayList.add(neuSchueler);	
						} else {
							lineIndex++;
						}
					}
		            aktualisiereListView();
					resetschuelerAddOrEditGP();
		            Alert errorAlert = new Alert(AlertType.CONFIRMATION);
		            errorAlert.setHeaderText("Importieren Status");
		            errorAlert.setContentText("Importieren War Erfolgreich");
		            errorAlert.showAndWait();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
	            Alert errorAlert = new Alert(AlertType.ERROR);
	            errorAlert.setHeaderText("Importieren Status");
	            errorAlert.setContentText("Importieren Hat Gescheitert");
	            errorAlert.showAndWait();
				e.printStackTrace();
			}
    	}
    }    
    
	/**
	 * Fuege einen Schueler hinzu oder bearbeite einen Schueler
	 * @author Khaled Badran
	 * @param ausgewaehltSchueler ist der/die ausgewählte Schueler*in aus der list view
	 */
    private void schuelerAbsenden(Schueler ausgewaehltSchueler) {    	
    	String vorname;
    	String nachname;
    	int klasse;
    	double durchschnittsnote;
//    	um sicher zu stellen, dass die Datentypen der Eingabe korrekt sind.
    	try { 
    		vorname =  ((TextField)schuelerAddOrEditControls[1]).getText();
    		nachname =  ((TextField)schuelerAddOrEditControls[3]).getText();
    		if (vorname.length() < 2)  throw new Exception();
    		if (nachname.length() < 2)  throw new Exception();
		} catch(Exception e){  
	        Alert errorAlert = new Alert(AlertType.ERROR);
	        errorAlert.setHeaderText("Input-Fehler");
	        errorAlert.setContentText("Vorname/Nachname muss mindestens aus 2 Buchstaben bestehen");
	        errorAlert.showAndWait();
	        return;
    	}
        try {
        	klasse = Integer.valueOf(((TextField)schuelerAddOrEditControls[5]).getText());
        	if (klasse < 1 || klasse > 13) throw new Exception();
        }
        catch(Exception e){  
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setContentText("Klasse muss zwischen 1 und 13 sein.");
            errorAlert.showAndWait();
            return;
        }  
        try { 
        	durchschnittsnote = Double.valueOf(((TextField)schuelerAddOrEditControls[7]).getText());
        	if (durchschnittsnote < 1.0 || durchschnittsnote > 6.0) throw new Exception();
        }
        catch(Exception e){
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setContentText("Durchschnittsnote muss zwischen 1.0 und 6.0 sein.");
            errorAlert.showAndWait();
            return;
        }
        
        if (ausgewaehltSchueler!=null){
        	int ausgewaehltSchuelerIndex = schuelerArrayList.indexOf(ausgewaehltSchueler);
        	schuelerArrayList.set(ausgewaehltSchuelerIndex, new Schueler(0, vorname, nachname, klasse, durchschnittsnote));  
        } else {
            Schueler neuSchueler = new Schueler(0, vorname, nachname, klasse, durchschnittsnote);
            schuelerArrayList.add(neuSchueler);
        }
    	
        aktualisiereId();
        aktualisiereListView();
        resetschuelerAddOrEditGP();
        resetTextFields();
    }
	 
	/**
	 * Lösche den ausgewählten Schueler von der list view
     * @author Khaled Badran
	 * @param ausgewaehltSchueler ist der/die ausgewählte Schueler*in aus der list view
	 */
    private void deleteausgewaehltSchueler(Schueler ausgewaehltSchueler) {
        schuelerArrayList.remove(ausgewaehltSchueler);
        aktualisiereId();
        aktualisiereListView(); 
        resetschuelerAddOrEditGP();
    }
    
	/**
	 * Zurücksetzen alle erforderlichen Steuerelemente/Controls und Layouts, die die Schueler*innen zeigen.  
	 * @author Khaled Badran
	 */
    private void resetschuelerAddOrEditGP() {
    	wurzelHBox.getChildren().remove(1);
    	schuelerAddOrEditGP = new GridPane();
    	wurzelHBox.getChildren().add(1, schuelerAddOrEditGP);
//      Anpassen den schuelerAddOrEditGP layout
        schuelerAddOrEditGP.setPadding(new Insets(30, 5, 30, 5));
//    	Hinzufügen von Abständen zwischen den Elementen innerhalb schuelerAddOrEditGP
        schuelerAddOrEditGP.setHgap(10);
        schuelerAddOrEditGP.setVgap(10);
        
//      Anpassen das Ausehen von manchen Buttons.
		ausgewaehltSchuelerControls[0].setPrefWidth(150); // ausgewaehltSchuelerControls[0] ist der "Schueler*in Bearbeiten" Button
		ausgewaehltSchuelerControls[0].setPadding(new Insets(5, 0, 5, 0));
		ausgewaehltSchuelerControls[1].setPrefWidth(150);// ausgewaehltSchuelerControls[1] ist der "Schueler*in Loeschen" Button
		ausgewaehltSchuelerControls[1].setPadding(new Insets(5, 0, 5, 0));
		schuelerAddOrEditControls[schuelerAddOrEditControls.length-1].setPrefWidth(320); // schuelerAddOrEditControls[schuelerAddOrEditControls.length-1] ist der "Bestaetigen" Button
		schuelerAddOrEditControls[schuelerAddOrEditControls.length-1].setPadding(new Insets(5, 0, 5, 0));
		ausgewaehltSchuelerControls[ausgewaehltSchuelerControls.length-1].setPrefWidth(320);
		ausgewaehltSchuelerControls[ausgewaehltSchuelerControls.length-1].setPadding(new Insets(5, 0, 5, 0));
     	((Button)ausgewaehltSchuelerControls[0]).setDisable(false); // aktiviere den "Schueler*in Bearbeiten" Button

		schuelerAddOrEditGP.setVisible(false);
//	 	ändere die Hintergrundfarbe von schuelerAddOrEditGP Layout
		schuelerAddOrEditGP.setBackground(new Background(new BackgroundFill(Color.PINK, CornerRadii.EMPTY, Insets.EMPTY)));
		schuelerAddOrEditGP.setVisible(false);
    }
    
	/**
	 * Zurücksetzen alle TextFields
	 * @author Khaled Badran
	 */
    private void resetTextFields(){
        for(int i = 1; i < schuelerAddOrEditControls.length; i+=2)  
            ((TextField)schuelerAddOrEditControls[i]).clear();
    }

    
	/**
	 * aktualisiere die list view mit allen Schueler und SChuelerinnen von dem SchulerArrayList.
	 * @author Khaled Badran
	 */
    private void aktualisiereListView(){
        schuelerListView.getItems().clear(); 
        for (Schueler schueler: schuelerArrayList)
        	schuelerListView.getItems().add(schueler);
    }
    
    
	/**
	 * aktualisiere die Identifikationsnummer aller Schueler*innen
	 * @author Khaled Badran
	 */
    private void aktualisiereId() {
        for (Schueler schueler: schuelerArrayList) {
        	int id = schuelerArrayList.indexOf(schueler)+1;
        	schueler.setId(id);
        }
    }

}
