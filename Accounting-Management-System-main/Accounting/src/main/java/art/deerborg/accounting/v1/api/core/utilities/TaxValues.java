package art.deerborg.accounting.v1.api.core.utilities;

import art.deerborg.accounting.v1.api.model.TaxType;

public class TaxValues {
    public static Double getTaxValue(TaxType taxType) {
        if(taxType == TaxType.KDV){
            return 20.0;
        }
        if(taxType == TaxType.OTV){
            return 220.0;
        }
        return 0.0;
    }
}
