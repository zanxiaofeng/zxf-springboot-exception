package zxf.springboot.support.exception;

public interface SystemError extends Error {
    default String exceptionMessage() {
        return String.format("%s, %s", getCode(), getDescription());
    }

    default SystemError copy() {
        return withNew(this.getCode(), this.getDescription(), this.getCause() == null ? null : this.getCause().copy());
    }

    default SystemError newDescription(String newDescription) {
        return withNew(this.getCode(), newDescription, this.getCause());
    }

    default SystemError withCause(Error cause) {
        return withNew(this.getCode(), this.getDescription(), cause);
    }

    default SystemError withNew(String code, String description, Error cause) {
        return new SystemError() {
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
