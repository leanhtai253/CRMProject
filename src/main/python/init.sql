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
    phone varchar(10),
    country varchar(20),
    
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
-- All countries
CREATE TABLE IF NOT EXISTS `countries` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `phone` int(5) NOT NULL,
    `code` char(2) NOT NULL,
    `name` varchar(80) NOT NULL,
    PRIMARY KEY (`id`)
);


-- User
INSERT INTO User VALUES (1,'Nông Hải','Dương','nhd','nhd@gmail.com',1,'nhdjava123','plugins/images/users/1.png','plugins/images/large/1.png',275507218,'Viet Nam');
INSERT INTO User VALUES (2,'Lê Việt','Khải','lvk','lvk@gmail.com',3,'lvkjava123','plugins/images/users/2.jpg','plugins/images/large/2.jpg',343122929,'Viet Nam');
INSERT INTO User VALUES (3,'Mẫn Hoàng','Khôi','mhk','mhk@gmail.com',4,'mhkjava123','plugins/images/users/3.jpg','plugins/images/large/3.jpg',572379478,'Viet Nam');
INSERT INTO User VALUES (4,'Trần Xuân','Thiện','txt','txt@gmail.com',3,'txtjava123','plugins/images/users/4.jpg','plugins/images/large/4.jpg',15767682,'Viet Nam');
INSERT INTO User VALUES (5,'Vũ Hữu','Chiến','vhc','vhc@gmail.com',2,'vhcjava123','plugins/images/users/5.jpg','plugins/images/large/5.jpg',199492379,'Viet Nam');
INSERT INTO User VALUES (6,'Phan Thành','Nguyên','ptn','ptn@gmail.com',3,'ptnjava123','plugins/images/users/6.jpg','plugins/images/large/6.jpg',602004437,'Viet Nam');
INSERT INTO User VALUES (7,'Nguyễn Ðình','Dương','ndd','ndd@gmail.com',4,'nddjava123','plugins/images/users/7.jpg','plugins/images/large/7.jpg',737453993,'Viet Nam');
INSERT INTO User VALUES (8,'Lê Diệu','Linh','ldl','ldl@gmail.com',3,'ldljava123','plugins/images/users/8.jpg','plugins/images/large/8.jpg',19863805,'Viet Nam');
INSERT INTO User VALUES (9,'Nguyễn Mai','Phương','nmp','nmp@gmail.com',4,'nmpjava123','plugins/images/users/9.jpeg','plugins/images/large/9.jpeg',657259016,'Viet Nam');
INSERT INTO User VALUES (10,'Lyly Tuyết','Hoa','llth','llth@gmail.com',3,'llthjava123','plugins/images/users/10.jpg','plugins/images/large/10.jpg',982244494,'Viet Nam');
INSERT INTO User VALUES (11,'Ông Quỳnh','Hoa','oqh','oqh@gmail.com',4,'oqhjava123','plugins/images/users/11.jpg','plugins/images/large/11.jpg',327734575,'Viet Nam');
INSERT INTO User VALUES (12,'Trần Khánh','Linh','tkl','tkl@gmail.com',2,'tkljava123','plugins/images/users/12.jpg','plugins/images/large/12.jpg',671668895,'Viet Nam');
INSERT INTO User VALUES (13,'Moore','Joel','joeyjoey','joeyjoey@gmail.com',3,'joeyjoeyjava123','plugins/images/users/13.png','plugins/images/large/13.png',183579336,'United Kingdom');
INSERT INTO User VALUES (14,'Matthews','Ewan','ewanmatt','ewanmatt@gmail.com',3,'ewanmattjava123','plugins/images/users/14.jpg','plugins/images/large/14.jpg',476952995,'United States');
INSERT INTO User VALUES (15,'Srisati','Preeya','preesri','preesri@gmail.com',4,'preesrijava123','plugins/images/users/15.jpg','plugins/images/large/15.jpg',378686948,'India');
INSERT INTO User VALUES (16,'Griffin','Stewart','stewgrf','stewgrf@gmail.com',3,'stewgrfjava123','plugins/images/users/16.png','plugins/images/large/16.jpg',484125617,'United States');
INSERT INTO User VALUES (17,'Wilson','Jack','jwilson','jwilson@gmail.com',3,'jwilsonjava123','plugins/images/users/17.jpg','plugins/images/large/17.jpg',974273476,'Finland');
INSERT INTO User VALUES (18,'Sadakuno','Mitsui','mitsuisan','mitsuisan@gmail.com',2,'mitsuisanjava123','plugins/images/users/18.jpg','plugins/images/large/18.jpg',555770877,'Japan');
INSERT INTO User VALUES (19,'Jie','Yang','yangjie','yangjie@gmail.com',3,'yangjiejava123','plugins/images/users/19.jpg','plugins/images/large/19.jpg',80333385,'Hong Kong');
INSERT INTO User VALUES (20,'Jung-Ho','Eoh','junghoeoh','junghoeoh@gmail.com',4,'junghoeohjava123','plugins/images/users/20.jpg','plugins/images/large/20.jpg',708808893,'Korea');
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

