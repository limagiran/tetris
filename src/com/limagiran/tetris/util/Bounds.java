package com.limagiran.tetris.util;

import java.awt.Rectangle;

/**
 *
 * @author Vinicius Silva
 */
public class Bounds {

    public static final Rectangle RECT_MENU_PLAY;
    public static final Rectangle RECT_MENU_LEVEL;
    public static final Rectangle RECT_MENU_LEVEL_LESS;
    public static final Rectangle RECT_MENU_LEVEL_MORE;
    public static final Rectangle RECT_GAME_PANEL_RECORD;
    public static final Rectangle RECT_GAME_RECORD_LABEL;
    public static final Rectangle RECT_GAME_RECORD;
    public static final Rectangle RECT_GAME_PANEL_INFO;
    public static final Rectangle RECT_GAME_INFO_POINTS_LABEL;
    public static final Rectangle RECT_GAME_INFO_POINTS;
    public static final Rectangle RECT_GAME_INFO_LEVEL_LABEL;
    public static final Rectangle RECT_GAME_INFO_LEVEL;
    public static final Rectangle RECT_GAME_PLAY;
    public static final Rectangle RECT_GAME_NEXT_PIECE;
    public static final Rectangle RECT_GAME_PAUSE;
    public static final Rectangle RECT_GAME_OVER;
    public static final Rectangle RECT_GAME_OVER_DETAILS;

    static {
        
        int mh = 20;
        RECT_MENU_PLAY = new Rectangle(75, 125, 450, 100);
        RECT_MENU_LEVEL = new Rectangle(75, 275, 450, 100);
        RECT_MENU_LEVEL_LESS = new Rectangle(75, 275, 75, 100);
        RECT_MENU_LEVEL_MORE = new Rectangle(450, 275, 75, 100);
        
        RECT_GAME_PANEL_RECORD = new Rectangle(25, (25 + mh), 150, 60);
        RECT_GAME_RECORD_LABEL = new Rectangle(25, (25 + mh), 150, 30);
        RECT_GAME_RECORD = new Rectangle(25, (55 + mh), 150, 30);
        RECT_GAME_PANEL_INFO = new Rectangle(25, (170 + mh), 150, 160);
        RECT_GAME_INFO_POINTS_LABEL = new Rectangle(25, (170 + mh), 150, 40);
        RECT_GAME_INFO_POINTS = new Rectangle(25, (210 + mh), 150, 40);
        RECT_GAME_INFO_LEVEL_LABEL = new Rectangle(25, (250 + mh), 150, 40);
        RECT_GAME_INFO_LEVEL = new Rectangle(25, (290 + mh), 150, 40);
        RECT_GAME_PLAY = new Rectangle(200, (50 + mh), 200, 400);
        RECT_GAME_NEXT_PIECE = new Rectangle(420, (20 + mh), 160, 140);
        
        RECT_GAME_PAUSE = new Rectangle(75, 175, 450, 100);
        RECT_GAME_OVER = new Rectangle(75, 175 , 450, 100);
        RECT_GAME_OVER_DETAILS = new Rectangle(75, 275, 450, 50);
    }
}
