/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leen.agrupar;

// Java program to group students by grades using `Collectors.groupingBy()`
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
//https://www.techiedelight.com/collectors-groupingby-method-java/
class Main
{
    public static void main(String[] args)
    {
        List<Student> students = Arrays.asList(new Student("Tom", "A+", 90),
                new Student("Lisa", "A+", 98),
                new Student("John", "A", 85),
                new Student("Joe", "A", 80),
                new Student("Jason", "E", 35));
 
        Map<String, List<Student>> studentsByGrade
                = students.stream()
                .collect(Collectors.groupingBy(Student::getGrade));
 
        for (Map.Entry<String, List<Student>> entry: studentsByGrade.entrySet()) {
            System.out.println("Students with " + entry.getKey() +
                    " grade are " + entry.getValue());
        }
    }
}
