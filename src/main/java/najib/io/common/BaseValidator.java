package najib.io.common;

public abstract class BaseValidator<T> {
    public abstract void validateCreate(T dto);

    public abstract void validateUpdate(T dto);
}
