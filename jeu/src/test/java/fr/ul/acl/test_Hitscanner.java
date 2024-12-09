package fr.ul.acl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import org.junit.BeforeClass;
import org.junit.Test;
import fr.ul.acl.model.Invisible;
import fr.ul.acl.model.Entity;
import fr.ul.acl.model.Hitscanner;

public class test_Hitscanner {

	static Hitscanner scanner;
	static Invisible wall;
	static ArrayList<Entity> liste_obstacles = new ArrayList<Entity>();
	
	@BeforeClass
	public static void init() {
		wall = new Invisible(5, -5, 5, 10);
		liste_obstacles.add(wall);
		liste_obstacles.add(new Invisible(15, -5, 4, 10));
		liste_obstacles.add(new Invisible(25, -5, 4, 10));
		scanner = new Hitscanner(liste_obstacles);
	}
	
	@Test
	public void test_liste() {
		assertEquals(liste_obstacles.size(), 3);
	}
	
	@Test
	public void should_detect_1() {
		scanner.set_current_pos(0, 0);
		scanner.set_target_pos(12, 0);
		assertTrue(scanner.test_intersect(wall));
	}
	
	@Test
	public void should_detect_2() {
		scanner.set_current_pos(7, 10);
		scanner.set_target_pos(6, -10);
		assertTrue(scanner.test_intersect(wall));
	}
	
	@Test
	public void should_detect_vertical() {
		scanner.set_current_pos(7, 10);
		scanner.set_target_pos(7, -10);
		assertTrue(scanner.test_intersect(wall));
	}
	
	@Test
	public void should_detect_horizontal() {
		scanner.set_current_pos(0, 1);
		scanner.set_target_pos(7, 1);
		assertTrue(scanner.test_intersect(wall));
	}
	
	@Test
	public void should_detect_diagonal_1() {
		scanner.set_current_pos(7, 6);
		scanner.set_target_pos(11, 0);
		assertTrue(scanner.test_intersect(wall));
	}
	
	@Test
	public void should_detect_diagonal_2() {
		scanner.set_current_pos(7, 6);
		scanner.set_target_pos(0, 0);
		assertTrue(scanner.test_intersect(wall));
	}
	

	@Test
	public void should_detect_diagonal_3() {
		scanner.set_current_pos(4, 3);
		scanner.set_target_pos(6, 6);
		assertTrue(scanner.test_intersect(wall));
	}

	@Test
	public void shouldnt_detect_horizontal_above() {
		scanner.set_current_pos(0, 6);
		scanner.set_target_pos(15, 6);
		assertFalse(scanner.test_intersect(wall));
	}
	
	@Test
	public void shouldnt_detect_horizontal_below() {
		scanner.set_current_pos(0, -6);
		scanner.set_target_pos(15, -6);
		assertFalse(scanner.test_intersect(wall));
	}
	
	@Test
	public void shouldnt_detect_horizontal_right() {
		scanner.set_current_pos(12, 0);
		scanner.set_target_pos(15, 0);
		assertFalse(scanner.test_intersect(wall));
	}
	
	@Test
	public void shouldnt_detect_horizontal_left() {
		scanner.set_current_pos(0, 0);
		scanner.set_target_pos(-5, 0);
		assertFalse(scanner.test_intersect(wall));
	}
	
	@Test
	public void shouldnt_detect_vertical_above() {
		scanner.set_current_pos(7, 6);
		scanner.set_target_pos(7, 10);
		assertFalse(scanner.test_intersect(wall));
	}
	
	@Test
	public void shouldnt_detect_vertical_below() {
		scanner.set_current_pos(7, -6);
		scanner.set_target_pos(7, -10);
		assertFalse(scanner.test_intersect(wall));
	}
	
	@Test
	public void shouldnt_detect_vertical_left() {
		scanner.set_current_pos(15, 5);
		scanner.set_target_pos(15, -5);
		assertFalse(scanner.test_intersect(wall));
	}
	
	@Test
	public void shouldnt_detect_vertical_right() {
		scanner.set_current_pos(0, 5);
		scanner.set_target_pos(0, -5);
		assertFalse(scanner.test_intersect(wall));
	}
	
	@Test
	public void shouldnt_detect_diagonal_1() {
		scanner.set_current_pos(3, 3);
		scanner.set_target_pos(7, 7);
		assertFalse(scanner.test_intersect(wall));
	}
	@Test
	public void shouldnt_detect_diagonal_2() {
		scanner.set_current_pos(7, 7);
		scanner.set_target_pos(4, 10);
		assertFalse(scanner.test_intersect(wall));
	}
	
	@Test
	public void on_the_edge_top() {
		scanner.set_current_pos(6, 5);
		scanner.set_target_pos(9, 5);
		assertTrue(scanner.test_intersect(wall));
	}
	
	@Test
	public void on_the_edge_side() {
		scanner.set_current_pos(5, 4);
		scanner.set_target_pos(5, -4);
		assertTrue(scanner.test_intersect(wall));
	}
	

	@Test
	public void no_obstacles() {
		scanner.set_current_pos(0, 0);
		scanner.set_target_pos(1, 0);
		assertFalse(scanner.test_all());
	}

	@Test
	public void one_obstacle_1() {
		scanner.set_current_pos(0, 0);
		scanner.set_target_pos(12, 0);
		assertTrue(scanner.test_all());
	}
	
	@Test
	public void one_obstacle_2() {
		scanner.set_current_pos(20, 0);
		scanner.set_target_pos(12, 0);
		assertTrue(scanner.test_all());
	}

	@Test
	public void two_obstacles() {
		scanner.set_current_pos(0, 0);
		scanner.set_target_pos(20, 0);
		assertTrue(scanner.test_all());
	}

}	
