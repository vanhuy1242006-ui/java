/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AppThucDon.view.icon;

/**
 *
 * @author loan phuong
 */
import java.awt.*;
import javax.swing.*;

public class PillButton extends JButton {

    public PillButton() {
        super();
        init();
    }

    public PillButton(String text) {
        super(text);
        init();
    }

    private void init() {
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(false);
        setForeground(Color.WHITE);
        setBackground(new Color(255, 120, 0));
        setMargin(new Insets(8, 20, 8, 20));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // nền nút
        if (getModel().isPressed()) {
            g2.setColor(getBackground().darker());
        } else if (getModel().isRollover()) {
            g2.setColor(getBackground().brighter());
        } else {
            g2.setColor(getBackground());
        }

        g2.fillRoundRect(0, 0, getWidth(), getHeight(),
                getHeight(), getHeight());

        g2.dispose();

        super.paintComponent(g);
    }
}