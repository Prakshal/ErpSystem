package com.brevitaz.model;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Employee {
    private String employeeId;
    private String firstName;
    private String lastName;
    private Address temporaryAddress;
	private Address permanentAddress;
    private Date dob;
    private Date doj;
    private String designation;
    private String department;
    private String contactNo;
    private String secondaryContactNo;
    private String emergencyContactNo;
    private String emailId;
    private String secondaryEmailId;
    private String password;
    private String createdBy;
    private Date createdDate;
    private String updatedBy;
    private Date updatedDate;
    private List<Experience> experience;
    private MultipartFile documents; //upload documents(eg:id proof) functionality to be provided
    private MultipartFile photo; //upload picture of the employee 
    private boolean status;
    private List<Role> role;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

	public Address getTemporaryAddress() {
		return temporaryAddress;
	}

	public void setTemporaryAddress(Address temporaryAddress) {
		this.temporaryAddress = temporaryAddress;
	}

	public Address getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(Address permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public List<Experience> getExperience() {
		return experience;
	}

	public void setExperience(List<Experience> experience) {
		this.experience = experience;
	}

	public List<Role> getRole() {
		return role;
	}

	public void setRole(List<Role> role) {
		this.role = role;
	}

	public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String mobileNo) {
        this.contactNo = mobileNo;
    }

    public String getSecondaryContactNo() {
		return secondaryContactNo;
	}

	public void setSecondaryContactNo(String secondaryContactNo) {
		this.secondaryContactNo = secondaryContactNo;
	}

	public String getEmergencyContactNo() {
		return emergencyContactNo;
	}

	public void setEmergencyContactNo(String emergencyContactNo) {
		this.emergencyContactNo = emergencyContactNo;
	}

	public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
    
	public String getSecondaryEmailId() {
		return secondaryEmailId;
	}

	public void setSecondaryEmailId(String secondaryEmailId) {
		this.secondaryEmailId = secondaryEmailId;
	}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public MultipartFile getDocuments() {
		return documents;
	}

	public void setDocuments(MultipartFile document) {
		this.documents = document;
	}

	public MultipartFile getPhoto() {
		return photo;
	}

	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "Employee{" +
				"employeeId='" + employeeId + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", temporaryAddress=" + temporaryAddress +
				", permanentAddress=" + permanentAddress +
				", dob=" + dob +
				", doj=" + doj +
				", designation='" + designation + '\'' +
				", department='" + department + '\'' +
				", contactNo='" + contactNo + '\'' +
				", secondaryContactNo='" + secondaryContactNo + '\'' +
				", emergencyContactNo='" + emergencyContactNo + '\'' +
				", emailId='" + emailId + '\'' +
				", secondaryEmailId='" + secondaryEmailId + '\'' +
				", password='" + password + '\'' +
				", createdBy='" + createdBy + '\'' +
				", createdDate=" + createdDate +
				", updatedBy='" + updatedBy + '\'' +
				", updatedDate=" + updatedDate +
				", experience=" + experience +
				", documents=" + documents +
				", photo=" + photo +
				", status=" + status +
				", role=" + role +
				'}';
	}
}
