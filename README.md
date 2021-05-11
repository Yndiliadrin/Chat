# ChitChat
A simple chat application written in Java. It was a school project, not likely gets updated.

## General
The application contains 2 main controller wich can be usefull for endusers
- Chat-desktop: its a desktop applictaion, we can call it the admin page, here you can create and delete rooms, and here you can delet users, nothing else, you cannot read the messages or anything else.
- Chat-webapp: the "main" application, here the users can regist to the site, login search amongs the rooms or users, send them messages or pictures

## TODO before start
If you want to use this, application, there is a few step you have to do before starting:
  1) You have to get and install SQLite, and Tomcat
  2) In the Chat/Chat-core/resources/application.properties file, you should chage the jdbc url, to your database, if you need it, you can find a template database nex to the properties file (If you are useing IntelliJ IDEA you can use the built in Database manager) [Dummy admin users password is: 1Q2w3e4r]
  3) Affter all of this, I recomend to run a Maven install (Again, in IntelliJ IDEA, right side of the window, Maven Chat/Lifecycle/install, it will install every other modul too)
  4) If you want to create rooms, you can do it from to Desktop application (Starts with Maven::window Chat/Chat-desktop/plugins/javafx/javafx:run)
  5) After all of this you can start the web module and use it (Setup the Tomcat9 configuration -> click Run/press 'Shitf'+F10), you can reach it on http://localhost:{whatever port you set for the Tomcat configuration}.
  6) And done, you have your very own chat application running on your computer
