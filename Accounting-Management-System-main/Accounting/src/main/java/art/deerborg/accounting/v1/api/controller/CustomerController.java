package art.deerborg.accounting.v1.api.controller;

import art.deerborg.accounting.v1.api.core.result.Result;
import art.deerborg.accounting.v1.api.core.result.ResultData;
import art.deerborg.accounting.v1.api.dto.request.customer.CustomerSaveRequest;
import art.deerborg.accounting.v1.api.dto.request.customer.CustomerUpdateRequest;
import art.deerborg.accounting.v1.api.dto.response.category.CategoryDetailResponse;
import art.deerborg.accounting.v1.api.dto.response.customer.CustomerDetailResponse;
import art.deerborg.accounting.v1.api.dto.response.customer.CustomerResponse;
import art.deerborg.accounting.v1.api.service.abstracts.ICustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final ICustomerService customerService;

    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResultData<CustomerResponse> createCustomer(@RequestBody CustomerSaveRequest customerSaveRequest) {
        return customerService.createCustomer(customerSaveRequest);
    }

    @PutMapping
    public ResultData<CustomerResponse> updateCustomer(@RequestBody CustomerUpdateRequest customerUpdateRequest) {
        return customerService.updateCustomer(customerUpdateRequest);
    }

    @GetMapping("/{id}")
    public ResultData<CustomerDetailResponse> getByCustomerId(@PathVariable Long id) {
        return customerService.getByCustomerId(id);
    }

    @GetMapping
    public ResultData<List<CustomerDetailResponse>> getAllCustomer() {
        return customerService.getAllCustomer();
    }

    @DeleteMapping
    public Result deleteCustomer(@RequestBody Long id) {
        return customerService.deleteCustomer(id);
    }
}
