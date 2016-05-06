package com.limagiran.tetris.view;

import com.limagiran.tetris.util.RWObj;
import static com.limagiran.tetris.Tetris.ICONS;
import com.limagiran.tetris.control.*;
import com.limagiran.tetris.piece.*;
import com.limagiran.tetris.util.*;
import static com.limagiran.tetris.util.Bounds.*;
import static com.limagiran.tetris.util.Moves.*;
import static com.limagiran.tetris.util.Utils.validateMove;
import static com.limagiran.tetris.util.Values.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import javax.swing.JFrame;

/**
 *
 * @author Vinicius Silva
 */
public class View extends JFrame {

    

    protected final List<Vector2D> moves = Collections.synchronizedList(new ArrayList<>());
    private Vector2D initPos;
    private Vector2D initPosNextPiece;
    protected int level = 1;
    private int timeControl = 0;
    protected int GAME_STATUS = STATUS_MENU;
    protected Structure structure = new Structure(COLUMNS, LINES, level);
    protected long record = getRecord();
    protected Piece currentPiece;
    protected Piece nextPiece;
    protected final List<Piece> animation = new ArrayList<>();
    protected final List<Vector2D> animationMove = new ArrayList<>();

    /**
     * Nova Instância do jogo
     */
    protected View() {
        init();
    }

    /**
     * Iniciar e configurar os componentes
     */
    public final void init() {
        setIgnoreRepaint(true);
        setResizable(false);
        setTitle("Tetris by Lima Giran");
        setIconImages(ICONS);
        pack();
        setSize((CG_GENERAL.width + getInsets().left + getInsets().right),
                (CG_GENERAL.height + getInsets().top + getInsets().bottom));
        setPreferredSize(getSize());
        setLocationRelativeTo(null);
        CG_GENERAL.setBounds(getInsets().left, getInsets().top, SCREEN.width, SCREEN.height);
        initPos = new Vector2D((COLUMNS / 2), 0);
        initPosNextPiece = new Vector2D(((RECT_GAME_NEXT_PIECE.width / RATE) / 2),
                (((RECT_GAME_NEXT_PIECE.height / RATE)) / 2));
        currentPiece = FactoryPiece.random(initPos);
        nextPiece = FactoryPiece.random(initPosNextPiece);
        IntStream.range(0, 12).forEach((i) -> {
            int x = Utils.random(SCREEN.width / RATE) - 1;
            int y = Utils.random(SCREEN.height / RATE) / 2;
            animation.add(FactoryPiece.random(new Vector2D(x, y)));
            animationMove.add(randomMove());
        });
    }

    /**
     * Inicia uma partida
     */
    protected void play() {
        GAME_STATUS = STATUS_PLAY;
        structure = new Structure(COLUMNS, LINES, level);
        moves.clear();
        timeControl = 0;
    }

    /**
     * Exibe a tela de GameOver
     */
    private void gameOver() {
        GAME_STATUS = STATUS_GAME_OVER;
        new Thread(() -> {
            record = ((structure.getPoints() > record) ? structure.getPoints() : record);
            Utils.sleep(2000);
            GAME_STATUS = STATUS_MENU;
        }).start();
    }

    /**
     * Retorna o record salvo
     *
     * @return record
     */
    private long getRecord() {
        Long _return = RWObj.lerObjeto(FILE_RECORD, Long.class, Values.KEY);
        return ((_return == null) ? 0L : _return);
    }

    /**
     * Processamento lógico do menu principal
     */
    protected void processMenu() {
        //animação
        if (((++timeControl) % 10 == 0)) {
            for (int i = 0; i < animation.size(); i++) {
                if (Utils.random(20) == 1) {
                    animationMove.set(i, randomMove());
                }
                animation.get(i).moveAndNormalizePoints(animationMove.get(i),
                        (SCREEN.width / RATE), (SCREEN.height / RATE));
            }
            timeControl = 0;
        }
    }

    /**
     * Processamento lógico do jogo
     */
    protected void processGame() {
        move();
        gravity();
        if (Utils.isGameOver(structure)) {
            gameOver();
        }
    }

    /**
     * Executa um movimento para baixo na peça atual. Se não tiver mais como
     * mover para baixo, junta a peça à estrutura e coloca a próxima peça em
     * jogo. O método é executado de acordo com o level. Quanto maior o level,
     * mais vezes por segundo será executado.
     */
    private void gravity() {
        if (((++timeControl) % (MainLoop.DEFAULT_UPS / structure.getLevel()) == 0)) {
            if (validateMove(structure, currentPiece, DOWN)) {
                //move piece to down
                currentPiece.move(DOWN);
            } else {
                //join piece into structure
                structure.joinPiece(currentPiece);
                currentPiece = FactoryPiece.create(initPos, nextPiece.type);
                nextPiece = FactoryPiece.random(initPosNextPiece);
            }
            timeControl = 0;
        }
    }

    /**
     * Executa os movimentos feitos pelo jogador
     */
    private void move() {
        if (!moves.isEmpty()) {
            Vector2D m = moves.remove(0);
            if (m.equals(UP)) {
                Utils.rotate(structure, currentPiece);
            } else if ((m.equals(RIGHT) || m.equals(LEFT) || m.equals(DOWN))
                    && validateMove(structure, currentPiece, m)) {
                currentPiece.move(m);
            }
        }
    }
}
