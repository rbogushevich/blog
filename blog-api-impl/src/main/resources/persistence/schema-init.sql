/*Table structure for table role */
CREATE TABLE IF NOT EXISTS role (
  id bigint NOT NULL AUTO_INCREMENT,
  name nvarchar2(50) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `role` */
merge  into role(id,name) values (1,'ROLE_ADMIN'),(2,'ROLE_USER');

/*Table structure for table user */
CREATE TABLE IF NOT EXISTS user (
	id bigint NOT NULL AUTO_INCREMENT,
	login nvarchar2(100) NOT NULL,
	password nvarchar2(64) NOT NULL,
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table user */
merge  into user(login,id,password) values ('admin',1,'8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918'),('test',2,'9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08');

/*Table structure for table user_to_role */
CREATE TABLE IF NOT EXISTS user_to_role (
  user_id bigint,
  role_id bigint,
  PRIMARY KEY (user_id, role_id),
  FOREIGN KEY (user_id) REFERENCES user(id),
  FOREIGN KEY (role_id) REFERENCES role(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table user_to_role */
merge  into user_to_role(user_id,role_id) values (1,1),(2,2);

/*Table structure for table post */
CREATE TABLE IF NOT EXISTS post (
  id bigint NOT NULL AUTO_INCREMENT,
  header nvarchar2(100) NOT NULL,
  text nvarchar2(1000) DEFAULT NULL,
  date datetime NOT NULL,
  user_id bigint NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES user (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

merge  into post(id,header,text,date,user_id) values (1,'admin post','admin post text','2013-07-07 13:08:00',1),(2,'test post','test post text','2014-07-07 23:22:00',2),(3,'test changed','test changed','2014-07-07 22:57:17',2),(4,'test','test test text some text','2014-07-07 22:41:07',2),(5,'many comments','many comments text','2014-07-07 22:41:38',2), (6,'6','6','2013-07-07 13:08:00',1), (7,'7','7','2013-07-07 13:08:00',1), (8,'8','8','2013-07-07 13:08:00',1), (9,'9','9','2013-07-07 13:08:00',1), (10,'10','10','2013-07-07 13:08:00',1),(11,'11','11','2013-07-07 13:08:00',1);
/*Data for the table post */

/*Table structure for table comment */
CREATE TABLE IF NOT EXISTS comment (
  id bigint NOT NULL AUTO_INCREMENT,
  text nvarchar2(1000) NOT NULL,
  date date NOT NULL,
  post_id bigint NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (post_id) REFERENCES post (id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table comment */
merge  into comment(id,text,date,post_id) values (1,'first second comment','2014-07-07',2),(2,'comment','2014-04-23',1),(3,'same comment','2014-07-07',1),(3,'one comment','2014-07-07',1),(4,'comment','2014-07-07',1),(5,'first next','2014-07-07',3),(6,'first comment','2014-04-24',2);