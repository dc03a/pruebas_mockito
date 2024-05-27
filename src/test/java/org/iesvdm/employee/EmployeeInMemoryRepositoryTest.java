package org.iesvdm.employee;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test doubles that are "fakes" must be tested
 *
 *
 */
public class EmployeeInMemoryRepositoryTest {

	private EmployeeInMemoryRepository employeeRepository;

	private List<Employee> employees;

	@BeforeEach
	public void setup() {
		employees = new ArrayList<>();
		employeeRepository = new EmployeeInMemoryRepository(employees);
	}

	/**
	 * Descripcion del test:
	 * crea 2 Employee diferentes
	 * aniadelos a la coleccion de employees
	 * comprueba que cuando llamas a employeeRepository.findAll
	 * obtienes los empleados aniadidos en el paso anterior
	 */
	@Test
	public void testEmployeeRepositoryFindAll() {
		Employee employee1 = new Employee("Paco sanz", 2500.0);
		Employee employee2 = new Employee("Pedro Picapiedra", 3500.0);

		employees.addAll(asList(employee1, employee2));
		List<Employee> empleados = employeeRepository.findAll();

		assertThat(empleados).hasSize(2);
		assertThat(empleados).contains(employee1, employee2);
	}

	/**
	 * Descripcion del test:
	 * salva un Employee mediante el metodo
	 * employeeRepository.save y comprueba que la coleccion
	 * employees contiene solo ese Employee
	 */
	@Test
	public void testEmployeeRepositorySaveNewEmployee() {
		Employee employee1 = new Employee("Paco sanz", 2500.0);

		employeeRepository.save(employee1);
		List<Employee> empleados = employeeRepository.findAll();

		assertThat(empleados).hasSize(1);
		assertThat(empleados).contains(employee1);
	}

	/**
	 * Descripcion del tets:
	 * crea un par de Employee diferentes
	 * aniadelos a la coleccion de employees.
	 * A continuacion, mediante employeeRepository.save
	 * salva los Employee anteriores (mismo id) con cambios
	 * en el salario y comprueba que la coleccion employees
	 * los contiene actualizados.
	 */
	@Test
	public void testEmployeeRepositorySaveExistingEmployee() {
		Employee employee1 = new Employee("Paco sanz", 2500.0);
		Employee employee2 = new Employee("Pedro Picapiedra", 3500.0);

		employees.addAll(asList(employee1, employee2));

		employee1.setSalary(4000.0);
		employeeRepository.save(employee1);

		List<Employee> empleados = employeeRepository.findAll();

		assertThat(empleados).hasSize(2);
		assertThat(empleados).containsExactly(employee1, employee2);
		assertThat(empleados.getFirst().getSalary()).isEqualTo(4000.0);
	}
}
