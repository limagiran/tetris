package com.limagiran.tetris.piece;

import com.limagiran.tetris.control.Vector2D;
import com.limagiran.tetris.piece.Piece.*;
import com.limagiran.tetris.util.Utils;

/**
 *
 * @author Vinicius Silva
 */
public class FactoryPiece {

    /**
     * Cria um novo objeto Piece
     *
     * @param initPos posição inicial da peça. A posição inicial
     * refere-se à parte inferior da peça.
     * @param type tipo da peça a ser criada (travessão, pirâmide, L, etc...)
     * @return Objeto Piece do typo passado por parâmetro
     * @throws NullPointerException se initPos == null
     */
    public static Piece create(Vector2D initPos, EnumPiece type) {
        if (initPos == null) {throw new NullPointerException();}
        return new Piece(type.color, FormsPiece.getFirst(type, EnumOrientation.UP, initPos), type);
    }

    /**
     * Cria um novo objeto Piece aleatório
     *
     * @param initPos posição inicial da peça. A posição inicial
     * refere-se à parte inferior da peça.
     * @return Objeto Piece
     * @throws NullPointerException se initPos == null
     */
    public static Piece random(Vector2D initPos) throws NullPointerException {
        if (initPos == null) {throw new NullPointerException();}
        return create(initPos, EnumPiece.values()[(Utils.random(EnumPiece.values().length) - 1)]);
    }
}
