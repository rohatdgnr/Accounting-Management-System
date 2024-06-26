package art.deerborg.accounting.v1.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private Double buyingPrice;
    private Integer buyingQuantity;
    private Double cost;
    private Integer stock;
    private Double quantityPrice;
    private String code;
    private TaxType tax;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Category category;
    @OneToMany
    @JsonIgnore
    private List<Invoice> invoices;

}
