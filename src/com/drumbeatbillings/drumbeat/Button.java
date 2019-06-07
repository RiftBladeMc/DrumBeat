package com.drumbeatbillings.drumbeat;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Button {
    static  {
        if (!SystemTray.isSupported()) {
            DrumBeat.handleError(new AWTException("Unable to add counter"));
        }
    }

    public static void set(String text, Color color, Runnable click) {
        int size = 16;
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setPaint(color);
        graphics.fill(new Rectangle2D.Double(0, size/3.5, size, size/2));
        graphics.setPaint(Color.BLACK);
        graphics.drawString(text, 0, (int) (size*0.6875));
        graphics.dispose();

        TrayIcon icon = new TrayIcon(image);
        icon.addActionListener(e -> {
            click.run();
            SystemTray.getSystemTray().remove(icon);
        });
        try {
            SystemTray.getSystemTray().add(icon);
        } catch (AWTException awt) {
            DrumBeat.handleError(awt);
        }
        //TrayIcon icon = new TrayIcon()
    }

    public static BufferedImage test() {
        BufferedImage image = new BufferedImage(39, 5, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        for (int i = 0; i < 10; i++) {
            draw(graphics, i, i*4);
        }
        graphics.dispose();
        return image;
    }

    private static void draw(Graphics2D graphics, int num, int x) {
        switch (num) {
            case 0:
                graphics.drawLine(x, 0, x+2, 0);
                graphics.drawLine(x, 4, x+2, 4);
                graphics.drawLine(x, 0, x, 3);
                graphics.drawLine(x+2, 0, x+2, 4);
                break;
            case 1:
                graphics.drawLine(x+2, 0, x+2, 4);
                break;
            case 2:
                graphics.drawLine(x, 0, x+2, 0);
                graphics.drawLine(x+2, 1, x+2, 1);
                graphics.drawLine(x, 2, x+2, 2);
                graphics.drawLine(x, 3, x, 3);
                graphics.drawLine(x, 4, x+2, 4);
                break;
            case 3:
                graphics.drawLine(x+2, 0, x+2, 4);
                graphics.drawLine(x, 0, x+1, 0);
                graphics.drawLine(x, 2, x+1, 2);
                graphics.drawLine(x, 4, x+1, 4);
                break;
            case 4:
                graphics.drawLine(x, 0, x, 2);
                graphics.drawLine(x+1, 2, x+1, 2);
                graphics.drawLine(x+2, 0, x+2, 4);
                break;
            case 5:
                graphics.drawLine(x, 0, x+2, 0);
                graphics.drawLine(x, 1, x, 1);
                graphics.drawLine(x, 2, x+2, 2);
                graphics.drawLine(x+2, 3, x+2, 3);
                graphics.drawLine(x, 4, x+2, 4);
                break;
            case 6:
                graphics.drawLine(x, 0, x+2, 0);
                graphics.drawLine(x, 1, x, 1);
                graphics.drawLine(x, 2, x+2, 2);
                graphics.drawLine(x+2, 3, x+2, 3);
                graphics.drawLine(x, 4, x+2, 4);
                graphics.drawLine(x, 3, x, 3);
                break;
            case 7:
                graphics.drawLine(x+2, 0, x+2, 4);
                graphics.drawLine(x, 0, x+1, 0);
                break;
            case 8:
                graphics.drawLine(x, 0, x+2, 0);
                graphics.drawLine(x, 4, x+2, 4);
                graphics.drawLine(x, 0, x, 3);
                graphics.drawLine(x+2, 0, x+2, 4);
                graphics.drawLine(x+1, 2, x+1, 2);
                break;
            case 9:
                graphics.drawLine(x, 0, x+2, 0);
                graphics.drawLine(x+2, 1, x+2, 1);
                graphics.drawLine(x, 1, x, 1);
                graphics.drawLine(x, 2, x+2, 2);
                graphics.drawLine(x+2, 3, x+2, 3);
                graphics.drawLine(x, 4, x+2, 4);
                break;
        }
    }
}
