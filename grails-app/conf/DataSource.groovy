// MONGODB-ONLY

// mongo
grails {
  mongo {
    host = "127.0.0.1"
    port = 27017
    databaseName = "grails" // the default database name
    //username = "grails"
    //password = "grails"
    options {
      autoConnectRetry = true
      connectTimeout = 300
    }
    createDrop      = "database"    // Recreate whole database.
  }
}
