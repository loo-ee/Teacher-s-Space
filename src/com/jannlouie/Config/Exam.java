package com.jannlouie.Config;

import java.util.Vector;

public class Exam {
    private int ID;
    private float maxScore;
    private Vector<Float> studentScores = new Vector<>();
    private Vector<String> studentNames = new Vector<>();

    public Exam(int ID, float maxScore) {
        this.ID = ID;
        this.maxScore = maxScore;
    }

    public void setScoresVector(Vector<Float> s) {
        this.studentScores = s;
    }
    public void setStudentNamesVector (Vector<String> n) {
        this.studentNames = n;
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

    public void addStudentName(String name) {
        studentNames.add(name);
    }

    public float getMaxScore() {
        return this.maxScore;
    }

    public Vector<Float> getStudentScoresRecord() {
        return this.studentScores;
    }

    public Vector<String> getStudentNamesRecord() {
        return this.studentNames;
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
