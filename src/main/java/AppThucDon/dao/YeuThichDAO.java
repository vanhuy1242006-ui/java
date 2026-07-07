package AppThucDon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import AppThucDon.database.Database;

public class YeuThichDAO {

    // 1. Thêm món ăn vào danh sách yêu thích
    public boolean addYeuThich(int userID, int maMon) {
        // Đã cập nhật: Tên bảng MonAnYeuThich, tên cột MaMon
        String sql = "INSERT INTO MonAnYeuThich (UserID, MaMon) VALUES (?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, userID);
            ps.setInt(2, maMon);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 2. Xóa món ăn khỏi danh sách yêu thích
    public boolean removeYeuThich(int userID, int maMon) {

        String sql = "DELETE FROM MonAnYeuThich WHERE UserID = ? AND MaMon = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, userID);
            ps.setInt(2, maMon);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 3. Kiểm tra xem User hiện tại đã thích món này chưa 
    public boolean isYeuThichExisted(int userID, int maMon) {
        // Đã cập nhật: Tên bảng MonAnYeuThich, tên cột MaMon
        String sql = "SELECT 1 FROM MonAnYeuThich WHERE UserID = ? AND MaMon = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, userID);
            ps.setInt(2, maMon);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // Nếu tìm thấy bản ghi thì trả về true
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 4. HÀM LẤY DANH SÁCH MÓN ĂN ĐÃ YÊU THÍCH CỦA MỘT USER
    public java.util.List<AppThucDon.model.MonAn> getDanhSachMonYeuThich(int userID) {
        java.util.List<AppThucDon.model.MonAn> list = new java.util.ArrayList<>();

        // Đã cập nhật: Kết nối qua bảng MonAnYeuThich thông qua điều kiện m.MaMon = y.MaMon
        String sql = "SELECT m.* FROM MonAn m " +
                     "INNER JOIN MonAnYeuThich y ON m.MaMon = y.MaMon " +
                     "WHERE y.UserID = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userID);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    AppThucDon.model.MonAn monAn = new AppThucDon.model.MonAn();
                    monAn.setMaMon(rs.getInt("MaMon"));
                    monAn.setTenMon(rs.getString("TenMon"));
                    monAn.setLoaiMon(rs.getString("LoaiMon"));
                    monAn.setNguyenLieu(rs.getString("NguyenLieu"));
                    monAn.setThoiGian(rs.getDouble("ThoiGian")); 
                    monAn.setDanhGia(rs.getDouble("DanhGia"));
                    monAn.setLinkAnh(rs.getString("LinkAnh"));
                    monAn.setMoTa(rs.getString("MoTa"));
                    monAn.setMaNguoiTao(rs.getInt("MaNguoiTao"));

                    list.add(monAn);
                }
            }
        } catch (Exception e) {
            System.out.println("Loi getDanhSachMonYeuThich: " + e.getMessage());
        }
        return list;
    }
}