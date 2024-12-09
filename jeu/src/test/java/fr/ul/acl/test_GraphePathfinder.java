package fr.ul.acl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.ul.acl.model.Entity;
import fr.ul.acl.model.GraphePathfinding;
import fr.ul.acl.model.GrapheWaypoint;
import fr.ul.acl.model.Invisible;

public class test_GraphePathfinder {
	
	
	static ArrayList<Entity> liste_obstacles = new ArrayList<Entity>();
	static GraphePathfinding graphe;
	static GrapheWaypoint node1;
	static GrapheWaypoint node2;
	static GrapheWaypoint node3;
	
	@BeforeClass
	public static void init() {
		liste_obstacles.add(new Invisible(5, -5, 5, 10));
		liste_obstacles.add(new Invisible(15, -5, 4, 10));
		liste_obstacles.add(new Invisible(25, -5, 4, 10));
	}
	
	@Before
	public void init_each() {
		graphe = new GraphePathfinding(liste_obstacles);
	}
	
	@Test
	public void add_nodes() {
		node1 = new GrapheWaypoint(0, 0);
		graphe.add_waypoint(node1);
		assertTrue(graphe.get_graphe_map().containsKey(node1));
	}
	
	@Test
	public void should_create_edje() {
		node1 = new GrapheWaypoint(0, 0);
		node2 = new GrapheWaypoint(1, 0);
		graphe.add_waypoint(node1);
		graphe.add_waypoint(node2);
		graphe.generate_edjes();
		assertTrue(graphe.get_graphe_map().get(node1).contains(node2));
		assertTrue(graphe.get_graphe_map().get(node2).contains(node1));
	}
	
	@Test
	public void verify_distance() {
		node1 = new GrapheWaypoint(-4, -3);
		node2 = new GrapheWaypoint(-2, -1);
		graphe.add_waypoint(node1);
		graphe.add_waypoint(node2);
		graphe.generate_edjes();
		double a = graphe.get_graphe_distance_map().get(node1).get(0);
		double b = graphe.get_graphe_distance_map().get(node2).get(0);
		assertEquals(a, 2*Math.sqrt(2), 0.0001);
		assertEquals(a, b, 0.0001);
	}
	
	@Test
	public void should_create_single_edje() {
		node1 = new GrapheWaypoint(0, 0);
		node2 = new GrapheWaypoint(12, 0);
		node3 = new GrapheWaypoint(12, 3);
		graphe.add_waypoint(node1);
		graphe.add_waypoint(node2);
		graphe.add_waypoint(node3);
		graphe.generate_edjes();
		assertFalse(graphe.get_graphe_map().get(node1).contains(node2));
		assertFalse(graphe.get_graphe_map().get(node2).contains(node1));
		assertTrue(graphe.get_graphe_map().get(node3).contains(node2));
		assertTrue(graphe.get_graphe_map().get(node2).contains(node3));
	}
	
	@Test
	public void should_create_two_edjes() {
		node1 = new GrapheWaypoint(4, 3);
		node2 = new GrapheWaypoint(7, 6);
		node3 = new GrapheWaypoint(4, 6);
		graphe.add_waypoint(node1);
		graphe.add_waypoint(node2);
		graphe.add_waypoint(node3);
		graphe.generate_edjes();
		assertFalse(graphe.get_graphe_map().get(node1).contains(node2));
		assertFalse(graphe.get_graphe_map().get(node2).contains(node1));
		assertTrue(graphe.get_graphe_map().get(node3).contains(node2));
		assertTrue(graphe.get_graphe_map().get(node2).contains(node3));
		assertTrue(graphe.get_graphe_map().get(node3).contains(node1));
		assertTrue(graphe.get_graphe_map().get(node1).contains(node3));
	}
	
	@Test
	public void create_shortest_path() {
		node1 = new GrapheWaypoint(1, 0);
		node2 = new GrapheWaypoint(4, 0);
		graphe.add_waypoint(node1);
		graphe.add_waypoint(node2);
		graphe.generate_edjes();
		graphe.generate_shortest_paths(node1);
		assertEquals(graphe.get_shortest_path_map().size(), 2);
	}
	
	@Test
	public void create_shortest_path_two_points() {
		node1 = new GrapheWaypoint(1, 0);
		node2 = new GrapheWaypoint(4, 0);
		graphe.add_waypoint(node1);
		graphe.add_waypoint(node2);
		graphe.generate_edjes();
		graphe.generate_shortest_paths(node1);
		assertTrue(graphe.get_shortest_path_map().get(node2).equals(node1));
	}
	
	@Test
	public void create_shortest_path_three_points_1() {
		node1 = new GrapheWaypoint(1, 0);
		node2 = new GrapheWaypoint(4, 0);
		node3 = new GrapheWaypoint(2, 1);
		graphe.add_waypoint(node1);
		graphe.add_waypoint(node2);
		graphe.add_waypoint(node3);
		graphe.generate_edjes();
		graphe.generate_shortest_paths(node1);
		assertTrue(graphe.get_shortest_path_map().get(node2).equals(node1));
		assertTrue(graphe.get_shortest_path_map().get(node3).equals(node1));
	}
	
	@Test
	public void create_shortest_path_three_points_2() {
		node1 = new GrapheWaypoint(1, 0);
		node2 = new GrapheWaypoint(2, 0);
		node3 = new GrapheWaypoint(3, 1);
		graphe.add_waypoint(node1);
		graphe.add_waypoint(node2);
		graphe.add_waypoint(node3);
		graphe.generate_edjes();
		graphe.generate_shortest_paths(node1);
		assertTrue(graphe.get_shortest_path_map().get(node2).equals(node1));
		assertTrue(graphe.get_shortest_path_map().get(node3).equals(node1));
	}

	@Test
	public void assign_node_to_entity() {
		node1 = new GrapheWaypoint(1, 0);
		node2 = new GrapheWaypoint(2, 0);
		node3 = new GrapheWaypoint(3, 0);
		graphe.add_waypoint(node1);
		graphe.add_waypoint(node2);
		graphe.add_waypoint(node3);
		Entity entity = new Entity(1, 1, 0, 0);
		GrapheWaypoint closest_node = graphe.locate_closest_node(entity);
		assertEquals(closest_node, node1);
	}
	
	@Test
	public void assign_node_to_entity_with_wall() {
		node1 = new GrapheWaypoint(0, 0);
		node2 = new GrapheWaypoint(6, 5);
		graphe.add_waypoint(node1);
		graphe.add_waypoint(node2);
		Entity entity = new Entity(4, 4, 0, 0);
		GrapheWaypoint closest_node = graphe.locate_closest_node(entity);
		assertEquals(closest_node, node1);
	
	}
}