-- Countries
INSERT INTO `countries` (`id`, `phone`, `code`, `name`) VALUES
(1, 93, 'AF', 'Afghanistan'),
(2, 358, 'AX', 'Aland Islands'),
(3, 355, 'AL', 'Albania'),
(4, 213, 'DZ', 'Algeria'),
(5, 1684, 'AS', 'American Samoa'),
(6, 376, 'AD', 'Andorra'),
(7, 244, 'AO', 'Angola'),
(8, 1264, 'AI', 'Anguilla'),
(9, 672, 'AQ', 'Antarctica'),
(10, 1268, 'AG', 'Antigua and Barbuda'),
(11, 54, 'AR', 'Argentina'),
(12, 374, 'AM', 'Armenia'),
(13, 297, 'AW', 'Aruba'),
(14, 61, 'AU', 'Australia'),
(15, 43, 'AT', 'Austria'),
(16, 994, 'AZ', 'Azerbaijan'),
(17, 1242, 'BS', 'Bahamas'),
(18, 973, 'BH', 'Bahrain'),
(19, 880, 'BD', 'Bangladesh'),
(20, 1246, 'BB', 'Barbados'),
(21, 375, 'BY', 'Belarus'),
(22, 32, 'BE', 'Belgium'),
(23, 501, 'BZ', 'Belize'),
(24, 229, 'BJ', 'Benin'),
(25, 1441, 'BM', 'Bermuda'),
(26, 975, 'BT', 'Bhutan'),
(27, 591, 'BO', 'Bolivia'),
(28, 599, 'BQ', 'Bonaire, Sint Eustatius and Saba'),
(29, 387, 'BA', 'Bosnia and Herzegovina'),
(30, 267, 'BW', 'Botswana'),
(31, 55, 'BV', 'Bouvet Island'),
(32, 55, 'BR', 'Brazil'),
(33, 246, 'IO', 'British Indian Ocean Territory'),
(34, 673, 'BN', 'Brunei Darussalam'),
(35, 359, 'BG', 'Bulgaria'),
(36, 226, 'BF', 'Burkina Faso'),
(37, 257, 'BI', 'Burundi'),
(38, 855, 'KH', 'Cambodia'),
(39, 237, 'CM', 'Cameroon'),
(40, 1, 'CA', 'Canada'),
(41, 238, 'CV', 'Cape Verde'),
(42, 1345, 'KY', 'Cayman Islands'),
(43, 236, 'CF', 'Central African Republic'),
(44, 235, 'TD', 'Chad'),
(45, 56, 'CL', 'Chile'),
(46, 86, 'CN', 'China'),
(47, 61, 'CX', 'Christmas Island'),
(48, 672, 'CC', 'Cocos (Keeling) Islands'),
(49, 57, 'CO', 'Colombia'),
(50, 269, 'KM', 'Comoros'),
(51, 242, 'CG', 'Congo'),
(52, 242, 'CD', 'Congo, Democratic Republic of the Congo'),
(53, 682, 'CK', 'Cook Islands'),
(54, 506, 'CR', 'Costa Rica'),
(55, 225, 'CI', 'Cote D\'Ivoire'),
(56, 385, 'HR', 'Croatia'),
(57, 53, 'CU', 'Cuba'),
(58, 599, 'CW', 'Curacao'),
(59, 357, 'CY', 'Cyprus'),
(60, 420, 'CZ', 'Czech Republic'),
(61, 45, 'DK', 'Denmark'),
(62, 253, 'DJ', 'Djibouti'),
(63, 1767, 'DM', 'Dominica'),
(64, 1809, 'DO', 'Dominican Republic'),
(65, 593, 'EC', 'Ecuador'),
(66, 20, 'EG', 'Egypt'),
(67, 503, 'SV', 'El Salvador'),
(68, 240, 'GQ', 'Equatorial Guinea'),
(69, 291, 'ER', 'Eritrea'),
(70, 372, 'EE', 'Estonia'),
(71, 251, 'ET', 'Ethiopia'),
(72, 500, 'FK', 'Falkland Islands (Malvinas)'),
(73, 298, 'FO', 'Faroe Islands'),
(74, 679, 'FJ', 'Fiji'),
(75, 358, 'FI', 'Finland'),
(76, 33, 'FR', 'France'),
(77, 594, 'GF', 'French Guiana'),
(78, 689, 'PF', 'French Polynesia'),
(79, 262, 'TF', 'French Southern Territories'),
(80, 241, 'GA', 'Gabon'),
(81, 220, 'GM', 'Gambia'),
(82, 995, 'GE', 'Georgia'),
(83, 49, 'DE', 'Germany'),
(84, 233, 'GH', 'Ghana'),
(85, 350, 'GI', 'Gibraltar'),
(86, 30, 'GR', 'Greece'),
(87, 299, 'GL', 'Greenland'),
(88, 1473, 'GD', 'Grenada'),
(89, 590, 'GP', 'Guadeloupe'),
(90, 1671, 'GU', 'Guam'),
(91, 502, 'GT', 'Guatemala'),
(92, 44, 'GG', 'Guernsey'),
(93, 224, 'GN', 'Guinea'),
(94, 245, 'GW', 'Guinea-Bissau'),
(95, 592, 'GY', 'Guyana'),
(96, 509, 'HT', 'Haiti'),
(97, 0, 'HM', 'Heard Island and Mcdonald Islands'),
(98, 39, 'VA', 'Holy See (Vatican City State)'),
(99, 504, 'HN', 'Honduras'),
(100, 852, 'HK', 'Hong Kong'),
(101, 36, 'HU', 'Hungary'),
(102, 354, 'IS', 'Iceland'),
(103, 91, 'IN', 'India'),
(104, 62, 'ID', 'Indonesia'),
(105, 98, 'IR', 'Iran, Islamic Republic of'),
(106, 964, 'IQ', 'Iraq'),
(107, 353, 'IE', 'Ireland'),
(108, 44, 'IM', 'Isle of Man'),
(109, 972, 'IL', 'Israel'),
(110, 39, 'IT', 'Italy'),
(111, 1876, 'JM', 'Jamaica'),
(112, 81, 'JP', 'Japan'),
(113, 44, 'JE', 'Jersey'),
(114, 962, 'JO', 'Jordan'),
(115, 7, 'KZ', 'Kazakhstan'),
(116, 254, 'KE', 'Kenya'),
(117, 686, 'KI', 'Kiribati'),
(118, 850, 'KP', 'Korea, Democratic People\'s Republic of'),
(119, 82, 'KR', 'Korea, Republic of'),
(120, 381, 'XK', 'Kosovo'),
(121, 965, 'KW', 'Kuwait'),
(122, 996, 'KG', 'Kyrgyzstan'),
(123, 856, 'LA', 'Lao People\'s Democratic Republic'),
(124, 371, 'LV', 'Latvia'),
(125, 961, 'LB', 'Lebanon'),
(126, 266, 'LS', 'Lesotho'),
(127, 231, 'LR', 'Liberia'),
(128, 218, 'LY', 'Libyan Arab Jamahiriya'),
(129, 423, 'LI', 'Liechtenstein'),
(130, 370, 'LT', 'Lithuania'),
(131, 352, 'LU', 'Luxembourg'),
(132, 853, 'MO', 'Macao'),
(133, 389, 'MK', 'Macedonia, the Former Yugoslav Republic of'),
(134, 261, 'MG', 'Madagascar'),
(135, 265, 'MW', 'Malawi'),
(136, 60, 'MY', 'Malaysia'),
(137, 960, 'MV', 'Maldives'),
(138, 223, 'ML', 'Mali'),
(139, 356, 'MT', 'Malta'),
(140, 692, 'MH', 'Marshall Islands'),
(141, 596, 'MQ', 'Martinique'),
(142, 222, 'MR', 'Mauritania'),
(143, 230, 'MU', 'Mauritius'),
(144, 262, 'YT', 'Mayotte'),
(145, 52, 'MX', 'Mexico'),
(146, 691, 'FM', 'Micronesia, Federated States of'),
(147, 373, 'MD', 'Moldova, Republic of'),
(148, 377, 'MC', 'Monaco'),
(149, 976, 'MN', 'Mongolia'),
(150, 382, 'ME', 'Montenegro'),
(151, 1664, 'MS', 'Montserrat'),
(152, 212, 'MA', 'Morocco'),
(153, 258, 'MZ', 'Mozambique'),
(154, 95, 'MM', 'Myanmar'),
(155, 264, 'NA', 'Namibia'),
(156, 674, 'NR', 'Nauru'),
(157, 977, 'NP', 'Nepal'),
(158, 31, 'NL', 'Netherlands'),
(159, 599, 'AN', 'Netherlands Antilles'),
(160, 687, 'NC', 'New Caledonia'),
(161, 64, 'NZ', 'New Zealand'),
(162, 505, 'NI', 'Nicaragua'),
(163, 227, 'NE', 'Niger'),
(164, 234, 'NG', 'Nigeria'),
(165, 683, 'NU', 'Niue'),
(166, 672, 'NF', 'Norfolk Island'),
(167, 1670, 'MP', 'Northern Mariana Islands'),
(168, 47, 'NO', 'Norway'),
(169, 968, 'OM', 'Oman'),
(170, 92, 'PK', 'Pakistan'),
(171, 680, 'PW', 'Palau'),
(172, 970, 'PS', 'Palestinian Territory, Occupied'),
(173, 507, 'PA', 'Panama'),
(174, 675, 'PG', 'Papua New Guinea'),
(175, 595, 'PY', 'Paraguay'),
(176, 51, 'PE', 'Peru'),
(177, 63, 'PH', 'Philippines'),
(178, 64, 'PN', 'Pitcairn'),
(179, 48, 'PL', 'Poland'),
(180, 351, 'PT', 'Portugal'),
(181, 1787, 'PR', 'Puerto Rico'),
(182, 974, 'QA', 'Qatar'),
(183, 262, 'RE', 'Reunion'),
(184, 40, 'RO', 'Romania'),
(185, 70, 'RU', 'Russian Federation'),
(186, 250, 'RW', 'Rwanda'),
(187, 590, 'BL', 'Saint Barthelemy'),
(188, 290, 'SH', 'Saint Helena'),
(189, 1869, 'KN', 'Saint Kitts and Nevis'),
(190, 1758, 'LC', 'Saint Lucia'),
(191, 590, 'MF', 'Saint Martin'),
(192, 508, 'PM', 'Saint Pierre and Miquelon'),
(193, 1784, 'VC', 'Saint Vincent and the Grenadines'),
(194, 684, 'WS', 'Samoa'),
(195, 378, 'SM', 'San Marino'),
(196, 239, 'ST', 'Sao Tome and Principe'),
(197, 966, 'SA', 'Saudi Arabia'),
(198, 221, 'SN', 'Senegal'),
(199, 381, 'RS', 'Serbia'),
(200, 381, 'CS', 'Serbia and Montenegro'),
(201, 248, 'SC', 'Seychelles'),
(202, 232, 'SL', 'Sierra Leone'),
(203, 65, 'SG', 'Singapore'),
(204, 1, 'SX', 'Sint Maarten'),
(205, 421, 'SK', 'Slovakia'),
(206, 386, 'SI', 'Slovenia'),
(207, 677, 'SB', 'Solomon Islands'),
(208, 252, 'SO', 'Somalia'),
(209, 27, 'ZA', 'South Africa'),
(210, 500, 'GS', 'South Georgia and the South Sandwich Islands'),
(211, 211, 'SS', 'South Sudan'),
(212, 34, 'ES', 'Spain'),
(213, 94, 'LK', 'Sri Lanka'),
(214, 249, 'SD', 'Sudan'),
(215, 597, 'SR', 'Suriname'),
(216, 47, 'SJ', 'Svalbard and Jan Mayen'),
(217, 268, 'SZ', 'Swaziland'),
(218, 46, 'SE', 'Sweden'),
(219, 41, 'CH', 'Switzerland'),
(220, 963, 'SY', 'Syrian Arab Republic'),
(221, 886, 'TW', 'Taiwan, Province of China'),
(222, 992, 'TJ', 'Tajikistan'),
(223, 255, 'TZ', 'Tanzania, United Republic of'),
(224, 66, 'TH', 'Thailand'),
(225, 670, 'TL', 'Timor-Leste'),
(226, 228, 'TG', 'Togo'),
(227, 690, 'TK', 'Tokelau'),
(228, 676, 'TO', 'Tonga'),
(229, 1868, 'TT', 'Trinidad and Tobago'),
(230, 216, 'TN', 'Tunisia'),
(231, 90, 'TR', 'Turkey'),
(232, 7370, 'TM', 'Turkmenistan'),
(233, 1649, 'TC', 'Turks and Caicos Islands'),
(234, 688, 'TV', 'Tuvalu'),
(235, 256, 'UG', 'Uganda'),
(236, 380, 'UA', 'Ukraine'),
(237, 971, 'AE', 'United Arab Emirates'),
(238, 44, 'GB', 'United Kingdom'),
(239, 1, 'US', 'United States'),
(240, 1, 'UM', 'United States Minor Outlying Islands'),
(241, 598, 'UY', 'Uruguay'),
(242, 998, 'UZ', 'Uzbekistan'),
(243, 678, 'VU', 'Vanuatu'),
(244, 58, 'VE', 'Venezuela'),
(245, 84, 'VN', 'Viet Nam'),
(246, 1284, 'VG', 'Virgin Islands, British'),
(247, 1340, 'VI', 'Virgin Islands, U.s.'),
(248, 681, 'WF', 'Wallis and Futuna'),
(249, 212, 'EH', 'Western Sahara'),
(250, 967, 'YE', 'Yemen'),
(251, 260, 'ZM', 'Zambia'),
(252, 263, 'ZW', 'Zimbabwe');