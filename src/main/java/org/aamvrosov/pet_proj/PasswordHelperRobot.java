package org.aamvrosov.pet_proj;

import java.awt.*;
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
        if (args.length == 2) {
            String login = extractEnv(args[0]);
            String password = extractEnv(args[1]);

            type(login);

            robot.keyPress(KeyEvent.VK_TAB);
            robot.delay(KEY_INPUT_DELAY);
            robot.keyRelease(KeyEvent.VK_TAB);
            robot.delay(KEY_INPUT_DELAY);

            type(password);

            robot.keyPress(KeyEvent.VK_ENTER);
            robot.delay(KEY_INPUT_DELAY);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } else {
            System.err.println("Program takes 2 arguments: login and password!");
            System.exit(1);
        }
    }

    private static void type(String line) {
        for (char c : line.toCharArray()) {
            final int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
            if (Character.isUpperCase(c)) {
                robot.keyPress(KeyEvent.VK_SHIFT);
                robot.delay(KEY_INPUT_DELAY);
            }

            robot.keyPress(keyCode);
            robot.delay(KEY_INPUT_DELAY);

            if (Character.isUpperCase(c)) {
                robot.keyRelease(KeyEvent.VK_SHIFT);
                robot.delay(KEY_INPUT_DELAY);
            }

            robot.keyRelease(keyCode);
            robot.delay(KEY_INPUT_DELAY);
        }
    }

    private static String extractEnv(String line) {
        final String trimmed = line.trim();
        if (trimmed.charAt(0) == '$') {
            return System.getenv(trimmed.substring(1));
        }
        return trimmed;
    }
}