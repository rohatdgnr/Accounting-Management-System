package art.deerborg.accounting.v1.api.controller;

import art.deerborg.accounting.v1.api.core.result.Result;
import art.deerborg.accounting.v1.api.core.result.ResultData;
import art.deerborg.accounting.v1.api.dto.request.invoice.InvoiceSaveRequest;
import art.deerborg.accounting.v1.api.dto.request.invoice.InvoiceUpdateRequest;
import art.deerborg.accounting.v1.api.dto.response.invoice.InvoiceDetailResponse;
import art.deerborg.accounting.v1.api.dto.response.invoice.InvoiceResponse;
import art.deerborg.accounting.v1.api.service.abstracts.IInvoiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {
    private final IInvoiceService invoiceService;

    public InvoiceController(IInvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping
    public ResultData<InvoiceResponse> createInvoice(@RequestBody InvoiceSaveRequest invoiceSaveRequest){
        return invoiceService.createInvoice(invoiceSaveRequest);
    }
    @PutMapping
    public ResultData<InvoiceResponse> updateInvoice(@RequestBody InvoiceUpdateRequest invoiceUpdateRequest){
        return invoiceService.updateInvoice(invoiceUpdateRequest);
    }
    @GetMapping("/{id}")
    public ResultData<InvoiceDetailResponse> getByInvoiceId(@PathVariable Long id){
        return invoiceService.getByInvoiceId(id);
    }
    @GetMapping
    public ResultData<List<InvoiceDetailResponse>> getAllInvoice(){
        return invoiceService.getAllInvoice();
    }

    @GetMapping("/pdf/{id}")
    public ResponseEntity<byte[]> generateInvoicePdf(@PathVariable Long id){
        return invoiceService.generateInvoicePdf(id);
    }

    @DeleteMapping
    public Result deleteInvoice(@RequestBody Long id){
        return invoiceService.deleteInvoice(id);
    }

}
