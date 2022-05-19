package zxf.springboot.support.exception;

public interface BusinessError {
    String getCode();

    String getDescription();

    BusinessError getCause();

    default String exceptionMessage() {
        return String.format("%s, %s", getCode(), getDescription());
    }

    default BusinessError copy() {
        return withNew(this.getCode(), this.getDescription(), this.getCause() == null ? null : this.getCause().copy());
    }

    default BusinessError newDescription(String newDescription) {
        return withNew(this.getCode(), newDescription, this.getCause());
    }

    default BusinessError withCause(BusinessError cause) {
        return withNew(this.getCode(), this.getDescription(), cause);
    }

    default BusinessError withNew(String code, String description, BusinessError cause) {
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
            public BusinessError getCause() {
                return cause;
            }
        };
    }
}
