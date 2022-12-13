CREATE DATABASE crm;
USE crm;
-- USE sys;
-- DROP DATABASE crm;
-- TABLE USER
CREATE TABLE User (
	userID int auto_increment,
    lastName nvarchar(50),
    firstName nvarchar(50) NOT NULL,
    username varchar(50) NOT NULL,
    email varchar(50),
    roleID int,
    pwd varchar(100),
    avatar varchar(100),
    bgImg varchar(100),
    
    PRIMARY KEY pk_user (userID)
);
-- TABLE ROLE
CREATE TABLE Role (
	roleID int auto_increment,
    name nvarchar(20),
    descr varchar(50),
    PRIMARY KEY pk_role (roleID)
);
-- TABLE PROJECT
CREATE TABLE Project (
	projectID int auto_increment,
    name nvarchar(50),
    startD date,
    endD date,
    leader int,
    PRIMARY KEY pk_prj (projectID)
);
-- TALBE TASK
CREATE TABLE Task (
	taskID int auto_increment,
	projectID int,
    member int,
    name nvarchar(50),
    start_date date,
    end_date date,
    status int,
    PRIMARY KEY pk_tsk (taskID)
);
-- TABLE STATUS 
CREATE TABLE Status (
	statusID int auto_increment,
    name nvarchar(20),
    PRIMARY KEY pk_st (statusID)
);

