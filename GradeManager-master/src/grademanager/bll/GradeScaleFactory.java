package grademanager.bll;

public class GradeScaleFactory
{
    public static final String SEVEN_POINT_SCALE = "7 point scale";
    public static final String THIRTEEN_SCALE = "13 scale";

    public static IGradeScale getGradeScale(String scaleDescription)
    {
        switch (scaleDescription)
        {
            case SEVEN_POINT_SCALE:
                return new SevenPointGradeScale();
            case THIRTEEN_SCALE:
                return new ThirteenGradeScale();
        }
        return null;

    }
}
