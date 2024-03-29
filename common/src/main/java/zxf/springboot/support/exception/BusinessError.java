package zxf.springboot.support.exception;

public interface BusinessError extends Error {
    default String exceptionMessage() {
        return String.format("%s, %s", getCode(), getDescription());
    }

    default BusinessError copy() {
        return withNew(this.getCode(), this.getDescription(), this.getCause() == null ? null : this.getCause().copy());
    }

    default BusinessError newDescription(String newDescription) {
        return withNew(this.getCode(), newDescription, this.getCause());
    }

    default BusinessError withCause(Error cause) {
        return withNew(this.getCode(), this.getDescription(), cause);
    }

    default BusinessError withNew(String code, String description, Error cause) {
        return new BusinessError() {
            @Override
            public String getCode() {
                return code;
            }

            @Override
            public String getDescription() {
                return description;
            }

            @Override
            public Error getCause() {
                return cause;
            }
        };
    }
}
