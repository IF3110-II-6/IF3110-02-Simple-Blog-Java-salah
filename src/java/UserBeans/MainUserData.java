/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserBeans;


import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Yusuf
 */
public class MainUserData {
    public static void main(String[] args){
        /*
        UserData ud = new UserData();
        User user = ud.getUser("ucup");
        System.out.println("nama : "+user.getFullname());
        System.out.println("user : "+user.getUsername());
        System.out.println("password : "+user.getPassword());
        */
       
        userMgnt um = new userMgnt();
        BlogPost post = new BlogPost();
        post.setPostcontent("lalala");
        post.setPosttitle("dadada");
        post.setPostdate(new Date());
        post.setUid(2);
        um.setPost(post);
        um.submitPost();
    }
}
