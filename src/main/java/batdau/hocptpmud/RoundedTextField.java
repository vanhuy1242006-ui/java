/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package batdau.hocptpmud;

/**
 *
 * @author loan phuong
 */

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class RoundedTextField extends JTextField {

    public RoundedTextField() {
        setOpaque(false);
        setBorder(new EmptyBorder(5, 15, 5, 15));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.WHITE);
        g2.fillRoundRect(
                0, 0,
                getWidth(), getHeight(),
                getHeight(), getHeight());

        super.paintComponent(g);
        g2.dispose();
    }
}
