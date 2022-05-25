package com.nfs.academy.util;

import com.nfs.academy.entity.MessageEnvelope;
import com.nfs.academy.exceptions.InvalidMetaDataException;

public class Validator {
    /**
     * @throws InvalidMetaDataException : if it doesn't satisfy the comm constraints.
     */
    public static void validateMetaInfo(MessageEnvelope envelope) {
        System.out.println(Thread.currentThread().getName()
                + ": Validating the meta-info while registering the message..");
        if (envelope == null || envelope.getFromAddress() == null || envelope.getToAddress() == null)
            throw new InvalidMetaDataException("",
                    new InvalidMetaDataException.InvalidCommDetailException(
                            InvalidMetaDataException.InvalidCommDetailException.COMM_DETAIL_NOT_VALID));
    }
}
