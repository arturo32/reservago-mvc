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
	private boolean transactionOk;
	private Long placeId;
	private Long userId;

	public Transaction() {
	}

	public Transaction(Long id, boolean transactionOk, Long placeId, Long userId) {
		this.id = id;
		this.transactionOk = transactionOk;
		this.placeId = placeId;
		this.userId = userId;
	}

	public Transaction(boolean transactionOk, Long placeId, Long userId) {
		this.transactionOk = transactionOk;
		this.placeId = placeId;
		this.userId = userId;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public boolean isTransactionOk() {
		return transactionOk;
	}

	public void setTransactionOk(boolean transactionOk) {
		this.transactionOk = transactionOk;
	}

	public Long getPlaceId() {
		return placeId;
	}

	public void setPlaceId(Long placeId) {
		this.placeId = placeId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
