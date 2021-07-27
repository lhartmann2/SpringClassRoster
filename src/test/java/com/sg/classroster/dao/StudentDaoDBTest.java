package com.sg.classroster.dao;

import com.sg.classroster.entities.Course;
import com.sg.classroster.entities.Student;
import com.sg.classroster.entities.Teacher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentDaoDBTest {

    @Autowired
    TeacherDao teacherDao;

    @Autowired
    StudentDao studentDao;

    @Autowired
    CourseDao courseDao;

    @Before
    public void setUp() throws Exception {
        List<Teacher> teachers = teacherDao.getAllTeachers();
        for(Teacher t : teachers) {
            teacherDao.deleteTeacherById(t.getId());
        }

        List<Student> students = studentDao.getAllStudents();
        for(Student s : students) {
            studentDao.deleteStudentById(s.getId());
        }

        List<Course> courses = courseDao.getAllCourses();
        for(Course c : courses) {
            courseDao.deleteCourseById(c.getId());
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAddAndGetStudent() {
        Student student = new Student();
        student.setFirstName("Test first");
        student.setLastName("Test last");
        student = studentDao.addStudent(student);

        Student fromDao = studentDao.getStudentById(student.getId());

        assertEquals(student, fromDao);
    }

    @Test
    public void testGetAllStudents() {
        Student student = new Student();
        student.setFirstName("Test first");
        student.setLastName("Test last");
        student = studentDao.addStudent(student);

        Student student2 = new Student();
        student2.setFirstName("test first 2");
        student2.setLastName("test last 2");
        student2 = studentDao.addStudent(student2);

        List<Student> students = studentDao.getAllStudents();

        assertEquals(2, students.size());
        assertTrue(students.contains(student));
        assertTrue(students.contains(student2));
    }

    @Test
    public void testUpdateStudent() {
        Student student = new Student();
        student.setFirstName("Test first");
        student.setLastName("Test last");
        student = studentDao.addStudent(student);

        Student fromDao = studentDao.getStudentById(student.getId());

        assertEquals(student, fromDao);

        student.setFirstName("test first different");
        studentDao.updateStudent(student);

        assertNotEquals(student, fromDao);

        fromDao = studentDao.getStudentById(student.getId());
        assertEquals(student, fromDao);
    }

    @Test
    public void testDeleteStudent() {
        Teacher teacher = new Teacher();
        teacher.setFirstName("test first");
        teacher.setLastName("test last");
        teacher.setSpecialty("test specialty");
        teacher = teacherDao.addTeacher(teacher);

        Student student = new Student();
        student.setFirstName("Test first");
        student.setLastName("Test last");
        student = studentDao.addStudent(student);
        List<Student> students = new ArrayList<>();
        students.add(student);

        Course course = new Course();
        course.setName("test name");
        course.setTeacher(teacher);
        course.setStudents(students);
        course = courseDao.addCourse(course);

        Student fromDao = studentDao.getStudentById(student.getId());
        assertEquals(student, fromDao);

        studentDao.deleteStudentById(student.getId());
        fromDao = studentDao.getStudentById(student.getId());
        assertNull(fromDao);
    }
}