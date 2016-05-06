package com.limagiran.tetris.util;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import static java.awt.RenderingHints.*;

/**
 *
 * @author Vinicius Silva
 */
public class G2Util {

    /**
     * DrawRect
     *
     * @param r Rectangle
     * @param g Graphics2D
     * @see Graphics2D#drawRect(int, int, int, int)
     */
    public static void drawRect(Rectangle r, Graphics2D g) {
        g.drawRect(r.x, r.y, r.width, r.height);
    }

    /**
     * FillRect
     *
     * @param r Rectangle
     * @param g Graphics2D
     * @see Graphics2D#fillRect(int, int, int, int)
     */
    public static void fillRect(Rectangle r, Graphics2D g) {
        g.fillRect(r.x, r.y, r.width, r.height);
    }
    
    /**
     * Retorna o ponto para desenhar um texto centralizado em um retângulo, em
     * um contexto Graphics2D
     *
     * @param t texto a ser desenhado
     * @param r área em objeto retângulo
     * @param f fonte do texto
     * @param g2 Graphics2D
     * @return Point com as coordenadas
     */
    public static Point centralizarTexto(String t, Rectangle r, Font f, Graphics2D g2) {
        int w = r.x + (r.width - g2.getFontMetrics(f).stringWidth(t)) / 2;
        int h = r.y + g2.getFontMetrics(f).getAscent();
        h -= g2.getFontMetrics(f).getDescent();
        h += (r.height - f.getSize()) / 2;
        return new Point(w, h);
    }
    
    /**
     * Configura a renderização padrão para o contexto gráfico
     *
     * @param g contexto gráfico
     */
    public static void configGraphics(Graphics2D g) {
        g.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
        g.setRenderingHint(KEY_INTERPOLATION, VALUE_INTERPOLATION_BICUBIC);
        g.setRenderingHint(KEY_RENDERING, VALUE_RENDER_QUALITY);
        g.setRenderingHint(KEY_ALPHA_INTERPOLATION, VALUE_ALPHA_INTERPOLATION_QUALITY);
    }
    
    /**
     * Desenha o texto centralizado em um retângulo, em um contexto Graphics2D
     *
     * @param o texto a ser desenhado
     * @param r área em objeto retângulo
     * @param f fonte do texto
     * @param g Graphics2D
     */
    public static void drawStringCenter(Object o, Rectangle r, Font f, Graphics2D g) {
        int w = r.x + (r.width - g.getFontMetrics(f).stringWidth(o.toString())) / 2;
        int h = r.y + g.getFontMetrics(f).getAscent();
        h -= g.getFontMetrics(f).getDescent();
        h += (r.height - f.getSize()) / 2;
        g.setFont(f);
        g.drawString(o.toString(), w, h);
    }
}
