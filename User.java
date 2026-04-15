
package company;

import java.sql.Date;
import java.util.logging.Logger;



public class User {
  private String uid;
  private String username;
  private String name;
  private String email;
  private String password;
  private String gender;
  private String dob;
  private String status;
  private String image;
  private String[] accno;
  private String[] bank;
  private String[] ifsc;
  private String[] fileName;
  private String url;
  private String role;

    public User(String uid, String username, String name, String email, String password, String gender, String dob, String status, String role) {
        this.uid = uid;
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.dob = dob;
        this.status = status;
        this.role = role;
    }



    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User(String[] accno, String[] bank, String[] ifsc, String url) {
        
        this.accno = accno;
        this.bank = bank;
        this.ifsc = ifsc;
        this.url = url;
      
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public User(String[] accno, String[] bank, String[] ifsc) {
        this.accno = accno;
        this.bank = bank;
        this.ifsc = ifsc;
    }
    

    public User(String[] fileName) {
        this.fileName = fileName;
    }

    public String[] getFileName() {
        return fileName;
    }

    public void setFileName(String[] fileName) {
        this.fileName = fileName;
    }

    public User(String uid, String status) {
        this.uid = uid;
        this.status = status;
    }

 
  
  
  
    public User(String uid) {
        this.uid = uid;
    }

    public User() {
    }


    


    public User(String uid, String username, String name, String email, String password, String gender, String dob, String status) {
        this.uid = uid;
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.dob = dob;
        this.status = status;
    }
    



    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }
    
       public String getDob() {
        return dob;
    }

    public String getStatus() {
        return status;
    }
    
    

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String[] getAccno() {
        return accno;
    }

    public String[] getBank() {
        return bank;
    }

    public String[] getIfsc() {
        return ifsc;
    }

    public void setAccno(String[] accno) {
        this.accno = accno;
    }

    public void setBank(String[] bank) {
        this.bank = bank;
    }

    public void setIfsc(String[] ifsc) {
        this.ifsc = ifsc;
    }
    

   
    

  
}
