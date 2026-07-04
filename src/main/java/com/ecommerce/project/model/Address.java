package com.ecommerce.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "addresses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @NotBlank
    @Size(min = 5, message = "Street name must be atleast 5 characters")
    private String street;

    @Size(min = 5, message = "Building name must be atleast 5 characters")
    private String buildingName;

    @Size(min = 4, message = "City name must be atleast 4 characters")
    private String city;

    @Size(min = 2, message = "State name must be atleast 2 characters")
    private String state;

    @Size(min = 2, message = "Country name must be atleast 2 characters")
    private String country;

    @Size(min = 6, message = "Pincode must be atleast 6 characters")
    private String pincode;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private  User user;
}




//
//@Entity
//@Table(name = "addresser")
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class Address {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long addressId;
//
//    @NotBlank
//    @Size(min = 5,message = "Street name must be atleast 5 characters")
//    private String street;
//
//
//    @ManyToOne
//    @Size(min = 5,message = "Building name must be atleast 5 characters")
//    private String buildingName;
//
//
//    @ManyToOne
//    @Size(min = 4,message = "city name must be atleast 4 characters")
//    private String city;
//
//    @ManyToOne
//    @Size(min = 2,message = "state name  must be atleast 2 characters")
//    private String state;
//
//    @ManyToOne
//    @Size(min = 2,message = "country name must be atleast 2 characters")
//    private String country;
//
//    @ManyToOne
//    @Size(min = 6,message = "Pincode must be atleast 6 characters")
//    private String pincode;
//
//    @ToString.Exclude
//    @ManyToMany(mappedBy = "addresses")
//    private List<User> users = new ArrayList<>();
//
//    public Address(String street, String buildingName, String city, String state, String country, String pincode) {
//        this.street = street;
//        this.buildingName = buildingName;
//        this.city = city;
//        this.state = state;
//        this.country = country;
//        this.pincode = pincode;
//    }
//}
