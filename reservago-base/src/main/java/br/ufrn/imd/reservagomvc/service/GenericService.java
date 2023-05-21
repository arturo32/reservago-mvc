package br.ufrn.imd.reservagomvc.service;

import br.ufrn.imd.reservagomvc.exception.EntityNotFoundException;
import br.ufrn.imd.reservagomvc.model.GenericModel;
import br.ufrn.imd.reservagomvc.respository.GenericRepository;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public abstract class GenericService<T extends GenericModel<PK>, Dto, PK extends Serializable> {

	protected String modelName;

	private String NOT_FOUND(PK id) {
		return "Entity of type '" + this.getModelName()
				+ "' and ID '" + id + "' not found.";
	}

	public GenericService() {
		this.modelName = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0].getTypeName();
		this.modelName = this.modelName.substring(this.modelName.lastIndexOf(".") + 1);
	}

	public String getModelName() {
		return this.modelName;
	}

	public abstract Dto convertToDto(T entity);

	public abstract T convertToEntity(Dto dto);

	protected abstract void validatePersistenceType(PersistenceType persistenceType, Dto dto);

	protected abstract void validate(Dto dto);

	public Collection<Dto> convertToDtoList(Collection<T> entities) {
		return entities.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	protected abstract GenericRepository<T, PK> repository();

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public T findById(PK id) {
		Optional<T> entity = repository().findByIdAndActiveIsTrue(id);
		if (entity.isEmpty()) {
			throw new EntityNotFoundException(NOT_FOUND(id));
		}
		return entity.get();
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<T> findAll(Integer limit, Integer page) {
		return repository().findAllByActiveIsTrueOrderByCreationDateDesc(PageRequest.of(page, limit));
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<T> findByIds(List<PK> ids) {
		return repository().findAllByActiveIsTrueAndIdIsInOrderByCreationDateDesc(ids);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<T> findByInterval(PK startId, PK endId) {
		return repository().findAllByActiveIsTrueAndIdBetweenOrderByCreationDateDesc(startId, endId);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public T save(Dto dto) {
		this.validatePersistenceType(PersistenceType.ADD, dto);
		this.validate(dto);
		return repository().save(convertToEntity(dto));
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public T update(Dto dto) {
		this.validatePersistenceType(PersistenceType.UPDATE, dto);
		this.validate(dto);
		return repository().save(convertToEntity(dto));
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(PK id) {
		Optional<T> entity = repository().findByIdAndActiveIsTrue(id);
		if (entity.isEmpty()) {
			throw new EntityNotFoundException(NOT_FOUND(id));
		} else {
			repository().deleteById(id);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteAll() {
		repository().deleteAll();
	}

}
