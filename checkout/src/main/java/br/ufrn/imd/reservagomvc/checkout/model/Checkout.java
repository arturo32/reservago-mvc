package br.ufrn.imd.reservagomvc.checkout.model;

import br.ufrn.imd.reservagomvc.model.GenericModel;
import jakarta.persistence.*;

@Entity
@Table
public class Checkout extends GenericModel<Long> {
    @Id
    @SequenceGenerator(
            name = "place_sequence",
            sequenceName = "place_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "place_sequence"
    )
    private Long id;


}
