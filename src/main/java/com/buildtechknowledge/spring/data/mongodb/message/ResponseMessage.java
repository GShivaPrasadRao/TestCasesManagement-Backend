package com.buildtechknowledge.spring.data.mongodb.message;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseMessage {
    private String message;
}
