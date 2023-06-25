Using breadth first search to find the shortest path in an unweighted graph is O(v + e), and using dijkstra's shortest path algorithm is O((v + e)logv). Since bfs moves traverses the graph and moves closer to the target node one vertex at a time, you know that whatever path was being taken when you reach the target first is the shortest path. The bfs-with-weights algorithm converts the weights into spacer nodes (nodes that are just meant to take up space) so that bfs can be used on a weighted graph. Depending one the values of the weights, doing so can be faster than dijkstra's algorithm, so i provide a method to compare the cost of the bfs-with-weights algorithm with dijkstra's for the state the graph is currently in (based on data collected while vertexes and edges were added to the graph), and the program will ru whichever algorithm would be faster.
<br><br>
Some notes about the graph and the bfs-with-weights algorithm:
<br>
-It's a directed graph, and loops and parallel edges are allowed.
<br>
-A Linked list is used to implement the queue for the bfs, and the nodes hold more than just a reference to the next node in the queue. Each node has a reference to the node before it in the path, so when he target vertex is found it can be wrapped in a node and returned to the user so the shortest path can be retraced.
<br>
-The weights must be whole numbers, because there's no way to handle getting a weight of 1.5. I can't be able to create one and a half edges.
<br>
-The vertexes have IDs so the uer can request operations done to vertexes and edges by specifying the vertexes invloved without having or needing access to these vertexes, thus isolating the access of data.
<br>
<br>
The following is an example of the bfs-with-weights algorithm running.
A is the source vertex, and D is the target vertex.
I will show what the queue looks like at each step, and I will outline the details with commentary.
<br>
![image](https://github.com/mosheWarshaw/bfsWithWeights/assets/113654579/aa121369-cca0-43db-bc6e-94f16eaf6cd2)
<br>
1)
<br>
Queue: A
<br>
Commentary: A is added to the queue.
<br>
2)
<br>
Queue: B -> C
<br>
Commentary: A is dequeued, and B and C are enqueued.
<br>
3)
<br>
Queue: C -> s1B
<br>
Commentary: B is dequeued and since the weight to C is more than 1, spacer nodes have to be created. Putting 2 spacer nodes between B and D will create the 3 edges needed between these 2 vertexes. s1B is the fist spacer node from B to D.
<br>
4)
<br>
Queue: s1B -> s1C
<br>
Commentary: C is dequeued, and a spacer node is created ad enqueued.
<br>
5)
<br>
Queue: s1C -> s2B
<br>
Commentary: s1B is dequeued and s2B is enqueued (the amount of spacer nodes needing to be created before reaching the adjacent vertex is kept track of in the spacer node).
<br>
6)
<br>
Commentary: s1c is dequeued, D is found, and the algorithm has completed.
<br>
<br>
In the end, the graph was treated as if it looked like:
<br>
![image](https://github.com/mosheWarshaw/bfsWithWeights/assets/113654579/759074ff-77ea-4871-8974-3a07b50d6b26)
