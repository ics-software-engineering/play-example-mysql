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


**3. Set root password***

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

  1. Other developers running your system will have to set the same local credentials;
  2. It is just totally lame to put credentials into publicly available files hosted online.
   
Fortunately, there is an easy solution: reference environment variables that point to 
the actual credentials in application.conf. So, the next step is to define two environment variables with the
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
 

        

 


Screenshot
==========

![screenshot](https://raw.github.com/ics-software-engineering/play-example-mysql/master/doc/play-example-mysql-home.png)

  
   
  
 
