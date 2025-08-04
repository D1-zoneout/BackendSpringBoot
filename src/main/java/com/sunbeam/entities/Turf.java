package com.sunbeam.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "turf")
public class Turf extends BaseEntity {

    @Column(name = "turf_name")
    private String turfName;

    @Column(length = 100)
    private String description;

    @Column(name = "owner_name")
    private String ownerName;

    private String city;
    private String state;

    private String pinCode;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "turf_location")
    private String turfLocation;

    @ElementCollection(targetClass = Sports.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "turf_sports", joinColumns = @JoinColumn(name = "turf_id"))
    @Column(name = "sports")
    private Set<Sports> sports;
 
    @ElementCollection(targetClass = Facility.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="facilities", joinColumns =  @JoinColumn(name = "turf_id"))
     @Column(name = "facility")
    private Set<Facility> facility;
    @OneToMany(mappedBy = "turf", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Slot> slots = new ArrayList<>();

    @OneToMany(mappedBy = "turf", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings = new ArrayList<>();

    @OneToMany(mappedBy = "turf", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "turf", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TurfImage> images = new ArrayList<>();
}
