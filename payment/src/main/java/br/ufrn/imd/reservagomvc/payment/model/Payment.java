package br.ufrn.imd.reservagomvc.payment.model;

import br.ufrn.imd.reservagomvc.model.GenericModel;
import jakarta.persistence.*;
import org.hibernate.validator.cfg.defs.CreditCardNumberDef;

import java.util.Date;

@Entity
@Table
public class Payment extends GenericModel<Long> {

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
