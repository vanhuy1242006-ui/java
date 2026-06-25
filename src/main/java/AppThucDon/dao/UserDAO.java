package AppThucDon.dao;


import AppThucDon.database.Database;
import AppThucDon.model.User;

import java.sql.*;

public class UserDAO {

    public static User login(
            String username,
            String password
    ) {

        String sql =
        """
        SELECT *
        FROM Users
        WHERE Username = ?
        AND PasswordHash = ?
        """;

        try (
            Connection conn = Database.getConnection();
            PreparedStatement ps =
                    conn.prepareStatement(sql)
        ) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                User u = new User();

                u.setUserID(
                        rs.getInt("UserID")
                );

                u.setUsername(
                        rs.getString("Username")
                );

                u.setPasswordHash(
                        rs.getString("PasswordHash")
                );

                u.setEmail(
                        rs.getString("Email")
                );

                u.setDisplayName(
                        rs.getString("DisplayName")
                );

                u.setAvatar(
                        rs.getString("Avatar")
                );

                u.setBio(
                        rs.getString("Bio")
                );

                u.setAccountType(
                        rs.getString("AccountType")
                );

                return u;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}