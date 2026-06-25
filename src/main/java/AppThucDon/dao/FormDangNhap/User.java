package AppThucDon.dao.FormDangNhap;

import java.io.Serializable;

public class User implements Serializable {

    private String username;

    private String password;

    private String email;

    private String displayName;

    private String avatar;

    private String bio;

    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public User() {
    }

// EMAIL
    public String getEmail() {
        return email;
    }

    public void setEmail(
            String email
    ) {
        this.email = email;
    }

// USERNAME
    public String getUsername() {
        return username;
    }

    public void setUsername(
            String username
    ) {
        this.username = username;
    }

// PASSWORD
    public String getPassword() {
        return password;
    }

    public void setPassword(
            String password
    ) {
        this.password = password;
    }

// DISPLAY NAME
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(
            String displayName
    ) {
        this.displayName = displayName;
    }

// AVATAR
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(
            String avatar
    ) {
        this.avatar = avatar;
    }

// BIO
    public String getBio() {
        return bio;
    }

    public void setBio(
            String bio
    ) {
        this.bio = bio;
    }

}
