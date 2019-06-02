package com.artemsirosh.tij.io;

import com.google.common.base.MoreObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Created on 21.06.2017.
 * </p>
 *
 * @author Artemis A. Sirosh
 */
class Logon implements Serializable {

    private Date date;
    private String username;
    private transient String password;

    public Logon(String username, String password) {
        this.username = username;
        this.password = password;
        this.date = new Date();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("Logon info")
                .add("username", username)
                .add("date", date)
                .add("password", password)
                .toString();
    }
}
