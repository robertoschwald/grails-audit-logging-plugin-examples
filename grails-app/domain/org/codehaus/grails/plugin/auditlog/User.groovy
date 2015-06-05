package org.codehaus.grails.plugin.auditlog

class User {

	transient springSecurityService

	static auditable = true

	String username
	String password
	boolean enabled = true
	boolean accountExpired = false
	boolean accountLocked = false
	boolean passwordExpired = false
	Date lastLogin
	Date dateCreated
	Date lastUpdated

	static constraints = {
		username blank: false, unique: true
		password blank: false
		lastLogin nullable:true
	}

	static mapping = {
		//password column: '`password`' // enable for other dbs than HSQLDB
	}

	Set<Role> getAuthorities() {
		if (!this.id) return []
		UserRole.findAllByUser(this).collect { it.role } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}
}
