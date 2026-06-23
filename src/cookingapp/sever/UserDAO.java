package cookingapp.sever;

import cookingapp.DBConnection;
import cookingapp.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    public static User login(
            String username,
            String password
    ) {

        try {

            Connection conn
                    = DBConnection.getConnection();

            String sql
                    = "SELECT * "
                    + "FROM Users "
                    + "WHERE Username=? "
                    + "AND PasswordHash=?";

            PreparedStatement ps
                    = conn.prepareStatement(
                            sql
                    );

            ps.setString(
                    1,
                    username
            );

            ps.setString(
                    2,
                    password
            );

            ResultSet rs
                    = ps.executeQuery();

            if (rs.next()) {

                User user
                        = new User();

                user.setUsername(
                        rs.getString(
                                "Username"
                        )
                );

                user.setDisplayName(
                        rs.getString(
                                "DisplayName"
                        )
                );

                user.setEmail(
                        rs.getString(
                                "Email"
                        )
                );

                user.setAvatar(
                        rs.getString(
                                "Avatar"
                        )
                );

                user.setBio(
                        rs.getString(
                                "Bio"
                        )
                );

                return user;

            }

            return null;

        } catch (Exception e) {

            e.printStackTrace();

            return null;

        }

    }

    public static boolean register(
            User user
    ) {

        try {

            Connection conn
                    = DBConnection.getConnection();

            String sql
                    = "INSERT INTO Users("
                    + "Username,"
                    + "Email,"
                    + "PasswordHash"
                    + ")"
                    + "VALUES(?,?,?)";

            PreparedStatement ps
                    = conn.prepareStatement(
                            sql
                    );

            ps.setString(
                    1,
                    user.getUsername()
            );

            ps.setString(
                    2,
                    user.getEmail()
            );

            ps.setString(
                    3,
                    user.getPassword()
            );

            ps.executeUpdate();

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;

        }

    }

}
