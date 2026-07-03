package AppThucDon.dao.FormDangNhap;

public class CurrentUser {

    public static int userId = -1;

    public static String username = "";

    public static String displayName = "";

    public static String avatar = "";

    public static String bio = "";

    public static void clear() {
        userId = -1;
        username = "";
        displayName = "";
        avatar = "";
        bio = "";
    }
}