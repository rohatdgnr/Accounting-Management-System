package art.deerborg.accounting.v1.api.service.concretes;

import art.deerborg.accounting.v1.api.core.config.modelMapper.ModelMapperService;
import art.deerborg.accounting.v1.api.core.result.Result;
import art.deerborg.accounting.v1.api.core.result.ResultData;
import art.deerborg.accounting.v1.api.core.utilities.Message;
import art.deerborg.accounting.v1.api.core.utilities.ResultHelper;
import art.deerborg.accounting.v1.api.dao.CustomerRepository;
import art.deerborg.accounting.v1.api.dto.request.customer.CustomerSaveRequest;
import art.deerborg.accounting.v1.api.dto.request.customer.CustomerUpdateRequest;
import art.deerborg.accounting.v1.api.dto.response.customer.CustomerDetailResponse;
import art.deerborg.accounting.v1.api.dto.response.customer.CustomerResponse;
import art.deerborg.accounting.v1.api.model.Customer;
import art.deerborg.accounting.v1.api.service.abstracts.ICustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService implements ICustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapperService mapperService;

    public CustomerService(CustomerRepository customerRepository, ModelMapperService mapperService) {
        this.customerRepository = customerRepository;
        this.mapperService = mapperService;
    }

    @Override
    public ResultData<CustomerResponse> createCustomer(CustomerSaveRequest customerSaveRequest) {
        return ResultHelper.CREATED(mapperService.forResponse().map(customerRepository.save(mapperService.forRequest().map(customerSaveRequest, Customer.class)), CustomerResponse.class));
    }

    @Override
    public ResultData<CustomerResponse> updateCustomer(CustomerUpdateRequest customerUpdateRequest) {
        if(customerUpdateRequest.getId() == null || customerRepository.findById(customerUpdateRequest.getId()).isEmpty()){
            throw new RuntimeException("Not Found ID");
        }
        return ResultHelper.OK(mapperService.forResponse().map(customerRepository.save(mapperService.forRequest().map(customerUpdateRequest, Customer.class)),CustomerResponse.class));
    }

    @Override
    public ResultData<List<CustomerDetailResponse>> getAllCustomer() {
        return ResultHelper.OK(customerRepository.findAll().stream().map(customer -> mapperService.forResponse().map(customer, CustomerDetailResponse.class)).collect(Collectors.toList()));
    }

    @Override
    public ResultData<CustomerDetailResponse> getByCustomerId(Long id) {
        return ResultHelper.OK(mapperService.forResponse().map(customerRepository.findById(id).orElseThrow(),CustomerDetailResponse.class));
    }

    @Override
    public Result deleteCustomer(Long id) {
        customerRepository.delete(customerRepository.findById(id).orElseThrow());
        return new Result(true, Message.DELETED,"201");
    }
}
