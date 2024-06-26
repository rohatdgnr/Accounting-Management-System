package art.deerborg.accounting.v1.api.controller;

import art.deerborg.accounting.v1.api.core.result.Result;
import art.deerborg.accounting.v1.api.core.result.ResultData;
import art.deerborg.accounting.v1.api.dto.request.product.ProductSaveRequest;
import art.deerborg.accounting.v1.api.dto.request.product.ProductUpdateRequest;
import art.deerborg.accounting.v1.api.dto.response.product.ProductDetailResponse;
import art.deerborg.accounting.v1.api.dto.response.product.ProductResponse;
import art.deerborg.accounting.v1.api.service.abstracts.IProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResultData<ProductResponse> createProduct(@RequestBody ProductSaveRequest productSaveRequest){
        return productService.createProduct(productSaveRequest);
    }
    @PutMapping
    public ResultData<ProductResponse> updateProduct(@RequestBody ProductUpdateRequest productUpdateRequest){
        return productService.updateProduct(productUpdateRequest);
    }
    @GetMapping
    public ResultData<List<ProductDetailResponse>> getAllProduct(){
        return productService.getAllProduct();
    }
    @GetMapping("/{id}")
    public ResultData<ProductDetailResponse> getByProductId(@PathVariable Long id){
        return productService.getByProductId(id);
    }
    @DeleteMapping
    public Result deleteProduct(@RequestBody Long id){
        return productService.deleteProduct(id);
    }

}
