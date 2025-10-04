package de.dhbwka.java.musterklausurenselbsterstellt.SpeedyQuiz;

public class Question {
    private String questionText;
    private String[] answers;
    private int correctIndex;
    private Status questionStatus;

    public Status getQuestionStatus() {
        return questionStatus;
    }

    public void setQuestionStatus(Status questionStatus) {
        this.questionStatus = questionStatus;
    }

    public Question(String questionText, String[] answers, int correctIndex) {
        this.questionText = questionText;
        this.answers = answers;
        this.correctIndex = correctIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    public int getCorrectIndex() {
        return correctIndex;
    }

    public void setCorrectIndex(int correctIndex) {
        this.correctIndex = correctIndex;
    }
}
