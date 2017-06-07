import org.example.myproject.Catagorie
import org.example.myproject.FiscalYear
import org.example.myproject.MyAuditLogEvent
import org.example.myproject.User

class BootStrap {

  def init = { servletContext ->
    User.withNewSession {

      User user = new User(username:"user1", firstname:"firstname1", lastname:"lastname1").save(failOnError:true, flush:true)
      log.warn("Created User: $user")
      MyAuditLogEvent.list().each {
        log.warn "AuditLog entry: $it"
      }
      // delete
      user.delete(failOnError:true, flush:true)
      log.warn("Deleted user.")
      MyAuditLogEvent.list().each {
        log.warn "AuditLog entry: $it"
      }

      // Catagorie
      def fiscalYear = new FiscalYear(name:"test").save(failOnError:true, flush:true)
      def catagorie = new Catagorie(productCatagory:'testCatagorie')
      fiscalYear.addToCatagories(catagorie)
      catagorie.save(flush:true, failOnError:true)
      fiscalYear.delete(flush:true, failOnError:true)
      log.info "Application Bootstrap done."
    }
  }
  def destroy = {
  }
}
