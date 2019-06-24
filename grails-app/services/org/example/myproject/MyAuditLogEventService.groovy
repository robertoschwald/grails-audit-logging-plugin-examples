package org.example.myproject

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional

@Service(MyAuditLogEvent)
@Transactional('auditing')
interface MyAuditLogEventService {

    MyAuditLogEvent get(Serializable id)

    List<MyAuditLogEvent> list(Map args)

    Long count()

    void delete(Serializable id)

    MyAuditLogEvent save(MyAuditLogEvent myAuditLogEvent)

}