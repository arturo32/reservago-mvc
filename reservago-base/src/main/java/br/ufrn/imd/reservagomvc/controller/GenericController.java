package br.ufrn.imd.reservagomvc.controller;

import br.ufrn.imd.reservagomvc.model.GenericModel;
import br.ufrn.imd.reservagomvc.service.GenericService;
import jakarta.validation.Valid;
import java.io.Serializable;
import java.util.Collection;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
public abstract class GenericController<T extends GenericModel<PK>, Dto, PK extends Serializable> {

	protected abstract GenericService<T, Dto, PK> service();

	@GetMapping
	public ResponseEntity<Collection<Dto>> findAll(@RequestParam Integer limit, @RequestParam Integer page) {
		return ResponseEntity.ok(
				service().convertToDtoList(service().findAll(limit, page)));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Dto> findById(@PathVariable PK id) {
		return ResponseEntity.ok(service().convertToDto(service().findById(id)));
	}

	@PostMapping
	public ResponseEntity<Dto> save(@Valid @RequestBody Dto dto) {
		return ResponseEntity.ok(service().convertToDto(service().save(dto)));
	}

	@PutMapping
	public ResponseEntity<Dto> update(@Valid @RequestBody Dto dto) {
		return ResponseEntity.ok(service().convertToDto(service().update(dto)));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Dto> remove(@PathVariable(value = "id") PK id) {
		service().update(id);
		return ResponseEntity.ok().build();
	}
}
