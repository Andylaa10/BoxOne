package grademanager.bll;

import grademanager.be.Grade;

public interface IGradeScale
{
    String FAILED_DESCRIPTION = "Failed";
    String BELOW_AVERAGE_DESCRIPTION = "Below average";
    String AVERAGE_DESCRIPTION = "Average";
    String PERFECT_DESCRIPTION = "Perfect";

    String[] getAllGrades();
    Grade createGrade(String studentName, String gradeDescription);
}
