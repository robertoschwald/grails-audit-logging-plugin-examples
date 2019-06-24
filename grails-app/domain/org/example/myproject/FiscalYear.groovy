package org.example.myproject

import grails.plugins.orm.auditable.Auditable

class FiscalYear implements Auditable {
    static auditable = true

    String name

    static hasMany = [catagories:Catagorie]

    static mapping = {
        catagories cascade: 'all-delete-orphan'
    }
}
