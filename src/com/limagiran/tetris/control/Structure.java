package com.limagiran.tetris.control;

import com.limagiran.tetris.util.G2Util;
import com.limagiran.tetris.piece.Piece;
import com.limagiran.tetris.util.Utils;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import static com.limagiran.tetris.util.Values.*;
import java.util.stream.IntStream;

/**
 *
 * @author Vinicius Silva
 */
public class Structure {

    public final int width;
    public final int height;
    public final List<Node> nodes;
    private int removedLines = 0;
    private long points = 0L;
    private int level;

    public Structure(int width, int height, int level) {
        nodes = new ArrayList<>();
        this.width = width;
        this.height = height;
        this.level = level;
    }

    public void joinPiece(Piece p) {
        p.getCorpo().stream().map((m) -> (new Node(p.color, m.x, m.y)))
                .filter((n) -> (!nodes.contains(n))).forEach(n -> nodes.add(n));
        refresh();
    }

    private void refresh() {
        int _removedLines = removedLines;
        IntStream.range(0, height).forEach(line -> updateLines(line));
        points += getPoints((removedLines - _removedLines));
    }
    
    private void updateLines(int l) {
        boolean[] array = new boolean[width];
        nodes.stream().filter((n) -> (n.y == l)).forEach(n -> array[n.x] = true);
        for (boolean b : array) {if (!b) {return;}}
        removeLine(l);
        if(((++removedLines) % 25 == 0) && (level < MAX_LEVEL)) {levelUp();}
    }

    private void removeLine(int line) {
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).y == line) {nodes.remove(i--);}
        }
        nodes.stream().filter((n) -> (n.y < line)).forEach((n) -> {n.down();});
    }

    private int getPoints(int removedLines) {
        switch(removedLines) {
            case 1: return (25 * level);
            case 2: return (50 * level);
            case 3: return (100 * level);
            case 4: return (200 * level);
            default: return 0;
        }
    }
    
    public long getPoints() {return points;}
    
    public long getLevel() {return level;}
    
    public void levelUp() {setLevel(level + 1);}
    
    public void setLevel(int l) {
        level = ((l < 1) ? 1 : ((l > MAX_LEVEL) ? MAX_LEVEL : l));
    }

    public void paint(Graphics2D g) {
        G2Util.configGraphics(g);
        nodes.stream().forEach((n) -> {
            g.setColor(n.color);
            g.fillRect((n.x * RATE), (n.y * RATE), RATE, RATE);
            Utils.addBorder((n.x * RATE), (n.y * RATE), n.color, g);
        });
    }

    public static class Node {

        public final Color color;
        public final int x;
        public int y;

        public Node(Color c, double x, double y) throws NullPointerException {
            if (c == null) {throw new NullPointerException();}
            color = c;
            this.x = (int) x;
            this.y = (int) y;
        }

        public void down() {y++;}

        @Override
        public boolean equals(Object obj) {
            if (super.equals(obj)) {return true;}
            if (!(obj instanceof Node)) {return false;}
            Node other = (Node) obj;
            return ((x == other.x) && (y == other.y));
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 19 * hash + x;
            hash = 19 * hash + y;
            return hash;
        }
    }
}
