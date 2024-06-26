package art.deerborg.accounting.v1.api.dto.response.product;

import art.deerborg.accounting.v1.api.model.Category;
import art.deerborg.accounting.v1.api.model.Invoice;
import art.deerborg.accounting.v1.api.model.TaxType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailResponse {
    private Long id;
    private String brand;
    private String model;
    private Double buyingPrice;
    private Double cost;
    private Integer stock;
    private Double quantityPrice;
    private String code;
    private LocalDate date;
    private TaxType tax;
    private Category category;
    private List<Invoice> invoices;
}
