package br.ufrn.imd.reservagomvc.payment.model;

import br.ufrn.imd.reservagomvc.model.GenericModel;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

public class CreditCard extends GenericModel<Long> {

    @Id
    @SequenceGenerator(
            name = "transaction_sequence",
            sequenceName = "transaction_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "transaction_sequence"
    )
    private Long id;

    private Long ownerId;
    private String number;
    private Double balance;
    private Integer verificationNumber;

    public CreditCard(Long id) {
        this.id = id;
    }

    public CreditCard(Long id, Long ownerId, String number, Double balance, Integer verificationNumber) {
        this.id = id;
        this.ownerId = ownerId;
        this.number = number;
        this.balance = balance;
        this.verificationNumber = verificationNumber;
    }

    public CreditCard(Long ownerId, String number, Double balance, Integer verificationNumber) {
        this.ownerId = ownerId;
        this.number = number;
        this.balance = balance;
        this.verificationNumber = verificationNumber;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Integer getVerificationNumber() {
        return verificationNumber;
    }

    public void setVerificationNumber(Integer verificationNumber) {
        this.verificationNumber = verificationNumber;
    }
}
