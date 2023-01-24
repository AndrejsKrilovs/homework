# Book store application.

**Technologies:**
* Java 11 as programming language
* Spring boot as framework to build test application
* Spring security to get permission add book for admin user
* Mustashe as template engine
* In memory Apache derby database.

All users can show needed books, but admin can add those.

**Preinstall steps:**
* Just download run *-jar* command.
* Then go to http://localhost:8080/ and application will works.

Some additional steps to be implemented:
* Using **ApacheMQ** to synhronize data with external system.
* Docker implementation for simple deploy (in clouds)
* Migrating to REST-API, if system will grow, also it helps use single REST-API for all platforms.