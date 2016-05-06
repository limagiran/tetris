package com.limagiran.tetris.view;

import static com.limagiran.tetris.util.G2Util.*;
import com.limagiran.tetris.control.LoopSteps;
import static com.limagiran.tetris.util.Bounds.*;
import static com.limagiran.tetris.util.Values.*;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.stream.IntStream;

/**
 *
 * @author Vinicius Silva
 */
public class Control extends View implements LoopSteps {

    /**
     * Efetuar o processo lógico do game
     */
    @Override
    public void processLogics() {
        switch (GAME_STATUS) {
            case STATUS_MENU:
                processMenu();
                break;
            case STATUS_PLAY:
                processGame();
                break;
        }
    }

    /**
     * Pintar o Graphics2D para exibição na tela
     */
    @Override
    public void renderGraphics() {
        Graphics2D g2 = (Graphics2D) getBufferStrategy().getDrawGraphics()
                .create(CG_GENERAL.x, CG_GENERAL.y, CG_GENERAL.width, CG_GENERAL.height);
        configGraphics(g2);
        g2.setColor(COLOR_BACKGROUND);
        g2.fillRect(0, 0, getWidth(), getHeight());
        switch (GAME_STATUS) {
            case STATUS_PAUSE:
            case STATUS_PLAY:
                paintRecord(g2);
                paintInfo(g2);
                paintStructure();
                if (GAME_STATUS == STATUS_PAUSE) {
                    paintPause(g2);
                }
                break;
            case STATUS_MENU:
                paintMenu(g2);
                break;
            case STATUS_GAME_OVER:
                paintGameOver(g2);
                break;
        }
        g2.dispose();
    }

    /**
     * Pintar a tela
     */
    @Override
    public void paintScreen() {
        if (!getBufferStrategy().contentsLost()) {
            getBufferStrategy().show();
        }
    }

    /**
     * Configuração inicial da partida
     */
    @Override
    public void setup() {
        createBufferStrategy(2);
    }

    /**
     * Finalizar partida
     */
    @Override
    public void tearDown() {
        //ignore
    }

    /**
     * Pinta o cabeçalho com as informações do jogo
     *
     * @param g Graphics2D
     */
    private void paintInfo(Graphics2D g) {
        g.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g.setColor(COLOR_MENU);
        drawRect(RECT_GAME_PANEL_INFO, g);
        String points = "Pontuação";
        String lvl = "Level";
        g.setFont(FONTE_DEFAULT);
        drawStringCenter(points, RECT_GAME_INFO_POINTS_LABEL, FONTE_DEFAULT, g);
        drawStringCenter(structure.getPoints(), RECT_GAME_INFO_POINTS, FONTE_DEFAULT, g);
        drawStringCenter(lvl, RECT_GAME_INFO_LEVEL_LABEL, FONTE_DEFAULT, g);
        drawStringCenter(structure.getLevel(), RECT_GAME_INFO_LEVEL, FONTE_DEFAULT, g);
    }

    /**
     * Pintar a tela de menu
     *
     * @param g Graphics2D
     */
    private void paintMenu(Graphics2D g) {
        paintAnimationMenu(g);
        paintRecord(g);
        Stroke stroke = g.getStroke();
        g.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g.setColor(COLOR_BACKGROUND);
        fillRect(RECT_MENU_PLAY, g);
        fillRect(RECT_MENU_LEVEL, g);
        g.setColor(COLOR_MENU);
        drawRect(RECT_MENU_PLAY, g);
        drawRect(RECT_MENU_LEVEL, g);
        g.setStroke(stroke);
        g.setFont(FONTE_MENU);
        drawStringCenter("Jogar", RECT_MENU_PLAY, FONTE_MENU, g);
        drawStringCenter("Level " + level, RECT_MENU_LEVEL, FONTE_MENU, g);
        drawStringCenter("-", RECT_MENU_LEVEL_LESS, FONTE_MENU, g);
        drawStringCenter("+", RECT_MENU_LEVEL_MORE, FONTE_MENU, g);
    }

    /**
     * Pintar a animação de fundo
     *
     * @param g2 Graphics2D
     */
    private void paintAnimationMenu(Graphics2D g2) {
        animation.stream().forEach((p) -> {
            p.paint(g2);
        });
    }

