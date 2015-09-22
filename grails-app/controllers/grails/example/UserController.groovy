package grails.example

import grails.transaction.Transactional

@Transactional(readOnly = true)
class UserController {
  static scaffold = true
}
