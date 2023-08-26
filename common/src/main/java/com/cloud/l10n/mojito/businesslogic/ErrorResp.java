package com.cloud.l10n.mojito.businesslogic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public record ErrorResp(List<ErrorElement> errors) {
    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {

        private final List<ErrorElement> elements = new ArrayList<>();

        protected Builder() {
        }

        public Builder add(String message) {
            this.elements.add(new ErrorElement(message));
            return this;
        }

        public ErrorResp build() {
            return new ErrorResp(elements);
        }
    }
    public record ErrorElement(String message) {
    }
}
