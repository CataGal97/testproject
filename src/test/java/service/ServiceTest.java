package service;

import domain.Student;
import junit.framework.AssertionFailedError;
import org.junit.Assert;
import org.junit.runner.RunWith;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.ValidationException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class ServiceTest {
    private Service service;
    private void initializeService(){
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "fisiere/Studenti.xml";
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";

        //StudentFileRepository studentFileRepository = new StudentFileRepository(filenameStudent);
        //TemaFileRepository temaFileRepository = new TemaFileRepository(filenameTema);
        //NotaValidator notaValidator = new NotaValidator(studentFileRepository, temaFileRepository);
        //NotaFileRepository notaFileRepository = new NotaFileRepository(filenameNota);

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
        this.service = service;
    }

    @org.junit.Test
    public void addStudent() {
          initializeService();
          Student jhonny = new Student("1", "Jhonny", 333, "nu");
          assertEquals(service.addStudent(jhonny).getNume(), "Jhonny");
    }
    @org.junit.Test
    public void addStudentFail(){
        initializeService();
        Student failed = new Student("2", "Garcea", 2323, "");
        try{
            service.addStudent(failed);
        }catch (Exception e){
            assertEquals(e.getMessage(), "Email incorect!");
        }


    }

    @org.junit.Test
    public void getAllStudenti() {
        initializeService();
        int number = 0;
        List<Student> students = new ArrayList<Student>();

//        service.getAllStudenti().forEach(
//                s->students.add(s)
//        );
        assertEquals(students.size(), 4);
    }
}
