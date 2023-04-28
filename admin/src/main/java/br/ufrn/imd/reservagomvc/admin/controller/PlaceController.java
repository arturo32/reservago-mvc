package br.ufrn.imd.reservagomvc.admin.controller;

import br.ufrn.imd.reservagomvc.admin.model.Place;
import br.ufrn.imd.reservagomvc.admin.model.dto.PlaceDto;
import br.ufrn.imd.reservagomvc.admin.service.PlaceService;
import br.ufrn.imd.reservagomvc.controller.GenericController;
import br.ufrn.imd.reservagomvc.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/places")
public class PlaceController extends GenericController<Place, PlaceDto, Long> {

    private final PlaceService placeService;

    @Autowired
    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @Override
    protected GenericService<Place, PlaceDto, Long> servico() {
        return this.placeService;
    }
}
