/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AppThucDon.view.icon;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class IconTraitim {

    private static ImageIcon loadIcon(String path) {
        ImageIcon icon = new ImageIcon(IconTraitim.class.getResource(path));
        Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

    public static ImageIcon heartEmpty = loadIcon("/images/heart.png");
    public static ImageIcon heartFull  = loadIcon("/images/heartfill.png");

    public static void setHeart(JButton btn, boolean liked) {
        btn.setIcon(liked ? heartFull : heartEmpty);

        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setText("");
    }
}