    private void paintGameOver(Graphics2D g) {
        String gameOver = "GAME OVER";
        String points = "PONTUAÇÃO: " + structure.getPoints();
        g.setColor(COLOR_MENU);
        g.setFont(FONTE_MENU);
        drawStringCenter(gameOver, RECT_GAME_OVER, FONTE_MENU, g);
        g.setFont(FONTE_DEFAULT);
        drawStringCenter(points, RECT_GAME_OVER_DETAILS, FONTE_DEFAULT, g);
    }

    private void paintPause(Graphics2D g) {
        String pause = "PAUSE";
        g.setColor(new Color(220, 220, 220, 220));
        g.fillRect(0, RECT_GAME_PAUSE.y, g.getClipBounds().width, RECT_GAME_PAUSE.height);
        g.setColor(COLOR_PAUSE);
        g.setFont(FONTE_MENU);
        drawStringCenter(pause, RECT_GAME_PAUSE, FONTE_MENU, g);
    }

    protected void setLevel(int level) {
        this.level = ((level < 1) ? 1 : ((level > MAX_LEVEL) ? MAX_LEVEL : level));
    }

    private void paintRecord(Graphics2D g) {
        Stroke stroke = g.getStroke();
        g.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g.setColor(COLOR_BACKGROUND);
        fillRect(RECT_GAME_PANEL_RECORD, g);
        g.setColor(COLOR_MENU);
        drawRect(RECT_GAME_PANEL_RECORD, g);
        String str = "Recorde";
        g.setFont(FONTE_DEFAULT);
        drawStringCenter(str, RECT_GAME_RECORD_LABEL, FONTE_DEFAULT, g);
        drawStringCenter(record, RECT_GAME_RECORD, FONTE_DEFAULT, g);
        g.setStroke(stroke);
    }

    private void paintNextPiece() {
        Graphics2D g = (Graphics2D) getBufferStrategy().getDrawGraphics()
                .create((RECT_GAME_NEXT_PIECE.x + getInsets().left),
                        (RECT_GAME_NEXT_PIECE.y + getInsets().top),
                        RECT_GAME_NEXT_PIECE.width,
                        RECT_GAME_NEXT_PIECE.height);
        configGraphics(g);
        g.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g.setColor(COLOR_MENU);
        g.drawRect(3, 3, (RECT_GAME_NEXT_PIECE.width - 6), (RECT_GAME_NEXT_PIECE.height - 6));
        nextPiece.paint(g);
        g.dispose();
    }

    private void paintModelStructure() {
        Graphics2D g = (Graphics2D) getBufferStrategy().getDrawGraphics()
                .create(RECT_GAME_PLAY.x - 6, RECT_GAME_PLAY.y - 6,
                        RECT_GAME_PLAY.width + 12, RECT_GAME_PLAY.height + 12);
        configGraphics(g);
        g.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g.setColor(COLOR_MENU);
        g.drawRect(3, 3, (RECT_GAME_PLAY.width + 5), (RECT_GAME_PLAY.height + 5));
        g.dispose();
        paintGrid();
    }

    private void paintGrid() {
        Graphics2D g = (Graphics2D) getBufferStrategy().getDrawGraphics()
                .create(RECT_GAME_PLAY.x, RECT_GAME_PLAY.y,
                        RECT_GAME_PLAY.width, RECT_GAME_PLAY.height);
        g.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g.setColor(new Color(25, 25, 25));
        IntStream.range(1, COLUMNS).map(i -> (i * RATE))
                .forEach(i -> g.drawLine(i, 0, i, g.getClipBounds().height));
        IntStream.range(1, LINES).map(i -> (i * RATE))
                .forEach(i -> g.drawLine(0, i, g.getClipBounds().width, i));
        g.dispose();
    }

    private void paintCurrentPiece() {
        Graphics2D g = (Graphics2D) getBufferStrategy().getDrawGraphics()
                .create(RECT_GAME_PLAY.x, RECT_GAME_PLAY.y,
                        RECT_GAME_PLAY.width, RECT_GAME_PLAY.height);
        currentPiece.paint(g);
        g.dispose();
    }

    private void paintStructure() {
        paintModelStructure();
        Graphics2D g = (Graphics2D) getBufferStrategy().getDrawGraphics()
                .create(RECT_GAME_PLAY.x, RECT_GAME_PLAY.y,
                        RECT_GAME_PLAY.width, RECT_GAME_PLAY.height);
        structure.paint(g);
        g.dispose();
        paintCurrentPiece();
        paintNextPiece();
    }
}
