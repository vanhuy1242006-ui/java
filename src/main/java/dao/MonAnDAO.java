package AppThucDon.dao;

import AppThucDon.database.Database; 
import AppThucDon.model.MonAn;       
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MonAnDAO {

    // 1. Hàm lấy toàn bộ danh sách món ăn
    public List<MonAn> getAll() {
        List<MonAn> list = new ArrayList<>();
        String sql = "SELECT * FROM MonAn";
        
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                MonAn monAn = new MonAn();
                monAn.setMaMon(rs.getInt("MaMon"));
                monAn.setTenMon(rs.getString("TenMon"));
                monAn.setLoaiMon(rs.getString("LoaiMon"));
                monAn.setNguyenLieu(rs.getString("NguyenLieu"));
                monAn.setThoiGian(rs.getDouble("ThoiGian"));
                monAn.setDanhGia(rs.getDouble("DanhGia")); // Đã sửa tên cột thành 'DanhGia' viết liền cho đúng SQL
                monAn.setLinkAnh(rs.getString("LinkAnh"));
                monAn.setMoTa(rs.getString("MoTa"));
                
                list.add(monAn);
            }
        } catch (SQLException e) {
            // Ghi chú: Chuyển sang in không dấu để tránh lỗi font
            System.out.println("Loi getAll: " + e.getMessage());
        }
        return list;
    }

    // 2. Hàm thêm mới một món ăn
 // 2. Hàm thêm mới một món ăn (Tự động sinh ID và tự gắp tên ảnh theo ID)
