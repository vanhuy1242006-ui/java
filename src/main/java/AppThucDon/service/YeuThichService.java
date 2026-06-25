package AppThucDon.service;

import AppThucDon.dao.YeuThichDAO;

public class YeuThichService {
    
    private YeuThichDAO yeuThichDAO;

    public YeuThichService() {
        this.yeuThichDAO = new YeuThichDAO();
    }


    public int NhanYeuThich(int userID, int monAnID) {
        // Nghiệp vụ 1: Kiểm tra xem người dùng đã đăng nhập chưa
        if (userID == -1) {
            return -2; // Trả về mã lỗi chưa đăng nhập cho View biết
        }
        
        // Nghiệp vụ 2: Kiểm tra dữ liệu đầu vào cơ bản (Phòng lỗi logic)
        if (monAnID <= 0) {
            return -1;
        }

        // Nghiệp vụ 3: Check trạng thái hiện tại dưới DB xem đã thích chưa để quyết định hành động
        boolean isExisted = yeuThichDAO.checkYeuThichExists(userID, monAnID);
        
        if (isExisted) {
            // Nếu thích rồi thì tiến hành XÓA THÍCH
            boolean result = yeuThichDAO.deleteYeuThich(userID, monAnID);
            return result ? 2 : -1;
        } else {
            // Nếu chưa thích thì tiến hành THÊM THÍCH
            boolean result = yeuThichDAO.insertYeuThich(userID, monAnID);
            return result ? 1 : -1;
        }
    }

    /**
     * Kiểm tra trạng thái để hiển thị giao diện ban đầu (Trái tim đỏ hay trắng)
     */
    public boolean isDaYeuThich(int userID, int monAnID) {
        if (userID == -1 || monAnID <= 0) {
            return false;
        }
        return yeuThichDAO.checkYeuThichExists(userID, monAnID);
    }
    // =================================================================
// CHỨC NĂNG: LẤY DANH SÁCH MÓN ĂN ĐÃ YÊU THÍCH CỦA USER
// =================================================================
    public java.util.List<AppThucDon.model.MonAn> layDanhSachMonYeuThich(int userID) {
        // Kiểm tra nghiệp vụ: Nếu chưa đăng nhập (userID == -1), trả về danh sách rỗng luôn
        if (userID <= 0) {
            System.out.println("Nghiep vu: User chua dang nhap hoac ID khong hop le. Khong the lay danh sach yeu thich!");
            return new java.util.ArrayList<>();
        }

        System.out.println("Nghiep vu: Dang lay danh sach mon an yeu thich cua UserID = " + userID);

        // Gọi DAO thực hiện truy vấn dữ liệu từ SQL Server
        YeuThichDAO yeuThichDAO = new YeuThichDAO();
        return yeuThichDAO.getDanhSachMonYeuThich(userID);
    }
}