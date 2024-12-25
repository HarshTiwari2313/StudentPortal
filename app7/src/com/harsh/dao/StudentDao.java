package com.harsh.dao;

import com.harsh.beans.Student;

public interface StudentDao {
    public String add(Student student);
    public Student search(String sid);
    public String delete(String sid);
    public String update(Student student);
}
