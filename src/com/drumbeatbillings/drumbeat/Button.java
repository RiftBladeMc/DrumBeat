package com.drumbeatbillings.drumbeat;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.time.LocalTime;
import java.util.ServiceConfigurationError;
import java.util.Timer;
import java.util.TimerTask;

public class Button  {
    private final BufferedImage image = new BufferedImage(16, 5, BufferedImage.TYPE_INT_ARGB);
    private final TrayIcon icon = new TrayIcon(image);
    private Timer timer = null;
    private LocalTime time = LocalTime.MIN;
    private static final Button button = new Button();
    public static Button getButton() {
        return button;
    }
    private Button() {
        try {
            System.out.print("test");
            create(getDigits("0000"), false);
            System.out.print("test");
            System.out.print("test");
            SystemTray.getSystemTray().add(icon);
            icon.addActionListener(e -> {
                if (timer==null) {
                    timer = new Timer();
                    timer.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            time = time.plusMinutes(1);
                            create(getDigits(time.toString()), true);
                        }
                    }, 1000, 1000);
                } else {
                    timer.cancel();
                    timer.purge();
                    timer = null;
                }
                create(getDigits(time.toString()), timer != null);

            });
        } catch (AWTException awt) {
            DrumBeat.handleError(new ServiceConfigurationError(SystemTray.isSupported() ? "Unable to add TrayIcon." : "TrayIcon not supported.", awt));
        }
    }

    private int[] getDigits(String text) {
        int[] ints = new int[4];
        text = "0000" + text.replaceAll("[^\\d]", "");
        System.out.println(":: " + text.charAt(text.length()-1) + " ::");
        ints[3] = text.charAt(text.length()-1)-48;
        ints[2] = text.charAt(text.length()-2)-48;
        ints[1] = text.charAt(text.length()-3)-48;
        ints[0] = text.charAt(text.length()-4)-48;
        return ints;
    }

    private void create(int[] digits, boolean red) {
        int gray = Color.DARK_GRAY.getRGB(), background = new Color(1f,1f,1f,.1f).getRGB();
        for (int x = 0; x < image.getWidth(); x++) for (int y = 0; y < image.getHeight(); y++) image.setRGB(x, y, background);
        image.setRGB(8, 1, gray);
        image.setRGB(8, 3, gray);
        int color = red ? Color.RED.getRGB() : Color.BLACK.getRGB();
        draw(image, digits[0], 0, color);
        draw(image, digits[1], 4, color);
        draw(image, digits[2], 9, color);
        draw(image, digits[3], 13, color);
        icon.setImage(image);
    }

    private void draw(BufferedImage image, int num, int x, int color) {
        switch (num) {
            case 0:
                image.setRGB(x, 0, color);
                image.setRGB(x, 1, color);
                image.setRGB(x, 2, color);
                image.setRGB(x, 3, color);
                image.setRGB(x, 4, color);
                image.setRGB(x+1, 0, color);
                image.setRGB(x+1, 4, color);
                image.setRGB(x+2, 0, color);
                image.setRGB(x+2, 1, color);
                image.setRGB(x+2, 2, color);
                image.setRGB(x+2, 3, color);
                image.setRGB(x+2, 4, color);
                break;
            case 1:
                image.setRGB(x+2, 0, color);
                image.setRGB(x+2, 1, color);
                image.setRGB(x+2, 2, color);
                image.setRGB(x+2, 3, color);
                image.setRGB(x+2, 4, color);
                break;
            case 2:
                image.setRGB(x, 0, color);
                image.setRGB(x, 2, color);
                image.setRGB(x, 3, color);
                image.setRGB(x, 4, color);
                image.setRGB(x+1, 0, color);
                image.setRGB(x+1, 2, color);
                image.setRGB(x+1, 4, color);
                image.setRGB(x+2, 0, color);
                image.setRGB(x+2, 1, color);
                image.setRGB(x+2, 2, color);
                image.setRGB(x+2, 4, color);
                break;
            case 3:
                image.setRGB(x, 0, color);
                image.setRGB(x, 2, color);
                image.setRGB(x, 4, color);
                image.setRGB(x+1, 0, color);
                image.setRGB(x+1, 2, color);
                image.setRGB(x+1, 4, color);
                image.setRGB(x+2, 0, color);
                image.setRGB(x+2, 1, color);
                image.setRGB(x+2, 2, color);
                image.setRGB(x+2, 3, color);
                image.setRGB(x+2, 4, color);
                break;
            case 4:
                image.setRGB(x, 0, color);
                image.setRGB(x, 1, color);
                image.setRGB(x, 2, color);
                image.setRGB(x+1, 2, color);
                image.setRGB(x+2, 0, color);
                image.setRGB(x+2, 1, color);
                image.setRGB(x+2, 2, color);
                image.setRGB(x+2, 3, color);
                image.setRGB(x+2, 4, color);
                break;
            case 5:
                image.setRGB(x, 0, color);
                image.setRGB(x, 1, color);
                image.setRGB(x, 2, color);
                image.setRGB(x, 4, color);
                image.setRGB(x+1, 0, color);
                image.setRGB(x+1, 2, color);
                image.setRGB(x+1, 4, color);
                image.setRGB(x+2, 0, color);
                image.setRGB(x+2, 2, color);
                image.setRGB(x+2, 3, color);
                image.setRGB(x+2, 4, color);
                break;
            case 6:
                image.setRGB(x, 0, color);
                image.setRGB(x, 1, color);
                image.setRGB(x, 2, color);
                image.setRGB(x, 3, color);
                image.setRGB(x, 4, color);
                image.setRGB(x+1, 0, color);
                image.setRGB(x+1, 2, color);
                image.setRGB(x+1, 4, color);
                image.setRGB(x+2, 0, color);
                image.setRGB(x+2, 2, color);
                image.setRGB(x+2, 3, color);
                image.setRGB(x+2, 4, color);
                break;
            case 7:
                image.setRGB(x, 0, color);
                image.setRGB(x+1, 0, color);
                image.setRGB(x+2, 0, color);
                image.setRGB(x+2, 1, color);
                image.setRGB(x+2, 2, color);
                image.setRGB(x+2, 3, color);
                image.setRGB(x+2, 4, color);
                break;
            case 8:
                image.setRGB(x, 0, color);
                image.setRGB(x, 1, color);
                image.setRGB(x, 2, color);
                image.setRGB(x, 3, color);
                image.setRGB(x, 4, color);
                image.setRGB(x+1, 0, color);
                image.setRGB(x+1, 2, color);
                image.setRGB(x+1, 4, color);
                image.setRGB(x+2, 0, color);
                image.setRGB(x+2, 1, color);
                image.setRGB(x+2, 2, color);
                image.setRGB(x+2, 3, color);
                image.setRGB(x+2, 4, color);
                break;
            case 9:
                image.setRGB(x, 0, color);
                image.setRGB(x, 1, color);
                image.setRGB(x, 2, color);
                image.setRGB(x, 4, color);
                image.setRGB(x+1, 0, color);
                image.setRGB(x+1, 2, color);
                image.setRGB(x+1, 4, color);
                image.setRGB(x+2, 0, color);
                image.setRGB(x+2, 1, color);
                image.setRGB(x+2, 2, color);
                image.setRGB(x+2, 3, color);
                image.setRGB(x+2, 4, color);
                break;
        }
    }
}