-- User
INSERT INTO User VALUES (1,'Nông Hải ','Dương','nhd','nhd@gmail.com',1,'nhdjava123','plugins/images/users/1.png','plugins/images/large/1.png');
INSERT INTO User VALUES (2,'Lê Việt ','Khải','lvk','lvk@gmail.com',3,'lvkjava123','plugins/images/users/2.jpg','plugins/images/large/2.jpg');
INSERT INTO User VALUES (3,'Mẫn Hoàng ','Khôi','mhk','mhk@gmail.com',4,'mhkjava123','plugins/images/users/3.jpg','plugins/images/large/3.jpg');
INSERT INTO User VALUES (4,'Trần Xuân ','Thiện','txt','txt@gmail.com',3,'txtjava123','plugins/images/users/4.jpg','plugins/images/large/4.jpg');
INSERT INTO User VALUES (5,'Vũ Hữu ','Chiến','vhc','vhc@gmail.com',2,'vhcjava123','plugins/images/users/5.jpg','plugins/images/large/5.jpg');
INSERT INTO User VALUES (6,'Phan Thành ','Nguyên','ptn','ptn@gmail.com',3,'ptnjava123','plugins/images/users/6.jpg','plugins/images/large/6.jpg');
INSERT INTO User VALUES (7,'Nguyễn Ðình ','Dương','ndd','ndd@gmail.com',4,'nddjava123','plugins/images/users/7.jpg','plugins/images/large/7.jpg');
INSERT INTO User VALUES (8,'Lê Diệu','Linh','ldl','ldl@gmail.com',3,'ldljava123','plugins/images/users/8.jpg','plugins/images/large/8.jpg');
INSERT INTO User VALUES (9,'Nguyễn Mai','Phương','nmp','nmp@gmail.com',4,'nmpjava123','plugins/images/users/9.jpeg','plugins/images/large/9.jpeg');
INSERT INTO User VALUES (10,'Lyly Tuyết ','Hoa','llth','llth@gmail.com',3,'llthjava123','plugins/images/users/10.jpg','plugins/images/large/10.jpg');
INSERT INTO User VALUES (11,'Ông Quỳnh ','Hoa','oqh','oqh@gmail.com',4,'oqhjava123','plugins/images/users/11.jpg','plugins/images/large/11.jpg');
INSERT INTO User VALUES (12,'Trần Khánh','Linh','tkl','tkl@gmail.com',2,'tkljava123','plugins/images/users/12.jpg','plugins/images/large/12.jpg');
INSERT INTO User VALUES (13,'Moore','Joel ','joeyjoey','joeyjoey@gmail.com',3,'joeyjoeyjava123','plugins/images/users/13.png','plugins/images/large/13.png');
INSERT INTO User VALUES (14,'Matthews','Ewan ','ewanmatt','ewanmatt@gmail.com',3,'ewanmattjava123','plugins/images/users/14.jpg','plugins/images/large/14.jpg');
INSERT INTO User VALUES (15,'Srisati','Preeya ','preesri','preesri@gmail.com',4,'preesrijava123','plugins/images/users/15.jpg','plugins/images/large/15.jpg');
INSERT INTO User VALUES (16,'Griffin','Stewart','stewgrf','stewgrf@gmail.com',3,'stewgrfjava123','plugins/images/users/16.png','plugins/images/large/16.jpg');
INSERT INTO User VALUES (17,'Wilson','Jack','jwilson','jwilson@gmail.com',3,'jwilsonjava123','plugins/images/users/17.jpg','plugins/images/large/17.jpg');
INSERT INTO User VALUES (18,'Sadakuno','Mitsui ','mitsuisan','mitsuisan@gmail.com',2,'mitsuisanjava123','plugins/images/users/18.jpg','plugins/images/large/18.jpg');
INSERT INTO User VALUES (19,'Jie','Yang ','yangjie','yangjie@gmail.com',3,'yangjiejava123','plugins/images/users/19.jpg','plugins/images/large/19.jpg');
INSERT INTO User VALUES (20,'Jung-Ho','Eoh ','junghoeoh','junghoeoh@gmail.com',4,'junghoeohjava123','plugins/images/users/20.jpg','plugins/images/large/20.jpg');
-- Role
INSERT INTO Role VALUES (1,'Admin','System Administration');
INSERT INTO Role VALUES (2,'Developer','System Maintenance');
INSERT INTO Role VALUES (3,'Member','Task Execution');
INSERT INTO Role VALUES (4,'Supporter','Task Support');
-- Project
INSERT INTO Project VALUES (1,'Foody Application','2022-11-11 00:00:00','2022-11-12 00:00:00',2);
INSERT INTO Project VALUES (2,'Shopee Sales Analysis','2022-11-25 00:00:00','2022-12-16 00:00:00',6);
INSERT INTO Project VALUES (3,'Speech Recognition','2023-01-01 00:00:00','2023-02-08 00:00:00',10);
INSERT INTO Project VALUES (4,'Financial Modelling','2022-10-28 00:00:00','2022-11-04 00:00:00',13);
INSERT INTO Project VALUES (5,'Virtual Assistant ','2022-12-07 00:00:00','2022-12-21 00:00:00',17);
-- Status
INSERT INTO Status VALUES (1,'Not Started');
INSERT INTO Status VALUES (2,'In Progress');
INSERT INTO Status VALUES (3,'Completed');
-- Task
INSERT INTO Task VALUES (1,1,18,'Database Design','2022-11-11 00:00:00','2022-11-15 00:00:00',1);
INSERT INTO Task VALUES (2,1,2,'Registration and Identification','2022-11-16 00:00:00','2022-11-16 00:00:00',2);
INSERT INTO Task VALUES (3,1,4,'Menus and Carts','2022-11-16 00:00:00','2022-11-23 00:00:00',1);
INSERT INTO Task VALUES (4,1,4,'Payment','2022-11-16 00:00:00','2022-11-23 00:00:00',1);
INSERT INTO Task VALUES (5,1,2,'Shipping','2022-11-23 00:00:00','2022-11-30 00:00:00',1);
INSERT INTO Task VALUES (6,2,20,'Data Research','2022-11-25 00:00:00','2022-11-30 00:00:00',1);
INSERT INTO Task VALUES (7,2,7,'Algorithms Design and Analysis','2022-11-30 00:00:00','2022-12-07 00:00:00',1);
INSERT INTO Task VALUES (8,2,14,'Algorithms Design and Analysis','2022-11-30 00:00:00','2022-12-07 00:00:00',3);
INSERT INTO Task VALUES (9,2,12,'Model Design and Optimization','2022-12-08 00:00:00','2022-12-10 00:00:00',1);
INSERT INTO Task VALUES (10,2,13,'Reports','2022-12-11 00:00:00','2022-12-13 00:00:00',1);
INSERT INTO Task VALUES (11,3,18,'Data Research','2023-01-01 00:00:00','2023-01-05 00:00:00',1);
INSERT INTO Task VALUES (12,3,7,'Algorithms Design and Analysis','2023-01-06 00:00:00','2023-01-13 00:00:00',2);
INSERT INTO Task VALUES (13,3,18,'Algorithms Design and Analysis','2023-01-06 00:00:00','2023-01-13 00:00:00',3);
INSERT INTO Task VALUES (14,3,16,'Model Research','2023-01-14 00:00:00','2023-01-16 00:00:00',1);
INSERT INTO Task VALUES (15,3,9,'Model Design and Optimization','2023-01-17 00:00:00','2023-02-01 00:00:00',2);
INSERT INTO Task VALUES (16,3,3,'Model Design and Optimization','2023-01-17 00:00:00','2023-02-01 00:00:00',1);
INSERT INTO Task VALUES (17,3,8,'Reports','2023-02-02 00:00:00','2023-02-05 00:00:00',2);
INSERT INTO Task VALUES (18,3,13,'Reports','2023-02-02 00:00:00','2023-02-05 00:00:00',1);
INSERT INTO Task VALUES (19,4,12,'Data Research','2022-10-28 00:00:00','2022-10-30 00:00:00',1);
INSERT INTO Task VALUES (20,4,5,'Model Design and Optimization','2022-10-31 00:00:00','2022-11-02 00:00:00',2);
INSERT INTO Task VALUES (21,4,18,'Reports','2022-11-02 00:00:00','2022-11-03 00:00:00',2);
INSERT INTO Task VALUES (22,5,9,'Database Design','2022-12-07 00:00:00','2022-12-10 00:00:00',2);
INSERT INTO Task VALUES (23,5,18,'Database Design','2022-12-07 00:00:00','2022-12-10 00:00:00',1);
INSERT INTO Task VALUES (24,5,12,'Database Design','2022-12-07 00:00:00','2022-12-10 00:00:00',3);
INSERT INTO Task VALUES (25,5,16,'Algorithms Design and Analysis','2022-12-11 00:00:00','2022-12-15 00:00:00',3);
INSERT INTO Task VALUES (26,5,14,'Algorithms Design and Analysis','2022-12-11 00:00:00','2022-12-15 00:00:00',1);
INSERT INTO Task VALUES (27,5,19,'Algorithms Design and Analysis','2022-12-11 00:00:00','2022-12-15 00:00:00',2);
INSERT INTO Task VALUES (28,5,17,'Model Design and Optimization','2022-12-16 00:00:00','2022-12-16 00:00:00',3);
INSERT INTO Task VALUES (29,5,16,'Model Design and Optimization','2022-12-16 00:00:00','2022-12-16 00:00:00',3);
INSERT INTO Task VALUES (30,5,10,'Deployment','2022-12-17 00:00:00','2022-12-18 00:00:00',1);


-- Constraint
ALTER TABLE User ADD constraint foreign key fk_role (roleID) REFERENCES Role(roleID) ON DELETE CASCADE;
ALTER TABLE Project ADD constraint foreign key fk_lead (leader) REFERENCES User(userID) ON DELETE CASCADE;
ALTER TABLE Task ADD CONSTRAINT FOREIGN KEY fk1_tsk (projectID) REFERENCES Project(projectID) ON DELETE CASCADE;
ALTER TABLE Task ADD CONSTRAINT FOREIGN KEY fk2_tsk (member) REFERENCES User(userID) ON DELETE CASCADE;
ALTER TABLE Task ADD CONSTRAINT FOREIGN KEY fk_st (status) REFERENCES Status (statusID) ON DELETE CASCADE;

