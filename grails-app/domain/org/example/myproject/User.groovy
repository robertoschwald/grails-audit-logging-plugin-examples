package org.example.myproject

import grails.plugins.orm.auditable.Auditable

class User implements Auditable {

    static auditable = true
    String username
    String firstname
    String lastname

    static constraints = {
    }
}
