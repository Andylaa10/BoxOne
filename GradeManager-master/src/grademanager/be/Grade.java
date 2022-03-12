package grademanager.be;

public class Grade
{
    private String studentName;
    private String grade;

    public Grade(String studentName, String grade)
    {
        this.studentName = studentName;
        this.grade = grade;
    }

    public String getStudentName()
    {
        return studentName;
    }

    public void setStudentName(String studentName)
    {
        this.studentName = studentName;
    }

    public String getGrade()
    {
        return grade;
    }

    public void setGrade(String grade)
    {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return studentName + ": " + grade;
    }
}
