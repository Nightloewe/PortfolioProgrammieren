package de.dhbw_mannheim.student;
import java.io.File;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.*;

import de.dhbw_mannheim.student.exceptions.FileIsDirectoryException;
import de.dhbw_mannheim.student.model.Person;
import de.dhbw_mannheim.student.support.*;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.stage.FileChooser;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import java.awt.*;

public class App extends Application {

    private MenuBar menuBar;
    private Menu datei;
    private MenuItem dateiOeffnen;
    private MenuItem dateiSchließen;
    private Menu Sortmenu;
    private MenuItem sortNachname;
    private MenuItem sortAlter;
    private MenuItem changeDirectionSortierung;
    private Scene scene;
    private ListView<Person> listView;
    private Path path1 ;
    private String fullPath  ;
    private NameComparator nameComparator = new NameComparator();
    private AgeComparator ageComparator = new AgeComparator();
    private Comparator<Person> active = nameComparator;
    private AppArrayList persons = new AppArrayList();
    private PersonService service = new PersonService();
    private ObservableList<String> list;


    @Override
    public void start(Stage stage) throws Exception {

        TableView table = new TableView<Person>();

        List<Person> persons;

        stage.setTitle("Sortierung von Datensätzen");

        Frame frame = new Frame();
        menuBar = new MenuBar();

        datei = new Menu("Datei ");
        dateiOeffnen = new MenuItem("Datei öffnen");
        dateiSchließen = new MenuItem("Datei schließen");
        datei.getItems().add(dateiOeffnen);
        datei.getItems().add(dateiSchließen);

        Sortmenu = new Menu("Sortierung ");
        sortNachname = new MenuItem("Nach Nachnamen sortieren ");
        sortAlter = new MenuItem("Nach Alter sortiern ");

        changeDirectionSortierung = new MenuItem("Absteigend sortieren");

        Sortmenu.getItems().add(sortNachname);
        Sortmenu.getItems().add(sortAlter);
        SeparatorMenuItem sep = new SeparatorMenuItem();
        sortMenu.getItems().addAll(sep);
        sortMenu.getItems().add(changeDirectionSortierung);

        menuBar.getMenus().add(datei);

        menuBar.getMenus().add(Sortmenu);


        //Erstellung einer Obsererlist in der die Liste mit den Objekten ausgegeben wird
        list = FXCollections.observableArrayList();
        list.add("loadPerson");
        listView = new ListView<Person>();
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        //Betätigung der MenuItems
        dateiOeffnen.setOnAction(this::onOpenFile);
        dateiSchließen.setOnAction(this::onOpenFile);
        sortNachname.setOnAction(this::onOpenFile);
        sortAlter.setOnAction(this::onOpenFile);
        changeDirectionSortierung.setOnAction(this::onOpenFile);


        VBox root = new VBox(menuBar, listView);
        scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.show();


    }




    void onOpenFile(ActionEvent e) {
               // int StringsInArray=0;

                if(e.getSource() == this.dateiOeffnen) {
                    //Fenster zum Auswählen  einer Datei
                    FileChooser chooser = new FileChooser();
                    File file = new File("");
                    file = chooser.showOpenDialog(scene.getWindow());


                    //Speicherung des Pfades
                    fullPath = file.getAbsolutePath();

                    path1=Paths.get(fullPath);

            try {
                this.persons = FXCollections.observableArrayList(service.loadPersons(file.toPath()));
                this.listView.setItems(this.persons);
                sort();

                this.dateiSchließen.setDisable(false);
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
        } else if (e.getSource() == this.dateiSchließen){
            //this.persons null setzen
            this.persons = null;
            //listView setItems auf null setzen
            listView.setItems(null);
            //listView.refresh
            listView.refresh();
            //name und age Comparator auf Standard Direction setzen (ASCENDING)
            nameComparator.setDirection(SortDirection.ASCENDING);
            ageComparator.setDirection(SortDirection.ASCENDING);

            this.dateiSchließen.setDisable(true);
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
    public void setDirection(SortDirection nextDirection){

        if(this.nameComparator.getDirection() == SortDirection.ASCENDING) {
            nextDirection = SortDirection.DESCENDING;
            changeDirectionSortierung.setText("Aufsteigend sortieren");
        } else {
            nextDirection = SortDirection.ASCENDING;
            changeDirectionSortierung.setText("Absteigend sortieren");
        }
    }

    //Pfad wird in loadPerson Methoder der Klasse Personservice als Übergabeparameter übergeben
    //es werden Objekte aus den Daten, welche in einer Liste gespeichert werden und in der ObservableList ausgegeben werden
    public void Pathausfuehren(String fullPath)  throws Exception {

        persons.addAll(service.loadPersons(path1));
        listView.getItems().addAll(persons);

    }

    //Ausführung der Sortierung
    public void sort (){

        service.sort(persons, active);
        listView.getItems().addAll(persons);
    }

    public static void main(String[] args) {

        launch(args);

    }

}

