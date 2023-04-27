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

	protected abstract GenericService<T, Dto, PK> servico();

	@GetMapping
	public ResponseEntity<Collection<Dto>> buscarTodos(@RequestParam("limit") Integer limite, @RequestParam("page") Integer pagina) {
		return ResponseEntity.ok(servico().converterParaListaDTO(servico().buscarTodos(limite, pagina)));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Dto> buscarPorId(@PathVariable PK id) {
		return ResponseEntity.ok(servico().converterParaDTO(servico().buscarPorId(id)));
	}

	@PostMapping
	public ResponseEntity<Dto> salvar(@Valid @RequestBody Dto dto) {
		return ResponseEntity.ok(servico().converterParaDTO(servico().salvar(dto)));
	}

	@PutMapping
	public ResponseEntity<Dto> atualizar(@Valid @RequestBody Dto dto) {
		return ResponseEntity.ok(servico().converterParaDTO(servico().atualizar(dto)));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Dto> remover(@PathVariable(value = "id") PK id) {
		servico().remover(id);
		return ResponseEntity.ok().build();
	}
}
