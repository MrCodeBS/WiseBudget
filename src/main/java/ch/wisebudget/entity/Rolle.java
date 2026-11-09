package ch.wisebudget.entity;

import io.quarkus.security.jpa.RolesValue;

public enum Rolle {
    USER,
    ADMIN;

    @RolesValue
    public String getRolleName() {
        return name();
    }
}
