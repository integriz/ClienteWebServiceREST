package com.mmarques.controller;

import com.mmarques.bo.Aluno;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "alunoes")
@XmlAccessorType(XmlAccessType.FIELD)
public class Alunos {
    @XmlElement(name="aluno")
    private List<Aluno> alunoList;
    
    public List<Aluno> getAlunoList(){
        return alunoList;
    }
    
    public void setEmployeeList(List<Aluno> alunoList){
        this.alunoList = alunoList;
    }
    
}
