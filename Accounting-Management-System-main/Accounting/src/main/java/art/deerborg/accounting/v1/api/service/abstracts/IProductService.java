package art.deerborg.accounting.v1.api.service.abstracts;

import art.deerborg.accounting.v1.api.core.result.Result;
import art.deerborg.accounting.v1.api.core.result.ResultData;
import art.deerborg.accounting.v1.api.dto.request.product.ProductSaveRequest;
import art.deerborg.accounting.v1.api.dto.request.product.ProductUpdateRequest;
import art.deerborg.accounting.v1.api.dto.response.product.ProductDetailResponse;
import art.deerborg.accounting.v1.api.dto.response.product.ProductResponse;

import java.util.List;

public interface IProductService {
    ResultData<ProductResponse> createProduct(ProductSaveRequest productSaveRequest);
    ResultData<ProductResponse> updateProduct(ProductUpdateRequest productUpdateRequest);
    ResultData<List<ProductDetailResponse>> getAllProduct();
    ResultData<ProductDetailResponse> getByProductId(Long id);
    Result deleteProduct(Long id);
}
