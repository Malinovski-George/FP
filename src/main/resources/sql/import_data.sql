INSERT INTO user (EMAIL, PASSWORD, FIRSTNAME, LASTNAME, POSITION, PHONE, ROLE_ID) VALUES ('user1_mogilev@yopmail.com', 'P@ssword1', 'Boris', 'Chaykovski', '1', '951035684125', 0);
INSERT INTO user (EMAIL, PASSWORD, FIRSTNAME, LASTNAME, POSITION, PHONE, ROLE_ID) VALUES ('user2_mogilev@yopmail.com', 'P@ssword1', 'Cristofer', 'Backwords', '1', '951035681249', 0);
INSERT INTO user (EMAIL, PASSWORD, FIRSTNAME, LASTNAME, POSITION, PHONE, ROLE_ID) VALUES ('manager1_mogilev@yopmail.com', 'P@ssword1', 'Inokenty', 'Filosofov', '2', '951049116548', 1);
INSERT INTO user (EMAIL, PASSWORD, FIRSTNAME, LASTNAME, POSITION, PHONE, ROLE_ID) VALUES ('manager2_mogilev@yopmail.com', 'P@ssword1', 'Jack', 'Handface', '2', '951065198454', 1);
INSERT INTO user (EMAIL, PASSWORD, FIRSTNAME, LASTNAME, POSITION, PHONE, ROLE_ID) VALUES ('engineer1_mogilev@yopmail.com', 'P@ssword1', 'Vasili', 'Black', '3', '951065849816', 2);
INSERT INTO user (EMAIL, PASSWORD, FIRSTNAME, LASTNAME, POSITION, PHONE, ROLE_ID) VALUES ('engineer2_mogilev@yopmail.com', 'P@ssword1', 'Stefan', 'Admiraltovich', '3', '951065498416', 2);

INSERT INTO address (STREET, CITY, STATE) VALUES ('23', 'NY', 'qwe');
INSERT INTO address (STREET, CITY, STATE) VALUES ('43', 'NY', 'qwe');
INSERT INTO address (STREET, CITY, STATE) VALUES ('11', 'NY', 'qwe');
INSERT INTO address (STREET, CITY, STATE) VALUES ('156', 'NY', 'qweq');
INSERT INTO address (STREET, CITY, STATE) VALUES ('123', 'NY', 'qweq');
INSERT INTO address (STREET, CITY, STATE) VALUES ('24', 'NY', 'qwe');

INSERT INTO category (NAME) VALUES ('Application & Services');
INSERT INTO category (NAME) VALUES ('Benefits & Paper Work');
INSERT INTO category (NAME) VALUES ('Hardware & Software');
INSERT INTO category (NAME) VALUES ('People Management');
INSERT INTO category (NAME) VALUES ('Security & Access');
INSERT INTO category (NAME) VALUES ('Workplaces & Facilities');

