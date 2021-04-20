 package leen.agrupar;
 
public class Student
{
    private String name;
    private String grade;
    private int marks;
 
    Student(String name, String grade, Integer marks)
    {
        this.name = name;
        this.grade = grade;
        this.marks = marks;
    }
 
    public String getName() { return name; }
    public String getGrade() { return grade; }
    public int getMarks() { return marks; }
 
    @Override
    public String toString() { return getName(); }
}