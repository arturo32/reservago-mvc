package br.ufrn.imd.reservagomvc.checkout.model;

import br.ufrn.imd.reservagomvc.model.GenericModel;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Payment extends GenericModel<Long> {

    @Id
    @SequenceGenerator(
            name = "payment_sequence",
            sequenceName = "payment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "payment_sequence"
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "credit_card_id")
    private CreditCard creditCard;
    private Date paymentDate;

    public Payment() {
    }

    public Payment(Long id, CreditCard creditCard, Date paymentDate) {
        this.id = id;
        this.creditCard = creditCard;
        this.paymentDate = paymentDate;
    }

    public Payment(CreditCard creditCard, Date paymentDate) {
        this.creditCard = creditCard;
        this.paymentDate = paymentDate;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}
