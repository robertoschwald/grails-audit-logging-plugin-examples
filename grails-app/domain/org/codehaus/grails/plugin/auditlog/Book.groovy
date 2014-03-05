package org.codehaus.grails.plugin.auditlog

class Book {
  static auditable = true

  String title
  String author

  static constraints = {
    title nullable: false
    author nullable:false
  }


  def onSave = {
    log.info "onSave called: ${it}"
  }

  def onDelete = {
    log.info "onDelete called ..."
  }

  def onChange = { oldMap,newMap ->
    log.info "onChange called ..."
    oldMap.each({ key, oldVal ->
      if(oldVal != newMap[key]) {
        println " * $key changed from $oldVal to " + newMap[key]
      }
    })
  }

}
