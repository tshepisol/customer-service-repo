package za.co.investec.cis.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import za.co.investec.cis.domain.validation.SAID;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;


@SAID
@Entity
@Table(name = "customer")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"id","createdAt", "updatedAt" }, allowGetters = true)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    @NotBlank(message = "FirstName required")
    @Column(nullable = false)
    private String firstName;

    @NotBlank(message = "LastName required")
    @Column(nullable = false)
    private String lastName;


    @Transient
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Transient
    @Enumerated(EnumType.STRING)
    private Citizen citizenship;

    @Size(min = 10, max = 10, message = "Invalid length. must be 10 digits")
    @Column(nullable = false, unique = true)
    private String mobileNumber;


    //todo custom validation
    @NotBlank(message = "SA ID required")
    @Size(min = 13, max = 13, message = "Invalid length. must be 13 digits")
    @Column(nullable = false, unique = true)
    private String idNumber;

    @Valid
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "customer_id")
    private List<CustomerAddress> customerAddresses;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Citizen getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(Citizen citizenship) {
        this.citizenship = citizenship;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public List<CustomerAddress> getCustomerAddresses() {
        return customerAddresses;
    }

    public void setCustomerAddresses(List<CustomerAddress> customerAddresses) {
        this.customerAddresses = customerAddresses;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", citizenship='" + citizenship + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", customerAddresses=" + customerAddresses +
                '}';
    }
}
