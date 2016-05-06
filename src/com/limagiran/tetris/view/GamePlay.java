package com.limagiran.tetris.view;

import com.limagiran.tetris.control.MainLoop;
import javax.swing.SwingUtilities;

/**
 *
 * @author Vinicius Silva
 */
public final class GamePlay extends Listeners {
    
    private static GamePlay instance;
    private final MainLoop loop = new MainLoop(this);
    
    private GamePlay() {
        initThread();
    }
    
    public void initThread() {
        new Thread(loop).start();
    }
    /**
     * Cria e exibe uma janela do jogo
     */
    public static void main() {
        if (instance == null) {
            SwingUtilities.invokeLater(() -> {
                (instance = new GamePlay()).setVisible(true);
            });
        }
    }
}
