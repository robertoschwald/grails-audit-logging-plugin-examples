import org.codehaus.grails.plugin.auditlog.Role
import org.codehaus.grails.plugin.auditlog.User
import org.codehaus.grails.plugin.auditlog.UserRole

class BootStrap {

    def init = { servletContext ->
      def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
      def testUser = new User(username: 'me', password: 'password')
      testUser.save(flush: true)
      UserRole.create testUser, adminRole, true
    }

    def destroy = {
    }
}
