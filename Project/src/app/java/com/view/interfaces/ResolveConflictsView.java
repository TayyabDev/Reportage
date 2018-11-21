package app.java.com.view.interfaces;

import app.java.com.model.Exceptions.InsertException;

import java.util.List;

public interface ResolveConflictsView {


    // CHANGE TO ConflictException list in future
    void getErrors(List<InsertException> errors );




}
