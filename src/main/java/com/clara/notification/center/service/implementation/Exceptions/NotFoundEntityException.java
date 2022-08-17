package com.clara.notification.center.service.implementation.Exceptions;

public class NotFoundEntityException extends RuntimeException {
    public NotFoundEntityException(int entityId) {
        super("Data entity not found " + entityId);
    }
    public NotFoundEntityException(String entityId) {
        super("Data entity not found " + entityId);
    }

}
