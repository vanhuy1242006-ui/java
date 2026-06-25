package AppThucDon.service;

import AppThucDon.dao.MonAnDAO;
import AppThucDon.model.MonAn;
import java.util.List;

public class MonAnService {
    
    // Khởi tạo đối tượng DAO để tương tác với Database
    private MonAnDAO monAnDAO = new MonAnDAO();

    // =================================================================
    // 1. CHỨC NĂNG: THÊM MỚI MÓN ĂN
    // =================================================================
    public boolean themMoiMonAn(MonAn monAn) {
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
        
        // Kiểm tra số sao phải nằm trong khoảng từ 0 đến 5
        if (monAn.getDanhGia() < 0 || monAn.getDanhGia() > 5) {
            System.out.println("Nghiep vu: Diem danh gia phai nam trong khoang tu 0 den 5!");
            return false;
        }

        // Kiểm tra ID người tạo phải hợp lệ (phải có người chịu trách nhiệm tạo món)
        if (monAn.getMaNguoiTao() <= 0) {
            System.out.println("Nghiep vu: Phai co thong tin nguoi tao hop le!");
            return false;
        }

        // Nếu vượt qua tất cả các vòng kiểm tra thì gọi DAO để lưu
        return monAnDAO.insert(monAn);
    }

    // =================================================================
    // 2. CHỨC NĂNG: CẬP NHẬT THÔNG TIN MÓN ĂN
    // =================================================================
    public boolean capNhatMonAn(MonAn monAn) {
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

        // Kiểm tra điểm đánh giá từ 0 đến 5
        if (monAn.getDanhGia() < 0 || monAn.getDanhGia() > 5) {
            System.out.println("Nghiep vu: Diem danh gia phai nam trong khoang tu 0 den 5!");
            return false;
        }
        
        return monAnDAO.update(monAn);
    }
    
    // =================================================================
    // 3. CHỨC NĂNG: XÓA MÓN ĂN Theo MaMon
    // =================================================================
    public boolean xoaMonAn(int maMon) {
        // Kiểm tra mã món ăn hợp lệ trước khi xóa
        if (maMon <= 0) {
            System.out.println("Nghiep vu: Ma mon an khong hop le de xoa!");
            return false;
        }
        return monAnDAO.delete(maMon);
    }
    
    // =================================================================
    // 4. CHỨC NĂNG: LẤY DANH SÁCH GỢI Ý MÓN ĂN
    // =================================================================
    public List<MonAn> getGoiYMonAn(int soLuong) {
        // Gọi DAO để lấy tổng số món ăn hiện tại trong database
        int tongSoMonTrongDb = monAnDAO.countAll();
        System.out.println("Nghiep vu: Tong so mon hien co trong Database la: " + tongSoMonTrongDb);
        
        // Nếu số lượng yêu cầu không hợp lệ hoặc lớn hơn số món hiện có, tự động gán bằng tối đa DB
        if (soLuong <= 0 || soLuong > tongSoMonTrongDb) {
            System.out.println("Nghiep vu: So luong yeu cau (" + soLuong + ") khong hop le. Tu dong gan bang tong so hang (" + tongSoMonTrongDb + ")");
            soLuong = tongSoMonTrongDb;
        }
        
        // Nếu database trống rỗng (0 món), trả về list rỗng
        if (soLuong == 0) {
            return new java.util.ArrayList<>();
        }
        
        System.out.println("Nghiep vu: Dang random ra top " + soLuong + " mon an ngau nhien");
        return monAnDAO.getRecommendedMeals(soLuong);
    } 

    // =================================================================
    // 5. CHỨC NĂNG: ĐÁNH GIÁ MÓN ĂN (Tính điểm trung bình cộng cuốn chiếu)
    // =================================================================
    public boolean danhGiaMonAn(int maMon, double soSaoMoi) {
        // Kiểm tra mã món ăn truyền vào phải hợp lệ
        if (maMon <= 0) {
            System.out.println("Nghiep vu: Ma mon an khong hop le de danh gia!");
            return false;
        }

        // Kiểm tra số sao người dùng chấm phải nằm trong khoảng thực tế từ 1 đến 5 sao
        if (soSaoMoi < 1.0 || soSaoMoi > 5.0) {
            System.out.println("Nghiep vu: So sao gui len phai nam trong khoang tu 1 den 5 sao!");
            return false;
        }

        System.out.println("Nghiep vu: Du lieu hop le, dang chuyen xuong DAO de tinh toan");
        return monAnDAO.DanhGiaMonAn(maMon, soSaoMoi);
    }
    // =================================================================
    // 6. CHỨC NĂNG: LẤY DANH SÁCH MÓN ĂN DO USER ĐÃ TẠO/THÊM
    // =================================================================
    public List<MonAn> layDanhSachMonDaTao(int maNguoiTao) {
        // Kiểm tra nghiệp vụ: Nếu chưa đăng nhập (ID <= 0 hoặc bằng -1), trả về danh sách rỗng luôn
        if (maNguoiTao <= 0) {
            System.out.println("Nghiep vu: User chua dang nhap. Khong the lay danh sach mon da tao!");
            return new java.util.ArrayList<>();
        }

        System.out.println("Nghiep vu: Dang lay danh sach mon tu tao cua MaNguoiTao = " + maNguoiTao);
        // Hợp lệ thì gọi DAO xử lý
        return monAnDAO.getDanhSachMonDaTao(maNguoiTao);
    }
}