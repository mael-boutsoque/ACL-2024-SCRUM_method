package fr.ul.acl.model;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class GraphePathfinding extends Hitscanner{
	
	private Map<GrapheWaypoint, List<GrapheWaypoint>> graphe_map = new HashMap<GrapheWaypoint, List<GrapheWaypoint>>();
	private Map<GrapheWaypoint, List<Float>> graphe_distance_map = new HashMap<GrapheWaypoint, List<Float>>();
	private Map<GrapheWaypoint, GrapheWaypoint> shortest_path_map = new HashMap<GrapheWaypoint, GrapheWaypoint>();
	private Map<GrapheWaypoint, Float> distance_to_source_map = new HashMap<GrapheWaypoint, Float>();
	private GrapheWaypoint player_pos;
	
	public GraphePathfinding(ArrayList<Entity> obstacles) {
		super(obstacles);
	}
	
	public void add_waypoint(GrapheWaypoint node) {
		this.graphe_map.put(node, new ArrayList<>());
		this.graphe_distance_map.put(node, new ArrayList<>());
	}
	
	public void add_waypoint(int x, int y) {
		GrapheWaypoint node = new GrapheWaypoint(x, y);
		this.add_waypoint(node);
	}
	
	public Map<GrapheWaypoint, List<GrapheWaypoint>> get_graphe_map(){
		return this.graphe_map;
	}
	
	public Map<GrapheWaypoint, List<Float>> get_graphe_distance_map(){
		return this.graphe_distance_map;
	}
	
	public Map<GrapheWaypoint, GrapheWaypoint> get_shortest_path_map(){
		return this.shortest_path_map;
	}
	
	public Map<GrapheWaypoint, Float> get_distance_to_source_map(){
		return this.distance_to_source_map;
	}
		
	public void generate_edjes() {
		
		for (GrapheWaypoint current_node:this.graphe_map.keySet()) {
			this.set_current_pos(current_node.x, current_node.y);
			for (GrapheWaypoint target_node:this.graphe_map.keySet()) {
				//if (!target_node.equals(current_node)) {
					this.set_target_pos(target_node.x, target_node.y);
					if (!this.test_all()) {
						this.graphe_map.get(current_node).add(target_node);
						this.graphe_distance_map.get(current_node).add(current_node.distance_to(target_node));
					}
				//}
			}
		}
	}
	
	public void generate_shortest_paths(GrapheWaypoint starting_point) {
		this.shortest_path_map.clear();
		this.distance_to_source_map.clear();
		this.shortest_path_map.put(starting_point, null);
		this.distance_to_source_map.put(starting_point,(float) 0);
		
		while (true) {
			float smallest_distance = Float.MAX_VALUE;
			boolean variation = false;
			GrapheWaypoint closest_node = null;
			GrapheWaypoint marked_node = null;
			
			for (GrapheWaypoint current_node : this.shortest_path_map.keySet()) {
				for (int i=0; i < this.graphe_map.get(current_node).size(); i++) {
					
					float distance_to_source = this.distance_to_source_map.get(current_node) + this.graphe_distance_map.get(current_node).get(i);
					if (distance_to_source < smallest_distance && !(this.shortest_path_map.containsKey(this.graphe_map.get(current_node).get(i)))) {
						marked_node = current_node;
						smallest_distance = distance_to_source;
						closest_node = this.graphe_map.get(current_node).get(i);
						variation = true;
					}
				}
			}
			if (!variation) {break;}
			else {
				this.shortest_path_map.put(closest_node, marked_node);
				this.distance_to_source_map.put(closest_node, smallest_distance);
			}
		}	
	}
	
	public void locate_player(Entities entities) {
		Entity e = new Entity(-entities.map.get_x() + 768, -entities.map.get_y() + 432, 0, 0);
		if (this.player_pos == null) {
			this.player_pos = locate_closest_node_init(e);
		}
		else {
			this.player_pos = locate_closest_node(e);
		}
		
	}
	
	public GrapheWaypoint get_player_pos() {
		return this.player_pos;
	}
	
	public GrapheWaypoint locate_closest_node_init(Entity Entity) {
		GrapheWaypoint closest_node = new GrapheWaypoint(0, 0);
		float shortest_distance = Float.MAX_VALUE;
		this.set_current_pos(Entity.x, Entity.y);
		//System.out.println(Entity.x);
		//System.out.println(Entity.y);
		//System.out.println("");
		for (GrapheWaypoint node : graphe_map.keySet()) {
			this.set_target_pos(node.x, node.y);
			//System.out.println(node.x);
			//System.out.println(node.y);
			//System.out.println("");

			if (!this.test_all()) {
				float distance = Entity.distance_to(node);
				//System.out.println(distance);
				//System.out.println("");
					if (distance < shortest_distance) {
						shortest_distance = distance;
						closest_node = node;
						
					}
			}	
		}
		//System.out.println(closest_node.x);
		//System.out.println(closest_node.y);
		//System.out.println("");
		return closest_node;
	}
	
	public GrapheWaypoint locate_closest_node(Entity Entity) {
		GrapheWaypoint closest_node = new GrapheWaypoint(0, 0);
		float shortest_distance = Float.MAX_VALUE;
		this.set_current_pos(Entity.x, Entity.y);
		//System.out.println(Entity.x);
		//System.out.println(Entity.y);
		//System.out.println("");
		for (GrapheWaypoint node : graphe_map.get(player_pos)) {
			this.set_target_pos(node.x, node.y);
			//System.out.println(node.x);
			//System.out.println(node.y);
			//System.out.println("");
			
			float distance = Entity.distance_to(node);
			//System.out.println(distance);
			//System.out.println("");
				if (distance < shortest_distance) {
					shortest_distance = distance;
					closest_node = node;
						
				}
				
		}
		//System.out.println(closest_node.x);
		//System.out.println(closest_node.y);
		//System.out.println("----");
		return closest_node;
	}
	
	public void generate_path_to_player() {
			this.generate_shortest_paths(this.player_pos);
	}
}
