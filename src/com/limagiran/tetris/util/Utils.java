package com.limagiran.tetris.util;

import com.limagiran.tetris.piece.*;
import com.limagiran.tetris.control.*;
import com.limagiran.tetris.piece.Piece.EnumOrientation;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;
import static com.limagiran.tetris.util.Values.RATE;

/**
 *
 * @author Vinicius Silva
 */
public class Utils {

    public static boolean isColision(Structure s, List<Vector2D> l) {
        return l.stream().anyMatch((v1)
                -> ((v1.y >= s.height) || (v1.x >= s.width) || (v1.x < 0)
                || s.nodes.stream().anyMatch((v2) -> ((v1.x == v2.x) && (v1.y == v2.y)))));
    }

    /**
     * Verifica se o movimento gera uma colisão
     *
     * @param s estrutura do jogo
     * @param p peça movimentada
     * @param d direção do movimento
     * @return <i>true</i> para colisão. <i>false</i> o contrário.
     */
    public static boolean validateMove(Structure s, Piece p, Vector2D d) {
        Piece p2 = RWObj.clonarObjeto(p, Piece.class);
        p2.move(d);
        return !isColision(s, p2.getCorpo());
    }

    /**
     * Verifica se houve game over na estrutura
     *
     * @param s estrutura
     * @return <i>true</i> ou <i>false</i>
     */
    public static boolean isGameOver(Structure s) {
        return s.nodes.stream().anyMatch((v) -> (v.y < 0));
    }

    /**
     * Rotaciona a peça
     *
     * @param s estrutura onde se encontra a peça
     * @param currentPiece peça a ser rotacionada
     */
    public static void rotate(Structure s, Piece currentPiece) {
        Piece p = RWObj.clonarObjeto(currentPiece, Piece.class);
        EnumOrientation nep = p.getOrientation().next();
        Vector2D i = p.getCorpo().get(0);
        for (List<Vector2D> v : FormsPiece.get(p.type, nep, i)) {
            p.getCorpo().clear();
            p.getCorpo().addAll(v);
            if (!isColision(s, p.getCorpo())) {
                currentPiece.getCorpo().clear();
                currentPiece.getCorpo().addAll(v);
                currentPiece.setOrientation(nep);
                break;
            }
        }
    }

    /**
     * Adiciona uma borda para efeito 3D na peça
     *
     * @param x coordenada x
     * @param y coordenada y
     * @param color cor
     * @param g2 contexto gráfico
     */
    public static void addBorder(int x, int y, Color color, Graphics2D g2) {
        g2.setStroke(new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.setColor(color.brighter().brighter());
        g2.drawLine(++x, ++y, (x + RATE - 3), y);
        g2.drawLine(x, y, x, (y + RATE - 3));
        g2.setColor(color.darker());
        x += (RATE - 2);
        y += (RATE - 2);
        g2.drawLine(x, y, (x - RATE + 2), y);
        g2.drawLine(x, y, x, (y - RATE + 2));
    }

    /**
     * Gera um número aleatório
     *
     * @param limit valor máximo a ser retornado
     * @return um número aleatório entre 1 e o limite informado. Retorna -1 para
     * valores menores do que 1.
     */
    public static int random(int limit) {
        return ((limit < 1) ? -1 : (int) ((Math.random() * 100000) % (limit)) + 1);
    }

    /**
     * Thread.sleep();
     *
     * @param time tempo em espera
     */
    public static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception ignore) {
        }
    }
    
}
