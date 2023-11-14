package assign02;

import java.util.ArrayList;

public class CS2420Student extends UofUStudent {
    private String firstName, lastName;
    private int uNID;
    private EmailAddress contactInfo;
    private ArrayList<Double> assignmentScores, examScores, labScores, quizScores;

    public CS2420Student(String firstName, String lastName, int uNID, EmailAddress contactInfo)
    {
        super(firstName, lastName, uNID);
        this.contactInfo = contactInfo;
        assignmentScores = new ArrayList<>();
        examScores = new ArrayList<>();
        labScores = new ArrayList<>();
        quizScores = new ArrayList<>();
    }

    public EmailAddress getContactInfo(){return this.contactInfo;}

    public void addScore(double score, String category)
    {
        switch(category)
        {
            case "assignment":
                assignmentScores.add(score);
                break;

            case "exam":
                examScores.add(score);
                break;

            case "lab":
                labScores.add(score);
                break;

            case "quiz":
                quizScores.add(score);
                break;

            default:
                break;
        }

    }

    public double computeFinalScore()
    {
        // if the student does not have at least one score in each category
        if(examScores.size() == 0 || assignmentScores.size() == 0 || quizScores.size() == 0 || labScores.size() == 0)
            return 0.0;

        // Compute average of exam scores
        double examSum = 0.0;
        double examCount = 0.0;
        for(Double x : examScores)
        {
            examSum += x;
            examCount++;
        }
        double examAverage = examSum / examCount;

        // if the average of exam scores is less than 65, return that value as final score
        if(examAverage < 65)
            return Math.round(examAverage * 10000.0) / 10000.0;

        // Compute average of assignment scores
        double assignmentSum = 0.0;
        double assignmentCount = 0.0;
        for(Double x : assignmentScores)
        {
            assignmentSum += x;
            assignmentCount++;
        }
        double assignmentAverage = assignmentSum / assignmentCount;

        // Compute average of quiz scores
        double quizSum = 0.0;
        double quizCount = 0.0;
        for(Double x : quizScores)
        {
            quizSum += x;
            quizCount++;
        }
        double quizAverage = quizSum / quizCount;

        // Compute average of lab scores
        double labSum = 0.0;
        double labCount = 0.0;
        for(Double x : labScores)
        {
            labSum += x;
            labCount++;
        }
        double labAverage = labSum / labCount;

        double finalScore = examAverage * 0.45 + assignmentAverage  * 0.35 + quizAverage * 0.1 + labAverage * 0.1;
        return Math.round(finalScore * 10000.0) / 10000.0;
    }


    public String computeFinalGrade()
    {
        if(examScores.size() == 0 || assignmentScores.size() == 0 || quizScores.size() == 0 || labScores.size() == 0)
            return "N/A";

        double finalScore = this.computeFinalScore();
        if(finalScore >= 93)
            return "A";
        else if(finalScore >= 90)
            return "A-";
        else if(finalScore >= 87)
            return "B+";
        else if(finalScore >= 83)
            return "B";
        else if(finalScore >= 80)
            return "B-";
        else if(finalScore >= 77)
            return "C+";
        else if(finalScore >= 73)
            return "C";
        else if(finalScore >= 70)
            return "C-";
        else if(finalScore >= 67)
            return "D+";
        else if(finalScore >= 63)
            return "D";
        else if(finalScore >= 60)
            return "D-";
        else
            return "E";
    }

}

