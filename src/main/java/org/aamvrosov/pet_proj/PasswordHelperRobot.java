package org.aamvrosov.pet_proj;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class PasswordHelperRobot {
    private final static int KEY_INPUT_DELAY = 15;

    private final static Robot robot;

    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        if (args.length != 1 && args.length != 2) {
            System.err.println("Program takes 1 or 2 arguments: user name(optional) and password(required)!");
            System.exit(1);
        }

        provideAltTabIfWindows();

        switch (args.length) {
            case 2: {
                String login = extractEnv(args[0]);
                String password = extractEnv(args[1]);

                typeTextAndPressKey(login, KeyEvent.VK_TAB);
                typeTextAndPressKey(password, KeyEvent.VK_ENTER);

                break;
            }
            case 1: {
                String password = extractEnv(args[0]);

                typeTextAndPressKey(password, KeyEvent.VK_ENTER);
                break;
            }
        }
    }

    private static void typeTextAndPressKey(String text, int keyCode) {
        type(text);

        robot.keyPress(keyCode);
        robot.delay(KEY_INPUT_DELAY);
        robot.keyRelease(keyCode);
        robot.delay(KEY_INPUT_DELAY);
    }

    private static void provideAltTabIfWindows() {
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            robot.keyPress(KeyEvent.VK_ALT);
            robot.delay(KEY_INPUT_DELAY);
            robot.keyPress(KeyEvent.VK_TAB);
            robot.delay(KEY_INPUT_DELAY);
            robot.keyRelease(KeyEvent.VK_TAB);
            robot.delay(KEY_INPUT_DELAY);
            robot.keyRelease(KeyEvent.VK_ALT);
            robot.delay(KEY_INPUT_DELAY * 2);
        }
    }

    private static void type(String line) {
        StringSelection stringSelection = new StringSelection(line);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.delay(KEY_INPUT_DELAY);
        robot.keyPress(KeyEvent.VK_V);
        robot.delay(KEY_INPUT_DELAY);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
    }

    private static String extractEnv(String line) {
        final String trimmed = line.trim();
        if (trimmed.charAt(0) == '$') {
            return System.getenv(trimmed.substring(1));
        }
        return trimmed;
    }
}