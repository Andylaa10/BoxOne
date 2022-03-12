package grademanager.bll;

import grademanager.be.Grade;

public class SevenPointGradeScale implements IGradeScale
{
    @Override
    public String[] getAllGrades()
    {
        return new String[] { "-3", "00", "2", "4", "7", "10", "12" };
    }

    @Override
    public Grade createGrade(String studentName, String gradeDescription) {
        switch (gradeDescription)
        {
            case FAILED_DESCRIPTION:
                return new Grade(studentName, "-3");
            case BELOW_AVERAGE_DESCRIPTION:
                return new Grade(studentName, "2");
            case AVERAGE_DESCRIPTION:
                return new Grade(studentName, "7");
            case PERFECT_DESCRIPTION:
                return new Grade(studentName, "12");
        }
        return null;
    }
}
