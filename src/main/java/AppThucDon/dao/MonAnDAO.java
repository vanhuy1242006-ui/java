package AppThucDon.dao;

import AppThucDon.database.Database; // Đã đổi thành Database viết hoa
import AppThucDon.model.MonAn;       // Sử dụng đúng class MonAn
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
                monAn.setThoiGian(rs.getDouble("ThoiGian")); // Đã đổi thành getDouble
                monAn.setLinkAnh(rs.getString("LinkAnh"));
                monAn.setMoTa(rs.getString("MoTa"));
                
                list.add(monAn);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi getAll: " + e.getMessage());
        }
        return list;
    }

    // 2. Hàm thêm mới một món ăn
    public boolean insert(MonAn monAn) {
        String sql = "INSERT INTO MonAn (MaMon, TenMon, LoaiMon, NguyenLieu, ThoiGian, LinkAnh, MoTa) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, monAn.getMaMon());
            ps.setString(2, monAn.getTenMon());
            ps.setString(3, monAn.getLoaiMon());
            ps.setString(4, monAn.getNguyenLieu());
            ps.setDouble(5, monAn.getThoiGian()); // Đã đổi thành setDouble
            ps.setString(6, monAn.getLinkAnh());
            ps.setString(7, monAn.getMoTa());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi insert: " + e.getMessage());
        }
        return false;
    }

    // 3. Hàm cập nhật thông tin món ăn theo MaMon
    public boolean update(MonAn monAn) {
        String sql = "UPDATE MonAn SET TenMon=?, LoaiMon=?, NguyenLieu=?, ThoiGian=?, LinkAnh=?, MoTa=? WHERE MaMon=?";
        
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, monAn.getTenMon());
            ps.setString(2, monAn.getLoaiMon());
            ps.setString(3, monAn.getNguyenLieu());
            ps.setDouble(4, monAn.getThoiGian()); // Đã đổi thành setDouble
            ps.setString(5, monAn.getLinkAnh());
            ps.setString(6, monAn.getMoTa());
            ps.setInt(7, monAn.getMaMon());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi update: " + e.getMessage());
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
            System.out.println("Lỗi delete: " + e.getMessage());
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
                    monAn.setThoiGian(rs.getDouble("ThoiGian")); // Đã đổi thành getDouble
                    monAn.setLinkAnh(rs.getString("LinkAnh"));
                    monAn.setMoTa(rs.getString("MoTa"));
                    
                    list.add(monAn);
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi findByName: " + e.getMessage());
        }
        return list;
    }
}