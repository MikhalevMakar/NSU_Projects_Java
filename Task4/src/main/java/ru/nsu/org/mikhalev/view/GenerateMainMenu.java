package ru.nsu.org.mikhalev.view;

import javax.swing.*;
import java.awt.*;

public class GenerateMainMenu extends JFrame {
    public GenerateMainMenu() {

        ImageIcon imageIcon = new ImageIcon
            (
            "/Users/natasamihaleva/NSU_Projects_Java/Task4/src/main/resources/image.gif"
            );

        JLabel label = new JLabel (imageIcon);

       // add(label, BorderLayout.WEST);
        //new SupplierSpeedSlider(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(ContextGUI.WIDTH, ContextGUI.HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}