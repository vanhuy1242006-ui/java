/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AppThucDon.view.icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author loan phuong
 */
public class IconTraitim {
     public static ImageIcon heartEmpty = new ImageIcon(
        IconTraitim.class.getResource("/icons/heart.png")
    );

    public static ImageIcon heartFull = new ImageIcon(
        IconTraitim.class.getResource("/icons/heart_fill.png")
    );

    public static void setHeart(JButton btn, boolean liked) {
        btn.setIcon(liked ? heartFull : heartEmpty);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
    }
}
