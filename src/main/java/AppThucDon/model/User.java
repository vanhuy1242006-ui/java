package AppThucDon.model;

public class User {
    private int userID;
    private String username;
    private String passwordHash;
    private String email;
    private String displayName;
    private String avatar;
    private String bio;
    private String accountType;

    // Thuộc tính lưu ID user đang đăng nhập
    public static int idUserHienTai;

    public User() {
    }

    public User(int userID, String username, String passwordHash,
                String email, String displayName,
                String avatar, String bio, String accountType) {
        this.userID = userID;
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
        this.displayName = displayName;
        this.avatar = avatar;
        this.bio = bio;
        this.accountType = accountType;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public static int getIdUserHienTai() {
        return idUserHienTai;
    }

    public static void setIdUserHienTai(int idUserHienTai) {
        User.idUserHienTai = idUserHienTai;
    }
}