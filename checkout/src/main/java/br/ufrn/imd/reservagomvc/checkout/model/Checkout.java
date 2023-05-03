package br.ufrn.imd.reservagomvc.checkout.model;

import br.ufrn.imd.reservagomvc.model.GenericModel;
import jakarta.persistence.*;

@Entity
@Table
public class Checkout extends GenericModel<Long> {
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
    private Integer amountOfDays;
    private Long transactionId;

    public Checkout() {
    }

    public Checkout(Long id, Integer amountOfDays, Long transactionId) {
        this.id = id;
        this.amountOfDays = amountOfDays;
        this.transactionId = transactionId;
    }

    public Checkout(Integer amountOfDays, Long transactionId) {
        this.amountOfDays = amountOfDays;
        this.transactionId = transactionId;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAmountOfDays() {
        return amountOfDays;
    }

    public void setAmountOfDays(Integer amountOfDays) {
        this.amountOfDays = amountOfDays;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }
}
