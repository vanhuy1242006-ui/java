package AppThucDon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import AppThucDon.database.Database; // Thay bằng file kết nối của bạn

public class YeuThichDAO {

    // 1. Thêm món ăn vào danh sách yêu thích
    public boolean addYeuThich(int userID, int monAnID) {
        String sql = "INSERT INTO YeuThich (UserID, MonAnID) VALUES (?, ?)";
        try {
            Connection conn = Database.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userID);
            ps.setInt(2, monAnID);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 2. Xóa món ăn khỏi danh sách yêu thích (Bỏ thích)
    public boolean removeYeuThich(int userID, int monAnID) {
        String sql = "DELETE FROM YeuThich WHERE UserID = ? AND MonAnID = ?";
        try {
            Connection conn = Database.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userID);
            ps.setInt(2, monAnID);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 3. Kiểm tra xem User hiện tại đã thích món này chưa (Để đổi màu nút bấm)
    public boolean isYeuThichExisted(int userID, int monAnID) {
        String sql = "SELECT 1 FROM YeuThich WHERE UserID = ? AND MonAnID = ?";
        try {
            Connection conn = Database.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userID);
            ps.setInt(2, monAnID);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // Nếu tìm thấy bản ghi thì trả về true
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkYeuThichExists(int userID, int monAnID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean deleteYeuThich(int userID, int monAnID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean insertYeuThich(int userID, int monAnID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    // =================================================================
    // 4. HÀM LẤY DANH SÁCH MÓN ĂN ĐÃ YÊU THÍCH CỦA MỘT USER
    // =================================================================
    public java.util.List<AppThucDon.model.MonAn> getDanhSachMonYeuThich(int userID) {
        java.util.List<AppThucDon.model.MonAn> list = new java.util.ArrayList<>();

        // Câu lệnh SQL: Lấy thông tin món ăn từ bảng MonAn thông qua bảng trung gian YeuThich
        String sql = "SELECT m.* FROM MonAn m " +
                     "INNER JOIN YeuThich y ON m.MaMon = y.MonAnID " + // Bạn nhớ check lại tên cột MaMon/MonAnID cho chuẩn CSDL nhé
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