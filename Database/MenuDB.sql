CREATE DATABASE AppThucDonDB
GO
USE AppThucDonDB
GO


CREATE TABLE Users (
    UserID INT IDENTITY(1,1) PRIMARY KEY,
    Username NVARCHAR(50) NOT NULL,
    PasswordHash NVARCHAR(255) NOT NULL,
    Email NVARCHAR(100),
    DisplayName NVARCHAR(100),
    Avatar NVARCHAR(MAX),
    Bio NVARCHAR(500),
    AccountType NVARCHAR(20) DEFAULT N'User' -- 'Admin' hoặc 'User'

);

CREATE TABLE MonAn(
	MaMon int IDENTITY(1,1) PRIMARY KEY,
	TenMon NVARCHAR(100),
	LoaiMon NVARCHAR(50),
	NguyenLieu NVARCHAR(100),
	ThoiGian FLOAT,
	DanhGia FLOAT,
	LinkAnh NVARCHAR(100),
	MoTa NVARCHAR(MAX),
    MaNguoiTao INT, -- 🌟 THÊM CỘT NÀY ĐỂ LƯU ID NGƯỜI TẠO
    -- Tạo khóa ngoại liên kết sang bảng NguoiDung
    FOREIGN KEY (MaNguoiTao) REFERENCES Users(UserID) ON DELETE SET NULL
);

-- Tạo bảng món ăn yêu thích
CREATE TABLE MonAnYeuThich (
    UserID INT NOT NULL, -- Sau này liên kết với bảng NguoiDung (nếu có)
    MaMon INT NOT NULL,       -- Liên kết với bảng MonAn
    PRIMARY KEY (UserID, MaMon), -- Khóa chính kết hợp để một người không thể thích trùng 1 món 2 lần
    FOREIGN KEY (MaMon) REFERENCES MonAn(MaMon) ON DELETE CASCADE, -- Nếu món ăn bị xóa thì tự động xóa lượt thích
    FOREIGN KEY (UserID) REFERENCES Users(UserID) ON DELETE CASCADE

);
INSERT INTO Users (UserName, PasswordHash, DisplayName) VALUES ('hungbeo', '123', 'hungbeo');

-- Chèn dữ liệu mẫu có dấu và hướng dẫn cách nấu chi tiết
INSERT INTO MonAn (TenMon, LoaiMon, NguyenLieu, ThoiGian, DanhGia, LinkAnh, MoTa, MaNguoiTao) VALUES 
(N'Phở Bò Hà Nội', N'Món nước', N'200g Bánh phở, 150g Thịt bò, Nước dùng xương ống, Hành lá, Gừng, Hoa hồi', 45.0, 4.8, N'1.png', N'Chần bánh phở qua nước sôi rồi xếp vào bát. Trần thịt bò chín tái sau đó đặt lên trên. Rưới nước dùng xương ống ninh nóng hổi kèm hành lá lên và thưởng thức.', 1),
( N'Bún Chả Obama', N'Món khô', N'200g Bún tươi, 150g Thịt ba chỉ, 100g Thịt băm, Nước mắm chua ngọt, Đu đủ xanh, Rau sống', 30.0, 4.7, N'2.png', N'Ướp thịt ba chỉ và thịt băm rồi nướng cháy cạnh trên than hoa. Pha nước mắm chua ngọt với tỏi ớt và đu đủ muối chua. Khi ăn chấm bún và rau sống vào bát nước chấm kèm thịt.', 1),
( N'Cơm Tấm Sườn Bì Chả', N'Món cơm', N'1 Bát Cơm tấm, 1 Miếng Sườn heo, 30g Bì heo, 1 Mieng Chả trứng, Nước mắm đường, Mỡ hành', 25.0, 4.5, N'3.png', N'Nướng chín vàng sườn heo trên bếp than. Xới cơm tấm ra đĩa, xếp sườn nướng, bì heo và chả trứng lên bên cạnh. Rưới mỡ hành lên mặt cơm và ăn kèm nước mắm chua ngọt pha đặc.', 1),
( N'Bánh Mì Hội An', N'Món khô', N'1 Ổ Bánh mì đặc ruột, 50g Ba tê gan, 50g Xíu mại, Bơ trứng gà, Chả lụa, Dưa góp, Rau thơm', 10.0, 4.9, N'4.png', N'Nướng nóng giòn ổ bánh mì rồi rạch dọc một bên. Quét một lớp bơ và ba tê gan vào bên trong, tiếp tục xếp xíu mại, chả lụa, dưa góp và rau thơm vào rồi rưới nước sốt xíu mại.', 1),
( N'Mỳ Quảng Gà', N'Món nước', N'200g Mỳ quảng, 150g Thịt gà ta, Nước dùng gà, Đậu phộng rang, Bánh tráng nướng, Rau bắp chuối', 35.0, 4.6, N'5.png', N'Xào thịt gà rim đậm đà để làm nước nhân. Cho mỳ quảng và rau bắp chuối vào tô, múc thịt gà và chan một ít nước dùng sâm sấp. Rắc đậu phộng rang lên trên và ăn kèm bánh tráng.', 1);
GO


TRUNCATE TABLE Users;
DROP DATABASE AppThucDonDB;
