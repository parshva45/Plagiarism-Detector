DELETE FROM course WHERE id=1;
INSERT INTO course  (id, course_name) VALUES
  (1, 'CS 5500 MSD');
DELETE FROM user WHERE id=1;
INSERT INTO user (id, first_name, last_name, user_name, password, email, role, create_date) VALUES
  (1, 'course', 'staff', 'coursestaff', 'password', 'coursestaff@gmail.com', 2, '2018-03-21 05:03:20');
DELETE FROM homework WHERE id=1;
INSERT INTO homework (id, course_id, description, home_work_number, professor_id) VALUES
  (1, 1, 'HomeWork', 1,  1);