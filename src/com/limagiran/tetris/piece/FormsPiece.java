package com.limagiran.tetris.piece;

import com.limagiran.tetris.control.Vector2D;
import com.limagiran.tetris.piece.Piece.*;
import static com.limagiran.tetris.util.Moves.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vinicius Silva
 */
public class FormsPiece {

    /**
     * Cria uma peça
     *
     * @param i posição inicial da forma
     * @param s direções (separadas por vírgula) a partir da posição inicial
     * para formar as demais peças.
     * <br>Ex.: "O,U,UL,R"
     * <br>O - posição inicial
     * <br>U - uma posição acima
     * <br>UL - uma posição acima e à esquerda da inicial
     * <br>R - uma posição à direita
     * <br>(peça no formato Z)
     * @return Lista de Vector2D com a posição de cada ponto da peça
     */
    private static List<Vector2D> createForm(Vector2D i, String s) {
        List<Vector2D> _return = new ArrayList<>();
        for (String p : s.split(",")) {
            _return.add(move(i.clone(), p));
        }
        return _return;
    }

    /**
     * Cria várias peças
     *
     * @param i posição inicial da forma
     * @param s direções (separadas por vírgula) a partir da posição inicial
     * para formar as demais peças.
     * <br>Ex.: "O,U,UL,R"
     * <br>O - posição inicial
     * <br>U - uma posição acima
     * <br>UL - uma posição acima e à esquerda da inicial
     * <br>R - uma posição à direita
     * <br>(peça no formato Z)
     * @return Lista de peças formadas
     * @see FormsPiece#createForm(Vector2D, String)
     */
    private static List<List<Vector2D>> createForms(Vector2D i, String... s) {
        List<List<Vector2D>> _return = new ArrayList<>();
        for (String form : s) {
            _return.add(createForm(i, form));
        }
        return _return;
    }

    /**
     * Criar todas as combinações possíveis com a posição inicial para
     * determinada peça
     *
     * @param ep tipo da peça
     * @param eo orientação da peça. (UP, DOWN, LEFT, RIGHT)
     * @param v posição inicial da peça
     * @return lista de peças criadas
     */
    public static List<List<Vector2D>> get(EnumPiece ep, EnumOrientation eo, Vector2D v) {
        switch (eo) {
            case UP:
                return createForms(v, ep.up);
            case DOWN:
                return createForms(v, ep.down);
            case LEFT:
                return createForms(v, ep.left);
            case RIGHT:
                return createForms(v, ep.right);
            default:
                return new ArrayList<>();
        }
    }

    /**
     * retorna a primeira combinação possível com a posição inicial para
     * determinada peça
     *
     * @param ep tipo da peça
     * @param eo orientação da peça. (UP, DOWN, LEFT, RIGHT)
     * @param v posição inicial da peça
     * @return Lista de Vector2D com a posição de cada ponto da peça
     */
    public static List<Vector2D> getFirst(EnumPiece ep, EnumOrientation eo, Vector2D v) {
        return get(ep, eo, v).get(0);
    }
}
