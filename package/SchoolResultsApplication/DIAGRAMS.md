# School Results Application - UML Diagrams

## 1. Class Diagram

### Description
The class diagram represents the structure of a school results application where students have subjects, and their scores are calculated for grades.

### Classes and Relationships

```
┌─────────────────────┐
│      Student        │
├─────────────────────┤
│ - studentId: String │
│ - name: String      │
│ - email: String     │
│ - subjects: List    │
├─────────────────────┤
│ + addSubject()      │
│ + getSubjects()     │
│ + displayInfo()     │
└─────────────────────┘
           │
           │ Aggregation (1..*)
           │ (Student HAS subjects)
           ▼
┌─────────────────────┐
│      Subject        │
├─────────────────────┤
│ - subjectName: String│
│ - marks: double     │
│ - maxMarks: double  │
├─────────────────────┤
│ + getPercentage()   │
│ + getGrade()        │
│ + displayInfo()     │
└─────────────────────┘
           │
           │ Uses
           ▼
┌─────────────────────┐
│  GradeCalculator    │
├─────────────────────┤
│ (no attributes)     │
├─────────────────────┤
│ + calculateGrade()  │
│ + calculateOverall()│
│ + generateReport()  │
└─────────────────────┘
```

### Key Points
- **Aggregation**: Student HAS-A relationship with Subject (subjects can exist without student)
- **Association**: GradeCalculator uses Student and Subject data
- **Encapsulation**: Private attributes with public methods

---

## 2. Object Diagram

### Scenario: Student John's Results at a Specific Time

```
┌────────────────────────────┐
│  john:Student              │
├────────────────────────────┤
│ studentId = "S001"         │
│ name = "John"              │
│ email = "john@school.com"  │
└────────────────────────────┘
           │
           │ has subjects
           ▼
┌────────────────────────────┐
│  math:Subject              │
├────────────────────────────┤
│ subjectName = "Maths"      │
│ marks = 90.0               │
│ maxMarks = 100.0           │
└────────────────────────────┘

┌────────────────────────────┐
│  science:Subject           │
├────────────────────────────┤
│ subjectName = "Science"    │
│ marks = 85.0               │
│ maxMarks = 100.0           │
└────────────────────────────┘

┌────────────────────────────┐
│  calc:GradeCalculator      │
├────────────────────────────┤
│ (calculates for john)      │
└────────────────────────────┘
```

### Snapshot Details
- **Student**: John (ID: S001)
- **Subjects**: 
  - Maths: 90/100 (90%) - Grade A
  - Science: 85/100 (85%) - Grade A
- **Overall**: 87.5% - Grade A

---

## 3. Sequence Diagram

### Scenario: Student Requests Grade Calculation

```
Student          GradeCalculator      Subject
  │                    │                 │
  │ calculateGrade()   │                 │
  │───────────────────>│                 │
  │                    │                 │
  │                    │ getSubjects()   │
  │                    │────────────────>│
  │                    │                 │
  │                    │<────────────────│
  │                    │ [subject list]  │
  │                    │                 │
  │                    │ For each subject│
  │                    │ ┌──────────────┐│
  │                    │ │ getMarks()   ││
  │                    │ │──────────────>│
  │                    │ │              ││
  │                    │ │<──────────────│
  │                    │ │ [marks value]││
  │                    │ └──────────────┘│
  │                    │                 │
  │                    │ calculateOverall│
  │                    │ [internal calc] │
  │                    │                 │
  │<───────────────────│                 │
  │ [grade result]     │                 │
  │                    │                 │
```

### Interaction Steps
1. Student requests grade calculation
2. GradeCalculator gets student's subjects
3. For each subject:
   - Get marks
   - Get maximum marks
   - Calculate percentage
4. GradeCalculator computes overall percentage
5. GradeCalculator determines grade (A, B, C, etc.)
6. Return result to Student

### Message Types
- **Solid arrows** (→): Synchronous calls
- **Dashed arrows** (⇢): Return messages
- **Activation bars**: Show when object is active
- **Loop**: Iterating through subjects

---

## Design Patterns Used

1. **Single Responsibility Principle**: Each class has one clear purpose
2. **Separation of Concerns**: Business logic separated from data
3. **Encapsulation**: Private data with public accessors
4. **Aggregation**: Loose coupling between Student and Subject