INSERT INTO ticket (NAME, DISCRIPTION, CREATED_ON, DESIRED_RESOLUTION_DATE, ASSIGNEE, OWNER, STATE, CATEGORY, URGENCY, APPROVER) VALUES ('Monitor', 'My second monitor is broken.', '2017-12-12 11:21:09', null, null, 1, 2, 3, 1, 3);
INSERT INTO ticket (NAME, DISCRIPTION, CREATED_ON, DESIRED_RESOLUTION_DATE, ASSIGNEE, OWNER, STATE, CATEGORY, URGENCY, APPROVER) VALUES ('The Word license is expires', null, '2017-12-12 11:24:18', null, 5, 1, 5, 1, 2, 4);
INSERT INTO ticket (NAME, DISCRIPTION, CREATED_ON, DESIRED_RESOLUTION_DATE, ASSIGNEE, OWNER, STATE, CATEGORY, URGENCY, APPROVER) VALUES ('New sofa', null, '2017-12-12 11:24:18', '2017-12-31 00:00:00', null, 1, 0, 6, 3, null);
INSERT INTO ticket (NAME, DISCRIPTION, CREATED_ON, DESIRED_RESOLUTION_DATE, ASSIGNEE, OWNER, STATE, CATEGORY, URGENCY, APPROVER) VALUES ('third monitor', null, '2017-12-12 11:24:18', '2017-12-31 00:00:00', null, 1, 0, 6, 3, null);
INSERT INTO ticket (NAME, DISCRIPTION, CREATED_ON, DESIRED_RESOLUTION_DATE, ASSIGNEE, OWNER, STATE, CATEGORY, URGENCY, APPROVER) VALUES ('broken shredder', 'I urgently need to destroy the project documentation !!', '2017-12-12 11:31:46', '2017-12-21 00:00:00', null, 1, 6, 2, 0, 3);
INSERT INTO ticket (NAME, DISCRIPTION, CREATED_ON, DESIRED_RESOLUTION_DATE, ASSIGNEE, OWNER, STATE, CATEGORY, URGENCY, APPROVER) VALUES ('broken internet cable', 'the chip broke off', '2017-12-12 11:34:28', '2017-12-12 00:00:00', 6, 1, 5, 3, 0, 3);
INSERT INTO ticket (NAME, DISCRIPTION, CREATED_ON, DESIRED_RESOLUTION_DATE, ASSIGNEE, OWNER, STATE, CATEGORY, URGENCY, APPROVER) VALUES ('Lost password', 'I need to restore access to the server', '2017-12-12 11:35:45', '2017-12-15 00:00:00', null, 1, 1, 5, 1, null);
INSERT INTO ticket (NAME, DISCRIPTION, CREATED_ON, DESIRED_RESOLUTION_DATE, ASSIGNEE, OWNER, STATE, CATEGORY, URGENCY, APPROVER) VALUES ('stationery', 'I run out of stationery. I have the last pen', '2017-12-12 11:37:43', '2017-12-27 00:00:00', null, 1, 1, 2, 1, null);
INSERT INTO ticket (NAME, DISCRIPTION, CREATED_ON, DESIRED_RESOLUTION_DATE, ASSIGNEE, OWNER, STATE, CATEGORY, URGENCY, APPROVER) VALUES ('Second monitor', 'to improve the quality of work I need a second monitor', '2017-12-12 11:43:42', null, null, 2, 1, 3, 2, null);
INSERT INTO ticket (NAME, DISCRIPTION, CREATED_ON, DESIRED_RESOLUTION_DATE, ASSIGNEE, OWNER, STATE, CATEGORY, URGENCY, APPROVER) VALUES ('keyboard', 'On the keyboard, all the inscriptions have been erased, I can not print', '2017-12-12 11:45:03', '2018-01-31 00:00:00', 6, 2, 5, 1, 1, 4);
INSERT INTO ticket (NAME, DISCRIPTION, CREATED_ON, DESIRED_RESOLUTION_DATE, ASSIGNEE, OWNER, STATE, CATEGORY, URGENCY, APPROVER) VALUES ('office access key', 'Today my access card has not worked again, please understand this', '2017-12-12 11:46:23', null, null, 2, 1, 5, 1, null);
INSERT INTO ticket (NAME, DISCRIPTION, CREATED_ON, DESIRED_RESOLUTION_DATE, ASSIGNEE, OWNER, STATE, CATEGORY, URGENCY, APPROVER) VALUES ('Teapot', 'i need teapot', '2017-12-12 11:47:55', null, null, 2, 0, 4, 3, null);
INSERT INTO ticket (NAME, DISCRIPTION, CREATED_ON, DESIRED_RESOLUTION_DATE, ASSIGNEE, OWNER, STATE, CATEGORY, URGENCY, APPROVER) VALUES ('employees', 'I need a tablet for remote work in non-working hours', '2017-12-12 11:50:42', null, 5, 3, 4, 4, 2, 4);

INSERT INTO comment (TEXT, DATE, USER_ID, TICKET_ID) VALUES ('Please repair or buy new one. The new one is better)', '2017-12-12 11:24:18', 1, 1);
INSERT INTO comment (TEXT, DATE, USER_ID, TICKET_ID) VALUES ('Please execute first', '2017-12-12 11:24:18', 1, 5);
INSERT INTO comment (TEXT, DATE, USER_ID, TICKET_ID) VALUES ('I lost the password, please send a new one', '2017-12-12 11:24:18', 1, 7);
INSERT INTO comment (TEXT, DATE, USER_ID, TICKET_ID) VALUES ('I''m the only one who has one monitor', '2017-12-12 11:24:18', 2, 10);
INSERT INTO comment (TEXT, DATE, USER_ID, TICKET_ID) VALUES ('Please look at another keyboard in stock or buy a new one', '2017-12-12 11:24:18', 2, 11);
INSERT INTO comment (TEXT, DATE, USER_ID, TICKET_ID) VALUES ('I''m afraid soon I will not be able to get to the office at all!', '2017-12-12 11:24:18', 2, 12);
INSERT INTO comment (TEXT, DATE, USER_ID, TICKET_ID) VALUES ('jrOK. I will look for or make a purchase request', '2017-12-12 11:57:34', 5, 14);
INSERT INTO comment (TEXT, DATE, USER_ID, TICKET_ID) VALUES ('i''ll take it', '2017-12-12 11:24:18', 6, 6);
INSERT INTO comment (TEXT, DATE, USER_ID, TICKET_ID) VALUES ('I hope this was a joke', '2017-12-12 11:24:18', 3, 5);

INSERT INTO feedback (USER_ID, TICKET_ID, DATE, RATE, TEXT) VALUES (1, 2, '2017-12-12 11:59:33', 5, 'very fast');

