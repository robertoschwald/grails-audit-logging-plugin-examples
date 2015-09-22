import grails.example.Role
import grails.example.User
import grails.example.UserRole

class BootStrap {

    def init = { servletContext ->

      println "Creating Users and Roles"
      def adminRole = Role.findOrCreateByAuthority('ROLE_ADMIN').save(flush: true)
      def userRole = Role.findOrCreateByAuthority('ROLE_USER').save(flush: true)
      def adminUser = User.findOrCreateByUsername('me')
      adminUser.with {
        password = "password"
      }
      adminUser.save(flush: true)
      UserRole.create adminUser, adminRole, true
      UserRole.create adminUser, userRole, true
    }

    def destroy = {
    }
}
