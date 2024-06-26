package art.deerborg.accounting.v1.api.service.concretes;

import art.deerborg.accounting.v1.api.core.config.modelMapper.ModelMapperService;
import art.deerborg.accounting.v1.api.core.result.Result;
import art.deerborg.accounting.v1.api.core.result.ResultData;
import art.deerborg.accounting.v1.api.core.utilities.Message;
import art.deerborg.accounting.v1.api.core.utilities.ResultHelper;
import art.deerborg.accounting.v1.api.dao.CategoryRepository;
import art.deerborg.accounting.v1.api.dto.request.category.CategorySaveRequest;
import art.deerborg.accounting.v1.api.dto.request.category.CategoryUpdateRequest;
import art.deerborg.accounting.v1.api.dto.response.category.CategoryDetailResponse;
import art.deerborg.accounting.v1.api.dto.response.category.CategoryResponse;
import art.deerborg.accounting.v1.api.model.Category;
import art.deerborg.accounting.v1.api.service.abstracts.ICategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService implements ICategoryService {
    private final ModelMapperService mapperService;
    private final CategoryRepository categoryRepository;

    public CategoryService(ModelMapperService mapperService, CategoryRepository categoryRepository) {
        this.mapperService = mapperService;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ResultData<CategoryResponse> createCategory(CategorySaveRequest categorySaveRequest) {
        return ResultHelper.CREATED(mapperService.forResponse().map(categoryRepository.save(mapperService.forRequest().map(categorySaveRequest, Category.class)),CategoryResponse.class));
    }

    @Override
    public ResultData<CategoryResponse> updateCategory(CategoryUpdateRequest categoryUpdateRequest) {
        if(categoryUpdateRequest.getId() == null || categoryRepository.findById(categoryUpdateRequest.getId()).isEmpty()){
            throw new RuntimeException("Not Found ID");
        }
        return ResultHelper.OK(mapperService.forResponse().map(categoryRepository.save(mapperService.forRequest().map(categoryUpdateRequest, Category.class)),CategoryResponse.class));
    }

    @Override
    public ResultData<List<CategoryDetailResponse>> getAllCategories() {
        return ResultHelper.OK(categoryRepository.findAll().stream().map(category -> mapperService.forResponse().map(category,CategoryDetailResponse.class)).collect(Collectors.toList()));
    }

    @Override
    public ResultData<CategoryDetailResponse> getByCategoryId(Long id) {
        return ResultHelper.OK(mapperService.forResponse().map(categoryRepository.findById(id).orElseThrow(),CategoryDetailResponse.class));
    }

    @Override
    public Result deleteCategory(Long id) {
        categoryRepository.delete(categoryRepository.findById(id).orElseThrow());
        return new Result(true, Message.DELETED,"201");
    }
}
