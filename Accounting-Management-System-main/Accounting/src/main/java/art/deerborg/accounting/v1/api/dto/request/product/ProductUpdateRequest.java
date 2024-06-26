package art.deerborg.accounting.v1.api.dto.request.product;

import art.deerborg.accounting.v1.api.model.Category;
import art.deerborg.accounting.v1.api.model.TaxType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateRequest {
    private Long id;
    private String brand;
    private String model;
    private Double buyingPrice;
    private Integer buyingQuantity;
    private Double cost;
    private Integer stock;
    private Double quantityPrice;
    private LocalDate date;
    private String code;
    private TaxType tax;
    private Category category;
}
