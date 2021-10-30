package com.crud.entities;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Servicesvm {

    @SerializedName("username")
    @Expose
    private String Username;
    @SerializedName("emailId")
    @Expose
    private String EmailId;
    @SerializedName("mobileNo")
    @Expose
    private String MobileNo;
    @SerializedName("useraddress")
    @Expose
    private String Useraddress;
    @SerializedName("bookingdate")
    @Expose
    private String Bookingdate;
    @SerializedName("servicesOrdered")
    @Expose
    private String ServicesOrdered;
    @SerializedName("timedata")
    @Expose
    private String Timedata;

    @SerializedName("loginId")
    @Expose
    private int LoginId;

    public int getServiceOrderId() {
        return ServiceOrderId;
    }

    public void setServiceOrderId(int serviceOrderId) {
        ServiceOrderId = serviceOrderId;
    }

    @SerializedName("serviceOrderId")
    @Expose
    private int ServiceOrderId;





    public String getUsername() {
        return Username;
    }

    public void setsUsername(String Username) {
        this.Username = Username;
    }

    public String getEmailId() {
        return EmailId;
    }

    public void setsEmailId(String EmailId) {
        this.EmailId = EmailId;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setsMobileNo(String MobileNo) {
        this.MobileNo = MobileNo;
    }

    public String getUseraddress() {
        return Useraddress;
    }

    public void setsUseraddress(String Useraddress) {
        this.Useraddress = Useraddress;
    }

    public String getBookingdate() {
        return Bookingdate;
    }

    public void setsBookingdate(String Bookingdate) {
        this.Bookingdate = Bookingdate;
    }

    public String getServicesOrdered() {
        return ServicesOrdered;
    }

    public void setsServicesOrdered(String ServicesOrdered) {
        this.ServicesOrdered = ServicesOrdered;
    }

    public String getTimedata() {
        return Timedata;
    }

    public void setsTimedata(String Timedata) {
        this.Timedata = Timedata;
    }


    public int getLoginId() {
        return LoginId;
    }

    public void setLoginId(int loginId) {
        LoginId = loginId;
    }
}
