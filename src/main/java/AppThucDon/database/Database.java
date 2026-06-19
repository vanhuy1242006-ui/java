package AppThucDon.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    
    // Hàm này dùng để mở kết nối tới SQL Server
    public static Connection getConnection() {
        Connection conn = null;
        try {
            // 1. Cấu hình các thông số kết nối (HÃY SỬA LẠI CHO ĐÚNG VỚI MÁY BẠN)
            String serverName = "localhost";        // Hoặc tên máy của bạn
            String port = "1433";                  // Cổng mặc định của SQL Server
            String dbName = "AppThucDonDB"; // Thay bằng tên DB bạn tạo trong SSMS
            String user = "sa";                    // Tài khoản đăng nhập SQL Server
            String password = "1234";               // Mật khẩu tài khoản sa của bạn
            
            // 2. Tạo chuỗi URL kết nối chuẩn kèm chứng chỉ bảo mật (encrypt=true)
            String url = "jdbc:sqlserver://" + serverName + ":" + port + ";"
                    + "databaseName=" + dbName + ";"
                    + "user=" + user + ";"
                    + "password=" + password + ";"
                    + "encrypt=true;trustServerCertificate=true;";
            
            // 3. Thực hiện kết nối
            conn = DriverManager.getConnection(url);
            System.out.println("🎉 Kết nối Cơ sở dữ liệu thành công!");
            
        } catch (SQLException e) {
            System.out.println("❌ Lỗi kết nối: " + e.getMessage());
        }
        return conn;
    }
    
    // Hàm này dùng để đóng kết nối khi đã xử lý xong dữ liệu (giúp tối ưu hệ thống)
    public static void closeConnection(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("🔌 Đã đóng kết nối an toàn.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Lỗi khi đóng kết nối: " + e.getMessage());
        }
    }
}