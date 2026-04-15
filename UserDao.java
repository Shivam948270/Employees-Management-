package company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    Connection con;

    public boolean saveDetail(User user) throws SQLException {
        boolean f = false;

        try {
            con = Connect.getConnection();

            String q = "INSERT INTO userDetails(uid,username,name,email,password,gender,dob,status,role) "
                    + "VALUES(?,?,?,?,?,?,?,?,?)";

            PreparedStatement p = con.prepareStatement(q);

            p.setString(1, user.getUid());
            p.setString(2, user.getUsername());
            p.setString(3, user.getName());
            p.setString(4, user.getEmail());
            p.setString(5, user.getPassword());
            p.setString(6, user.getGender());
            p.setString(7, user.getDob());
            p.setString(8, user.getStatus());
            p.setString(9, user.getRole());

            int r = p.executeUpdate();

            if (r > 0) {
                boolean imgSaved = saveImage(user);
                boolean bankSaved = saveBank(user);
                f = bankSaved;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return f;
    }

    public boolean updateUser(User u) {
        boolean f = false;

        try {
            con = Connect.getConnection();

            String q = "UPDATE userDetails SET username=?,name=?,email=?,password=?,gender=?,dob=?,status=?,role=? WHERE uid=?";
            PreparedStatement p = con.prepareStatement(q);

            p.setString(1, u.getUsername());
            p.setString(2, u.getName());
            p.setString(3, u.getEmail());
            p.setString(4, u.getPassword());
            p.setString(5, u.getGender());
            p.setString(6, u.getDob());
            p.setString(7, u.getStatus());
            p.setString(8, u.getRole());
            p.setString(9, u.getUid());

            int r = p.executeUpdate();
            if (r > 0) {
                updateImage(u);
                updateBank(u);
                f = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return f;
    }

    public boolean deleteUser(User u) {
        boolean f = false;
        try {
            con = Connect.getConnection();
            PreparedStatement p = con.prepareStatement(
                    "DELETE FROM userDetails WHERE uid=?");
            p.setString(1, u.getUid());
            f = p.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return f;
    }

    public User getbyId(String uid) {
        User u = null;

        try {
            con = Connect.getConnection();

            PreparedStatement p = con.prepareStatement(
                    "SELECT * FROM userDetails WHERE uid=?");
            p.setString(1, uid);

            ResultSet r = p.executeQuery();
            if (r.next()) {
                u = new User();
                u.setUid(uid);
                u.setUsername(r.getString("username"));
                u.setName(r.getString("name"));
                u.setEmail(r.getString("email"));
                u.setPassword(r.getString("password"));
                u.setGender(r.getString("gender"));
                u.setDob(r.getString("dob"));
                u.setStatus(r.getString("status"));
                u.setRole(r.getString("role"));
            }

            PreparedStatement p2 = con.prepareStatement(
                    "SELECT accno,bank,ifsc,url FROM bankdetails WHERE uid=?");
            p2.setString(1, uid);

            ResultSet r2 = p2.executeQuery();

            List<String> acc = new ArrayList<>();
            List<String> bank = new ArrayList<>();
            List<String> ifsc = new ArrayList<>();

            while (r2.next()) {
                acc.add(r2.getString("accno"));
                bank.add(r2.getString("bank"));
                ifsc.add(r2.getString("ifsc"));
                u.setUrl(r2.getString("url"));
            }

            u.setAccno(acc.toArray(new String[0]));
            u.setBank(bank.toArray(new String[0]));
            u.setIfsc(ifsc.toArray(new String[0]));

            PreparedStatement p3 = con.prepareStatement(
                    "SELECT url FROM multipleDocs WHERE uid=?");
            p3.setString(1, uid);

            ResultSet r3 = p3.executeQuery();
            List<String> imgs = new ArrayList<>();
            while (r3.next()) {
                imgs.add(r3.getString("url"));
            }
            u.setFileName(imgs.toArray(new String[0]));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    public boolean saveBank(User user) {
        boolean f = false;
        try {
            con = Connect.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO bankdetails(uid,accno,bank,ifsc,url) VALUES(?,?,?,?,?)");

            for (int i = 0; i < user.getAccno().length; i++) {
                ps.setString(1, user.getUid());
                ps.setString(2, user.getAccno()[i]);
                ps.setString(3, user.getBank()[i]);
                ps.setString(4, user.getIfsc()[i]);
                ps.setString(5, user.getUrl());
                ps.addBatch();
            }
            ps.executeBatch();
            f = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return f;
    }

    public boolean saveImage(User user) {
        boolean f = false;
        try {
            con = Connect.getConnection();
            for (String img : user.getFileName()) {
                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO multipleDocs(uid,url) VALUES(?,?)");
                ps.setString(1, user.getUid());
                ps.setString(2, img);
                if (ps.executeUpdate() > 0) {
                    f = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return f;
    }

    public boolean updateBank(User user) {
        boolean f = false;
        try {
            con = Connect.getConnection();
            PreparedStatement del = con.prepareStatement(
                    "DELETE FROM bankdetails WHERE uid=?");
            del.setString(1, user.getUid());
            del.executeUpdate();
            f = saveBank(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return f;
    }

    public boolean updateImage(User user) {
        boolean f = false;
        try {
            con = Connect.getConnection();
            PreparedStatement del = con.prepareStatement(
                    "DELETE FROM multipleDocs WHERE uid=?");
            del.setString(1, user.getUid());
            del.executeUpdate();
            f = saveImage(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return f;
    }

    public boolean changeStatus(User u) {
        boolean f = false;
        try {
            con = Connect.getConnection();
            PreparedStatement p = con.prepareStatement(
                    "UPDATE userDetails SET status=? WHERE uid=?");
            p.setString(1, u.getStatus());
            p.setString(2, u.getUid());
            f = p.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return f;
    }

    public User checkLogin(String email, String password) {
        User user = null;
        try {
            con = Connect.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM userDetails WHERE email=? AND password=?");
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUid(rs.getString("uid"));
                user.setUsername(rs.getString("username"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setGender(rs.getString("gender"));
                user.setDob(rs.getString("dob"));
                user.setStatus(rs.getString("status"));
                user.setRole(rs.getString("role"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> getAllUsers() {

        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM userDetails";

        try (Connection con = Connect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                User u = new User();
                u.setUid(rs.getString("uid"));
                u.setUsername(rs.getString("username"));
                u.setName(rs.getString("name"));
                u.setEmail(rs.getString("email"));
                u.setGender(rs.getString("gender"));
                u.setDob(rs.getString("dob"));
                u.setStatus(rs.getString("status"));
                u.setRole(rs.getString("role"));
                list.add(u);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
                            
   public List<User> getUsers(String uid, String email, String status) {

    List<User> list = new ArrayList<>();

    String sql = "SELECT * FROM userDetails WHERE 1=1";

    if (uid != null && !uid.isEmpty()) {
        sql += " AND uid = ?";
    }
    if (email != null && !email.isEmpty()) {
        sql += " AND email = ?";
    }
    if (status != null && !status.isEmpty()) {
        sql += " AND status = ?";
    }

    try (Connection con = Connect.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        int i = 1;

        if (uid != null && !uid.isEmpty()) {
            ps.setString(i++, uid);
        }
        if (email != null && !email.isEmpty()) {
            ps.setString(i++, email);
        }
        if (status != null && !status.isEmpty()) {
            ps.setString(i++, status);
        }

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            User u = new User();
            u.setUid(rs.getString("uid"));
            u.setUsername(rs.getString("username"));
            u.setName(rs.getString("name"));
            u.setEmail(rs.getString("email"));
            u.setGender(rs.getString("gender"));
            u.setDob(rs.getString("dob"));
            u.setStatus(rs.getString("status"));
            u.setRole(rs.getString("role"));
            list.add(u);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}


}
