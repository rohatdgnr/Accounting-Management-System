package art.deerborg.accounting.v1.api.dto.response.category;

import art.deerborg.accounting.v1.api.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDetailResponse {
    private Long id;
    private String name;
    private List<Product> products;
}
