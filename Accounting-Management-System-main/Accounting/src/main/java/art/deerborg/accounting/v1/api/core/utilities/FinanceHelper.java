package art.deerborg.accounting.v1.api.core.utilities;

import art.deerborg.accounting.v1.api.model.TaxType;

import java.time.LocalDate;
import java.util.UUID;

public class FinanceHelper {
    public static Double calculateNetIncome(TaxType type, Double total) {
        return total - ((total*TaxValues.getTaxValue(type)) / 100);
    }
    public static Double totalIncome(Integer quantity,Double quantityPrice){
        return quantity * quantityPrice;
    }
    public static Double taxAmount(TaxType type,Double total){
        return (total*TaxValues.getTaxValue(type) / 100);
    }
    public static boolean checkStock(Integer stock, Integer quantity){
        return stock >= quantity;
    }
    public static boolean checkDate(LocalDate createDate,LocalDate salesDate){
        return !salesDate.isBefore(createDate);
    }
    public static String createProductCode(String productName){
        char first = productName.charAt(0);
        char last = productName.charAt(productName.length() - 1);
        return String.valueOf(first) + last + UUID.randomUUID().toString();
    }
    public static Double costCalculate(Double buyingPrice,Integer buyingQuantity){
        return buyingPrice*buyingQuantity;
    }
}
