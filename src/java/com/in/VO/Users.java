/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.in.VO;

import java.sql.Blob;
import java.util.Date;

/**
 *
 * @author sougata das
 */
public class Users {

    private String username = null;
    private String firstname = null;
    private String lastname = null;
    private String email = null;
    private Date dob = null;
    private String dateStr = null;
    private String address = null;
    private String companyname = null;
    private String yourrole = null;
    private String phonenumber = null;
    private String webpage = null;
    private String password = null;
    private String confirmpassword = null;
    private String isadmin = null;
    private String isonline = null;
    private Blob userImageFile = null;

    public Blob getUserImageFile() {
        return userImageFile;
    }

    public void setUserImageFile(Blob userImageFile) {
        this.userImageFile = userImageFile;
    }

    public String getIsadmin() {
        return isadmin;
    }

    public void setIsadmin(String isadmin) {
        this.isadmin = isadmin;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWebpage() {
        return webpage;
    }

    public void setWebpage(String webpage) {
        this.webpage = webpage;
    }

    public String getYourrole() {
        return yourrole;
    }

    public void setYourrole(String yourrole) {
        this.yourrole = yourrole;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getIsonline() {
        return isonline;
    }

    public void setIsonline(String isonline) {
        this.isonline = isonline;
    }
    
}
