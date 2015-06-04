package org.codehaus.grails.plugin.auditlog

import org.codehaus.groovy.grails.plugins.orm.auditable.AuditLogEvent

class AuditLogController {

  static defaultAction = "list"

  def list() {
    renderInto('list') << queryAuditLogEventsMap << request.xhr
  }

  def view(Long id) {
    def auditEventInstance = Book.get(id)
    if (!auditEventInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'auditlog.label', default: 'Book'), id])
      redirect(action: "list")
      return
    }
    [item: auditEventInstance]
  }

  // Build the criteria for querying AuditLogEvent
  private Closure<? extends Map> queryAuditLogEventsMap = { String qs ->
    List<String> queries = qs?.splitToQueries()
    def criteria = AuditLogEvent.createCriteria()
    def max = params.max ?: 10
    def offset = params.offset ?: 0
    def items = criteria.list(max:max, offset:offset) {
      and {
        queries?.each { query ->
          or {
            ilike 'actor', query
            ilike 'eventName', query
            ilike 'className', query
            ilike 'propertyName', query
            ilike 'oldValue', query
            ilike 'newValue', query
          }
        }
      }
      order 'dateCreated', 'desc'
    }
    [items:items, max:max, offset:offset, total:items.totalCount, query:qs]
  }
}
