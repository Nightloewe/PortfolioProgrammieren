package de.dhbw_mannheim.student;

import javafx.application.Application;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;

public class App extends Application {

    private JPanel panel;
    private JFrame frame;
    private JMenuBar menuBar;
    private JMenu menu;
  //  private JMenuItem menuItem1;
  //  private JMenuItem menuItem2;
    private JMenu subMenu1;
    private JMenu subMenu2;
    private JCheckBoxMenuItem checkItem1;
    private JCheckBoxMenuItem checkItem2;
    private JCheckBoxMenuItem checkItem3;
    private JCheckBoxMenuItem checkItem4;
    private JSeparator sep;
    private JSeparator sep1;
    private JSeparator sep2;
    private JTextField text;
    private JScrollBar scroll;


    public Test(){
        frame = new JFrame();

        menuBar = new JMenuBar();
        menu = new JMenu("Sortierung ");
        //    menuItem1 = new JMenuItem("Sortierung nach Vorname");                  //Auswählen (können mehrere)
        //    menuItem2 = new JMenuItem("Sortierung nachAlter");
        subMenu1 = new JMenu("Sortierung nach Vorname");
        subMenu2 = new JMenu("Sortierung nach Alter");
        checkItem1 = new JCheckBoxMenuItem("Absteigend");                 // Auswählen (immer nur eins)
        checkItem2 = new JCheckBoxMenuItem("Aufsteigend");
        checkItem3 = new JCheckBoxMenuItem("Absteigend");
        checkItem4 = new JCheckBoxMenuItem("Aufsteigend");
        sep = new JSeparator();                                              //Linie zwischen den menu und den CheckBoxMenuItems
        sep1 = new JSeparator();
        sep2 = new JSeparator();

        menuBar.add(menu);
        menu.add(subMenu1);
        menu.add(sep);
        menu.add(subMenu2);
        //    menu.add(menuItem1);
        subMenu1.add(checkItem1);
        subMenu1.add(sep1);
        subMenu1.add(checkItem2);
        //   menu.add(menuItem2);
        subMenu2.add(checkItem3);
        subMenu2.add(sep2);
        subMenu2.add(checkItem4);

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





        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Aufgabe 6");
        frame.pack();
        frame.setVisible(true);
        frame.setSize(840,420);

        frame.setJMenuBar(menuBar);

    }

    @Override
    public void start(Stage stage) throws Exception {

    }


    public static void main(String [] Args){
        launch();
        Test test = new Test();
    }
}
