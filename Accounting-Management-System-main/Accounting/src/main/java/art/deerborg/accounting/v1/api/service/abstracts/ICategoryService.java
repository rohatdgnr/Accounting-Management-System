package art.deerborg.accounting.v1.api.service.abstracts;

import art.deerborg.accounting.v1.api.core.result.Result;
import art.deerborg.accounting.v1.api.core.result.ResultData;
import art.deerborg.accounting.v1.api.dto.request.category.CategorySaveRequest;
import art.deerborg.accounting.v1.api.dto.request.category.CategoryUpdateRequest;
import art.deerborg.accounting.v1.api.dto.response.category.CategoryDetailResponse;
import art.deerborg.accounting.v1.api.dto.response.category.CategoryResponse;

import java.util.List;

public interface ICategoryService {
    ResultData<CategoryResponse> createCategory(CategorySaveRequest categorySaveRequest);
    ResultData<CategoryResponse> updateCategory(CategoryUpdateRequest categoryUpdateRequest);
    ResultData<List<CategoryDetailResponse>> getAllCategories();
    ResultData<CategoryDetailResponse> getByCategoryId(Long id);
    Result deleteCategory(Long id);
}
