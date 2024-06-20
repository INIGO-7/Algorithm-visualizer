# Pathfinding Algorithm Vizualizer 🚶🗺️
<br>
<p align="center">Random mazes are generated to visualize the traced paths by each of the implemented pathfinding algorithms.</p>

![algorithmVisualizerMainMenu](https://github.com/INIGO-7/Algorithm-visualizer/assets/58185185/04f51504-21f9-497e-b071-0fe796bc1699)

## 🚀 Pathfinding Algorithms

In this project, I've implemented four powerful pathfinding algorithms that help navigate through mazes and grids. Here's a brief overview of each one:

### 🌐 Breadth-First Search (BFS)
**Breadth-First Search** explores all nodes at the present depth level before moving on to nodes at the next depth level. It’s perfect for finding the shortest path in an unweighted graph, guaranteeing the shortest distance in terms of number of edges.

- **Pros:** Guarantees shortest path, simple to implement.
- **Cons:** Can be memory-intensive on large graphs.

### 🧭 Depth-First Search (DFS)
**Depth-First Search** dives deep into the graph, following each path to its end before backtracking. It's great for exploring all possible paths but doesn't guarantee the shortest path.

- **Pros:** Low memory usage, simple to implement.
- **Cons:** Doesn’t guarantee shortest path, can get stuck in deep or infinite paths.

### ⭐ A* Search
**A* Search** is a superstar in the world of pathfinding algorithms. It uses heuristics to guide its search, combining the benefits of Dijkstra’s algorithm and Greedy Best-First-Search. It’s efficient and often finds the shortest path quickly.

- **Pros:** Efficient, guarantees shortest path (with an admissible heuristic).
- **Cons:** Performance depends on the heuristic, can be more complex to implement.

### 🛤️ Dijkstra’s Algorithm
**Dijkstra’s Algorithm** is a classic algorithm for finding the shortest path in a weighted graph. It explores all possible paths from the start node, ensuring the shortest path is found.

- **Pros:** Guarantees shortest path, works with any non-negative weights.
- **Cons:** Can be slower and more memory-intensive than A* in some cases.
---
## 📖 Usage

In the main menu, click on "GO". Then you'll see the following screen:

![algorithmVisualizerAfterMainMenu](https://github.com/INIGO-7/Algorithm-visualizer/assets/58185185/38cd9fff-9836-492b-84e6-8c68761b1819)

After that, click on "GENERATE" in order to create a whole new maze. The green dot is the starting poing, and the blue one will be the destination.

![algorithmVisualizerNewMaze](https://github.com/INIGO-7/Algorithm-visualizer/assets/58185185/bf83502c-5bf3-427b-a1d0-bb83653275b4)

Lastly, select your preferred algorithm by clicking on it, and then click "VISUALIZE" to see how it figures out its route to the destination.

![algorithmVisualizerSolvingMaze-ezgif com-optimize](https://github.com/INIGO-7/Algorithm-visualizer/assets/58185185/fe7bad01-c61c-4877-8ad4-9c6f92b2f92f)

Press "CLEARVIS" to clear the visualization and try to solve the same maze, with a different algorithm.

### Another option

Apart from the creation of random mazes, we can create our own ones! Instead of pressing "GENERATE", click on "PAINT"

![algorithmVisualizerPaintMode](https://github.com/INIGO-7/Algorithm-visualizer/assets/58185185/fcca6a87-be6e-48da-8c4c-1ac727542fac)

Then move around the origin and destination nodes (right click to delete a node, left click to place selected one), and place walls in between to obstaculize them and force them to observe different solutions (drag to place multiple walls).

![algorithmVisualizerCustomMaze](https://github.com/INIGO-7/Algorithm-visualizer/assets/58185185/4d2d0276-da40-4e65-bb1f-136f5f4a2d81)

- A* Solution

![algorithmVisualizerCustomMazeASolution](https://github.com/INIGO-7/Algorithm-visualizer/assets/58185185/5d591575-1a0a-451c-87a3-a21308cfd37f)

- BFS Solution

![algorithmVisualizerCustomMazeBFSSolution](https://github.com/INIGO-7/Algorithm-visualizer/assets/58185185/a2ea20d8-9b26-4de1-beba-63d484ebce13)

---

**Enjoy this application to better understand the behavior of these algorithms!**
