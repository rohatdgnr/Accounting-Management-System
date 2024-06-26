package art.deerborg.accounting.v1.api.controller;

import art.deerborg.accounting.v1.api.core.result.Result;
import art.deerborg.accounting.v1.api.core.result.ResultData;
import art.deerborg.accounting.v1.api.dto.request.category.CategorySaveRequest;
import art.deerborg.accounting.v1.api.dto.request.category.CategoryUpdateRequest;
import art.deerborg.accounting.v1.api.dto.response.category.CategoryDetailResponse;
import art.deerborg.accounting.v1.api.dto.response.category.CategoryResponse;
import art.deerborg.accounting.v1.api.service.abstracts.ICategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResultData<CategoryResponse> createCategory(@RequestBody CategorySaveRequest categorySaveRequest){
        return categoryService.createCategory(categorySaveRequest);
    }
    @PutMapping
    public ResultData<CategoryResponse> updateCategory(@RequestBody CategoryUpdateRequest categorySaveRequest){
        return categoryService.updateCategory(categorySaveRequest);
    }
    @GetMapping("/{id}")
    public ResultData<CategoryDetailResponse> getByCategoryId(@PathVariable Long id){
        return categoryService.getByCategoryId(id);
    }
    @GetMapping
    public ResultData<List<CategoryDetailResponse>> getAllCategories(){
        return categoryService.getAllCategories();
    }
    @DeleteMapping
    public Result deleteCategory(@RequestBody Long id){
        return categoryService.deleteCategory(id);
    }
}
