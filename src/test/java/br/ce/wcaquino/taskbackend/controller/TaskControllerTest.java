package br.ce.wcaquino.taskbackend.controller;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class TaskControllerTest {

    @Mock
    TaskRepo repo;

    @InjectMocks
    TaskController controller;

    @Before
    public void setUp(){
        initMocks(this);
    }

    @Test
    public void naoDeveSalvarTarefaSemDescricao(){
        Task task = new Task();
        task.setDueDate(LocalDate.now());
        try {
            controller.save(task);
            Assert.fail("Não deveria ter chegado nesse ponto");
        } catch (ValidationException e) {
            Assert.assertEquals("Fill the task description", e.getMessage());
        }
    }

    @Test
    public void naoDeveSalvarTarefaComDescricaoVazia(){
        Task task = new Task();
        task.setDueDate(LocalDate.now());
        task.setTask("");
        try {
            controller.save(task);
            Assert.fail("Não deveria ter chegado nesse ponto");
        } catch (ValidationException e) {
            Assert.assertEquals("Fill the task description", e.getMessage());
        }
    }

    @Test
    public void naoDeveSalvarTarefaSemData(){
        Task task = new Task();
        task.setTask("task");
        try {
            controller.save(task);
            Assert.fail("Não deveria ter chegado nesse ponto");
        } catch (ValidationException e) {
            Assert.assertEquals("Fill the due date", e.getMessage());
        }
    }

    @Test
    public void naoDeveSalvarTarefaComDataPassada(){
        Task task = new Task();
        task.setTask("task");
        task.setDueDate(LocalDate.of(2010,01,01));
        try {
            controller.save(task);
            Assert.fail("Não deveria ter chegado nesse ponto");
        } catch (ValidationException e) {
            Assert.assertEquals("Due date must not be in past", e.getMessage());
        }
    }

    @Test
    public void deveSalvarTarefaComSucesso() throws ValidationException {
        Task task = new Task();
        task.setTask("task");
        task.setDueDate(LocalDate.now());
        controller.save(task);
        verify(repo).save(task);
    }
}
