package de.dhbw_mannheim.student;

import de.dhbw_mannheim.student.model.Person;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
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



public class App extends Application {

    private MenuBar menuBar;
    private Menu menu;
    private Menu menuItemOpenFile;  //kann methode mit set und
   // private MenuItem menuItem1;
   // private MenuItem menuItem2;
    private Menu subMenu1;
    private Menu subMenu11;
    private Menu subMenu12;
    private Menu subMenu2;
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
    private ObservableList <Person>list;
    //this. list ist die arraylist wleche über dei methode geladen hat
    private TableView <Person> table;
    private ListView <Person> listView;


    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Aufgabe 6");

        menuBar = new MenuBar();

        subMenu1 = new Menu("Sortierung nach Nachnam");
        subMenu2 = new Menu("Sortierung nach Alter");

        RadioMenuItem1 = new RadioMenuItem("Aufsteigend");
        RadioMenuItem2 = new RadioMenuItem("Absteigend");
        RadioMenuItem3 = new RadioMenuItem("Aufsteigend");
        RadioMenuItem4 = new RadioMenuItem("Absteigend");

        menu = new Menu("Sortierung ");

        //erstellen
        menuBar.getMenus().add(menu);

        menu.getItems().add(subMenu2);
        //seperator noch einfügen
        menu.getItems().add(subMenu1);

        ToggleGroup difficultyToggle=new ToggleGroup();

        subMenu1.getItems().add(RadioMenuItem1);
        subMenu1.getItems().add(RadioMenuItem2);
        subMenu2.getItems().add(RadioMenuItem3);
        subMenu2.getItems().add(RadioMenuItem4);

        RadioMenuItem1.setToggleGroup(difficultyToggle);
        RadioMenuItem2.setToggleGroup(difficultyToggle);
        RadioMenuItem3.setToggleGroup(difficultyToggle);
        RadioMenuItem4.setToggleGroup(difficultyToggle);

        ObservableList <String> list = FXCollections.observableArrayList("asdsa");


    //   PersonService service = new PersonService();
    //   List<Person> persons = service.loadPersons(Paths.get("C:\test.txt"));
    //   for(Person person : persons) {
    //       System.out.println(person);
    //       listView.getItems().add(person);

    //   }

        list.add("loadPerson");
        ListView <String> listView = new ListView<String>();
        listView.setItems(list);
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


        RadioMenuItem1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                listView.getItems().clear();listView.getItems().add("Personenangaben");
                listView.getItems().add("asd");
                listView.getItems().add("asd");
                listView.getItems().add("asd");
            }
        });
        RadioMenuItem2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                listView.getItems().clear();listView.getItems().add("Personenangaben");
                listView.getItems().add("assssssd");
                listView.getItems().add("assssssd");
                listView.getItems().add("assssssd");
            }
        });
        RadioMenuItem3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                listView.getItems().clear();listView.getItems().add("Personenangaben");
                listView.getItems().add("asdas sad 545d");
                listView.getItems().add("asdas sad 545d");
                listView.getItems().add("asdas sad 545d");
            }
        });
        RadioMenuItem4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                listView.getItems().clear();listView.getItems().add("Personenangaben");
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


          VBox root = new VBox(menuBar, listView);
          Scene scene = new Scene(root, 640, 480);
          stage.setScene(scene);
          stage.show();
    }


    public static void main(String [] Args){
        launch();
    }
}
