package art.deerborg.accounting.v1.api.service.concretes;

import art.deerborg.accounting.v1.api.core.config.modelMapper.ModelMapperService;
import art.deerborg.accounting.v1.api.core.utilities.PdfGenerator;
import art.deerborg.accounting.v1.api.core.result.Result;
import art.deerborg.accounting.v1.api.core.result.ResultData;
import art.deerborg.accounting.v1.api.core.utilities.FinanceHelper;
import art.deerborg.accounting.v1.api.core.utilities.Message;
import art.deerborg.accounting.v1.api.core.utilities.ResultHelper;
import art.deerborg.accounting.v1.api.dao.InvoiceRepository;
import art.deerborg.accounting.v1.api.dao.ProductRepository;
import art.deerborg.accounting.v1.api.dto.request.invoice.InvoiceSaveRequest;
import art.deerborg.accounting.v1.api.dto.request.invoice.InvoiceUpdateRequest;
import art.deerborg.accounting.v1.api.dto.response.invoice.InvoiceDetailResponse;
import art.deerborg.accounting.v1.api.dto.response.invoice.InvoiceResponse;
import art.deerborg.accounting.v1.api.model.Invoice;
import art.deerborg.accounting.v1.api.model.Product;
import art.deerborg.accounting.v1.api.service.abstracts.IInvoiceService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceService implements IInvoiceService {
    private final ModelMapperService mapperService;
    private final InvoiceRepository invoiceRepository;
    private final ProductRepository productRepository;

    public InvoiceService(ModelMapperService mapperService, InvoiceRepository invoiceRepository, ProductRepository productRepository) {
        this.mapperService = mapperService;
        this.invoiceRepository = invoiceRepository;
        this.productRepository = productRepository;
    }

    @Override
    public ResultData<InvoiceResponse> createInvoice(InvoiceSaveRequest invoiceSaveRequest) {
        Product product = productRepository.findById(invoiceSaveRequest.getProduct().getId()).orElseThrow();
        if(!FinanceHelper.checkStock(product.getStock(), invoiceSaveRequest.getQuantity())) {
            throw new RuntimeException("stok yetersiz");
        }
        if(!FinanceHelper.checkDate(product.getDate(),invoiceSaveRequest.getDate())){
            throw new RuntimeException("saçma tarih");
        }

        invoiceSaveRequest.setTotalAmount(FinanceHelper.totalIncome(invoiceSaveRequest.getQuantity(),product.getQuantityPrice()));
        invoiceSaveRequest.setTaxAmount(FinanceHelper.taxAmount(product.getTax(), invoiceSaveRequest.getTotalAmount()));
        invoiceSaveRequest.setNetAmount(FinanceHelper.calculateNetIncome(product.getTax(),invoiceSaveRequest.getTotalAmount()));

        return ResultHelper.CREATED(mapperService.forResponse().map(invoiceRepository.save(mapperService.forRequest().map(invoiceSaveRequest, Invoice.class)), InvoiceResponse.class));
    }

    @Override
    public ResultData<InvoiceResponse> updateInvoice(InvoiceUpdateRequest invoiceUpdateRequest) {
        if(invoiceUpdateRequest.getId() == null || invoiceRepository.findById(invoiceUpdateRequest.getId()).isEmpty()){
            throw new RuntimeException("Not Found ID");
        }
        Product product = productRepository.findById(invoiceUpdateRequest.getProduct().getId()).orElseThrow();
        if(!FinanceHelper.checkStock(product.getStock(), invoiceUpdateRequest.getQuantity())) {
            throw new RuntimeException("stok yetersiz");
        }
        invoiceUpdateRequest.setTotalAmount(FinanceHelper.totalIncome(invoiceUpdateRequest.getQuantity(),product.getQuantityPrice()));
        invoiceUpdateRequest.setTaxAmount(FinanceHelper.taxAmount(product.getTax(), invoiceUpdateRequest.getTotalAmount()));
        invoiceUpdateRequest.setNetAmount(FinanceHelper.calculateNetIncome(product.getTax(),invoiceUpdateRequest.getTotalAmount()));
        return ResultHelper.OK(mapperService.forResponse().map(invoiceRepository.save(mapperService.forRequest().map(invoiceUpdateRequest, Invoice.class)), InvoiceResponse.class));
    }

    @Override
    public ResultData<List<InvoiceDetailResponse>> getAllInvoice() {
        return ResultHelper.OK(invoiceRepository.findAll().stream().map(invoice -> mapperService.forResponse().map(invoice, InvoiceDetailResponse.class)).collect(Collectors.toList()));

    }

    @Override
    public ResultData<InvoiceDetailResponse> getByInvoiceId(Long id) {
        return ResultHelper.OK(mapperService.forResponse().map(invoiceRepository.findById(id).orElseThrow(),InvoiceDetailResponse.class));

    }

    @Override
    public Result deleteInvoice(Long id) {
        invoiceRepository.delete(invoiceRepository.findById(id).orElseThrow());
        return new Result(true, Message.DELETED,"201");
    }

    @Override
    public ResponseEntity<byte[]> generateInvoicePdf(Long id) {
        InvoiceDetailResponse detailResponse = mapperService.forResponse().map(invoiceRepository.findById(id).orElseThrow(),InvoiceDetailResponse.class);
        byte[] pdfByte;
        try {
            pdfByte = PdfGenerator.createPdf(detailResponse);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename","invoice-"+id+".pdf");
        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfByte);
    }
}
