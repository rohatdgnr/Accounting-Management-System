package art.deerborg.accounting.v1.api.service.abstracts;

import art.deerborg.accounting.v1.api.core.result.Result;
import art.deerborg.accounting.v1.api.core.result.ResultData;
import art.deerborg.accounting.v1.api.dto.request.customer.CustomerSaveRequest;
import art.deerborg.accounting.v1.api.dto.request.customer.CustomerUpdateRequest;
import art.deerborg.accounting.v1.api.dto.response.customer.CustomerDetailResponse;
import art.deerborg.accounting.v1.api.dto.response.customer.CustomerResponse;

import java.util.List;

public interface ICustomerService {
    ResultData<CustomerResponse> createCustomer(CustomerSaveRequest customerSaveRequest);
    ResultData<CustomerResponse> updateCustomer(CustomerUpdateRequest customerUpdateRequest);
    ResultData<List<CustomerDetailResponse>> getAllCustomer();
    ResultData<CustomerDetailResponse> getByCustomerId(Long id);
    Result deleteCustomer(Long id);
}
