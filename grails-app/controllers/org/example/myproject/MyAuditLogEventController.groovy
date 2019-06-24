package org.example.myproject

import static org.springframework.http.HttpStatus.*

class MyAuditLogEventController {

    MyAuditLogEventService myAuditLogEventService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond myAuditLogEventService.list(params), model:[myAuditLogEventCount: myAuditLogEventService.count()]
    }

    def show(Long id) {
        respond myAuditLogEventService.get(id)
    }

    def create() {
        respond new MyAuditLogEvent(params)
    }


    def edit(Long id) {
        respond myAuditLogEventService.get(id)
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'myAuditLogEvent.label', default: 'MyAuditLogEvent'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
