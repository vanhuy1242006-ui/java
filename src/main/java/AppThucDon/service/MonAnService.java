package AppThucDon.service;

import AppThucDon.dao.MonAnDAO;
import AppThucDon.model.MonAn;
import java.util.List;

public class MonAnService {
    
    // Khởi tạo đối tượng DAO để tương tác với Database
    private MonAnDAO monAnDAO = new MonAnDAO();

    // 1. Hàm kiểm tra trùng mã món (Trả về true nếu đã tồn tại)
    public boolean isMaMonTrung(int maMon) {
        List<MonAn> list = monAnDAO.getAll();
        for (MonAn ma : list) {
            if (ma.getMaMon() == maMon) {
                System.out.println("Nghiep vu: Ma mon nay da ton tai trong he thong!");
                return true; 
            }
        }
        return false;
    }

    // 2. Hàm validate tổng hợp trước khi thêm mới (Insert)
    public boolean checkThemMoi(MonAn monAn) {
        // Kiểm tra tên món không được để trống
        if (monAn.getTenMon() == null || monAn.getTenMon().trim().isEmpty()) {
            System.out.println("Nghiep vu: Ten mon an khong duoc de trong!");
            return false;
        }
        
        // Kiểm tra thời gian không được âm
        if (monAn.getThoiGian() < 0) {
            System.out.println("Nghiep vu: Thoi gian nau khong duoc am!");
            return false;
        }
        
        // Kiểm tra trùng mã món trước khi thêm
        if (isMaMonTrung(monAn.getMaMon())) {
            return false;
        }
        
        // Nếu vượt qua tất cả các vòng kiểm tra thì gọi DAO để lưu
        return monAnDAO.insert(monAn);
    }

    // 3. Hàm validate trước khi cập nhật (Update)
    public boolean checkCapNhat(MonAn monAn) {
        // Kiểm tra tên món không được để trống
        if (monAn.getTenMon() == null || monAn.getTenMon().trim().isEmpty()) {
            System.out.println("Nghiep vu: Ten mon an khong duoc de trong de cap nhat!");
            return false;
        }
        
        // Kiểm tra thời gian không được âm
        if (monAn.getThoiGian() < 0) {
            System.out.println("Nghiep vu: Thoi gian nau khong duoc am!");
            return false;
        }
        
        // Khi sửa thì không kiểm tra trùng mã nữa vì mã món là khóa chính cố định
        return monAnDAO.update(monAn);
    }
    
    // 4. Các hàm trung gian khác gọi trực tiếp từ DAO qua
    public List<MonAn> layToanBoMonAn() {
        return monAnDAO.getAll();
    }
    
    public boolean xoaMonAn(int maMon) {
        return monAnDAO.delete(maMon);
    }
    
    public List<MonAn> timKiemTheoTen(String keyword) {
        return monAnDAO.findByName(keyword);
    }
}