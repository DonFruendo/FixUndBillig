package fixundbillig.sendungsverwaltung.view;

import fixundbillig.sendungsverwaltung.core.control.SendungManager;
import fixundbillig.sendungsverwaltung.core.factory.SendungsverwaltungFactory;
import fixundbillig.sendungsverwaltung.data.interfaces.ISendungAnlegen;
import fixundbillig.sendungsverwaltung.data.packstueck.PackstueckTO;
import fixundbillig.sendungsverwaltung.data.sendung.SendungTO;
import fixundbillig.sendungsverwaltung.data.utils.Adresse;
import fixundbillig.sendungsverwaltung.data.utils.Paketart;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GUIMain extends Application {

    private Stage fenster;
    private Scene start, sAnlegen, sVerfolgen;
    //private ObservableList<PackstueckTO> tableList = FXCollections.observableArrayList();
    private List<PackstueckTO> tableList = new ArrayList<>();
    private String sNummerString;

    public static void main(String[] args){launch(args);}

    @Override
    public void start(Stage primaryStage) throws Exception {
        SendungsverwaltungFactory fact = SendungsverwaltungFactory.getInstance();
        fenster = primaryStage;
        fenster.setTitle("Fix und Billig Sendungsverwaltung");
        fenster.setOnCloseRequest(e -> {
            e.consume();
            closeProgram(fact);
        });


        // --- START SCENE ---
        // --- Main Elements ---
        Button sAnlegenBtn = new Button("Sendung anlegen");
        sAnlegenBtn.setOnAction(e -> {
            sNummerString = SendungManager.generateSendungsnummer(); // Methode zur Sendumgsnummer Generierung einfügen!
            fenster.setScene(sAnlegen);
        });
        Button sVerfolgungBtn = new Button("Sendung suchen");
        sVerfolgungBtn.setOnAction(e -> fenster.setScene(sVerfolgen));

        // --- Start Layout ---
        VBox layoutStart = new VBox();
        layoutStart.getChildren().addAll(sAnlegenBtn,sVerfolgungBtn);
        layoutStart.setAlignment(Pos.CENTER);

        start = new Scene(layoutStart,200,150);


        // --- ANLEGEN SCENE ---
        // --- Main Elements ---
        Label titleSNummer = new Label("Sendungsnummer: ");
        Label dateSNummer = new Label();
        Label titleSDatum = new Label("Anlagedatum: ");
        Label dateSDatum = new Label();
        Label titleSAdresse = new Label("Zieladresse: ");
        TextField sStrasseField = new TextField();
        sStrasseField.setPromptText("Straße");
        TextField sHausNr = new TextField();
        sHausNr.setPromptText("Hausnummer");
        TextField sPlzField = new TextField();
        sPlzField.setPromptText("Postleitzahl");
        TextField sOrtField = new TextField();
        sOrtField.setPromptText("Ort");
        Label titleSKundenNr = new Label("Kundennummer: ");
        TextField sKundenNrField = new TextField();
        Label titleTranspAuftrag = new Label("Transportauftrag");
        TextField sTransAuftragField = new TextField();
        Button sSpeichernBtn = new Button("Speichern");
        Button sAbbrechenBtn = new Button("Abbrechen");
        Button sPackstueckBtn = new Button("Packstück anlegen");

        sAbbrechenBtn.setOnAction(e ->{
            Boolean abbrechen = GUIBestaetigungsBox.display("Vorgang abbrechen",
                    "Sind Sie sicher, dass Sie den Vorgang abbrechen möchten?");
            if(abbrechen)
                sNummerString = null;
                //tableList.clear();
                tableList = null;
                fenster.setScene(start);
        });

        // --- Bei Scene Aufruf ---
        dateSNummer.setText(sNummerString);
        dateSDatum.setText(getCurrentDate());

        // --- Packstück Tabelle ---
        // --- Columns ---
        TableColumn<PackstueckTO, String> idColumn = new TableColumn<>("Packstück ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<PackstueckTO, Integer> refnrColumn = new TableColumn<>("Referenznummer");
        refnrColumn.setCellValueFactory(new PropertyValueFactory<>("refnr"));
        TableColumn<PackstueckTO, Paketart> paketartColumn = new TableColumn<>("Paketart");
        paketartColumn.setCellValueFactory(new PropertyValueFactory<>("paketart"));
        TableColumn<PackstueckTO, Double> volumenColumn = new TableColumn<>("Volumen");
        volumenColumn.setCellValueFactory(new PropertyValueFactory<>("volumen"));
        TableColumn<PackstueckTO, Double> gewichtColumn = new TableColumn<>("Gewicht");
        gewichtColumn.setCellValueFactory(new PropertyValueFactory<>("gewicht"));
        TableColumn<PackstueckTO, String> lagerortColumn = new TableColumn<>("Lagerort");
        lagerortColumn.setCellValueFactory(new PropertyValueFactory<>("lagerort"));

        // --- Table Aufbau ---
        TableView<PackstueckTO> sPackstuekListe = new TableView<>();
        sPackstuekListe.getColumns().addAll(idColumn,refnrColumn,paketartColumn,volumenColumn,gewichtColumn,lagerortColumn);

        // --- Speichern Call ---
        sSpeichernBtn.setOnAction(e -> {
            if (tableList != null) {
                Adresse adresse = new Adresse(sStrasseField.getText().trim(),sHausNr.getText().trim(), sPlzField.getText().trim(), sOrtField.getText().trim());
                boolean ergebnis = adresse.isValid();
                if (ergebnis) {
                    boolean antwort = GUIBestaetigungsBox.display("Sendung anlegen", "Sind Sie sicher, dass Sie die Sendung anlegen möchten?");
                    if (antwort) {
                        List<PackstueckTO> sPacktuecke = new ArrayList<>();
                        for (PackstueckTO pTO : tableList) {
                            sPacktuecke.add(pTO);
                        }
                        sendungSpeichern(/*dateSNummer.getText()*/sNummerString, adresse, sKundenNrField.getText(), sTransAuftragField.getText(), sPacktuecke, fact);
                    }
                } else {
                    GUIFehlerBox.display("Keine gültige Adresse", "Die eingegebene Adresse ist nicht gültig.");
                }
            } else {
               GUIFehlerBox.display("Keine Packstücke angelegt", "Sie können keine Sendung anlegen, der keine Packstücke zugewiesen sind!");
            }
        });

        // --- Paket anlegen Call ---
        sPackstueckBtn.setOnAction(e -> {
            PackstueckTO neuesPackstueck = GUIPackstueckErstellen.display(sNummerString);
            tableList.add(neuesPackstueck);
            //ObservableList<PackstueckTO> packstueckListe = FXCollections.observableArrayList(tableList);
            //sPackstuekListe.setItems(packstueckListe);
            sPackstuekListe.getItems().add(neuesPackstueck);
        });

        // --- Anlegen Layout ---
        GridPane autoFillGrid = new GridPane();
        autoFillGrid.setConstraints(titleSNummer,0,0);
        autoFillGrid.setConstraints(dateSNummer,1,0);
        autoFillGrid.setConstraints(titleSDatum,0,1);
        autoFillGrid.setConstraints(dateSDatum,1,1);
        autoFillGrid.setConstraints(titleSKundenNr,0,3);
        autoFillGrid.setConstraints(sKundenNrField,1,3);
        autoFillGrid.setConstraints(titleTranspAuftrag,0,4);
        autoFillGrid.setConstraints(sTransAuftragField,1,4);
        autoFillGrid.getChildren().addAll(titleSNummer,dateSNummer,titleSDatum,dateSDatum,titleSKundenNr,sKundenNrField,titleTranspAuftrag,sTransAuftragField);

        GridPane adresseGrid = new GridPane();
        adresseGrid.setConstraints(titleSAdresse,0,0);
        adresseGrid.setConstraints(sStrasseField,0,2);
        adresseGrid.setConstraints(sHausNr, 1, 2);
        adresseGrid.setConstraints(sPlzField,0,3);
        adresseGrid.setConstraints(sOrtField,1,3);
        adresseGrid.getChildren().addAll(titleSAdresse,sStrasseField,sHausNr,sPlzField,sOrtField);

        VBox anlegenTop = new VBox();
        anlegenTop.getChildren().addAll(autoFillGrid,adresseGrid);

        VBox anlegenPackstueck = new VBox();
        anlegenPackstueck.getChildren().addAll(sPackstueckBtn,sPackstuekListe);

        HBox anlegenButtons = new HBox();
        anlegenButtons.getChildren().addAll(sSpeichernBtn,sAbbrechenBtn);

        VBox anlegenLayout = new VBox();
        anlegenLayout.getChildren().addAll(anlegenTop,anlegenPackstueck,anlegenButtons);

        sAnlegen = new Scene(anlegenLayout,720,480);


        // --- SUCHEN SCENE ---
        // --- Main Elements ---
        TextField sNummerTxtField = new TextField();
        sNummerTxtField.setPromptText("Sendungsnummer eingeben");
        Button sSuchenBtn = new Button("Sendung suchen");
        Button sSucheAbbrechenBtn = new Button("Abbrechen");

        sSucheAbbrechenBtn.setOnAction(e ->{
            Boolean abbrechen = GUIBestaetigungsBox.display("Vorgang abbrechen",
                    "Sind Sie sicher, dass Sie den Vorgang abbrechen möchten?");
            if(abbrechen)
                fenster.setScene(start);
        });

        // --- Packstück Table ---
        // Table Aufbau mit früher definierten Columns
        TableView<PackstueckTO> pSucheTable = new TableView<>();
        pSucheTable.getColumns().addAll(idColumn,refnrColumn,paketartColumn,volumenColumn,gewichtColumn,lagerortColumn);

        // --- Sendungsdaten Anzeige ---
        Label titleAnlagedatum = new Label("Anlagedatum: ");
        Label titleZielort = new Label("Empfängeradresse: ");
        Label titleKundenNr = new Label("Kundennummer: ");
        Label titleTransAuftrag = new Label("Transportauftrag: ");

        Label datenAnlagedatum = new Label();
        Label datenZielortStrasse = new Label();
        Label datenZielortStadt = new Label();
        Label datenKundenNr = new Label();
        Label datenTransAuftrag = new Label();

        // --- Search Call --- Die Tabelle wird durch Weitergeben der eingegebenen Sendungsnummer befüllt
        sSuchenBtn.setOnAction(e -> {
            SendungTO sendung = fact.getSendungSuchen().sendungSuchen(sNummerTxtField.getText());
            pSucheTable.getItems().clear();
            getSendungDetails(sendung,datenAnlagedatum,datenZielortStrasse,datenZielortStadt,datenKundenNr,datenTransAuftrag);
            pSucheTable.setItems(getPackstueckDetails(sendung));
            System.out.println(sendung.packstuecke);
        });

        // --- Suchen Layout ---
        VBox sendungsSuche = new VBox();
        sendungsSuche.getChildren().addAll(sNummerTxtField,sSuchenBtn,sSucheAbbrechenBtn);

        GridPane sendungsInfos = new GridPane();
        sendungsInfos.setConstraints(titleAnlagedatum,0,0);
        sendungsInfos.setConstraints(titleZielort,0,1);
        sendungsInfos.setConstraints(titleKundenNr,0,3);
        sendungsInfos.setConstraints(titleTransAuftrag,0,4);

        sendungsInfos.setConstraints(datenAnlagedatum,1,0);
        sendungsInfos.setConstraints(datenZielortStrasse,1,1);
        sendungsInfos.setConstraints(datenZielortStadt,1,2);
        sendungsInfos.setConstraints(datenKundenNr,1,3);
        sendungsInfos.setConstraints(datenTransAuftrag,1,4);
        sendungsInfos.getChildren().addAll(titleAnlagedatum,titleZielort,titleKundenNr,titleTransAuftrag,
                datenAnlagedatum,datenZielortStrasse,datenZielortStadt,datenKundenNr,datenTransAuftrag);

        AnchorPane sSucheLinks = new AnchorPane();
        sSucheLinks.getChildren().addAll(sendungsSuche,sendungsInfos);
        sSucheLinks.setTopAnchor(sendungsSuche, 10.0);
        sSucheLinks.setBottomAnchor(sendungsInfos, 10.0);

        BorderPane sSucheLayout = new BorderPane();
        sSucheLayout.setLeft(sSucheLinks);
        sSucheLayout.setCenter(pSucheTable);

        sVerfolgen = new Scene(sSucheLayout, 720, 480);


        // --- On Program Start ---
        fenster.setScene(start);
        fenster.centerOnScreen();
        fenster.show();
    }

    private void closeProgram(SendungsverwaltungFactory fact){
        Boolean schließen = GUIBestaetigungsBox.display("Programm Beenden?", "Sind Sie sicher, dass Sie das Programm beenden möchten?");
        if(schließen)
            fact.destroy();
            fenster.close();
    }

    // Fetch Packstück Details für Tabelle
    public ObservableList<PackstueckTO> getPackstueckDetails(SendungTO sendung){
        ObservableList<PackstueckTO> packstuecke = FXCollections.observableArrayList(sendung.packstuecke);
        return packstuecke;
    }
    // Fetch Sendungs Details für Anzeige
    public void getSendungDetails(SendungTO sendung, Label anlagedatum, Label zielortstr, Label zielortstadt, Label kundenNr, Label transAuftrag){
        anlagedatum.setText(sendung.anlagedatum.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        zielortstr.setText(sendung.zielort.getStrasse() + " " + sendung.zielort.getHausnummer());
        zielortstadt.setText(sendung.zielort.getPlz() + " " + sendung.zielort.getOrt());
        kundenNr.setText(sendung.kundenNr);
        transAuftrag.setText(sendung.transportauftrag);
    }

    private String getCurrentDate(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    // Sendungsdaten weitergeben an Datenbank
    public void sendungSpeichern(String sendungsnummer, Adresse adresse,
                                 String kundenNr, String transauftrag, List<PackstueckTO> packstuecke, SendungsverwaltungFactory  fact){
        SendungTO sendung = new SendungTO();
        sendung.anlagedatum = LocalDate.now();
        sendung.kundenNr = kundenNr;
        sendung.sendungsnummer = sendungsnummer;
        sendung.transportauftrag = transauftrag;
        sendung.zielort = adresse;
        sendung.packstuecke = packstuecke;

        ISendungAnlegen sendungAnlegen = fact.getSendungAnlegen();
        sendungAnlegen.sendungAnlegen(sendung);
        boolean success = sendungAnlegen.sendungAnlegen(sendung);
        if (success){
            GUIFehlerBox.display("Sendung erfolgreich angelegt", "Die Sendung wurde erfolgreich angelegt.");
        } else {
            GUIFehlerBox.display("Sendung angelegen fehlgeschlagen", "Die Sendung wurde nicht angelegt.");
        }

        tableList.clear();
        sNummerString = null;
        fenster.setScene(start);
    }
}
