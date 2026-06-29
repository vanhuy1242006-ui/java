/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package AppThucDon.dao.FormDangNhap;

import AppThucDon.dao.FormDangNhap.EditProfileFirstTime;
import AppThucDon.dao.FormDangNhap.JFrameLoginForm;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class UserPanel extends javax.swing.JPanel {

    private String username;

    public UserPanel(
            String username,
            String displayName,
            String avatar,
            String bio
    ) {

        initComponents();
        

        lblDisplayName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUserID.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btnLogout.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {

                btnLogout.setBackground(
                        new Color(190, 50, 50));

            }

            @Override
            public void mouseExited(MouseEvent e) {

                btnLogout.setBackground(
                        new Color(220, 70, 70));

            }

        });

        

        btnLogout.setFocusPainted(false);

        btnLogout.setBackground(
                new Color(220, 70, 70));

        btnLogout.setForeground(Color.WHITE);

        btnEdit.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {

                btnEdit.setBackground(
                        new Color(255, 245, 220));

            }

            @Override
            public void mouseExited(MouseEvent e) {

                btnEdit.setBackground(
                        Color.orange);

            }

        });

        

        btnEdit.setBackground(Color.orange);

        btnEdit.setForeground(
                new Color(255, 255, 255));

        btnEdit.setFocusPainted(false);

        btnEdit.setBorder(
                BorderFactory.createLineBorder(
                        new Color(232, 181, 53), 2, true));

        jScrollPane1.setBorder(null);

        jScrollPane1.getViewport().setBackground(
                new Color(255, 253, 247));

        txtBio.setEditable(false);

        txtBio.setLineWrap(true);

        txtBio.setWrapStyleWord(true);

        txtBio.setFont(
                new Font("Segoe UI", Font.PLAIN, 15));

        txtBio.setBackground(
                new Color(255, 253, 247));

        txtBio.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 10, 10, 10));

        lblUserID.setFont(
                new Font("Segoe UI", Font.PLAIN, 18));

        lblUserID.setForeground(
                new Color(120, 120, 120));

        lblDisplayName.setFont(
                new Font("Segoe UI", Font.BOLD, 28));

        lblDisplayName.setForeground(
                new Color(154, 98, 20));

        lblAvatar.setBorder(
                BorderFactory.createLineBorder(
                        new Color(232, 181, 53), 2, true));

        lblAvatar.setHorizontalAlignment(
                SwingConstants.CENTER);

        lblAvatar.setBackground(Color.WHITE);

        txtBio.setEditable(false);

        this.username = username;

        loadUser(
                username,
                displayName,
                avatar,
                bio
        );
    }

    public UserPanel() {
        initComponents();

        txtBio.setEditable(false);
    }

    private void loadUser(
            String username,
            String displayName,
            String avatar,
            String bio
    ) {

        // hiện @username
        lblUserID.setText(
                username == null
                        ? ""
                        : "@" + username
        );

        // hiện tên thật
        lblDisplayName.setText(
                displayName == null
                        ? username
                        : displayName
        );

        txtBio.setText(
                bio == null
                        ? ""
                        : bio
        );

        try {

            if (avatar != null
                    && !avatar.isEmpty()) {

                File f = new File(avatar);

                if (f.exists()) {

                    ImageIcon icon
                            = new ImageIcon(
                                    avatar
                            );

                    Image img
                            = icon
                                    .getImage()
                                    .getScaledInstance(
                                            150,
                                            150,
                                            Image.SCALE_SMOOTH
                                    );

                    lblAvatar.setText("");

                    lblAvatar.setIcon(
                            new ImageIcon(img)
                    );

                }

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblDisplayName = new javax.swing.JLabel();
        lblAvatar = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtBio = new javax.swing.JTextArea();
        btnEdit = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        lblUserID = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblDisplayName.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        add(lblDisplayName, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 300, 310, 41));

        lblAvatar.setBackground(new java.awt.Color(255, 255, 255));
        lblAvatar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblAvatar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAvatar.setMaximumSize(new java.awt.Dimension(150, 150));
        lblAvatar.setMinimumSize(new java.awt.Dimension(150, 150));
        lblAvatar.setOpaque(true);
        lblAvatar.setPreferredSize(new java.awt.Dimension(150, 150));
        add(lblAvatar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 130, -1, -1));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtBio.setColumns(20);
        txtBio.setRows(5);
        jScrollPane1.setViewportView(txtBio);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 456, 420, 80));

        btnEdit.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pencil (1).png"))); // NOI18N
        btnEdit.setText("Chỉnh sửa hồ sơ");
        btnEdit.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnEdit.addActionListener(this::btnEditActionPerformed);
        add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 550, 190, 61));

        btnLogout.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/door.png"))); // NOI18N
        btnLogout.setText("Đăng xuất");
        btnLogout.addActionListener(this::btnLogoutActionPerformed);
        add(btnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 550, 187, 61));

        lblUserID.setFont(new java.awt.Font("Segoe UI", 2, 16)); // NOI18N
        lblUserID.setForeground(new java.awt.Color(140, 140, 140));
        add(lblUserID, new org.netbeans.lib.awtextra.AbsoluteConstraints(229, 350, 280, 41));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(154, 98, 20));
        jLabel1.setText("JavaCook Profiles");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, 290, 47));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Giới thiệu");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 410, -1, -1));

        jSeparator1.setForeground(new java.awt.Color(230, 210, 170));
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 443, 420, 20));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/nen.png"))); // NOI18N
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        int confirm
                = JOptionPane.showConfirmDialog(
                        this,
                        "Đăng xuất?",
                        "Xác nhận",
                        JOptionPane.YES_NO_OPTION
                );

        if (confirm
                == JOptionPane.YES_OPTION) {

            CurrentUser.userId = 0;
            CurrentUser.username = null;
            CurrentUser.displayName = null;
            CurrentUser.avatar = null;
            CurrentUser.bio = null;

            JFrameLoginForm login
                    = new JFrameLoginForm();

            login.setVisible(true);

            javax.swing.SwingUtilities
                    .getWindowAncestor(this)
                    .dispose();

        }


    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        EditProfileFirstTime edit
                = new EditProfileFirstTime(
                        CurrentUser.username
                );

        edit.setVisible(true);

        javax.swing.SwingUtilities
                .getWindowAncestor(this)
                .dispose();
    }//GEN-LAST:event_btnEditActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnLogout;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblAvatar;
    private javax.swing.JLabel lblDisplayName;
    private javax.swing.JLabel lblUserID;
    private javax.swing.JTextArea txtBio;
    // End of variables declaration//GEN-END:variables
}
