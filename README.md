Summary
=======

This project shows how to set up your Play Framework application to:

  * use MySQL locally.
  * deploy to CloudBees and configure to use their MySQL database.
  

Local use of MySQL with Play
============================

Here are the steps to use MySQL locally for development of Play applications:

**1. Download and install MySQL**

Install MySQL by downloading the [MySQL Community Server](http://dev.mysql.com/downloads/mysql/) following the [instructions](http://dev.mysql.com/doc/refman/5.6/en/installing.html).
You will need to create a free account. This project was tested using MySQL
Community Server 5.6.12 for Mac OS X version 10.7 (x86, 64 bit). 

**2. Start MySQL**

This varies depending upon the platform.  On a Mac, the installation process enables you to create
a preferences panel where you can start or stop your local MySQL server:

![screenshot](https://raw.github.com/ics-software-engineering/play-example-mysql/master/doc/play-example-mysql-mac-prefs-panel.png)


**3. Set root password**

The most important thing to do following your local installation is to immediately create a 
password for the root accounts.  The following commands show one way to do it:

    $ mysql -u root
    mysql> select User, Host, Password from mysql.user; 
    mysql> update mysql.user SET Password = PASSWORD('ReplaceWithGoodPassword') WHERE User = 'root';
    mysql> flush privileges;
    
See the [post-installation instructions](http://dev.mysql.com/doc/refman/5.7/en/postinstallation.html)
for more details. For example, you might want to drop the "test" database, or restrict
anonymous use. 

**4. Create user and password environment variables**

There are at least two good reasons to not put your MySQL credentials (username and password) in your Play application.conf file
if you are using a cloud-based hosting service such as GitHub:

  1. Other developers working on the system will have to set the same local credentials;
  2. It is just totally lame to put credentials into publicly available files hosted online.
   
Fortunately, there is an easy solution: reference environment variables that point to 
the actual credentials in application.conf instead. To prepare for this, the next step is to define two environment variables with the
MySQL username and password you wish to use for local Play development.  On Unix, you might edit ~/.profile to include:

    export PLAY_MYSQL_USER=root
    export PLAY_MYSQL_PASSWORD=ReplaceWithGoodPassword
    
If you choose to create a new MySQL user rather than using the root user, then 
you will need to be sure to grant that user privileges for the database
manipulated by the application. 

**5. Create the database to be used with your Play application**

A significant difference between the default "H2" database application used by Play and MySQL
is that H2 will automatically create the database to be used with your Play
application, but but MySQL will not. Thus, you have to manually create the database (but not the schema) to be used
with your application.

For this example, we will call our database "playexamplemysql". Assuming we 
are using the root user, you can create it in MySQL with the following

    $ mysql -u root -p
    Enter password: <enter password here>
    mysql> create database playexamplemysql;
    Query OK, 1 row affected (0.00 sec)
    mysql> exit
 
**6. Edit Build.scala to import the mysql connector**

Add this line:

    "mysql" % "mysql-connector-java" % "5.1.18"

See the [example Build.scala file](https://github.com/ics-software-engineering/play-example-mysql/blob/master/project/Build.scala) for details.

**7. Edit application.conf to use MySQL database properties**

You will edit four properties to something similar to the following:

    db.default.driver=com.mysql.jdbc.Driver
    db.default.url="jdbc:mysql://localhost/playexamplemysql?characterEncoding=UTF-8"
    db.default.user=${?PLAY_MYSQL_USER}
    db.default.password=${?PLAY_MYSQL_PASSWORD}
    
Note that the db.default.url property references the newly created database (playexamplemysql), and the
db.default.user and db.default.password properties reference the newly created environment variables with the MySQL
credentials.

To enable the ORM to automatically create and maintain the MySQL tables and schemas, you will want
to add the following line in the Evolutions section:

    applyEvolutions.default=true

Finally, to activate the Ebean ORM (if that's what you want), you'll want to make sure the following 
line is uncommented:

    ebean.default="models.*"

See the [example application.conf](https://github.com/ics-software-engineering/play-example-mysql/blob/master/conf/application.conf) to 
see all these changes in context.


Test your local MySQL installation
==================================

An easy way to test your local MySQL installation is to run this sample application (play-example-mysql).
This application minimally enhances the default Play application with a single entity (PageRetrieval),
an instance of which is created and saved in the MySQL database each time the home page is retrieved.  The index
controller is modified to retrieve the total number of PageRetrieval instances from the database
each time a request for the home page is received, and print that out that total in the home page as illustrated
in the following screen shot:

![screenshot](https://raw.github.com/ics-software-engineering/play-example-mysql/master/doc/play-example-mysql-home.png)

To get this application to run, just do the following:

  * Install and run MySQL as described above. 
  * Define the PLAY_MYSQL_USER and PLAY_MYSQL_PASSWORD environment variables.
  * Download the system, cd into the directory, and invoke "play run".
  * Retrieve the system in your browser at http://localhost:9000
  
    
  
   
  
 
