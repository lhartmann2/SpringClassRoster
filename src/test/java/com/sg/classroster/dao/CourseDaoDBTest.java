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
public class CourseDaoDBTest {

    @Autowired
    TeacherDao teacherDao;

    @Autowired
    StudentDao studentDao;

    @Autowired
    CourseDao courseDao;

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
    public void testAddAndGetCourse() {
        Teacher teacher = new Teacher();
        teacher.setFirstName("test first");
        teacher.setLastName("test last");
        teacher.setSpecialty("Test specialty");
        teacher = teacherDao.addTeacher(teacher);

        Student student = new Student();
        student.setFirstName("test first name");
        student.setLastName("test last name");
        student = studentDao.addStudent(student);

        List<Student> studentList = new ArrayList<>();
        studentList.add(student);

        Course course = new Course();
        course.setName("test course name");
        course.setDescription("test course description");
        course.setTeacher(teacher);
        course.setStudents(studentList);
        course = courseDao.addCourse(course);

        Course fromDao = courseDao.getCourseById(course.getId());
        assertEquals(course, fromDao);
    }

    @Test
    public void testGetAllCourses() {
        Teacher teacher = new Teacher();
        teacher.setFirstName("test first");
        teacher.setLastName("test last");
        teacher.setSpecialty("Test specialty");
        teacher = teacherDao.addTeacher(teacher);

        Student student = new Student();
        student.setFirstName("test first name");
        student.setLastName("test last name");
        student = studentDao.addStudent(student);

        List<Student> studentList = new ArrayList<>();
        studentList.add(student);

        Course course = new Course();
        course.setName("test course name");
        course.setDescription("test course description");
        course.setTeacher(teacher);
        course.setStudents(studentList);
        course = courseDao.addCourse(course);

        Course course2 = new Course();
        course2.setName("test course 2");
        course2.setDescription("test course description 2");
        course2.setTeacher(teacher);
        course2.setStudents(studentList);
        course2 = courseDao.addCourse(course2);

        List<Course> courses = courseDao.getAllCourses();

        assertEquals(2, courses.size());

        assertTrue(courses.contains(course));
        assertTrue(courses.contains(course2));
    }

    @Test
    public void testUpdateCourse() {
        Teacher teacher = new Teacher();
        teacher.setFirstName("test first");
        teacher.setLastName("test last");
        teacher.setSpecialty("Test specialty");
        teacher = teacherDao.addTeacher(teacher);

        Student student = new Student();
        student.setFirstName("test first name");
        student.setLastName("test last name");
        student = studentDao.addStudent(student);

        List<Student> studentList = new ArrayList<>();
        studentList.add(student);

        Course course = new Course();
        course.setName("test course name");
        course.setDescription("test course description");
        course.setTeacher(teacher);
        course.setStudents(studentList);
        course = courseDao.addCourse(course);

        Course fromDao = courseDao.getCourseById(course.getId());
        assertEquals(course, fromDao);

        course.setName("new test course");
        Student student2 = new Student();
        student2.setFirstName("test first name 2");
        student2.setLastName("test last name 2");
        student2 = studentDao.addStudent(student2);
        studentList.add(student2);
        course.setStudents(studentList);

        courseDao.updateCourse(course);

        assertNotEquals(course, fromDao);

        fromDao = courseDao.getCourseById(course.getId());
        assertEquals(course, fromDao);
    }

    @Test
    public void testDeleteCourseById() {
        Teacher teacher = new Teacher();
        teacher.setFirstName("test first");
        teacher.setLastName("test last");
        teacher.setSpecialty("Test specialty");
        teacher = teacherDao.addTeacher(teacher);

        Student student = new Student();
        student.setFirstName("test first name");
        student.setLastName("test last name");
        student = studentDao.addStudent(student);

        List<Student> studentList = new ArrayList<>();
        studentList.add(student);

        Course course = new Course();
        course.setName("test course name");
        course.setDescription("test course description");
        course.setTeacher(teacher);
        course.setStudents(studentList);
        course = courseDao.addCourse(course);

        Course fromDao = courseDao.getCourseById(course.getId());
        assertEquals(course, fromDao);

        courseDao.deleteCourseById(course.getId());
        fromDao = courseDao.getCourseById(course.getId());
        assertNull(fromDao);
    }

    @Test
    public void testGetCoursesForTeacher() {
        Teacher teacher = new Teacher();
        teacher.setFirstName("test first");
        teacher.setLastName("test last");
        teacher.setSpecialty("Test specialty");
        teacher = teacherDao.addTeacher(teacher);

        Teacher teacher2 = new Teacher();
        teacher2.setFirstName("test teacher first 2");
        teacher2.setLastName("test teacher last 2");
        teacher2.setSpecialty("test specialty 2");
        teacher2 = teacherDao.addTeacher(teacher2);

        Student student = new Student();
        student.setFirstName("test first name");
        student.setLastName("test last name");
        student = studentDao.addStudent(student);

        List<Student> studentList = new ArrayList<>();
        studentList.add(student);

        Course course = new Course();
        course.setName("test course name");
        course.setDescription("test course description");
        course.setTeacher(teacher);
        course.setStudents(studentList);
        course = courseDao.addCourse(course);

        Course course2 = new Course();
        course2.setName("test course name 2");
        course2.setDescription("test course description 2");
        course2.setTeacher(teacher2);
        course2.setStudents(studentList);
        course2 = courseDao.addCourse(course2);

        Course course3 = new Course();
        course3.setName("test course name 3");
        course3.setDescription("test course description 3");
        course3.setTeacher(teacher);
        course3.setStudents(studentList);
        course3 = courseDao.addCourse(course3);

        List<Course> courses = courseDao.getCoursesForTeacher(teacher);
        assertEquals(2, courses.size());
        assertTrue(courses.contains(course));
        assertFalse(courses.contains(course2));
        assertTrue(courses.contains(course3));
    }

    @Test
    public void testGetCoursesForStudent() {
        Teacher teacher = new Teacher();
        teacher.setFirstName("test first");
        teacher.setLastName("test last");
        teacher.setSpecialty("Test specialty");
        teacher = teacherDao.addTeacher(teacher);

        Student student = new Student();
        student.setFirstName("test first name");
        student.setLastName("test last name");
        student = studentDao.addStudent(student);

        Student student2 = new Student();
        student2.setFirstName("test first name 2");
        student2.setLastName("test last name 2");
        student2 = studentDao.addStudent(student2);

        List<Student> studentList = new ArrayList<>();
        studentList.add(student);
        studentList.add(student2);

        List<Student> studentList2 = new ArrayList<>();
        studentList2.add(student2);

        Course course = new Course();
        course.setName("test course name");
        course.setDescription("test course description");
        course.setTeacher(teacher);
        course.setStudents(studentList);
        course = courseDao.addCourse(course);

        Course course2 = new Course();
        course2.setName("test course name 2");
        course2.setDescription("test course description 2");
        course2.setTeacher(teacher);
        course2.setStudents(studentList2);
        course2 = courseDao.addCourse(course2);

        Course course3 = new Course();
        course3.setName("test course name 3");
        course3.setDescription("test course description 3");
        course3.setTeacher(teacher);
        course3.setStudents(studentList);
        course3 = courseDao.addCourse(course3);

        List<Course> courses = courseDao.getCoursesForStudent(student);
        assertEquals(2, courses.size());
        assertTrue(courses.contains(course));
        assertFalse(courses.contains(course2));
        assertTrue(courses.contains(course3));
    }
}