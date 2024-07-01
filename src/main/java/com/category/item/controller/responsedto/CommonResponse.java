package com.category.item.controller.responsedto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Builder
public class CommonResponse<T> {
    private HttpStatus statusCode;
    private String resultMsg;
    private T resultData;

    public CommonResponse(final HttpStatus statusCode, final String resultMsg) {
        this.statusCode = statusCode;
        this.resultMsg= resultMsg;
        this.resultData = null;
    }

    public static<T> CommonResponse<T> response(final HttpStatus statusCode, final String resultMsg) {
        return response(statusCode, resultMsg, null);
    }

    public static<T> CommonResponse<T> response(final HttpStatus statusCode, final String resultMsg, final T resultData) {
        return CommonResponse.<T>builder()
                .resultData(resultData)
                .statusCode(statusCode)
                .resultMsg(resultMsg)
                .build();
    }
}
