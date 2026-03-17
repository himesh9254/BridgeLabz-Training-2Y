public class StudentManagement {
    private static class Student {
        private final int rollNumber;
        private final String name;
        private final int age;
        private String grade;

        private Student(int rollNumber, String name, int age, String grade) {
            this.rollNumber = rollNumber;
            this.name = name;
            this.age = age;
            this.grade = grade;
        }

        @Override
        public String toString() {
            return rollNumber + " | " + name + " | age=" + age + " | grade=" + grade;
        }
    }

    private static class StudentNode {
        private Student student;
        private StudentNode next;

        private StudentNode(Student student) {
            this.student = student;
        }
    }

    private static class StudentList {
        private StudentNode head;
        private int size;

        private void addAtBeginning(int rollNumber, String name, int age, String grade) {
            StudentNode node = new StudentNode(new Student(rollNumber, name, age, grade));
            node.next = head;
            head = node;
            size++;
        }

        private void addAtEnd(int rollNumber, String name, int age, String grade) {
            StudentNode node = new StudentNode(new Student(rollNumber, name, age, grade));

            if (head == null) {
                head = node;
                size++;
                return;
            }

            StudentNode current = head;

            while (current.next != null) {
                current = current.next;
            }

            current.next = node;
            size++;
        }

        private boolean addAtPosition(int rollNumber, String name, int age, String grade, int position) {
            if (position < 1 || position > size + 1) {
                return false;
            }

            if (position == 1) {
                addAtBeginning(rollNumber, name, age, grade);
                return true;
            }

            StudentNode current = head;

            for (int index = 1; index < position - 1; index++) {
                current = current.next;
            }

            StudentNode node = new StudentNode(new Student(rollNumber, name, age, grade));
            node.next = current.next;
            current.next = node;
            size++;
            return true;
        }

        private boolean deleteByRollNumber(int rollNumber) {
            if (head == null) {
                return false;
            }

            if (head.student.rollNumber == rollNumber) {
                head = head.next;
                size--;
                return true;
            }

            StudentNode current = head;

            while (current.next != null && current.next.student.rollNumber != rollNumber) {
                current = current.next;
            }

            if (current.next == null) {
                return false;
            }

            current.next = current.next.next;
            size--;
            return true;
        }

        private Student searchByRollNumber(int rollNumber) {
            StudentNode current = head;

            while (current != null) {
                if (current.student.rollNumber == rollNumber) {
                    return current.student;
                }

                current = current.next;
            }

            return null;
        }

        private boolean updateGrade(int rollNumber, String newGrade) {
            Student student = searchByRollNumber(rollNumber);

            if (student == null) {
                return false;
            }

            student.grade = newGrade;
            return true;
        }

        private String displayAll() {
            StringBuilder builder = new StringBuilder();
            StudentNode current = head;

            while (current != null) {
                if (builder.length() > 0) {
                    builder.append(System.lineSeparator());
                }

                builder.append(current.student);
                current = current.next;
            }

            return builder.toString();
        }
    }

    public static void main(String[] args) {
        StudentList students = new StudentList();
        students.addAtBeginning(101, "Rahul", 20, "A");
        students.addAtEnd(102, "Priya", 21, "B");
        students.addAtEnd(104, "Karan", 22, "C");
        students.addAtPosition(103, "Aman", 19, "A", 3);

        System.out.println("All Students");
        System.out.println(students.displayAll());
        System.out.println();

        System.out.println("Search Roll 102");
        System.out.println(students.searchByRollNumber(102));
        System.out.println();

        students.updateGrade(104, "B+");
        students.deleteByRollNumber(101);

        System.out.println("Updated Students");
        System.out.println(students.displayAll());
    }
}