INSERT INTO history (ACTION, USER_ID, TICKET_ID, DATE, DESCRIPTION) VALUES (0, 1, 1, '2017-12-12 11:21:09', 'Ticket is created');
INSERT INTO history (ACTION, USER_ID, TICKET_ID, DATE, DESCRIPTION) VALUES (0, 1, 2, '2017-12-12 11:24:18', 'Ticket is created');
INSERT INTO history (ACTION, USER_ID, TICKET_ID, DATE, DESCRIPTION) VALUES (0, 1, 3, '2017-12-12 11:24:18', 'Ticket is created');
INSERT INTO history (ACTION, USER_ID, TICKET_ID, DATE, DESCRIPTION) VALUES (0, 1, 4, '2017-12-12 11:24:18', 'Ticket is created');
INSERT INTO history (ACTION, USER_ID, TICKET_ID, DATE, DESCRIPTION) VALUES (0, 1, 5, '2017-12-12 11:31:46', 'Ticket is created');
INSERT INTO history (ACTION, USER_ID, TICKET_ID, DATE, DESCRIPTION) VALUES (0, 1, 6, '2017-12-12 11:34:28', 'Ticket is created');
INSERT INTO history (ACTION, USER_ID, TICKET_ID, DATE, DESCRIPTION) VALUES (0, 1, 7, '2017-12-12 11:35:45', 'Ticket is created');
INSERT INTO history (ACTION, USER_ID, TICKET_ID, DATE, DESCRIPTION) VALUES (0, 1, 8, '2017-12-12 11:37:43', 'Ticket is created');
INSERT INTO history (ACTION, USER_ID, TICKET_ID, DATE, DESCRIPTION) VALUES (0, 2, 10, '2017-12-12 11:43:42', 'Ticket is created');
INSERT INTO history (ACTION, USER_ID, TICKET_ID, DATE, DESCRIPTION) VALUES (0, 2, 11, '2017-12-12 11:45:03', 'Ticket is created');
INSERT INTO history (ACTION, USER_ID, TICKET_ID, DATE, DESCRIPTION) VALUES (0, 2, 12, '2017-12-12 11:46:23', 'Ticket is created');
INSERT INTO history (ACTION, USER_ID, TICKET_ID, DATE, DESCRIPTION) VALUES (0, 2, 13, '2017-12-12 11:47:55', 'Ticket is created');
INSERT INTO history (ACTION, USER_ID, TICKET_ID, DATE, DESCRIPTION) VALUES (1, 3, 1, '2017-12-12 11:49:33', 'Ticket Status is changed from [New] to [Approved]');
INSERT INTO history (ACTION, USER_ID, TICKET_ID, DATE, DESCRIPTION) VALUES (1, 3, 6, '2017-12-12 11:49:58', 'Ticket Status is changed from [New] to [Approved]');
INSERT INTO history (ACTION, USER_ID, TICKET_ID, DATE, DESCRIPTION) VALUES (1, 3, 5, '2017-12-12 11:50:12', 'Ticket Status is changed from [New] to [Cancelled]');
INSERT INTO history (ACTION, USER_ID, TICKET_ID, DATE, DESCRIPTION) VALUES (0, 3, 14, '2017-12-12 11:50:42', 'Ticket is created');
INSERT INTO history (ACTION, USER_ID, TICKET_ID, DATE, DESCRIPTION) VALUES (1, 4, 14, '2017-12-12 11:53:09', 'Ticket Status is changed from [New] to [Approved]');
INSERT INTO history (ACTION, USER_ID, TICKET_ID, DATE, DESCRIPTION) VALUES (1, 4, 2, '2017-12-12 11:54:18', 'Ticket Status is changed from [New] to [Approved]');
INSERT INTO history (ACTION, USER_ID, TICKET_ID, DATE, DESCRIPTION) VALUES (1, 4, 11, '2017-12-12 11:54:23', 'Ticket Status is changed from [New] to [Approved]');
INSERT INTO history (ACTION, USER_ID, TICKET_ID, DATE, DESCRIPTION) VALUES (1, 5, 14, '2017-12-12 11:57:42', 'Ticket Status is changed from [Approved] to [In Progress]');
INSERT INTO history (ACTION, USER_ID, TICKET_ID, DATE, DESCRIPTION) VALUES (1, 5, 2, '2017-12-12 11:57:55', 'Ticket Status is changed from [Approved] to [In Progress]');
INSERT INTO history (ACTION, USER_ID, TICKET_ID, DATE, DESCRIPTION) VALUES (1, 5, 2, '2017-12-12 11:58:00', 'Ticket Status is changed from [In Progress] to [Done]');
INSERT INTO history (ACTION, USER_ID, TICKET_ID, DATE, DESCRIPTION) VALUES (1, 6, 6, '2017-12-12 11:58:16', 'Ticket Status is changed from [Approved] to [In Progress]');
INSERT INTO history (ACTION, USER_ID, TICKET_ID, DATE, DESCRIPTION) VALUES (1, 6, 6, '2017-12-12 11:58:41', 'Ticket Status is changed from [In Progress] to [Done]');
INSERT INTO history (ACTION, USER_ID, TICKET_ID, DATE, DESCRIPTION) VALUES (1, 6, 11, '2017-12-12 11:58:47', 'Ticket Status is changed from [Approved] to [In Progress]');
INSERT INTO history (ACTION, USER_ID, TICKET_ID, DATE, DESCRIPTION) VALUES (1, 6, 11, '2017-12-12 11:58:50', 'Ticket Status is changed from [In Progress] to [Done]');


