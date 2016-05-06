package com.limagiran.tetris.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import static java.io.File.separator;

/**
 *
 * @author Vinicius
 */
public class Values {
    
    public static final Dimension SCREEN = new Dimension(600, 500);
    public static final Rectangle CG_GENERAL = new Rectangle(new Point(), SCREEN);
    
    public static final int RATE = 20;
    public static final int HEADER_SIZE = RATE;
    public static final int MAX_LEVEL = 10;
    public static final int COLUMNS = 10;
    public static final int LINES = 20;
    public static final int STATUS_MENU = 0;
    public static final int STATUS_PLAY = 1;
    public static final int STATUS_PAUSE = 2;
    public static final int STATUS_GAME_OVER = 3;
    
    public static final String KEY = "#k3y$ttr#";
    public static final Color COLOR_BACKGROUND = Color.BLACK;
    public static final Color COLOR_MENU = Color.WHITE;
    public static final Color COLOR_PAUSE = Color.RED;
    public static final Color COLOR_HEADER_FOREGROUND = Color.GRAY.brighter();   
    
    public static final Font FONTE_DEFAULT = new Font("Modern", Font.PLAIN, 22);
    public static final Font FONTE_MENU = new Font("Modern", Font.PLAIN, 70);
    
    public static final File FILE_RECORD;
    
    static {
        FILE_RECORD = new File(System.getProperty("user.home")
                + separator + "Tetris by Lima Giran" + separator + "record.ttr");
        if (FILE_RECORD.getParentFile() != null) {
            FILE_RECORD.getParentFile().mkdirs();
        }
    }
}
