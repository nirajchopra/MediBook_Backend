package com.mbp.mediBook.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageResponse {
    private boolean success;
    private String message;
    private Object data;
    
    public MessageResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}