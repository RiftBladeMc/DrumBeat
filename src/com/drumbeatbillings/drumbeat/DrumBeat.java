package com.drumbeatbillings.drumbeat;

import javafx.scene.control.Accordion;
import org.jdesktop.swingx.JXTaskPane;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.Objects;

public class DrumBeat {
    private static DrumBeat instance;

    public static DrumBeat getInstance() {
        return instance;
    }
    public static Runnable button = null;
    public static void main(String[] args) throws InterruptedException {
        instance = new DrumBeat();
        Button.getButton();
    }

    public static void handleError(Throwable e) {
        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw)) {
            e.printStackTrace(pw);
        }
        String error = e.getMessage();
        if (error == null) error = "An error has occurred.";
        JPanel err = new JPanel();
        err.setLayout(new BoxLayout(err, BoxLayout.Y_AXIS));
        JTextField textField = new JTextField(error);
        textField.setMinimumSize(new Dimension(0, 20));
        textField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
        textField.setEditable(false);
        textField.setBorder(null);
        err.add(textField);

        JTextField report = new JTextField("Please report the following to our github.");
        report.setMinimumSize(new Dimension(0, 20));
        report.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
        report.setEditable(false);
        report.setBorder(null);
        err.add(report);

        JXTaskPane taskPane = new JXTaskPane();
        taskPane.setTitle("More Info");
        JTextArea textArea = new JTextArea(sw.toString());
        textArea.setBackground(Color.getColor("eeeeee"));
        textArea.setEditable(false);
        taskPane.add(textArea);
        err.add(taskPane);
        JOptionPane.showMessageDialog(null, err, error, JOptionPane.WARNING_MESSAGE);
    }

    private DrumBeat() {
    }

    public static boolean isPro() {
        return false;
    }
}
