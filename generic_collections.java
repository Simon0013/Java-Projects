import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
public class Main
{
	static String name, middleName, surname;
	static int groupNumber, userSelection, familyIncome;
    	static float averageScore;
        static ArrayList<Student> arrayListStudent = new ArrayList<>();
        static int doubleMinimumWage = 26000;
        
	public static void main(String[] args) {	
	        arrayListStudent.add(new Student("Kolya", "Mirnov", "Alekseevich", 31, 5f, 28000));
	        arrayListStudent.add(new Student("Oleg", "Mimin", "Romanovich", 32, 3.1f, 20000));
	        arrayListStudent.add(new Student("Tanya", "Vacilyeva", "Andreeva", 33, 4.9f, 18000));
	        menu();
	}
	public static void menu(){
	    	Scanner in = new Scanner(System.in);
	    	System.out.println("Выберете пункт:\n1- посмотреть все пункты\n2-добавить студентов\n3-Удалить студентов\n4- Отсортировать студентов\n5- Поиск студентов по фимилии\n6-Вывод фамилии студентов, у которых доход на члена семьи меньше двух мини-мальных зарплат\n7- Выход");
        	userSelection = in.nextInt();
        	switch (userSelection) {
           		case (1):
		               arrayListStudent.forEach(student -> System.out.println(student.toString()));
		                menu();
		               break;
           		case (2):
		               addStudent();
		               menu();
		               break;
           		case (3):
		               removeStudent();
		               menu();
		               break;
	               	case (4):
		               Collections.reverse(arrayListStudent);
		               menu();
		               break;
	               	case (5):
		               searchStudentsBySurename();
		               menu();
		               break;
	               	case (6):
		               withdrawalStudentsByFamilyIncome();
		               menu();
		               break;
	               	case (7):
	               		break;
	       	}
	}
	public static void addStudent(){
		Scanner in = new Scanner(System.in);
	    	System.out.println("Введите имя:");
		name = in.nextLine();
		System.out.println("Введите отчество:");
	        middleName= in.nextLine();
	        System.out.println("Введите фамилию:");
	        surname= in.nextLine();
	        System.out.println("Введите номер группы:");
	        groupNumber = in.nextInt();
	        System.out.println("Средний балл:");
	        averageScore = in.nextFloat();
	        System.out.println("Введите доход на члена семьи:");
	        familyIncome= in.nextInt();
        	arrayListStudent.add(new Student(name, surname, middleName, groupNumber, averageScore,familyIncome));
        }
	public static void removeStudent(){
		Scanner in = new Scanner(System.in);
	        System.out.println("Введите фамилию:");
	        String surnameRemove= in.nextLine();
	        Student elRemoveStudent =null;
	        for(int i = 0; i< arrayListStudent.size();i++){
	           Student surnameList = arrayListStudent.get(i);
	            if(surnameList.getSurname().equals(surnameRemove) ){
	                elRemoveStudent = arrayListStudent.get(i);
	            }
	    	}
	        if(elRemoveStudent != null){
	            arrayListStudent.remove(elRemoveStudent);
	        }
	}
	public static void searchStudentsBySurename(){
		Scanner in = new Scanner(System.in);
        	System.out.println("Введите фамилию:");
        	String surnameRemove= in.nextLine();
        	for(int i = 0; i< arrayListStudent.size();i++){
	        	Student surnameList = arrayListStudent.get(i); 
	        	if(surnameList.getSurname().equals(surnameRemove) ){
	                	System.out.println(arrayListStudent.get(i));
	        	}
	    }
	}
	public static void withdrawalStudentsByFamilyIncome(){
		for(int i = 0; i< arrayListStudent.size();i++){
	        	Student surnameList = arrayListStudent.get(i);
	        	if(surnameList.getFamilyIncome() < doubleMinimumWage ){
	                	System.out.println(arrayListStudent.get(i));
	            	}
    		}
	}
}
class Student{
    	String name, middleName, surname;
	int groupNumber, familyIncome;
    	float averageScore;   
	public Student(String name, String surname, String middleName, int groupNumber, float averageScore, int familyIncome) {
                this.name = name;
                this.surname = surname;
                this.middleName = middleName;
                this.groupNumber = groupNumber;
                this.averageScore = averageScore;
                this.familyIncome = familyIncome;
        }
        @Override
        public String toString() {
                return "Student{" +
                        "name=" + name + " " +
                        ", surname=" + surname + " " +
                        ", middleName=" + middleName + " " +
                        ", groupNumber=" + groupNumber + " " +
                        ", averageScore=" + averageScore +" " +
                        ", familyIncome=" + familyIncome +
                        '}';
        }
        public String getSurname(){
                return surname;
        }
        public int getFamilyIncome(){
                return familyIncome;
        }
}