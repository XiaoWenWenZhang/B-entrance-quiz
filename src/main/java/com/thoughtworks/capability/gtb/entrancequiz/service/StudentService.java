package com.thoughtworks.capability.gtb.entrancequiz.service;

import com.thoughtworks.capability.gtb.entrancequiz.bo.Student;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.thoughtworks.capability.gtb.entrancequiz.constants.StudentConstants.INIT_STUDENTS;

@Service
public class StudentService {
    List<Student> students = new ArrayList<>();
    public StudentService() {
        this.initData();
    }

    private void initData() {
        AtomicInteger id= new AtomicInteger(1);
        Arrays.stream(INIT_STUDENTS).forEach(item->{
            students.add(new Student(id.getAndIncrement(),item));
        });
    }

    public void addStudent(String student) {

        Student lastStudent = students.get(students.size() - 1);
        int lastId = lastStudent.getId();
        Student currentStudent = new Student(lastId+1,student);
          students.add(currentStudent);
    }

    public List<Student> getStudents() {
        return students;
    }

    public void deleteStudentById(int id) {
        List<Student> studentList = students.stream()
                .filter(item -> item.getId() == id)
                .collect(Collectors.toList());
        students.remove(studentList.get(0));

    }
}
