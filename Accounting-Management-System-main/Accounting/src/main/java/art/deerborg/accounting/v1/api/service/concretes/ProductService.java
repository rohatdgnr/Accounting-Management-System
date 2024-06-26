package art.deerborg.accounting.v1.api.service.concretes;

import art.deerborg.accounting.v1.api.core.config.modelMapper.ModelMapperService;
import art.deerborg.accounting.v1.api.core.result.Result;
import art.deerborg.accounting.v1.api.core.result.ResultData;
import art.deerborg.accounting.v1.api.core.utilities.FinanceHelper;
import art.deerborg.accounting.v1.api.core.utilities.Message;
import art.deerborg.accounting.v1.api.core.utilities.ResultHelper;
import art.deerborg.accounting.v1.api.dao.ProductRepository;
import art.deerborg.accounting.v1.api.dto.request.product.ProductSaveRequest;
import art.deerborg.accounting.v1.api.dto.request.product.ProductUpdateRequest;
import art.deerborg.accounting.v1.api.dto.response.product.ProductDetailResponse;
import art.deerborg.accounting.v1.api.dto.response.product.ProductResponse;
import art.deerborg.accounting.v1.api.model.Product;
import art.deerborg.accounting.v1.api.service.abstracts.IProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {
    private final ModelMapperService mapperService;
    private final ProductRepository productRepository;

    public ProductService(ModelMapperService mapperService, ProductRepository productRepository) {
        this.mapperService = mapperService;
        this.productRepository = productRepository;
    }

    @Override
    public ResultData<ProductResponse> createProduct(ProductSaveRequest productSaveRequest) {
        productSaveRequest.setCode(FinanceHelper.createProductCode(productSaveRequest.getBrand()));
        productSaveRequest.setCost(FinanceHelper.costCalculate(productSaveRequest.getBuyingPrice(),productSaveRequest.getBuyingQuantity()));
        return ResultHelper.CREATED(mapperService.forResponse().map(productRepository.save(mapperService.forRequest().map(productSaveRequest, Product.class)), ProductResponse.class));
    }

    @Override
    public ResultData<ProductResponse> updateProduct(ProductUpdateRequest productUpdateRequest) {
        if(productUpdateRequest.getId() == null || productRepository.findById(productUpdateRequest.getId()).isEmpty()){
            throw new RuntimeException("Not Found ID");
        }
        productUpdateRequest.setCode(productRepository.findById(productUpdateRequest.getId()).orElseThrow().getCode());
        return ResultHelper.OK(mapperService.forResponse().map(productRepository.save(mapperService.forRequest().map(productUpdateRequest, Product.class)), ProductResponse.class));
    }

    @Override
    public ResultData<List<ProductDetailResponse>> getAllProduct() {
        return ResultHelper.OK(productRepository.findAll().stream().map(product -> mapperService.forResponse().map(product, ProductDetailResponse.class)).collect(Collectors.toList()));

    }

    @Override
    public ResultData<ProductDetailResponse> getByProductId(Long id) {
        return ResultHelper.OK(mapperService.forResponse().map(productRepository.findById(id).orElseThrow(),ProductDetailResponse.class));

    }

    @Override
    public Result deleteProduct(Long id) {
        productRepository.delete(productRepository.findById(id).orElseThrow());
        return new Result(true, Message.DELETED,"201");
    }
}
