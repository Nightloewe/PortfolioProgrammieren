package de.dhbw_mannheim.student;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import de.dhbw_mannheim.student.model.Person;
import de.dhbw_mannheim.student.support.*;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.control.MenuItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.stage.FileChooser;
import java.nio.file.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import java.util.Scanner;
import javafx.stage.FileChooser;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.CheckMenuItem;

import javafx.scene.control.ListView;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javafx.scene.control.SeparatorMenuItem;


import javafx.scene.layout.HBox;

import javax.imageio.plugins.tiff.ExifParentTIFFTagSet;
import javax.print.attribute.standard.MediaSize;
import javax.swing.*;


public class App extends Application {

    private Panel panel;
    private Frame frame;
    private MenuBar menuBar;
    private Menu menu;
    private Menu datei;
    private Menu menuItemOpenFile;  //kann methode mit set und
    // private MenuItem menuItem1;
    // private MenuItem menuItem2;

    private MenuItem dateiOeffnen;
    private MenuItem dateiSchließen;
    private MenuItem sortNachname;
    private MenuItem sortAlter;
    private MenuItem aufsteigend;
    private MenuItem absteigend;
    private MenuItem aufsteigendAlter;
    private MenuItem absteigendAlter;
    private MenuItem subMenu1;
    private MenuItem subMenu2;
    private MenuItem richttung;
    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;
    private CheckBox checkBox4;
    private CheckMenuItem checkItem1;    // CheckBoxMenuItem gibt es nicht in javaFX, deswegen nehme ich CheckMenuItem
    private CheckMenuItem checkItem2;
    private CheckMenuItem checkItem3;
    private CheckMenuItem checkItem4;
    private RadioMenuItem RadioMenuItem1;
    private RadioMenuItem RadioMenuItem2;
    private RadioMenuItem RadioMenuItem3;
    private RadioMenuItem RadioMenuItem4;
    private Separator sep;
    private Separator sep1;
    private Separator sep2;
    private TextField text;
    private ScrollBar scroll;
    private VBox mainBox;
    private Label label;
    private TextField textfield;
    private Text t;
    private ObservableList<Person> list;
    //this.list ist die arraylist wleche über dei methode geladen hat
    private  Person [] listePersonen;
    private List<Person> persons = new ArrayList<>();
    private TableView<Person> table;
    private List<Person> listPersonPath;
    private Scene scene;
    private String[] speicherarry = new String[255];
    private ListView<Person> listView;
    private Path path1 ;  //C:/Users/nilsf/Desktop/new 8.txt;
    private OrderedComparator <Person> aktuellerOperato;
    private Scanner s;
    private static String fullPath ="C:\\Users\\nilsf\\Desktop\\new 8.txt" ;
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private NameComparator nameComparator;
    private AgeComparator ageComparator;
    private Comparator<Person> active = nameComparator;



    @Override
    public void start(Stage stage) throws Exception {
        table = new TableView<Person>();
        List<Person> persons;


        stage.setTitle("Aufgabe 6");

        frame = new Frame();
        menuBar = new MenuBar();




        datei = new Menu("Datei ");
        dateiOeffnen = new MenuItem("Datei öffnen");
        dateiSchließen = new MenuItem("Datei schließen");
        datei.getItems().add(dateiOeffnen);
        datei.getItems().add(dateiSchließen);

        menu = new Menu("Sortierung ");
        sortNachname = new MenuItem("Nach Nachnamen sortieren ");
        sortAlter = new MenuItem("Nach Alter sortiern ");

        Separator sep = new Separator(Orientation.HORIZONTAL);


        aufsteigend = new MenuItem("Aufsteigend ");
        absteigend = new MenuItem("Absteigend ");

        menu.getItems().add(sortNachname);
        menu.getItems().add(sortAlter);
        menu.getItems().add(aufsteigend);
        menu.getItems().add(absteigend);



        //erstellen
        menuBar.getMenus().add(datei);
        menuBar.getMenus().add(menu);



        ObservableList<String> list = FXCollections.observableArrayList("asdsa");
        list.add("loadPerson");
        listView = new ListView<Person>();
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        //Datei auswählen
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
                    FileChooser chooser = new FileChooser();

                    File file = new File("");
                    file = chooser.showOpenDialog(scene.getWindow());



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
                    active = ageComparator;
                    listView.getItems().clear();
                    sort();
                }
                else if(e.getSource() == this.sortAlter){
                    active = ageComparator;
                    listView.getItems().clear();
                    sort();
                }
                else if(e.getSource() == this.aufsteigend){
                    this.nameComparator.setDirection(SortDirection.ASCENDING);
                    this.ageComparator.setDirection(SortDirection.ASCENDING);
                }
                else if(e.getSource() == this.absteigend){
                    this.nameComparator.setDirection(SortDirection.DESCENDING);
                    this.ageComparator.setDirection(SortDirection.DESCENDING);
                }
    }



    public void setDirection(SortDirection nextDirection){
        if(this.nameComparator.getDirection() == SortDirection.ASCENDING) {
            nextDirection = SortDirection.DESCENDING;
        } else {
            nextDirection = SortDirection.ASCENDING;
        }
    }


    public void Pathausfuehren(String fullPath)  throws Exception {
        PersonService service = new PersonService();
        service.loadPersons(path1);
        persons = service.loadPersons(Paths.get(fullPath));
        for (Person person : persons) {
            listView.getItems().add(person);
        }
     //  listView.getItems().add(fullPath);
    }

    public void sort (){
        PersonService service = new PersonService();
        service.sort(persons, active);
        for (Person person : persons) {
            listView.getItems().add(person);
        }
    }

    public static void main(String[] args) {
        launch(args);

    }

}

