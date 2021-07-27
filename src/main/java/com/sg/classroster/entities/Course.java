package com.sg.classroster.entities;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

public class Course {
    private int id;

    @NotBlank(message = "Course name must not be blank.")
    @Size(max = 50, message = "Course name must be less than 50 characters.")
    private String name;

    @Size(max = 255, message = "Description must be less than 255 characters.")
    private String description;

    private Teacher teacher;
    private List<Student> students;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher t) {
        teacher = t;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (getId() != course.getId()) return false;
        if (getName() != null ? !getName().equals(course.getName()) : course.getName() != null) return false;
        if (getDescription() != null ? !getDescription().equals(course.getDescription()) : course.getDescription() != null)
            return false;
        return getStudents() != null ? getStudents().equals(course.getStudents()) : course.getStudents() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getTeacher() != null ? getTeacher().hashCode() : 0);
        result = 31 * result + (getStudents() != null ? getStudents().hashCode() : 0);
        return result;
    }
}
