package com.limagiran.tetris.util;

import com.limagiran.tetris.control.Vector2D;

/**
 *
 * @author Vinicius Silva
 */
public class Moves {

    public static final Vector2D UP = new Vector2D(0, -1);
    public static final Vector2D DOWN = new Vector2D(0, 1);
    public static final Vector2D RIGHT = new Vector2D(1, 0);
    public static final Vector2D LEFT = new Vector2D(-1, 0);
    public static final Vector2D U = UP;
    public static final Vector2D D = DOWN;
    public static final Vector2D R = RIGHT;
    public static final Vector2D L = LEFT;
    
    public static Vector2D up(int x) {
        return new Vector2D(0, -x);
    }

    public static Vector2D down(int x) {
        return new Vector2D(0, x);
    }

    public static Vector2D left(int x) {
        return new Vector2D(-x, 0);
    }

    public static Vector2D right(int x) {
        return new Vector2D(x, 0);
    }

    public static Vector2D u(int x) {
        return up(x);
    }

    public static Vector2D d(int x) {
        return down(x);
    }

    public static Vector2D l(int x) {
        return left(x);
    }

    public static Vector2D r(int x) {
        return right(x);
    }

    public static Vector2D move(Vector2D v, String s) {
        while ((s != null) && (!s.isEmpty())) {
            char c = s.charAt(0);
            switch (c) {
                case 'U':
                    v.addMe(U);
                    break;
                case 'D':
                    v.addMe(D);
                    break;
                case 'L':
                    v.addMe(L);
                    break;
                case 'R':
                    v.addMe(R);
                    break;
            }
            s = s.substring(1);
        }
        return v;
    }

    public static Vector2D randomMove() {
        return new Vector2D[]{U, D, L, R}[(Utils.random(4) - 1)];
    }
}
