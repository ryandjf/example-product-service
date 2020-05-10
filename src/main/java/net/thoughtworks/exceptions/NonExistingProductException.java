
package net.thoughtworks.exceptions;

public class NonExistingProductException extends RuntimeException {
    public NonExistingProductException(String message) {
        super(message);
    }
}
