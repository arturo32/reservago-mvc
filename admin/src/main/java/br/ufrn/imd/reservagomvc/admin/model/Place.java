package br.ufrn.imd.reservagomvc.admin.model;

import br.ufrn.imd.reservagomvc.model.GenericModel;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    private double stars;
    private double valuePerDay;
    private String name;
    private String location;
    private String description;
    private Integer daysAvailable;
    @ManyToOne
    @JoinColumn(name = "host_id")
    private User host;

    @OneToMany
    private List<User> guests = new ArrayList<>();

    private Integer maxNumberOfGuests;

    public Place() {
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

    public double getStars() {
        return stars;
    }

    public void setStars(double stars) {
        this.stars = stars;
    }

    public double getValuePerDay() {
        return valuePerDay;
    }

    public void setValuePerDay(double value) {
        this.valuePerDay = value;
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

    public User getHost() {
        return host;
    }

    public void setHost(User hostId) {
        this.host = hostId;
    }

    public List<User> getGuests() {
        return guests;
    }

    public void setGuests(List<User> guests) {
        this.guests = guests;
    }

    public Integer getMaxNumberOfGuests() {
        return maxNumberOfGuests;
    }

    public void setMaxNumberOfGuests(Integer maxNumberOfGuests) {
        this.maxNumberOfGuests = maxNumberOfGuests;
    }

    public boolean isFull(){
        return this.getGuests().size() == maxNumberOfGuests;
    }
}
