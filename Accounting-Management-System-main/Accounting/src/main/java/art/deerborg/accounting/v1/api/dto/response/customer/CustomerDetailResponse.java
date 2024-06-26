package art.deerborg.accounting.v1.api.dto.response.customer;

import art.deerborg.accounting.v1.api.model.Invoice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDetailResponse {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private List<Invoice> invoices;
}
