package fixundbillig.sendungsverwaltung.view;

import fixundbillig.sendungsverwaltung.core.control.PackstueckManager;
import fixundbillig.sendungsverwaltung.data.packstueck.PackstueckTO;
import fixundbillig.sendungsverwaltung.data.utils.Paketart;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

class GUIPackstueckErstellen {

    private static PackstueckTO packstueck;

    static PackstueckTO display(String sendungsnummer){
        Stage fenster = new Stage();
        fenster.initModality(Modality.APPLICATION_MODAL);
        fenster.setTitle("Neues Packstück anlegen");
        fenster.setMinWidth(360);

        // --- Main Elements ---
        Label titleSendungsnummer = new Label("Sendungsnummer: ");
        Label dateSendungsnummer = new Label(sendungsnummer);


        Label titleId = new Label("Packstück ID: ");
        Label dateId = new Label();

        Label titleReferenzNr = new Label("Referenznummer: ");
        TextField dateReferenzNr = new TextField();
        Label titleLagerort = new Label("Lagerort: ");
        TextField datenLagerort = new TextField();
        datenLagerort.setPromptText("Lagerort ID");
        TextField gewicht = new TextField();
        gewicht.setPromptText("Gewicht");
        TextField volumen = new TextField();
        volumen.setPromptText("Volumen");
        ComboBox<Paketart> pArt = new ComboBox<>();
        pArt.setPromptText("Paketart auswählen");
        Button speichern = new Button("Speichern");
        Button abbrechen = new Button("Abbrechen");

        pArt.getItems().addAll(Paketart.values());

        dateId.setText(PackstueckManager.generatePackstueckID()); //  Methode zum generieren einfügen
        // --- Button Calls ---


        speichern.setOnAction(e -> { //  Fehler weil dateId(id in PackstückTO) noch kein String
            packstueck = new PackstueckTO(dateId.getText(),Double.valueOf(volumen.getText()),
                    Double.valueOf(gewicht.getText()),Integer.valueOf(dateReferenzNr.getText()), sendungsnummer, datenLagerort.getText(),pArt.getValue());
            fenster.close();
        });

        abbrechen.setOnAction(e -> {
            boolean antwort = GUIBestaetigungsBox.display("Vorgang abbrechen", "Sind Sie sicher, dass Sie den Vorgang abbrechen möchten?");
            if(antwort) {fenster.close();}
        });


        // --- Layout ---
        GridPane grid = new GridPane();
        grid.setConstraints(titleSendungsnummer,0,0);
        grid.setConstraints(dateSendungsnummer,1,0);
        grid.setConstraints(titleId,0,1);
        grid.setConstraints(dateId,1,1);
        grid.setConstraints(titleReferenzNr,0,2);
        grid.setConstraints(dateReferenzNr,1,2);
        grid.setConstraints(titleLagerort,0,3);
        grid.setConstraints(datenLagerort,1,3);
        grid.setConstraints(gewicht,0,5);
        grid.setConstraints(volumen,1,5);
        grid.setConstraints(pArt,0,6);
        grid.setConstraints(speichern,0,7);
        grid.setConstraints(abbrechen,1,7);
        grid.getChildren().addAll(titleSendungsnummer,dateSendungsnummer,titleId,dateId,titleReferenzNr,dateReferenzNr,
                titleLagerort,datenLagerort,gewicht,volumen,pArt,speichern,abbrechen);

        Scene scene = new Scene(grid,200,200);
        fenster.setScene(scene);
        fenster.showAndWait();

        return packstueck;
    }
}