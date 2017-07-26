package com.doit.wheels.dao.entities;

import com.doit.wheels.dao.entities.basic.Contact;
import com.doit.wheels.dao.entities.basic.Description;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Country extends Description {

    @OneToMany(mappedBy = "country")
    @JsonBackReference
    private Set<Contact> contacts;

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }
}
