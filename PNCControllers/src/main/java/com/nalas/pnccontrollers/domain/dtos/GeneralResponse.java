package com.nalas.pnccontrollers.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@AllArgsConstructor
public class GeneralResponse {
    private String message;
    private Object data;

    // method to start using the builder design pattern
    public static Builder builder() {
        return new Builder();
    }

    // static nested class to implements Builder design pattern
   public static class Builder {
       private String message;
       private Object data;
       private HttpStatus status = HttpStatus.OK; // By default, all responses will have OK status

       // method to add a message to the response
       public Builder message(String message) {
           this.message = message;
           return this;
       }

       // method to add the data to send in the response
       public Builder data(Object data) {
           this.data = data;
           return this;
       }

       // method to set the response status
       public Builder status(HttpStatus status) {
           this.status = status;
           return this;
       }

       // method to create the response from the configured parameters
       public ResponseEntity<GeneralResponse> build () {
           if (this.message == null) {
               this.message = this.status.getReasonPhrase();
           }

           return new ResponseEntity<>(new GeneralResponse(this.message, this.data), this.status);
       }
   }

}
