/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cookingapp;

/**
 *
 * @author loan phuong
 */
import java.awt.*;
import javax.swing.*;

public class RoundedPanel extends JPanel {
    
    public RoundedPanel() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground());

        // Bo cong hoàn toàn 2 đầu
        g2.fillRoundRect(
                0,
                0,
                getWidth(),
                getHeight(),
                getHeight(),
                getHeight());

        g2.dispose();
        
    
    }
}
