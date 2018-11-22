package app.java.com.presenter.interfaces;

public interface ResolveConflictResultInterface {

    void onSuccessConflictResolved();

    void onSuccessInvalidResolved();

    void onErrorConflictFix(String message);

    void onErrorInvalidFix(String message);
}
