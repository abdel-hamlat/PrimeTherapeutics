package specialtyPatientPortal;

import primetherapeuticsCommon.User;

public final  class PatientUser implements User {
    private final String email;
    private final String password;

    public PatientUser() {
        this.email = "qa_testers@qabrains.com";
        this.password = "Password123";
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
