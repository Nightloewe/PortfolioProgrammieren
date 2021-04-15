package de.dhbw_mannheim.student;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.List;
import de.dhbw_mannheim.student.model.Person;
import de.dhbw_mannheim.student.support.PersonService;
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
    //this. list ist die arraylist wleche über dei methode geladen hat
    private TableView<Person> table;
    private ListView<Person> listView;
    private Scene scene;


    @Override
    public void start(Stage stage) throws Exception {


        stage.setTitle("Aufgabe 6");

        frame = new Frame();
        menuBar = new MenuBar();


        RadioMenuItem1 = new RadioMenuItem("Aufsteigend");
        RadioMenuItem2 = new RadioMenuItem("Absteigend");
        RadioMenuItem3 = new RadioMenuItem("Aufsteigend");
        RadioMenuItem4 = new RadioMenuItem("Absteigend");

        datei = new Menu("Datei ");
        dateiOeffnen = new MenuItem("Datei öffnen");
        dateiSchließen = new MenuItem("Datei schließen");
        datei.getItems().add(dateiOeffnen);
        datei.getItems().add(dateiSchließen);

        menu = new Menu("Sortierung ");
        subMenu1 = new MenuItem("Nach Nachnamen sortieren ");
        subMenu2 = new MenuItem("Nach Vorname sortiern ");
        richttung = new MenuItem("Sortierrichtung ");
        menu.getItems().add(subMenu1);
        menu.getItems().add(subMenu2);
        menu.getItems().add(richttung);

        //erstellen
        menuBar.getMenus().add(datei);
        menuBar.getMenus().add(menu);

        //Datei auswählen
        dateiOeffnen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FileChooser chooser = new FileChooser();

                File file = chooser.showOpenDialog(scene.getWindow());
                Path path = file.toPath();





            }
        });
     //   PersonService service = new PersonService();
     //   List<Person> persons = service.loadPersons(Paths.get("C:\test.txt"));
     //   for (Person person : persons) {
     //       System.out.println(person);
     //   }


        //  FileChooser chooser = new FileChooser();
//
        //  File file = chooser.showOpenDialog(scene.getWindow());
        //  Path path = file.toPath();
//
//


        //seperator noch einfügen


        ToggleGroup difficultyToggle = new ToggleGroup();

        //    subMenu1.getItems().add(RadioMenuItem1);
        //    subMenu1.getItems().add(RadioMenuItem2);
        //    subMenu2.getItems().add(RadioMenuItem3);
        //    subMenu2.getItems().add(RadioMenuItem4);

        RadioMenuItem1.setToggleGroup(difficultyToggle);
        RadioMenuItem2.setToggleGroup(difficultyToggle);
        RadioMenuItem3.setToggleGroup(difficultyToggle);
        RadioMenuItem4.setToggleGroup(difficultyToggle);


        ObservableList<String> list = FXCollections.observableArrayList("asdsa");


        //   PersonService service = new PersonService();
        //   List<Person> persons = service.loadPersons(Paths.get("C:\test.txt"));
        //   for(Person person : persons) {
        //       System.out.println(person);
        //       listView.getItems().add(person);

        //   }

        list.add("loadPerson");
        ListView<String> listView = new ListView<String>();
        listView.setItems(list);
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


        RadioMenuItem1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                listView.getItems().clear();
                listView.getItems().add("Personenangaben");
                listView.getItems().add("asd");
                listView.getItems().add("asd");
                listView.getItems().add("asd");
            }
        });
        RadioMenuItem2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                listView.getItems().clear();
                listView.getItems().add("Personenangaben");
                listView.getItems().add("assssssd");
                listView.getItems().add("assssssd");
                listView.getItems().add("assssssd");
            }
        });
        RadioMenuItem3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                listView.getItems().clear();
                listView.getItems().add("Personenangaben");
                listView.getItems().add("asdas sad 545d");
                listView.getItems().add("asdas sad 545d");
                listView.getItems().add("asdas sad 545d");
            }
        });
        RadioMenuItem4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                listView.getItems().clear();
                listView.getItems().add("Personenangaben");
                //     listView.getItems().add(Personen);
                listView.getItems().add("a400405000 50    5sd");
                listView.getItems().add("a400405000 50    5sd");
            }
        });
        //    menu.getItems().add(new MenuItem("Sortieren nach Vorname"));
        //    MenuItem aufsteigendVorname = new MenuItem("Aufsteigend");
        //
        //hier der Befehl
        //    aufsteigendVorname.setAccelerator(listView.getItems().add("asd"));
        //    menu.getItems().add(aufsteigendVorname);
        //    MenuItem absteigendVorname = new MenuItem("Absteigend");
        //    //hier der Befehl
        //    menu.getItems().add(absteigendVorname);
//
        //    menu.getItems().add(new MenuItem("Sortieren nach Alter"));
        //    MenuItem aufsteigendAlter = new MenuItem("Aufsteigend");
        //    //hier der Befehl
        //    menu.getItems().add(aufsteigendAlter);
        //    MenuItem absteigendAlter = new MenuItem("Absteigend");
        //    //hier der Befehl
        //    menu.getItems().add(absteigendVorname);


//    menu.getItems().add(new MenuItem("Sortieren nach Vorname"));
        //    MenuItem aufsteigendVorname = new MenuItem("Aufsteigend");
        //
        //hier der Befehl
        //    aufsteigendVorname.setAccelerator(listView.getItems().add("asd"));
        //    menu.getItems().add(aufsteigendVorname);
        //    MenuItem absteigendVorname = new MenuItem("Absteigend");
        //    //hier der Befehl
        //    menu.getItems().add(absteigendVorname);
//
        //    menu.getItems().add(new MenuItem("Sortieren nach Alter"));
        //    MenuItem aufsteigendAlter = new MenuItem("Aufsteigend");
        //    //hier der Befehl
        //    menu.getItems().add(aufsteigendAlter);
        //    MenuItem absteigendAlter = new MenuItem("Absteigend");
        //    //hier der Befehl
        //    menu.getItems().add(absteigendVorname);


        VBox root = new VBox(menuBar, listView);
        scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.show();


    }


    public static void main(String[] args) {
        launch(args);





        //    PersonService service = new PersonService();
        //    List<Person> persons = service.loadPersons(Paths.get("C:\test.txt"));
        //    for (Person person : persons) {
        //        System.out.println(person);
    }

}

