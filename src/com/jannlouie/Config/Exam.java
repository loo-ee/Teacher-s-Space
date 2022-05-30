package com.jannlouie.Config;

import java.util.Vector;

public class Exam {
    private int ID;
    private float maxScore;
    private Vector<Float> studentScores = new Vector<>();

    public Exam(int ID, float maxScore) {
        this.ID = ID;
        this.maxScore = maxScore;
    }

    public void setVector(Vector<Float> s) {
        this.studentScores = s;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return this.ID;
    }

    public void setMaxScore(float maxScore) {
        this.maxScore = maxScore;
    }

    public void addStudentScore(float score) {
        studentScores.add(score);
    }

    public float getMaxScore() {
        return this.maxScore;
    }

    public Vector<Float> getStudentScoresRecord() {
        return this.studentScores;
    }

    public float getStudentScore(int index) {
        return studentScores.get(index);
    }

    public int getRecordSize() {
        return studentScores.size();
    }

    public String toString() {
        return "Exam #" + this.ID;
    }
}
