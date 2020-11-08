package com.thoughtworks.capability.gtb.entrancequiz.service;

import com.thoughtworks.capability.gtb.entrancequiz.bo.Group;
import com.thoughtworks.capability.gtb.entrancequiz.bo.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static com.thoughtworks.capability.gtb.entrancequiz.constants.StudentConstants.TOTAL_GROUPS;

@Service
public class GroupService {
    List<Group> groups = new ArrayList<>();
    private final StudentService studentService;
    Integer[] groupSize = new Integer[TOTAL_GROUPS];
    List<Student> studentList ;

    public GroupService(StudentService studentService) {
        this.studentService = studentService;

    }

    private void initGroups() {
        groups.clear();
        studentList = new ArrayList<>(studentService.getStudents());
        int averageSize = studentList.size() / TOTAL_GROUPS;
        int remainStudents = studentList.size()  - averageSize * TOTAL_GROUPS;

        for (int i = 0; i < TOTAL_GROUPS; i++) {
            if (i < remainStudents) groupSize[i] = averageSize + 1;
            else groupSize[i] = averageSize;
            groups.add(new Group(i + 1, (i + 1) + " 组", new ArrayList<Student>()));
        }

    }

    public List<Group> getGroups() {
        this.initGroups();


        for (int i = 0; i < TOTAL_GROUPS; i++) {
            for (int j = 0; j < groupSize[i]; j++) {
                int studentIndex = new Random().nextInt(studentList.size());
                List<Student> students = groups.get(i).getStudents();
                students.add(studentList.get(studentIndex));
                groups.get(i).setStudents(students);
                studentList.remove(studentIndex);
            }
        }
        sortStudents(groups);

        return groups;
    }

    private void sortStudents(List<Group> groups) {
        for (int i = 0; i < TOTAL_GROUPS; i++) {
            List<Student> currentStudents = groups.get(i).getStudents();
            currentStudents.sort(new Comparator<Student>() {
                @Override
                public int compare(Student o1, Student o2) {
                    return o1.getId() - o2.getId();
                }
            });
            groups.get(i).setStudents(currentStudents);
        }
    }


}
