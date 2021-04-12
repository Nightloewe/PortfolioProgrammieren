package de.dhbw_mannheim.student;

import javafx.application.Application;
import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.awt.*;

public class App extends Application {

    private Panel panel;
    private Frame frame;
    private MenuBar menuBar;
    private Menu menu;
  //  private MenuItem menuItem1;
  //  private MenuItem menuItem2;
    private Menu subMenu1;
    private Menu subMenu2;
    private CheckMenuItem checkItem1;    // CheckBoxMenuItem gibt es nicht in javaFX, deswegen nehme ich CheckMenuItem
    private CheckMenuItem checkItem2;
    private CheckMenuItem checkItem3;
    private CheckMenuItem checkItem4;
    private Separator sep;
    private Separator sep1;
    private Separator sep2;
    private TextField text;
    private ScrollBar scroll;




    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Aufgabe 6");
        frame = new Frame();

        menuBar = new MenuBar();
        VBox vBox = new VBox(menuBar);

        Scene scene = new Scene(vBox, 960, 600);

        stage.setScene(scene);
        stage.show();


        menu = new Menu("Sortierung ");
        //    menuItem1 = new MenuItem("Sortierung nach Vorname");                  //Auswählen (können mehrere)
        //    menuItem2 = new MenuItem("Sortierung nachAlter");
        subMenu1 = new Menu("Sortierung nach Vorname");
        subMenu2 = new Menu("Sortierung nach Alter");
        checkItem1 = new CheckMenuItem("Absteigend");                 // Auswählen (immer nur eins)
        checkItem2 = new CheckMenuItem("Aufsteigend");
        checkItem3 = new CheckMenuItem("Absteigend");
        checkItem4 = new CheckMenuItem("Aufsteigend");
        sep = new Separator();                                              //Linie zwischen den menu und den CheckBoxMenuItems
        sep1 = new Separator();
        sep2 = new Separator();

        menuBar.getMenus().add(menu);
        menu.getItems().add(subMenu1);
     //   menu.getItems().add(sep);
        menu.getItems().add(subMenu2);
        //    menu.add(menuItem1);
        subMenu1.getItems().add(checkItem1);
      //  subMenu1.getItems().add(sep1);
        subMenu1.getItems().add(checkItem2);
        //   menu.add(menuItem2);
        subMenu2.getItems().add(checkItem3);
      //  subMenu2.getItems().add(sep2);
        subMenu2.getItems().add(checkItem4);

        //   panel = new JPanel();
        //   panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,10));
        //   panel.setLayout(new GridLayout(0,1));
        //   frame.add(panel, BorderLayout.CENTER);
//
        //   text = new JTextField();
        //   text.setSize(80,80);
        //   text.setHorizontalAlignment(text.RIGHT);
        //   text.setEditable(false);
        //   scroll  = new JScrollBar();





     //   frame.setDefaultCloseOperation(Frame.EXIT_ON_CLOSE);
     //   frame.setTitle("Aufgabe 6");
     //   frame.pack();
     //   frame.setVisible(true);
     //   frame.setSize(840,420);
//
     //   frame.setMenuBar(menuBar);
    }


    public static void main(String [] Args){
        launch();
    }
}
