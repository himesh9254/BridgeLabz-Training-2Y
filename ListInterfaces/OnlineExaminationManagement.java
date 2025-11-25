import java.util.*;

class Question {
    private int questionId;
    private String questionText;
    private String[] options;
    private int correctAnswer;

    public Question(int questionId, String questionText, String[] options, int correctAnswer) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public int getQuestionId() { return questionId; }
    public String getQuestionText() { return questionText; }
    public String[] getOptions() { return options; }
    public int getCorrectAnswer() { return correctAnswer; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Q").append(questionId).append(": ").append(questionText).append("\n");
        for (int i = 0; i < options.length; i++) {
            sb.append("  ").append((char)('A' + i)).append(") ").append(options[i]).append("\n");
        }
        return sb.toString();
    }
}

class Student {
    private String studentId;
    private String name;
    private int currentQuestion;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.currentQuestion = 0;
    }

    public String getStudentId() { return studentId; }
    public String getName() { return name; }
    public int getCurrentQuestion() { return currentQuestion; }
    public void setCurrentQuestion(int q) { this.currentQuestion = q; }

    @Override
    public String toString() {
        return String.format("Student[%s, %s]", studentId, name);
    }
}

public class OnlineExaminationManagement {
    private List<Question> questionBank;
    private Set<String> enrolledStudentIds;
    private Queue<Student> examWaitingQueue;
    private Stack<Question> questionNavigationStack;

    public OnlineExaminationManagement() {
        questionBank = new ArrayList<>();
        enrolledStudentIds = new HashSet<>();
        examWaitingQueue = new LinkedList<>();
        questionNavigationStack = new Stack<>();
    }

    public boolean enrollStudent(String studentId, String name) {
        if (enrolledStudentIds.contains(studentId)) {
            System.out.println("Student " + studentId + " already enrolled!");
            return false;
        }
        enrolledStudentIds.add(studentId);
        Student student = new Student(studentId, name);
        examWaitingQueue.add(student);
        System.out.println("Enrolled: " + student);
        return true;
    }

    public void addQuestion(Question question) {
        questionBank.add(question);
        System.out.println("Added question: Q" + question.getQuestionId());
    }

    public void randomizeQuestions() {
        System.out.println("\n=== Randomizing Questions ===");
        Collections.shuffle(questionBank);
        System.out.println("Questions randomized. New order:");
        for (int i = 0; i < questionBank.size(); i++) {
            System.out.println("  Position " + (i + 1) + ": Q" + questionBank.get(i).getQuestionId());
        }
    }

    public Student serveNextStudent() {
        if (examWaitingQueue.isEmpty()) {
            System.out.println("No students waiting.");
            return null;
        }
        Student student = examWaitingQueue.poll();
        System.out.println("Now serving: " + student);
        return student;
    }

    public void displayQuestion(int index) {
        if (index < 0 || index >= questionBank.size()) {
            System.out.println("Invalid question index!");
            return;
        }
        Question q = questionBank.get(index);
        questionNavigationStack.push(q);
        System.out.println("\n" + q);
    }

    public Question goBack() {
        if (questionNavigationStack.size() <= 1) {
            System.out.println("Cannot go back further!");
            return null;
        }
        questionNavigationStack.pop();
        Question previousQuestion = questionNavigationStack.peek();
        System.out.println("Going back to: Q" + previousQuestion.getQuestionId());
        return previousQuestion;
    }

    public void displayNavigationHistory() {
        System.out.println("\n=== Navigation History (Recent to Oldest) ===");
        Stack<Question> tempStack = new Stack<>();
        tempStack.addAll(questionNavigationStack);
        while (!tempStack.isEmpty()) {
            System.out.println("  Q" + tempStack.pop().getQuestionId());
        }
    }

    public void displayWaitingStudents() {
        System.out.println("\n=== Students Waiting for Exam ===");
        for (Student s : examWaitingQueue) {
            System.out.println("  " + s);
        }
    }

    public void displayEnrolledStudents() {
        System.out.println("\n=== Enrolled Student IDs ===");
        System.out.println(enrolledStudentIds);
    }

    public static void main(String[] args) {
        OnlineExaminationManagement system = new OnlineExaminationManagement();

        system.addQuestion(new Question(1, "What is Java?",
            new String[]{"Programming Language", "Coffee", "Island", "None"}, 0));
        system.addQuestion(new Question(2, "What is JVM?",
            new String[]{"Java Virtual Machine", "Java Version Manager", "Java Visual Mode", "None"}, 0));
        system.addQuestion(new Question(3, "What is OOP?",
            new String[]{"Object Oriented Programming", "Online Object Protocol", "Open Office Program", "None"}, 0));
        system.addQuestion(new Question(4, "What is a Class?",
            new String[]{"Blueprint of Object", "Type of Variable", "Method Name", "None"}, 0));
        system.addQuestion(new Question(5, "What is Inheritance?",
            new String[]{"Code Reuse Mechanism", "Data Type", "Loop Structure", "None"}, 0));

        System.out.println("\n=== Enrolling Students ===");
        system.enrollStudent("STU001", "John Doe");
        system.enrollStudent("STU002", "Jane Smith");
        system.enrollStudent("STU001", "John Duplicate");
        system.enrollStudent("STU003", "Bob Wilson");
        system.enrollStudent("STU004", "Alice Brown");

        system.displayEnrolledStudents();
        system.displayWaitingStudents();

        system.randomizeQuestions();

        System.out.println("\n=== Serving Students ===");
        Student currentStudent = system.serveNextStudent();

        System.out.println("\n=== Student " + currentStudent.getName() + " Taking Exam ===");
        system.displayQuestion(0);
        system.displayQuestion(1);
        system.displayQuestion(2);

        System.out.println("\n=== Student Goes Back ===");
        system.goBack();
        system.displayNavigationHistory();

        system.displayQuestion(3);
        system.displayNavigationHistory();
    }
}
