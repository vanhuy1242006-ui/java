package AppThucDon.model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author duc
 */
public class User {
    private String tendangnhap;
    private String password;

    public User(String tendangnhap, String password) {
        this.tendangnhap = tendangnhap;
        this.password = password;
    }
    public void hienthithongtin(){
        System.out.printf(this.tendangnhap);
        System.out.printf(this.password);
    }
    // Getter/Setter bên dưới...
}
