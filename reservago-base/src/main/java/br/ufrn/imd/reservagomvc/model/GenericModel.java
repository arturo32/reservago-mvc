package br.ufrn.imd.reservagomvc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@MappedSuperclass
public abstract class GenericModel<PK extends Serializable> {
	@Column(name = "creation_date", nullable = false, updatable = false)
	@CreatedDate
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonIgnore
	private LocalDateTime creationDate = LocalDateTime.now();

	@Column(name = "modification_date")
	@LastModifiedDate
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonIgnore
	private LocalDateTime modificationDate;

	@Column(name = "exlusion_date")
	@LastModifiedDate
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonIgnore
	private LocalDateTime exclusionDate;

	@NotNull
	private Boolean active = true;

	public abstract PK getId();

	public abstract void setId(PK id);

	@JsonIgnore
	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime dataCriacao) {
		this.creationDate = dataCriacao;
	}

	@JsonIgnore
	public LocalDateTime getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(LocalDateTime dataModificacao) {
		this.modificationDate = dataModificacao;
	}

	@JsonIgnore
	public LocalDateTime getExclusionDate() {
		return exclusionDate;
	}

	public void setExclusionDate(LocalDateTime dataExclusao) {
		this.exclusionDate = dataExclusao;
	}

	@PreUpdate
	public void preUpdate() {
		this.modificationDate = LocalDateTime.now();
	}

	@PreRemove
	public void preRemove() {
		this.exclusionDate = LocalDateTime.now();
	}

	@JsonIgnore
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean ativo) {
		this.active = ativo;
	}

	@JsonIgnore
	public Boolean isActive() {
		return active;
	}
}
