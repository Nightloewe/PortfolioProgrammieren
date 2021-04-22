package de.dhbw_mannheim.student;
import java.io.File;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.*;
import java.util.List;
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
    private MenuItem aufsteigend;
    private MenuItem absteigend;
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

        aufsteigend = new MenuItem("Aufsteigend ");
        absteigend = new MenuItem("Absteigend ");

        Sortmenu.getItems().add(sortNachname);
        Sortmenu.getItems().add(sortAlter);
        SeparatorMenuItem sep = new SeparatorMenuItem();
        Sortmenu.getItems().addAll(sep);
        Sortmenu.getItems().add(aufsteigend);
        Sortmenu.getItems().add(absteigend);

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
        aufsteigend.setOnAction(this::onOpenFile);
        absteigend.setOnAction(this::onOpenFile);




        VBox root = new VBox(menuBar, listView);
        scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.show();


    }




    void onOpenFile(ActionEvent e) {
               // int StringsInArray=0;

                if(e.getSource() == this.dateiOeffnen) {

                    //Falls die alten Daten vor dem Öffnen einer neuen Datei nicht geschlossen wurde
                    //wird diese aus der ListView gelöscht
                    listView.getItems().clear();

                    //Fenster zum Auswählen  einer Datei
                    FileChooser chooser = new FileChooser();
                    File file = new File("");
                    file = chooser.showOpenDialog(scene.getWindow());


                    //Speicherung des Pfades
                    fullPath = file.getAbsolutePath();

                    path1=Paths.get(fullPath);
                    try {
                        Pathausfuehren(fullPath);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }

                else if (e.getSource() == this.dateiSchließen){
                    listView.getItems().clear();
                    persons.clear();
                }

                else if(e.getSource() == this.sortNachname){
                    //Setzung des aktiven Comparators
                    active = nameComparator;
                    //Unsortierte Liste wird aus ListView entfernt und die sortierte Liste wird eingefügt
                    listView.getItems().clear();
                    sort();
                }

                else if(e.getSource() == this.sortAlter){
                    //Setzung des aktiven Comparators
                    active = ageComparator;
                    //Unsortierte Liste wird aus ListView entfernt und die sortierte Liste wird eingefügt
                    listView.getItems().clear();
                    sort();
                }

                else if(e.getSource() == this.aufsteigend){
                    //Setzung der Sortierrichtung
                    this.nameComparator.setDirection(SortDirection.ASCENDING);
                    this.ageComparator.setDirection(SortDirection.ASCENDING);
                    //Neu Sortierte Liste wird eingefügt und die aLte Liste wird entfernt
                    listView.getItems().clear();
                    sort();
                }


                else if(e.getSource() == this.absteigend){
                    //Setzung der Sortierrichtung
                    this.nameComparator.setDirection(SortDirection.DESCENDING);
                    this.ageComparator.setDirection(SortDirection.DESCENDING);
                    //Neu Sortierte Liste wird eingefügt und die aLte Liste wird entfernt
                    listView.getItems().clear();
                    sort();
                }
    }


    //Setzung der Sortierrichtung
    public void setDirection(SortDirection nextDirection){

        if(this.nameComparator.getDirection() == SortDirection.ASCENDING) {
            nextDirection = SortDirection.DESCENDING;
        } else {
            nextDirection = SortDirection.ASCENDING;
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

