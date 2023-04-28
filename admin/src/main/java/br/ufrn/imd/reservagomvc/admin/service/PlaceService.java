package br.ufrn.imd.reservagomvc.admin.service;

import br.ufrn.imd.reservagomvc.admin.model.Place;
import br.ufrn.imd.reservagomvc.admin.model.dto.PlaceDto;
import br.ufrn.imd.reservagomvc.admin.repository.PlaceRepository;
import br.ufrn.imd.reservagomvc.respository.GenericRepository;
import br.ufrn.imd.reservagomvc.service.GenericService;
import br.ufrn.imd.reservagomvc.service.PersistenceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceService extends GenericService<Place, PlaceDto, Long> {

    private final PlaceRepository placeRepository;

    @Autowired
    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @Override
    public PlaceDto converterParaDTO(Place entity) {
        return null;
    }

    @Override
    public Place converterParaEntidade(PlaceDto placeDto) {
        return null;
    }

    @Override
    protected void validarModoPersistencia(PersistenceType tipoPersistencia, PlaceDto placeDto) {

    }

    @Override
    protected void validar(PlaceDto placeDto) {

    }

    @Override
    protected GenericRepository<Place, Long> repositorio() {
        return null;
    }
}
