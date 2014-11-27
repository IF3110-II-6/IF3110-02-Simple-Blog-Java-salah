/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserBeans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yusuf
 */
public class UserData {

    public UserData(){
    }
    
    public User getUser(int uid) {
        User user = null;
        Connection con = getConnection();
        String sql = "SELECT * FROM user WHERE uid=" + uid;
        try {
            Statement pst = con.createStatement();
            ResultSet rs = pst.executeQuery(sql);
            con.close();
            user = new User();
            while (rs.next()) {
                user.setUid(rs.getInt("uid"));
                user.setFullname(rs.getString("fullname"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setEmail(rs.getString("email"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public User getUser(String username) {
        User user = null;
        ResultSet rs = null;
        Statement pst = null;
        Connection con = getConnection();
        String sql = "SELECT * FROM user WHERE username='" + username + "'";
        try {
            pst = con.createStatement();
            rs = pst.executeQuery(sql);

            user = new User();
            while (rs.next()) {
                user.setUid(rs.getInt("uid"));
                user.setFullname(rs.getString("fullname"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setEmail(rs.getString("email"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public void writeKomentar(Komentar komentar) {
        ResultSet rs = null;
        PreparedStatement pst = null;
        Connection con = getConnection();
        String sql = "INSERT INTO komentar VALUES (NULL, " + "'" + komentar.getPid()
                + "', '" + komentar.getKomentator() + "', '"
                + komentar.getKomen() + "', '"
                + komentar.getEmail() + "', '"
                + komentar.getCommentDate() + "')";
        try {
            pst = con.prepareStatement(sql);
            pst.execute();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void writeKomentar(int Pid, String komentator,
            String komen, String email, Date commentDate) {
        ResultSet rs = null;
        PreparedStatement pst = null;
        Connection con = getConnection();
        String sql = "INSERT INTO komentar VALUES (NULL, " + "'" + Pid
                + "', '" + komentator + "', '"
                + komen + "', '"
                + email + "', '"
                + commentDate + "')";
        try {
            pst = con.prepareStatement(sql);
            pst.execute();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Komentar getKomentar(int cid) {
        Komentar komentar = null;
        ResultSet rs = null;
        Statement pst = null;
        Connection con = getConnection();
        String sql = "SELECT * FROM komentar WHERE cid=" + cid;
        try {
            pst = con.createStatement();
            rs = pst.executeQuery(sql);
            con.close();

            komentar = new Komentar();
            while (rs.next()) {
                komentar.setCid(rs.getInt("cid"));
                komentar.setPid(rs.getInt("pid"));
                komentar.setKomentator(rs.getString("komentator"));
                komentar.setKomen(rs.getString("komen"));
                komentar.setEmail(rs.getString("email"));
                komentar.setCommentDate(rs.getDate("commentdate"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return komentar;
    }

    public ArrayList<Komentar> getListKomentar(int pid) {
        ArrayList<Komentar> records = new ArrayList<Komentar>();
        ResultSet rs = null;
        Statement pst = null;
        Connection con = getConnection();
        String sql = "SELECT * FROM komentar WHERE pid=" + pid;
        try {
            pst = con.createStatement();
            rs = pst.executeQuery(sql);
            con.close();

            while (rs.next()) {
                Komentar komentar = new Komentar();
                komentar.setCid(rs.getInt("cid"));
                komentar.setPid(rs.getInt("pid"));
                komentar.setKomentator(rs.getString("komentator"));
                komentar.setKomen(rs.getString("komen"));
                komentar.setEmail(rs.getString("email"));
                komentar.setCommentDate(rs.getDate("commentdate"));
                records.add(komentar);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return records;
    }

    public void writeBlogPost(BlogPost blogPost) {
        
        PreparedStatement pst = null;
        Connection con = getConnection();
        String sql = "INSERT INTO blogpost VALUES (NULL, '"+ blogPost.getUid()
                + "', '" + blogPost.getPosttitle()
                + "', '" + blogPost.getPostcontent() + "', '"
                + blogPost.getPostdate() + "', '"
                + blogPost.publishedToInt() + "', '"
                + blogPost.deletedToInt() + "')";
        
        try {
            pst = con.prepareStatement(sql);
            pst.execute();   
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public BlogPost getBlogPost(int pid) {
        BlogPost blogPost = null;
        ResultSet rs = null;
        Statement pst = null;
        Connection con = getConnection();
        String sql = "SELECT * FROM blogpost WHERE pid=" + pid;
        try {
            pst = con.createStatement();
            rs = pst.executeQuery(sql);
            con.close();

            blogPost = new BlogPost();
            while (rs.next()) {
                blogPost.setPid(rs.getInt("pid"));
                blogPost.setUid(rs.getInt("uid"));
                blogPost.setPosttitle(rs.getString("posttitle"));
                blogPost.setPostcontent(rs.getString("postcontent"));
                blogPost.setPostdate(rs.getDate("postdate").toString());
                blogPost.setPublished(rs.getBoolean("published"));
                blogPost.setDeleted(rs.getBoolean("deleted"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return blogPost;
    }

    public ArrayList<BlogPost> getListBlogPost(int uid) {
        ArrayList<BlogPost> records = new ArrayList<BlogPost>();
        ResultSet rs = null;
        Statement pst = null;
        Connection con = getConnection();
        String sql = "SELECT * FROM blogpost WHERE uid=" + uid;
        try {
            pst = con.createStatement();
            rs = pst.executeQuery(sql);
            con.close();

            while (rs.next()) {
                BlogPost blogPost = new BlogPost();
                blogPost.setPid(rs.getInt("pid"));
                blogPost.setUid(rs.getInt("uid"));
                blogPost.setPosttitle(rs.getString("posttitle"));
                blogPost.setPostcontent(rs.getString("postcontent"));
                blogPost.setPostdate(rs.getDate("postdate").toString());
                blogPost.setPublished(rs.getBoolean("published"));
                blogPost.setDeleted(rs.getBoolean("deleted"));
                records.add(blogPost);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return records;
    }

    public ArrayList<BlogPost> getAllBlogPost() {
        ArrayList<BlogPost> records = new ArrayList<BlogPost>();
        ResultSet rs = null;
        Statement pst = null;
        Connection con = getConnection();
        String sql = "SELECT blogpost.*, user.fullname FROM blogpost NATURAL JOIN user WHERE blogpost.uid = user.uid AND published=1 ORDER BY blogpost.postdate DESC";
        try {
            pst = con.createStatement();
            rs = pst.executeQuery(sql);
            
            while (rs.next()) {
                BlogPost blogPost = new BlogPost();
                blogPost.setPid(rs.getInt("pid"));
                blogPost.setUid(rs.getInt("uid"));
                blogPost.setUsername(rs.getString("fullname"));
                blogPost.setPosttitle(rs.getString("posttitle"));
                blogPost.setPostcontent(rs.getString("postcontent"));
                blogPost.setPostdate(rs.getDate("postdate").toString());
                blogPost.setPublished(rs.getBoolean("published"));
                blogPost.setDeleted(rs.getBoolean("deleted"));
                records.add(blogPost);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return records;
    }

    public Connection getConnection() {
        Connection con = null;
        String url = "jdbc:mysql://localhost:3307/blogjava";
        String user = "root";
        String password = "";

        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }

        return con;
    }

}