package com.limagiran.tetris.piece;

import com.limagiran.tetris.util.*;
import com.limagiran.tetris.control.Vector2D;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.List;
import static com.limagiran.tetris.util.Values.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Vinicius Silva
 */
public class Piece implements Serializable {

    private EnumOrientation orientation;
    public final Color color;
    private final List<Vector2D> body;
    public final EnumPiece type;

    /**
     * Instância default para uma peça
     *
     * @param color Cor da peça
     * @param body corpo da peça
     * @param type tipo da peça
     */
    protected Piece(Color color, List<Vector2D> body, EnumPiece type) {
        this.color = color;
        this.body = body;
        this.type = type;
        orientation = EnumOrientation.UP;
    }

    /**
     * Pinta a peça
     *
     * @param g2 Graphics2D
     */
    public void paint(Graphics2D g2) {
        G2Util.configGraphics(g2);
        body.stream().forEach((v) -> {
            g2.setColor(color);
            int x = ((int) v.x * RATE);
            int y = ((int) v.y * RATE);
            g2.fillRect(x, y, RATE, RATE);
            Utils.addBorder(x, y, color, g2);
        });
    }

    /**
     * Movimenta a peça
     *
     * @param direcao direção no formato (x, y) para movimentar a peça.
     * <br>Ex.: (1, 0) direita, (-1, 0) esquerda, ...
     *
     */
    public void move(Vector2D direcao) {
        body.stream().forEach(b -> b.addMe(direcao));
    }

    /**
     * Movimenta a peça
     *
     * @param direcao direção no formato (x, y) para movimentar a peça.
     * <br>Ex.: (1, 0) direita, (-1, 0) esquerda, ...
     * @param w largura do contexto gráfico
     * @param h altura do contexto gráfico
     *
     */
    public void moveAndNormalizePoints(Vector2D direcao, int w, int h) {
        move(direcao);
        body.stream().forEach(b -> b.set(normalize((int) b.x, w), normalize((int) b.y, h)));
    }

    /**
     * Normalizar a peça dentro da tela, para quando a peça sair pela lateral
     * esquerda, aparecer na lateral direita, vice-e-versa.
     *
     * @param x coordenada (x ou y)
     * @param limit coordenada limite (width ou height)
     * @return x dentro dos limites da coordenada
     */
    private static int normalize(int x, int limit) {
        return (((x < 0) ? (limit - (Math.abs(x) % limit)) : x) % limit);
    }

    /**
     * Retorna a lista de coordenadas onde cada pedaço do body da peça está
     *
     * @return Lista de Vector2D
     */
    public List<Vector2D> getCorpo() {
        return body;
    }

    public Piece setOrientation(EnumOrientation orientation) {
        this.orientation = orientation;
        return this;
    }

    public EnumOrientation getOrientation() {
        return orientation;
    }

    public static enum EnumOrientation {
        UP, RIGHT, DOWN, LEFT;
        private static final List<EnumOrientation> list
                = new ArrayList<>(Arrays.asList(EnumOrientation.values()));

        public EnumOrientation next() {
            return list.get((list.indexOf(this) + 1) % list.size());
        }
    }

    public static enum EnumPiece {
        DASH(194, 29, 36, "O,U,D,DD", "O,L,R,RR;R,RR,RRR,O;L,LL,O,R;LL,LLL,L,O"),
        INVERTEDL(162, 62, 155, "O,L,LU,R;R,RR,O,U;L,LL,O,LLU", "O,R,RD,L;L,LL,O,D;R,RR,O,RRD", "O,D,DL,U", "O,U,UR,D"),
        NORMALL(205, 202, 48, "O,R,RU,L;L,LL,O,U;R,RR,O,RRU", "O,L,LD,R;R,RR,O,D;L,LL,O,LLD", "O,U,UL,D", "O,D,DR,U"),
        PYRAMID(115, 115, 115, "O,R,L,U;L,LL,O,LU;R,RR,O,RU", "O,R,L,D;L,LL,O,LD;R,RR,O,RD", "O,U,D,L;L,O,LD,LU", "O,U,D,R;R,O,RD,RU"),
        S(41, 62, 162, "O,U,UR,L;R,O,RU,RUR", "O,R,RD,U;L,O,D,LU"),
        Z(49, 179, 73, "O,U,UL,R;L,O,LU,LUL", "O,L,LD,U;R,O,D,RU"),
        SQUARE(25, 183, 187, "O,L,LU,U", "O,L,LU,U");

        public final Color color;
        public final String up[];
        public final String down[];
        public final String left[];
        public final String right[];

        private EnumPiece(int r, int g, int b, String... forms) {
            color = new Color(r, g, b);
            up = forms[0].split(";");
            down = forms[((forms.length == 4) ? 1 : 0)].split(";");
            left = forms[((forms.length == 4) ? 2 : 1)].split(";");
            right = forms[((forms.length == 4) ? 3 : 1)].split(";");
        }
    }
}
