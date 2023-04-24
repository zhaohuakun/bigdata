package com.big.data.bean.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommonResponse {
    private int code;
    private String message;
}
