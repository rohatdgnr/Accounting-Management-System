package art.deerborg.accounting.v1.api.core.utilities;

import art.deerborg.accounting.v1.api.core.result.Result;
import art.deerborg.accounting.v1.api.core.result.ResultData;

public class ResultHelper {
    public static <T>ResultData<T> OK(T data) {
        return new ResultData<>(true,Message.OK,"200",data);
    }
    public static <T>ResultData<T> CREATED(T data) {
        return new ResultData<>(true,Message.CREATED,"201",data);
    }
    public static Result DELETED() {
        return new Result(true,Message.DELETED,"200");
    }
}
