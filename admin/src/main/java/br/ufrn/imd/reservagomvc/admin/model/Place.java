package br.ufrn.imd.reservagomvc.admin.model;

import br.ufrn.imd.reservagomvc.model.GenericModel;
import jakarta.persistence.*;

@Entity
@Table
public class Place extends GenericModel<Long> {
    @Id
    @SequenceGenerator(
            name = "place_sequence",
            sequenceName = "place_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "place_sequence"
    )
    private Long id;
    private boolean available;

    // TODO: find another name
    // private boolean isActive;
    private double stars;
    private double value;
    private String name;
    private String location;
    private String description;
    private Integer daysAvailable;
    private Long hostId;

    public Place() {
    }

    public Place(Long id, boolean available, boolean isActive, double stars, double value, String name, String location, String description, Integer daysAvailable, Long hostId) {
        this.id = id;
        this.available = available;
        //this.isActive = isActive;
        this.stars = stars;
        this.value = value;
        this.name = name;
        this.location = location;
        this.description = description;
        this.daysAvailable = daysAvailable;
        this.hostId = hostId;
    }

    public Place(boolean available, boolean isActive, double stars, double value, String name, String location, String description, Integer daysAvailable, Long hostId) {
        this.available = available;
        //this.isActive = isActive;
        this.stars = stars;
        this.value = value;
        this.name = name;
        this.location = location;
        this.description = description;
        this.daysAvailable = daysAvailable;
        this.hostId = hostId;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

//    public boolean isActive() {
//        return isActive;
//    }
//
//    public void setActive(boolean active) {
//        isActive = active;
//    }

    public double getStars() {
        return stars;
    }

    public void setStars(double stars) {
        this.stars = stars;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(Integer daysAvailable) {
        this.daysAvailable = daysAvailable;
    }

    public Long getHostId() {
        return hostId;
    }

    public void setHostId(Long hostId) {
        this.hostId = hostId;
    }
}
