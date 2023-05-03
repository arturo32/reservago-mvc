package br.ufrn.imd.reservagomvc.payment.model;

import br.ufrn.imd.reservagomvc.model.GenericModel;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table
public class Transaction extends GenericModel<Long> {

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
	private Boolean isTransactionOk;

	public Transaction() {
	}

	public Transaction(Long id, Boolean isTransactionOk) {
		this.id = id;
		this.isTransactionOk = isTransactionOk;
	}

	public Transaction(Boolean isTransactionOk) {
		this.isTransactionOk = isTransactionOk;
	}

	@Override public Long getId() {
		return this.id;
	}

	@Override public void setId(Long id) {
		this.id = id;
	}

	public Boolean getTransactionOk() {
		return isTransactionOk;
	}

	public void setTransactionOk(Boolean transactionOk) {
		isTransactionOk = transactionOk;
	}
}
