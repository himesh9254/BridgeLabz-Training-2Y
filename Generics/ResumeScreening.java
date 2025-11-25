import java.util.ArrayList;
import java.util.List;

abstract class JobRole {
    private String roleName;
    private int minExperienceYears;

    public JobRole(String roleName, int minExperienceYears) {
        this.roleName = roleName;
        this.minExperienceYears = minExperienceYears;
    }

    public String getRoleName() {
        return roleName;
    }

    public int getMinExperienceYears() {
        return minExperienceYears;
    }

    public abstract List<String> getRequiredSkills();
}

class SoftwareEngineer extends JobRole {
    public SoftwareEngineer() {
        super("Software Engineer", 2);
    }

    @Override
    public List<String> getRequiredSkills() {
        List<String> skills = new ArrayList<>();
        skills.add("Java");
        skills.add("Data Structures");
        skills.add("Algorithms");
        skills.add("Git");
        return skills;
    }
}

class DataScientist extends JobRole {
    public DataScientist() {
        super("Data Scientist", 3);
    }

    @Override
    public List<String> getRequiredSkills() {
        List<String> skills = new ArrayList<>();
        skills.add("Python");
        skills.add("Machine Learning");
        skills.add("Statistics");
        skills.add("SQL");
        return skills;
    }
}

class ProductManager extends JobRole {
    public ProductManager() {
        super("Product Manager", 4);
    }

    @Override
    public List<String> getRequiredSkills() {
        List<String> skills = new ArrayList<>();
        skills.add("Product Strategy");
        skills.add("Market Analysis");
        skills.add("Agile Methodology");
        skills.add("Communication");
        return skills;
    }
}

class Resume<T extends JobRole> {
    private String candidateName;
    private String email;
    private int experienceYears;
    private List<String> skills;
    private T appliedRole;

    public Resume(String candidateName, String email, int experienceYears, List<String> skills, T appliedRole) {
        this.candidateName = candidateName;
        this.email = email;
        this.experienceYears = experienceYears;
        this.skills = skills;
        this.appliedRole = appliedRole;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public String getEmail() {
        return email;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public List<String> getSkills() {
        return skills;
    }

    public T getAppliedRole() {
        return appliedRole;
    }

    public void displayResume() {
        System.out.println("Name: " + candidateName);
        System.out.println("Email: " + email);
        System.out.println("Experience: " + experienceYears + " years");
        System.out.println("Skills: " + String.join(", ", skills));
        System.out.println("Applied for: " + appliedRole.getRoleName());
    }
}

class ResumeScreeningSystem {
    public static <T extends JobRole> boolean screenResume(Resume<T> resume) {
        T role = resume.getAppliedRole();
        
        if (resume.getExperienceYears() < role.getMinExperienceYears()) {
            return false;
        }

        List<String> requiredSkills = role.getRequiredSkills();
        List<String> candidateSkills = resume.getSkills();
        int matchedSkills = 0;

        for (String skill : requiredSkills) {
            for (String candidateSkill : candidateSkills) {
                if (skill.equalsIgnoreCase(candidateSkill)) {
                    matchedSkills++;
                    break;
                }
            }
        }

        double matchPercentage = (double) matchedSkills / requiredSkills.size() * 100;
        return matchPercentage >= 50;
    }

    public static void processResumes(List<? extends Resume<? extends JobRole>> resumes) {
        System.out.println("\n=== AI-Driven Resume Screening Results ===\n");

        List<Resume<? extends JobRole>> shortlisted = new ArrayList<>();
        List<Resume<? extends JobRole>> rejected = new ArrayList<>();

        for (Resume<? extends JobRole> resume : resumes) {
            if (screenResume(resume)) {
                shortlisted.add(resume);
            } else {
                rejected.add(resume);
            }
        }

        System.out.println("SHORTLISTED CANDIDATES (" + shortlisted.size() + "):");
        System.out.println("-----------------------------------");
        for (Resume<? extends JobRole> resume : shortlisted) {
            System.out.println("✓ " + resume.getCandidateName() + " - " + resume.getAppliedRole().getRoleName());
        }

        System.out.println("\nREJECTED CANDIDATES (" + rejected.size() + "):");
        System.out.println("-----------------------------------");
        for (Resume<? extends JobRole> resume : rejected) {
            System.out.println("✗ " + resume.getCandidateName() + " - " + resume.getAppliedRole().getRoleName());
        }
    }
}

public class ResumeScreening {
    public static void main(String[] args) {
        List<String> johnSkills = new ArrayList<>();
        johnSkills.add("Java");
        johnSkills.add("Data Structures");
        johnSkills.add("Algorithms");
        johnSkills.add("Spring Boot");
        Resume<SoftwareEngineer> johnResume = new Resume<>("John Doe", "john@email.com", 3, johnSkills, new SoftwareEngineer());

        List<String> janeSkills = new ArrayList<>();
        janeSkills.add("JavaScript");
        janeSkills.add("HTML");
        Resume<SoftwareEngineer> janeResume = new Resume<>("Jane Smith", "jane@email.com", 1, janeSkills, new SoftwareEngineer());

        List<String> aliceSkills = new ArrayList<>();
        aliceSkills.add("Python");
        aliceSkills.add("Machine Learning");
        aliceSkills.add("TensorFlow");
        aliceSkills.add("Statistics");
        Resume<DataScientist> aliceResume = new Resume<>("Alice Johnson", "alice@email.com", 4, aliceSkills, new DataScientist());

        List<String> bobSkills = new ArrayList<>();
        bobSkills.add("Excel");
        bobSkills.add("PowerPoint");
        Resume<DataScientist> bobResume = new Resume<>("Bob Wilson", "bob@email.com", 2, bobSkills, new DataScientist());

        List<String> charlieSkills = new ArrayList<>();
        charlieSkills.add("Product Strategy");
        charlieSkills.add("Market Analysis");
        charlieSkills.add("Agile Methodology");
        charlieSkills.add("Leadership");
        Resume<ProductManager> charlieResume = new Resume<>("Charlie Brown", "charlie@email.com", 5, charlieSkills, new ProductManager());

        List<String> dianaSkills = new ArrayList<>();
        dianaSkills.add("Communication");
        dianaSkills.add("Teamwork");
        Resume<ProductManager> dianaResume = new Resume<>("Diana Prince", "diana@email.com", 3, dianaSkills, new ProductManager());

        System.out.println("=== Individual Resume Details ===\n");
        System.out.println("--- Resume 1 ---");
        johnResume.displayResume();
        System.out.println();

        System.out.println("--- Resume 2 ---");
        aliceResume.displayResume();
        System.out.println();

        List<Resume<? extends JobRole>> allResumes = new ArrayList<>();
        allResumes.add(johnResume);
        allResumes.add(janeResume);
        allResumes.add(aliceResume);
        allResumes.add(bobResume);
        allResumes.add(charlieResume);
        allResumes.add(dianaResume);

        ResumeScreeningSystem.processResumes(allResumes);
    }
}
