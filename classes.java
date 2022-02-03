import java.util.ArrayList;
import java.util.Collections;
class Main {
    public static void main(String[] args) {
        StudentsTask studentsTask = new StudentsTask();
        studentsTask.init();
        System.out.println("Отображение всех студентов");
        studentsTask.display();
        studentsTask.sortBySalary();
        System.out.println("Отображение отсортированного списка студентов");
        studentsTask.display();
        StudentsTask.Student minMarkStudent = studentsTask.minMark();
        System.out.println("Студент с минимальным средним баллом");
        System.out.println(minMarkStudent.toString());
    }
    static class StudentsTask {
        ArrayList<Student> students = new ArrayList<>();
        void init() {
            students.add(new Student("Паша", "Степанов", "Александрович", 32, 4.3f, 13_000));
            students.add(new Student("Катя", "Костюкова", "Степановна", 33, 4.5f, 25_000));
            students.add(new Student("Саша", "Иванов", "Александрович", 31, 3.9f, 10_000));
            students.add(new Student("Даша", "Гордеева", "Степановна", 41, 3.5f, 26_000));
            students.add(new Student("Костя", "Гречка", "Александрович", 32, 4f, 27_000));
        }
        void display() {
            students.forEach(student -> System.out.println(student.toString()));
        }
        Student minMark() {
           Student minMarkStudent = null;
            for (Student student : students) {
                if (minMarkStudent == null) {
                    minMarkStudent = student;
                } else {
                    if (minMarkStudent.averageMark > student.averageMark) {
                        minMarkStudent = student;
                    }
                }
            }
            return minMarkStudent;
        } 
        void sortBySalary() {
            Collections.sort(students);
        }
        static class Student implements Comparable<Student> {
            String name;
            String surname;
            String middleName;
            int groupNumber;
            float averageMark;
            float familyMemberSalary;
            public Student(String name, String surname, String middleName, int groupNumber, float averageMark, float familyMemberSalary) {
                this.name = name;
                this.surname = surname;
                this.middleName = middleName;
                this.groupNumber = groupNumber;
                this.averageMark = averageMark;
                this.familyMemberSalary = familyMemberSalary;
            }
            @Override
            public String toString() {
                return "Student{" +
                        "name='" + name + '\'' +
                        ", surname='" + surname + '\'' +
                        ", middleName='" + middleName + '\'' +
                        ", groupNumber=" + groupNumber +
                        ", averageMark=" + averageMark +
                        ", familyMemberSalary=" + familyMemberSalary +
                        '}';
            }
            @Override
            public int compareTo(Student o) {
                if(this.familyMemberSalary > o.familyMemberSalary){
                    return 1;
                } else if (this.familyMemberSalary < o.familyMemberSalary){
                    return -1;
                } else {
                    return 0;
                }
            }
        }
    }
}