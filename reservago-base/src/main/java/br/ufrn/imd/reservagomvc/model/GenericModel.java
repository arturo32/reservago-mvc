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
	@Column(name = "data_criacao", nullable = false, updatable = false)
	@CreatedDate
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonIgnore
	private LocalDateTime dataCriacao = LocalDateTime.now();

	@Column(name = "data_modificacao")
	@LastModifiedDate
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonIgnore
	private LocalDateTime dataModificacao;

	@Column(name = "data_exclusao")
	@LastModifiedDate
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonIgnore
	private LocalDateTime dataExclusao;

	@NotNull
	private Boolean ativo = true;

	public abstract PK getId();

	public abstract void setId(PK id);

	@JsonIgnore
	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	@JsonIgnore
	public LocalDateTime getDataModificacao() {
		return dataModificacao;
	}

	public void setDataModificacao(LocalDateTime dataModificacao) {
		this.dataModificacao = dataModificacao;
	}

	@JsonIgnore
	public LocalDateTime getDataExclusao() {
		return dataExclusao;
	}

	public void setDataExclusao(LocalDateTime dataExclusao) {
		this.dataExclusao = dataExclusao;
	}

	@PreUpdate
	public void preUpdate() {
		this.dataModificacao = LocalDateTime.now();
	}

	@PreRemove
	public void preRemove() {
		this.dataExclusao = LocalDateTime.now();
	}

	@JsonIgnore
	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	@JsonIgnore
	public Boolean isAtivo() {
		return ativo;
	}
}
