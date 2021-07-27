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
public class TeacherDaoDBTest {

    @Autowired
    TeacherDao teacherDao;

    @Autowired
    StudentDao studentDao;

    @Autowired
    CourseDao courseDao;

    public TeacherDaoDBTest() {
    }

    @Before
    public void setUp() {
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
    public void tearDown() {
    }

    @Test
    public void testAddAndGetTeacher() {
        Teacher teacher = new Teacher();
        teacher.setFirstName("Test First");
        teacher.setLastName("Test Last");
        teacher.setSpecialty("Test Specialty");
        teacher = teacherDao.addTeacher(teacher);

        Teacher fromDao = teacherDao.getTeacherById(teacher.getId());

        assertEquals(teacher, fromDao);
    }

    @Test
    public void testGetAllTeachers() {
        Teacher teacher = new Teacher();
        teacher.setFirstName("Test First");
        teacher.setLastName("Test Last");
        teacher.setSpecialty("Test Specialty");
        teacher = teacherDao.addTeacher(teacher);

        Teacher teacher2 = new Teacher();
        teacher2.setFirstName("Test first 2");
        teacher2.setLastName("Test last 2");
        teacher2.setSpecialty("Test specialty 2");
        teacher2 = teacherDao.addTeacher(teacher2);

        List<Teacher> testList = teacherDao.getAllTeachers();

        assertEquals(2, testList.size());
        assertTrue(testList.contains(teacher));
        assertTrue(testList.contains(teacher2));
    }

    @Test
    public void testUpdateTeacher() {
        Teacher teacher = new Teacher();
        teacher.setFirstName("Test First");
        teacher.setLastName("Test Last");
        teacher.setSpecialty("Test Specialty");
        teacher = teacherDao.addTeacher(teacher);

        Teacher fromDao = teacherDao.getTeacherById(teacher.getId());

        assertEquals(teacher, fromDao);

        teacher.setFirstName("test first different");
        teacherDao.updateTeacher(teacher);

        assertNotEquals(fromDao, teacher);

        fromDao = teacherDao.getTeacherById(teacher.getId());

        assertEquals(fromDao, teacher);
    }

    @Test
    public void testDeleteTeacherById() {
        Teacher teacher = new Teacher();
        teacher.setFirstName("Test First");
        teacher.setLastName("Test Last");
        teacher.setSpecialty("Test Specialty");
        teacher = teacherDao.addTeacher(teacher);

        Student student = new Student();
        student.setFirstName("Test first");
        student.setLastName("Test last");
        student = studentDao.addStudent(student);
        List<Student> students = new ArrayList<>();
        students.add(student);

        Course course = new Course();
        course.setName("Test course");
        course.setTeacher(teacher);
        course.setStudents(students);
        course = courseDao.addCourse(course);

        Teacher fromDao = teacherDao.getTeacherById(teacher.getId());
        assertEquals(teacher, fromDao);

        teacherDao.deleteTeacherById(teacher.getId());

        fromDao = teacherDao.getTeacherById(teacher.getId());
        assertNull(fromDao);
    }
}