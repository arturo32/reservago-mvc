package br.ufrn.imd.reservagomvc.checkout.model;

import br.ufrn.imd.reservagomvc.model.GenericModel;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table
public class Checkout extends GenericModel<Long> {
    @Id
    @SequenceGenerator(
            name = "checkout_sequence",
            sequenceName = "checkout_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "checkout_sequence"
    )
    private Long id;
    private Date expirationDate;

    private Long userId;

    private Long placeId;


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }
}
