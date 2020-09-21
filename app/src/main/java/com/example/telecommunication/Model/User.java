package com.example.telecommunication.Model;

public class User {

    private String id;
    private String fullname;
    private String address;
    private String gender;
    private String clinic;
    private String speciality;
    private String experience;
    private String imageURL;
    private String status;
    private String search;


    private String usertype;


    public User(String id, String fullname, String address, String gender, String clinic,
                String speciality, String experience,String imageURL, String status, String search,String usertype) {
        this.id = id;
        this.fullname = fullname;
        this.address = address;
        this.gender = gender;
        this.clinic = clinic;
        this.speciality = speciality;
        this.experience = experience;
        this.imageURL = imageURL;
        this.status = status;
        this.search = search;
        this.usertype=usertype;
    }

    public User() {

    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getClinic() {
        return clinic;
    }

    public void setClinic(String clinic) {
        this.clinic = clinic;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

}
