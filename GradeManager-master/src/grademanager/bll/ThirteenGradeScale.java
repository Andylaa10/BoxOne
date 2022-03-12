package grademanager.bll;

import grademanager.be.Grade;

public class ThirteenGradeScale implements IGradeScale
{
    @Override
    public String[] getAllGrades() {
        return new String[] { "00", "03", "5", "6", "7", "8", "9", "10", "11", "13" };
    }

    @Override
    public Grade createGrade(String studentName, String gradeDescription) {
        switch (gradeDescription)
        {
            case FAILED_DESCRIPTION:
                return new Grade(studentName, "03");
            case BELOW_AVERAGE_DESCRIPTION:
                return new Grade(studentName, "6");
            case AVERAGE_DESCRIPTION:
                return new Grade(studentName, "8");
            case PERFECT_DESCRIPTION:
                return new Grade(studentName, "13");
        }
        return null;
    }
}
