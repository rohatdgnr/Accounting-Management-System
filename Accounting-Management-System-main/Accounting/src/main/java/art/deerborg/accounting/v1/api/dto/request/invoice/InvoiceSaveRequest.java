package art.deerborg.accounting.v1.api.dto.request.invoice;

import art.deerborg.accounting.v1.api.model.Customer;
import art.deerborg.accounting.v1.api.model.Product;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class InvoiceSaveRequest {
    private LocalDate date;
    private Integer quantity;
    private Double totalAmount;
    private Double netAmount;
    private Double taxAmount;
    private Customer customer;
    private Product product;
}
