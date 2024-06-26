package art.deerborg.accounting.v1.api.dto.response.invoice;

import art.deerborg.accounting.v1.api.model.Customer;
import art.deerborg.accounting.v1.api.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDetailResponse {
    private Long id;
    private LocalDate date;
    private Integer quantity;
    private Double totalAmount;
    private Double netAmount;
    private Double taxAmount;
    private Customer customer;
    private Product product;
}
