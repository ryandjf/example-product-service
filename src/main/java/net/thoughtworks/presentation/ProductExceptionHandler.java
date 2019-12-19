
package net.thoughtworks.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import net.thoughtworks.exceptions.NonExistingProductException;

@RestControllerAdvice
public class ProductExceptionHandler {
    @ExceptionHandler(NonExistingProductException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNonExistingProduct() {

    }
}
