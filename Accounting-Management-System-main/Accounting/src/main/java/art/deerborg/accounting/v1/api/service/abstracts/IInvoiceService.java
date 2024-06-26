package art.deerborg.accounting.v1.api.service.abstracts;

import art.deerborg.accounting.v1.api.core.result.Result;
import art.deerborg.accounting.v1.api.core.result.ResultData;
import art.deerborg.accounting.v1.api.dto.request.invoice.InvoiceSaveRequest;
import art.deerborg.accounting.v1.api.dto.request.invoice.InvoiceUpdateRequest;
import art.deerborg.accounting.v1.api.dto.response.invoice.InvoiceDetailResponse;
import art.deerborg.accounting.v1.api.dto.response.invoice.InvoiceResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IInvoiceService {
    ResultData<InvoiceResponse> createInvoice(InvoiceSaveRequest invoiceSaveRequest);
    ResultData<InvoiceResponse> updateInvoice(InvoiceUpdateRequest invoiceUpdateRequest);
    ResultData<List<InvoiceDetailResponse>> getAllInvoice();
    ResultData<InvoiceDetailResponse> getByInvoiceId(Long id);
    Result deleteInvoice(Long id);
    ResponseEntity<byte[]> generateInvoicePdf(Long id);
}
