package AppThucDon;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

//import java.awt.EventQueue;
import AppThucDon.dao.MonAnDAO;
import AppThucDon.database.Database;
import AppThucDon.model.MonAn;
import AppThucDon.model.User;
import AppThucDon.view.Mainlayout;
import java.sql.Connection;
import java.util.List;
import AppThucDon.service.MonAnService;


/**
 *
 * @author duc
 */
public class Main {
    
    public static void main(String args[]) {
    MonAnService monanservice = new MonAnService();
    List<MonAn> monan = new java.util.ArrayList<>();
    monan = monanservice.goiYAnGiBayGio();
    for(MonAn i : monan){
        i.hienThiThongTin();
    }
    // Bật công tắc hiển thị cửa sổ
    java.awt.EventQueue.invokeLater(() -> {
    Mainlayout frame = new Mainlayout();
    frame.setVisible(true); 
    
    });
    
    System.out.println("Đang kiểm tra kết nối...");
        Connection conn = Database.getConnection();
        
        // Nếu kết nối thành công thì đóng lại luôn để test
        if (conn != null) {
            Database.closeConnection(conn);
        }
        
    }
}
