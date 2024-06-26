package art.deerborg.accounting.v1.api.dto.request.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSaveRequest {
    private String name;
    private String address;
    private String phone;
}
