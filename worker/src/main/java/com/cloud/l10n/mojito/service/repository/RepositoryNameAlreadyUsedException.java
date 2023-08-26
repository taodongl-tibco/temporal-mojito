package com.cloud.l10n.mojito.service.repository;

public class RepositoryNameAlreadyUsedException extends Exception {

    public RepositoryNameAlreadyUsedException(String message) {
        super(message);
    }

    public RepositoryNameAlreadyUsedException(Throwable cause) {
        super(cause);
    }
}

