class Course {
    String courseName;
    int duration;

    Course(String courseName, int duration) {
        this.courseName = courseName;
        this.duration = duration;
    }

    void displayCourseInfo() {
        System.out.println("Course Name: " + courseName);
        System.out.println("Duration: " + duration + " weeks");
    }
}

class OnlineCourse extends Course {
    String platform;
    boolean isRecorded;

    OnlineCourse(String courseName, int duration, String platform, boolean isRecorded) {
        super(courseName, duration);
        this.platform = platform;
        this.isRecorded = isRecorded;
    }

    @Override
    void displayCourseInfo() {
        super.displayCourseInfo();
        System.out.println("Platform: " + platform);
        System.out.println("Recorded: " + (isRecorded ? "Yes" : "No"));
    }
}

class PaidOnlineCourse extends OnlineCourse {
    double fee;
    double discount;

    PaidOnlineCourse(String courseName, int duration, String platform, boolean isRecorded, double fee, double discount) {
        super(courseName, duration, platform, isRecorded);
        this.fee = fee;
        this.discount = discount;
    }

    @Override
    void displayCourseInfo() {
        super.displayCourseInfo();
        System.out.println("Fee: $" + fee);
        System.out.println("Discount: " + discount + "%");
        double finalPrice = fee - (fee * discount / 100);
        System.out.println("Final Price: $" + finalPrice);
    }

    double calculateFinalPrice() {
        return fee - (fee * discount / 100);
    }
}

public class CourseHierarchy {
    public static void main(String[] args) {
        Course basicCourse = new Course("Introduction to Programming", 8);
        OnlineCourse onlineCourse = new OnlineCourse("Web Development", 12, "Coursera", true);
        PaidOnlineCourse paidCourse = new PaidOnlineCourse("Advanced Java", 16, "Udemy", true, 199.99, 20);

        System.out.println("=== Educational Course Hierarchy ===\n");
        
        System.out.println("Basic Course:");
        basicCourse.displayCourseInfo();
        
        System.out.println("\nOnline Course:");
        onlineCourse.displayCourseInfo();
        
        System.out.println("\nPaid Online Course:");
        paidCourse.displayCourseInfo();
        
        System.out.println("\nMultilevel Inheritance Chain:");
        System.out.println("Course <- OnlineCourse <- PaidOnlineCourse");
    }
}