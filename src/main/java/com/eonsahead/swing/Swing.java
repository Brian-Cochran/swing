package com.eonsahead.swing;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Swing extends JFrame implements ActionListener {

    private final int FRAME_WIDTH = 512;
    private final int FRAME_HEIGHT = 512;
    private final String FRAME_TITLE = "Swing";
    private static final int NUMBER_OF_COLORS = 3;
    private final String BG_COLOR = "Background Color";
    private final String FG_COLOR = "Foreground Color";
    private final Color DEFAULT_BG = new Color(255, 255, 255);

    private static final List<Color> bgPalette = new ArrayList<>();
    private static final List<Color> fgPalette = new ArrayList<>();
    private final SwingPanel panel;

    public Swing() {
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setTitle(FRAME_TITLE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container pane = this.getContentPane();
        this.panel = new SwingPanel();
        pane.add(panel);
        
        bgPalette.add(DEFAULT_BG);
        Random rng = new Random();
        for (int i = 1; i < NUMBER_OF_COLORS; i++) {
            int red = 128 + rng.nextInt(128);
            int green = 128 + rng.nextInt(128);
            int blue = 128 + rng.nextInt(128);
            Color color = new Color(red, green, blue);
            bgPalette.add(color);
        } // for
        this.panel.setBackground(bgPalette.get(0));

        for (int i = 0; i < NUMBER_OF_COLORS; i++) {
            int red = 32 + rng.nextInt(224);
            int green = 32 + rng.nextInt(224);
            int blue = 32 + rng.nextInt(224);
            Color color = new Color(red, green, blue);
            fgPalette.add(color);
        } // for
        this.panel.setColor(fgPalette.get(0));

        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        JMenu bgColorMenu = new JMenu(BG_COLOR);
        menuBar.add(bgColorMenu);

        for (int i = 0; i < NUMBER_OF_COLORS; i++) {
            String label = BG_COLOR + " " + i;
            JMenuItem item = new JMenuItem(label);
            item.addActionListener(this);
            item.setActionCommand(label);
            bgColorMenu.add(item);
        } // for

        JMenu fgColorMenu = new JMenu(FG_COLOR);
        menuBar.add(fgColorMenu);

        for (int i = 0; i < NUMBER_OF_COLORS; i++) {
            String label = FG_COLOR + " " + i;
            JMenuItem item = new JMenuItem(label);
            item.addActionListener(this);
            item.setActionCommand(label);
            fgColorMenu.add(item);
        } // for

        this.setVisible(true);
    } // Swing()

    @Override
    public void actionPerformed(ActionEvent event) {
        String actionCommand = event.getActionCommand();

        if (actionCommand.indexOf(BG_COLOR) >= 0) {
            int i = BG_COLOR.length();
            String suffix = actionCommand.substring(i).trim();
            int index = Integer.parseInt(suffix);
            this.panel.setBackground(bgPalette.get(index));
        } // if
        else if (actionCommand.indexOf(FG_COLOR) >= 0) {
            int i = FG_COLOR.length();
            String suffix = actionCommand.substring(i).trim();
            int index = Integer.parseInt(suffix);
            this.panel.setColor(fgPalette.get(index));
        } // else if
    } // actionPerformed( ActionEvent )

    public static void main(String[] args) {
        Swing swing = new Swing();
    } // main( String [] )
} // Swing
