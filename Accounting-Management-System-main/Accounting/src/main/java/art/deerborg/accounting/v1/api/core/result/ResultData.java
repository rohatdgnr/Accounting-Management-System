package art.deerborg.accounting.v1.api.core.result;

import lombok.Getter;

@Getter
public class ResultData<T> extends Result{
    private T data;

    public ResultData(boolean status, String message, String httpCode, T data) {
        super(status, message, httpCode);
        this.data = data;
    }
}
