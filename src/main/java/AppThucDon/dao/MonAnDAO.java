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

    // =================================================================
    // 1. HÀM GỢI Ý MÓN ĂN - ĐÃ ĐƯỢC NÂNG CẤP LEFT JOIN LẤY TÊN NGƯỜI TẠO
    // =================================================================
    public List<MonAn> getRecommendedMeals(int limit) {
        List<MonAn> list = new ArrayList<>();
        
        // Dùng LEFT JOIN để lôi kèm cột HoTen của bảng NguoiDung ra đặt tên là TenNguoiTao
        String sql = "SELECT TOP (?) m.*, n.HoTen AS TenNguoiTao " +
                     "FROM MonAn m " +
                     "LEFT JOIN NguoiDung n ON m.MaNguoiTao = n.MaNguoiDung " +
                     "ORDER BY NEWID()";
        
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, limit); 
            
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
                    
                    // 🌟 Gắp trọn thông tin người tạo đổ vào Object Java chỉ với 1 lần truy vấn
                    monAn.setMaNguoiTao(rs.getInt("MaNguoiTao"));
                    monAn.setTenNguoiTao(rs.getString("TenNguoiTao")); 
                    
                    list.add(monAn);
                }
            }
        } catch (SQLException e) {
            System.out.println("Loi getRecommendedMeals: " + e.getMessage());
        }
        return list;
    }

    // =================================================================
    // 2. HÀM THÊM MÓN ĂN - ĐÃ SỬA ĐỂ LƯU THÊM MÃ NGƯỜI TẠO (MaNguoiTao)
    // =================================================================
    public boolean insert(MonAn monAn) {
        // Bổ sung thêm cột MaNguoiTao vào câu lệnh chèn dữ liệu
        String sqlInsert = "INSERT INTO MonAn (TenMon, LoaiMon, NguyenLieu, ThoiGian, DanhGia, MoTa, MaNguoiTao) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlUpdateAnh = "UPDATE MonAn SET LinkAnh = ? WHERE MaMon = ?";
        
        Connection conn = null;
        PreparedStatement psInsert = null;
        PreparedStatement psUpdate = null;
        ResultSet rs = null;
        
        try {
            conn = Database.getConnection();
            psInsert = conn.prepareStatement(sqlInsert, java.sql.Statement.RETURN_GENERATED_KEYS);
            
            psInsert.setString(1, monAn.getTenMon());
            psInsert.setString(2, monAn.getLoaiMon());
            psInsert.setString(3, monAn.getNguyenLieu());
            psInsert.setDouble(4, monAn.getThoiGian());
            psInsert.setDouble(5, monAn.getDanhGia());
            psInsert.setString(6, monAn.getMoTa());
            psInsert.setInt(7, monAn.getMaNguoiTao()); // 🌟 Lưu ID của Admin tạo món vào DB
            
            int rows = psInsert.executeUpdate();
            
            if (rows > 0) {
                rs = psInsert.getGeneratedKeys();
                if (rs.next()) {
                    int idVuaSinhRa = rs.getInt(1); 
                    String tenAnhTheoId = idVuaSinhRa + ".png"; 
                    
                    psUpdate = conn.prepareStatement(sqlUpdateAnh);
                    psUpdate.setString(1, tenAnhTheoId);
                    psUpdate.setInt(2, idVuaSinhRa);
                    psUpdate.executeUpdate();
                    
                    monAn.setMaMon(idVuaSinhRa);
                    monAn.setLinkAnh(tenAnhTheoId);
                    
                    System.out.println("DAO: Them thanh cong mon an co ID la " + idVuaSinhRa);
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Loi insert tu dong tang: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (psInsert != null) psInsert.close(); } catch (Exception e) {}
            try { if (psUpdate != null) psUpdate.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
        return false;
    }

    // =================================================================
    // 3. HÀM CẬP NHẬT THÔNG TIN MÓN ĂN
    // =================================================================
    public boolean update(MonAn monAn) {
        String sql = "UPDATE MonAn SET TenMon=?, LoaiMon=?, NguyenLieu=?, ThoiGian=?, DanhGia=?, LinkAnh=?, MoTa=?, MaNguoiTao=? WHERE MaMon=?";
        
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, monAn.getTenMon());
            ps.setString(2, monAn.getLoaiMon());
            ps.setString(3, monAn.getNguyenLieu());
            ps.setDouble(4, monAn.getThoiGian()); 
            ps.setDouble(5, monAn.getDanhGia());
            ps.setString(6, monAn.getLinkAnh());
            ps.setString(7, monAn.getMoTa());
            ps.setInt(8, monAn.getMaNguoiTao()); // Cập nhật lại ID người tạo nếu cần thiết
            ps.setInt(9, monAn.getMaMon());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Loi update: " + e.getMessage());
        }
        return false;
    }

    // =================================================================
    // 4. HÀM XÓA MÓN ĂN
    // =================================================================
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

    // =================================================================
    // 5. HÀM ĐẾM TỔNG SỐ LƯỢNG MÓN ĂN
    // =================================================================
    public int countAll() {
        String sql = "SELECT COUNT(*) FROM MonAn";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            if (rs.next()) {
                return rs.getInt(1); 
            }
        } catch (SQLException e) {
            System.out.println("Loi countAll: " + e.getMessage());
        }
        return 0; 
    }
    // 🌟 HÀM XỬ LÝ ĐÁNH GIÁ THEO CÔNG THỨC: (CŨ + MỚI) / 2
    public boolean DanhGiaMonAn(int maMon, double soSaoMoi) {
        // 1. Câu lệnh lấy điểm hiện tại
        String sqlSelect = "SELECT DanhGia FROM MonAn WHERE MonAnID = ?";
        // 2. Câu lệnh cập nhật điểm mới
        String sqlUpdate = "UPDATE MonAn SET DanhGia = ? WHERE MonAnID = ?";
        
        try {
            Connection conn = Database.getConnection();
            
            // --- BƯỚC A: LẤY ĐIỂM ĐÁNH GIÁ HIỆN TẠI VỀ ---
            PreparedStatement psSelect = conn.prepareStatement(sqlSelect);
            psSelect.setInt(1, maMon);
            ResultSet rs = psSelect.executeQuery();
            
            double danhGiaHienTai = 0.0;
            if (rs.next()) {
                danhGiaHienTai = rs.getDouble("DanhGia");
            }
            
            // --- BƯỚC B: TÍNH TOÁN THEO CÔNG THỨC CỦA BẠN ---
            double danhGiaCapNhat = 0.0;
            if (danhGiaHienTai == 0.0) {
                // Nếu món ăn chưa có ai đánh giá (bằng 0), thì lấy luôn số sao mới
                danhGiaCapNhat = soSaoMoi;
            } else {
                // Nếu đã có điểm cũ, áp dụng công thức: (Cũ + Mới) / 2
                danhGiaCapNhat = (danhGiaHienTai + soSaoMoi) / 2.0;
                
                // Làm tròn đến 1 chữ số thập phân (ví dụ: 4.25 -> 4.3) cho đẹp giao diện
                danhGiaCapNhat = Math.round(danhGiaCapNhat * 10.0) / 10.0;
            }
            
            // --- BƯỚC C: CẬP NHẬT ĐIỂM MỚI VÀO DATABASE ---
            PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate);
            psUpdate.setDouble(1, danhGiaCapNhat);
            psUpdate.setInt(2, maMon);
            
            return psUpdate.executeUpdate() > 0;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // =================================================================
    // 6. HÀM LẤY DANH SÁCH MÓN ĂN DO CHÍNH USER ĐÓ TỰ TẠO
    // =================================================================
    public List<MonAn> getDanhSachMonDaTao(int maNguoiTao) {
        List<MonAn> list = new ArrayList<>();
        String sql = "SELECT * FROM MonAn WHERE MaNguoiTao = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maNguoiTao);

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
                    monAn.setMaNguoiTao(rs.getInt("MaNguoiTao"));

                    list.add(monAn);
                }
            }
        } catch (SQLException e) {
            System.out.println("Loi getDanhSachMonDaTao: " + e.getMessage());
        }
        return list;
    }
}