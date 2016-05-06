package com.limagiran.tetris;

import com.limagiran.tetris.view.GamePlay;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

/**
 *
 * @author Vinicius Silva
 */
public class Tetris {

    public static final List<Image> ICONS = new ArrayList<>();

    static {
        String dir = "/com/limagiran/tetris/img/icon?.png";
        for (String s : new String[]{"16", "24", "32", "48", "64"}) {
            ICONS.add(new ImageIcon(Tetris.class.getResource(dir.replace("?", s))).getImage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GamePlay.main();
    }

}
