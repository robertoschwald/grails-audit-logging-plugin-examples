package org.example.myproject

class FiscalYear {
    static auditable = true

    String name

    static hasMany = [catagories:Catagorie]

    static mapping = {
        catagories cascade: 'all-delete-orphan'
    }
}
