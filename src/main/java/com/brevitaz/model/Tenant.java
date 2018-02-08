package com.brevitaz.model;

public class Tenant {
    private String tenantId;
    private String tenantName;
    private Address address;
    private String webSite;
    private String officeNo;
    private String email;
    private String contactPerson;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getOfficeNo() {
        return officeNo;
    }

    public void setOfficeNo(String officeNo) {
        this.officeNo = officeNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    @Override
    public String toString() {
        return "Tenant{" +
                "tenantId='" + tenantId + '\'' +
                ", tenantName='" + tenantName + '\'' +
                ", address=" + address +
                ", webSite='" + webSite + '\'' +
                ", officeNo='" + officeNo + '\'' +
                ", email='" + email + '\'' +
                ", contactPerson='" + contactPerson + '\'' +
                '}';
    }
}
