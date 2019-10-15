package cz.it4i.fiji.ij1_mpi_wrapper.test;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import cz.it4i.fiji.ij1_mpi_wrapper.MPIWrapper;

public class progressReportTest {

	@Test
	public void progressShouldBeReportedOnlyIfTaskExists() {
		MPIWrapper.resetState();
		MPIWrapper.reportProgress(0, 100);
	}

	@Test
	public void tasksShouldBeReportedOnlyIfAtLeastOneExists() {
		MPIWrapper.resetState();
		MPIWrapper.reportTasks();
	}

	@Test
	public void tasksShouldBeReportedOnlyOnce() {
		MPIWrapper.resetState();
		MPIWrapper.addTask("A task");
		MPIWrapper.reportTasks();
		MPIWrapper.reportTasks();
	}

	@Test
	public void addingTasksAfterTheyHaveBeenReportedShouldNotBePossible() {
		MPIWrapper.resetState();
		MPIWrapper.addTask("A task");
		MPIWrapper.reportTasks();
		MPIWrapper.reportTasks();
		MPIWrapper.addTask("A second task");
	}

	@Test
	public void correctIdShouldBeAssignedToTaskWhenAdded() {
		MPIWrapper.resetState();
		MPIWrapper.addTask("Task one.");
		MPIWrapper.addTask("Task two.");
		int id = MPIWrapper.addTask("Task three.");
		assertEquals(2, id);
	}
}