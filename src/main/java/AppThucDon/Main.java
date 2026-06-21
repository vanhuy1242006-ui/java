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


/**
 *
 * @author duc
 */
public class Main {
    
    public static void main(String args[]) {
    
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
        
    // Khoi tao doi tuong DAO de goi ham lay du lieu
        MonAnDAO dao = new MonAnDAO();
        
        System.out.println("--- BAT DAU KIEM TRA LAY DU LIEU TU SQL SERVER ---");
        
        // Goi ham getAll de lay toan bo danh sach mon an
        List<MonAn> danhSach = dao.getAll();
        
        // Kiem tra xem co lay duoc mon nao khong
        if (danhSach.isEmpty()) {
            System.out.println("Ket qua: Khong tim thay mon an nao. Hay kiem tra lai ket noi hoac du lieu ben SQL!");
        } else {
            System.out.println("Ket qua: Lay du lieu thanh cong! Tim thay " + danhSach.size() + " mon an:");
            System.out.println("--------------------------------------------------");
            
            // Duyet qua tung mon va in ra man hinh
            for (MonAn ma : danhSach) {
                System.out.println(" + Ma mon: " + ma.getMaMon());
                System.out.println(" + Ten mon: " + ma.getTenMon());
                System.out.println(" + Loai mon: " + ma.getLoaiMon());
                System.out.println(" + Nguyen lieu: " + ma.getNguyenLieu());
                System.out.println(" + Thoi gian: " + ma.getThoiGian() + " phut");
                System.out.println(" + Danh gia: " + ma.getDanhGia() + " sao");
                System.out.println(" + File anh: " + ma.getLinkAnh());
                System.out.println(" + Mo ta: " + ma.getMoTa());
                System.out.println("--------------------------------------------------");
            }
        }
        System.out.println("--- KET THUC KIEM TRA ---");
    }
}
