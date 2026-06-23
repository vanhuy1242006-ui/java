package cookingapp;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SearchTextField extends JTextField {

    private String placeholder = "Tìm kiếm...";
    private final Color borderColor = new Color(220, 220, 220);

    public SearchTextField() {
        setOpaque(false);
        setBorder(new EmptyBorder(8, 40, 8, 15));
        setBackground(Color.WHITE);
        setCaretColor(Color.BLACK);
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        repaint();
    }

    @Override
    public void paint(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        int arc = getHeight();

        Shape shape = new RoundRectangle2D.Float(
                0,
                0,
                getWidth(),
                getHeight(),
                arc,
                arc);

        g2.setClip(shape);

        // nền
        g2.setColor(Color.WHITE);
        g2.fill(shape);

        super.paint(g2);

        // viền
        g2.setClip(null);
        g2.setColor(borderColor);
        g2.draw(shape);

        // icon kính lúp
        drawSearchIcon(g2);

        // placeholder
        if (getText().isEmpty()
                && !isFocusOwner()
                && placeholder != null) {

            g2.setColor(new Color(150, 150, 150));

            FontMetrics fm = g2.getFontMetrics();

            int y = (getHeight() - fm.getHeight()) / 2
                    + fm.getAscent();

            g2.drawString(placeholder, 40, y);
        }

        g2.dispose();
    }

    private void drawSearchIcon(Graphics2D g2) {

        g2.setStroke(new BasicStroke(2f));
        g2.setColor(new Color(120, 120, 120));

        int size = 14;
        int x = 14;
        int y = (getHeight() - size) / 2;

        g2.drawOval(x, y, size, size);

        g2.drawLine(
                x + size - 1,
                y + size - 1,
                x + size + 6,
                y + size + 6);
    }
}