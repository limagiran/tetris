package com.limagiran.tetris.view;

import com.limagiran.tetris.util.RWObj;
import static com.limagiran.tetris.util.Bounds.*;
import static com.limagiran.tetris.util.Moves.*;
import com.limagiran.tetris.util.Values;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static com.limagiran.tetris.util.Values.*;

/**
 *
 * @author Vinicius Silva
 */
public class Listeners extends Control {

    public Listeners() {
        mouse();
        key();
        windows();
    }

    /**
     * Configura os listeners de evento para o programa
     */
    public final void mouse() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Point p = e.getPoint();
                p.y -= getInsets().top;
                p.x -= getInsets().left;
                if (GAME_STATUS == STATUS_MENU) {
                    if (RECT_MENU_PLAY.contains(p)) {
                        play();
                    } else if (RECT_MENU_LEVEL_LESS.contains(p)) {
                        setLevel(level - 1);
                    } else if (RECT_MENU_LEVEL_MORE.contains(p)) {
                        setLevel(level + 1);
                    }
                }
            }
        });
    }

    public final void key() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        moves.add(UP);
                        break;
                    case KeyEvent.VK_DOWN:
                        moves.add(DOWN);
                        break;
                    case KeyEvent.VK_LEFT:
                        moves.add(LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        moves.add(RIGHT);
                        break;
                    case KeyEvent.VK_ENTER:
                    case KeyEvent.VK_ESCAPE:
                        GAME_STATUS = ((GAME_STATUS == STATUS_PAUSE)
                                ? STATUS_PLAY : ((GAME_STATUS == STATUS_PLAY)
                                        ? STATUS_PAUSE : GAME_STATUS));
                        break;
                }
            }
        });
    }

    public final void windows() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                RWObj.gravarObjeto(record, FILE_RECORD, Values.KEY);
                System.exit(0);
            }
        });
    }
}
