package de.dhbw_mannheim.student;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import de.dhbw_mannheim.student.exceptions.FileIsDirectoryException;
import de.dhbw_mannheim.student.model.Person;
import de.dhbw_mannheim.student.support.*;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;

public class App extends Application {

    private MenuBar menuBar;
    private Menu datei;
    private MenuItem dateiOeffnen;
    private MenuItem dateiSchliessen;
    private Menu sortMenu;
    private MenuItem sortNachname;
    private MenuItem sortAlter;
    private MenuItem changeDirectionSortierung;
    private Scene scene;
    private ListView<Person> listView;
    private NameComparator nameComparator = new NameComparator();
    private AgeComparator ageComparator = new AgeComparator();
    private Comparator<Person> active = nameComparator;

    private PersonService service = new PersonService();
    private ObservableList<Person> persons;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Sortierung von Datensätzen");
        menuBar = new MenuBar();

        datei = new Menu("Datei ");
        dateiOeffnen = new MenuItem("Datei öffnen");
        dateiSchliessen = new MenuItem("Datei schließen");
        dateiSchliessen.setDisable(true);
        datei.getItems().add(dateiOeffnen);
        datei.getItems().add(dateiSchliessen);

        sortMenu = new Menu("Sortierung ");
        sortMenu.setVisible(false);
        sortNachname = new MenuItem("Nach Nachnamen sortieren ");
        sortAlter = new MenuItem("Nach Alter sortiern ");

        changeDirectionSortierung = new MenuItem("Absteigend sortieren");

        sortMenu.getItems().add(sortNachname);
        sortMenu.getItems().add(sortAlter);
        SeparatorMenuItem sep = new SeparatorMenuItem();
        sortMenu.getItems().add(sep);
        sortMenu.getItems().add(changeDirectionSortierung);

        menuBar.getMenus().add(datei);

        menuBar.getMenus().add(sortMenu);

        listView = new ListView<>();
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listView.setPlaceholder(new Label("Bitte öffnen Sie eine Datei."));

        //Betätigung der MenuItems
        dateiOeffnen.setOnAction(this::onClick);
        dateiSchliessen.setOnAction(this::onClick);
        sortNachname.setOnAction(this::onClick);
        sortAlter.setOnAction(this::onClick);
        changeDirectionSortierung.setOnAction(this::onClick);


        VBox root = new VBox(menuBar, listView);
        scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    void onClick(ActionEvent e) {
        if(e.getSource() == this.dateiOeffnen) {
            //Fenster zum Auswählen  einer Datei
            FileChooser chooser = new FileChooser();
            File file = chooser.showOpenDialog(scene.getWindow());

            if(file == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Es wurde keine Datei ausgewählt!");
                alert.show();
                return;
            }

            try {
                this.persons = FXCollections.observableArrayList(service.loadPersons(file.toPath()));
                this.listView.setItems(this.persons);
                sort();

                this.dateiSchliessen.setDisable(false);
                this.sortMenu.setVisible(true);
            } catch(FileNotFoundException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Datei wurde nicht gefunden!");
                alert.show();
            } catch(FileIsDirectoryException ex){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Datei ist ein Ordner!");
                alert.show();
            } catch (IOException ex){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Datei ist nicht lesbar!");
                alert.show();
            }
        } else if (e.getSource() == this.dateiSchliessen){
            //this.persons null setzen
            this.persons = null;
            //listView setItems auf null setzen
            listView.setItems(null);
            //listView.refresh
            listView.refresh();
            //name und age Comparator auf Standard Direction setzen (ASCENDING)
            nameComparator.setDirection(SortDirection.ASCENDING);
            ageComparator.setDirection(SortDirection.ASCENDING);

            this.dateiSchliessen.setDisable(true);
            this.sortMenu.setVisible(false);
        } else if(e.getSource() == this.sortNachname){
            //Setzung des aktiven Comparators
            active = nameComparator;
            sort();
        } else if(e.getSource() == this.sortAlter){
            //Setzung des aktiven Comparators
            active = ageComparator;
            sort();
        } else if(e.getSource() == this.changeDirectionSortierung){
            //Sortierrichtung wird geändert
            changeDirection();
            sort();
        }
    }

    //Setzung der Sortierrichtung
    public void changeDirection() {
        SortDirection nextDirection;
        if(this.nameComparator.getDirection() == SortDirection.ASCENDING) {
            nextDirection = SortDirection.DESCENDING;
            changeDirectionSortierung.setText("Aufsteigend sortieren");
        } else {
            nextDirection = SortDirection.ASCENDING;
            changeDirectionSortierung.setText("Absteigend sortieren");
        }

        this.nameComparator.setDirection(nextDirection);
        this.ageComparator.setDirection(nextDirection);
    }

    public void sort() {
        service.sort(persons, active);
    }

    public static void main(String[] args) {
        launch(args);
    }

}

