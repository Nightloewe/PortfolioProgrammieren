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
    private MenuItem aufsteigendNachname;
    private MenuItem absteigendNachname;
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
 //   private static NameComparator nameComparator = new NameComparator();
 //   private static AgeComparator ageComparator = new AgeComparator();

  //  private static Comparator aktivComparator = new Comparator() {
  //      @Override
  //      public int compare(Object o1, Object o2) {
  //          return 0;
  //      }
  //  };





    @Override
    public void start(Stage stage) throws Exception {
        table = new TableView<Person>();



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
        sortNachname = new MenuItem("Nach Nachnamen sortieren ");
        aufsteigendNachname = new MenuItem("Aufsteigend");
        absteigendNachname = new MenuItem("Absteigend");

        Separator sep = new Separator(Orientation.HORIZONTAL);


        sortAlter = new MenuItem("Nach Alter sortiern ");
        aufsteigendAlter = new MenuItem("Aufsteigend ");
        absteigendAlter = new MenuItem("Absteigend ");

        menu.getItems().add(sortNachname);
        menu.getItems().add(aufsteigendNachname);
        menu.getItems().add(absteigendNachname);
        menu.getItems().add(sortAlter);
        menu.getItems().add(aufsteigendAlter);
        menu.getItems().add(absteigendAlter);



        //erstellen
        menuBar.getMenus().add(datei);
        menuBar.getMenus().add(menu);



        ObservableList<String> list = FXCollections.observableArrayList("asdsa");
        list.add("loadPerson");
        listView = new ListView<Person>();
      //nicht nötig  listView.setItems(list);
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        //Datei auswählen
        dateiOeffnen.setOnAction(this::onOpenFile);
        dateiSchließen.setOnAction(this::onOpenFile);






     //   subMenu1.setOnAction(new EventHandler<ActionEvent>() {
     //       @Override
     //       public void handle(ActionEvent event) {
//
     //           NameComparator nameComparator = new NameComparator();
     //           AgeComparator ageComparator = new AgeComparator();
     //           SortDirection nextDirektion = null;
//
     //           if(this.aktivComparator.getDirection() == SortDirection.ASCENDING) {
     //               nextDirection = SortDirection.DESCENDING;
     //           } else {
     //               nextDirection = SortDirection.ASCENDING;
     //           }
//
     //           this.nameComparator.setDirection(nextDirection);
     //           this.ageComparator.setDirection(nextDirection);
     //       }
     //   });


        ToggleGroup difficultyToggle = new ToggleGroup();

                    //RadioMenuItem1.setToggleGroup(difficultyToggle);
                    //RadioMenuItem2.setToggleGroup(difficultyToggle);
                    //RadioMenuItem3.setToggleGroup(difficultyToggle);
                    //RadioMenuItem4.setToggleGroup(difficultyToggle);









        //   }



    //  menuItemOpenFile.setOnAction(this::onOpenFileClick);

                    //   RadioMenuItem1.setOnAction(new EventHandler<ActionEvent>() {
                    //       @Override
                    //       public void handle(ActionEvent actionEvent) {
                    //           listView.getItems().clear();
                    //           listView.getItems().add("Personenangaben");
                    //           listView.getItems().add("asd");
                    //           listView.getItems().add("asd");
                    //           listView.getItems().add("asd");
                    //       }
                    //   });
                    //   RadioMenuItem2.setOnAction(new EventHandler<ActionEvent>() {
                    //       @Override
                    //       public void handle(ActionEvent actionEvent) {
                    //           listView.getItems().clear();
                    //           listView.getItems().add("Personenangaben");
                    //           listView.getItems().add("assssssd");
                    //           listView.getItems().add("assssssd");
                    //           listView.getItems().add("assssssd");
                    //       }
                    //   });
                    //   RadioMenuItem3.setOnAction(new EventHandler<ActionEvent>() {
                    //       @Override
                    //       public void handle(ActionEvent actionEvent) {
                    //           listView.getItems().clear();
                    //           listView.getItems().add("Personenangaben");
                    //           listView.getItems().add("asdas sad 545d");
                    //           listView.getItems().add("asdas sad 545d");
                    //           listView.getItems().add("asdas sad 545d");
                    //       }
                    //   });
                    //   RadioMenuItem4.setOnAction(new EventHandler<ActionEvent>() {
                    //       @Override
                    //       public void handle(ActionEvent actionEvent) {
//
                    //           listView.getItems().clear();
                    //           listView.getItems().add("Personenangaben");
                    //           //     listView.getItems().add(Personen);
                    //           listView.getItems().add("a400405000 50    5sd");
                    //           listView.getItems().add("a400405000 50    5sd");
                    //       }
                    //   });


        //    menu.getItems().add(new MenuItem("Sortieren nach Vorname"));
        //  MenuItem aufsteigendVorname = new MenuItem("Aufsteigend");
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

    //public void onOpenFileClick(ActionEvent e) {
        //Die ListView hier dann bearbeiten


     //   SortDirection nextDirection = null;
     //   if(this.nameComparator.getDirection() == SortDirection.ASCENDING) {
     //       nextDirection = SortDirection.DESCENDING;
     //   } else {
     //       nextDirection = SortDirection.ASCENDING;
     //   }
//
     //   this.nameComparator.setDirection(nextDirection);
     //   this.ageComparator.setDirection(nextDirection);




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

                    //    path1 = path;

              // catch(FileNotFoundException ea){
              //     path1=Paths.get("C:/Users/nilsf/Desktop/new 8.txt");

              //     }
                 //   if(path1 == null ){
                 //       path1=C:/Users/nilsf/Desktop/new 8.txt;
                 //   }


                }
                else if (e.getSource() == this.dateiSchließen){
                    listView.getItems().clear();
                }


                else if(e.getSource() == this.sortNachname){

                }
                else if(e.getSource() == this.sortAlter){

                }
                else if(e.getSource() == this.absteigendNachname){

                }


               //   String datensatz = null;
               //   try {
               //       Scanner scanner = new Scanner(file);
               //       while (scanner.hasNextLine()) {
               //           datensatz = scanner.nextLine();

               //           speicherarry[StringsInArray]= datensatz;
               //           listView.getItems().add(datensatz);
               //           StringsInArray++;

               //       }
               //       scanner.close();
               //       //speicherarry= new String[StringsInArray];

               //   } catch (FileNotFoundException e) {
               //       System.out.println("Datei konnte nicht gefunden werden");
               //       e.printStackTrace();

               //   }


    }
    public SortDirection nextDirection = null;
    public SortDirection nameComparator = SortDirection.ASCENDING;
    public SortDirection getDirection;

    Comparator nextDirektion = null;
    Comparator nameComarator;
    Comparator ageComparator;
    Comparator getDirection;

    public void setDirection(SortDirection getDirection){
       // nextDirection = null;



        if(this.nameComparator.getDirection() == SortDirection.ASCENDING) {
            nextDirection = SortDirection.DESCENDING;
        } else {
            nextDirection = SortDirection.ASCENDING;
        }
    }

    public void getDirection(){

    }

    public void Pathausfuehren(String fullPath)  throws Exception {
        PersonService service = new PersonService();
        service.loadPersons(path1);
        List<Person> persons = service.loadPersons(Paths.get(fullPath));        //Hier liegt der Fehler !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        for (Person person : persons) {
            listView.getItems().add(person);
        }
     //  listView.getItems().add(fullPath);
    }

    public static void main(String[] args) {
        launch(args);





        //    PersonService service = new PersonService();
        //    List<Person> persons = service.loadPersons(Paths.get("C:\test.txt"));
        //    for (Person person : persons) {
        //        System.out.println(person);
    }

}

