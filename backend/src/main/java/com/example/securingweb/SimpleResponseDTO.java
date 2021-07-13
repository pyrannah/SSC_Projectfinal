package com.example.securingweb;

public class SimpleResponseDTO {
    
    private boolean success;
    private String message;

    SimpleResponseDTO(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public static SimpleResponseDTOBuilder builder() {
        return new SimpleResponseDTOBuilder();
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean getSuccess() {
        return success; 
    }
    
    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    
    public static class SimpleResponseDTOBuilder {

        private boolean success;
        private String message;

        SimpleResponseDTOBuilder() {
        }

        public SimpleResponseDTOBuilder success(boolean success) {
            this.success = success;
            return this;
        }

        public SimpleResponseDTOBuilder message(String message) {
            this.message = message;
            return this;
        }

        public SimpleResponseDTO build() {
            return new SimpleResponseDTO(success, message);
        }
    }
}
