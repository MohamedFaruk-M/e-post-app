package com.nfs.academy.exceptions;

/**
 * custom exception class for Exception translation
 */
public class InvalidMetaDataException extends BusinessException {

    private static final long serialVersionUID = -926210288264853019L;

    public InvalidMetaDataException(String message) {
        super(message);
    }

    public InvalidMetaDataException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Nested class for Exception Chaining
     */
    public static class InvalidCommDetailException extends InvalidMetaDataException {
        public static final String COMM_DETAIL_NOT_VALID = "Invalid Communication details found. Comm detail should have valid from and to address";

        private static final long serialVersionUID = -969807477278574995L;

        public InvalidCommDetailException(String message) {
            super(message);
        }

    }

}