public boolean insert(MonAn monAn) {
    // Câu lệnh SQL không hề có MaMon và LinkAnh vì SQL sẽ tự sinh ID trước
    String sqlInsert = "INSERT INTO MonAn (TenMon, LoaiMon, NguyenLieu, ThoiGian, DanhGia, MoTa) VALUES (?, ?, ?, ?, ?, ?)";
    String sqlUpdateAnh = "UPDATE MonAn SET LinkAnh = ? WHERE MaMon = ?";
    
    Connection conn = null;
    PreparedStatement psInsert = null;
    PreparedStatement psUpdate = null;
    ResultSet rs = null;
    
    try {
        conn = Database.getConnection();
        // Bật tính năng RETURN_GENERATED_KEYS để lấy ID tự tăng vừa sinh ra từ SQL Server
        psInsert = conn.prepareStatement(sqlInsert, java.sql.Statement.RETURN_GENERATED_KEYS);
        
        psInsert.setString(1, monAn.getTenMon());
        psInsert.setString(2, monAn.getLoaiMon());
        psInsert.setString(3, monAn.getNguyenLieu());
        psInsert.setDouble(4, monAn.getThoiGian());
        psInsert.setDouble(5, monAn.getDanhGia());
        psInsert.setString(6, monAn.getMoTa());
        
        int rows = psInsert.executeUpdate();
        
        // Nếu chèn thông tin món ăn thành công, tiến hành lấy ID ra để đặt tên ảnh
        if (rows > 0) {
            rs = psInsert.getGeneratedKeys();
            if (rs.next()) {
                int idVuaSinhRa = rs.getInt(1); // Lấy được số ID tự tăng (Ví dụ: 6)
                
                // Tự sinh chuỗi tên ảnh dựa theo ID: "6.png"
                String tenAnhTheoId = idVuaSinhRa + ".png"; 
                
                // Chạy lệnh UPDATE nạp tên ảnh vào đúng món ăn đó
                psUpdate = conn.prepareStatement(sqlUpdateAnh);
                psUpdate.setString(1, tenAnhTheoId);
                psUpdate.setInt(2, idVuaSinhRa);
                psUpdate.executeUpdate();
                
                // Đặt ngược lại giá trị ID và tên ảnh vào đối tượng monAn để đẩy về View hiển thị nếu cần
                monAn.setMaMon(idVuaSinhRa);
                monAn.setLinkAnh(tenAnhTheoId);
                
                System.out.println("DAO: Them thanh cong mon an co ID tu tang la " + idVuaSinhRa);
                return true;
            }
        }
    } catch (SQLException e) {
        System.out.println("Loi insert tu dong tang: " + e.getMessage());
    } finally {
        // Đóng các kết nối giải phóng bộ nhớ
        try { if (rs != null) rs.close(); } catch (Exception e) {}
        try { if (psInsert != null) psInsert.close(); } catch (Exception e) {}
        try { if (psUpdate != null) psUpdate.close(); } catch (Exception e) {}
        try { if (conn != null) conn.close(); } catch (Exception e) {}
    }
    return false;
}

    // 3. Hàm cập nhật thông tin món ăn theo MaMon
    public boolean update(MonAn monAn) {
        String sql = "UPDATE MonAn SET TenMon=?, LoaiMon=?, NguyenLieu=?, ThoiGian=?, DanhGia=?, LinkAnh=?, MoTa=? WHERE MaMon=?";
        
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, monAn.getTenMon());
            ps.setString(2, monAn.getLoaiMon());
            ps.setString(3, monAn.getNguyenLieu());
            ps.setDouble(4, monAn.getThoiGian()); 
            ps.setDouble(5, monAn.getDanhGia());
            ps.setString(6, monAn.getLinkAnh());
            ps.setString(7, monAn.getMoTa());
            ps.setInt(8, monAn.getMaMon());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Loi update: " + e.getMessage());
        }
        return false;
    }

    // 4. Hàm xóa món ăn theo MaMon
    public boolean delete(int maMon) {
        String sql = "DELETE FROM MonAn WHERE MaMon=?";
        
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, maMon);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Loi delete: " + e.getMessage());
        }
        return false;
    }

    // 5. Hàm tìm kiếm món ăn gần đúng theo tên
    public List<MonAn> findByName(String keyword) {
        List<MonAn> list = new ArrayList<>();
        String sql = "SELECT * FROM MonAn WHERE TenMon LIKE ?";
        
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    MonAn monAn = new MonAn();
                    monAn.setMaMon(rs.getInt("MaMon"));
                    monAn.setTenMon(rs.getString("TenMon"));
                    monAn.setLoaiMon(rs.getString("LoaiMon"));
                    monAn.setNguyenLieu(rs.getString("NguyenLieu"));
                    monAn.setThoiGian(rs.getDouble("ThoiGian")); 
                    monAn.setDanhGia(rs.getDouble("DanhGia")); // Đã bổ sung đọc cột DanhGia bị thiếu ở hàm cũ
                    monAn.setLinkAnh(rs.getString("LinkAnh"));
                    monAn.setMoTa(rs.getString("MoTa"));
                    
                    list.add(monAn);
                }
            }
        } catch (SQLException e) {
            System.out.println("Loi findByName: " + e.getMessage());
        }
        return list;
    }
    // Hàm lấy danh sách món ăn đề xuất (Sắp xếp theo Đánh giá giảm dần)
    public List<MonAn> getRecommendedMeals(int limit) {
    List<MonAn> list = new ArrayList<>();
    // Lệnh SQL lấy ra các món ăn sắp xếp theo điểm đánh giá từ cao xuống thấp
    String sql = "SELECT TOP (?) * FROM MonAn ORDER BY NEWID()";
    
    try (Connection conn = Database.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        ps.setInt(1, limit); // Số lượng món ăn muốn đề xuất (Ví dụ: lấy top 3 món)
        
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                MonAn monAn = new MonAn();
                monAn.setMaMon(rs.getInt("MaMon"));
                monAn.setTenMon(rs.getString("TenMon"));
                monAn.setLoaiMon(rs.getString("LoaiMon"));
                monAn.setNguyenLieu(rs.getString("NguyenLieu"));
                monAn.setThoiGian(rs.getDouble("ThoiGian")); 
                monAn.setDanhGia(rs.getDouble("DanhGia"));
                monAn.setLinkAnh(rs.getString("LinkAnh"));
                monAn.setMoTa(rs.getString("MoTa"));
                
                list.add(monAn);
            }
        }
    } catch (SQLException e) {
        System.out.println("Loi getRecommendedMeals: " + e.getMessage());
    }
    return list;
    }
    
    // Hàm đếm tổng số lượng món ăn hiện có trong Database
    public int countAll() {
    String sql = "SELECT COUNT(*) FROM MonAn";
    try (Connection conn = Database.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        
        if (rs.next()) {
            return rs.getInt(1); // Trả về tổng số dòng đếm được
        }
    } catch (SQLException e) {
        System.out.println("Loi countAll: " + e.getMessage());
    }
    return 0; // Nếu lỗi hoặc không có dữ liệu thì trả về 0
    }
}