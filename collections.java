import java.util.ArrayList;
import java.util.Comparator;
class Main {
    static float minSalary = 13_000;
    public static void main(String[] args) {
        StudentsTask studentsTask = new StudentsTask();
        studentsTask.init();
        studentsTask.sortByGroupNumber();
        studentsTask.printSurnameIfSalaryLessThan2Min();
    }
    static class StudentsTask {
        ArrayList<Student> arrayList = new ArrayList<>();
        void init() {
            arrayList.add(new Student("Паша", "Степанов", "Александрович", 32, 4.3f, 13_000));
            arrayList.add(new Student("Катя", "Костюкова", "Степановна", 33, 4.5f, 25_000));
            arrayList.add(new Student("Саша", "Иванов", "Александрович", 31, 3.9f, 10_000));
            arrayList.add(new Student("Даша", "Гордеева", "Степановна", 41, 3.5f, 26_000));
            arrayList.add(new Student("Костя", "Гречка", "Александрович", 32, 4f, 27_000));
        }
        void printAll() {
            arrayList.forEach(student -> System.out.println(student.toString()));
        }
        void sortByGroupNumber() {
            arrayList.sort(Comparator.comparingInt(o -> o.groupNumber)); 
        }
        void printSurnameIfSalaryLessThan2Min() {
            arrayList.stream()
                    .filter(student -> student.familyMemberSalary < minSalary * 2)
                    .forEach(student -> System.out.println(student.surname));
        }
        void remove(int index) {
            arrayList.remove(index);
        }
        static class Student {
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
                return "Prac7.StudentsTask.Student{" +
                        "name='" + name + '\'' +
                        ", surname='" + surname + '\'' +
                        ", middleName='" + middleName + '\'' +
                        ", groupNumber=" + groupNumber +
                        ", averageMark=" + averageMark +
                        ", familyMemberSalary=" + familyMemberSalary +
                        '}';
            }
        }
    }
}