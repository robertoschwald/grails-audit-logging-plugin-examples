package org.example.myproject

import grails.plugins.orm.auditable.Auditable
import groovy.util.logging.Slf4j

@Slf4j
class Catagorie implements Auditable {
    String productCatagory;

    Date dateCreated
    Date lastUpdated
    String lastUpdatedBy
    String createdBy

    static constraints = {
        lastUpdatedBy nullable: true;
        createdBy nullable: true;
    }
    static belongsTo = [fiscalYear:FiscalYear];

    static auditable = true
    static stampable = true

    def onSave = {
        log.warn "new catagorie  inserted"
        // may optionally refer to newState map
    }
    def onDelete = {
        log.warn "catagorie  was deleted"
        // may optionally refer to oldState map
    }
    def onChange = { oldMap,newMap ->
        log.warn "catagorie  was changed ..."
        oldMap.each({ key, oldVal ->
            if(oldVal != newMap[key]) {
                log.warn " * $key changed from $oldVal to " + newMap[key]
            }
        })
    }
}
